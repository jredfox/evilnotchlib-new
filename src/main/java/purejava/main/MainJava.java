package purejava.main;

import java.io.File;

import jml.evilnotch.lib.asm.ASMHelper;
import jml.evilnotch.lib.simple.SimpleConfig;

public class MainJava {
	
	public static void main(String[] args)
	{
		SimpleConfig cfg = new SimpleConfig(new File(ASMHelper.getHome(),"eclipse/test.cfg"), '=', true);
		cfg.load();
		cfg.getString("grade", "F");
		cfg.getBoolean("enable", true);
		cfg.save();
		System.out.println("done:" + cfg);
	}

}
