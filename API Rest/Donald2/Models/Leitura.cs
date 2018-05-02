using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Web;
using System.Globalization;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using API.Banco;
using System.Data;

namespace API.Models
{
    public class Leitura
    {
        
        public int idUso { get; set; }

        public int Hd { get; set; }

        public int Mram { get; set; }

        public int Cpu { get; set; }

        public int Maquina_Uso { get; set; }

        public string ConvertClasse()
        {
            return "";
        }
    }
}