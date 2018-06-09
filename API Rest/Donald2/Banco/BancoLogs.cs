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

        public Conexao Conexao
        {
            get => default(Conexao);
            set
            {
            }
        }

        public int Salva_Logs(Logs log)
        {
            try
            {

                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("insert into Logss(Data,Msg,Leitura_Logs) values('"+log.Data+"','"+log.Msg+"',"+log.Leitura_Logs+")", LstParametros);
                dt = ObjBanco.ExecuteQuery("select max(idLogs) as idLogs from Logss", LstParametros);
                if (dt != null)
                {
                    return int.Parse(dt.Rows[0][0].ToString());
                }

                return 0;
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

        public string Busca_Email(int log)
        {
            try
            {

                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("select Min(Email) as Email from Logss lo inner join Leitura  l on l.idLeitura=lo.Leitura_Logs inner join Maquina m on m.idMAquina=l.Maquina_Uso inner join grupo g on g.idGrupo = m.Grupo_Cliente inner join cliente c on c.Grupo_Cliente=m.Grupo_Cliente where idLogs = "+log+"", LstParametros);

                if (dt != null)
                {
                    string retorno = dt.Rows[0][0].ToString().Substring(1, int.Parse(dt.Rows[0][0].ToString().Length.ToString()) - 2);
                    return retorno;
                }

                return "";
            }
            catch (Exception e)
            {
                return e+"";
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