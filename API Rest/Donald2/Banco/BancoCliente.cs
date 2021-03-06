﻿using API.Models;
using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace API.Banco
{
	public class BancoCliente
	{
        private Conexao ObjBanco;

        public BancoCliente()
        {
            ObjBanco = new Conexao();
        }
        ~BancoCliente()
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

        public bool Salvar_Cliente(Cliente c)
        {
            try
            {

                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("insert into Cliente(Nome,Email,Senha,Acesso_Cliente,Grupo_Cliente) values('"+c.Nome+"','"+c.Email+"','"+c.Senha+"',"+c.AcessoCliente+","+c.GrupoCliente+")", LstParametros);

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

        public DataTable Carrega_Cliente(int usuario)
        {
            try
            {
                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("select idCliente,Nome,Email,Senha,Nome_Acesso,Nome_grupo from Cliente inner join Acesso on Acesso_Cliente=idAcesso inner join grupo on Grupo_Cliente=idGrupo where idCliente="+usuario+"", LstParametros);
                return dt;
            }
            catch (Exception ex)
            {

                throw null;
            }
        }

        public DataTable Carrega_Cliente_Grupo(int grupo)
        {
            try
            {
                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("select idCliente,Nome,Email,Senha,idAcesso,Nome_Acesso,Nome_grupo from Cliente inner join Acesso on Acesso_Cliente=idAcesso inner join grupo on Grupo_Cliente=idGrupo where idGrupo=" + grupo + "", LstParametros);
                return dt;
            }
            catch (Exception ex)
            {

                throw null;
            }
        }

        public bool Altera_Cliente(Cliente c)
        {
            try
            {
                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("update cliente set Nome='"+c.Nome+"',Email='"+c.Email+"',Senha='"+c.Senha+"',Acesso_Cliente="+c.AcessoCliente+",Grupo_Cliente="+c.GrupoCliente+" where idCliente="+c.idCliente+"", LstParametros);

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

        public bool excluir_Cliente(int idCliente)
        {
            try
            {
                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("delete from cliente where idCliente="+idCliente+"", LstParametros);

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