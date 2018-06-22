var jsonC;
var jsonM;
var maquina;
var jsonA;
$(document).ready(function () {
	maquinas(Cookie("Grupo_Cliente"));
	$(".alert").hide();
	setInterval(function () {
		drawChart(maquina);
	}, 1000);
	$(".nano").nanoScroller();
	$('.pane').show();
	Sair();
	salvaAviso();
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

function Sair() {

	$('#btnSair').on('click', function () {
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
		"url": "http://imperius.azurewebsites.net/api/View/CarregaLeitura?escolhida=" + mostrar + "",
		"method": "post",
		"dataType": "json",
		"headers": {
			"Cache-Control": "no-cache",
			"Postman-Token": "df98e587-f312-4bab-b892-cab75b40d4be"
		}
	}

	$.ajax(settings).done(function (response) {
		if (response.length == 0) {
			return null;
		}
		jsonC = response[0];
	}).fail(function (response) {});

	if (jsonC != null) {

		var data = google.visualization.arrayToDataTable([
			['Label', 'Value'],
			['Memory', jsonC['Mram']],
			['CPU', jsonC['Cpu']],
			['Disk', jsonC['Hd']]
		]);

		var options = {
			width: 0,
			height: 300,
			redFrom: 90,
			redTo: 100,
			yellowFrom: 75,
			yellowTo: 90,
			greenFrom: 0,
			greenTo: 75,
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
		"url": "http://imperius.azurewebsites.net/api/View/CarregaMaquina?grupo=" + grupo + "",
		"method": "POST",
		"headers": {
			"Cache-Control": "no-cache",
			"Postman-Token": "3bb6501c-abdb-43ed-8bef-db3d106374be"
		}
	}

	$.ajax(settings).done(function (response) {
		console.log(response);
		var keepAlive = "btn btn-lg offline";
		jsonM = response;
		document.getElementById('Letreiro1').innerHTML = jsonM[0].Nome_grupo;
		var view = "\n";

		for (var i in response) {

			if (response[i].Keep_Alive == 1) {
				keepAlive = "btn btn-lg online";
			}

			view += `\t<button type="button" class="${keepAlive} mostra" id="maq${response[i].idMaquina}"   value=${i}><i class="maquina material-icons md-48" >computer</i> <br><span style="font-size: small;">${response[i].Nome_Maquina.substring(0, 8)}</span></button>`;
			keepAlive = "btn btn-lg offline";
		}

		view += "\n";
		document.getElementById('Letreiro2').innerHTML = "Escolha a maquina ao lado";
		document.getElementById('Tela').innerHTML = view;
		$(".nano").nanoScroller();
		$(function () {

			$('.btn').on("click", function () {
				var local = $(this).attr('value');
				$("#chart_info").show();
				var settings = {
					"async": true,
					"crossDomain": false,
					"url": "https://imperius.azurewebsites.net/api/Coleta/CarregaAviso?maquina=" + jsonM[local].idMaquina + "",
					"method": "POST"

				}

				$.ajax(settings).done(function (response) {
					josnA = response;
					if (response.length > 0) {
						var data = response;
						var selectbox = $('#Avisos');
						//selectbox.find('option').remove();

						$.each(data, function (i, d) {

							$('<option>').val(i).text(d.NomeAviso).appendTo(selectbox);

						});

						$('#Avisos').on('change', function () {
							var selecionado = $(this).find(":selected").val();
							if(selecionado < 999){
								$('#txtCpuInicio').val(josnA[selecionado].AvisoI1);
								$('#txtCpuFim').val(josnA[selecionado].AvisoF1);
								$('#txtMemoInicio').val(josnA[selecionado].AvisoI2);
								$('#txtMemoFim').val(josnA[selecionado].AvisoF2);
								$('#txtHdInicio').val(josnA[selecionado].AvisoI3);
								$('#txtHdFim').val(josnA[selecionado].AvisoF3);
							} else {
								$('#txtCpuInicio').val("");
								$('#txtCpuFim').val("");
								$('#txtMemoInicio').val("");
								$('#txtMemoFim').val("");
								$('#txtHdInicio').val("");
								$('#txtHdFim').val("");
							}
						});
					} else {
						$('#txtCpuInicio').val("");
						$('#txtCpuFim').val("");
						$('#txtMemoInicio').val("");
						$('#txtMemoFim').val("");
						$('#txtHdInicio').val("");
						$('#txtHdFim').val("");
					}

					document.getElementById('Letreiro2').innerHTML = jsonM[local].Nome_Maquina + " Cod: " + jsonM[local].idMaquina;
					$('#txtAdiquirida').val(jsonM[local].Adquirida);
					$('#txtResponsavel').val(jsonM[local].Responsavel);
					$('#txtCompra').val(jsonM[local].Data_Compra);
					$('#txtSistema').val(jsonM[local].Sistema);
					//document.getElementById('chart_info').innerHTML = CriaInfo;
					google.charts.load('current', {
						'packages': ['gauge']
					});
					maquina = jsonM[local].idMaquina;
					google.charts.setOnLoadCallback(drawChart(maquina));
				});
			});

		});
	}).fail(function (response) {

		window.location.href = "index.html";

	});


}
$(function () {
	$('#btnDelete').on('click', function () {
		var x;
		var r = confirm("Tem certeza que deseja excluir essa maquina ?");
		if (r == true) {
			x = "você pressionou OK!";
			var settings = {

				"url": "http://imperius.azurewebsites.net/api/view/ExcluiMaquina?maquina="+maquina+"",
				"method": "POST",

			}

			$.ajax(settings).done(function (response) {
				$(".alert").show();
				$(".alert").css('position', 'absolute');
				setTimeout(function () {
					window.location.reload(1);
				}, 1000);
			});
		}

	});
	
	

});

function salvaAviso(){
	
			$('#btnSalvar').on('click', function () {
			var x;
			var r = confirm("Tem certeza que deseja Alterar o aviso ?");
			
			if($('#Avisos').length > 1){
				var id =josnA[selecionado].idAviso;
				var nome = $('#Avisos').text();
			}
			else{
				var nome = "";
			}
			var selecionado = $(this).find(":selected").val();
			
			var cpui = $('#txtCpuInicio').val();
			var cpuf = $('#txtCpuFim').val();
			var memoriai = $('#txtMemoInicio').val();
			var memoriaf = $('#txtMemoFim').val();
			var hdi = $('#txtHdInicio').val();
			var hdf = $('#txtHdFim').val();



			if (r == true) {
				x = "você pressionou OK!";
				var settings = {
	
					"url": "http://imperius.azurewebsites.net/api/Coleta/SalvaAviso?aviso",
					"method": "POST",
					"processData": false,
					"data": "{\"idAviso\":"+id+",\"NomeAviso\":\""+nome+"\",\"AvisoI1\":"+cpui+",\"AvisoI2\":"+memoriai+",\"AvisoI3\":"+hdi+",\"AvisoF1\":"+cpuf+",\"AvisoF2\":"+memoriaf+",\"AvisoF3\":"+hdf+",\"Maquina_Aviso\":"+maquina+"}"

				
				}
	
				$.ajax(settings).done(function (response) {
					$(".alert").show();
					$(".alert").css('position', 'absolute');
					setTimeout(function () {
						window.location.reload(1);
					}, 1000);
				});
			}
		})
	
	
	
}