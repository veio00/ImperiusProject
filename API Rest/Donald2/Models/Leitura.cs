using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Web;
using System.Globalization;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
using Donald2.Banco;
using System.Data;

namespace Donald2.Models
{
    public class Leitura
    {
        
        public int IdUso { get; set; }

        public int Hd { get; set; }

        public int Mram { get; set; }

        public int Cpu { get; set; }

        public int MaquinaUso { get; set; }

        public string ConvertClasse()
        {
            return "";
        }
    }
}