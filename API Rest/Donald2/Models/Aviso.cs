using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace API.Models
{
    public class Aviso
    {
        public int idAviso { get; set; }
        public string NomeAviso { get; set; }
        public int AvisoI1 { get; set; }
        public int AvisoI2 { get; set; }
        public int AvisoI3 { get; set; }
        public int AvisoF1 { get; set; }
        public int AvisoF2 { get; set; }
        public int AvisoF3 { get; set; }
        public int Maquina_Aviso { get; set; }
    }
}