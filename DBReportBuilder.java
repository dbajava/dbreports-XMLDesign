package XMLWizard;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JCheckBox;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class DBReportBuilder {

	private JFrame frame;
	private Instance instance;
	private JTable tbResults;
	private JTextField txTitle;
	private JTextField txColumns;
	private JEditorPane epQuery;
	private JCheckBox chckbxEveryday;
	private JCheckBox chckbxMonday;
	private JCheckBox chckbxTuesday;
	private JCheckBox chckbxWednesday;
	private JCheckBox chckbxThursday;
	private JCheckBox chckbxFriday;
	private JCheckBox chckbxSaturday;
	private JCheckBox chckbxSunday;
	private boolean validated=true;
	private JPanel pnCtlBtn;
	private JTextField txDom;
	private JTextField txHour;
	private JPanel panel;
	private JButton btnAddQuery;
	private Report report = new Report();
	public DBReportBuilder(Instance inst,boolean val) {
		this.instance=inst;
		this.validated=val;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 618, 562);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		JPanel pnPrim = new JPanel();
		frame.getContentPane().add(pnPrim, BorderLayout.NORTH);
		GridBagLayout gbl_pnPrim = new GridBagLayout();
		gbl_pnPrim.columnWidths = new int[]{150, 150, 0};
		gbl_pnPrim.rowHeights = new int[]{36, 0};
		gbl_pnPrim.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_pnPrim.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		pnPrim.setLayout(gbl_pnPrim);

		JLabel lbDbname = new JLabel(this.instance.getDbName());
		lbDbname.setBorder(new TitledBorder(null, "Database", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lbDbname.setToolTipText("");
		GridBagConstraints gbc_lbDbname = new GridBagConstraints();
		gbc_lbDbname.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbDbname.anchor = GridBagConstraints.NORTH;
		gbc_lbDbname.insets = new Insets(0, 0, 0, 5);
		gbc_lbDbname.gridx = 0;
		gbc_lbDbname.gridy = 0;
		pnPrim.add(lbDbname, gbc_lbDbname);

		JLabel lbUsername = new JLabel(this.instance.getUserName());
		lbUsername.setBorder(new TitledBorder(null, "Username", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_lbUsername = new GridBagConstraints();
		gbc_lbUsername.fill = GridBagConstraints.HORIZONTAL;
		gbc_lbUsername.anchor = GridBagConstraints.NORTH;
		gbc_lbUsername.gridx = 1;
		gbc_lbUsername.gridy = 0;
		pnPrim.add(lbUsername, gbc_lbUsername);

		JPanel pnTras = new JPanel();
		frame.getContentPane().add(pnTras, BorderLayout.CENTER);
		GridBagLayout gbl_pnTras = new GridBagLayout();
		gbl_pnTras.columnWidths = new int[]{285, 0};
		gbl_pnTras.rowHeights = new int[]{20, 78, 0, 159, 160, 0};
		gbl_pnTras.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_pnTras.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		pnTras.setLayout(gbl_pnTras);

		txTitle = new JTextField();
		GridBagConstraints gbc_txTitle = new GridBagConstraints();
		gbc_txTitle.fill = GridBagConstraints.BOTH;
		gbc_txTitle.insets = new Insets(0, 0, 5, 0);
		gbc_txTitle.gridx = 0;
		gbc_txTitle.gridy = 0;
		pnTras.add(txTitle, gbc_txTitle);
		txTitle.setAlignmentX(Component.LEFT_ALIGNMENT);
		txTitle.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Report Title", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txTitle.setColumns(10);

		JPanel pnDown = new JPanel();
		pnDown.setMaximumSize(new Dimension(15000, 32767));
		GridBagConstraints gbc_pnDown = new GridBagConstraints();
		gbc_pnDown.fill = GridBagConstraints.HORIZONTAL;
		gbc_pnDown.anchor = GridBagConstraints.NORTH;
		gbc_pnDown.insets = new Insets(0, 0, 5, 0);
		gbc_pnDown.gridx = 0;
		gbc_pnDown.gridy = 1;
		pnTras.add(pnDown, gbc_pnDown);
		GridBagLayout gbl_pnDown = new GridBagLayout();
		gbl_pnDown.columnWidths = new int[]{122, 251, 68, 92, 0};
		gbl_pnDown.rowHeights = new int[] {0, 0, 20, 0};
		gbl_pnDown.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnDown.rowWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		pnDown.setLayout(gbl_pnDown);

		txColumns = new JTextField();
		GridBagConstraints gbc_txColumns = new GridBagConstraints();
		gbc_txColumns.fill = GridBagConstraints.HORIZONTAL;
		gbc_txColumns.insets = new Insets(0, 0, 5, 0);
		gbc_txColumns.gridx = 0;
		gbc_txColumns.gridy = 2;
		pnTras.add(txColumns, gbc_txColumns);
		txColumns.setBorder(new TitledBorder(null, "Columns", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txColumns.setColumns(10);
		JScrollPane spQuery = new JScrollPane();
		GridBagConstraints gbc_spQuery = new GridBagConstraints();
		gbc_spQuery.fill = GridBagConstraints.BOTH;
		gbc_spQuery.insets = new Insets(0, 0, 5, 0);
		gbc_spQuery.gridx = 0;
		gbc_spQuery.gridy = 3;
		pnTras.add(spQuery, gbc_spQuery);

		epQuery = new JEditorPane();
		epQuery.setBorder(new TitledBorder(null, "Query", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		spQuery.setViewportView(epQuery);

		pnCtlBtn = new JPanel();
		GridBagConstraints gbc_pnCtlBtn = new GridBagConstraints();
		gbc_pnCtlBtn.anchor = GridBagConstraints.NORTH;
		gbc_pnCtlBtn.gridwidth = 2;
		gbc_pnCtlBtn.insets = new Insets(0, 0, 5, 5);
		gbc_pnCtlBtn.fill = GridBagConstraints.HORIZONTAL;
		gbc_pnCtlBtn.gridx = 0;
		gbc_pnCtlBtn.gridy = 0;
		pnDown.add(pnCtlBtn, gbc_pnCtlBtn);
		GridBagLayout gbl_pnCtlBtn = new GridBagLayout();
		gbl_pnCtlBtn.columnWidths = new int[]{90, 0, 0, 0, 0};
		gbl_pnCtlBtn.rowHeights = new int[]{0, 0, 0};
		gbl_pnCtlBtn.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_pnCtlBtn.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		pnCtlBtn.setLayout(gbl_pnCtlBtn);

		chckbxEveryday = new JCheckBox("Everyday");
		GridBagConstraints gbc_chckbxEveryday = new GridBagConstraints();
		gbc_chckbxEveryday.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxEveryday.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxEveryday.gridx = 0;
		gbc_chckbxEveryday.gridy = 0;
		pnCtlBtn.add(chckbxEveryday, gbc_chckbxEveryday);
		chckbxEveryday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				if(chckbxEveryday.isSelected() ){
					chckbxMonday.setEnabled(false);
					chckbxFriday.setEnabled(false);
					chckbxSaturday.setEnabled(false);
					chckbxSunday.setEnabled(false);
					chckbxThursday.setEnabled(false);
					chckbxTuesday.setEnabled(false);
					chckbxWednesday.setEnabled(false);
					chckbxMonday.setSelected(false);
					chckbxFriday.setSelected(false);
					chckbxSaturday.setSelected(false);
					chckbxSunday.setSelected(false);
					chckbxThursday.setSelected(false);
					chckbxTuesday.setSelected(false);
					chckbxWednesday.setSelected(false);
				}else{
					chckbxMonday.setEnabled(true);
					chckbxFriday.setEnabled(true);
					chckbxSaturday.setEnabled(true);
					chckbxSunday.setEnabled(true);
					chckbxThursday.setEnabled(true);
					chckbxTuesday.setEnabled(true);
					chckbxWednesday.setEnabled(true);
				}
			}
		});
		chckbxMonday = new JCheckBox("Monday");
		GridBagConstraints gbc_chckbxMonday = new GridBagConstraints();
		gbc_chckbxMonday.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxMonday.gridx = 1;
		gbc_chckbxMonday.gridy = 0;
		pnCtlBtn.add(chckbxMonday, gbc_chckbxMonday);

		chckbxTuesday = new JCheckBox("Tuesday");
		GridBagConstraints gbc_chckbxTuesday = new GridBagConstraints();
		gbc_chckbxTuesday.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxTuesday.gridx = 2;
		gbc_chckbxTuesday.gridy = 0;
		pnCtlBtn.add(chckbxTuesday, gbc_chckbxTuesday);

		chckbxWednesday = new JCheckBox("Wednesday");
		GridBagConstraints gbc_chckbxWednesday = new GridBagConstraints();
		gbc_chckbxWednesday.anchor = GridBagConstraints.WEST;
		gbc_chckbxWednesday.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxWednesday.gridx = 3;
		gbc_chckbxWednesday.gridy = 0;
		pnCtlBtn.add(chckbxWednesday, gbc_chckbxWednesday);

		chckbxThursday = new JCheckBox("Thursday");
		GridBagConstraints gbc_chckbxThursday = new GridBagConstraints();
		gbc_chckbxThursday.fill = GridBagConstraints.HORIZONTAL;
		gbc_chckbxThursday.anchor = GridBagConstraints.NORTH;
		gbc_chckbxThursday.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxThursday.gridx = 0;
		gbc_chckbxThursday.gridy = 1;
		pnCtlBtn.add(chckbxThursday, gbc_chckbxThursday);
		chckbxFriday = new JCheckBox("Friday");
		GridBagConstraints gbc_chckbxFriday = new GridBagConstraints();
		gbc_chckbxFriday.anchor = GridBagConstraints.NORTH;
		gbc_chckbxFriday.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxFriday.gridx = 1;
		gbc_chckbxFriday.gridy = 1;
		pnCtlBtn.add(chckbxFriday, gbc_chckbxFriday);

		chckbxSaturday = new JCheckBox("Saturday");
		GridBagConstraints gbc_chckbxSaturday = new GridBagConstraints();
		gbc_chckbxSaturday.anchor = GridBagConstraints.NORTH;
		gbc_chckbxSaturday.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxSaturday.gridx = 2;
		gbc_chckbxSaturday.gridy = 1;
		pnCtlBtn.add(chckbxSaturday, gbc_chckbxSaturday);

		chckbxSunday = new JCheckBox("Sunday");
		GridBagConstraints gbc_chckbxSunday = new GridBagConstraints();
		gbc_chckbxSunday.anchor = GridBagConstraints.NORTHWEST;
		gbc_chckbxSunday.gridx = 3;
		gbc_chckbxSunday.gridy = 1;
		pnCtlBtn.add(chckbxSunday, gbc_chckbxSunday);

		JPanel pnBtn = new JPanel();
		GridBagConstraints gbc_pnBtn = new GridBagConstraints();
		gbc_pnBtn.gridwidth = 2;
		gbc_pnBtn.insets = new Insets(0, 0, 5, 0);
		gbc_pnBtn.gridx = 2;
		gbc_pnBtn.gridy = 0;
		pnDown.add(pnBtn, gbc_pnBtn);
		GridBagLayout gbl_pnBtn = new GridBagLayout();
		gbl_pnBtn.columnWidths = new int[]{0, 91, 0};
		gbl_pnBtn.rowHeights = new int[]{23, 23, 0};
		gbl_pnBtn.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_pnBtn.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		pnBtn.setLayout(gbl_pnBtn);

		JButton btnCleanUp = new JButton("Clean up");
		btnCleanUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clearTx();
			}
		});
		JButton btnRunQuery = new JButton("Run Query");
		GridBagConstraints gbc_btnRunQuery = new GridBagConstraints();
		gbc_btnRunQuery.insets = new Insets(0, 0, 5, 5);
		gbc_btnRunQuery.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnRunQuery.gridx = 0;
		gbc_btnRunQuery.gridy = 0;
		pnBtn.add(btnRunQuery, gbc_btnRunQuery);
		btnRunQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "DbName", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				Connection connection = null;
				try {
					connection = DriverManager.getConnection("jdbc:oracle:thin:@"+instance.getHostName()+":"+instance.getPort()+"/"+instance.getDbName(),instance.getUserName(),instance.getPassw());
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "DbName", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				try {
					Statement stmt = connection.createStatement();
					System.out.println(epQuery.getText());
					ResultSet rs=stmt.executeQuery(epQuery.getText());
					resultSetToTableModel(rs,tbResults);
					connection.close();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "DbName", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnCleanUp = new GridBagConstraints();
		gbc_btnCleanUp.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnCleanUp.insets = new Insets(0, 0, 5, 0);
		gbc_btnCleanUp.gridx = 1;
		gbc_btnCleanUp.gridy = 0;
		pnBtn.add(btnCleanUp, gbc_btnCleanUp);

		btnAddQuery = new JButton("Add Query");
		btnAddQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				report.getRawColname().add(txColumns.getText());
				report.getQuery().add(epQuery.getText());
				txColumns.setText("");
				epQuery.setText("");
			}
		});
		GridBagConstraints gbc_btnAddQuery = new GridBagConstraints();
		gbc_btnAddQuery.insets = new Insets(0, 0, 0, 5);
		gbc_btnAddQuery.gridx = 0;
		gbc_btnAddQuery.gridy = 1;
		pnBtn.add(btnAddQuery, gbc_btnAddQuery);
		JButton btnSaveQuery = new JButton("Save Report");
		GridBagConstraints gbc_btnSaveQuery = new GridBagConstraints();
		gbc_btnSaveQuery.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnSaveQuery.anchor = GridBagConstraints.NORTH;
		gbc_btnSaveQuery.gridx = 1;
		gbc_btnSaveQuery.gridy = 1;
		pnBtn.add(btnSaveQuery, gbc_btnSaveQuery);
		btnSaveQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				StringTokenizer strtk =new StringTokenizer(txColumns.getText(), ",");
				try{
					if(!getDayOfWeek().equals("")){
						if(validated){
							if(strtk.countTokens()==tbResults.getColumnCount()){
								genReport(report);
							}else{
								JOptionPane.showMessageDialog(null, "Invalid Number of columns", "DbName", JOptionPane.ERROR_MESSAGE);
								return;
							}}else{
								genReport(report);
							}

					}
				}catch(StringIndexOutOfBoundsException e){
					JOptionPane.showMessageDialog(null, "Choose at least one day for this report!", "DbName", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});

		panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 4;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 2;
		pnDown.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		txDom = new JTextField();
		GridBagConstraints gbc_txDom = new GridBagConstraints();
		gbc_txDom.fill = GridBagConstraints.HORIZONTAL;
		gbc_txDom.insets = new Insets(0, 0, 0, 5);
		gbc_txDom.gridx = 0;
		gbc_txDom.gridy = 0;
		panel.add(txDom, gbc_txDom);
		txDom.setBorder(new TitledBorder(null, "Days of the Month", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txDom.setColumns(10);

		txHour = new JTextField();
		GridBagConstraints gbc_txHour = new GridBagConstraints();
		gbc_txHour.fill = GridBagConstraints.HORIZONTAL;
		gbc_txHour.gridx = 1;
		gbc_txHour.gridy = 0;
		panel.add(txHour, gbc_txHour);
		txHour.setBorder(new TitledBorder(null, "Run Hours", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		txHour.setColumns(10);

		tbResults = new JTable();
		JScrollPane spResults = new JScrollPane(tbResults);
		GridBagConstraints gbc_spResults = new GridBagConstraints();
		gbc_spResults.fill = GridBagConstraints.BOTH;
		gbc_spResults.gridx = 0;
		gbc_spResults.gridy = 4;
		pnTras.add(spResults, gbc_spResults);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	public void resultSetToTableModel(ResultSet rs, JTable table) throws SQLException{
		DefaultTableModel tableModel = new DefaultTableModel();
		ResultSetMetaData metaData = rs.getMetaData();
		int columnCount = metaData.getColumnCount();
		for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++){
			tableModel.addColumn(metaData.getColumnLabel(columnIndex));
		}
		Object[] row = new Object[columnCount];
		while (rs.next()){
			for (int i = 0; i < columnCount; i++){
				row[i] = rs.getObject(i+1);
			}
			tableModel.addRow(row);
		}
		table.setModel(tableModel);
	}
	public void clearTx(){
		this.txColumns.setText("");
		this.txTitle.setText("");
		this.epQuery.setText("");
		tbResults.setModel(new DefaultTableModel());
		this.chckbxMonday.setEnabled(true);
		this.chckbxFriday.setEnabled(true);
		this.chckbxSaturday.setEnabled(true);
		this.chckbxSunday.setEnabled(true);
		this.chckbxThursday.setEnabled(true);
		this.chckbxTuesday.setEnabled(true);
		this.chckbxWednesday.setEnabled(true);
		this.chckbxEveryday.setSelected(false);
		this.chckbxMonday.setSelected(false);
		this.chckbxFriday.setSelected(false);
		this.chckbxSaturday.setSelected(false);
		this.chckbxSunday.setSelected(false);
		this.chckbxThursday.setSelected(false);
		this.chckbxTuesday.setSelected(false);
		this.chckbxWednesday.setSelected(false);
		this.chckbxEveryday.setEnabled(true);

	}
	public String getDayOfWeek(){
		String days="";
		ArrayList<String> dow=new ArrayList<String>();
		if(this.chckbxEveryday.isSelected()){
			return "Everyday";
		}else{
			if(this.chckbxMonday.isSelected()){
				dow.add("Monday");
			}
			if(this.chckbxTuesday.isSelected()){
				dow.add("Tuesday");
			}
			if(this.chckbxWednesday.isSelected()){
				dow.add("Wednesday");
			}
			if(this.chckbxThursday.isSelected()){
				dow.add("Thursday");
			}
			if(this.chckbxFriday.isSelected()){
				dow.add("Friday");
			}
			if(this.chckbxSaturday.isSelected()){
				dow.add("Saturday");
			}
			if(this.chckbxSunday.isSelected()){
				dow.add("Sunday");
			}
			for(int i=0;i<dow.size();i++){
				days=days+dow.get(i)+",";
			}
			return days.substring(0,days.length()-1);
		}
	}
	private void genReport(Report report){
		Object[] options = {"Create another report",
		"Save this report and exit"};
		int n = JOptionPane.showOptionDialog(frame,
				"What Would you like to do?",
				"A Silly Question",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[1]);
		report.setDayOfWeek(getDayOfWeek());
		report.setTitle(txTitle.getText());
		report.getRawColname().add(txColumns.getText());
		report.getQuery().add(epQuery.getText());
		report.setDayOfMonth(txDom.getText());
		report.setrunHour(txHour.getText());
		if (n==0){
			instance.add(report);
			clearTx();
		} else if(n==1){
			instance.add(report);
			try {
				String passTpm=instance.getPassw();
				instance.setPassw(instance.getEncryPass());
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("File XML (.xml)", "xml"));
				File file;
				if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
					file = fileChooser.getSelectedFile();
					String filePath=file.getAbsolutePath();
					if(!filePath.toUpperCase().endsWith(".XML")) file=new File(filePath+".xml");
					JAXBContext jaxbContext = JAXBContext.newInstance(Instance.class);
					Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
					jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
					jaxbMarshaller.marshal(instance, file);
					jaxbMarshaller.marshal(instance, System.out);
					JOptionPane.showMessageDialog(null, "File Saved:\n"+file.getAbsolutePath(), "DbName", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
				instance.setPassw(passTpm);
			} catch (JAXBException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "DbName", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
		this.report = new Report();

	}
}
