package GUIForms;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")

public class HomeScreen extends JFrame implements ActionListener {
	JPanel pan;
	JLabel L1, Imageicon, L3, L2;
	JButton AddCars, SellCars, OrderCarDetails, ViewCars, CustomerDetails, AdminDetails, AddEmployee, ViewEmployee,
			UpdateEmp, Close, AdminRegistration, PaymentBtn;
	ImageIcon image;
	// Border blackline = BorderFactory.createLineBorder(Color.black);
	// super.paintComponent(g);
	JSeparator separator_1;

	HomeScreen() {
		// pan.setBorder(blackline);

		setBounds(0, 0, 2000, 1220);
		// pan = getContentPane();
		pan = new JPanel();
		pan.setBackground(Color.black);
		setLayout(null);
		setUndecorated(true);

		L1 = new JLabel("       Aditya Automotive Group");
		L1.setBounds(500, 600, 380, 35);
		L1.setFont(new Font("Times new roman", Font.BOLD, 25));
		L1.setForeground(Color.black);
		L1.setBackground(Color.white);
		L1.setOpaque(true);
		add(L1);

		L2 = new JLabel("Since 2002");
		L2.setBounds(630,650 , 300, 35);
		L2.setFont(new Font("Times new roman", Font.BOLD, 25));
		L2.setForeground(Color.white);
//		L2.setBackground(Color.white);
//		L2.setOpaque(true);
		add(L2);
		// ------------------------------------------------------
		AddCars = new JButton("Add Car");
		AddCars.setBounds(100, 200, 150, 50);
		AddCars.setFont(new Font("Times new roman", Font.BOLD, 20));
		AddCars.setForeground(Color.white);
		AddCars.setBackground(Color.black);
		add(AddCars);
		AddCars.addActionListener(this);

		Close = new JButton("Close");
		Close.setBounds(1270, 0, 100, 30);
		Close.setFont(new Font("Times new roman", Font.BOLD, 17));
		Close.setForeground(Color.black);
		Close.setBackground(new Color(202, 244, 255));
		add(Close);
		Close.addActionListener(this);

		// ------------------------------------------------------
		SellCars = new JButton("Sell Car");
		SellCars.setBounds(300, 200, 150, 50);
		SellCars.setFont(new Font("Times new roman", Font.BOLD, 20));
		SellCars.setForeground(Color.white);
		SellCars.setBackground(Color.black);
		SellCars.addActionListener(this);
		add(SellCars);

		// ------------------------------------------------------
		OrderCarDetails = new JButton("Order Details");
		OrderCarDetails.setBounds(100, 260, 150, 50);
		OrderCarDetails.setFont(new Font("Times new roman", Font.BOLD, 20));
		OrderCarDetails.setForeground(Color.white);
		OrderCarDetails.setBackground(Color.black);
		OrderCarDetails.addActionListener(this);
		add(OrderCarDetails);

		// ------------------------------------------------------
		ViewCars = new JButton("View Car");
		ViewCars.setBounds(300, 260, 150, 50);
		ViewCars.setFont(new Font("Times new roman", Font.BOLD, 20));
		ViewCars.setForeground(Color.white);
		ViewCars.setBackground(Color.black);
		ViewCars.addActionListener(this);
		add(ViewCars);

		// ------------------------------------------------------

		PaymentBtn = new JButton("Payment Details");
		PaymentBtn.setBounds(100, 320, 350, 50);
		PaymentBtn.setFont(new Font("Serif", Font.PLAIN, 20));
		PaymentBtn.setForeground(Color.white);
		PaymentBtn.setBackground(Color.black);
		PaymentBtn.addActionListener(this);
		add(PaymentBtn);

		CustomerDetails = new JButton("Customer Details");
		CustomerDetails.setBounds(100, 380, 350, 50);
		CustomerDetails.setFont(new Font("Serif", Font.PLAIN, 20));
		CustomerDetails.setForeground(Color.white);
		CustomerDetails.setBackground(Color.black);
		CustomerDetails.addActionListener(this);
		add(CustomerDetails);

		// ------------------------------------------------------
		AddEmployee = new JButton("Add Employee");
		AddEmployee.setBounds(600, 200, 200, 50);
		AddEmployee.setFont(new Font("Times new roman", Font.BOLD, 20));
		AddEmployee.setForeground(Color.white);
		AddEmployee.setBackground(Color.black);
		AddEmployee.addActionListener(this);
		add(AddEmployee);

		// ------------------------------------------------------
		ViewEmployee = new JButton("View Employee");
		ViewEmployee.setBounds(600, 260, 200, 50);
		ViewEmployee.setFont(new Font("Times new roman", Font.BOLD, 20));
		ViewEmployee.setForeground(Color.white);
		ViewEmployee.setBackground(Color.black);
		ViewEmployee.addActionListener(this);
		add(ViewEmployee);

		// -------------------------------------------------------

//		AdminDetails = new JButton("Admin Details");
//		AdminDetails.setBounds(600, 100, 200, 50);
//		AdminDetails.setFont(new Font("Times new roman", Font.BOLD, 20));
//		AdminDetails.setForeground(Color.white);
//		AdminDetails.setBackground(Color.black);
//		AdminDetails.addActionListener(this);
//		add(AdminDetails);

		AdminRegistration = new JButton("Admin Registration");
		AdminRegistration.setBounds(1000, 200, 200, 50);
		AdminRegistration.setFont(new Font("Times new roman", Font.BOLD, 20));
		AdminRegistration.setForeground(Color.white);
		AdminRegistration.setBackground(Color.black);
		AdminRegistration.addActionListener(this);
		add(AdminRegistration);
		// ------------------------------------------------------

		image = new ImageIcon(ClassLoader.getSystemResource("Pictures/Dashboard_Image.jpg"));
		Image img1 = image.getImage().getScaledInstance(1366, 768, Image.SCALE_DEFAULT);
		ImageIcon ic1 = new ImageIcon(img1);

		Imageicon = new JLabel(ic1);
		Imageicon.setBounds(0, 0, 1366, 768);
		add(Imageicon);

		setVisible(true);
		setDefaultCloseOperation(AdminLogin.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new HomeScreen();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == AddCars) {
			new AddCarsDetails();

		} else if (e.getSource() == SellCars) {

			new SellCar();
		} else if (e.getSource() == OrderCarDetails) {
			
			new OrderDetails();
		} else if (e.getSource() == ViewCars) {

			new ViewCars();
		} else if (e.getSource() == CustomerDetails) {
			new ViewCustomer();
		} else if (e.getSource() == AdminDetails) {

		} else if (e.getSource() == AddEmployee) {
			new AddEmployee();
		} else if (e.getSource() == ViewEmployee) {
			new ViewEmployee();

		} else if (e.getSource() == AdminRegistration) {
			new AdminRegistration();
		}
		else if(e.getSource()==PaymentBtn)
		{
			new Payment();
		}
		
		if (e.getSource() == Close) {
			System.exit(0);
		}
	}
}
