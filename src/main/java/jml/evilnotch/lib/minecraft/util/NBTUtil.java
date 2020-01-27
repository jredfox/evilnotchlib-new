package jml.evilnotch.lib.minecraft.util;

import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class NBTUtil {
	
	public static NBTTagCompound getNBTTagCompound(String str)
	{
		return (NBTTagCompound) getNBT(str);
	}
	
	public static NBTTagList getNBTTagList(String str)
	{
		return (NBTTagList) getNBT(str);
	}
	
	public static NBTBase getNBT(String str)
	{
		try
		{
			JsonToNBT.func_150315_a(str);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
