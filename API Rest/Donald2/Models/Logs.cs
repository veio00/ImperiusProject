using System;
using System.Collections.Generic;

using System.Globalization;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace API.Models
{
    public partial class Logs
    {
        public int idLogs { get; set; }

        public string Data { get; set; }
        public string Msg { get; set; }

        public int Leitura_Logs { get; set; }
    }
}