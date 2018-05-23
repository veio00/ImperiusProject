using API.Banco;
using API.Models;
using API.Notificacoes;
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



        [HttpGet]//api/view/
        public DataTable CarregaEmpresa()
        {
            BancoGrupo Empresa = new BancoGrupo();
            DataTable lista = new DataTable();
            lista = Empresa.Carregar_Grupo();
            return lista;
        }

        [HttpGet]//api/view/
        public DataTable SalvaEmpresa(string nome)
        {
            BancoGrupo SalvaEmpresa = new BancoGrupo();
            DataTable lista = new DataTable();
            lista = SalvaEmpresa.Salva_Grupo(nome);
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

        [HttpPost]//api/view/
        public DataTable Login(string user, string senha)
        {
            BancoLogin ValidarLogin = new BancoLogin();
            DataTable lista = new DataTable();
            lista = ValidarLogin.ValidarAcesso(user,senha);
            return lista;
        }

        [HttpPost]
        public void alerta(string msg,string email)
        {
            if (email == null)
            {
                Telegram.EnvioTelegram(msg);
            }
            else
            {
                Email.EnvioEmail(msg, email);
            }
            
            
        }

        [HttpGet]//api/view/
        public DataTable RelatorioLeitura(int empresa, int cod)
        {
            BancoRelatorios relatorio = new BancoRelatorios();
            DataTable lista = new DataTable();
            lista = relatorio.Carregar_Relatorio_Leitura(empresa, cod);
            return lista;
        }

        [HttpGet]//api/view/
        public DataTable RelatorioLogs([FromBody] int empresa)
        {
            BancoLogs logs = new BancoLogs();
            DataTable lista = new DataTable();
            lista = logs.Carrega_Logs(empresa);
            return lista;
        }

        [HttpPost]
        public bool SalvaCliente([FromBody] Cliente c)
        {
            BancoCliente cli = new BancoCliente();
            return cli.Salvar_Cliente(c);
        }

        [HttpGet]//api/view/
        public DataTable CarregaCliente([FromBody] int idCliente)
        {
            BancoCliente cli = new BancoCliente();
            DataTable lista = new DataTable();
            lista = cli.Carrega_Cliente(idCliente);
            return lista;
        }

        [HttpGet]//api/view/
        public bool AlterarCliente([FromBody] Cliente c)
        {
            BancoCliente cli = new BancoCliente();
            return cli.Altera_Cliente(c);
        }

        
    }
}
