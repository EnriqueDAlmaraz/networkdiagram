import java.awt.EventQueue;

import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;

public class Main extends NetworkDiagram {

	private JFrame frmTeam;
	private JTextField textFieldName;
	private JTextField textFieldDependencies;
	private JTextField textFieldDuration;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frmTeam.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
        
		frmTeam = new JFrame();
		frmTeam.setTitle("Team 11");
		frmTeam.setBounds(100, 100, 577, 565);
		frmTeam.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTeam.getContentPane().setLayout(null);
		
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
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldName.setBounds(27, 115, 166, 40);
		frmTeam.getContentPane().add(textFieldName);
		textFieldName.setColumns(10);
		
		textFieldDependencies = new JTextField();
		textFieldDependencies.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldDependencies.setColumns(10);
		textFieldDependencies.setBounds(27, 221, 166, 40);
		frmTeam.getContentPane().add(textFieldDependencies);
		
		textFieldDuration = new JTextField();
		textFieldDuration.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textFieldDuration.setColumns(10);
		textFieldDuration.setBounds(27, 327, 166, 40);
		frmTeam.getContentPane().add(textFieldDuration);
		
		JLabel lblPreview = new JLabel("Preview:");
		lblPreview.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPreview.setBounds(225, 76, 140, 26);
		frmTeam.getContentPane().add(lblPreview);
		
		JTextArea textAreaOutput = new JTextArea();
		textAreaOutput.setLineWrap(true);
		textAreaOutput.setEditable(false);
		textAreaOutput.setBounds(235, 124, 295, 322);
		frmTeam.getContentPane().add(textAreaOutput);
		
		PrintStream printStream = new PrintStream(new CustomOutputStream(textAreaOutput));

        // re-assigns standard output stream and error output stream
        System.setOut(printStream);
        System.setErr(printStream);
        
		JButton btnEnter = new JButton("Enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String activity = textFieldName.getText();
				Activity a = new Activity(activity); //get activity name
				textFieldName.setText("");
				
				String p = textFieldDependencies.getText(); //predecessors
				a.addPredecessor(p);
				textFieldDependencies.setText("");
				
				int duration = Integer.parseInt(textFieldDuration.getText());
					if (duration >=0)
						a.setDuration(duration);
				textFieldDuration.setText("");
			}
		});
		btnEnter.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnEnter.setBounds(27, 480, 97, 25);
		frmTeam.getContentPane().add(btnEnter);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createTree();
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSubmit.setBounds(238, 481, 97, 25);
		frmTeam.getContentPane().add(btnSubmit);
		
		JButton btnRestart = new JButton("Restart");
		btnRestart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaOutput.setText(""); //only clears output, doesn't clear tree
			}
		});
		btnRestart.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRestart.setBounds(450, 481, 97, 25);
		frmTeam.getContentPane().add(btnRestart);
		
		JButton btnAbout = new JButton("");
		btnAbout.setForeground(Color.BLACK);
		btnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(frmTeam, "FAQ: \n 1. This is a test \n 2. This is a test \n 3. This is a test", null, JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnAbout.setIcon(new ImageIcon(Main.class.getResource("/com/sun/javafx/scene/control/skin/caspian/dialog-confirm.png")));
		btnAbout.setBounds(0, 0, 48, 48);
		frmTeam.getContentPane().add(btnAbout);
		
		JButton btnHelp = new JButton("Help");
		btnHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frmTeam, "About: \n Hello and Welcome to Team 11's CSE 360 Project", null, JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnHelp.setBounds(462, 0, 97, 25);
		frmTeam.getContentPane().add(btnHelp);
		
		
	}
}
