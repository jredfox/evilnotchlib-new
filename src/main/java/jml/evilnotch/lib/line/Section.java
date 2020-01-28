package jml.evilnotch.lib.line;

public class Section {
	
	public String start;
	public String end;
	public char lquote;
	public char rquote;
	
	public Section(String start, String end, char lquote, char rquote)
	{
		this.start = start;
		this.end = end;
		this.lquote = lquote;
		this.rquote = rquote;
	}
	
	public static String[] splitSections(Section sections...)
	{
		
	}

}
