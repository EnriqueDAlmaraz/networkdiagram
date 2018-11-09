import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.awt.Choice;
import javax.swing.JComboBox;

/**
 * <center>
 * <table cellpadding="5" cellspacing="5">
 *  <tr>
 *  <td valign="top">
 *   Course: CSE 360<br>
 *   Section Line Number: 89049<br>
 *   Project: Activity Network<br>
 *  </td>
 *  
 *  <td valign="top">
 *   Contributor: Anthony Benites,<br>
 *   Arizona State Univeristy<br>
 *  </td>
 * 
 *  <td valign="top">
 *   Contributor: Luis Claramunt <br>
 *   Arizona State Univeristy<br>
 *  </td>
 * 
 * <td valign="top">
 *   Contributor: Enrique Almaraz<br>
 *   Arizona State Univeristy<br>
 *  </td>
 *  </tr>
 * </table>
 * </center>
 */
public class GUI {

	public JFrame frmTeam;
	private JTextField textFieldName, textFieldPredecessor, textFieldDuration;
	private JTextArea outputCreatedActivities, outputSortedPaths;
	public static JButton btnRestart, btnAbout, btnEnter, btnSubmit, btnHelp, btnQuit, btnExport, btnEdit;
	public static JComboBox choice;
	private List<Activity> activities;
	private int justOnce = 0;
	
