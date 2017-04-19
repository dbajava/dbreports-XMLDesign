package XMLWizard;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JPasswordField;

public class vxmlWizard {

	private JFrame frmDbreportXml;
	private final JPanel pnGeral = new JPanel();
	private JTextField txDbname;
	private JTextField txEmailTitle;
	private JTextField txHostname;
	private JTextField txDbUser;
	private JTextField txMailTo;
	private JTextField txMailHost;
	private JPanel pnControl;
	private JButton btContruct;
	private JTextPane txOutput;
	private JTextField txPort;
	private JButton btnCreateReports;
	private int valPoints=0;
	private JPasswordField txPasswd;
	private JPanel panel;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vxmlWizard window = new vxmlWizard();
					window.frmDbreportXml.setLocationRelativeTo(null);  // *** this will center your app ***
					window.frmDbreportXml.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public vxmlWizard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmDbreportXml = new JFrame();
		frmDbreportXml.setTitle("DBReport - XML Wizard");
		frmDbreportXml.setBounds(100, 100, 676, 517);
		frmDbreportXml.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDbreportXml.getContentPane().setLayout(new BoxLayout(frmDbreportXml.getContentPane(), BoxLayout.X_AXIS));

		
		panel = new JPanel();
		frmDbreportXml.getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{664, 0};
		gbl_panel.rowHeights = new int[]{179, 303, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		GridBagConstraints gbc_pnGeral = new GridBagConstraints();
		gbc_pnGeral.anchor = GridBagConstraints.NORTH;
		gbc_pnGeral.fill = GridBagConstraints.HORIZONTAL;
		gbc_pnGeral.insets = new Insets(0, 0, 5, 0);
		gbc_pnGeral.gridx = 0;
		gbc_pnGeral.gridy = 0;
		panel.add(pnGeral, gbc_pnGeral);
		pnGeral.setBorder(new TitledBorder(null, "Instance Setup", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagLayout gbl_pnGeral = new GridBagLayout();
		gbl_pnGeral.columnWidths = new int[]{330, 330, 0};
		gbl_pnGeral.rowHeights = new int[]{36, 36, 36, 36, 0};
		gbl_pnGeral.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_pnGeral.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnGeral.setLayout(gbl_pnGeral);
		
		txDbname = new JTextField();
		txDbname.setBorder(new TitledBorder(null, "Database Service", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txDbname.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_txDbname = new GridBagConstraints();
		gbc_txDbname.fill = GridBagConstraints.BOTH;
		gbc_txDbname.insets = new Insets(0, 0, 5, 5);
		gbc_txDbname.gridx = 0;
		gbc_txDbname.gridy = 0;
		pnGeral.add(txDbname, gbc_txDbname);
		txDbname.setColumns(11);
		
		txEmailTitle = new JTextField();
		txEmailTitle.setBorder(new TitledBorder(null, "Email Title", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_txEmailTitle = new GridBagConstraints();
		gbc_txEmailTitle.fill = GridBagConstraints.BOTH;
		gbc_txEmailTitle.insets = new Insets(0, 0, 5, 0);
		gbc_txEmailTitle.gridx = 1;
		gbc_txEmailTitle.gridy = 0;
		pnGeral.add(txEmailTitle, gbc_txEmailTitle);
		txEmailTitle.setColumns(10);
		
		txHostname = new JTextField();
		txHostname.setBorder(new TitledBorder(null, "Database Hostname", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_txHostname = new GridBagConstraints();
		gbc_txHostname.fill = GridBagConstraints.BOTH;
		gbc_txHostname.insets = new Insets(0, 0, 5, 5);
		gbc_txHostname.gridx = 0;
		gbc_txHostname.gridy = 1;
		pnGeral.add(txHostname, gbc_txHostname);
		txHostname.setColumns(10);
		
		txPort = new JTextField();
		txPort.setBorder(new TitledBorder(null, "Database Port", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txPort.setColumns(10);
		GridBagConstraints gbc_txPort = new GridBagConstraints();
		gbc_txPort.fill = GridBagConstraints.BOTH;
		gbc_txPort.insets = new Insets(0, 0, 5, 0);
		gbc_txPort.gridx = 1;
		gbc_txPort.gridy = 1;
		pnGeral.add(txPort, gbc_txPort);
		
		txDbUser = new JTextField();
		txDbUser.setBorder(new TitledBorder(null, "Database Username", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_txDbUser = new GridBagConstraints();
		gbc_txDbUser.fill = GridBagConstraints.BOTH;
		gbc_txDbUser.insets = new Insets(0, 0, 5, 5);
		gbc_txDbUser.gridx = 0;
		gbc_txDbUser.gridy = 2;
		pnGeral.add(txDbUser, gbc_txDbUser);
		txDbUser.setColumns(10);
		
		txPasswd = new JPasswordField();
		txPasswd.setBorder(new TitledBorder(null, "Password", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_txPasswd = new GridBagConstraints();
		gbc_txPasswd.fill = GridBagConstraints.BOTH;
		gbc_txPasswd.insets = new Insets(0, 0, 5, 0);
		gbc_txPasswd.gridx = 1;
		gbc_txPasswd.gridy = 2;
		pnGeral.add(txPasswd, gbc_txPasswd);
		
		txMailTo = new JTextField();
		txMailTo.setBorder(new TitledBorder(null, "Send To", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_txMailTo = new GridBagConstraints();
		gbc_txMailTo.fill = GridBagConstraints.BOTH;
		gbc_txMailTo.insets = new Insets(0, 0, 0, 5);
		gbc_txMailTo.gridx = 0;
		gbc_txMailTo.gridy = 3;
		pnGeral.add(txMailTo, gbc_txMailTo);
		txMailTo.setColumns(10);
		
		txMailHost = new JTextField();
		txMailHost.setBorder(new TitledBorder(null, "SMTP Server", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_txMailHost = new GridBagConstraints();
		gbc_txMailHost.fill = GridBagConstraints.BOTH;
		gbc_txMailHost.gridx = 1;
		gbc_txMailHost.gridy = 3;
		pnGeral.add(txMailHost, gbc_txMailHost);
		txMailHost.setColumns(10);
		
		pnControl = new JPanel();
		GridBagConstraints gbc_pnControl = new GridBagConstraints();
		gbc_pnControl.fill = GridBagConstraints.BOTH;
		gbc_pnControl.gridx = 0;
		gbc_pnControl.gridy = 1;
		panel.add(pnControl, gbc_pnControl);
		GridBagLayout gbl_pnControl = new GridBagLayout();
		gbl_pnControl.columnWidths = new int[]{120, 120, 0, 0};
		gbl_pnControl.rowHeights = new int[]{0, 36, 0};
		gbl_pnControl.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_pnControl.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		pnControl.setLayout(gbl_pnControl);
		
		btContruct = new JButton("Validate");
		GridBagConstraints gbc_btContruct = new GridBagConstraints();
		gbc_btContruct.fill = GridBagConstraints.HORIZONTAL;
		gbc_btContruct.insets = new Insets(0, 0, 5, 5);
		gbc_btContruct.gridx = 0;
		gbc_btContruct.gridy = 0;
		pnControl.add(btContruct, gbc_btContruct);
		btContruct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txOutput.setText("Validating fields...");
				if(txDbname.getText().equals("")){
			        JOptionPane.showMessageDialog(null, "Please insert Database name. ", "DbName", JOptionPane.ERROR_MESSAGE);
			        return;
				}
				if(txDbUser.getText().equals("")){
			        JOptionPane.showMessageDialog(null, "Please insert Database username. ", "DbName", JOptionPane.ERROR_MESSAGE);
			        return;
				}
				if(txEmailTitle.getText().equals("")){
			        JOptionPane.showMessageDialog(null, "Please insert email title. ", "DbName", JOptionPane.ERROR_MESSAGE);
			        return;
				}
				if(txHostname.getText().equals("")){
			        JOptionPane.showMessageDialog(null, "Please insert Database hostname. ", "DbName", JOptionPane.ERROR_MESSAGE);
			        return;
				}
				if(txMailHost.getText().equals("")){
			        JOptionPane.showMessageDialog(null, "Please insert SMTP mail server hostname. ", "DbName", JOptionPane.ERROR_MESSAGE);
			        return;
				}
				if(txMailTo.getText().equals("")){
			        JOptionPane.showMessageDialog(null, "Please insert e-mails. ", "DbName", JOptionPane.ERROR_MESSAGE);
			        return;
				}
				if(txPasswd.getPassword().equals("")){
			        JOptionPane.showMessageDialog(null, "Please insert Password. ", "DbName", JOptionPane.ERROR_MESSAGE);
			        return;
				}
				valPoints++;//1
				txOutput.setText(txOutput.getText()+"\nValidating DB connections...");
				//validate db info
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "DbName", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
					valPoints=0;
					return;
				}
				Connection connection = null;
				//connection = DriverManager.getConnection("jdbc:oracle:thin:@"+instance.getHostName()+":"+instance.getPort()+"/"+instance.getDbName(),instance.getUserName(),decrypt(instance.getPassw()));

				try {
					connection = DriverManager.getConnection("jdbc:oracle:thin:@"+txHostname.getText()+":"+txPort.getText()+"/"+txDbname.getText(),txDbUser.getText(),txPasswd.getText());
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "DbName", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
					valPoints=0;
				}
				//+txDbname.getText()+"/"+instance.getDbName(),instance.getUserName(),decrypt(instance.getPassw()));
				try {
					//testing connection
					connection.createStatement();
					connection.close();
					txOutput.setText(txOutput.getText()+"\nDatabase connection successfully...");
					valPoints++;//2
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "DbName", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
					valPoints=0;
				}
				//checking mail server 
				InetAddress address;
				try {
					address = InetAddress.getByName(txMailHost.getText());
					if(address.isReachable(100)){
						txOutput.setText(txOutput.getText()+"\nMail Server reachable...");
						valPoints++;//3
					}else{
						txOutput.setText(txOutput.getText()+"\nMail Server unreachable...");
						valPoints=0;
					}
				} catch (IOException e) {
					txOutput.setText(txOutput.getText()+"\nMail Server unreachable...");
					valPoints=0;
					e.printStackTrace();
				}
			
			}
		});
		
		btnCreateReports = new JButton("Create Reports");
		GridBagConstraints gbc_btnCreateReports = new GridBagConstraints();
		gbc_btnCreateReports.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCreateReports.insets = new Insets(0, 0, 5, 5);
		gbc_btnCreateReports.gridx = 1;
		gbc_btnCreateReports.gridy = 0;
		pnControl.add(btnCreateReports, gbc_btnCreateReports);
		btnCreateReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (valPoints==3){
					 //jump to report screen String dbName, String fantasyName, String hostName, String port, String userName, String passw,String mailto, String hostmail
					Instance inst=new Instance(txDbname.getText(),txEmailTitle.getText(),txHostname.getText(),txPort.getText(),txDbUser.getText(),txPasswd.getText(),txMailTo.getText(),txMailHost.getText());
					new DBReportBuilder(inst);
					frmDbreportXml.setVisible(false);
				}else{
					valPoints=0;
					JOptionPane.showMessageDialog(null, "Invalid arguments, please check... ", "DbName", JOptionPane.ERROR_MESSAGE);
			        return;
				}
			}
		});
		
		txOutput = new JTextPane();
		txOutput.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Validation Output", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txOutput.setEditable(false);
		GridBagConstraints gbc_txOutput = new GridBagConstraints();
		gbc_txOutput.gridwidth = 3;
		gbc_txOutput.fill = GridBagConstraints.BOTH;
		gbc_txOutput.gridx = 0;
		gbc_txOutput.gridy = 1;
		pnControl.add(txOutput, gbc_txOutput);
	}

}