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
import java.sql.SQLException;
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

import com.toedter.calendar.JDateChooser;

import GUIForms.ConnectionClass;

public class AddEmployee extends JFrame implements ActionListener {
	JLabel L1, L2, L3, L4, L5, L6, L7, L8, L9, L10, L11, L12, Lemployeedetails;
	JTextField FirstName, LastName, ContactNo, EmailId, EmpSalary;
	JTextArea EmpAddress;
	JComboBox EmpEducation, EmpGender, Role;
	JPanel P;
	JButton Submit, Cancle;
	Random random2 = new Random();
	int RandomNumber = random2.nextInt(900000) + 100000;
	Border blackline = BorderFactory.createLineBorder(Color.black);
	JDateChooser BirthDate = new com.toedter.calendar.JDateChooser();
	JDateChooser JoiningDate = new com.toedter.calendar.JDateChooser();

	String[] Edu = { "SSC", "HSC", "Graduation", "Post Graduation", "Master Degree", "Diploma" };
	String[] Gender = { "Male", "Female" };
	String[] Roles = { "Manager", "Supervisor", "Accountant", "Salesman", "Worker", "Admin" };

	Connection connection;
	PreparedStatement preparedStatement;
	ConnectionClass Cclass = new ConnectionClass();

	AddEmployee() {
		setTitle("Enter Car Details");
		setBounds(200, 0, 1000, 700);
		P = new JPanel();
		getContentPane().setBackground(Color.white);
		setLayout(null);
		setUndecorated(true);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - this.getWidth()) / 2;
		int y = (screenSize.height - this.getHeight()) / 2;
		setLocation(x, y);

		L1 = new JLabel("Employee Id");
		L1.setBounds(150, 20, 100, 50);
		L1.setFont(new Font("Serif", Font.PLAIN, 20));
		L1.setForeground(Color.black);
		add(L1);

		L2 = new JLabel("" + RandomNumber);
		L2.setBounds(150, 60, 100, 50);
		L2.setFont(new Font("Serif", Font.PLAIN, 20));
		L2.setForeground(Color.pink);
		add(L2);

		L3 = new JLabel("First Name");
		L3.setBounds(150, 110, 150, 50);
		L3.setFont(new Font("Serif", Font.PLAIN, 20));
		L3.setForeground(Color.black);
		add(L3);

		FirstName = new JTextField();
		FirstName.setBounds(150, 160, 180, 30);
		add(FirstName);
		// -----------------------------------------------------

		L3 = new JLabel("Last Name");
		L3.setBounds(410, 110, 150, 50);
		L3.setFont(new Font("Serif", Font.PLAIN, 20));
		L3.setForeground(Color.black);
		add(L3);

		LastName = new JTextField();
		LastName.setBounds(410, 160, 180, 30);
		add(LastName);
		// -----------------------------------------------------

		L4 = new JLabel("Contact Number");
		L4.setBounds(150, 200, 150, 50);
		L4.setFont(new Font("Serif", Font.PLAIN, 20));
		L4.setForeground(Color.black);
		add(L4);

		ContactNo = new JTextField();
		ContactNo.setBounds(150, 250, 180, 30);
		add(ContactNo);
		// -----------------------------------------------------

		L5 = new JLabel("Email");
		L5.setBounds(410, 200, 150, 50);
		L5.setFont(new Font("Serif", Font.PLAIN, 20));
		L5.setForeground(Color.black);
		add(L5);

		EmailId = new JTextField();
		EmailId.setBounds(410, 250, 180, 30);
		add(EmailId);
		// -----------------------------------------------------
		L6 = new JLabel("Education");
		L6.setBounds(150, 290, 150, 50);
		L6.setFont(new Font("Serif", Font.PLAIN, 20));
		L6.setForeground(Color.black);
		add(L6);

		EmpEducation = new JComboBox(Edu);
		EmpEducation.setBounds(150, 340, 180, 30);
		add(EmpEducation);
		// -----------------------------------------------------
		L7 = new JLabel("Gender");
		L7.setBounds(410, 290, 150, 50);
		L7.setFont(new Font("Serif", Font.PLAIN, 20));
		L7.setForeground(Color.black);
		add(L7);

		EmpGender = new JComboBox(Gender);
		EmpGender.setBounds(410, 340, 180, 30);
		add(EmpGender);
		// -----------------------------------------------------

		L8 = new JLabel("Address");
		L8.setBounds(150, 380, 150, 50);
		L8.setFont(new Font("Serif", Font.PLAIN, 20));
		L8.setForeground(Color.black);
		add(L8);

