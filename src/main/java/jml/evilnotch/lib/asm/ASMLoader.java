package jml.evilnotch.lib.asm;

import org.objectweb.asm.tree.ClassNode;

import net.minecraft.launchwrapper.IClassTransformer;
import net.minecraft.launchwrapper.Launch;

public class ASMLoader implements IClassTransformer{

	@Override
	public byte[] transform(String oldName, String name, byte[] bytes) 
	{
		if(bytes == null)
			return bytes;//do not parse null classes
		
		ClassNode node = null;
		ITransformer last = null;
		try
		{
			TransformsReg.sort();
			for(ITransformer transformer : TransformsReg.transformers)
			{
				if(!transformer.canTransform(name))
					continue;
				if(node == null)
					node = ASMHelper.getClassNode(bytes);
				last = transformer;
				transformer.transform(name, node);
			}
			if(last == null)
				return bytes;
			
			byte[] custom = ASMHelper.getClassWriter(node).toByteArray();
			if(ObfHelper.isDeob)
				ASMHelper.dumpFile(name, custom);
			return custom;
		}
		catch(Throwable t)
		{
			t.printStackTrace();
			if(!name.equals("net.minecraft.util.ResourceLocation"))
				System.out.print("Blamed Transformer:\t" + (last != null ? last.id() : null) + "\nLoaded Transformers:" + TransformsReg.printIds() + "\n");
			else
				System.out.print("Blamed Transformer:\t" + (last != null ? last.getClass() : null) + TransformsReg.printClasses() + "\n");
		}
		return bytes;
	}

}
