package Glossary;

import java.sql.*;  
import com.microsoft.sqlserver.jdbc.*; 
import java.util.*;

public class TermsManager 
{
	ArrayList<Term> termsList;
	Connection conn;
	
	public TermsManager()
	{         
		//connect to database and populate terms list with currently existing terms
		String connectionString = "jdbc:sqlserver://localhost:45777;databaseName=glossary;user=sa;password=asdfbnm8";
		termsList = new ArrayList<Term>();
		this.conn = null;
		try 
		{  
                    this.conn = DriverManager.getConnection(connectionString); 
                }  
                catch (Exception e) 
                {  
                    e.printStackTrace();  
                }  
                this.termsList = getTermsList();
	}
	
	//returns list of terms currently existing in database
	public ArrayList<Term> getTermsList() 
	{	
		termsList = new ArrayList<Term>();
		Statement stmt = null;   
                ResultSet terms = null;
		try 
		{                      
                    // get all existing terms from database  
                    String sql = "SELECT termName,termDefinition FROM term ORDER BY termName;";  
                    stmt = this.conn.createStatement();  
                    terms = stmt.executeQuery(sql);  
      
                    // add terms from database into termsList array list
                    Term temp;
                    while (terms.next())   
                    {  
                    	temp = new Term(terms.getString("termName"),terms.getString("termDefinition"));
                    	termsList.add(temp);
                    } 
                }  
                catch (Exception e) 
                {  
                    e.printStackTrace();  
                }  
                finally 
                {  
                    // Close the connections after the data has been handled.  
                    if (terms != null) try { terms.close(); } catch(Exception e) {}  
                    if (stmt != null) try { stmt.close(); } catch(Exception e) {}  
                }
		return termsList;
	}
	//add new glossary term to database and update termsList
	public void addTerm(String termName, String termDefinition)
	{
		//clean input
		termName = termName.trim();
		termDefinition = termDefinition.trim();
		
		//insert new term into database 
		Statement stmt = null;   
                ResultSet terms = null;
		try 
		{                      
                    // Create and execute a SELECT SQL statement.  
                    String sql = "INSERT INTO term (termName, termDefinition) VALUES ('" + termName + "', '" + termDefinition + "');";  
                    stmt = this.conn.createStatement();  
                    stmt.executeUpdate(sql);  
                }  
                catch (Exception e) 
                {  
                    e.printStackTrace();  
                }  
                finally 
                {  
                    // Close the connections after the data has been handled.  
                    if (terms != null) try { terms.close(); } catch(Exception e) {}  
                    if (stmt != null) try { stmt.close(); } catch(Exception e) {}  
                }
                
                //update termsList
                this.termsList = getTermsList();
	}
	
	public boolean deleteTerm(String termName, String termDefinition)
	{
		//check that there are items in the list
		if(this.termsList.isEmpty() == false)
		{
			//clean input
			termName = termName.trim();
			termDefinition = termDefinition.trim();
			System.out.print (termName + "\n");
			System.out.print (termDefinition + "\n");
			
			//create vars
			boolean deleteSuccessful = false;  //return value to signal whether the term was found and deleted successfully or not
			Term currentTerm;  //holds current Term object from termsList 
			
			//search for query match with one of the term names in termsList
			boolean found = false;     //flag to tell when/if term is found
			int matchIndex = -1;		//save index of found term
			//search for term, found when term name matches query
			for (int i = 0; i < this.termsList.size(); i++) 
			{
				currentTerm = this.termsList.get(i);
				if((currentTerm.getTermName().equals(termName)) && (currentTerm.getTermDefinition().equals(termDefinition)))
				{
					found = true;
					matchIndex = i;
					break;
				}
			}
			//if query found, delete term from database and update termsList
			if(found)
			{
				//delete term in database 
				Statement stmt = null;   
				ResultSet terms = null;
				try 
				{      
				    // Create and execute a DELETE SQL statement.  
				    String sql = "DELETE FROM term WHERE termName = '" + termName + "' AND termDefinition = '" + termDefinition + "';";  
				    stmt = this.conn.createStatement();  
				    stmt.executeUpdate(sql);  
				}  
				catch (Exception e) 
				{  
				    e.printStackTrace();  
				}  
				finally 
				{  
				    // Close the connections after the data has been handled.  
				    if (terms != null) try { terms.close(); } catch(Exception e) {}  
				    if (stmt != null) try { stmt.close(); } catch(Exception e) {}  
				}
				
				//update termsList
				this.termsList = getTermsList();
				
				//return delete successful signal
				deleteSuccessful = true;
				return deleteSuccessful;
			}
			//if query not found, send delete unsuccessful signal to calling method
			else
			{
				//return delete unsuccessful signal
				return deleteSuccessful;
			}
		}
		else 
		{
			return false;
		}
	}
		
	
	protected void finalize() throws Throwable 
	{
		if (conn != null)
		{
			try 
			{ 
				conn.close(); 
			} 
			catch(Exception e) 
			{
			}
		}
	}
}