using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

namespace API.Banco
{
    public class BancoRelatorios
    {
        private Conexao ObjBanco;

        public BancoRelatorios()
        {
            ObjBanco = new Conexao();
        }
        ~BancoRelatorios()
        {
            ObjBanco = null;
        }

        public DataTable Carregar_Relatorio_Leitura()
        {
            try
            {

                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("select idMaquina as codigo, Responsavel, Data_Compra, Sistema, Hd, Mram, Cpu, data as Data_Leitura from Maquina inner join Processador on Maquina_Cpu = idMaquina inner join Memoria on Maquina_Memoria = idMaquina inner join Disco on Maquina_Disco = idMaquina inner join Leitura on idMaquina=Maquina_Uso where idMaquina = 1 and Grupo_Cliente=1", LstParametros);

                return dt;
            }
            catch (Exception)
            {
                return null;
            }

        }

    }
}