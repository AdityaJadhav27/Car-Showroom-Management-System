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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ViewCustomer extends JFrame implements ActionListener {

	JTable Table;
	JScrollPane Scroll;
	ConnectionClass Cclass = new ConnectionClass();
	JLabel L1;
	JComboBox SearchText;
	JButton Back, Print;
	ArrayList<String> CustomerData = new ArrayList<>();

	ViewCustomer() {
		getContentPane().setBackground(new Color(28, 22, 120));
		setLayout(null);
		setSize(1300, 600);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - this.getWidth()) / 2;
		int y = (screenSize.height - this.getHeight()) / 2;
		setLocation(x, y);

		InsertDataList();
		EditComponent();

		String[] Columns = { "ID", "First Name", "Last Name", "Contact", "Aadhar No", "Payment Mode", "Address",
				"Booking Date"};

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdityaAutomative", "root",
					"root");

			// Execute the query to retrieve employee details
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String query = "select * from Customer_Details";
			ResultSet resultSet = statement.executeQuery(query);

			resultSet.last();
			int rowCount = resultSet.getRow();
			resultSet.beforeFirst();

			Object[][] data = new Object[rowCount][12];
			int i = 0;
			// Populate data into the table model
			while (resultSet.next()) {
				Object[] row = { data[i][0] = resultSet.getInt("Customer_Id"),
						data[i][1] = resultSet.getString("Firstname"), data[i][2] = resultSet.getString("Lastname"),
						data[i][3] = resultSet.getString("Contact"), data[i][4] = resultSet.getString("Aadhar_No"),
						data[i][5] = resultSet.getString("Payment_Mode"), data[i][6] = resultSet.getString("Address"),
						data[i][7] = resultSet.getString("Booking_Date") };
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
			// System.out.println(e);
			e.printStackTrace();
		}

		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(AdminLogin.EXIT_ON_CLOSE);
	}

	public void EditComponent() {
		L1 = new JLabel("--- CUSTOMER DETAILS ---");
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

	}

	public static void main(String[] args) {
		new ViewCustomer();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
         if(e.getSource()==Back)
         {
        	 this.dispose();
         }
         if(e.getSource()==Print)
         {
        	 try {
                 Table.print();
  			}catch(Exception eae)
  			{
  				JOptionPane.showMessageDialog(this, eae);
  			}
         }
	}

	public void InsertDataList() {

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdityaAutomative", "root",
					"root");
			Statement statement = connection.createStatement();

			// Execute query to retrieve data

			ResultSet resultSet = statement.executeQuery("select Customer_Id from Customer_Details");

			while (resultSet.next()) {
				String value = resultSet.getString("Customer_Id");
				CustomerData.add(value);
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
