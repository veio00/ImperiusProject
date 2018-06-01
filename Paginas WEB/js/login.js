function login(nome,senha) {

	var settings = {
		"url": "http://imperius.azurewebsites.net/api/view/Login?user="+nome+"&senha="+senha+"",
		"method": "post",
		"headers": {
			"Cache-Control": "no-cache",
			"Postman-Token": "8244fa6d-a5b1-41d3-898a-ce64debebb14"
		}
	}
	$.ajax(settings).done(function (response) {
		console.log(response);
		if(response.length > 0){
			for (var i in response[0]) {
				var valor;
				valor = createCookie(i,response[0][i])
				document.cookie = valor ;
				i++;
			}
			window.location.href = "principal.html";
		}else {
			alert("usuario e/ou senha");
		}
	});
}
$(function(){
   $('#b').on('click' , function(){
        
		var nome = document.getElementById("user").value;
		var senha = document.getElementById("senha").value;
		if(nome == "")
		{
			$('#user').css('border', 'red');
			$('#user').css('color', 'white');
			
		}
		else if(senha == "")
		{
			$('#senha').css('background', 'red');
			$('#senha').css('color', 'white');
		}
		else
		{
			login(nome,senha);
		}
		
		
   });
   $('#c').on('click' , function(){
        
		$('.modal').show();
		$('.modal').height('400');
		$('.entar').hide();
   });
   $('#d').on('click' , function(){
        if($('#nome').val() == "")
		{
			$('#nome').css('background', 'red');
			$('#nome').css('color', 'white');
			
		}
		else if($('#email').val() == "")
		{
			$('#email').css('background', 'red');
			$('#email').css('color', 'white');
		}
		else if($('#senhaCadastro').val() == "")
		{
			$('#senhaCadastro').css('background', 'red');
			$('#senhaCadastro').css('color', 'white');
		}
		else
		{
			$('.modal2').show();
			$('.modal').hide();
		}
   });
   $('#t').on('click' , function(){
		SalvaEmpresa($("#nomeEmpresa").val());
		$('.modal3').show();
		$('.modal2').hide();
		setTimeout(function(){
			window.location.reload(1);
		}, 2500);
   });

});

function createCookie(name,value) {
    
    var date = new Date();
    date.setTime(date.getTime()+(60*60000));
    var expires = "; expires="+date.toUTCString();
    
    console.log(date.toUTCString());
    console.log(name+"="+value+expires+";");
	return name+"="+value+expires+";";
    //document.cookie = name+"="+value+expires+";";
}

$.getScript("https://cdnjs.cloudflare.com/ajax/libs/particles.js/2.0.0/particles.min.js", function(){
    particlesJS('particles-js',
      {
        "particles": {
          "number": {
            "value": 80,
            "density": {
              "enable": true,
              "value_area": 800
            }
          },
          "color": {
            "value": "#d8b600"
          },
          "shape": {
            "type": "circle",
            "stroke": {
              "width": 0,
              "color": "#000000"
            },
            "polygon": {
              "nb_sides": 5
            },
            "image": {
              "width": 100,
              "height": 100
            }
          },
          "opacity": {
            "value": 0.5,
            "random": false,
            "anim": {
              "enable": false,
              "speed": 1,
              "opacity_min": 0.1,
              "sync": false
            }
          },
          "size": {
            "value": 5,
            "random": true,
            "anim": {
              "enable": false,
              "speed": 40,
              "size_min": 0.1,
              "sync": false
            }
          },
          "line_linked": {
            "enable": true,
            "distance": 150,
            "color": "#ffffff",
            "opacity": 0.4,
            "width": 1
          },
          "move": {
            "enable": true,
            "speed": 6,
            "direction": "none",
            "random": false,
            "straight": false,
            "out_mode": "out",
            "attract": {
              "enable": false,
              "rotateX": 600,
              "rotateY": 1200
            }
          }
        },
        "interactivity": {
          "detect_on": "canvas",
          "events": {
            "onhover": {
              "enable": true,
              "mode": "repulse"
            },
            "onclick": {
              "enable": true,
              "mode": "push"
            },
            "resize": true
          },
          "modes": {
            "grab": {
              "distance": 400,
              "line_linked": {
                "opacity": 1
              }
            },
            "bubble": {
              "distance": 400,
              "size": 40,
              "duration": 2,
              "opacity": 8,
              "speed": 3
            },
            "repulse": {
              "distance": 100
            },
            "push": {
              "particles_nb": 4
            },
            "remove": {
              "particles_nb": 2
            }
          }
        },
        "retina_detect": true,
        "config_demo": {
          "hide_card": false,
          "background_color": "#b61924",
          "background_image": "",
          "background_position": "50% 50%",
          "background_repeat": "no-repeat",
          "background_size": "cover"
        }
      }
    );

});

function SalvaCliente(Nome, Email, Senha, Empresa){
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
		"data": "{\"Nome\": \""+Nome+"\",\"Senha\": \""+Senha+"\",\"Email\": \""+Email+"\",\"AcessoCliente\": \"1\",\"GrupoCliente\": \""+Empresa+"\"}"
		}
		
		$.ajax(settings).done(function (response) {
			console.log(response);
			var settings = {
				"async": true,
				"crossDomain": true,
				"url": "http://imperius.azurewebsites.net/api/view/alerta?msg=Ola "+Nome+", Cadastro%20realizado%20com%20sucesso,%20%20Obrigado%20por%20assianr%20o%20Imperius%20Project%20Monitor.%20http://imperius.azurewebsites.net/&email="+Email+"",
				"method": "POST",
				"headers": {
					"Cache-Control": "no-cache",
					"Postman-Token": "6ee5a559-8eaa-483b-a11d-34da97875325"
			}
		}

			$.ajax(settings).done(function (response) {
				console.log(response);
			});
		});
}
function SalvaEmpresa(Empresa){
	
	var settings = {
		"async": true,
		"crossDomain": false,
		"url": "http://imperius.azurewebsites.net/api/view/SalvaEmpresa?nome="+Empresa+"",
		"method": "GET",
		"headers": {
			"Cache-Control": "no-cache",
			"Postman-Token": "dd0ecad2-0ff2-43c1-bdde-0a8a6fec2ab3"
		}
	}

	$.ajax(settings).done(function (response) {
		console.log(response);
		var Nome = $("#nome").val();
		var Email = $("#email").val();
		var Senha = $("#senhaCadastro").val();
		var empresa =response[0].idGrupo;
		SalvaCliente(Nome,Email,Senha,empresa);
	});
}