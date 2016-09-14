package Glossary;

public class Term  
{
	private int termId;
	private String termName;
	private String termDefinition;
	
	public Term()
	{         
		;
	}
	
	public Term(String termName, String termDefinition)
	{
		this.termName = termName;
		this.termDefinition = termDefinition;
	}
	
	public Term(int termId, String termName, String termDefinition)
	{
		this.termId = termId;
		this.termName = termName;
		this.termDefinition = termDefinition;
	}
	
	public int getTermId() 
	{		
		return this.termId;
	}
	
	public void setTermId(int termId) 
	{		
		this.termId = termId;
	}
	
	public String getTermName() 
	{		
		return this.termName;
	}
	
	public void setTermName(String termName) 
	{		
		this.termName = termName;
	}
	
	public String getTermDefinition() 
	{		
		return this.termDefinition;
	}
	
	public void setTermDefinition(String termDefinition) 
	{		
		this.termDefinition = termDefinition;
	}
}