package com.tech3s.ffm.ui;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.tech3s.ffm.model.SecurityContextHolder;
import com.tech3s.ffm.model.User;
import com.tech3s.ffm.persistence.UserDao;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class MainWindow {

	private JFrame frmQunLTi;
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private JTextField txtName;
	private JTextField txtAge;
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	private JTable table;
	private JTextField txtId;
	private JComboBox cbxPosition;
	
	private UserDao userDao = new UserDao();

	/**
	 * Create the application.
	 */
	public MainWindow(User user) {			
		initialize();
		
		SecurityContextHolder.setLoggedUser(user);
		System.out.println("Logged User: " + SecurityContextHolder.getLoggedUser());
		frmQunLTi.setTitle(frmQunLTi.getTitle() + " - " + user.getEmail());
		
		loadData();
	}

	public JFrame getFrame() {
		return frmQunLTi;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmQunLTi = new JFrame();
		frmQunLTi.setTitle("Qu\u1EA3n l\u00FD t\u00E0i ch\u00EDnh gia \u0111\u00ECnh");
		frmQunLTi.setResizable(false);
		frmQunLTi.setBounds(100, 100, 450, 450);
		frmQunLTi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmQunLTi.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Thành viên", null, panel, null);
		panel.setLayout(null);	
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "H\u1ECD v\u00E0 T\u00EAn", "Vai Tr\u00F2", "Tu\u1ED5i", "Email", "Password"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(29);
		table.getColumnModel().getColumn(1).setPreferredWidth(105);
		table.getColumnModel().getColumn(2).setPreferredWidth(55);
		table.getColumnModel().getColumn(3).setPreferredWidth(44);
		table.getColumnModel().getColumn(4).setPreferredWidth(151);
		table.getColumnModel().getColumn(5).setPreferredWidth(15);
		table.setBounds(0, 0, 200, 100);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 439, 100);
		panel.add(scrollPane);		
		
		JLabel lblHVTn = new JLabel("H\u1ECD v\u00E0 T\u00EAn");
		lblHVTn.setBounds(10, 156, 57, 14);
		panel.add(lblHVTn);
		
		txtName = new JTextField();
		txtName.setBounds(77, 153, 300, 20);
		panel.add(txtName);
		txtName.setColumns(10);
		
		JLabel lblVaiTr = new JLabel("Vai Tr\u00F2");
		lblVaiTr.setBounds(10, 197, 46, 14);
		panel.add(lblVaiTr);
		
		cbxPosition = new JComboBox();
		cbxPosition.setModel(new DefaultComboBoxModel(new String[] {"------", "Ba", "M\u1EB9", "Con trai", "Con g\u00E1i"}));
		cbxPosition.setBounds(77, 194, 300, 20);
		panel.add(cbxPosition);
		
		JLabel lblTui = new JLabel("Tu\u1ED5i");
		lblTui.setBounds(10, 237, 46, 14);
		panel.add(lblTui);
		
		txtAge = new JTextField();
		txtAge.setBounds(77, 234, 300, 20);
		panel.add(txtAge);
		txtAge.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 281, 46, 14);
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(77, 278, 300, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		JButton btnSave = new JButton("L\u01B0u");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveUser();
			}
		});
		btnSave.setBounds(77, 360, 89, 23);
		panel.add(btnSave);
		
		JLabel lblMtKhu = new JLabel("M\u1EADt kh\u1EA9u");
		lblMtKhu.setBounds(10, 326, 57, 14);
		panel.add(lblMtKhu);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(77, 323, 300, 20);
		panel.add(txtPassword);
		
		JButton btnAdd = new JButton("Th\u00EAm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createUser();
			}
		});
		btnAdd.setBounds(10, 107, 113, 23);
		panel.add(btnAdd);
		
		JButton btnUpdate = new JButton("S\u1EEDa");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateUser();
			}
		});
		btnUpdate.setBounds(140, 107, 105, 23);
		panel.add(btnUpdate);
		
		JButton btnDelete = new JButton("X\u00F3a");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteUser();
			}
		});
		btnDelete.setBounds(264, 107, 113, 23);
		panel.add(btnDelete);
		
		txtId = new JTextField();
		txtId.setBounds(77, 132, 1, 1);
		panel.add(txtId);
		txtId.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Thu", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Chi", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Báo cáo", null, panel_3, null);
	}
		
	private void loadData() {
		loadUsers();		
	}
	
	private void loadUsers() {		
		List<User> users = userDao.list();
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.getDataVector().clear();
		
		Object[] rowData  = new Object[model.getColumnCount()];
		for(int i=0; i<users.size(); i++) {
			User user = users.get(i);
			rowData[0] = user.getId();
			rowData[1] = user.getName();
			rowData[2] = user.getPosition();
			rowData[3] = user.getAge();			
			rowData[4] = user.getEmail();
			rowData[5] = user.getPassword();
		    model.addRow(rowData);
		}
	}
	
	
	// Event handlers	
	private void createUser() {
		txtId.setText("");
		txtName.setText("");
		cbxPosition.getModel().setSelectedItem("------");
		txtAge.setText("");
		txtEmail.setText("");
		txtPassword.setText("");
	}
	
	private void updateUser() {		
		int rowIndex = table.getSelectedRow();
		if (rowIndex > -1) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			txtId.setText(model.getValueAt(rowIndex, 0).toString());
			txtName.setText(model.getValueAt(rowIndex, 1).toString());
			cbxPosition.getModel().setSelectedItem(model.getValueAt(rowIndex, 2).toString());
			txtAge.setText(model.getValueAt(rowIndex, 3).toString());
			txtEmail.setText(model.getValueAt(rowIndex, 4).toString());
			txtPassword.setText(model.getValueAt(rowIndex, 5).toString());
		}
	}
	
	private void deleteUser() {		
		int rowIndex = table.getSelectedRow();
		if (rowIndex > -1) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();				
			String idStr = model.getValueAt(rowIndex, 0).toString();
			userDao.delete(Integer.parseInt(idStr));			
			loadUsers();
		}
	}
	
	private void saveUser() {
		User user = new User();		
		user.setName(txtName.getText());
		user.setEmail(txtEmail.getText());
		user.setAge(Integer.parseInt(txtAge.getText()));
		user.setPassword(txtPassword.getText());
		user.setPosition(cbxPosition.getSelectedItem().toString());
		String idStr = txtId.getText();
		if (!idStr.equals("")) {
			user.setId(Integer.parseInt(idStr));
			userDao.update(user);			
		}
		else {
			userDao.create(user);
		}
		JOptionPane.showMessageDialog(frmQunLTi, "Success");
		loadUsers();				
	}
}
