﻿using System;
using System.Collections.Generic;
using System.Web;
using System.Data;
using System.Data.SqlClient;
using API.Models;

namespace API.Banco
{
    public class BancoAviso
    {
        private Conexao ObjBanco;

        public BancoAviso()
        {
            ObjBanco = new Conexao();
        }
        ~BancoAviso()
        {
            ObjBanco = null;
        }

        public DataTable Carrega_Aviso(int maquina)
        {
            try
            {
                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("Select * from Aviso where Maquina_Aviso=" + maquina + "", LstParametros);
                return dt;
            }
            catch (Exception ex)
            {

                throw ex;
            }
        }

        public bool Salva_Aviso(Aviso a)
        {
            try
            {

                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("insert into Aviso(NomeAviso,AvisoI1,AvisoI2,AvisoI3,AvisoF1,AvisoF2,AvisoF3,Maquina_Aviso) values('" + a.NomeAviso + "'," + a.AvisoI1 + "," + a.AvisoI2 + "," + a.AvisoI3 + "," + a.AvisoF1 + "," + a.AvisoF2 + "," + a.AvisoF3 + "," + a.Maquina_Aviso + ")", LstParametros);

                if (dt != null)
                {
                    return true;
                }
                return false;
            }
            catch (Exception)
            {
                return false;
            }

        }

        public bool Alterar_Aviso(Aviso a)
        {
            try
            {

                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("update Aviso set NomeAviso='"+a.NomeAviso+"', AvisoI1="+a.AvisoI1+",AvisoI2="+a.AvisoI2+",AvisoI3="+a.AvisoI3+",AvisoF1="+a.AvisoF1+",AvisoF2="+a.AvisoF2+",AvisoF3="+a.AvisoF3+" where idAviso="+a.idAviso+"", LstParametros);

                if (dt != null)
                {
                    return true;
                }
                return false;
            }
            catch (Exception)
            {
                return false;
            }

        }
    }
}