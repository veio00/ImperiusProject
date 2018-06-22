var grupo;
var cod = 1;
$(document).ready(function () {
	grupo = Cookie('Grupo_Cliente');
	CarregaCombo(grupo);
	Botoes();
	$(".alert").hide();
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

function Botoes() {

	$('#btnLeitura').on("click", function () {
		$('#btnGerar').show();
		Relatorio('Leitura', grupo, cod);
	});

	$('#btnLogs').on("click", function () {
		$('#btnGerar').hide();
		Relatorio('Logs', grupo, cod);
	});

	$('#btnInventario').on("click", function () {
		$('#btnGerar').hide();
		Relatorio('Inventario', grupo, cod);
	});
	$('#btnSair').on('click', function () {
		var cookies = document.cookie.split(";");

		for (var i = 0; i < cookies.length; i++) {
			var cookie = cookies[i];
			var eqPos = cookie.indexOf("=");
			var name = eqPos > -1 ? cookie.substr(0, eqPos) : cookie;
			document.cookie = name + "=;expires=Thu, 01 Jan 1970 00:00:00 GMT";
		}
		window.location.href = "../index.html";
	});
}


function Relatorio(relatorio, empressa, maquina) {
	var settings;
	var tabela;
	switch (relatorio) {
		case "Leitura":
			$('#btnGerar').show();
			$('#empresa').show();
			$('#tbLeitura_wrapper').show();
			$('#tbInventario_wrapper').hide();
			$('#tbLogs_wrapper').hide();
			document.getElementById('nomeRelatorio').innerHTML = "Relatório de Leitura";
			settings = {
				"async": true,
				"crossDomain": false,
				"url": "http://imperius.azurewebsites.net/api/view/RelatorioLeitura?empresa=" + empressa + "&cod=" + cod + "",
				"Content-Type": "application/json; charset=utf-8",
				"method": "GET",
				"headers": {
					"Cache-Control": "no-cache",
					"Postman-Token": "5a170e39-2218-a3f9-dcbb-f6cd553aa831"
				}
			}
			tabela = 'tbLeitura';
			break;
		case "Inventario":
			$('#btnGerar').hide();
			$('#empresa').hide();
			$('#tbLeitura_wrapper').hide();
			$('#tbInventario_wrapper').show();
			$('#tbLogs_wrapper').hide();
			document.getElementById('nomeRelatorio').innerHTML = "Relatório de Inventario";
			var settings = {
				"async": true,
				"crossDomain": false,
				"url": "http://imperius.azurewebsites.net/api/view/RelatorioInventario?empresa=1",
				"method": "GET",
				"headers": {
					"Cache-Control": "no-cache",
					"Postman-Token": "8c0727c4-93c4-4482-9a59-a42e1ed5c1d7"
				}
			}
			tabela = 'tbInventario';
			break;
		case "Logs":
			$('#btnGerar').hide();
			$('#empresa').hide();
			$('#tbLeitura_wrapper').hide();
			$('#tbInventario_wrapper').hide();
			$('#tbLogs_wrapper').show();
			document.getElementById('nomeRelatorio').innerHTML = "Relatório de Logs";
			var settings = {
				"async": true,
				"crossDomain": false,
				"url": "http://imperius.azurewebsites.net/api/view/RelatorioLogs?empresa=1",
				"method": "GET",
				"headers": {
					"Cache-Control": "no-cache",
					"Postman-Token": "b373bbbc-d438-4ba1-93b1-73275f39bacb"
				}
			}
			tabela = 'tbLogs';
			break;
		default:

	}

	$.ajax(settings).done(function (data) {
		criarCabecario(data, tabela);

		if (data.length > 0) {

			criarRelatorio(data, tabela);

		} else {

			$(".alert").show();
			$(".alert").css('position', 'absolute');
			$(tabela).dataTable().fnDestroy();

		}
	});
}

function CarregaCombo(grupo) {

	var settings = {
		"async": true,
		"crossDomain": false,
		"url": "http://imperius.azurewebsites.net/api/View/CarregaMaquina?grupo=" + grupo + "",
		"method": "POST",
		"headers": {
			"Cache-Control": "no-cache",
			"Postman-Token": "3bb6501c-abdb-43ed-8bef-db3d106374be"
		}
	}

	$.ajax(settings).done(function (response) {

		if (response != null) {
			var data = response;
			var selectbox = $('#empresa');
			selectbox.find('option').remove();

			$.each(data, function (i, d) {

				$('<option>').val(d.idMaquina).text(d.Nome_Maquina).appendTo(selectbox);

			});
		}
		$(function () {

			$('#empresa').on('change', function () {

				cod = $("#empresa").val();
				$(".alert").hide();
				Relatorio('Leitura', Cookie('Grupo_Cliente'), cod);
			});


		});
	}).fail(function (response) {
		console.log(response);
		window.location.href = "../index.html";

	});


}

function criarCabecario(data, tabela) {
	var view;
	switch (tabela) {
		case "tbLeitura":

			if (document.getElementById('trLeituraCabecario').innerHTML == "\n\t\t\t\t\t\t\t\n\t\t\t\t\t") {
				if (data.length > 0) {
					for (var i in data[0]) {

						view += "<th>" + i + " </th>";

					}

					view += "\n";
				}

				document.getElementById('trLeituraCabecario').innerHTML = view;
				document.getElementById('trLeituraRodape').innerHTML = view;
			}
			break;
		case "tbInventario":

			if (document.getElementById('trInventarioCabecario').innerHTML == "\n\t\t\t\t\t\t\t\n\t\t\t\t\t") {
				if (data.length > 0) {
					for (var i in data[0]) {

						view += "<th>" + i + " </th>";

					}

					view += "\n";
				}

				document.getElementById('trInventarioCabecario').innerHTML = view;
				document.getElementById('trInventarioRodape').innerHTML = view;
			}
			break;
		case "tbLogs":

			if (document.getElementById('trLogsCabecario').innerHTML == "\n\t\t\t\t\t\t\t\n\t\t\t\t\t") {
				if (data.length > 0) {
					for (var i in data[0]) {

						view += "<th>" + i + " </th>";

					}

					view += "\n";

				}

				document.getElementById('trLogsCabecario').innerHTML = view;
				document.getElementById('trLogsRodape').innerHTML = view;
			}
			break;
		default:


	}

}

function criarRelatorio(data, tabela) {
	console.log(tabela);
	var column_names = []; //Array com confi das colunas
	var jsonObj = data; //Parse no obj json, facilita o manuseio

	//Verifica se tem registro, para nao caga tudo
	if (jsonObj.length > 0) {

		//Monta array de colunas
		$.each(Object.keys(jsonObj[0]), function (idx, obj) {

			column_names.push({
				"sTitle": obj,
				"mData": obj
			});
		});

	}

	switch (tabela) {
		case "tbLeitura":

			$('#tbLeitura').DataTable({
				stateSave: true,
				//retrieve: true,
				paging: false,
				destroy: true,
				"language": {
					"lengthMenu": "Ver _MENU_ Por pagina",
					"zeroRecords": "Nada encontrado",
					"info": "Total de _PAGE_ de _PAGES_",
					"infoEmpty": "Nenhum Dado Encontrado",
					"search": "Filtrar:",
					"infoFiltered": "(filtered from MAX total records)",
					"paginate": {
						"first": "Primeira",
						"last": "Ultima",
						"next": "Proxima",
						"previous": "Anterior"
					},
					"processing": "DataTables is currently busy"
				},
				"dom": '<"top"f l>t<"bottom"ip >', //configuração de menu
				"pagingType": "full_numbers",
				"scrollX": true,
				"scrollY": false,
				scrollCollapse: true,
				paging: true,
				"aaData": jsonObj,
				"aoColumns": column_names

			});
			break;
		case "tbInventario":

			$('#tbInventario').DataTable({
				stateSave: true,
				//retrieve: true,
				paging: false,
				destroy: true,
				"language": {
					"lengthMenu": "Ver _MENU_ Por pagina",
					"zeroRecords": "Nada encontrado",
					"info": "Total de _PAGE_ de _PAGES_",
					"infoEmpty": "Nenhum Dado Encontrado",
					"search": "Filtrar:",
					"infoFiltered": "(filtered from MAX total records)",
					"paginate": {
						"first": "Primeira",
						"last": "Ultima",
						"next": "Proxima",
						"previous": "Anterior"
					},
					"processing": "DataTables is currently busy"
				},
				"dom": '<"top"f l>t<"bottom"ip >', //configuração de menu
				"pagingType": "full_numbers",
				"scrollX": true,
				"scrollY": false,
				scrollCollapse: true,
				paging: true,
				"aaData": jsonObj,
				"aoColumns": column_names

			});
			break;
		case "tbLogs":

			$('#tbLogs').DataTable({
				stateSave: true,
				//retrieve: true,
				paging: false,
				destroy: true,
				"language": {
					"lengthMenu": "Ver _MENU_ Por pagina",
					"zeroRecords": "Nada encontrado",
					"info": "Total de _PAGE_ de _PAGES_",
					"infoEmpty": "Nenhum Dado Encontrado",
					"search": "Filtrar:",
					"infoFiltered": "(filtered from MAX total records)",
					"paginate": {
						"first": "Primeira",
						"last": "Ultima",
						"next": "Proxima",
						"previous": "Anterior"
					},
					"processing": "DataTables is currently busy"
				},
				"dom": '<"top"f l>t<"bottom"ip >', //configuração de menu
				"pagingType": "full_numbers",
				"scrollX": true,
				"scrollY": false,
				scrollCollapse: true,
				paging: true,
				"aaData": jsonObj,
				"aoColumns": column_names

			});
			break;
		default:

	}
}