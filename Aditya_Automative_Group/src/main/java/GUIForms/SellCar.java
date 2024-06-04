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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class SellCar extends JFrame implements ActionListener {
	JLabel L1, L2, L3, L4, L5, L6, L7, L8, Lbl, CustomerId;

	JLabel L9, L10, L11, L12, L13, L14, L15, L16, L17, L18, L19, L20, L21, L22;
	JLabel LCarname, LCompany, LModel, LColor, LEngine, LMileage, LDescription, LStoreddate, LRating, LCngOrNot, LCost,
			LAirbags, LSeats;
	JComboBox CarIdNo, PaymentMode;
	JTextField Fname, Lname, ContactNo, AadharNo;
	JTextArea Address;
	JPanel P;
	JButton Submit, ViewDetails, SellTheCar, Cancle;
	Border blackline = BorderFactory.createLineBorder(Color.black);
	Random random2 = new Random();
	Random OrderIdGenrator = new Random();
	int CustomerIdNo = random2.nextInt(900000) + 100000;
	ArrayList<String> CarIdData = new ArrayList<>();
	String DisplayCarData;
	Connection connection;
	String[] PM = { "Net Banking","Credit Card","Cash" };
	String FormattedDate;
    int OrderId =OrderIdGenrator.nextInt(900000) + 100000;;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	LocalDateTime CDT = LocalDateTime.now();

	public SellCar() {
		setTitle("Enter Car Details");
		setBounds(50, 10, 1300, 750);
		P = new JPanel();
		getContentPane().setBackground(Color.white);
		setLayout(null);
		setUndecorated(true);

		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - this.getWidth()) / 2;
        int y = (screenSize.height - this.getHeight()) / 2;
        setLocation(x, y);
        
		FormattedDate = dtf.format(CDT);

		CreateConnection();
		InsertDataList();
		CreateComponent();

		setVisible(true);
		setDefaultCloseOperation(AddEmployee.EXIT_ON_CLOSE);
	}

	public void CreateComponent() {
		L1 = new JLabel("Customer Id");
		L1.setBounds(100, 100, 100, 30);
		L1.setFont(new Font("Serif", Font.PLAIN, 20));
		L1.setForeground(Color.black);
		add(L1);

		CustomerId = new JLabel("" + CustomerIdNo);
		CustomerId.setBounds(250, 100, 100, 30);
		CustomerId.setFont(new Font("Serif", Font.PLAIN, 20));
		CustomerId.setForeground(Color.white);
		CustomerId.setBackground(new Color(6, 17, 60));
		CustomerId.setOpaque(true);
		add(CustomerId);
		// ---------------------------------------------------
		L3 = new JLabel("Firstname");
		L3.setBounds(100, 160, 150, 30);
		L3.setFont(new Font("Serif", Font.PLAIN, 20));
		L3.setForeground(Color.black);
		add(L3);

		Fname = new JTextField();
		Fname.setBounds(250, 160, 180, 30);
		add(Fname);

		// ---------------------------------------------------
		L4 = new JLabel("Lastname");
		L4.setBounds(100, 220, 150, 30);
		L4.setFont(new Font("Serif", Font.PLAIN, 20));
		L4.setForeground(Color.black);
		add(L4);

		Lname = new JTextField();
		Lname.setBounds(250, 220, 180, 30);
		add(Lname);

		// ---------------------------------------------------
		L5 = new JLabel("Contact");
		L5.setBounds(100, 280, 150, 30);
		L5.setFont(new Font("Serif", Font.PLAIN, 20));
		L5.setForeground(Color.black);
		add(L5);

		ContactNo = new JTextField();
		ContactNo.setBounds(250, 280, 180, 30);
		add(ContactNo);

		// ---------------------------------------------------
		L6 = new JLabel("Aadhar No");
		L6.setBounds(100, 340, 150, 30);
		L6.setFont(new Font("Serif", Font.PLAIN, 20));
		L6.setForeground(Color.black);
		add(L6);

		AadharNo = new JTextField();
		AadharNo.setBounds(250, 340, 180, 30);
		add(AadharNo);
		// ---------------------------------------------------
		Lbl = new JLabel("Payment Mode");
		Lbl.setBounds(100, 400, 150, 30);
		Lbl.setFont(new Font("Serif", Font.PLAIN, 20));
		Lbl.setForeground(Color.black);
		add(Lbl);

		PaymentMode = new JComboBox(PM);
		PaymentMode.setBounds(250, 400, 180, 30);
		add(PaymentMode);
		// ---------------------------------------------------
		L7 = new JLabel("Address");
		L7.setBounds(100, 460, 150, 30);
		L7.setFont(new Font("Serif", Font.PLAIN, 20));
		L7.setForeground(Color.black);
		add(L7);

		Address = new JTextArea();
		Address.setBounds(250, 460, 180, 90);
		add(Address);
		Address.setLineWrap(true);
		Address.setWrapStyleWord(true);
		Address.setBorder(blackline);

		// ----------------------------------------------------------------------------------------------------
		// ----------------------------------------------------------------------------------------------------

		CarIdNo = new JComboBox<>(CarIdData.toArray(new String[0]));
		CarIdNo.setBounds(500, 100, 150, 30);
		CarIdNo.setFont(new Font("Serif", Font.PLAIN, 20));
		CarIdNo.setForeground(Color.black);
		add(CarIdNo);

		ViewDetails = new JButton("View Details");
		ViewDetails.setBounds(650, 100, 130, 30);
		ViewDetails.setFont(new Font("Arial", Font.PLAIN, 17));
		add(ViewDetails);
		ViewDetails.addActionListener(this);

		L9 = new JLabel("Car Name :");
		L9.setBounds(500, 160, 150, 30);
		L9.setFont(new Font("Serif", Font.PLAIN, 20));
		L9.setForeground(Color.black);
		add(L9);

		LCarname = new JLabel();
		LCarname.setBounds(650, 160, 150, 30);
		LCarname.setFont(new Font("Serif", Font.PLAIN, 20));
		LCarname.setForeground(Color.blue);
		add(LCarname);
		// ---------------------------------------------------
		L10 = new JLabel("Company :");
		L10.setBounds(500, 220, 150, 30);
		L10.setFont(new Font("Serif", Font.PLAIN, 20));
		L10.setForeground(Color.black);
		add(L10);

		LCompany = new JLabel();
		LCompany.setBounds(650, 220, 150, 30);
		LCompany.setFont(new Font("Serif", Font.PLAIN, 20));
		LCompany.setForeground(Color.blue);
		LCompany.setBackground(Color.pink);
		add(LCompany);
		// ---------------------------------------------------
		L11 = new JLabel("Engine :");
		L11.setBounds(500, 280, 150, 30);
		L11.setFont(new Font("Serif", Font.PLAIN, 20));
		L11.setForeground(Color.black);
		add(L11);

		LEngine = new JLabel();
		LEngine.setBounds(650, 280, 150, 30);
		LEngine.setFont(new Font("Serif", Font.PLAIN, 20));
		LEngine.setForeground(Color.blue);
		LEngine.setBackground(Color.pink);
		add(LEngine);
		// ---------------------------------------------------
		L12 = new JLabel("Model :");
		L12.setBounds(500, 340, 150, 30);
		L12.setFont(new Font("Serif", Font.PLAIN, 20));
		L12.setForeground(Color.black);
		add(L12);

		LModel = new JLabel();
		LModel.setBounds(650, 340, 150, 30);
		LModel.setFont(new Font("Serif", Font.PLAIN, 20));
		LModel.setForeground(Color.blue);
		LModel.setBackground(Color.pink);
		add(LModel);
		// ---------------------------------------------------
		L13 = new JLabel("Cost :");
		L13.setBounds(500, 400, 150, 30);
		L13.setFont(new Font("Serif", Font.PLAIN, 20));
		L13.setForeground(Color.black);
		add(L13);

		LCost = new JLabel();
		LCost.setBounds(650, 400, 150, 30);
		LCost.setFont(new Font("Serif", Font.PLAIN, 20));
		LCost.setForeground(Color.blue);
		LCost.setBackground(Color.pink);
		add(LCost);
		// ---------------------------------------------------
		L14 = new JLabel("Color :");
		L14.setBounds(500, 460, 150, 30);
		L14.setFont(new Font("Serif", Font.PLAIN, 20));
		L14.setForeground(Color.black);
		add(L14);

		LColor = new JLabel();
		LColor.setBounds(650, 460, 150, 30);
		LColor.setFont(new Font("Serif", Font.PLAIN, 20));
		LColor.setForeground(Color.blue);
		LColor.setBackground(Color.pink);
		add(LColor);
		// ---------------------------------------------------
		L15 = new JLabel("Airbags :");
		L15.setBounds(850, 280, 150, 30);
		L15.setFont(new Font("Serif", Font.PLAIN, 20));
		L15.setForeground(Color.black);
		add(L15);

		LAirbags = new JLabel();
		LAirbags.setBounds(1000, 280, 150, 30);
		LAirbags.setFont(new Font("Serif", Font.PLAIN, 20));
		LAirbags.setForeground(Color.blue);
		LAirbags.setBackground(Color.pink);
		add(LAirbags);
		// ---------------------------------------------------
		L16 = new JLabel("Seats :");
		L16.setBounds(500, 520, 150, 30);
		L16.setFont(new Font("Serif", Font.PLAIN, 20));
		L16.setForeground(Color.black);
		add(L16);

		LSeats = new JLabel();
		LSeats.setBounds(650, 520, 150, 30);
		LSeats.setFont(new Font("Serif", Font.PLAIN, 20));
		LSeats.setForeground(Color.blue);
		LSeats.setBackground(Color.pink);
		add(LSeats);
		// ---------------------------------------------------
		L17 = new JLabel("Mileage :");
		L17.setBounds(500, 580, 150, 30);
		L17.setFont(new Font("Serif", Font.PLAIN, 20));
		L17.setForeground(Color.black);
		add(L17);

		LMileage = new JLabel();
		LMileage.setBounds(650, 580, 150, 30);
		LMileage.setFont(new Font("Serif", Font.PLAIN, 20));
		LMileage.setForeground(Color.blue);
		LMileage.setBackground(Color.pink);
		add(LMileage);
		// ---------------------------------------------------
		L18 = new JLabel("Safty Rating :");
		L18.setBounds(500, 640, 150, 30);
		L18.setFont(new Font("Serif", Font.PLAIN, 20));
		L18.setForeground(Color.black);
		add(L18);

		LRating = new JLabel();
		LRating.setBounds(650, 640, 150, 30);
		LRating.setFont(new Font("Serif", Font.PLAIN, 20));
		LRating.setForeground(Color.blue);
		LRating.setBackground(Color.pink);
		add(LRating);
		// ---------------------------------------------------
		L19 = new JLabel("Contain CNG :");
		L19.setBounds(850, 160, 150, 30);
		L19.setFont(new Font("Serif", Font.PLAIN, 20));
		L19.setForeground(Color.black);
		add(L19);

		LCngOrNot = new JLabel();
		LCngOrNot.setBounds(1000, 160, 150, 30);
		LCngOrNot.setFont(new Font("Serif", Font.PLAIN, 20));
		LCngOrNot.setForeground(Color.blue);
		LCngOrNot.setBackground(Color.pink);
		add(LCngOrNot);
		// ---------------------------------------------------
		L20 = new JLabel("Description  :");
		L20.setBounds(850, 220, 150, 30);
		L20.setFont(new Font("Serif", Font.PLAIN, 20));
		L20.setForeground(Color.black);
		add(L20);

		LDescription = new JLabel();
		LDescription.setBounds(1000, 220, 150, 30);
		LDescription.setFont(new Font("Serif", Font.PLAIN, 20));
		LDescription.setForeground(Color.blue);
		LDescription.setBackground(Color.pink);
		add(LDescription);

		SellTheCar = new JButton("Sell and Payment");
		SellTheCar.setBounds(850, 340, 230, 47);
		SellTheCar.setFont(new Font("Times new roman", Font.BOLD, 19));
		SellTheCar.setForeground(Color.white);
		SellTheCar.setBackground(new Color(6, 17, 60));
		add(SellTheCar);
		SellTheCar.addActionListener(this);

		Cancle = new JButton("Cancle The Process");
		Cancle.setBounds(850, 400, 230, 47);
		Cancle.setFont(new Font("Times new roman", Font.BOLD, 19));
		Cancle.setForeground(Color.white);
		Cancle.setBackground(new Color(6, 17, 60));
		add(Cancle);
		Cancle.addActionListener(this);
		// ---------------------------------------------------
		
		   
	           
	}

