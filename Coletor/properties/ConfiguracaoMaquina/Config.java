package ConfiguracaoMaquina;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
public class Config {
    
    	public static Properties getProp() throws IOException {
            Properties props = new Properties();
            FileInputStream file = new FileInputStream("./properties/ConfiguracaoMaquina/dados.properties");
            props.load(file);
            return props;

        }
        
        public static void setProp(String propriedade, String valor) throws IOException {
            Properties p = getProp();
            p.setProperty(propriedade, valor);
            try {
                FileOutputStream fos = new FileOutputStream("./properties/ConfiguracaoMaquina/dados.properties");
                p.store(fos,"");
                fos.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
        
        public static void setProp(String propriedade, String valor, String Comentario) throws IOException {
            Properties p = getProp();
            p.setProperty(propriedade, valor);
            try {
                FileOutputStream fos = new FileOutputStream("./properties/ConfiguracaoMaquina/dados.properties");
                
                p.store(fos,Comentario);
                fos.close();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
        
        

	public static void  main(String args[]) throws IOException {
		String id; //Variavel que guardará o id do servidor.

		System.out.println("************Teste de leitura do arquivo de propriedades************");
		
		Properties prop = getProp();
	
		id = prop.getProperty("id");
		
		System.out.println("ID = " + id);
                Config.setProp("id","4");
                prop = getProp();
                id = prop.getProperty("id");
                System.out.println("ID = " + id);
	}
}
