package GUIForms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConnectionClass {
	
	
	public void createConnection() {
		Connection connection;
		PreparedStatement preparedStatement;
        try {
           
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdityaAutomative", "root", "root");          
            Statement statement = connection.createStatement();  	  
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, this, "Failed to connect to database", 0);
            System.exit(1);
        }
    }		
}
