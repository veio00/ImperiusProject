using System;
using System.Collections.Generic;

using System.Globalization;
using Newtonsoft.Json;
using Newtonsoft.Json.Converters;

namespace API.Models
{
    public partial class Cliente
    {

        public int idCliente { get; set; }

        public string Nome { get; set; }

        public string Senha { get; set; }
        public string Email { get; set; }

        public int AcessoCliente { get; set; }

        public int GrupoCliente { get; set; }
    }
}