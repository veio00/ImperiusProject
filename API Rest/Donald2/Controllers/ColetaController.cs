using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using API.Models;
using Swashbuckle.Swagger.Annotations;
using System.Runtime.Serialization.Json;
using System.Text;
using System.IO;
using System.Web.Script.Serialization;
using System.Web.UI.WebControls;
using Newtonsoft.Json;
using System.Web.Http.Description;
using API.Banco;
using System.Data;
using System.Web.Http.Cors;
using API.Notificacoes;

namespace API.Controllers
{
    [EnableCors(origins: "*", headers: "*", methods: "*")]
    public class ColetaController : ApiController 
    {
        // GET api/Coleta
        [SwaggerOperation("GetAll")]
        [HttpGet]
        //[Produces("application/json")]
        public IEnumerable<string> Get()
        {
            return new string[] {
                "Get:                    https://imperius.azurewebsites.net/api/Coleta/Get Mostra todos os caminhos da API da parte de coleta",
                "POST:LeituraAgora:      https://imperius.azurewebsites.net/api/Coleta/LeituraAgora Salva Leitura vinda do coletor em forma de json para classe de leitura",
                "POST:KeepAlive:         https://imperius.azurewebsites.net/api/Coleta/KeepAlive Recebe sinal das maquinas ativas no momento (fora do ar no momento)",
                "POST:InfoMaquina:       https://imperius.azurewebsites.net/api/Coleta/InfoMaquina Salva uma MAquina nova vinda do coletor em forma de json para classe de Maquina",
                "POST:InfoProcessador:   https://imperius.azurewebsites.net/api/Coleta/InfoProcessador Salva as informaçoes de processador de uma maquina existente vinda do coletor em forma de json para classe de Processador",
                "POST:InfoMemoria:       https://imperius.azurewebsites.net/api/Coleta/InfoMemoria Salva as informações de memoria de uma maquina existente vinda do coletor em forma de json para classe de Memoria",
                "POST:InfoDisco:         https://imperius.azurewebsites.net/api/Coleta/InfoDisco Salva as informações de  disco uma maquina existente vinda do coletor em forma de json para classe de Disco",
                "POST:PesquisaCadastro:  https://imperius.azurewebsites.net/api/Coleta/PesquisaCadastro Verifica de ja existe um cadastro de maquina com o id enviado e reotrna o id do grupo dessa maquina",
                "POST:SalvaLogs:         https://imperius.azurewebsites.net/api/Coleta/SalvaLogs Salva logs vinda do coletor em forma de json para classe de Logss"


            };
        }

        [HttpPost]
        public int LeituraAgora([FromBody]Leitura l)
        {

                BancoLeitura bl = new BancoLeitura();
                
                return bl.Salva_Leitura(l);
        }


        [HttpPost]
        public bool KeepAlive([FromBody]bool ok)
        {
            if(ok == true)
            {
                return true;
            }
            else
            {
                return false;
            }
            
        }


        [HttpPost]
        public int InfoMaquina([FromBody]Maquina maq)
        {
            BancoMaquina bm = new BancoMaquina();
            return bm.Salva_Maquina(maq);
        }

        [HttpPost]
        public bool InfoProcessador([FromBody]Processador maq)
        {
            BancoMaquina bm = new BancoMaquina();
            return bm.Salva_Processador(maq);
        }

        [HttpPost]
        public bool InfoMemoria([FromBody]Memoria maq)
        {
            BancoMaquina bm = new BancoMaquina();
            return bm.Salva_Memoria(maq);
        }

        [HttpPost]
        public bool InfoDisco([FromBody]Disco maq)
        {
            BancoMaquina bm = new BancoMaquina();
            return bm.Salva_Disco(maq);
        }

        [HttpPost] //api/Coleta/PesquisaCadastro
        public int PesquisaCadastro(string email)
        {
            BancoGrupo emp = new BancoGrupo();
            return emp.Carregar_Grupo_Esp(email);
        }


        [HttpPost] //api/Coleta/SalvaLogs
        public void SalvaLogs([FromBody] Logs l)
        {
            BancoLogs lo = new BancoLogs();
            
            Email.EnvioEmail(l.Msg, lo.Busca_Email(lo.Salva_Logs(l)));
            Telegram.EnvioTelegram(l.Data+ " "+ l.Msg);
        }
        
    }
}
