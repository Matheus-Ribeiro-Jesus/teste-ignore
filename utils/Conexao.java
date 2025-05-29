package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexao {

    private String driver;
    private String url;
    private String usuario;
    private String senha;

    Connection condb = null;

    //Inicializando um construtor

    public Conexao() {
        carregarConfiguracoes();
    }

    private void carregarConfiguracoes() {
        Properties props = new Properties();
        try (InputStream inputPropsConfig = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            props.load(inputPropsConfig);
            driver = props.getProperty("driver");
            url = props.getProperty("url");
            usuario = props.getProperty("usuario");
            senha = props.getProperty("senha");
        } catch (IOException erro) {
            System.out.println("Erro ao carregar as configurações: " + erro.getMessage());
        }
    }

    public Connection getConexao() {
        try {
            // Especifica a rota do Driver a ser Carregada JDBC para MySQL

            Class.forName(driver);

            // Inicializar o driver ja carregado por meio do metodo getConnection(IP, porta, nome do banco, usuario, senha)

            condb = DriverManager.getConnection(url, usuario, senha);
            return condb;

        } catch (SQLException erro) {
            System.out.println("Erro ao se conectar ao banco de dados" + erro);
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
