using API.Banco;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Cors;

namespace API.Controllers
{
    [EnableCors(origins: "*", headers: "*", methods: "*")]
    public class ViewController : ApiController
    {
        public IEnumerable<string> Get()
        {
            return new string[] { "value1", "value2" };
        }

        [HttpPost]//api/view/
        public DataTable CarregaLeitura(int escolhida)
        {
            BancoLeitura CarregaLeitura = new BancoLeitura();
            DataTable lista = new DataTable();
            lista = CarregaLeitura.Carregar_Leitura(escolhida);
            return lista;
        }



        [HttpPost]//api/view/
        public DataTable CarregaEmpresa()
        {
            BancoGrupo CarregaMaquinas = new BancoGrupo();
            DataTable lista = new DataTable();
            lista = CarregaMaquinas.Carregar_Grupo();
            return lista;
        }

        [HttpPost]//api/view/
        public DataTable CarregaMaquina(int grupo)
        {
            BancoMaquina CarregaMaquinas = new BancoMaquina();
            DataTable lista = new DataTable();
            lista = CarregaMaquinas.Carregar_Maquina(grupo);
            return lista;
        }
        [HttpPost]//api/view/ExcluiMaquina
        public bool ExcluiMaquina(int maquina)
        {
            BancoMaquina CarregaMaquinas = new BancoMaquina();
            bool resposta = CarregaMaquinas.Exclui_Maquina(maquina);
            return resposta;
        }

        [HttpPost]//api/view/
        public DataTable CarregaMaquinaEsp(int maquina)
        {
            BancoMaquina CarregaMaquinas = new BancoMaquina();
            DataTable lista = new DataTable();
            lista = CarregaMaquinas.Carregar_Maquina(maquina);
            return lista;
        }

        [HttpGet]//api/view/
        public DataTable Login(string user, string senha)
        {
            BancoLogin ValidarLogin = new BancoLogin();
            DataTable lista = new DataTable();
            lista = ValidarLogin.ValidarAcesso(user,senha);
            return lista;
        }

    }
}
