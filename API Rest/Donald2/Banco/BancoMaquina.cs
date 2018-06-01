using API.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace API.Banco
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

                DataTable dt = ObjBanco.ExecuteQuery("select * from Maquina inner join grupo on idgrupo = Grupo_Cliente where Grupo_Cliente =" + empresa +" ", LstParametros);

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

                DataTable resposta = ObjBanco.ExecuteQuery("insert into Maquina(Responsavel,Nome_Maquina,Adquirida,Sistema,Keep_Alive,Grupo_Cliente)  values('" + m.Responsavel + "','" + m.Nome_Maquina + "',getdate(),'" + m.Sistema + "'," + m.Keep_Alive + "," + m.Grupo_Cliente + ")", LstParametros);
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

        public bool Salva_Processador(Processador p)
        {
            try
            {
                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("insert into Processador values('"+p.Modelo+"',"+p.MaquinaCpu+")", LstParametros);

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

        public bool Salva_Memoria(Memoria me)
        {
            try
            {
                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("insert into Memoria(Qtd,Geracao,Maquina_Memoria) values ("+me.Qtd+",'"+me.Geracao+"',"+ me.MaquinaMemoria + ")", LstParametros);

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


        public bool Salva_Disco(Disco d)
        {
            try
            {
                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("insert into Disco(Espaco,Marca,N_Discos,Maquina_Disco) values ("+ d.Espaco+",'"+d.Marca+"',"+d.NDisco+","+d.MaquinaDisco+")", LstParametros);

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

        public bool Exclui_Maquina(int maquina)
        {
            try
            {

                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("delete from Memoria where Maquina_Memoria =" + maquina + "", LstParametros);   
                dt = ObjBanco.ExecuteQuery("delete from Disco where Maquina_Disco =" + maquina + "", LstParametros);
                dt = ObjBanco.ExecuteQuery("delete from Processador where Maquina_Cpu =" + maquina + "", LstParametros);
                dt = ObjBanco.ExecuteQuery("delete from logss where Leitura_Logs in (select idLeitura from Maquina inner join Leitura on idMaquina = Maquina_Uso where idMAquina = "+maquina+")", LstParametros);
                dt = ObjBanco.ExecuteQuery("delete from leitura where Maquina_Uso =" + maquina + "", LstParametros);
                dt = ObjBanco.ExecuteQuery("delete from maquina where idMAquina =" + maquina + "", LstParametros);
                if (dt == null)
                {
                    return false;
                }
                return true;
            }
            catch (Exception)
            {
                return false;
            }

        }
    }
}