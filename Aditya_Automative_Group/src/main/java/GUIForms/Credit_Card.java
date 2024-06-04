package GUIForms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Credit_Card extends JFrame implements ActionListener {
	String PaymentModeType, CId;
	String Oid;

	JLabel L1, L2, L3, L4, L5;
	JPanel P;
	JTextField CardNumber, CVC, AccountHolderName;
	JComboBox Day, Month, Year;
	String[] DayList = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
	String[] MonthList = {"Jaunary","February","March","April","May","June","July","August","September","Octomber","November","December"};
	String[] YearList = {"2025","2026","2027","2028","2029","2030","2031","2032","2033","2034","2035"};
	Random RandomPaymentId = new Random();
	int GenerateRandomId = RandomPaymentId.nextInt(900000) + 100000;
	JButton ConfirmCardPayment;
	Connection connection;

	Credit_Card(String PaymentModeType, String CId, String Oid) {
		this.PaymentModeType = PaymentModeType;
		this.CId = CId;
		this.Oid = Oid;
		setUndecorated(true);
		//CreateConnection();
		setSize(500, 550);
		getContentPane().setBackground(Color.white);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - this.getWidth()) / 2;
		int y = (screenSize.height - this.getHeight()) / 2;
		setLocation(x, y);
		setLayout(null);

		L1 = new JLabel("Card Number");
		L1.setBounds(50, 90, 150, 30);
		L1.setFont(new Font("Arial", Font.PLAIN, 17));
		add(L1);

		CardNumber = new JTextField();
		CardNumber.setBounds(50, 130, 420, 30);
		CardNumber.setBorder(new LineBorder(new Color(50, 53, 54)));
		add(CardNumber);

		L2 = new JLabel("Expiration Date");
		L2.setBounds(50, 180, 150, 30);
		L2.setFont(new Font("Arial", Font.PLAIN, 17));
		add(L2);

		Day = new JComboBox(DayList);
		Day.setBounds(50, 220, 50, 30);
		add(Day);

		Month = new JComboBox(MonthList);
		Month.setBounds(110, 220, 100, 30);
		add(Month);

		Year = new JComboBox(YearList);
		Year.setBounds(230, 220, 100, 30);
		add(Year);

		L3 = new JLabel("CVC");
		L3.setBounds(370, 180, 150, 30);
		L3.setFont(new Font("Arial", Font.PLAIN, 17));
		add(L3);

		CVC = new JTextField();
		CVC.setBounds(370, 220, 100, 30);
		add(CVC);

		L4 = new JLabel("Account Holder Name");
		L4.setBounds(50, 270, 150, 30);
		add(L4);

		AccountHolderName = new JTextField();
		AccountHolderName.setBounds(50, 310, 420, 30);
		add(AccountHolderName);

		ConfirmCardPayment = new JButton("Confirm Payment");
		ConfirmCardPayment.setBounds(50, 370, 420, 50);
		ConfirmCardPayment.setFont(new Font("Times new roman", Font.BOLD, 19));
		ConfirmCardPayment.setBackground(new Color(101, 183, 65));
		ConfirmCardPayment.setForeground(Color.white);
		ConfirmCardPayment.addActionListener(this);;
		add(ConfirmCardPayment);

		setVisible(true);
		setDefaultCloseOperation(AdminRegistration.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Credit_Card("", "", "");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == ConfirmCardPayment) {
			System.out.println(CId);
			
			CreateConnection();
			AddPaymentData();
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

	public void AddPaymentData() {
		System.out.println(CId);
		
		String CombineDateFormat = (String) Day.getSelectedItem() + "/" + Month.getSelectedItem() + "/"
				+ Year.getSelectedItem();
		try {
			String InsertPaymentDetails = "INSERT INTO Credit_Card_Details (Payment_Id, Card_Number,CVC,Card_Holder_Name, Expiry_Date,Customer_Id, Order_Id) VALUES (?, ?, ?, ?, ?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(InsertPaymentDetails);

			statement.setInt(1, GenerateRandomId);
			statement.setBigDecimal(2, new BigDecimal(CardNumber.getText()));
			statement.setString(3, CVC.getText());
			statement.setString(4, AccountHolderName.getText());
			statement.setString(5, CombineDateFormat);
			statement.setString(6, CId);
			statement.setString(7, Oid);
			//System.out.println("Insert Succesfully");
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				JOptionPane.showMessageDialog(this, "Payment successfull");
				clearFields();
			}
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
		} 
	}

	private void clearFields() {
		CardNumber.setText("");
		CVC.setText("");
		AccountHolderName.setText("");
	}
}
