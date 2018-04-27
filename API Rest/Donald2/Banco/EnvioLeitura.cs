using Donald2.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace Donald2.Banco
{
    public class BancoLeitura
    {
        private Conexao ObjBanco;

        public BancoLeitura()
        {
            ObjBanco = new Conexao();
        }
        ~BancoLeitura()
        {
            ObjBanco = null;
        }

        public DataTable Carregar_Leitura()
        {
            try
            {

                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("select * from leitura where Maquina_Uso= 1 ", LstParametros);

                return dt;
            }
            catch (Exception)
            {
                return null;
            }

        }


    }
}