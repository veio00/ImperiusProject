//<!--https://jsonplaceholder.typicode.com/users/-->
var jsonC;var jsonM;var maquina;
$(document).ready(function(){
	maquinas(Cookie("Grupo_Cliente"));
	$(".alert").hide();
	setInterval(function() {
        drawChart(maquina);
    }, 1000);
	$(".nano").nanoScroller();
	$('.pane').show();
	 Sair();
});
	function Cookie(name) {
		var cookies = document.cookie;
		var prefix = name + "=";
		var begin = cookies.indexOf("; " + prefix);
	
		if (begin == -1) {
	
			begin = cookies.indexOf(prefix);
			
			if (begin != 0) {
				return null;
			}
	
		} else {
			begin += 2;
		}
	
		var end = cookies.indexOf(";", begin);
		
		if (end == -1) {
			end = cookies.length;                        
		}
	
		return unescape(cookies.substring(begin + prefix.length, end));
	}
	
	function Sair(){

		$('#btnSair').on('click', function(){
			var cookies = document.cookie.split(";");

			for (var i = 0; i < cookies.length; i++) {
				var cookie = cookies[i];
				var eqPos = cookie.indexOf("=");
				var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
				document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
			}
			window.location.href = "index.html";
		});
	}
	
	function drawChart(mostrar) {
		
		var settings = {
		"async": false,
		"url": "http://imperius.azurewebsites.net/api/View/CarregaLeitura?escolhida="+mostrar+"",
		"method": "post",
		"dataType": "json",
		"headers": {
			"Cache-Control": "no-cache",
			"Postman-Token": "df98e587-f312-4bab-b892-cab75b40d4be"
			}
		}
		
		$.ajax(settings).done(function (response) {
			if(response.length == 0){
				return null;
			}
			jsonC =response[0];
		}).fail(function(response){});
		if(jsonC != null){

			var data = google.visualization.arrayToDataTable([
			['Label', 'Value'],
			['Memory', jsonC['Mram']],
			['CPU', jsonC['Cpu']],
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
		}
        
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
			var keepAlive="btn btn-lg offline";
			jsonM = response;
            document.getElementById('Letreiro1').innerHTML = jsonM[0].Nome_grupo;
			var view = "\n";
			
			for (var i in response) {
				
				if(response[i].Keep_Alive == 1){
					keepAlive = "btn btn-lg online";
				}
					
				view += `\t<button type="button" class="${keepAlive} mostra"  value=${i}><i class="maquina material-icons md-48" >computer</i> <br><span style="font-size: small;">${response[i].Nome_Maquina}</span></button>`;
				keepAlive="btn btn-lg offline";
			}
			
			view += "\n";
			document.getElementById('Letreiro2').innerHTML = "Escolha a maquina ao lado";
			document.getElementById('Tela').innerHTML = view;
			$(".nano").nanoScroller();
			$(function(){
				
				$('.btn').on("click", function(){
					var local = $(this).attr('value');
					document.getElementById('Letreiro2').innerHTML = jsonM[local].Nome_Maquina;
					var CriaInfo= '<h1>data de aquisição: '+jsonM[local].Adiquirida+'</h1><h1>Responsavel: '+jsonM[local].Responsavel+'</h1><h1>Sistema Atual: '+jsonM[local].Sistema+'</h1><h1>Cod da maquina: '+jsonM[local].idMaquina+'</h1>';           
					document.getElementById('chart_info').innerHTML = CriaInfo;
					google.charts.load('current', {'packages':['gauge']});
					maquina = jsonM[local].idMaquina;
					google.charts.setOnLoadCallback(drawChart(maquina));
				});
		
			});
		}).fail(function(response){
			
			window.location.href = "index.html";

		});

            
	}
	$(function(){
			$('#btnDelete').on('click' , function(){
				var x;
				var r=confirm("Tem certeza que deseja excluir essa maquina ?");
				if (r==true)
				{
					x="você pressionou OK!";
					var settings = {
					
					"url": "http://imperius.azurewebsites.net/api/view/ExcluiMaquina?maquina="+maquina+"",
					"method": "POST",
					
					}
					
					$.ajax(settings).done(function (response) {
						$(".alert").show();
						$(".alert").css('position', 'absolute');
						setTimeout(function(){
							window.location.reload(1);
						}, 1000);
					});
				}
			});

		});
	
