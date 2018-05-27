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

        public DataTable Carregar_Relatorio_Leitura(int grupo, int maquina)
        {
            try
            {

                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("select idLeitura as Codigo, hd as HD, Mram as Memoria, cpu as CPU, Data  from leitura inner join Maquina on idMaquina=Maquina_Uso where Maquina_Uso ="+maquina+" and Grupo_Cliente="+grupo+"", LstParametros);

                return dt;
            }
            catch (Exception)
            {
                return null;
            }

        }

        public DataTable Carregar_Relatorio_Inventario(int grupo)
        {
            try
            {

                List<SqlParameter> LstParametros = new List<SqlParameter>();

                DataTable dt = ObjBanco.ExecuteQuery("select idMaquina as Codigo, Responsavel, Data_Compra as Instalada, Sistema, Espaco as HD, Qtd as Qtd_Memoria , Modelo as Processador from Maquina inner join Processador on Maquina_Cpu = idMaquina inner join Memoria on Maquina_Memoria = idMaquina inner join Disco on Maquina_Disco = idMaquina  where Grupo_Cliente = " + grupo + "", LstParametros);

                return dt;
            }
            catch (Exception)
            {
                return null;
            }

        }
    }
}