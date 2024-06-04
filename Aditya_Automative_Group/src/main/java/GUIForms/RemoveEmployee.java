package GUIForms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RemoveEmployee extends JFrame implements ActionListener{
	String DelEmpId;
	JLabel L1,L2,L3,L4,L5,L6,L7,L8,L9,L10,L11,L12;      
	JLabel L13,L14,L15,L16,L17,L18,L19,L20,L21,L22,L23,L24;   //Displaying employee here
    JButton Remove,Back;
    JPanel P;
   
	public RemoveEmployee(String DelEmpId) {
		this.DelEmpId = DelEmpId;
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;
        setLocation(x, y);
        
		setTitle("Remove Employee");
		setBounds(300, 0, 900, 700);
		P = new JPanel();
		P.setBackground(Color.white);
		setLayout(null);
		
		L1 = new JLabel("Id");
		L1.setBounds(100, 100, 150, 50);
		L1.setFont(new Font("Serif", Font.PLAIN, 20));
		L1.setForeground(Color.black);
		add(L1);
		
		L2 = new JLabel();
		L2.setBounds(260, 100, 150, 50);
		L2.setFont(new Font("Serif", Font.PLAIN, 20));
		L2.setForeground(Color.green);
		add(L2);
		
		//----------------------------------------------------
		
		L3 = new JLabel("Firstname");
		L3.setBounds(100, 160, 150, 50);
		L3.setFont(new Font("Serif", Font.PLAIN, 20));
		L3.setForeground(Color.black);
		add(L3);
		
		L4 = new JLabel();
		L4.setBounds(260, 160, 150, 50);
		L4.setFont(new Font("Serif", Font.PLAIN, 20));
		L4.setForeground(Color.black);
		add(L4);
		
		//----------------------------------------------------
		L5 = new JLabel("Lastname");
		L5.setBounds(100, 220, 150, 50);
		L5.setFont(new Font("Serif", Font.PLAIN, 20));
		L5.setForeground(Color.black);
		add(L5);
		
		L6 = new JLabel();
		L6.setBounds(260, 220, 150, 50);
		L6.setFont(new Font("Serif", Font.PLAIN, 20));
		L6.setForeground(Color.black);
		add(L6);
		
		//----------------------------------------------------
		L7 = new JLabel("Contact");
		L7.setBounds(100, 280, 150, 50);
		L7.setFont(new Font("Serif", Font.PLAIN, 20));
		L7.setForeground(Color.black);
		add(L7);
		
		L8 = new JLabel();
		L8.setBounds(260, 280, 150, 50);
		L8.setFont(new Font("Serif", Font.PLAIN, 20));
		L8.setForeground(Color.black);
		add(L8);
		
		//----------------------------------------------------
		L9 = new JLabel("Email");
		L9.setBounds(100, 340, 150, 50);
		L9.setFont(new Font("Serif", Font.PLAIN, 20));
		L9.setForeground(Color.black);
		add(L9);
		
		L10 = new JLabel();
		L10.setBounds(260, 340, 150, 50);
		L10.setFont(new Font("Serif", Font.PLAIN, 20));
		L10.setForeground(Color.black);
		add(L10);
		
		//----------------------------------------------------
		L11 = new JLabel("Education");
		L11.setBounds(100, 400, 150, 50);
		L11.setFont(new Font("Serif", Font.PLAIN, 20));
		L11.setForeground(Color.black);
		add(L11);
		
		L12 = new JLabel();
		L12.setBounds(260, 400, 150, 50);
		L12.setFont(new Font("Serif", Font.PLAIN, 20));
		L12.setForeground(Color.black);
		add(L12);
		
		//----------------------------------------------------
		L13 = new JLabel("Gender");
		L13.setBounds(450, 100, 150, 50);
		L13.setFont(new Font("Serif", Font.PLAIN, 20));
		L13.setForeground(Color.black);
		add(L13);
		
		L14 = new JLabel();
		L14.setBounds(610, 100, 150, 50);
		L14.setFont(new Font("Serif", Font.PLAIN, 20));
		L14.setForeground(Color.black);
		add(L14);
		
		//----------------------------------------------------
		L15 = new JLabel("Address");
		L15.setBounds(450, 160, 150, 50);
		L15.setFont(new Font("Serif", Font.PLAIN, 20));
		L15.setForeground(Color.black);
		add(L15);
		
		L16 = new JLabel();
		L16.setBounds(610, 160, 150, 50);
		L16.setFont(new Font("Serif", Font.PLAIN, 20));
		L16.setForeground(Color.black);
		add(L16);
		
		//----------------------------------------------------
		L17 = new JLabel("DOB");
		L17.setBounds(450, 220, 150, 50);
		L17.setFont(new Font("Serif", Font.PLAIN, 20));
		L17.setForeground(Color.black);
		add(L17);
		
		L18 = new JLabel();
		L18.setBounds(610, 220, 150, 50);
		L18.setFont(new Font("Serif", Font.PLAIN, 20));
		L18.setForeground(Color.black);
		add(L18);
		
		//----------------------------------------------------
		L19 = new JLabel("Joining Date");
		L19.setBounds(450, 280, 150, 50);
		L19.setFont(new Font("Serif", Font.PLAIN, 20));
		L19.setForeground(Color.black);
		add(L19);
		
		L20 = new JLabel();
		L20.setBounds(610, 280, 150, 50);
		L20.setFont(new Font("Serif", Font.PLAIN, 20));
		L20.setForeground(Color.black);
		add(L20);
		
		//----------------------------------------------------
		L21 = new JLabel("Salary");
		L21.setBounds(450, 340, 150, 50);
		L21.setFont(new Font("Serif", Font.PLAIN, 20));
		L21.setForeground(Color.black);
		add(L21);
		
		L22 = new JLabel();
		L22.setBounds(610, 340, 150, 50);
		L22.setFont(new Font("Serif", Font.PLAIN, 20));
		L22.setForeground(Color.black);
		add(L22);
		
		//----------------------------------------------------
		L23 = new JLabel("Role");
		L23.setBounds(450, 400, 150, 50);
		L23.setFont(new Font("Serif", Font.PLAIN, 20));
		L23.setForeground(Color.black);
		add(L23);
		
		L24 = new JLabel();
		L24.setBounds(610, 400, 150, 50);
		L24.setFont(new Font("Serif", Font.PLAIN, 20));
		L24.setForeground(Color.black);
		add(L24);
		
		//----------------------------------------------------
		
		
		Remove = new JButton("Remove");
		Remove.setBounds(100, 480, 250, 30);
		Remove.setFont(new Font("Arial", Font.PLAIN, 19));
		add(Remove);
		Remove.addActionListener(this);

		Back = new JButton("Back");
		Back.setBounds(450,480, 250, 30);
		Back.setFont(new Font("Arial", Font.PLAIN, 19));
		add(Back);
		Back.addActionListener(this);
		setVisible(true);
		setDefaultCloseOperation(AddEmployee.EXIT_ON_CLOSE);
		
		
		
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdityaAutomative", "root",
					"root");
			//Statement statement = connection.createStatement();
			// Execute query to retrieve data

		   String Query3="SELECT Id,Firstname,Lastname,Contact,Email,Education,Gender,Address,Birth_Date,Joining_Date,Salary,Emp_Role FROM Employee where id='" + DelEmpId + "'";
			PreparedStatement statement = connection.prepareStatement(Query3);
			
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				L2.setText(resultSet.getString("Id"));
				
				L4.setText(resultSet.getString("Firstname"));

				L6.setText(resultSet.getString("Lastname"));

				L8.setText(Long.toString(resultSet.getLong("Contact")));
				
				L10.setText(resultSet.getString("Email"));
				
				L12.setText(resultSet.getString("Education"));
				
				L14.setText(resultSet.getString("Gender"));
				
				L16.setText(resultSet.getString("Address"));
				L18.setText(resultSet.getString("Birth_Date"));
				L20.setText(resultSet.getString("Joining_Date"));
				L22.setText(Double.toString(resultSet.getDouble("Salary")));
				L24.setText(resultSet.getString("Emp_Role"));
				

			}
			 else {
	                // If ResultSet is empty, display a message or handle accordingly
	                JOptionPane.showMessageDialog(this, "No data found!");
	            }
			resultSet.close();
			statement.close();
			connection.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	public static void main(String[] args) {
		new RemoveEmployee("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Back)
        {
        	this.dispose();
        	new ViewEmployee();
        }
        if(e.getSource()==Remove)
        {
        	RemoveEmp();
        }
	}
	
	public void RemoveEmp()
	{
		try {
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdityaAutomative", "root",
				"root");
		//Statement statement = connection.createStatement();
		// Execute query to retrieve data
		String DelQuery="Delete from Employee where id=?";
		PreparedStatement statement = connection.prepareStatement(DelQuery);
		
		statement.setString(1, DelEmpId);
	    
	    int rowsAffected = statement.executeUpdate();
	    
	    if (rowsAffected > 0) {
	        JOptionPane.showMessageDialog(null, "Employee removed");
	    } else {
	        JOptionPane.showMessageDialog(null, "Employee with given ID not found");
	    }
		//JOptionPane.showMessageDialog(null,"Employee removed");
		
		statement.close();
		connection.close();
	}
		
		catch(Exception ea)
		{
			System.out.println(ea);
		}
		}

}
