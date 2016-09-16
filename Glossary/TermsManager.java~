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
                termsList = getTermsList();
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
	
	public void addTerm(String termName, String termDefinition)
	{
		termName = termName.trim();
		termDefinition = termDefinition.trim();
		Term term = new Term(termName, termDefinition);
		termsList.add(term);
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