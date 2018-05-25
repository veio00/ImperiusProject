$(document).ready (function () {
	CarregaCombo(Cookie('Grupo_Cliente'));
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

function geraRelatorio(empressa,cod){
	var settings = {
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
		
	$.ajax(settings).done(function (data) {
		criarCabecario(data);
		
		if(data.length > 0){
		console.log(data);
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
				data: data,
				columns: [ //configuração de pesquisa das tabelas
					{ "data": "codigo" },
					{ "data": "Responsavel" },
					{ "data": "Data_Compra" },
					{ "data": "Sistema" },
					{ "data": "Hd" },
					{ "data": "Mram" },
					{ "data": "Cpu" },
					{ "data": "Data_Leitura" }
				]
			});
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
		console.log(response[0].Nome_grupo);
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
				var cod = $("#empresa").val();
				geraRelatorio(Cookie('Grupo_Cliente'),cod);
			});
	
		});
	}).fail(function(response){
		
		window.location.href = "file:///C:/Users/Will/OneDrive%20-%20Faculdade%20de%20Tecnologia%20Bandeirantes%20-%20BandTec/Imperius/ImperiusProject/Paginas%20WEB/index.html";

	});

           
}

function criarCabecario(data){
	var view;
		for (var i in data[0]) {
			
					
				view += "<th>" + i +" </th>";
	
			}
			view += "\n";
		
		document.getElementById('cabecario').innerHTML = view;
		document.getElementById('rodape').innerHTML = view;
		
	
}
