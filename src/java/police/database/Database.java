package police.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {

    public Connection createDbConn(String ip, String db, String user, String password) {
        Connection con = null;//?zeroDateTimeBehavior=convertToNull&useSSL=false&autoReconnect=true
        String url = "jdbc:mysql://" + ip + ":3306/" + db + "?zeroDateTimeBehavior=convertToNull&useSSL=false&autoReconnect=true";
        try {
//            System.out.println("Searcing class : " + url);
            Class.forName("com.mysql.jdbc.Driver");
//            System.out.println("Class found attempt connection");
            con = DriverManager.getConnection(url,user,password);
//            System.out.println("Connection Established...");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public Connection getConnectiondbPOLICE() {
        Connection con = this.createDbConn("127.0.0.1", "dbPOLICE", "mysql", "mysql123");
        return con;

    }

    public void close(Connection conn) {
        try {
            conn.close();
//            System.out.println("Connection Terminated...");
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(ex);
        }

    }

    public static void main(String[] args) {
       // new Database().getConnectiondbPOLICE();
    }

}
