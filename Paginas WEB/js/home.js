//<!--https://jsonplaceholder.typicode.com/users/-->
var jsonC;var jsonM;
$(document).ready(function(){
	google.charts.load('current', {'packages':['gauge']});
	google.charts.setOnLoadCallback(drawChart);
	empresa();
	maquinas(1);
    agoravai();
	function drawChart() {
		var settings = {
		"async": false,
		"url": "http://imperius.azurewebsites.net/api/View/CarregaLeitura",
		"method": "post",
		"dataType": "json",
		"headers": {
			"Cache-Control": "no-cache",
			"Postman-Token": "df98e587-f312-4bab-b892-cab75b40d4be"
			}
		}
		
		$.ajax(settings).done(function (response) {
			jsonC =response[0];
		});
			
        var data = google.visualization.arrayToDataTable([
          ['Label', 'Value'],
          ['Memory', jsonC['Cpu']],
          ['CPU', jsonC['Mram']],
          ['Disk', jsonC['Hd']]
        ]);

        var options = {
          width: 0, height: 300,
          redFrom: 90, redTo: 100,
          yellowFrom:75, yellowTo: 90,
		  greenFrom:0,greenTo:75,
          minorTicks: 20
        };

        var chart = new google.visualization.Gauge(document.getElementById('chart_div'));

        chart.draw(data, options);

        setInterval(function() {
          chart.draw(data, options);
        }, 1000);
    }
	
	function empresa() {
		var maq = {
		"url": "http://imperius.azurewebsites.net/api/View/CarregaEmpresa",
		"method": "post",
		"dataType": "json",
		"headers": {
			"Cache-Control": "no-cache",
			"Postman-Token": "df98e587-f312-4bab-b892-cab75b40d4be"
			}
		}
		
		$.ajax(maq).done(function (response) {
			var view = "\n";
			for (var i in response) {
			view += '	<a class="dropdown-item" id="Escolha" value='+response[i].idGrupo+' href="#" >'+response[i].Nome_grupo +'</a> ';
			}
			view += "\n";
			document.getElementById('empresa').innerHTML = view;
		}, 
		function (errorCode, errorText) {
			console.log('Código: ' + errorCode);
			console.log('Mensagem de erro: ' + errorText);
		});
	}
	
	
	function maquinas(grupo) {
		
		var settings = {
			"async": true,
			"crossDomain": true,
			"url": "http://imperius.azurewebsites.net/api/View/CarregaMaquina?grupo="+grupo+"",
			"method": "POST",
			"headers": {
				"Cache-Control": "no-cache",
				"Postman-Token": "3bb6501c-abdb-43ed-8bef-db3d106374be"
			}
		}
		
		$.ajax(settings).done(function (response) {
			var keepAlive="btn btn-lg btn-danger";
			jsonM = response;
			var view = "\n";
			for (var i in response) {
				
				if(response[i].Keep_Alive == 1){
					keepAlive = "btn btn-lg btn-success";
				}
					
				view += `\t<button type="button" class="${keepAlive} + mostra"  value=${i}><i class="maquina material-icons"  style="font-size: 300%;">computer</i> <br><span style="font-size: small;">${response[i].Nome_Maquina}</span></button>`;
				keepAlive="btn btn-lg btn-danger";
			}
			view += "\n";
			document.getElementById('Tela').innerHTML = view;
		});
		
		
		
		
	}
	function agoravai(){
        $(".dropdown-item").on("click", function () {
            var NomeEmpresa = $(this).text();
            console.log('aa');
            var Cod = $(this).attr('value');
            maquinas(Cod);
            document.getElementById('Letreiro1').innerHTML = NomeEmpresa;
            agoravai();
        });

        $('.mostra').on("click", function(){
            var local = $(this).attr('value');
            document.getElementById('Letreiro2').innerHTML = jsonM[local].Nome_Maquina;
            var CriaInfo= '<h1>data de aquisição: '+jsonM[local].Adiquirida+'</h1><h1>Responsavel: '+jsonM[local].Responsavel+'</h1><h1>Sistema Atual: '+jsonM[local].Sistema+'</h1><h1>Cod da maquina: '+jsonM[local].idMaquina+'</h1>';            document.getElementById('chart_info').innerHTML = CriaInfo;
        });
	}

});
