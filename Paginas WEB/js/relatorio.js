var grupo;var cod=1;
$(document).ready (function () {
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
function Botoes(){

	$('#btnLeitura').on("click", function(){
		var table = $('#example').DataTable();
		table.destroy();
		Relatorio('Leitura', grupo ,cod);
	});
		
	$('#btnLogs').on("click", function(){
		var table1 = $('#example').DataTable();
		table1.destroy();
		Relatorio('Logs', grupo ,cod);
	});
	
	$('#btnInventario').on("click", function(){
		

		Relatorio('Inventario', grupo ,cod);
	});

}



function Relatorio(relatorio, empressa,cod){
	var settings;
	switch(relatorio) {
		case "Leitura":
			$('#btnGerar').show();
			document.getElementById('nomeRelatorio').innerHTML = "Relatório de Leitura";
			settings = {
				"async": true,
				"crossDomain": false,
				"url": "http://localhost:3182/api/view/RelatorioLeitura?empresa="+empressa+"&cod="+cod+"",
				"Content-Type":"application/json; charset=utf-8",
				"method": "GET",
				"headers": {
					"Cache-Control": "no-cache",
					"Postman-Token": "5a170e39-2218-a3f9-dcbb-f6cd553aa831"
				}
			}
			break;
		case "Inventario":
			$('#btnGerar').hide();
			document.getElementById('nomeRelatorio').innerHTML = "Relatório de Leitura";
			var settings = {
				"async": true,
				"crossDomain": false,
				"url": "http://localhost:3182/api/view/RelatorioInventario?empresa=1",
				"method": "GET",
				"headers": {
					"Cache-Control": "no-cache",
					"Postman-Token": "8c0727c4-93c4-4482-9a59-a42e1ed5c1d7"
				}
			}
			break;
		case "Logs":
			$('#btnGerar').hide();
			document.getElementById('nomeRelatorio').innerHTML = "Relatório de Logs";
			var settings = {
				"async": true,
				"crossDomain": false,
				"url": "http://localhost:3182/api/view/RelatorioLogs?empresa=1",
				"method": "GET",
				"headers": {
					"Cache-Control": "no-cache",
					"Postman-Token": "b373bbbc-d438-4ba1-93b1-73275f39bacb"
				}
			}
			break;
		default:
        $('#btnGerar').hide();
	}
	
	$.ajax(settings).done(function (data) {
		criarCabecario(data);
		
		if(data.length > 0){
			
			criarRelatorio(data);
			
		}else{
			
			$(".alert").show();
			$(".alert").css('position', 'absolute');
			$("#example").dataTable().fnDestroy();
			
		}
	});
}

function CarregaCombo(grupo) {
	
	var settings = {
		"async": true,
		"crossDomain": false,
		"url": "http://imperius.azurewebsites.net/api/View/CarregaMaquina?grupo="+grupo+"",
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
		$(function(){
			
			$('#btnGerar').on("click", function(){
				
				cod = $("#empresa").val();
				$(".alert").hide();
				Relatorio(Cookie('Grupo_Cliente'),cod);
			});
			
			
	
		});
	}).fail(function(response){
		
		window.location.href = "file:///C:/Users/Will/OneDrive%20-%20Faculdade%20de%20Tecnologia%20Bandeirantes%20-%20BandTec/Imperius/ImperiusProject/Paginas%20WEB/index.html";

	});

           
}

function criarCabecario(data){
	var view;

	if(data.length >0){
		for (var i in data[0]) {
			
					
				view += "<th>" + i +" </th>";
	
			}
			view += "\n";
		
		document.getElementById('cabecario').innerHTML = view;
		document.getElementById('rodape').innerHTML = view;
	}else {
		view = "";
		document.getElementById('cabecario').innerHTML = view;
		document.getElementById('rodape').innerHTML = view;
	}
}

function criarRelatorio(data){
		
	var column_names = []; //Array com confi das colunas
	var jsonObj = data; //Parse no obj json, facilita o manuseio

	//Verifica se tem registro, para nao caga tudo
	if(jsonObj.length>0){
	
		//Monta array de colunas
		$.each(Object.keys(jsonObj[0]),function(idx,obj){
			
			column_names.push({"sTitle": obj, "mData":obj});
		});

	}
	$("#example").dataTable().fnDestroy();
	$('#example').DataTable({
		stateSave: true,
		"language": {
			"lengthMenu": "MENU",
			"zeroRecords": "Nada encontrado",
			"info": "Total de PAGE de PAGES",
			"infoEmpty": "Nenhum Dado Encontrado",
			"search": "Filtrar:",
			"infoFiltered": "(filtered from MAX total records)",
			"paginate": {
				"first": "Primeira",
				"last": "Ultima",
				"next": "Proxima",
				"previous": "Anterior"
			}
		},
		"dom": '<"top"f l>t<"bottom"ip >',//configuração de menu
		"pagingType": "full_numbers",
		"scrollX": true,
		"scrollY": false,
		scrollCollapse: true,
		paging: true,
		"aaData": jsonObj,
		"aoColumns":column_names
			
	});
}

