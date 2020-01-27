package jml.evilnotch.lib.line.config;

import java.io.File;
import java.util.List;

import jml.evilnotch.lib.line.ILine;
import jml.evilnotch.lib.line.LineDynamicLogic;
import jml.evilnotch.lib.line.util.LineUtil;

public class ConfigDynamicLogic extends ConfigLine{
	
	/**
	 * create config base for only in memory manipulation
	 */
	public ConfigDynamicLogic()
	{

	}
	
	public ConfigDynamicLogic(File f) 
	{
		super(f);
	}
	public ConfigDynamicLogic(String inputStream,File output)
	{
		super(inputStream,output);
	}
	public ConfigDynamicLogic(File f, List<String> comments) {
		this(f,comments,"");
	}
	
	public ConfigDynamicLogic(File f,List<String> comments,String header)
	{
		this(f,header,LineUtil.commentDefault,comments);
	}
	public ConfigDynamicLogic(File f,String header,char commentStart,List<String> comments)
	{
		super(f,header,commentStart,comments);
	}
	public ConfigDynamicLogic(File f,String header,boolean allowComments,char commentStart,List<String> comments,char[] headerWrappers,char sep,char q,char[] metaBrackets,char[] arrBrackets,String orLogic,String andLogic)
	{
		super(f,header,allowComments,commentStart,comments,headerWrappers,sep,q,metaBrackets,arrBrackets,orLogic,andLogic);
	}

	@Override
	public ILine getLineFromString(String str) 
	{
		return new LineDynamicLogic(str);
	}

}
