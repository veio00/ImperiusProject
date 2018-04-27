using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Web;
using System.Globalization;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace Donald2.Models
{
   
    public class Maquina
    {
        public int idMaquina { get; set; }
        public string Responsavel { get; set; }
        public string Nome_Maquina { get; set; }
        public string Adiquirida { get; set; }
        public string Sistema { get; set; }
        public int KeepAlive { get; set; }
        public int Grupo_Cliente { get; set; }
    }
}