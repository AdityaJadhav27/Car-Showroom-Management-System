package GUIForms;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AdminLogin extends JFrame implements ActionListener {
	Container C;
	JButton Login, Cancel;
	JLabel L1, L2, Imageicon, L3;
	JTextField Username;
	JPasswordField Password;
	ImageIcon image;
	Connection connection;

	AdminLogin() {
		setTitle("Login here admin");
		setSize(700, 450);
		C = getContentPane();
		C.setBackground(Color.white);
		setUndecorated(true);
		CreateConnection();

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - this.getWidth()) / 2;
		int y = (screenSize.height - this.getHeight()) / 2;
		setLocation(x, y);

		setLayout(null);

		L3 = new JLabel("");
		L3.setBounds(100, 50, 150, 45);
		L3.setFont(new Font("Arial", Font.PLAIN, 19));
		L3.setForeground(Color.red);
		C.add(L3);

		L1 = new JLabel("Admin Name");
		L1.setBounds(100, 125, 150, 45);
		L1.setFont(new Font("Arial", Font.PLAIN, 19));
		C.add(L1);

		Username = new JTextField();
		Username.setBounds(230, 125, 150, 45);
		Username.setFont(new Font("Arial", Font.PLAIN, 19));
		C.add(Username);

		L2 = new JLabel("Password");
		L2.setBounds(100, 205, 230, 45);
		L2.setFont(new Font("Arial", Font.PLAIN, 19));
		C.add(L2);

		Password = new JPasswordField();
		Password.setBounds(230, 205, 150, 45);
		Password.setFont(new Font("Arial", Font.PLAIN, 19));
		C.add(Password);

		Login = new JButton("Login");
		Login.setBounds(100, 280, 149, 50);
		Login.setFont(new Font("Arial", Font.PLAIN, 19));
		Login.setBackground(Color.black);
		Login.setForeground(Color.white);
		C.add(Login);
		Login.addActionListener(this);

		Cancel = new JButton("Cancel");
		Cancel.setBounds(250, 280, 149, 50);
		Cancel.setFont(new Font("Arial", Font.PLAIN, 19));
		Cancel.setBackground(Color.black);
		Cancel.setForeground(Color.white);
		C.add(Cancel);
		Cancel.addActionListener(this);
//------------------------------------------------------------------------------

		image = new ImageIcon(ClassLoader.getSystemResource("Pictures/UserIcon.png"));
		Image img1 = image.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
		ImageIcon ic1 = new ImageIcon(img1);

		Imageicon = new JLabel(ic1);
		Imageicon.setBounds(450, 100, 200, 250);
		C.add(Imageicon);

		setVisible(true);
		setDefaultCloseOperation(AdminLogin.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new AdminLogin();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == Login) {
           AdminLoginDetails();
		}
		if (e.getSource() == Cancel) {
			this.dispose();
		}

	}

	public void AdminLoginDetails()
	{
		String TxtAdminName= Username.getText(),TxtPassword=Password.getText();
		String SelectAdmin ="select * from Admin_Details where Firstname='"+TxtAdminName+"' and Password='"+TxtPassword+"'";
		try
		{
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdityaAutomative", "root",
					"root");
			Statement statement = connection.createStatement();
			ResultSet RS= statement.executeQuery(SelectAdmin);
			if(RS.next())
			{
				this.dispose();
				new HomeScreen();
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Invalid Username or Password");
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,"Connection failed");
		}
	}

	private void CreateConnection() {
		try {
			// Replace 'jdbc:mysql://localhost:3306/database_name' with your database URL
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdityaAutomative", "root", "root");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, this, "Failed to connect to database", 0);
			System.exit(1);
		}
	}
}
