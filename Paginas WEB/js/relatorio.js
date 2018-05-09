$(document).ready (function () {
	geraRelatorio();
	function geraRelatorio (){
            $.ajax({
                url: "https://jsonplaceholder.typicode.com/users/",
                type: "GET",
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
                            { "data": "id" },
							{ "data": "name" },
							{ "data": "username" },
							{ "data": "email" },
							{ "data": "address" }
                        ]
                    });
                },
                error: function () {
                    alert("Erro de Carregamento de Dados");
                }
            });
	}
});