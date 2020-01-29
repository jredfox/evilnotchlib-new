package jml.evilnotch.lib.line;

import java.util.ArrayList;
import java.util.List;

import jml.evilnotch.lib.JavaUtil;
import jml.evilnotch.lib.minecraft.util.NBTUtil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class Line extends AbstractLine{

	public char sep;
	public char lmeta;
	public char rmeta;
	public char lmetaJson;
	public char rmetaJSon;
	public char lquote;
	public char rquote;
	public char equals;
	public char comma;
	public List<Section> sections = new ArrayList(4);
	public static final String[] types = {"B", "S", "I", "L", "F", "D", "Z"};
	
	/**
	 * the default line parsing chars
	 */
	public Line() 
	{
		this(':', '<', '>', '{', '}', '"', '"', '=', ',');
	}
	
	/**
	 * everything is customizable for parsing
	 */
	public Line(char sep, char lmeta, char rmeta, char lmetaJson, char rmetaJson, char lquote, char rquote, char equals, char comma)
	{
		super();
		this.sep = sep;
		this.lmeta = lmeta;
		this.rmeta = rmeta;
		this.lmetaJson = lmetaJson;
		this.rmetaJSon = rmetaJson;
		this.lquote = lquote;
		this.rquote = rquote;
		this.equals = equals;
		this.comma = comma;
		this.meta = new ArrayList(2);
		this.values = new ArrayList(1);
		this.sections.add(new Section(null, null, this.lquote, this.rquote));
		this.sections.add(new Section(this.lmeta, this.rmeta, this.lquote, this.rquote));
		this.sections.add(new Section(this.lmetaJson, this.rmetaJSon, this.lquote, this.rquote));
		this.sections.add(new Section(this.equals, null, this.lquote, this.rquote));
	}

	/**
	 * called by the config after line construction for parsing
	 */
	@Override
	public void parse(String str)
	{
		String[] sections = Section.splitSections(str, this.sections);
		this.parseId(sections[0]);
		this.parseMeta(sections[1]);
		this.parseJson(sections[2]);
		this.parseValues(sections[3]);
	}

	public void parseId(String idSection)
	{
		if(idSection == null)
			throw new RuntimeException("Section for line is null this should never happen!");
		if(idSection.contains("" + this.sep))
		{
			String[] idSplit = JavaUtil.splitFirst(idSection, this.sep);
			this.domain = idSplit[0];
			this.path = idSplit[1];
		}
		else
		{
			this.path = idSection;
		}
		this.id = this.domain + this.path;
	}
	
	public void parseMeta(String metaStr) 
	{
		if(metaStr == null)
			return;
		metaStr = JavaUtil.parseQuotes(metaStr, 0, this.lmeta + "" + this.rmeta);
		this.meta.add(this.parseValue(metaStr));
	}
	
	public void parseJson(String jsonStr) 
	{
		if(jsonStr == null)
			return;
		NBTTagCompound json = NBTUtil.getNBTTagCompound(jsonStr);
		if(json == null)
			throw new IllegalArgumentException("invalid parsing for json:" + jsonStr);
		this.meta.add(json);
	}
	
	public void parseValues(String valuesStr) 
	{
		if(valuesStr == null)
			return;
		valuesStr = valuesStr.substring(1);//remove the equals
		String[] values = JavaUtil.split(valuesStr, this.comma, this.lquote, this.rquote);
		for(String str : values)
			this.values.add(this.parseValue(str));
	}
	
	public Object parseValue(String str)
	{
		if(str.endsWith(types[0]))
			return Byte.parseByte(str.substring(0, str.length()-1));
		else if(str.endsWith(types[1]))
			return Short.parseShort(str.substring(0, str.length()-1));
		else if(str.endsWith(types[2]))
			return Integer.parseInt(str.substring(0, str.length()-1));
		else if(str.endsWith(types[3]))
			return Long.parseLong(str.substring(0, str.length()-1));
		else if(str.endsWith(types[4]))
			return Float.parseFloat(str.substring(0, str.length()-1));
		else if(str.endsWith(types[5]))
			return Double.parseDouble(str.substring(0, str.length()-1));
		else if(str.endsWith(types[6]))
			return Boolean.parseBoolean(str.substring(0, str.length()-1));
		else if(str.startsWith("" + this.lquote))
			return JavaUtil.parseQuotes(str, 0, this.lquote + "" + this.rquote);
		else if(JavaUtil.isStringNum(str))
			return Long.parseLong(str);
		return str.trim();//asserts that the value is now a string and will keep it trimed
	}

	@Override
	public String getDomainDefault()
	{
		return "minecraft";
	}
	
	public ResourceLocation getResourceLocation()
	{
		return new ResourceLocation(this.domain, this.path);
	}
	
	public Byte getMetaByte()
	{
		return (Byte)this.getMeta();
	}
	
	public Short getMetaShort()
	{
		return (Short) this.getMeta();
	}
	
	public Integer getMetaInt()
	{
		return (Integer) this.getMeta();
	}
	
	public Long getMetaLong()
	{
		return (Long) this.getMeta();
	}
	
	public Float getMetaFloat()
	{
		return (Float) this.getMeta();
	}
	
	public Double getMetaDouble()
	{
		return (Double) this.getMeta();
	}
	
	public Boolean getMetaBoolean()
	{
		return (Boolean) this.getMeta();
	}
	
	public String getMetaString()
	{
		return (String) this.getMeta();
	}
	
	public Object getMeta()
	{
		return this.meta.get(0);
	}
	
	public NBTTagCompound getNBT()
	{
		return (NBTTagCompound)this.meta.get(1);
	}

}
