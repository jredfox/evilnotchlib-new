package jml.evilnotch.lib.asm;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.SortingIndex;
import net.minecraft.launchwrapper.Launch;

@IFMLLoadingPlugin.Name("ASMPlugin")
@IFMLLoadingPlugin.MCVersion("1.7.10")
@IFMLLoadingPlugin.SortingIndex(1001)
@IFMLLoadingPlugin.TransformerExclusions("jml.evilnotch.lib.asm.")
public class ASMPlugin extends CoreMod{
	
	@Override
	public String[] getASMTransformerClass() 
	{
		return new String[]{"jml.evilnotch.lib.asm.ASMLoader"};
	}

	@Override
	public void registerTransformers() 
	{
		PatchedClassLoader.stopMemoryOverflow(Launch.classLoader);
	}

}
