package GUIForms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
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
import javax.swing.table.DefaultTableModel;

public class OrderDetails extends JFrame implements ActionListener {
	JTable Table, T2;
	JScrollPane Scroll;
	ConnectionClass Cclass = new ConnectionClass();
	JLabel L1;
	JComboBox SearchText;
	JButton Update, Remove, Back, Print;
	ArrayList<String> data = new ArrayList<>();
	DefaultTableModel model;

	OrderDetails() {
		getContentPane().setBackground(new Color(28, 22, 120));
		setLayout(null);
		setSize(1300, 600);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - this.getWidth()) / 2;
		int y = (screenSize.height - this.getHeight()) / 2;

		String[] Columns = { "Customer Id", "Car name", "Car Company", "Engine", "Model", "Cost", "Description", "Sells Date",
				"Order Id"};

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdityaAutomative", "root",
					"root");

			// Execute the query to retrieve employee details
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String query = "SELECT * FROM Order_Details";
			ResultSet resultSet = statement.executeQuery(query);

			resultSet.last();
			int rowCount = resultSet.getRow();
			resultSet.beforeFirst();

			Object[][] data = new Object[rowCount][9];
			int i = 0;
			// Populate data into the table model
			while (resultSet.next()) {
				Object[] row = { data[i][0] = resultSet.getInt("Customer_Id"),
						data[i][1] = resultSet.getString("Car_Name"),
						data[i][2] = resultSet.getString("Car_Company"),
						data[i][3] = resultSet.getString("Car_Engine"),
						data[i][4] = resultSet.getString("Car_Model"),
						data[i][5] = resultSet.getString("Cost"), 
						data[i][6] = resultSet.getString("Description"),
						data[i][7] = resultSet.getString("Sell_Date"), 
						data[i][8] = resultSet.getString("Order_Id") };
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

			JOptionPane.showMessageDialog(this, e);
		}

		EditComponent();
		InsertDataList();
		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(AdminLogin.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new OrderDetails();
	}

	public void EditComponent() {
		
		L1 = new JLabel("--- ORDER DETAILS ---");
		L1.setBounds(500, 10, 500, 50);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		 if (e.getSource() == Back) {
			this.dispose();
			 

		} else if (e.getSource() == Print) {
			try {
				Table.print();
			} catch (Exception ea) {
				JOptionPane.showMessageDialog(this, ea);
			}
		}
	}

	public void InsertDataList() {

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdityaAutomative", "root",
					"root");
			Statement statement = connection.createStatement();

			// Execute query to retrieve data

			ResultSet resultSet = statement.executeQuery("SELECT Order_Id FROM Order_Details ");

			while (resultSet.next()) {
				String value = resultSet.getString("Order_Id");
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
