package Glossary;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;  
import com.microsoft.sqlserver.jdbc.*;  

public class Glossary extends JPanel
{
	//define gui components
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
	//other variables
	private TermsManager termsManager;
	
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
		txtaTermsList = new JTextArea(100,100);
		this.termsManager = new TermsManager();
		
		//set title pane components
		JPanel titleLine = new JPanel();
		titleLine.add(lblTitle);
		
		//set form components
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
		
		//set terms list components
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
		
		//populate terms list text area with currently existing terms
		updateList();
		
		//implement button functionality
		//attempt to add a term and definition to termsManager, update txtaTermsList text
		btnAdd.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					String term = txtTerm.getText().trim();
					String definition = txtDefinition.getText().trim();
					termsManager.addTerm(term, definition);
					txtTerm.setText("");
					txtDefinition.setText("");
					updateList();
				}          
			});
		
		//search for a term based on a query entered into txtSearchTerm, prints list with term moved to beginning of the list if found
		btnSearch.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					String query = txtSearchTerm.getText();
					txtSearchTerm.setText("");
					updateList(query);
				}          
			});
	
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
	
	//prints current list of search terms in txtaTermsList
	public void updateList()
	{
		//clear terms list text area
		this.txtaTermsList.setText("");
		
		//set vars
		ArrayList<Term> termsList = this.termsManager.getTermsList();  //holds array list of Term objects
		Term currentTerm = new Term();     //holds current Term object in arrayList
		String listText = new String("");  //string containing all list terms to be added to terms list text area
		
		//build listText string using all elements in termsList
		for (int i = 0; i < termsList.size(); i++) 
		{
			currentTerm = termsList.get(i);
			listText = listText + currentTerm.getTermName() + ":" +  "\n" + currentTerm.getTermDefinition() + "\n\n";
		}
		//set terms list text area text value to listText
		this.txtaTermsList.setText(listText);
	}	
	
	//searches for a specific term name based on query; if found, prints term name and definition at top of the list in txtaTermsList, then prints the rest of the list in normal order
	public void updateList(String query)
	{
		//clean input
		query = query.trim();
		
		//set vars
		ArrayList<Term> termsList = this.termsManager.getTermsList();  //holds array list of Term objects
		Term currentTerm;  //holds current Term object from termsList 
		
		//search for query match with one of the term names in termsList
		boolean found = false;     //flag to tell when/if term is found
		int matchIndex = -1;		//save index of found term
		//search for term, found when term name matches query
		for (int i = 0; i < termsList.size(); i++) 
		{
			currentTerm = termsList.get(i);
			if(currentTerm.getTermName().equals(query))
			{
				found = true;
				matchIndex = i;
				break;
			}
		}
		//if query found, print it and its corresponding defintion first, then the rest of the search terms in normal order
		if(found)
		{
			//clear terms list text area
			this.txtaTermsList.setText("");
			//set currentTerm to term found in search
			currentTerm = termsList.get(matchIndex);
			
			//set vars
			String listText = new String();  //string containing all list terms to be added to terms list text area
			//initilize listText string with term and definition at index of found matching query
			listText = currentTerm.getTermName() + ":" +  "\n" + currentTerm.getTermDefinition() + "\n\n";
			
			//build listText string using all elements in termsList minus the found term
			for (int i = 0; i < termsList.size(); i++) 
			{
				if(matchIndex != i)
				{
					currentTerm = termsList.get(i);
					listText = listText + currentTerm.getTermName() + ":" +  "\n" + currentTerm.getTermDefinition() + "\n\n";
				}
			}
			//set terms list text area text value to listText
			this.txtaTermsList.setText(listText);
		}
		//if query not found, list items normally
		else
		{
			//clear terms list text area
			this.txtaTermsList.setText("");
			
			//set vars
			String listText = new String("");  //string containing all list terms to be added to terms list text area
			
			//build listText string using all elements in termsList
			for (int i = 0; i < termsList.size(); i++) 
			{
				currentTerm = termsList.get(i);
				listText = listText + currentTerm.getTermName() + ":" +  "\n" + currentTerm.getTermDefinition() + "\n\n";
			}
			//set terms list text area text value to listText
			this.txtaTermsList.setText(listText);
		}		
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