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
		Connection conn = null;
		Statement stmt = null;   
                ResultSet terms = null;
		try 
		{  
                    conn = DriverManager.getConnection(connectionString); 
                    
                    // Create and execute a SELECT SQL statement.  
                    String selectSql = "select termName,termDefinition from term;";  
                    stmt = conn.createStatement();  
                    terms = stmt.executeQuery(selectSql);  
      
                    // add terms from database into termsList array
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
                    if (conn != null) try { conn.close(); } catch(Exception e) {}
                }
	}
	
	public ArrayList<Term> getTermsList() 
	{		
		return (this.termsList);
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