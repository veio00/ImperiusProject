﻿using API.Models;
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

        public DataTable Carregar_Leitura()
        {
            try
            {

                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("select * from leitura where idLeitura=(select max(idleitura) from leitura where Maquina_Uso = 1)", LstParametros);

                return dt;
            }
            catch (Exception)
            {
                return null;
            }

        }

        public bool Salva_Leitura(Leitura l)
        {
            try
            {
                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("insert into Leitura(Hd,Mram,Cpu,Maquina_Uso) values("+l.Hd+","+l.Mram+","+l.Cpu+","+l.Maquina_Uso+")", LstParametros);
                if(dt != null)
                {
                    return true;
                }
                return false;
            }
            catch (Exception e)
            {

                return false;
            }
        }


    }
}