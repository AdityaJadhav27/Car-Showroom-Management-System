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
import java.sql.SQLException;
import java.util.Random;
import java.util.Vector;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;

import com.toedter.calendar.*;
import GUIForms.AddEmployee;

public class AddCarsDetails extends JFrame implements ActionListener {
	Container C;
	JLabel L1, LblCarId, L2, L3, L4, L5, L6, L7, L8, L9, L10, L11, L12, L13, L14, L15;
	JTextField CarId, CarCompany, CarName, CarModel, CarColor, CarPrice, TotalAirBags, TotalSeats, TotalPrice,
			CarMilage;
	JComboBox CarEngine, ContainCngOrNot, CarRating;
	JTextArea CarDescription;
	JButton SubmitCarDetails, ClearCarDetails;
	Random random = new Random();

	// Generate a random 6-digit number 
	int randomNumber = random.nextInt(900000) + 100000;
	Border blackline = BorderFactory.createLineBorder(Color.black);
	JDateChooser date;
	Connection connection;
	PreparedStatement preparedStatement;
	Vector v;

	AddCarsDetails() {
		setTitle("Enter Car Details");
		setSize(900, 800);
		setUndecorated(true);
		C = getContentPane();
		C.setBackground(new Color(232, 255, 206));
		setLayout(null);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;
        setLocation(x, y);
        
		L1 = new JLabel("        Enter car details");
		L1.setBounds(280, 30, 250, 47);
		L1.setFont(new Font("Times new roman", Font.BOLD, 23));
		L1.setForeground(Color.white);
		L1.setBackground(new Color(6, 17, 60));
		L1.setOpaque(true);
		C.add(L1);

		L2 = new JLabel("Car Id");
		L2.setBounds(100, 100, 150, 45);
		L2.setFont(new Font("Arial", Font.PLAIN, 17));
		C.add(L2);

		LblCarId = new JLabel("" + randomNumber);
		LblCarId.setBounds(100, 145, 150, 30);
		C.add(LblCarId);
		// ----------------------------------------------------------------------

		L3 = new JLabel("Car Name");
		L3.setBounds(100, 190, 150, 45);
		L3.setFont(new Font("Arial", Font.PLAIN, 17));
		C.add(L3);

		CarName = new JTextField();
		CarName.setBounds(100, 235, 150, 30);
		C.add(CarName);
		// ----------------------------------------------------------------------

		L4 = new JLabel("Car Company");
		L4.setBounds(100, 280, 150, 45);
		L4.setFont(new Font("Arial", Font.PLAIN, 17));
		C.add(L4);

		CarCompany = new JTextField();
		CarCompany.setBounds(100, 325, 150, 30);
		C.add(CarCompany);
		// ----------------------------------------------------------------------

		L5 = new JLabel("Car Engine");
		L5.setBounds(100, 370, 150, 45);
		L5.setFont(new Font("Arial", Font.PLAIN, 17));
		C.add(L5);
		
		String Engines[]={"Revotron Engines","mFalcon Engines ","mHawk Engines","Dual VVT-i Engines","D-4D Engines"};
	
		
		 CarEngine=new JComboBox(Engines);
		CarEngine.setBounds(100, 415, 150, 30);
		C.add(CarEngine);

		// ----------------------------------------------------------------------
		L6 = new JLabel("Car Model");
		L6.setBounds(100, 460, 150, 45);
		L6.setFont(new Font("Arial", Font.PLAIN, 17));
		C.add(L6);

		CarModel = new JTextField();
		CarModel.setBounds(100, 500, 150, 30);
		C.add(CarModel);
		// ----------------------------------------------------------------------

		L7 = new JLabel("Car Price");
		L7.setBounds(100, 550, 150, 45);
		L7.setFont(new Font("Arial", Font.PLAIN, 17));
		C.add(L7);

		CarPrice = new JTextField();
		CarPrice.setBounds(100, 590, 150, 30);
		C.add(CarPrice);

		// ----------------------------------------------------------------------
		// ----------------------------------------------------------------------

		L8 = new JLabel("Car Color");
		L8.setBounds(300, 100, 150, 45);
		L8.setFont(new Font("Arial", Font.PLAIN, 17));
		C.add(L8);

		CarColor = new JTextField();
		CarColor.setBounds(300, 145, 150, 30);
		C.add(CarColor);
		// ----------------------------------------------------------------------

		L9 = new JLabel("Total Airbags");
		L9.setBounds(300, 190, 150, 45);
		L9.setFont(new Font("Arial", Font.PLAIN, 17));
		C.add(L9);

		TotalAirBags = new JTextField();
		TotalAirBags.setBounds(300, 235, 150, 30);
		C.add(TotalAirBags);
		// ----------------------------------------------------------------------

		L10 = new JLabel("Total Seats");
		L10.setBounds(300, 280, 150, 45);
		L10.setFont(new Font("Arial", Font.PLAIN, 17));
		C.add(L10);

		TotalSeats = new JTextField();
		TotalSeats.setBounds(300, 325, 150, 30);
		C.add(TotalSeats);
		// ----------------------------------------------------------------------

		L11 = new JLabel("Total Mileage");
		L11.setBounds(300, 370, 150, 45);
		L11.setFont(new Font("Arial", Font.PLAIN, 17));
		C.add(L11);

		CarMilage = new JTextField();
		CarMilage.setBounds(300, 415, 150, 30);
		C.add(CarMilage);

		// ----------------------------------------------------------------------
		L12 = new JLabel("Car Rating");
		L12.setBounds(300, 460, 150, 45);
		L12.setFont(new Font("Arial", Font.PLAIN, 17));
		C.add(L12);
		String CRating[] = { "1", "2", "3", "4", "5" };
		CarRating = new JComboBox(CRating);
		CarRating.setBounds(300, 500, 150, 30);
		C.add(CarRating);
		// ----------------------------------------------------------------------

		L13 = new JLabel("Contain CNG?");
		L13.setBounds(300, 550, 150, 45);
		L13.setFont(new Font("Arial", Font.PLAIN, 17));
		C.add(L13);

		String ContainCng[] = { "Yes", "No" };
		ContainCngOrNot = new JComboBox(ContainCng);
		ContainCngOrNot.setBounds(300, 590, 150, 30);
		C.add(ContainCngOrNot);
		// ----------------------------------------------------------------------

		L14 = new JLabel("Car Description");
		L14.setBounds(500, 100, 150, 30);
		L14.setFont(new Font("Arial", Font.PLAIN, 17));
		C.add(L14);

		CarDescription = new JTextArea();
		CarDescription.setBounds(500, 145, 300, 200);
		// CarDescription.setVerticalScrollBarPolicy(JTextArea.VERTICAL_SCROLLBAR_ALWAYS);
		CarDescription.setBackground(Color.white);
		CarDescription.setLineWrap(true);
		CarDescription.setWrapStyleWord(true);
		CarDescription.setBorder(blackline);
		C.add(CarDescription);

		L15 = new JLabel("Date");
		L15.setBounds(500, 400, 130, 30);
		L15.setFont(new Font("Arial", Font.PLAIN, 17));
		C.add(L15);

		date = new com.toedter.calendar.JDateChooser();
		date.setBounds(500, 430, 150, 30);
		date.setFont(new Font("Arial", Font.PLAIN, 17));
		C.add(date);

		SubmitCarDetails = new JButton("Add To Inventry");
		SubmitCarDetails.setBounds(500, 520, 180, 50);
		SubmitCarDetails.setFont(new Font("Times new roman", Font.BOLD, 19));
		SubmitCarDetails.setBackground(new Color(6, 17, 60));
		SubmitCarDetails.setForeground(Color.white);
		C.add(SubmitCarDetails);
		SubmitCarDetails.addActionListener(this);

		ClearCarDetails = new JButton("Cancle Process");
		ClearCarDetails.setBounds(500, 580, 180, 50);
 		ClearCarDetails.setFont(new Font("Times new roman", Font.BOLD, 19));
		ClearCarDetails.setBackground(new Color(6, 17, 60));
		ClearCarDetails.setForeground(Color.white);
		C.add(ClearCarDetails);
		ClearCarDetails.addActionListener(this);

		setVisible(true);
		setDefaultCloseOperation(AdminLogin.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == SubmitCarDetails) {
			CreateConnection();
			AddCarsToDB();
		}
		if (e.getSource() == ClearCarDetails) {
			this.dispose();
			
			//new HomeScreen();
		}
	}

