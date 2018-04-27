using System;
using System.Collections.Generic;

using System.Globalization;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace Donald2.Models
{
    public partial class Logs
    {
        [JsonProperty("idLogs", Required = Required.Always)]
        public int IdLogs { get; set; }

        [JsonProperty("data", Required = Required.Always)]
        public string Data { get; set; }

        [JsonProperty("Leitura_Logs", Required = Required.Always)]
        public int LeituraLogs { get; set; }
    }
}