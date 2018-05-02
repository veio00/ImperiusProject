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

namespace API.Controllers
{
    [EnableCors(origins: "*", headers: "*", methods: "*")]
    public class ColetaController : ApiController 
    {
        // GET api/values
        [SwaggerOperation("GetAll")]
        [HttpGet]
        public IEnumerable<string> Get()
        {
            return new string[] { "GetFoi: http://localhost:3182/api/Values/GetFoi", "value2" };
        }

        [HttpPost]
        public bool LeituraAgora([FromBody]Leitura l)
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
        public bool InfoProcessador([FromBody]Processador maq,int id)
        {
            BancoMaquina bm = new BancoMaquina();
            return bm.Salva_Processador(maq,id);
        }

        [HttpPost]
        public bool InfoMemoria([FromBody]Memoria maq, int id)
        {
            BancoMaquina bm = new BancoMaquina();
            return bm.Salva_Memoria(maq, id);
        }

        [HttpPost]
        public bool InfoDisco([FromBody]Disco maq, int id)
        {
            BancoMaquina bm = new BancoMaquina();
            return bm.Salva_Disco(maq, id);
        }
    }
}
