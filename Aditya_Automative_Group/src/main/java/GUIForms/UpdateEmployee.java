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

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class UpdateEmployee extends JFrame implements ActionListener {

	JLabel L1, L2, L3, L4, L5, L6, L7, L8, L9, L10, L11, L12, UBirthDate, UJoiningDate, UEmpGender;
	JTextField UFirstName, ULastName, UContactNo, UEmailId, UEmpSalary;
	JTextArea UEmpAddress;
	JComboBox UEmpEducation, URole;
	JPanel P;
	JButton Update, Cancle, ButtonSearch;

	Border blackline = BorderFactory.createLineBorder(Color.black);

	String[] Edu = { "SSC", "HSC", "Graduation", "Post Graduation", "Master Degree", "Diploma" };
	String[] Gender = { "Male", "Female" };
	String[] Roles = { "Manager", "Supervisor", "Accountant", "Salesman", "Worker", "Admin" };

	Connection connection;
	PreparedStatement preparedStatement;
	private JComboBox<String> SearchById;
	ArrayList<String> data = new ArrayList<>();
	String value1, Value2;
	ConnectionClass CCL = new ConnectionClass();
	String EmpId, V1, V2, V4, V5, V6, V7;
	Long V3;
	double V8;
	String Query1;

	// String Efname,Elname,Econtact,Eemail,Eeducation,Eaddress,Esalary,Erole;
	public UpdateEmployee(String EmpId) {
		this.EmpId = EmpId;
		CCL.createConnection();
		InsertDataList();

		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;
        setLocation(x, y);
        
		setTitle("Update Employee Details");
		setBounds(200, 0, 1000, 700);
		P = new JPanel();
		P.setBackground(Color.black);
		setLayout(null);
		setUndecorated(true);
//		L2 = new JLabel("Search by id");
//		L2.setBounds(150, 10, 150, 50);
//		L2.setFont(new Font("Serif", Font.PLAIN, 20));
//		L2.setForeground(Color.black);
//		add(L2);
//
//		SearchById = new JComboBox<>(data.toArray(new String[0]));
//		SearchById.setBounds(150, 70, 180, 30);
//		add(SearchById);
//
//		ButtonSearch = new JButton("Search");
//		ButtonSearch.setBounds(350, 70, 180, 30);
//		ButtonSearch.setFont(new Font("Arial", Font.PLAIN, 19));
//		add(ButtonSearch);
//		ButtonSearch.addActionListener(this);
//-----------------------------------------------------------		
		L3 = new JLabel("First Name");
		L3.setBounds(150, 110, 150, 50);
		L3.setFont(new Font("Serif", Font.PLAIN, 20));
		L3.setForeground(Color.black);
		add(L3);

		UFirstName = new JTextField();
		UFirstName.setBounds(150, 160, 180, 30);
		add(UFirstName);
		// -----------------------------------------------------

		L3 = new JLabel("Last Name");
		L3.setBounds(410, 110, 150, 50);
		L3.setFont(new Font("Serif", Font.PLAIN, 20));
		L3.setForeground(Color.black);
		add(L3);

		ULastName = new JTextField();
		ULastName.setBounds(410, 160, 180, 30);
		add(ULastName);
		// -----------------------------------------------------

		L4 = new JLabel("Contact Number");
		L4.setBounds(150, 200, 150, 50);
		L4.setFont(new Font("Serif", Font.PLAIN, 20));
		L4.setForeground(Color.black);
		add(L4);

		UContactNo = new JTextField();
		UContactNo.setBounds(150, 250, 180, 30);
		add(UContactNo);
		// -----------------------------------------------------

		L5 = new JLabel("Email");
		L5.setBounds(410, 200, 150, 50);
		L5.setFont(new Font("Serif", Font.PLAIN, 20));
		L5.setForeground(Color.black);
		add(L5);

		UEmailId = new JTextField();
		UEmailId.setBounds(410, 250, 180, 30);
		add(UEmailId);
		// -----------------------------------------------------
		L6 = new JLabel("Education");
		L6.setBounds(150, 290, 150, 50);
		L6.setFont(new Font("Serif", Font.PLAIN, 20));
		L6.setForeground(Color.black);
		add(L6);

		UEmpEducation = new JComboBox(Edu);
		UEmpEducation.setBounds(150, 340, 180, 30);
		add(UEmpEducation);
		// -----------------------------------------------------
		L7 = new JLabel("Gender");
		L7.setBounds(410, 290, 150, 50);
		L7.setFont(new Font("Serif", Font.PLAIN, 20));
		L7.setForeground(Color.black);
		add(L7);

		UEmpGender = new JLabel();
		UEmpGender.setBounds(410, 340, 180, 30);
		UEmpGender.setFont(new Font("Arial", Font.PLAIN, 17));
		add(UEmpGender);
		// -----------------------------------------------------

		L8 = new JLabel("Address");
		L8.setBounds(150, 380, 150, 50);
		L8.setFont(new Font("Serif", Font.PLAIN, 20));
		L8.setForeground(Color.black);
		add(L8);

		UEmpAddress = new JTextArea();
		UEmpAddress.setBounds(150, 430, 450, 80);
		UEmpAddress.setLineWrap(true);
		UEmpAddress.setWrapStyleWord(true);
		UEmpAddress.setBorder(blackline);
		add(UEmpAddress);
		// -----------------------------------------------------

		L9 = new JLabel("Birth date");
		L9.setBounds(150, 520, 150, 30);
		L9.setFont(new Font("Arial", Font.PLAIN, 17));
		add(L9);

		UBirthDate = new JLabel();
		UBirthDate.setBounds(150, 550, 180, 30);
		UBirthDate.setFont(new Font("Arial", Font.PLAIN, 17));
		add(UBirthDate);
		// -----------------------------------------------------

		L10 = new JLabel("Joining Date");
		L10.setBounds(410, 520, 150, 30);
		L10.setFont(new Font("Arial", Font.PLAIN, 17));
		add(L10);

		UJoiningDate = new JLabel("" + V7);
		UJoiningDate.setBounds(410, 550, 180, 30);
		UJoiningDate.setFont(new Font("Arial", Font.PLAIN, 17));
		add(UJoiningDate);
		// -----------------------------------------------------
		L11 = new JLabel("Salary");
		L11.setBounds(660, 110, 150, 50);
		L11.setFont(new Font("Serif", Font.PLAIN, 20));
		L11.setForeground(Color.black);
		add(L11);

		UEmpSalary = new JTextField();
		UEmpSalary.setBounds(660, 160, 180, 30);
		add(UEmpSalary);
		// -----------------------------------------------------

		L12 = new JLabel("Role");
		L12.setBounds(660, 200, 150, 50);
		L12.setFont(new Font("Serif", Font.PLAIN, 20));
		L12.setForeground(Color.black);
		add(L12);

		URole = new JComboBox(Roles);
		URole.setBounds(660, 250, 180, 30);
		add(URole);

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdityaAutomative", "root",
					"root");
			// Statement statement = connection.createStatement();
			// Execute query to retrieve data

			Query1 = "SELECT Firstname,Lastname,Contact,Email,Education,Address,Birth_Date,Joining_Date,Salary,Emp_Role FROM Employee where id='"
					+ EmpId + "'";
			PreparedStatement statement = connection.prepareStatement(Query1);

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				// String value = resultSet.getString("Id");
				// data.add(value);
				UFirstName.setText(resultSet.getString("Firstname"));

				ULastName.setText(resultSet.getString("Lastname"));

				UContactNo.setText(Long.toString(resultSet.getLong("Contact")));

				UEmailId.setText(resultSet.getString("Email"));

				UEmpAddress.setText(resultSet.getString("Address"));
				UBirthDate.setText(resultSet.getString("Birth_Date"));
				UJoiningDate.setText(resultSet.getString("Joining_Date"));
				UEmpSalary.setText(Double.toString(resultSet.getDouble("Salary")));

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

		Update = new JButton("Update");
		Update.setBounds(660, 430, 180, 30);
		Update.setFont(new Font("Arial", Font.PLAIN, 19));
		add(Update);
		Update.addActionListener(this);

		Cancle = new JButton("Cancle");
		Cancle.setBounds(660, 490, 180, 30);
		Cancle.setFont(new Font("Arial", Font.PLAIN, 19));
		add(Cancle);
		Cancle.addActionListener(this);
		setVisible(true);
		setDefaultCloseOperation(AddEmployee.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Cancle) {
			new ViewEmployee();
			this.dispose();
		} else if (e.getSource() == Update) {
			// UpdateEmployeeDetails();
			String Efname = UFirstName.getText();
			String Elname = ULastName.getText();
			String Econtact = UContactNo.getText();
			String Eemail = UEmailId.getText();
			String Eeducation = (String) UEmpEducation.getSelectedItem();
			String Eaddress = UEmpAddress.getText();
			String Esalary = UEmpSalary.getText();
			String Erole = (String) URole.getSelectedItem();
			try {
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdityaAutomative",
						"root", "root");
				// String Query ="Update Employee set Firstname='"+Efname+"',
				// Lastname='"+Elname+"',Contact ='"+Econtact+"', Email='"+Eemail+"',
				// Address='"+Eaddress+"', Education='"+Eeducation+"', Salary='"+Esalary+"' ,
				// Emp_Role='"+Erole+"' where Id='"+EmpId+"'";
				String Query2 = "Update Employee set Firstname=?, Lastname=?,Contact =?, Email=?,Education=? , Address=?,  Salary=? , Emp_Role=? where Id='"
						+ EmpId + "'";

				PreparedStatement statement = connection.prepareStatement(Query2);
				// Statement statement = connection.createStatement();
				// Execute query to update data
				// ResultSet resultSet = statement.executeQuery(Query);
				statement.setString(1, Efname);
				statement.setString(2, Elname);
				statement.setString(3, Econtact);
				statement.setString(4, Eemail);
				statement.setString(5, Eeducation);
				statement.setString(6, Eaddress);
				statement.setString(7, Esalary);
				statement.setString(8, Erole);

				JOptionPane.showMessageDialog(null, "Update successfully");

				int rowsUpdated = statement.executeUpdate();

				// Handle the result

				// resultSet.close();
				statement.close();
				connection.close();

			} catch (Exception ae) {
				System.out.println(ae);
			}
		}
	}

	public static void main(String[] args) {
		new UpdateEmployee("");
	}

	public void InsertDataList() {
		{
			try {
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdityaAutomative",
						"root", "root");
				Statement statement = connection.createStatement();

				// Execute query to retrieve data

				ResultSet resultSet = statement.executeQuery("SELECT Id FROM Employee");

				while (resultSet.next()) {
					String value = resultSet.getString("Id");
					data.add(value);
				}
				resultSet.close();
				statement.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

//	public void DisplayData() {
//		
//		
//	}
//	
//	
//	public void UpdateEmployeeDetails()
//	{
//		
//	}
}
