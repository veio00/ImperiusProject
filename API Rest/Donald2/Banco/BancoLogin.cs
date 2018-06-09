using API.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace API.Banco
{
    public class BancoLogin
    {
        private Conexao ObjBanco;

        public BancoLogin()
        {
            ObjBanco = new Conexao();
        }
        ~BancoLogin()
        {
            ObjBanco = null;
        }

        public Conexao Conexao
        {
            get => default(Conexao);
            set
            {
            }
        }

        public DataTable ValidarAcesso(string user, string senha)
        {
            try
            {

                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("select idCliente,Acesso_Cliente,Grupo_Cliente from Cliente where Email = '" + user +"' and Senha = '"+ senha +"' ", LstParametros);

                return dt;
            }
            catch (Exception)
            {
                return null;
            }

        }


    }
}