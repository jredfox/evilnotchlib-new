package jml.evilnotch.lib.line;

import java.util.ArrayList;
import java.util.List;

public class Section {
	
	public Character start;
	public Character end;
	public Character lquote;//be null if you don't want to support quotes
	public Character rquote;//be null if you don't want to support quotes
	
	public Section(Character start, Character end, char lquote, char rquote)
	{
		this.start = start;
		this.end = end;
		this.lquote = lquote;
		this.rquote = rquote;
	}
	
	/**
	 * if the section doesn't exist it will be null inside of the string array
	 */
	public static String[] splitSections(String string, List<Section> sections)
	{
		String[] list = new String[sections.size()];
		int index = 0;
		int count = 0;
		for(Section section : sections)
		{
			int startIndex = indexOf(string, index, section.start, section.lquote, section.rquote);
			int endIndex = indexOf(string, startIndex, section.end, section.lquote, section.rquote);
			if(startIndex == -1 || endIndex == -1)
				continue;
			list[count++] = string.substring(startIndex, endIndex);
			index = endIndex;
		}
		return list;
	}
	
	public static int indexOf(String string, int index, String find, char lq, char rq)
	{
		if(find.isEmpty())
			return index;
		else if(index == -1)
			return -1;
		return -1;
	}

}
