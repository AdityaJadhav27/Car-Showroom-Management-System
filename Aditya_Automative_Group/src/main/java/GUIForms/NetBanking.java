package GUIForms;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NetBanking extends JFrame implements ActionListener {
	String PaymentModeType, CId;
	String Oid;
	JLabel paymentIdLabel, paidAmountLabel, orderIdLabel, customerIdLabel, ifscNumberLabel, accountNumberLabel,
			paidToLabel;
	JPanel panel;
	JTextField paymentIdField, paidToField, accountNumberField, ifscNumberField, customerIdField, orderIdField,
			paidAmountField;

	NetBanking(String PaymentModeType, String CId, String Oid) {
		this.PaymentModeType = PaymentModeType;
		this.CId = CId;
		this.Oid = Oid;

		setTitle("Update Employee Details");
		setSize(600, 500);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width - this.getWidth()) / 2;
		int y = (screenSize.height - this.getHeight()) / 2;
		setLocation(x, y);


		setVisible(true);
		setDefaultCloseOperation(NetBanking.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new NetBanking("", "", "");
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

	private void insertData() {
		String url = "jdbc:mysql://localhost:3306/your_database_name";
		String username = "your_username";
		String password = "your_password";

		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			String query = "INSERT INTO Net_Banking (Payment_Id, Paid_To, Account_Number, Ifsc_Number, Customer_Id, Order_Id, Paid_Amount) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, Integer.parseInt(paymentIdField.getText()));
			statement.setString(2, paidToField.getText());
			statement.setBigDecimal(3, new BigDecimal(accountNumberField.getText()));
			statement.setString(4, ifscNumberField.getText());
			statement.setInt(5, Integer.parseInt(customerIdField.getText()));
			statement.setInt(6, Integer.parseInt(orderIdField.getText()));
			statement.setBigDecimal(7, new BigDecimal(paidAmountField.getText()));
			int rowsInserted = statement.executeUpdate();
			if (rowsInserted > 0) {
				JOptionPane.showMessageDialog(this, "Data inserted successfully");
				clearFields();
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Error: Please enter valid numbers");
		}
	}

	private void clearFields() {
		paymentIdField.setText("");
		paidToField.setText("");
		accountNumberField.setText("");
		ifscNumberField.setText("");
		customerIdField.setText("");
		orderIdField.setText("");
		paidAmountField.setText("");
	}
}
