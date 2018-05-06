using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace API.Banco
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

        public int Carregar_Grupo_Esp(string email)
        {
            try
            {

                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("select idGrupo from grupo inner join Cliente on idGrupo = Grupo_Cliente where Email ='"+email+"'", LstParametros);


                if(dt != null)
                {
                    return int.Parse(dt.Rows[0][0].ToString());
                }

                return 0;
            }

            catch (Exception)
            {
                return 0;
            }

        }
    }
}