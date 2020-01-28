package jml.evilnotch.lib.line;

import java.util.List;

public abstract class AbstractLine {
	
	public String domain = this.getDomainDefault();//will be empty if unsupported based on it's line type
	public String path;
	public String id;
	public List<Object> meta;
	public List<Object> values;
	
	public AbstractLine(String str)
	{
		str = str.trim();
		this.parse(str);
	}
	
	public abstract String getDomainDefault();
	public abstract void parse(String str);

}
