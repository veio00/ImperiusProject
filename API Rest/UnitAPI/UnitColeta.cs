using System;
using NUnit.Framework;
using API;
using API.Models;
using API.Controllers;
using System.Net.Http;


namespace UnitAPI
{
    [TestFixture]
    public class UnitColeta
    {
        [Test]
        public void UnitKeepAlive()
        {

            ColetaController cl = new ColetaController();
            //Assert.IsTrue(cl.KeepAlive(true));
        }
    }
}
