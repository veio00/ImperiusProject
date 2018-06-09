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
using Telegram.Net.Core;
using Telegram.Net.Core.MTProto;
using Telegram.Net.Core.Requests;

namespace API.Controllers
{
    [EnableCors(origins: "*", headers: "*", methods: "*")]
    public class ViewController : ApiController
    {
        public BancoMaquina BancoMaquina
        {
            get => default(BancoMaquina);
            set
            {
            }
        }

        public BancoLeitura BancoLeitura
        {
            get => default(BancoLeitura);
            set
            {
            }
        }

        public BancoLogs BancoLogs
        {
            get => default(BancoLogs);
            set
            {
            }
        }

        public BancoGrupo BancoGrupo
        {
            get => default(BancoGrupo);
            set
            {
            }
        }

        public BancoRelatorios BancoRelatorios
        {
            get => default(BancoRelatorios);
            set
            {
            }
        }

        public BancoLogin BancoLogin
        {
            get => default(BancoLogin);
            set
            {
            }
        }

        public BancoCliente BancoCliente
        {
            get => default(BancoCliente);
            set
            {
            }
        }

        public Email Email
        {
            get => default(Email);
            set
            {
            }
        }

        public IEnumerable<string> Get()
        {
            return new string[] {
                "Get:                       https://imperius.azurewebsites.net/api/View/Get Mostra todos os caminhos da API da parte de View",
                "POST:CarregaLeitura:       https://imperius.azurewebsites.net/api/View/CarregaLeitura Carrega Leituras da maquina solicita com id dado",
                "GET:CarregaEmpresa:        https://imperius.azurewebsites.net/api/View/CarregaEmpresa Carrega todos os dados das empresas cadastradas",
                "GET:SalvaEmpresa:          https://imperius.azurewebsites.net/api/View/SalvaEmpresa Cadastra uma empresa com o nome dela recebido",
                "POST:CarregaMaquina:       https://imperius.azurewebsites.net/api/View/CarregaMaquina Carrega todas as maquinas do grupo solicitado com id dado",
                "POST:ExcluiMaquina:        https://imperius.azurewebsites.net/api/View/ExcluiMaquina Exclui uma maquina com o id dado",
                "POST:CarregaMaquinaEsp:    https://imperius.azurewebsites.net/api/View/CarregaMaquinaEsp Busca maquina especifica com o id dado",
                "POST:Login:                https://imperius.azurewebsites.net/api/View/Login Valida usuario e senha dado e retorna id do grupo, nome da empresa e id do cliente",
                "POST:alerta:               https://imperius.azurewebsites.net/api/View/alerta Enviar mensagens via telegra (1 parametro 'Msg' e/ou E-mail (2 parametros 'msg' e 'email'",
                "GET:RelatorioLeitura:      https://imperius.azurewebsites.net/api/View/RelatorioLeitura Carrega relatorio de leituras da maquina solicitada com o id dado",
                "GET:RelatorioLogs:         https://imperius.azurewebsites.net/api/View/RelatorioLogs Carrega relatorio de Logs do grupo solicitado com o id dado",
                "GET:RelatorioInventario:   https://imperius.azurewebsites.net/api/View/RelatorioInventario  Carrega relatorio de Inventario do grupo solicitado com o id dado",
                "POST:SalvaCliente:         https://imperius.azurewebsites.net/api/View/SalvaCliente Cadastra um cliente com a classe Cliente dada",
                "GET:CarregaClienteGrupo:   https://imperius.azurewebsites.net/api/View/CarregaClienteGrupo Carrega todos os dados de clientes do grupo solicitado com o id dado",
                "GET:CarregaCliente:        https://imperius.azurewebsites.net/api/View/CarregaCliente Carrega todos os dados de cliente solicitado com o id dado",
                "POST:AlterarCliente:        https://imperius.azurewebsites.net/api/View/AlterarCliente Altera informações de cliente com a classe cliente dada ",

            };
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

        [HttpPost]//api/view/
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

        [HttpPost]//api/view/
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
        public DataTable RelatorioLogs(int empresa)
        {
            BancoLogs logs = new BancoLogs();
            DataTable lista = new DataTable();
            lista = logs.Carrega_Logs(empresa);
            return lista;
        }

        [HttpGet]//api/view/
        public DataTable RelatorioInventario(int empresa)
        {
            BancoRelatorios relatorio = new BancoRelatorios();
            DataTable lista = new DataTable();
            lista = relatorio.Carregar_Relatorio_Inventario(empresa);
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
        public DataTable CarregaClienteGrupo(int idGrupo)
        {
            BancoCliente cli = new BancoCliente();
            DataTable lista = new DataTable();
            lista = cli.Carrega_Cliente_Grupo(idGrupo);
            return lista;
        }
        [HttpPost]//api/view/
        public bool AlterarCliente([FromBody] Cliente c)
        {
            BancoCliente cli = new BancoCliente();
            return cli.Altera_Cliente(c);
        }

        [HttpGet]
        public async System.Threading.Tasks.Task<bool> AddTelegramAsync(int chat, InputUser iduser, int numeroMsg)
        {
            //AddChatUserRequest add = new AddChatUserRequest(chat,iduser,numeroMsg);
            
            return true;
        }
    }
}
