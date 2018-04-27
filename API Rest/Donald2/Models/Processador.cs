using System;
using System.Collections.Generic;

using System.Globalization;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace Donald2.Models
{

    public partial class Processador
    {
        [JsonProperty("idCpu", Required = Required.Always)]
        public int IdCpu { get; set; }

        [JsonProperty("Modelo", Required = Required.Always)]
        public string Modelo { get; set; }

        [JsonProperty("Maquina_Cpu", Required = Required.Always)]
        public int MaquinaCpu { get; set; }
    }
}