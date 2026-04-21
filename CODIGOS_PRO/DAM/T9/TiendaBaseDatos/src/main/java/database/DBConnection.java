package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// patron singleton -> solo 1 instancia del objeto,
// si alguien pide lo mismo, se le da una copia
public class DBConnection {
    private static Connection connection;

    public static Connection getConnection(){
        if(connection == null){
            createConnection();
        }
        return connection;
    }

    private static void createConnection() {
        String user = "root";
        String pass = "root";
        String url = "127.0.0.1";
        String port  = "3306";
        String dbName = "tienda_thpw_dam";
        String urlJDBC = String.format("jdbc:mysql://%s:%s/%s",url,port,dbName);

        try {
            connection = DriverManager.getConnection(urlJDBC,user,pass);
        } catch (SQLException e) {
            System.out.println("Error en conexion");
            System.out.println(e.getMessage());
        }
    }
}
