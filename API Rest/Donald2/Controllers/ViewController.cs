using Donald2.Banco;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Cors;

namespace Donald2.Controllers
{
    [EnableCors(origins: "*", headers: "*", methods: "*")]
    public class ViewController : ApiController
    {
        public IEnumerable<string> Get()
        {
            return new string[] { "value1", "value2" };
        }

        [HttpPost]
        public DataTable CarregaLeitura()
        {
            BancoLeitura CarregaLeitura = new BancoLeitura();
            DataTable lista = new DataTable();
            lista = CarregaLeitura.Carregar_Leitura();
            return lista;
        }

        [HttpPost]
        public DataTable CarregaEmpresa()
        {
            BancoGrupo CarregaMaquinas = new BancoGrupo();
            DataTable lista = new DataTable();
            lista = CarregaMaquinas.Carregar_Grupo();
            return lista;
        }

        [HttpPost]
        public DataTable CarregaMaquina(int grupo)
        {
            BancoMaquina CarregaMaquinas = new BancoMaquina();
            DataTable lista = new DataTable();
            lista = CarregaMaquinas.Carregar_Maquina(grupo);
            return lista;
        }

        public DataTable CarregaMaquinaEsp(int maquina)
        {
            BancoMaquina CarregaMaquinas = new BancoMaquina();
            DataTable lista = new DataTable();
            lista = CarregaMaquinas.Carregar_Maquina(maquina);
            return lista;
        }
    }
}
