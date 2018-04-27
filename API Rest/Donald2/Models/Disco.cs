using System;
using System.Collections.Generic;

using System.Globalization;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace Donald2.Models
{

    public partial class Disco
    {
        [JsonProperty("idDisco", Required = Required.Always)]
        public int IdDisco { get; set; }

        [JsonProperty("Espaco", Required = Required.Always)]
        public int Espaco { get; set; }

        [JsonProperty("Marca", Required = Required.Always)]
        public string Marca { get; set; }

        [JsonProperty("N_Disco", Required = Required.Always)]
        public int NDisco { get; set; }

        [JsonProperty("Maquina_Disco", Required = Required.Always)]
        public int MaquinaDisco { get; set; }
    }
}