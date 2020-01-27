package jml.evilnotch.lib.line;

import jml.evilnotch.lib.JavaUtil;
/**
 * does your head have a line implement this
 * @author jredfox
 *
 */
public interface ILineHead extends ILine{
	
	public Object getHead();
	public void setHead(Object obj);
	
	default public int getInt(){
		return JavaUtil.castInt((Number)this.getHead());
	}
	default public short getShort(){
		return JavaUtil.castShort((Number)this.getHead());
	}
	default public long getLong(){
		return JavaUtil.castLong((Number)this.getHead());
	}
	default public byte getByte(){
		return JavaUtil.castByte((Number)this.getHead());
	}
	default public float getFloat(){
		return JavaUtil.castFloat((Number)this.getHead());
	}
	default public double getDouble(){
		return JavaUtil.castDouble((Number)this.getHead());
	}
	default public String getString(){
		return (String)this.getHead();
	}
	default public boolean getBoolean(){
		return (Boolean)this.getHead();
	}
	default public boolean hasHead(){
		return this.getHead() != null;
	}
	
}
