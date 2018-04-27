using System;
using System.Collections.Generic;

using System.Globalization;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace Donald2.Models
{
    public partial class Cliente
    {
        [JsonProperty("idCliente", Required = Required.Always)]
        public int IdCliente { get; set; }

        [JsonProperty("Nome", Required = Required.Always)]
        public string Nome { get; set; }

        [JsonProperty("Email", Required = Required.Always)]
        public string Email { get; set; }

        [JsonProperty("Acesso_Cliente", Required = Required.Always)]
        public int AcessoCliente { get; set; }

        [JsonProperty("Grupo_Cliente", Required = Required.Always)]
        public int GrupoCliente { get; set; }
    }
}