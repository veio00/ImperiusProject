using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Mail;
using System.Text;
using System.Web;

namespace API.Notificacoes
{
    public class Email
    {
        private static string emailLocal;
        public static void EnvioEmail(string msg, string email)
        {
            emailLocal = email;
            // Especifica o servidor SMTP e a porta
            using (SmtpClient client = new SmtpClient("smtp.gmail.com",587))
            {
                try
                {
                    // EnableSsl ativa a comunicação segura com o servidor
                    client.EnableSsl = true;

                    // Especifica a credencial utilizada para envio da mensagem
                    client.UseDefaultCredentials = false;
                    client.Credentials = new NetworkCredential("imperiusprojec@gmail.com", "Cardrelayne");

                    // Especifia o remetente e o destinatário da mensagem
                    MailMessage message = new MailMessage(new MailAddress("imperiusprojec@gmail.com", "IMPERIUS", Encoding.UTF8),
                        new MailAddress(email));

                    // Preenche o corpo e o assunto da mensagem
                    message.BodyEncoding = Encoding.UTF8;
                    message.Body = msg;
                    message.SubjectEncoding = Encoding.UTF8;
                    message.Subject = "Teste de envio";

                    // Anexos devem ser adicionados através do método
                    // message.Attachments.Add()

                    // Envia a mensagem
                    client.Send(message);
                }
                catch (Exception ex)
                {
                    EnvioEmail(msg);
                    throw ex;
                }
            }

        }

        public static void EnvioEmail(string msg)
        {
            using (SmtpClient client = new SmtpClient("smtp.gmail.com", 587)
            {
                Credentials = new NetworkCredential("imperiusprojec@gmail.com", "Cardrelayne"),
                EnableSsl = true
            })
            {
                client.Send("imperiusprojec@gmail.com", emailLocal, "test", "test");
            }



        }
    }
}