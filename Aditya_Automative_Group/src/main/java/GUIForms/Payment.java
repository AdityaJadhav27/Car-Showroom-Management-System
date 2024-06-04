package GUIForms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Payment extends JFrame implements ActionListener {
	Connection connection;

	JTable Table;
	JScrollPane Scroll;
	ConnectionClass Cclass = new ConnectionClass();
	JLabel L1;
	JComboBox SearchText;
	JButton Back, Print, PaidByCredit;

	Payment() {

		getContentPane().setBackground(new Color(28, 22, 120));
		setLayout(null);
		
		CreateConnection();
		setSize(1300, 600);
		EditComponent();
		String[] Columns = { "Payment Id", "Cost", "100rs Notes", "200rs Notes", "500rs Notes", "2000rs Notes",
				"Customer Id", "Order Id" };

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdityaAutomative", "root",
					"root");

			// Execute the query to retrieve employee details
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String query = "select * from Cash_Payment_Details";
			ResultSet resultSet = statement.executeQuery(query);

			resultSet.last();
			int rowCount = resultSet.getRow();
			resultSet.beforeFirst();

			Object[][] data = new Object[rowCount][12];
			int i = 0;
			// Populate data into the table model
			while (resultSet.next()) {
				Object[] row = { data[i][0] = resultSet.getInt("Payment_Id"),
						data[i][1] = resultSet.getString("Paid_Amount"), data[i][2] = resultSet.getString("100_Notes"),
						data[i][3] = resultSet.getString("200_Notes"), data[i][4] = resultSet.getString("500_Notes"),
						data[i][5] = resultSet.getString("2000_Notes"), data[i][6] = resultSet.getString("Customer_Id"),
						data[i][7] = resultSet.getString("Order_Id") };
				i++;

				Table = new JTable(data, Columns);
				Scroll = new JScrollPane(Table);
				Scroll.setBounds(0, 150, 1300, 600);
				getContentPane().add(Scroll, BorderLayout.CENTER);
				// Table.setBackground(new Color(245, 247, 247));;
				add(Scroll);

			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(AdminLogin.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new Payment();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Back) {
			this.dispose();
		}
		if (e.getSource() == Print) {
			try {
				Table.print();
			} catch (Exception eae) {
				JOptionPane.showMessageDialog(this, eae);
			}
		}
		if (e.getSource() == PaidByCredit) {
			this.dispose();
			new ViewCreditCardDetails();
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

	public void EditComponent() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - this.getWidth()) / 2;
		int y = (screenSize.height - this.getHeight()) / 2;

		L1 = new JLabel("--- CASH PAYMENT DETAILS ---");
		L1.setBounds(450, 10, 500, 50);
		L1.setFont(new Font("Times new roman", Font.BOLD, 25));
		L1.setForeground(Color.white);
		add(L1);

		Back = new JButton("Back");
		Back.setBounds(50, 100, 130, 25);
		Back.setFont(new Font("Times new roman", Font.BOLD, 15));
		add(Back);
		Back.addActionListener(this);

		Print = new JButton("Print");
		Print.setBounds(200, 100, 130, 25);
		Print.setFont(new Font("Times new roman", Font.BOLD, 15));
		add(Print);
		Print.addActionListener(this);

		PaidByCredit = new JButton("Credit Card Details");
		PaidByCredit.setBounds(350, 100, 170, 25);
		PaidByCredit.setFont(new Font("Times new roman", Font.BOLD, 15));
		add(PaidByCredit);
		PaidByCredit.addActionListener(this);

	}
}
