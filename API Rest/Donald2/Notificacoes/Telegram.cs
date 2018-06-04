using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Web;


namespace API.Controllers
{
    public class Telegram
    {
        public static void EnvioTelegram(string msg){
            const string token = "574896666:AAFLMEzCPFx_cqZ3w22fccZhxnk2yWGtRhg"; // "Pluto";
            //const string Ds_Mensagem = ; // "Colocar mensagem";
            //const string Ds_Canal = "-1001244864542";// " @Imperius_Imfernus_bot";
            const string Ds_Canal = "-1001267600200;-294943455;-1001244864542";// " @IMPERIUS";


            try
            {

                var mensagem = msg;
                var canais = Ds_Canal.Split(';');

                foreach (var canal in canais)
                {
                    var dsScript = $"chat_id={canal.Trim()}&text={mensagem}&parse_mode=Markdown";

                    var url = $"https://api.telegram.org/bot{token}/sendMessage";

                    var request = (HttpWebRequest)WebRequest.Create(url);

                    request.Method = "POST";
                    request.UserAgent = "curl/7.45.0";
                    request.ContentType = "application/x-www-form-urlencoded";

                    var buffer = Encoding.GetEncoding("UTF-8").GetBytes(dsScript);
                    using (var reqstr = request.GetRequestStream())
                    {
                        reqstr.Write(buffer, 0, buffer.Length);

                        using (var response = request.GetResponse())
                        {
                            using (var dataStream = response.GetResponseStream())
                            {
                                if (dataStream == null) return;

                                using (var reader = new StreamReader(dataStream))
                                {
                                    var responseFromServer = reader.ReadToEnd();
                                }
                            }
                        }
                    }
                }
            }
            catch (Exception e)//catch (Exception e)
            {
                Console.WriteLine("Exception: " + e.Message);
            }
        }


    }
}