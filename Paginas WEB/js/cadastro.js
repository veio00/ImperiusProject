var grupo;
var cod = 1;
$(document).ready(function () {
	$(".alert").hide();
	grupo = Cookie('Grupo_Cliente');	
	$(".nano").nanoScroller();
	$('.pane').show();
	
	Usuario(grupo);
	botoes();
	
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

function botoes() {
	
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
						$('#txtNome').val("")
						$('#txtEmail').val("")
						$('#txtSenha').val("")
						$('#txtSenhaConf').val("")
						$("#nivel").val(0);
						$("#txtCod").val("")
						setTimeout(function(){
							window.location.reload(1);
						}, 1000);
					});
				}
			});
	
	$('#btnSalvar').on('click', function () {
			$("#nivel").val();
	
	
		if($('#nivel').val() == 0){
		
			$('#nivel').css('background', 'red');
			$('#nivel').css('color', 'white');
			
		}else if($('#txtNome').val() == ""){
		
			$('#txtNome').css('background', 'red');
			$('#txtNome').css('color', 'white');
			
		}else if($('#txtEmail').val() == ""){
		
			$('#txtEmail').css('background', 'red');
			$('#txtEmail').css('color', 'white');
		
		}else if($('#txtSenha').val() == ""){
		
			$('#txtSenha').css('background', 'red');
			$('#txtSenha').css('color', 'white');
			
		}else if($('#txtSenha').val() !=  $('#txtSenhaConf').val()){
			$('#txtSenha').css('background', 'red');
			$('#txtSenha').css('color', 'white');
			$('#txtSenhaConf').css('background', 'red');
			$('#txtSenhaConf').css('color', 'white');
			$(".alert").innerHTML("senha não conhecidem");
			$(".alert").show();
			setTimeout(function(){
				$(".alert").hide();
			}, 2000);
			
		}
		var nome = $('#txtNome').val()
		var email = $('#txtEmail').val()
		var senha = $('#txtSenha').val()
		var acesso = $("#nivel").val();
		var cod = $("#txtCod").val();
		
		if($('#txtCod').val() == ""){
			SalvaCliente(nome,email,senha,acesso,grupo);
		}else{
			AlteraCliente(cod, nome,email,senha,acesso,grupo);
		}
	});
}

function Usuario(grupo) {
	
	var settings = {
		"async": true,
		"crossDomain": false,
		"url": "http://imperius.azurewebsites.net/api/View/CarregaClienteGrupo?idGrupo=" + grupo + "",
		"method": "GET"
	}
	
	$.ajax(settings).done(function (response) {
		var acesso = "btn btn-lg adm";
		jsonM = response;
		document.getElementById('Letreiro1').innerHTML = jsonM[0].Nome_grupo;
		var view = "\n";
		
		for (var i in response) {
			
			if (response[i].idAcesso == 2) {
				keepAlive = "btn btn-lg user";
			}
			
			view += `\t<button type="button" class="${acesso} mostra"  value=${i}><i class="maquina material-icons md-48" >perm_identity</i> <br><span style="font-size: small;">${response[i].Nome}</span></button>`;
			keepAlive = "btn btn-lg adm";
		}
		
		view += "\n";
		document.getElementById('Letreiro2').innerHTML = "Gerenciamento de usuario";
		document.getElementById('dados').innerHTML = view;
		$(".nano").nanoScroller();
		$(function () {
			
			$('.btn').on("click", function () {
				var local = $(this).attr('value');
				$('#txtCod').val(jsonM[local].idCliente.toString())
				$('#txtNome').val(jsonM[local].Nome.toString())
				$('#txtEmail').val(jsonM[local].Email.toString())
				$('#txtSenha').val(jsonM[local].Senha.toString());
				$('#txtSenhaConf').val(jsonM[local].Senha.toString());
				$('#nivel').val(jsonM[local].idAcesso.toString());
				
			});
			
		});
		}).fail(function (response) {
		
		window.location.href = "../index.html";
		
	});
	
	
}

