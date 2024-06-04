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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class AdminRegistration extends JFrame implements ActionListener {
	Container C;
	JLabel L1, L2, L3, L4, L5, L6, L7, ImageIcon2;
	JLabel Firstname, Lastname, Contact, Email;
	JPasswordField AdminPassword;
	JButton Submit, Back, SearchById;
	ImageIcon image;
	JComboBox AdminId;
	ArrayList<String> SearchAdmin = new ArrayList<>();;
	String AdminDetails, InsertAdminDetails;

	Connection connection;

	AdminRegistration() {
		setUndecorated(true);
		// setTitle("Admin Registration");

		setSize(900, 550);
		C = getContentPane();
		C.setBackground(new Color(245, 247, 247));

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - this.getWidth()) / 2;
		int y = (screenSize.height - this.getHeight()) / 2;
		setLocation(x, y);
		// LineBorder border = new LineBorder(new Color(121, 231, 237));
		// C.setBorder(new LineBorder(new Color(50, 53, 54)));
		setLayout(null);
		CreateConnection();
		InsertDataList();
		AdminId = new JComboBox<>(SearchAdmin.toArray(new String[0]));
		AdminId.setBounds(100, 50, 150, 30);
		AdminId.setBorder(new LineBorder(new Color(50, 53, 54)));
		C.add(AdminId);

		SearchById = new JButton("Search By Id");
		SearchById.setBounds(260, 50, 150, 30);
		SearchById.setFont(new Font("Times new roman", Font.BOLD, 17));
		SearchById.setBackground(new Color(148, 137, 121));
		SearchById.setForeground(Color.white);
		SearchById.addActionListener(this);
		C.add(SearchById);
		// ---------------------------------------------------
		L3 = new JLabel("Password");
		L3.setBounds(100, 110, 150, 30);
		L3.setFont(new Font("Arial", Font.PLAIN, 17));
		C.add(L3);

		AdminPassword = new JPasswordField();
		AdminPassword.setBounds(200, 110, 210, 30);
		AdminPassword.setBorder(new LineBorder(new Color(50, 53, 54)));

		C.add(AdminPassword);
		// ---------------------------------------------------

		L2 = new JLabel("Admin name");
		L2.setBounds(100, 170, 150, 30);
		L2.setFont(new Font("Arial", Font.PLAIN, 17));
		C.add(L2);

		Firstname = new JLabel();
		Firstname.setBounds(200, 170, 210, 30);
		Firstname.setBorder(new LineBorder(new Color(50, 53, 54)));
		Firstname.setEnabled(false);
		Firstname.setBackground(Color.white);
		Firstname.setOpaque(true);
		C.add(Firstname);
		// ---------------------------------------------------

		L4 = new JLabel("Lastname");
		L4.setBounds(100, 230, 150, 30);
		L4.setFont(new Font("Arial", Font.PLAIN, 17));
		C.add(L4);

		Lastname = new JLabel();
		Lastname.setBounds(200, 230, 210, 30);
		Lastname.setBorder(new LineBorder(new Color(50, 53, 54)));
		Lastname.setBackground(Color.white);
		Lastname.setOpaque(true);
		Lastname.setEnabled(false);
		C.add(Lastname);
		// ---------------------------------------------------

		L5 = new JLabel("Contact");
		L5.setBounds(100, 290, 150, 30);
		L5.setFont(new Font("Arial", Font.PLAIN, 17));
		C.add(L5);

		Contact = new JLabel();
		Contact.setBounds(200, 290, 210, 30);
		Contact.setBorder(new LineBorder(new Color(50, 53, 54)));
		Contact.setBackground(Color.white);
		Contact.setOpaque(true);
		Contact.setEnabled(false);
		C.add(Contact);
		// ---------------------------------------------------
		L5 = new JLabel("Email");
		L5.setBounds(100, 350, 150, 30);
		L5.setFont(new Font("Arial", Font.PLAIN, 17));
		C.add(L5);

		Email = new JLabel();
		Email.setBounds(200, 350, 210, 30);
		Email.setBorder(new LineBorder(new Color(50, 53, 54)));
		Email.setBackground(Color.white);
		Email.setOpaque(true);
		Email.setEnabled(false);
		C.add(Email);

		Submit = new JButton("SUBMIT");
		Submit.setBounds(100, 400, 310, 30);
		Submit.setFont(new Font("Times new roman", Font.BOLD, 19));
		Submit.setBackground(new Color(148, 137, 121));
		Submit.setForeground(Color.white);
		Submit.addActionListener(this);
		C.add(Submit);

		Back = new JButton("BACK");
		Back.setBounds(100, 450, 310, 30);
		Back.setFont(new Font("Times new roman", Font.BOLD, 19));
		Back.setBackground(new Color(148, 137, 121));
		Back.setForeground(Color.white);
		Back.addActionListener(this);
		C.add(Back);

		image = new ImageIcon(ClassLoader.getSystemResource("Pictures/Register.png"));
		Image img2 = image.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
		ImageIcon ic1 = new ImageIcon(img2);

		ImageIcon2 = new JLabel(ic1);
		ImageIcon2.setBounds(450, 100, 300, 300);
		C.add(ImageIcon2);

		setVisible(true);
		setDefaultCloseOperation(AdminRegistration.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new AdminRegistration();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == SearchById) {
			DisplayAdminData();
		}
		if (e.getSource() == Submit) {
			SubmitData();
		}
		if(e.getSource()==Back)
		{
			this.dispose();
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

	public void InsertDataList() {

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdityaAutomative", "root",
					"root");
			Statement statement = connection.createStatement();

			// Execute query to retrieve data

			ResultSet resultSet = statement.executeQuery("SELECT Id FROM Employee where Emp_Role='Admin'");

			while (resultSet.next()) {
				String value = resultSet.getString("Id");
				SearchAdmin.add(value);
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void DisplayAdminData() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdityaAutomative", "root",
					"root");
			// Statement statement = connection.createStatement();
			// Execute query to retrieve data

			InsertAdminDetails = "SELECT Firstname,Lastname,Contact,Email from Employee where id='"
					+ AdminId.getSelectedItem() + "'";
			PreparedStatement statement = connection.prepareStatement(InsertAdminDetails);

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				// String value = resultSet.getString("Id");
				// data.add(value);
				Firstname.setText(resultSet.getString("Firstname"));

				Lastname.setText(resultSet.getString("Lastname"));

				Contact.setText(Long.toString(resultSet.getLong("Contact")));

				Email.setText(resultSet.getString("Email"));

			} else {
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

	public void SubmitData() {
		String EmpId = (String) AdminId.getSelectedItem();
		String PasswordAdmin = AdminPassword.getText();
		String FName = Firstname.getText();
		String LName = Lastname.getText();
		Long CNo = Long.parseLong(Contact.getText());
		String EmpEmail = Email.getText();

		try {

			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO Admin_Details (Admin_Id,Firstname,Lastname,Password,Contact,Email) VALUES (?, ?,?, ?,?,?)");
			preparedStatement.setString(1, EmpId);
			preparedStatement.setString(2, FName);
			preparedStatement.setString(3, LName);
			preparedStatement.setString(4, PasswordAdmin);
			preparedStatement.setLong(5, CNo);
			preparedStatement.setString(6, EmpEmail);

			preparedStatement.executeUpdate();

			JOptionPane.showMessageDialog(this, "Admin data inserted successfully");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Admin already available");
			System.out.println(e);
		}
	}
}
