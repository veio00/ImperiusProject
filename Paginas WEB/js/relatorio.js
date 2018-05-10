$(document).ready (function () {
	geraRelatorio2();
	function geraRelatorio (){
            $.ajax({
                async: false,
				url: "http://imperius.azurewebsites.net/api/view/CarregaMaquina",
                data: {grupo:1},
				method: "POST",
				dataType: "json",
				headers: {
				"Cache-Control": "no-cache",
				
				},
                success: function (data) {
                    $('#example').DataTable({
                        stateSave: true,
                        "language": {
                            "lengthMenu": "Mostrar MENU",
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
                            { "data": "idMaquina" },
							{ "data": "Responsavel" },
							{ "data": "Nome_Maquina" },
							{ "data": "Adiquirida" },
							{ "data": "Sistema" },
							{ "data": "Keep_Alive" },
							{ "data": "Grupo_Cliente" },
							{ "data": "idGrupo" },
							{ "data": "Nome_Grupo" }
                        ]
                    });
                },
                error: function () {
                    alert("Erro de Carregamento de Dados");
                }
            });
	}
	
	function geraRelatorio2 (){
		var settings = {
  "async": true,
  "crossDomain": true,
  "url": "http://imperius.azurewebsites.net/api/view/CarregaMaquina?grupo=1",
  "method": "POST",
  "headers": {
    "Cache-Control": "no-cache",
    "Postman-Token": "5a170e39-2218-a3f9-dcbb-f6cd553aa831"
  }
}

$.ajax(settings).done(function (data) {
  console.log(data);
  $('#example').DataTable({
                        stateSave: true,
                        "language": {
                            "lengthMenu": "Mostrar MENU",
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
                            { "data": "idMaquina" },
							{ "data": "Responsavel" },
							{ "data": "Nome_Maquina" },
							{ "data": "Adiquirida" },
							{ "data": "Sistema" },
							{ "data": "Keep_Alive" },
							{ "data": "Grupo_Cliente" },
							{ "data": "idGrupo" },
							{ "data": "Nome_Grupo" }
                        ]
                    });
});
	}
});