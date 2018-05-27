using API.Models;
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


        public bool Salva_Logs(Logs log)
        {
            try
            {

                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("insert into Logss(Data,Msg,Leitura_Logs) values('"+log.Data+"','"+log.Msg+"',"+log.Leitura_Logs+")", LstParametros);

                if (dt != null)
                {

                    return true;
                }

                return false;
            }
            catch (Exception e)
            {
                throw e ;
            }

        }

        public DataTable Carrega_Logs(int empresa)
        {
            try
            {

                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("select idLogs as Codigo, Msg as Mensagem, Hd as Status_HD, Mram as Status_Ram, Cpu as Status_CPU, l.Data as Data_Ocorrência from Logss l inner join Leitura le on idLeitura = Leitura_Logs inner join maquina m on idmaquina = Maquina_uso where Grupo_Cliente="+ empresa +"", LstParametros);

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