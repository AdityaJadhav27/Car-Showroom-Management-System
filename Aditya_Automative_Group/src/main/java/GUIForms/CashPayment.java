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
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class CashPayment extends JFrame implements ActionListener{
	String Pcost, CId;
	String Oid;
	JLabel L1, L2, L3, L4, L5;
	JPanel P;
	JTextField TotalAmount, Notes_100,Notes_200,Notes_500,Notes_2000;
	JButton SubmitDetails;
	Connection connection;
	Random RandomPaymentId = new Random();
	int GenerateRandomId = RandomPaymentId.nextInt(900000) + 100000;
	CashPayment(String Pcost, String CId, String Oid){
		this.Pcost = Pcost;
		this.CId = CId;
		this.Oid = Oid;
		
		
		
		setUndecorated(true);
		//CreateConnection();
		setSize(500, 450);
		//getContentPane().setBackground(Color.white);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - this.getWidth()) / 2;
		int y = (screenSize.height - this.getHeight()) / 2;
		setLocation(x, y);
		setLayout(null);
		
		CreateConnection();
		
		L1 = new JLabel("Total Cost");
		L1.setBounds(50, 90, 150, 30);
		L1.setFont(new Font("Times new roman", Font.PLAIN, 17));
		add(L1);
		
		//TotalAmount.setText(Pcost);
		TotalAmount = new JTextField();
		TotalAmount.setBounds(50, 130, 420, 30);
		TotalAmount.setBorder(new LineBorder(new Color(50, 53, 54)));
		TotalAmount.setEnabled(false);
		TotalAmount.setOpaque(true);
		TotalAmount.setText(Pcost);
		add(TotalAmount);
		
		
		L2 = new JLabel("100rs Notes");
		L2.setBounds(50, 180, 150, 30);
		L2.setFont(new Font("Times new roman", Font.PLAIN, 17));
		add(L2);

		Notes_100 = new JTextField();
		Notes_100.setBounds(50, 220, 50, 30);
		Notes_100.setBorder(new LineBorder(new Color(50, 53, 54)));
		add(Notes_100);
		
		L3 = new JLabel("200rs Notes");
		L3.setBounds(150, 180, 150, 30);
		L3.setFont(new Font("Times new roman", Font.PLAIN, 17));
		add(L3);

		Notes_200 = new JTextField();
		Notes_200.setBounds(150, 220, 50, 30);
		Notes_200.setBorder(new LineBorder(new Color(50, 53, 54)));
		add(Notes_200);
		
		L3 = new JLabel("500rs Notes");
		L3.setBounds(250, 180, 150, 30);
		L3.setFont(new Font("Times new roman", Font.PLAIN, 17));
		add(L3);

		Notes_500 = new JTextField();
		Notes_500.setBounds(250, 220, 50, 30);
		Notes_500.setBorder(new LineBorder(new Color(50, 53, 54)));
		add(Notes_500);
		
		L4 = new JLabel("2000rs Notes");
		L4.setBounds(350, 180, 150, 30);
		L4.setFont(new Font("Times new roman", Font.PLAIN, 17));
		add(L4);

		Notes_2000 = new JTextField();
		Notes_2000.setBounds(350, 220, 50, 30);
		Notes_2000.setBorder(new LineBorder(new Color(50, 53, 54)));
		add(Notes_2000);
		
		
		
		SubmitDetails = new JButton("Submit details");
		SubmitDetails.setBounds(50, 300, 420, 50);
		SubmitDetails.setFont(new Font("Times new roman", Font.BOLD, 19));
		SubmitDetails.setBackground(new Color(101, 183, 65));
		SubmitDetails.setForeground(Color.white);
		SubmitDetails.addActionListener(this);;
		add(SubmitDetails);

		setVisible(true);
		setDefaultCloseOperation(AdminRegistration.EXIT_ON_CLOSE);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==SubmitDetails)
		{
			AddCashData();
		}
	}
   public static void main(String[] args)
   {
	   new CashPayment("","","");
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

	public void AddCashData() {
		
		try {
			String InsertPaymentDetails = "INSERT INTO Cash_Payment_Details (Payment_Id,Paid_Amount,100_Notes,200_Notes, 500_Notes,2000_Notes, Customer_Id,Order_Id) VALUES (?, ?, ?, ?, ?, ?, ?,?)";

			PreparedStatement statement = connection.prepareStatement(InsertPaymentDetails);

			statement.setInt(1, GenerateRandomId);
			statement.setString(2,Pcost);
			statement.setString(3, Notes_100.getText());
			statement.setString(4, Notes_200.getText());
			statement.setString(5, Notes_500.getText());
			statement.setString(6, Notes_2000.getText());
			statement.setString(7, CId);
			statement.setString(8, Oid);
			
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
		Notes_100.setText("");
		Notes_200.setText("");
		Notes_500.setText("");
		Notes_2000.setText("");
	}
}
