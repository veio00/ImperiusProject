import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class Config {
    	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream(
				"./properties/dados.properties");
		props.load(file);
		return props;

	}

	public static void  main(String args[]) throws IOException {
		String id; //Variavel que guardará o id do servidor.

		System.out.println("************Teste de leitura do arquivo de propriedades************");
		
		Properties prop = getProp();
		
		id = prop.getProperty("prop.id");
		
		System.out.println("ID = " + id);
	}
}