	public GUI() {
		activities = new LinkedList<>();
		
		frmTeam = new JFrame();
		frmTeam.setTitle("Path Analysis");
		frmTeam.setBounds(100, 100, 577, 565);
		frmTeam.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTeam.getContentPane().setLayout(null);
		frmTeam.setResizable(false);
		
		//Labels 		
		JLabel lblName = new JLabel("Name: ");
		lblName.setBounds(27, 62, 120, 40);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		frmTeam.getContentPane().add(lblName);
		
		JLabel lblDependencies = new JLabel("Dependencies:");
		lblDependencies.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDependencies.setBounds(27, 168, 120, 40);
		frmTeam.getContentPane().add(lblDependencies);
		
		JLabel lblDuration = new JLabel("Duration:");
		lblDuration.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDuration.setBounds(27, 274, 120, 40);
		frmTeam.getContentPane().add(lblDuration);
		
		JLabel lblPreview = new JLabel("Activities Created:");
		lblPreview.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPreview.setBounds(225, 76, 180, 26);
		frmTeam.getContentPane().add(lblPreview);
		
		//TextFields
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldName.setBounds(27, 115, 166, 40);
		frmTeam.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		textFieldName.setText("");
		
		textFieldPredecessor = new JTextField();
		textFieldPredecessor.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldPredecessor.setColumns(10);
		textFieldPredecessor.setBounds(27, 221, 166, 40);
		frmTeam.getContentPane().add(textFieldPredecessor);
		textFieldPredecessor.setText("");
		
		textFieldDuration = new JTextField();
		textFieldDuration.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldDuration.setColumns(10);
		textFieldDuration.setBounds(27, 327, 166, 40);
		frmTeam.getContentPane().add(textFieldDuration);
		textFieldDuration.setText("");
		
		//Text Area
		outputCreatedActivities = new JTextArea();			
		outputCreatedActivities.setEditable(false);
		JScrollPane scrollPanel1 = new JScrollPane(outputCreatedActivities);
		scrollPanel1.setBounds(225, 115, 300, 150);
		frmTeam.getContentPane().add(scrollPanel1);
		
		outputSortedPaths = new JTextArea();			
		outputSortedPaths.setEditable(false);
		JScrollPane scrollPanel2 = new JScrollPane(outputSortedPaths);
		scrollPanel2.setBounds(225, 300, 300, 150);
		frmTeam.getContentPane().add(scrollPanel2);
		
		//Combo Box
		String[] pathChoice = new String[] {"All Paths", "Critical Paths"};

		JComboBox choice = new JComboBox(pathChoice);
		choice.setBounds(377, 270, 148, 26);
		frmTeam.getContentPane().add(choice);
		choice.setVisible(true);
		
		//Buttons
		btnEnter = new JButton("Enter");
		btnEnter.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEnter.setBounds(27, 424, 97, 25);
		frmTeam.getContentPane().add(btnEnter);
		
		btnEdit = new JButton("Edit");
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEdit.setBounds(27, 424, 97, 25);
		frmTeam.getContentPane().add(btnEdit);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSubmit.setBounds(27, 481, 97, 25);
		frmTeam.getContentPane().add(btnSubmit);
	
		btnRestart = new JButton("Restart");
		btnRestart.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRestart.setBounds(161, 481, 97, 25);
		frmTeam.getContentPane().add(btnRestart);
		
		btnQuit = new JButton("Quit");
		btnQuit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnQuit.setBounds(425, 481, 97, 25);
		frmTeam.getContentPane().add(btnQuit);
		
		btnExport = new JButton("Export");
		btnExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String reportName = JOptionPane.showInputDialog("What is the name of your report?");
				try (PrintWriter out = new PrintWriter("report.txt")) {
					String dateTime = new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss").format(Calendar.getInstance().getTime());
				    out.println("Title: " + reportName + "\n"); 
				    		out.println("\nDate & Time: "  + dateTime +"\n");
				    		out.println("\nActivities: " + outputCreatedActivities.getText() +"\n");
				    		out.println("\nPaths: " + outputSortedPaths.getText() + "\n" /*+paths*/);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnExport.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnExport.setBounds(297, 481, 97, 25);
		frmTeam.getContentPane().add(btnExport);
		
		//Add action Listeners
		ActionListener listener = new ButtonListener();
		btnEnter.addActionListener(listener);
		btnSubmit.addActionListener(listener);
		btnRestart.addActionListener(listener);
		btnQuit.addActionListener(listener);
		
		//About button already has an action  
		btnAbout = new JButton("");
		btnAbout.setForeground(Color.BLACK);
		JLabel about = new JLabel("<html>About: <br>Hello and welcome to our Path Analysis application. This program finds relations between <br>existing nodes " 
				+ "based on the dependencies that exist between them.<br><br>The main functionality of the program is to find all the paths between<br>the existing nodes," 
				+ "calculate their duration, and sort them in a descending order based on it.");
		about.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,about,"ABOUT",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		
		btnAbout.setIcon(new ImageIcon(Main.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-confirm.png")));
		btnAbout.setBounds(0, 0, 48, 48);
		frmTeam.getContentPane().add(btnAbout);
		
		btnHelp = new JButton("Help");
		JLabel help = new JLabel("<html>Help:<br>This application interface takes in three different inputs, and all are needed to proceed:<br>" + 
				"<br>" + 
				"<b>Name</b>: The name of this activity.<br>" + 
				"<br>" + 
				"<b>Duration</b>: The duration of this activity.<br>" + 
				"<br>" + 
				"<b>Dependencies</b>: The dependencies that this activity related to.<br>" + 
				"<br>" + 
				"<b>On the right-hand side:</b> there is a preview feature that displays all current node data when the button Submit is entered. <br>" + 
				"<br>" + 
				"<b>Restart button:</b> clears all the information that the user has previously entered<br>" +
				"<br>" +
				"<b>Enter button:</b> allows the user to enter in the currently filled text boxes as an activity");
		help.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,help,"HELP",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		btnHelp.setBounds(462, 0, 97, 25);
		frmTeam.getContentPane().add(btnHelp);
		
		JLabel lblSortedPath = new JLabel("Sorted Paths:");
		lblSortedPath.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSortedPath.setBounds(225, 270, 140, 26);
		frmTeam.getContentPane().add(lblSortedPath);
		
	}
	
	//This class determines what the buttons are actually going to do 
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {		
			List<String> predecessors = new ArrayList<String>();	//Store the predecessors read as strings from the textField
			boolean errors = false;
			
			//Get inputs from the text fields 
			String nodeName = textFieldName.getText();
			String getDuration = textFieldDuration.getText();
			String getPredecessor = textFieldPredecessor.getText();
			predecessors = Arrays.asList(getPredecessor.split(" "));	//Split the input into multiple strings
			
			//Find errors in the inputs
			
	
			//User clicks enter
			if (event.getSource() == btnEnter && errors == false) {
				errors = findInputErrors();
				Activity newActivity = new Activity(null);
				newActivity.setName(nodeName);
				newActivity.setDuration(Integer.parseInt(getDuration));
				for(int i = 0; i < predecessors.size(); i++) {			//Add each predecessor
					newActivity.addPredecessor(predecessors.get(i));
				}
				if (errors == false) {
					activities.add(newActivity);	//Add the new Activity
					outputCreatedActivities.append(newActivity.toString());			
					textFieldName.setText("");
					textFieldDuration.setText("");
					textFieldPredecessor.setText("");
				}
			}		
			//User clicks submit
			if(event.getSource() == btnSubmit && activities.size() != 0 && justOnce == 0) {		//justOnce is here if we want to allow the user to submit it only once
				btnEnter.setVisible(false);
				btnEdit.setVisible(true);
				outputSortedPaths.setText(null);
				try
				{
					outputSortedPaths.append(Network.createTree(activities));
				} catch (Exception e)
				{
					outputSortedPaths.append(e.getMessage());
				}
				//justOnce = 1;
			}
			//User click restart
			if(event.getSource() == btnRestart) {
				btnEnter.setVisible(true);
				btnEdit.setVisible(false);
				activities.clear();		//Work later on clearing the TextArea and TextField
				outputCreatedActivities.setText(null);
				outputSortedPaths.setText(null);
				textFieldName.setText("");
				textFieldDuration.setText("");
				textFieldPredecessor.setText("");
				//justOnce = 1;
			}
			
			if(event.getSource()==btnQuit) {
				System.exit(0);
			}
		}
	}
	
	//Find errors such as missing and invalid information
	private boolean findInputErrors() {
		boolean errorFound = false;
		if (alreadyExists(textFieldName.getText()) == true) {
			JOptionPane.showMessageDialog(frmTeam, "An activity under that name already exists. Please choose another one", null, JOptionPane.ERROR_MESSAGE);
			errorFound = true;
		}
		
		if (textFieldName.getText().equals("")) {
			JOptionPane.showMessageDialog(frmTeam, "Pleaser enter a name for the activity", null, JOptionPane.ERROR_MESSAGE);
			errorFound = true;
		}
		//Error if the user does not enter a duration
		if (textFieldDuration.getText().equals("")) {
			JOptionPane.showMessageDialog(frmTeam, "Pleaser enter a duration for the activity", null, JOptionPane.ERROR_MESSAGE);
			errorFound = true;
		}
		else {
			//Check if the user entered a number for the duration	
			try {
	        	   Integer.parseInt(textFieldDuration.getText());		
	           }
	           catch(NumberFormatException numberForTesting){				
	        	   JOptionPane.showMessageDialog(frmTeam, "Invalid duration. Please enter an integer", null, JOptionPane.ERROR_MESSAGE);
	        	   errorFound = true;			
	           }
		}
				
		return errorFound;
	}
	//activities.get(i).getName()
	
	private boolean alreadyExists(String name) {
		boolean exists = false;
		String test = null;
		for (int i = 0; i < activities.size(); i++) {
			test = activities.get(i).getName();
			if (name.equalsIgnoreCase(test)) {
				exists = true;
			}
		}		
		return exists;
	}
} 