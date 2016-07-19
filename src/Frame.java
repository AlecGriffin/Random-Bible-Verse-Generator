import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class Frame extends JFrame {
	private int sizeX;
	private int sizeY;

	private JButton randomButton; 
	private JButton happyButton;
	private JButton sadButton;
	private JButton depressedButton;
	private JButton familyButton;
	private JButton friendsButton;
	private JButton upliftButton;
	private JButton addVerseButton;
	private JButton allVersesButton;

	private JTextArea textOutput;
	private JTabbedPane tabbedPane;
	private JComboBox verseType;

	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	private JLabel label4;

	private JTextField verseTextField;
	private JTextField bookField;
	private JTextField chapterField;
	private JTextField verseNumField;

	private static String fileToRead;
	private static String fileToWrite;

	private static ArrayList <String> verseList;


	public Frame(){

		verseList = new ArrayList(10);
		fileToRead = "Temp Lists/DefaultVerseList.txt";
		fileToWrite = "Temp Lists/HappyTempList.txt";
		
		//Frame Settings
		sizeX = 700;
		sizeY = 300;
		setSize(sizeX, sizeY);
		setTitle("Bible Verse Generator - Version 1.0");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Create mainPanel / set as Content Pane
		JPanel mainPanel = new JPanel();
		JPanel addVersePanel = new JPanel(new GridLayout(5, 2));
		JPanel selectVersePanel = new JPanel(new GridLayout(4,2));

		// Creates Button that produces random bible verse
		randomButton = new JButton("Press:");
		randomButton.addActionListener(new RandomButtonListener());
		happyButton = new JButton("Happy List");
		happyButton.addActionListener(new happyButtonListener());
		sadButton = new JButton("Sad List:");
		sadButton.addActionListener(new sadButtonListener());
		depressedButton = new JButton("Depressed List:");
		depressedButton.addActionListener(new depressedButtonListener());
		familyButton = new JButton("Family List:");
		familyButton.addActionListener(new familyButtonListener());
		friendsButton = new JButton("Friends List:");
		friendsButton.addActionListener( new friendsButtonListener());
		upliftButton = new JButton("Uplift List:");
		upliftButton.addActionListener(new upliftButtonListener());
		addVerseButton = new JButton("Add Verse:");
		addVerseButton.addActionListener(new addVerseListener());
		allVersesButton = new JButton("All Verses");
		allVersesButton.addActionListener(new allVersesButtonListener());

		// Creates TextArea for Bible verses to be placed in
		textOutput = new JTextArea("Press Button to generate random bible verse!");
		textOutput.setEditable(false);
		
		// Creates Tabbed Area 
		tabbedPane = new JTabbedPane();
		this.setContentPane(tabbedPane);
		tabbedPane.addTab("Random Verse: ", mainPanel);
		tabbedPane.addTab("Add Verse:", addVersePanel);
		tabbedPane.addTab("Select Verse List:", selectVersePanel);


		// Creates Label
		label1 = new JLabel("Enter verse (Text): ");
		label2 = new JLabel("Enter Book: ");
		label3 = new JLabel("Enter Chapter: ");
		label4 = new JLabel("Enter Verse (Number): ");

		// Text Field
		verseTextField = new JTextField(40);
		bookField = new JTextField(40);
		chapterField = new JTextField(40);
		verseNumField = new JTextField(40);

		// JComboBox
		verseType = new JComboBox(new String[]{"Happy", "Sad", "Depressed", "Family", "Friends", "Uplift"});
		verseType.addActionListener(new listToAddToComboBox());

		// Add Components to mainPanel
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(randomButton, BorderLayout.WEST);
		mainPanel.add(textOutput, BorderLayout.CENTER);

		// Add Components to panel2	
		addVersePanel.add(verseType);
		addVersePanel.add(addVerseButton); // Inefficient
		addVersePanel.add(label1);
		addVersePanel.add(verseTextField);
		addVersePanel.add(label2);
		addVersePanel.add(bookField);
		addVersePanel.add(label3);
		addVersePanel.add(chapterField);
		addVersePanel.add(label4);
		addVersePanel.add(verseNumField);

		// Add Components to panel3
		selectVersePanel.add(allVersesButton);
		selectVersePanel.add(happyButton);
		selectVersePanel.add(sadButton);
		selectVersePanel.add(depressedButton);
		selectVersePanel.add(familyButton);
		selectVersePanel.add(friendsButton);
		selectVersePanel.add(upliftButton);

		setVisible(true);
	}

	public static void main(String[] args){

		Frame frame = new Frame();
		readTextFile();
	}

	public static void writeToTextFile(String str){
		try{
			
			PrintWriter pWriter1 = new PrintWriter(new FileWriter(fileToWrite, true));
			pWriter1.println(str);
			
			// Adds All New Verse to the Default List (Contains all added verses)
			PrintWriter pWriter2 = new PrintWriter(new FileWriter("Temp Lists/DefaultVerseList.txt", true));
			pWriter2.println(str); 
			
//			 <--- Testing ---> 
			System.out.println("Writing Successful");
			System.out.println(fileToWrite);
			
			pWriter1.close();
			pWriter2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String generateBibleVerse(Random rand){

		// Generates Random Number: 1  through verseList.size()
		System.out.println(verseList.size());
		int randomNum = rand.nextInt(verseList.size());
		return verseList.get(randomNum);
	}

	public static void readTextFile(){
		try{
			Scanner scan = new Scanner(new File(fileToRead));
			
			// <--- Testing ---> 
//			System.out.println("Reading File Successful!");
//			System.out.println(verseList.toString()+ "\n");
			// <--- Testing --->
			
			while(scan.hasNextLine()){
				String line = scan.nextLine();
				verseList.add(line);
			}
			// <--- Testing ---> 
			//System.out.println(verseList.toString());
			
		}catch(FileNotFoundException e){
			// <--- Testing ---> 
//			System.out.println("ERROR: FILE NOT FOUND");
			
			e.printStackTrace();
		}

	}

	// 	<----------Action Listener Classes ---------->
	public class RandomButtonListener implements ActionListener{
		Random rand = new Random();

		@Override
		public void actionPerformed(ActionEvent e){
			textOutput.setText(generateBibleVerse(rand));

		}

	}

	public class addVerseListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			//JTextField[] a1 = {verseTextField, bookField, chapterField, verseNumField};
			StringBuilder sb = new StringBuilder();

			String verseString = verseTextField.getText();
			String bookString = bookField.getText();
			String chapterString = chapterField.getText();
			String verseNumString = verseNumField.getText();

			if(!verseString.isEmpty() && !bookString.isEmpty() && !chapterString.isEmpty() && !verseNumString.isEmpty()){ 
				sb.append("\"");
				sb.append(verseString);
				sb.append("\" ");
				sb.append(bookString);
				sb.append(" ");
				sb.append(chapterString);
				sb.append(":");
				sb.append(verseNumString);
				writeToTextFile(sb.toString());

				// Clears Textfields after verse is added to text file
				verseTextField.setText("");
				bookField.setText("");
				chapterField.setText("");
				verseNumField.setText("");
			}
		}
	}
	
	public class allVersesButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			verseList.clear();
			fileToRead = "Permanent Lists/HappyList.txt";
		}
	}

	public class happyButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			verseList.clear();
			fileToRead = "Permanent Lists/HappyList.txt";
			readTextFile();
			fileToRead = "Temp Lists/HappyTempList.txt";
			readTextFile();
		}
	}

	public class sadButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			verseList.clear();
			fileToRead = "Permanent Lists/SadList.txt";
			readTextFile();
			fileToRead = "Temp Lists/SadTempList.txt";
			readTextFile();
		}
	}

	public class depressedButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			verseList.clear();
			fileToRead = "Permanent Lists/DepressedList.txt";
			readTextFile();
			fileToRead = "Temp Lists/DepressedTempVerseList.txt";
			readTextFile();
		}
	}

	public class familyButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			verseList.clear();
			fileToRead = "Permanent Lists/FamilyList.txt";
			readTextFile();
			fileToRead = "Temp Lists/FamilyTempList.txt";
			readTextFile();
		}
	}

	public class friendsButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			verseList.clear();
			fileToRead = "Permanent Lists/FriendsList.txt";
			readTextFile();
			fileToRead = "Temp Lists/FriendsTempList.txt";
			readTextFile();
		}
	}

	public  class upliftButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			verseList.clear();
			fileToRead = "Permanent Lists/UpliftList.txt";
			readTextFile();
			fileToRead = "Temp Lists/UpliftTempList.txt";
			readTextFile();
			
		}
	}
	
	public class listToAddToComboBox implements ActionListener{
		
		public void actionPerformed(ActionEvent e){
			JComboBox listToAddToComboBox = (JComboBox) e.getSource();
			String item = (String) listToAddToComboBox.getSelectedItem();
			// System.out.println(item); // Test
			fileToWrite = "Temp Lists/" + item + "TempList.txt";
		}
	}

}



