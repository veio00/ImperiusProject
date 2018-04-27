using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
//Import das DLL uitlizadas para conexão com banco de dados
using System.Data;
using System.Data.SqlClient;
using System.Configuration;

namespace Donald2.Banco
{
    public class Conexao
    {
        private SqlConnection GerarConexao()
        {
            try
            {
                string strConn = ConfigurationManager.ConnectionStrings["CONEXAO"]
                    .ConnectionString;

                if (!string.IsNullOrEmpty(strConn))
                {
                    SqlConnection conexao = new SqlConnection(strConn);
                    return conexao;
                }
                else
                    return null;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
        private SqlConnection AbrirConexao()
        {
            SqlConnection cn = GerarConexao();

            try
            {
                cn.Open();
                return cn;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
        private void FecharConexao(SqlConnection cn)
        {
            try
            {
                cn.Close();
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
        public DataTable ExecuteQuery(string strQuery, List<SqlParameter> lstParametros)
        {
            try
            {
                DataTable dtDados = new DataTable();
                using (SqlConnection sqlConexao = AbrirConexao())
                {
                    using (SqlCommand sqlComando = new SqlCommand(strQuery, sqlConexao))
                    {
                        sqlComando.CommandType = System.Data.CommandType.Text;

                        if (lstParametros != null)
                        {
                            sqlComando.Parameters.AddRange(lstParametros.ToArray());
                        }

                        using (SqlDataAdapter sqlAdaptador = new SqlDataAdapter(sqlComando))
                        {
                            sqlAdaptador.Fill(dtDados);
                        }
                    }

                    FecharConexao(sqlConexao);
                }

                return dtDados;
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }
    }
}