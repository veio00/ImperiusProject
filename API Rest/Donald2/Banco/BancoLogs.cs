using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace API.Banco
{
    public class BancoLogs
    {
        private Conexao ObjBanco;

        public BancoLogs()
        {
            ObjBanco = new Conexao();
        }
        ~BancoLogs()
        {
            ObjBanco = null;
        }


        public DataTable Salva_Logs(string mensagem, int empresa)
        {
            try
            {

                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("insert into Logss(Data,Msg,Leitura_Logs) values(getdate(),'"+mensagem+"',"+empresa+")", LstParametros);

                return dt;
            }
            catch (Exception)
            {
                return null;
            }

        }

        public DataTable Carrega_Logs(int empresa)
        {
            try
            {

                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("select idlogs, Msg, Leitura_Logs from logss inner join leitura on idLeitura = Leitura_Logs inner join Maquina on idMaquina = Maquina_Uso where Grupo_Cliente = "+ empresa +"", LstParametros);

                return dt;
            }
            catch (Exception)
            {
                return null;
            }

        }

        //public DataTable Deleta_Logs(int empresa)
        //{
        //    try
        //    {

        //        List<SqlParameter> LstParametros = new List<SqlParameter>();

        //        DataTable dt = ObjBanco.ExecuteQuery("", LstParametros);

        //        return dt;
        //    }
        //    catch (Exception)
        //    {
        //        return null;
        //    }

        //}

    }
}