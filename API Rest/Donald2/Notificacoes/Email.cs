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
        public static void EnvioEmail(string msg)
        {
            // Especifica o servidor SMTP e a porta
            using (System.Net.Mail.SmtpClient client = new System.Net.Mail.SmtpClient("smtp.gmail.com"))
            {
                try
                {
                    // EnableSsl ativa a comunicação segura com o servidor
                    client.EnableSsl = true;

                    // Especifica a credencial utilizada para envio da mensagem
                    client.UseDefaultCredentials = false;
                    client.Credentials = new System.Net.NetworkCredential("imperiusprojec@gmail.com", "Cardrelayne");

                    // Especifia o remetente e o destinatário da mensagem
                    System.Net.Mail.MailMessage message = new System.Net.Mail.MailMessage(
                        new System.Net.Mail.MailAddress("imperiusprojec@gmail.com", "IMPERIUS", Encoding.UTF8),
                        new System.Net.Mail.MailAddress("50036@alunos.bandtec.com.br"));

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
                    throw ex;
                }
            }

        }

        public static void EnvioEmail(string msg, string email)
        {
            using (SmtpClient client = new SmtpClient("smtp.gmail.com", 587)
            {
                Credentials = new NetworkCredential("imperiusprojec@gmail.com", "Cardrelayne"),
                EnableSsl = true
            })
            {
                client.Send("imperiusprojec@gmail.com", "50036@alunos.bandtec.com.br",  "test", "test");
            }
           
           

        }
    }
}