		EmpAddress = new JTextArea();
		EmpAddress.setBounds(150, 430, 450, 80);
		EmpAddress.setLineWrap(true);
		EmpAddress.setWrapStyleWord(true);
		EmpAddress.setBorder(blackline);
		add(EmpAddress);
		// -----------------------------------------------------
//		L9 = new JLabel("Salary");
//		L9.setBounds(150, 550, 150, 50);
//		L9.setFont(new Font("Serif", Font.PLAIN, 20));
//		L9.setForeground(Color.black);
//		add(L9);
//		
//		EmpSalary = new JTextField();
//		EmpSalary.setBounds(150, 600, 180, 30);
//		add(EmpSalary);		

		L9 = new JLabel("Birth date");
		L9.setBounds(150, 520, 150, 30);
		L9.setFont(new Font("Arial", Font.PLAIN, 17));
		add(L9);

		BirthDate.setBounds(150, 550, 180, 30);
		BirthDate.setFont(new Font("Arial", Font.PLAIN, 17));
		add(BirthDate);
		// -----------------------------------------------------

		L10 = new JLabel("Joining Date");
		L10.setBounds(410, 520, 150, 30);
		L10.setFont(new Font("Arial", Font.PLAIN, 17));
		add(L10);

		JoiningDate.setBounds(410, 550, 180, 30);
		JoiningDate.setFont(new Font("Arial", Font.PLAIN, 17));
		add(JoiningDate);
		// -----------------------------------------------------
		L11 = new JLabel("Salary");
		L11.setBounds(660, 110, 150, 50);
		L11.setFont(new Font("Serif", Font.PLAIN, 20));
		L11.setForeground(Color.black);
		add(L11);

		EmpSalary = new JTextField();
		EmpSalary.setBounds(660, 160, 180, 30);
		add(EmpSalary);
		// -----------------------------------------------------

		L12 = new JLabel("Role");
		L12.setBounds(660, 200, 150, 50);
		L12.setFont(new Font("Serif", Font.PLAIN, 20));
		L12.setForeground(Color.black);
		add(L12);

		Role = new JComboBox(Roles);
		Role.setBounds(660, 250, 180, 30);
		add(Role);

		Submit = new JButton("Add Employee");
		Submit.setBounds(660, 430, 180, 48);
		Submit.setFont(new Font("Times new roman", Font.BOLD, 19));
		Submit.setForeground(Color.white);
		Submit.setBackground(new Color(6, 17, 60));
		add(Submit);
		Submit.addActionListener(this);

		Cancle = new JButton("Cancle Process");
		Cancle.setBounds(660, 490, 180, 48);
		Cancle.setFont(new Font("Times new roman", Font.BOLD, 19));
		Cancle.setForeground(Color.white);
		Cancle.setBackground(new Color(6, 17, 60));
		add(Cancle);
		Cancle.addActionListener(this);
		setVisible(true);
		setDefaultCloseOperation(AddEmployee.EXIT_ON_CLOSE);
		// Cclass.createConnection();
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

	private void InsertEmpData() {

		int EmpId = Integer.parseInt(L2.getText());
		String FName = FirstName.getText();
		String LName = LastName.getText();
		Long CNo = Long.parseLong(ContactNo.getText());
		String EmpEmail = EmailId.getText();
		String Edu = (String) EmpEducation.getSelectedItem();
		String EGender = (String) EmpGender.getSelectedItem();
		String EAddress = EmpAddress.getText();
		String EBirthDate = ((JTextField) BirthDate.getDateEditor().getUiComponent()).getText();
		String EJoiningDate = ((JTextField) JoiningDate.getDateEditor().getUiComponent()).getText();
		double ESalary = Double.parseDouble(EmpSalary.getText());
		String ERole = (String) Role.getSelectedItem();

		try {

			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO Employee (Id,Firstname,Lastname,Contact,Email,Education,Gender,Address,Birth_Date,Joining_Date,Salary,Emp_Role) VALUES (?, ?, ?,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setInt(1, EmpId);
			preparedStatement.setString(2, FName);
			preparedStatement.setString(3, LName);
			preparedStatement.setLong(4, CNo);
			preparedStatement.setString(5, EmpEmail);
			preparedStatement.setString(6, Edu);
			preparedStatement.setString(7, EGender);
			preparedStatement.setString(8, EAddress);
			preparedStatement.setString(9, EBirthDate);
			preparedStatement.setString(10, EJoiningDate);
			preparedStatement.setDouble(11, ESalary);
			preparedStatement.setString(12, ERole);

			preparedStatement.executeUpdate();

			JOptionPane.showMessageDialog(this, "Employee data inserted successfully");
		} catch (SQLException e) {
			// JOptionPane.showMessageDialog(this, "Failed to insert employee data");
			System.out.println(e);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Submit) {
			CreateConnection();
			InsertEmpData();
		}

		if (e.getSource() == Cancle) {
			// this.dispose();
			setVisible(false);
			new HomeScreen();
		}

	}

	public static void main(String[] args) {
		new AddEmployee();
	}
}
