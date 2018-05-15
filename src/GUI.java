import java.awt.EventQueue;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;


/**
 * A Graphical User Interface for the plagiarism checker
 * @author Erik L
 *
 */
public class GUI extends JFrame {

	private JPanel 		contentPane;
	private JTextField 	txtFileTwo;
	private JTextField 	txtFile;
	private Label 		lblResult;
	private JButton 	btnCheck;

	/**
	 * Create the frame.
	 * 
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				ColumnSpec.decode("141px"),
				ColumnSpec.decode("max(141px;default)"),
				ColumnSpec.decode("141px"),},
			new RowSpec[] {
				RowSpec.decode("50px"),
				RowSpec.decode("30px"),
				RowSpec.decode("30px"),
				RowSpec.decode("90px"),
				RowSpec.decode("245px"),}));
		
		JLabel lblTwoFilePaths = new JLabel("Input two file with their paths");
		contentPane.add(lblTwoFilePaths, "1, 1, 3, 1, center, center");
		
		
		txtFile = new JTextField();
		txtFile.setText("Path for 1st file");
		contentPane.add(txtFile, "1, 2, 3, 1, fill, fill");
		txtFile.setColumns(10);
		
		txtFileTwo = new JTextField();
		txtFileTwo.setText("Path for 2nd file");
		contentPane.add(txtFileTwo, "1, 3, 3, 1, fill, fill");
		txtFileTwo.setColumns(10);
		
		btnCheck = new JButton("Check");
		contentPane.add(btnCheck, "2, 4, fill, fill");
		
		lblResult = new Label((String) null);
		lblResult.setAlignment(Label.CENTER);
		lblResult.setFont(new Font("Dialog", Font.PLAIN, 35));
		contentPane.add(lblResult, "1, 5, 3, 1, fill, fill");
		
		btnCheck.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String input1 = txtFile.getText();
				String input2 = txtFileTwo.getText();
				
				if(Main.readFile(input1) != null && Main.readFile(input2) != null) {
					HashTableClass file1HashTable = Main.readFile(input1);
					HashTableClass file2HashTable = Main.readFile(input2);
					String result = String.valueOf(Main.compareHash(file1HashTable, file2HashTable));
					lblResult.setText("Same identifiers: " + result + "%");
				}
				else {lblResult.setText("File not found");}
			}
		});
	}
}
