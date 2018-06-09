var grupo;var cod=1;
$(document).ready (function () {
	$(".alert").hide();
	grupo = Cookie('Grupo_Cliente');
	$(".nano").nanoScroller();
	$('.pane').show();
	
	Usuario(grupo);
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
			window.location.href = "../index.html";
		});
	}
	
function Usuario(grupo) {
		
		var settings = {
			"async": true,
			"crossDomain": false,
			"url": "http://imperius.azurewebsites.net/api/View/CarregaClienteGrupo?idGrupo="+grupo+"",
			"method": "GET"
		}
		
		$.ajax(settings).done(function (response) {
			var acesso ="btn btn-lg adm";
			jsonM = response;
            document.getElementById('Letreiro1').innerHTML = jsonM[0].Nome_grupo;
			var view = "\n";
			
			for (var i in response) {
				
				if(response[i].idAcesso == 2){
					keepAlive = "btn btn-lg user";
				}
					
				view += `\t<button type="button" class="${acesso} mostra"  value=${i}><i class="maquina material-icons md-48" >perm_identity</i> <br><span style="font-size: small;">${response[i].Nome}</span></button>`;
				keepAlive="btn btn-lg adm";
			}
			
			view += "\n";
			document.getElementById('Letreiro2').innerHTML = "Gerenciamento de usuario";
			document.getElementById('dados').innerHTML = view;
			$(".nano").nanoScroller();
			$(function(){
				
				$('.btn').on("click", function(){
					var local = $(this).attr('value');
					$('#txtCod').val(jsonM[local].idCliente.toString())
					$('#txtNome').val(jsonM[local].Nome.toString())
					$('#txtEmail').val(jsonM[local].Email.toString())
					$('#txtSenha').val(jsonM[local].Senha.toString());
					$('#txtSenhaConf').val(jsonM[local].idCliente.toString());
				});
		
			});
		}).fail(function(response){
			
			window.location.href = "../index.html";

		});

            
	}