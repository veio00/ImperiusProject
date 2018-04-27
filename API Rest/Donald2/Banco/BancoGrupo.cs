using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace Donald2.Banco
{
    public class BancoGrupo
    {
        private Conexao ObjBanco;

        public BancoGrupo()
        {
            ObjBanco = new Conexao();
        }
        ~BancoGrupo()
        {
            ObjBanco = null;
        }

        public DataTable Carregar_Grupo()
        {
            try
            {

                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("select * from Grupo", LstParametros);

                return dt;
            }
            catch (Exception)
            {
                return null;
            }

        }
    }
}