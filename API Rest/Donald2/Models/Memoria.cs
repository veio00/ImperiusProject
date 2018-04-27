using System;
using System.Collections.Generic;

using System.Globalization;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace Donald2.Models
{

    public partial class Memoria
    {
        [JsonProperty("idMemoria", Required = Required.Always)]
        public int IdMemoria { get; set; }

        [JsonProperty("Qtd", Required = Required.Always)]
        public int Qtd { get; set; }

        [JsonProperty("Geracao", Required = Required.Always)]
        public string Geracao { get; set; }

        [JsonProperty("Maquina_Memoria", Required = Required.Always)]
        public int MaquinaMemoria { get; set; }
    }
}