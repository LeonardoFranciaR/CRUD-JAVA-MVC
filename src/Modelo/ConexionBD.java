package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author LeonardoFrancia
 */
public class ConexionBD {
    Connection cn;
    String conexionMysql = "com.mysql.jdbc.Driver";
    String urlMysql = "jdbc:mysql://localhost:3306/bd_estudiantesutp";
    String userMysql = "leonardo";
    String passMysql = "123456";
    public Connection getConnection(){
        try {
            Class.forName(conexionMysql);
            cn = DriverManager.getConnection(urlMysql,userMysql,passMysql);
        } catch (Exception e) {
            System.err.println("Error:"+e);
        }
        return cn;
    }
}
