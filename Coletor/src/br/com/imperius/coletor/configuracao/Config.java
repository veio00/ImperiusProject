package br.com.imperius.coletor.configuracao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Properties;

public class Config {

    private static final String NOME_ARQUIVO = "/dados.properties";

    private static InputStream getLocalLeitura() {
        return Config.class.getResourceAsStream(NOME_ARQUIVO);
    }

    private static OutputStream getLocalEscrita() throws FileNotFoundException, URISyntaxException {

        return new FileOutputStream(new File(Config.class.getResource(NOME_ARQUIVO).toURI()));
    }

    public static Properties getProp() throws IOException {
        Properties props = new Properties();
        InputStream file = getLocalLeitura();
        props.load(file);
        return props;
    }

    public static void setProp(String propriedade, String valor) throws IOException {
        setProp(propriedade, valor, null);
    }

    public static void setProp(String propriedade, String valor, String Comentario) throws IOException {
        Properties p = getProp();
        p.setProperty(propriedade, valor);
        try {
            OutputStream fos = getLocalEscrita();

            p.store(fos, Comentario);
            fos.close();
        } catch (IOException | URISyntaxException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
