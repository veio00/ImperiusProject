using API.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace API.Banco
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

        public DataTable Carregar_Leitura(int maquina)
        {
            try
            {

                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("select * from leitura where idLeitura=(select max(idleitura) from leitura where Maquina_Uso ="+maquina+")", LstParametros);

                return dt;
            }
            catch (Exception)
            {
                return null;
            }

        }

        public int Salva_Leitura(Leitura l)
        {
            try
            {
                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("insert into Leitura(Hd,Mram,Cpu,Data,Maquina_Uso) values("+l.Hd+","+l.Mram+","+l.Cpu+",'"+l.Data+"'," + l.Maquina_Uso+")", LstParametros);
                dt = ObjBanco.ExecuteQuery("select max(idLeitura) as idLeitura from Leitura", LstParametros);

                if (dt != null)
                {
                    return int.Parse(dt.Rows[0][0].ToString());
                }

                return 0;
            }
            catch (Exception e)
            {

                throw e;
            }
        }


    }
}