package com.tech3s.ffm.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.tech3s.ffm.model.User;
import com.tech3s.ffm.persistence.SecurityDao;

public class LoginWindow {

	private JFrame frmQunLTi;
	private JTextField txtEmail;	
	private JPasswordField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
					window.frmQunLTi.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmQunLTi = new JFrame();
		frmQunLTi.setTitle("Qu\u1EA3n l\u00FD t\u00E0i ch\u00EDnh gia \u0111\u00ECnh");
		frmQunLTi.setBounds(100, 100, 330, 200);
		frmQunLTi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmQunLTi.getContentPane().setLayout(null);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 32, 84, 14);
		frmQunLTi.getContentPane().add(lblEmail);
		
		JLabel lblMtKhu = new JLabel("M\u1EADt kh\u1EA9u");
		lblMtKhu.setBounds(10, 77, 84, 14);
		frmQunLTi.getContentPane().add(lblMtKhu);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(115, 29, 174, 20);
		frmQunLTi.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		JButton btnngNhp = new JButton("\u0110\u0103ng nh\u1EADp");
		btnngNhp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String email = txtEmail.getText();
				String password = txtPassword.getText();
				
				SecurityDao loginDao = new SecurityDao();				
				User user = loginDao.authenticate(email, password);
				
				if (user.getId() > 0) {
					MainWindow mainWindow = new MainWindow(user);
					mainWindow.getFrame().setVisible(true);
					frmQunLTi.setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(frmQunLTi, "Email or password is invalid, please try again!", "Error message", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnngNhp.setBounds(115, 113, 174, 23);
		frmQunLTi.getContentPane().add(btnngNhp);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(115, 74, 174, 20);
		frmQunLTi.getContentPane().add(txtPassword);
	}
}
