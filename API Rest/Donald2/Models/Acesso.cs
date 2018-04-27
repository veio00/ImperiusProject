using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Globalization;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;
namespace Donald2.Models
{


    public partial class Acesso
    {
        [JsonProperty("idAcesso", Required = Required.Always)]
        public int IdAcesso { get; set; }

        [JsonProperty("DescricaoAcesso", Required = Required.Always)]
        public string DescricaoAcesso { get; set; }

        [JsonProperty("NomeAcesso", Required = Required.Always)]
        public int NomeAcesso { get; set; }
    }
}