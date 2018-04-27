using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.Web;

namespace Donald2.Models
{
    [DataContract]
    public class Usuario 
    {
        [DataMember]
        private string Nome;
        [DataMember]
        private string Senha;
        /**
         * @return the Nome
         */
        public string getNome()
        {
            return Nome;
        }

        /**
         * @param Nome the Nome to set
         */
        public void setNome(string Nome)
        {
            this.Nome = Nome;
        }

        /**
         * @return the Senha
         */
        public string getSenha()
        {
            return Senha;
        }

        /**
         * @param Senha the Senha to set
         */
        public void setSenha(string Senha)
        {
            this.Senha = Senha;
        }



    }
}