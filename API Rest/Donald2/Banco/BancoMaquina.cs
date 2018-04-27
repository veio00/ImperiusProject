using Donald2.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace Donald2.Banco
{
    public class BancoMaquina
    {
        private Conexao ObjBanco;

        public BancoMaquina()
        {
            ObjBanco = new Conexao();
        }
        ~BancoMaquina()
        {
            ObjBanco = null;
        }

        public DataTable Carregar_Maquina(int empresa)
        {
            try
            {

                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("select * from Maquina where Grupo_Cliente =" + empresa +" ", LstParametros);

                return dt;
            }
            catch (Exception)
            {
                return null;
            }

        }

        public DataTable Carregar_Maquina_Esp(int maquina)
        {
            try
            {

                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("select * from Maquina where idMaquina =" + maquina + " ", LstParametros);

                return dt;
            }
            catch (Exception)
            {
                return null;
            }
        }

        public int Salva_Maquina(Maquina m)
        {
            try
            {

                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable resposta = ObjBanco.ExecuteQuery("insert into Maquina values('" + m.Responsavel + "','" + m.Nome_Maquina + "',getdate()" + ",'" + m.Sistema + "'," + m.KeepAlive + "," + m.Grupo_Cliente + ")", LstParametros);
                DataTable dt = ObjBanco.ExecuteQuery("select max(idMAquina) from Maquina", LstParametros);
                if (dt != null)
                {
                    return int.Parse(dt.Rows[0][0].ToString());
                }
                else
                {
                    return 0;
                }
    

            }
            catch (Exception)
            {
                return 0;
            }
        }

        public bool Salva_Processador(Processador p,int id)
        {
            try
            {
                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("insert into Processador('"+p.Modelo+"',"+id+")", LstParametros);

                if (dt != null)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            catch (Exception)
            {
                return false;
            }
        }

        public bool Salva_Memoria(Memoria me, int id)
        {
            try
            {
                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("insert into Memoria(Qtd,Geracao,Maquina_Memoria) values ("+me.Qtd+",'"+me.Geracao+"',"+id+")", LstParametros);

                if (dt != null)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            catch (Exception)
            {
                return false;
            }
        }


        public bool Salva_Disco(Disco d, int id)
        {
            try
            {
                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("insert into Disco(Espaço,Marca,N_Discos,Maquina_Disco) values ("+ d.Espaco+",'"+d.Marca+"',"+d.NDisco+","+d.MaquinaDisco+")", LstParametros);

                if (dt != null)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            catch (Exception)
            {
                return false;
            }
        }
    }
}