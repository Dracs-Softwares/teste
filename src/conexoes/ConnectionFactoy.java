
package conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactoy {
    
    private static final String DRIVER ="com.mysql.jdbc.Diver";
    private static final String URL ="jdbc:mysql://localhost:3306/estoque";
    private static final String USER ="root";
    private static final String PASS ="";
    
    public static Connection getConnection(){
        try {
            Class.forName(DRIVER);
            
            return DriverManager.getConnection(DRIVER, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
           throw new RuntimeException("Erro na conexão", ex);
        }
    }
    
    public static void closeConnection(Connection con){
        
        if(con != null){
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactoy.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeConnection(con);
        }
    
}
}