//Insert car id in JComboBox
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

//	LCarname, LCompany, LModel, LColor, LEngine, LMileage, LDescription, LStoreddate, LRating, LCngOrNot, LCost,
//	LAirbags, LSeats;
	public void DisplayData() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdityaAutomative", "root",
					"root");
			// Statement statement = connection.createStatement();
			// Execute query to retrieve data

			DisplayCarData = "Select Car_Name,Company, Car_Engine, Model, Cost, Color, Airbags, Seats,Mileage,Safty_Rating,Contain_CNG, Description from Car_Details where Car_Id='"
					+ CarIdNo.getSelectedItem() + "'";

			PreparedStatement statement = connection.prepareStatement(DisplayCarData);

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				// String value = resultSet.getString("Id");
				// data.add(value);
				LCarname.setText(resultSet.getString("Car_Name"));

				LCompany.setText(resultSet.getString("Company"));
				LEngine.setText(resultSet.getString("Car_Engine"));
				LModel.setText(resultSet.getString("Model"));

				LColor.setText(resultSet.getString("Color"));

				LMileage.setText(resultSet.getString("Mileage"));
				LDescription.setText(resultSet.getString("Description"));
				LRating.setText(resultSet.getString("Safty_Rating"));

				LCngOrNot.setText(resultSet.getString("Contain_CNG"));
				LCost.setText(resultSet.getString("Cost"));
				LAirbags.setText(resultSet.getString("Airbags"));

				LSeats.setText(resultSet.getString("Seats"));

				LCarname.setBackground(Color.pink);
				LCarname.setOpaque(true);
				LCompany.setBackground(Color.pink);
				LCompany.setOpaque(true);
				LEngine.setBackground(Color.pink);
				LEngine.setOpaque(true);
				LModel.setBackground(Color.pink);
				LModel.setOpaque(true);
				LColor.setBackground(Color.pink);
				LColor.setOpaque(true);
				LMileage.setBackground(Color.pink);
				LMileage.setOpaque(true);
				LDescription.setBackground(Color.pink);
				LDescription.setOpaque(true);
				LRating.setBackground(Color.pink);
				LRating.setOpaque(true);
				LCngOrNot.setBackground(Color.pink);
				LCngOrNot.setOpaque(true);
				LCost.setBackground(Color.pink);
				LCost.setOpaque(true);
				LAirbags.setBackground(Color.pink);
				LAirbags.setOpaque(true);
				LSeats.setBackground(Color.pink);
				LSeats.setOpaque(true);

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
	}

	public void SellTheCarToCustomer() {
		try {
			int CustomerIdNumber = Integer.parseInt(CustomerId.getText());
			String cFName = Fname.getText();
			String cLName = Lname.getText();
			Long cContactNo = Long.parseLong(ContactNo.getText());
			Long cAadharNo = Long.parseLong(AadharNo.getText());
			String cPaymentMode = (String) PaymentMode.getSelectedItem();
			String cAddress = Address.getText();
			int BookCarId = Integer.parseInt((String) CarIdNo.getSelectedItem());
			String BookDate = FormattedDate;

			PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO Customer_Details (Customer_Id,Firstname,Lastname,Contact,Aadhar_No,Payment_Mode,Address,Booking_Date) VALUES (?, ?, ?,?,?,?,?,?)");
			preparedStatement.setInt(1, CustomerIdNumber);
			preparedStatement.setString(2, cFName);
			preparedStatement.setString(3, cLName);
			preparedStatement.setLong(4, cContactNo);
			preparedStatement.setLong(5, cAadharNo);
			preparedStatement.setString(6, cPaymentMode);
			preparedStatement.setString(7, cAddress);
			preparedStatement.setString(8, BookDate);
			

			preparedStatement.executeUpdate();

			//JOptionPane.showMessageDialog(this, "Successfully sell the car");
		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(this, "Failed to sell car data");
			
			//System.out.println(e);
		}
       //_________________________________________________________________________________________________________
	    // Storing data in the sell car details
		
		try {
			int CustomerIdNumber = Integer.parseInt(CustomerId.getText());
			
			String Cname = LCarname.getText();
			String Ccompany = LCompany.getText();
			String Cmodel = LModel.getText();
			String Cengine = LEngine.getText();
			double Cprice = Double.parseDouble(LCost.getText());
			String Cdescription = LDescription.getText();
			String Cselldate = FormattedDate;
			int CorderId = OrderId;
			
			PreparedStatement preparedStatement2 = connection.prepareStatement(
					"INSERT INTO Order_Details (Customer_Id,Car_Name,Car_Company,Car_Engine,Car_Model,Cost,Description,Sell_Date,Order_Id) VALUES (?, ?, ?,?,?,?,?,?,?)");
			preparedStatement2.setInt(1, CustomerIdNumber);
			
			preparedStatement2.setString(2, Cname);
			preparedStatement2.setString(3, Ccompany);	
			preparedStatement2.setString(4, Cengine);
			preparedStatement2.setString(5, Cmodel);
			preparedStatement2.setDouble(6, Cprice);
			preparedStatement2.setString(7, Cdescription);
			preparedStatement2.setString(8, Cselldate);
			preparedStatement2.setInt(9, CorderId);
			
			preparedStatement2.executeUpdate();	
			
		}
		
		catch(Exception ae)
		{
			JOptionPane.showMessageDialog(this, "Failed to insert sell car data");
			 System.out.println(ae);
		}
		
		try {
			

			String DelQuery="Delete from Car_Details where Car_Id=?";
			
			int BookCarId = Integer.parseInt((String) CarIdNo.getSelectedItem());
			
			PreparedStatement statement = connection.prepareStatement(DelQuery);
			statement.setInt(1, BookCarId);
			int rowsAffected = statement.executeUpdate();
			    
			    if (rowsAffected > 0) {
			      //  JOptionPane.showMessageDialog(null, "Car removed");
			    } else {
			      //  JOptionPane.showMessageDialog(null, "Car with given ID not found");
			    }
			
		}
		catch(Exception aea)
		{
			JOptionPane.showMessageDialog(this, "Failed to delete sell car data");
			 System.out.println(aea);

		}
	}

	private void CreateConnection() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdityaAutomative", "root", "root");
			//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/AdityaAutomative", "root", "root");

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, this, "Failed to connect to database", 0);
			System.exit(1);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String OrderIdNumber =String.valueOf(OrderId);
		if (e.getSource() == ViewDetails) {
			DisplayData();
		}
		if(e.getSource()==SellTheCar)
		{
			SellTheCarToCustomer();
			if(PaymentMode.getSelectedItem()=="Credit Card") {
				this.dispose();
		    new Credit_Card((String)PaymentMode.getSelectedItem(),CustomerId.getText(),OrderIdNumber);
			}
			if(PaymentMode.getSelectedItem()=="Cash")
			{
				this.dispose();
				new CashPayment(LCost.getText(),CustomerId.getText(),OrderIdNumber);
			}
		}
		if(e.getSource()==Cancle){
			this.dispose();
			
		}
	}

	public static void main(String[] args) {
		new SellCar();
	}
}
