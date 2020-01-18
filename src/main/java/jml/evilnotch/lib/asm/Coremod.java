package jml.evilnotch.lib.asm;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public abstract class Coremod implements IFMLLoadingPlugin{
	
	public Coremod()
	{
		this.registerTransformers();
	}
	
	public abstract void registerTransformers();

	@Override
	public String[] getASMTransformerClass() {return null;}

	@Override
	public String getModContainerClass() {return null;}

	@Override
	public String getSetupClass() {return null;}

	@Override
	public void injectData(Map<String, Object> data) {}

	@Override
	public String getAccessTransformerClass() {return null;}

}
