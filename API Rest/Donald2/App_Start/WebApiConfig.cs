using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Http;

namespace Donald2
{
    public static class WebApiConfig
    {

        public static void Register(HttpConfiguration config)
        {
            // Configuração e serviços da Web API
            config.EnableCors();
            // Rotas da Web API
            config.MapHttpAttributeRoutes();

            config.Routes.MapHttpRoute(
                name: "DefaultApi",
                routeTemplate: "api/{controller}/{action}",
                defaults: new { id = RouteParameter.Optional }
            );
        }
    }
}
