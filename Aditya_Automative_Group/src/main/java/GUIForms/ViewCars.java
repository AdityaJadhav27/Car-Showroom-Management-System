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
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ViewCars extends JFrame implements ActionListener {
	JTable Table;
	JScrollPane Scroll;
	ConnectionClass Cclass = new ConnectionClass();
	JLabel L1;
	JComboBox SearchText;
	JButton Back, Print;
	ArrayList<String> CarIdData = new ArrayList<>();

	public ViewCars() {
		getContentPane().setBackground(new Color(28, 22, 120));
		setLayout(null);
		setBounds(20, 10, 1300, 600);

		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;
        setLocation(x, y);
        
		InsertDataList();
		EditComponent();

		String[] Columns = { "ID", "Car Name", "Company", "Engine", "Model", "Price", "Color", "Airbags", "Seats",
				"Mileage", "Safety Rating", "Contain_CNG", "Description", "Stored Date" };

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdityaAutomative", "root",
					"root");

			// Execute the query to retrieve employee details
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);

			String query = "SELECT * FROM Car_Details";
			ResultSet resultSet = statement.executeQuery(query);

			resultSet.last();
			int rowCount = resultSet.getRow();
			resultSet.beforeFirst();

			Object[][] data = new Object[rowCount][14];
			int i = 0;
			// Populate data into the table model
			while (resultSet.next()) {
				Object[] row = { data[i][0] = resultSet.getInt("Car_Id"), data[i][1] = resultSet.getString("Car_Name"),
						data[i][2] = resultSet.getString("Company"), data[i][3] = resultSet.getString("Car_Engine"),
						data[i][4] = resultSet.getString("Model"), data[i][5] = resultSet.getString("Cost"),
						data[i][6] = resultSet.getString("Color"), data[i][7] = resultSet.getString("Airbags"),
						data[i][8] = resultSet.getString("Seats"), data[i][9] = resultSet.getString("Mileage"),
						data[i][10] = resultSet.getDouble("Safty_Rating"),
						data[i][11] = resultSet.getString("Contain_CNG"),
						data[i][12] = resultSet.getString("Description"),
						data[i][13] = resultSet.getString("Stored_Date")

				};
				i++;

				Table = new JTable(data, Columns);
				Scroll = new JScrollPane(Table);
				Scroll.setBounds(0, 150, 1300, 600);
				getContentPane().add(Scroll, BorderLayout.CENTER);
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

	public static void main(String[] args) {
		new ViewCars();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Back) {
			this.dispose();
			new HomeScreen();
		}
	}

	public void EditComponent() {
		L1 = new JLabel("--- CAR  DETAILS ---");
		L1.setBounds(530, 10, 500, 50);
		L1.setFont(new Font("Times new roman", Font.BOLD, 25));
		L1.setForeground(Color.white);
		add(L1);

		SearchText = new JComboBox<>(CarIdData.toArray(new String[0]));
		SearchText.setBounds(50, 50, 130, 25);
		SearchText.setFont(new Font("Arial", Font.PLAIN, 15));
		add(SearchText);

		Back = new JButton("BACK");
		Back.setBounds(50, 100, 130, 25);
		Back.setFont(new Font("Times new roman", Font.BOLD, 15));
		add(Back);
		Back.addActionListener(this);

		Print = new JButton("PRINT");
		Print.setBounds(200, 100, 130, 25);
		Print.setFont(new Font("Times new roman", Font.BOLD, 15));
		add(Print);
		Print.addActionListener(this);

		

	}

	public void InsertDataList() {

		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdityaAutomative", "root",
					"root");
			Statement statement = connection.createStatement();

			// Execute query to retrieve data

			ResultSet resultSet = statement.executeQuery("SELECT Car_Id FROM Car_Details");

			while (resultSet.next()) {
				String value = resultSet.getString("Car_Id");
				CarIdData.add(value);
			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