	public static void main(String[] args) {
		AddCarsDetails Addcardetails = new AddCarsDetails();
	}

	private void CreateConnection() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdityaAutomative", "root", "root");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, this, "Failed to connect to database", 0);
			System.exit(1);
		}
	}

	public void AddCarsToDB() {

		int CarId = Integer.parseInt(LblCarId.getText());
		String Cname = CarName.getText();
		String Ccompany = CarCompany.getText();
		String Cmodel = CarModel.getText();
		String Ccolor = CarColor.getText();
		double Cprice = Double.parseDouble(CarPrice.getText());
		int Cairbags = Integer.parseInt(TotalAirBags.getText());
		int Cseats = Integer.parseInt(TotalSeats.getText());
		int Cmileage = Integer.parseInt(CarMilage.getText());
		String Cengine = (String) CarEngine.getSelectedItem();
		String CngOrNot = (String) ContainCngOrNot.getSelectedItem();	
		String Crating = (String) CarRating.getSelectedItem();
		String Cdescription = CarDescription.getText();
		String Cstoreddate = ((JTextField) date.getDateEditor().getUiComponent()).getText();

		Integer intValue = Integer.parseInt(Crating);
		try {

			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO Car_Details (Car_Id,Car_Name,Company,Car_Engine,Model,Cost,Color,Airbags,Seats,Mileage,Safty_Rating,Contain_CNG,Description,Stored_Date) VALUES (?, ?, ?,?,?,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setInt(1, CarId);
			preparedStatement.setString(2, Cname);
			preparedStatement.setString(3, Ccompany);
			preparedStatement.setString(4, Cengine);
			preparedStatement.setString(5, Cmodel);
			preparedStatement.setDouble(6, Cprice);
			preparedStatement.setString(7, Ccolor);
			preparedStatement.setInt(8, Cairbags);
			// preparedStatement.setString(9, Cstoreddate);
			preparedStatement.setInt(9, Cseats);
			preparedStatement.setDouble(10, Cmileage);
			preparedStatement.setInt(11, intValue);
			preparedStatement.setString(12, CngOrNot);
			preparedStatement.setString(13, Cdescription);
			preparedStatement.setString(14, Cstoreddate);

			preparedStatement.executeUpdate();

			JOptionPane.showMessageDialog(this, "Car data inserted successfully");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Failed to insert Car data");
			// System.out.println(e);"

		}

	}

}