function SalvaCliente(Nome, Email, Senha, Acesso, Empresa){
	var settings = {
		"async": true,
		"crossDomain": false,
		"url": "http://imperius.azurewebsites.net/api/view/SalvaCliente",
		"method": "POST",
		"headers": {
			"Content-Type": "application/json",
			"Cache-Control": "no-cache",
			"Postman-Token": "61076739-19cf-4b00-9cd1-3805616b0793"
		},
		"processData": false,
		"data": "{\"Nome\": \""+Nome+"\",\"Senha\": \""+Senha+"\",\"Email\": \""+Email+"\",\"AcessoCliente\": \""+Acesso+"\",\"GrupoCliente\": \""+Empresa+"\"}"
	}
	
	$.ajax(settings).done(function (response) {
		var settings = {
			"async": true,
			"crossDomain": false,
			"url": "http://imperius.azurewebsites.net/api/view/alerta?msg=Ola "+Nome+", Cadastro%20realizado%20com%20sucesso,%20%20Obrigado%20por%20assianr%20o%20Imperius%20Project%20Monitor.%20http://imperius.azurewebsites.net/&email="+Email+"",
			"method": "POST",
			"headers": {
				"Cache-Control": "no-cache",
				"Postman-Token": "6ee5a559-8eaa-483b-a11d-34da97875325"
			}
		}
		
		$.ajax(settings).done(function (response) {

		});
		$("#alert").text("Usuario salvo com sucesso");
		$(".alert").show();
		setTimeout(function(){
			$(".alert").css('position', 'absolute');
			$(".alert").hide();
			$('#txtNome').val("")
			$('#txtEmail').val("")
			$('#txtSenha').val("")
			$('#txtSenhaConf').val("")
			$("#nivel").val(0);
			$("#txtCod").val("")
		}, 2000);
	});
	
}

function AlteraCliente(idCliente, Nome, Email, Senha, Acesso, Empresa){
		var settings = {
		"async": false,
		"crossDomain": false,
		"url": "http://imperius.azurewebsites.net/api/view/AlterarCliente",
		"method": "POST",
		"headers": {
			"Content-Type": "application/json",
			"Cache-Control": "no-cache",
			"Postman-Token": "61076739-19cf-4b00-9cd1-3805616b0793"
		},
		"processData": false,
		"data": "{\"idCliente\": \""+idCliente+"\",\"Nome\": \""+Nome+"\",\"Senha\": \""+Senha+"\",\"Email\": \""+Email+"\",\"AcessoCliente\": \""+Acesso+"\",\"GrupoCliente\": \""+Empresa+"\"}"
	}
	
	$.ajax(settings).done(function (response) {
			$("#alert").text("Alterações feita com sucesso");
			$(".alert").show();
			setTimeout(function(){
				$(".alert").css('position', 'absolute');
				$(".alert").hide();
				$('#txtNome').val("")
				$('#txtEmail').val("")
				$('#txtSenha').val("")
				$('#txtSenhaConf').val("")
				$("#nivel").val(0);
				$("#txtCod").val("")
			}, 2000);
	});
}

function ExcluirCliente(idCliente){
		var settings = {
		"async": false,
		"crossDomain": false,
		"url": "http://imperius.azurewebsites.net/api/view/ExcluirCliente",
		"method": "GET",
		"headers": {
			"Cache-Control": "no-cache",
			"Postman-Token": "61076739-19cf-4b00-9cd1-3805616b0793"
		}
	}
	
	$.ajax(settings).done(function (response) {
			$("#alert").text("Usuario excluido com sucesso");
			$(".alert").show();
			setTimeout(function(){
				$(".alert").css('position', 'absolute');
				$(".alert").hide();
				$('#txtNome').val("")
				$('#txtEmail').val("")
				$('#txtSenha').val("")
				$('#txtSenhaConf').val("")
				$("#nivel").val(0);
				$("#txtCod").val("")
			}, 2000);
	});
}
	


