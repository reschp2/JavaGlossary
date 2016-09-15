package Glossary;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;  
import com.microsoft.sqlserver.jdbc.*;  

public class Glossary extends JPanel
{
	private JLabel lblTitle;
	private JLabel lblTerm;
	private JLabel lblDefinition;
	private JLabel lblSearchTerm;
	private JLabel lblTermsList;
	private JButton btnAdd;
	private JButton btnSearch;
	private JTextField txtTerm;
	private JTextField txtDefinition;
	private JTextField txtSearchTerm;
	private JTextArea txtaTermsList;
	private String connectionString;
	
	public Glossary()
	{         
		//create componenets
		lblTitle = new JLabel("Glossary", JLabel.CENTER);
		lblTerm = new JLabel("Term:", JLabel.CENTER);
		lblDefinition = new JLabel("Definition:", JLabel.CENTER);
		lblSearchTerm = new JLabel("Search Term:", JLabel.CENTER);
		lblTermsList = new JLabel("Terms", JLabel.CENTER);
		btnAdd = new JButton("Add");
		btnSearch = new JButton("Search");
		txtTerm = new JTextField(25);
		txtDefinition = new JTextField(100);
		txtSearchTerm = new JTextField(25);
		txtaTermsList = new JTextArea(50,50);
		
		//create and display title
		JPanel titleLine = new JPanel();
		titleLine.add(lblTitle);
		
		//create and display form components
		JPanel form = new JPanel();
		form.setLayout(new GridBagLayout());
		GridBagConstraints form_Contraints = new GridBagConstraints();
		form_Contraints.gridx = 0;
		form_Contraints.gridy = 0;
		form_Contraints.anchor = GridBagConstraints.CENTER;
		form.add(lblTerm,form_Contraints);
		form_Contraints.gridx++;
		form_Contraints.anchor = GridBagConstraints.LINE_START;
		form.add(txtTerm,form_Contraints);
		form_Contraints.gridx = 0;
		form_Contraints.gridy = 1;
		form_Contraints.anchor = GridBagConstraints.CENTER;
		form.add(lblDefinition,form_Contraints);
		form_Contraints.gridx++;
		form_Contraints.anchor = GridBagConstraints.LINE_START;
		form.add(txtDefinition,form_Contraints);
		form_Contraints.gridy++;
		form.add(btnAdd,form_Contraints);
		form_Contraints.gridx = 0;
		form_Contraints.gridy = 4;
		form_Contraints.anchor = GridBagConstraints.CENTER;
		form.add(lblSearchTerm,form_Contraints);
		form_Contraints.gridx++;
		form_Contraints.anchor = GridBagConstraints.LINE_START;
		form.add(txtSearchTerm,form_Contraints);
		form_Contraints.gridy++;
		form.add(btnSearch,form_Contraints);
		
		//create and display terms list text area components
		txtaTermsList.setEditable(false);
		txtaTermsList.setLineWrap(true);
		JPanel termsListGrid = new JPanel();
		termsListGrid.setLayout(new GridBagLayout());
		GridBagConstraints termsListGrid_constraints = new GridBagConstraints();
		termsListGrid_constraints.gridx = 0;
		termsListGrid_constraints.gridy = 0;
		termsListGrid.add(lblTermsList,termsListGrid_constraints);
		termsListGrid_constraints.gridy++;
		termsListGrid.add(txtaTermsList,termsListGrid_constraints);
		
		//populate terms list with currently existing terms
		ArrayList<Term> termsList = new ArrayList<Term>();
		TermsManager tm = new TermsManager();
		termsList = tm.getTermsList();
		Term curr = new Term();
		String listText = new String("");
		for (int i = 0; i < termsList.size(); i++) 
		{
			curr = termsList.get(i);
			listText = listText + curr.getTermName() + ":" +  "\n" + curr.getTermDefinition() + "\n\n";
		}
		txtaTermsList.setText(listText);
	
                //add views to parent grid
		JPanel parentGrid = new JPanel();
		parentGrid.setLayout(new GridBagLayout());
		GridBagConstraints parentGrid_contraints = new GridBagConstraints();
		parentGrid_contraints.gridx = 0;
		parentGrid_contraints.gridy = 0;
		parentGrid.add(titleLine,parentGrid_contraints);
		parentGrid_contraints.gridy++;
		parentGrid.add(form,parentGrid_contraints);
		parentGrid_contraints.gridy++;
		parentGrid.add(termsListGrid,parentGrid_contraints);
		
		//make application scrollable by placing parentGrid in a scroll pane
		JScrollPane parentPane = new JScrollPane(parentGrid);
		parentPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		add(parentPane);
	}
	
	public static void drawScreen() 
	{		
		//Create and set up the window.
		JFrame frame = new JFrame("Glossary");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		//Create and set up the content pane.
		Glossary newContentPane = new Glossary();
		newContentPane.setOpaque(true); //content panes must be opaque
		frame.setContentPane(newContentPane);
	
		//Display the window.
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) 
	{
		//Schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
			drawScreen(); 
		    }
		});
	}
}