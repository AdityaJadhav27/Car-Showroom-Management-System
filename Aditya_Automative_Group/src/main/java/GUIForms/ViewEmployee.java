package GUIForms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ViewEmployee extends JFrame implements ActionListener {

	JTable Table, T2;
	JScrollPane Scroll;
	ConnectionClass Cclass = new ConnectionClass();
	JLabel L1;
	JComboBox SearchText;
	JButton Update, Remove, Back, Print;
	ArrayList<String> data = new ArrayList<>();
	DefaultTableModel model;

	ViewEmployee() {

		getContentPane().setBackground(new Color(28, 22, 120));
		setLayout(null);
		setSize(1300, 600);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - this.getWidth()) / 2;
		int y = (screenSize.height - this.getHeight()) / 2;
		setLocation(x, y);

		InsertDataList();
		EditComponent();
		String[] Columns = { "ID", "First Name", "Last Name", "Contact", "Email", "Education", "Gender", "Address",
				"Birth Date", "Joining Date", "Salary", "Role" };

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdityaAutomative", "root",
					"root");

			// Execute the query to retrieve employee details
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String query = "SELECT * FROM Employee";
			ResultSet resultSet = statement.executeQuery(query);

			resultSet.last();
			int rowCount = resultSet.getRow();
			resultSet.beforeFirst();

			Object[][] data = new Object[rowCount][12];
			int i = 0;
			// Populate data into the table model
			while (resultSet.next()) {
				Object[] row = { data[i][0] = resultSet.getInt("Id"), data[i][1] = resultSet.getString("Firstname"),
						data[i][2] = resultSet.getString("Lastname"), data[i][3] = resultSet.getLong("Contact"),
						data[i][4] = resultSet.getString("Email"), data[i][5] = resultSet.getString("Education"),
						data[i][6] = resultSet.getString("Gender"), data[i][7] = resultSet.getString("Address"),
						data[i][8] = resultSet.getString("Birth_Date"),
						data[i][9] = resultSet.getString("Joining_Date"), data[i][10] = resultSet.getDouble("Salary"),
						data[i][11] = resultSet.getString("Emp_Role") };
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
		setUndecorated(true);
		setVisible(true);
		setDefaultCloseOperation(AdminLogin.EXIT_ON_CLOSE);
	}

	public void EditComponent() {
		L1 = new JLabel("Search By Id");
		L1.setBounds(50, 10, 150, 30);
		L1.setFont(new Font("Times new roman", Font.BOLD, 18));
		L1.setForeground(Color.white);
		add(L1);

		SearchText = new JComboBox<>(data.toArray(new String[0]));
		SearchText.setBounds(50, 50, 130, 25);
		SearchText.setFont(new Font("Times new roman", Font.BOLD, 15));
		add(SearchText);

		Update = new JButton("Update");
		Update.setBounds(50, 100, 130, 25);
		Update.setFont(new Font("Times new roman", Font.BOLD, 15));
		add(Update);
		Update.addActionListener(this);

		Remove = new JButton("Delete");
		Remove.setBounds(200, 100, 130, 25);
		Remove.setFont(new Font("Times new roman", Font.BOLD, 15));
		add(Remove);
		Remove.addActionListener(this);

		Back = new JButton("Back");
		Back.setBounds(350, 100, 130, 25);
		Back.setFont(new Font("Times new roman", Font.BOLD, 15));
		add(Back);
		Back.addActionListener(this);

		Print = new JButton("Print");
		Print.setBounds(500, 100, 130, 25);
		Print.setFont(new Font("Times new roman", Font.BOLD, 15));
		add(Print);
		Print.addActionListener(this);

	}

	public static void main(String[] args) {
		new ViewEmployee();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Update) {
			setVisible(false);
			new UpdateEmployee((String) SearchText.getSelectedItem());
		}

		else if (e.getSource() == Remove) {
			this.dispose();
			new RemoveEmployee((String) SearchText.getSelectedItem());
		} else if (e.getSource() == Back) {
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

	private void SearchEmployee() {
		String id = (String) SearchText.getSelectedItem();
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdityaAutomative", "root",
					"root");

			String query = "SELECT * FROM Employee WHERE id = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			model = (DefaultTableModel) T2.getModel();
			model.setRowCount(0); // Clear existing rows

			while (resultSet.next()) {
				Object[] rowData = new Object[model.getColumnCount()];
				for (int i = 1; i <= model.getColumnCount(); i++) {
					rowData[i - 1] = resultSet.getObject(i);
				}
				model.addRow(rowData);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
