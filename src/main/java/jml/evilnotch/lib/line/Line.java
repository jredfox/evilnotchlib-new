package jml.evilnotch.lib.line;

import jml.evilnotch.lib.JavaUtil;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class Line extends AbstractLine{

	public Line(String str) 
	{
		super(str);
	}

	@Override
	public void parse(String str)
	{
		
	}
	
	public void parseId(String idSection)
	{
		if(idSection.contains(":"))
		{
			String[] idSplit = JavaUtil.splitFirst(idSection, ':');
			this.domain = idSplit[0];
			this.path = idSplit[1];
		}
		else
		{
			this.path = this.id;
		}
		this.id = this.domain + this.path;
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
	
	public Object getMeta()
	{
		return this.meta.get(0);
	}
	
	public NBTTagCompound getNBT()
	{
		return (NBTTagCompound)this.meta.get(1);
	}

}
