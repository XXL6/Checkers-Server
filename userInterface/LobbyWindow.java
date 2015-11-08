package userInterface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JSeparator;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class LobbyWindow implements LobbyWindowInterface{

	private JFrame frmCheckers;
	private JTextField chatTxtField;
	private JTextArea chatTxtArea;
	private JList<String> usrList;
	private DefaultListModel<String> usrListModel;
	private DefaultListModel<String> tableListModel;
	private JButton btnDisconnect;
	private JButton sendButton;
	private JButton btnCreateTable;
	private JList tableList;
	private JScrollPane chatScrollPane;
	/**
	 * Create the application.
	 */
	public LobbyWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	public void display(boolean display) {
		frmCheckers.setVisible(display);
	}
	
	private void initialize() {
		frmCheckers = new JFrame();
		frmCheckers.setTitle("Checkers");
		usrListModel = new DefaultListModel<String>();
		tableListModel = new DefaultListModel<String>();
		frmCheckers.setBounds(100, 100, 750, 700);
		frmCheckers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		chatTxtArea = new JTextArea();
		chatTxtArea.setEditable(false);
		chatTxtArea.setLineWrap(true);
		chatTxtArea.setRows(2);
		Border border = BorderFactory.createLineBorder(Color.BLACK);
		chatTxtArea.setBorder(border);
		chatScrollPane = new JScrollPane (chatTxtArea, 
				   JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		usrList = new JList<String>(usrListModel);
		usrList.setBorder(border);
		JScrollPane userScrollPane = new JScrollPane (usrList, 
				   JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);		
		chatTxtField = new JTextField();
		chatTxtField.setColumns(10);
		chatTxtField.setBorder(border);
		
		btnDisconnect = new JButton("Disconnect");
		btnDisconnect.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		sendButton = new JButton("Send");
		sendButton.setFont(new Font("Tahoma", Font.PLAIN, 9));
		
		btnCreateTable = new JButton("Create Table");
		btnCreateTable.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		tableList = new JList<String>(tableListModel);
		tableList.setBorder(border);
		JScrollPane tableScrollPane = new JScrollPane (tableList, 
				   JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);			
		
		JLabel lblTablesInServer = new JLabel("Tables in server");
		lblTablesInServer.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		GroupLayout groupLayout = new GroupLayout(frmCheckers.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(tableScrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(chatTxtField, GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
								.addComponent(chatScrollPane, GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(userScrollPane, GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnCreateTable, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnDisconnect, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblTablesInServer))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTablesInServer)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tableScrollPane, GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnDisconnect)
						.addComponent(btnCreateTable))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(chatScrollPane, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(chatTxtField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(sendButton, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)))
						.addComponent(userScrollPane, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
					.addGap(21))
		);
		frmCheckers.getContentPane().setLayout(groupLayout);
//		usrList.addMouseListener( new MouseAdapter()
//		{
//		    public void mousePressed(MouseEvent e)
//		    {
//		        if ( SwingUtilities.isRightMouseButton(e) )
//		        {
//		            JList list = (JList)e.getSource();
//		            int row = list.locationToIndex(e.getPoint());
//		            list.setSelectedIndex(row);
//		        }
//		    }
//
//		});
	}
	
	public void setChatListener(ActionListener listener) {
		chatTxtField.addActionListener(listener);
		sendButton.addActionListener(listener);
	}
	
	public void setDisconnectListener(ActionListener listener) {
		btnDisconnect.addActionListener(listener);
	}
	
	public void insertText(String text) {
		chatTxtArea.append(text + "\n");
		chatTxtArea.validate();
		chatScrollPane.getVerticalScrollBar().setValue(chatScrollPane.getVerticalScrollBar().getMaximum());
	}
	
	public String retrieveInputText() {
		String returnString = chatTxtField.getText();
		chatTxtField.setText("");
		return returnString;
	}
	
	public void insertUser(String username, boolean client) {
		if (client) {
			usrListModel.insertElementAt(username, 0);	
		} else {
			usrListModel.addElement(username);
		}
	}
	
	public void removeUser(String username) {
		String removeString;
		for(int i = 0; i < usrListModel.getSize(); i++){
		     removeString =  usrListModel.getElementAt(i).toString(); 
		     if (removeString.equalsIgnoreCase(username)) {
		    	 usrListModel.remove(i);
		     }
		}
	}
	
	public boolean containsUser(String username) {
		for(int i = 0; i < usrListModel.getSize(); i++){
		    if (usrListModel.getElementAt(i).toString().equalsIgnoreCase(username)) {
		    	return true;
		    }
		}
		return false;
	}
	
	public void clearUsers() {
		usrListModel.clear();
	}
	
	public void insertTable(String tableIdentifier) {
		tableListModel.addElement(tableIdentifier);
	}
	
	public void removeTable(String tableIdentifier) {
		String removeString;
		for(int i = 0; i < tableListModel.getSize(); i++){
		     removeString =  tableListModel.getElementAt(i).toString(); 
		     if (removeString.equalsIgnoreCase(tableIdentifier)) {
		    	 tableListModel.remove(i);
		     }
		}
	}
	
	public void clearTables() {
		tableListModel.clear();
	}

//	@Override
//	public void adjustmentValueChanged(AdjustmentEvent arg0) {
//		e.getAdjustable().setValue(e.getAdjustable().getMaximum());
//		
//	}
}
