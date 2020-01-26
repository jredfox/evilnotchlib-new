package jml.evilnotch.lib.asm.test;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraft.launchwrapper.LogWrapper;

public class ITweakDerp implements ITweaker{

	@Override
	public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) 
	{
		LogWrapper.log(Level.ERROR, "ACCEPTING OPTIONS");
	}

	@Override
	public void injectIntoClassLoader(LaunchClassLoader classLoader) 
	{
		LogWrapper.log(Level.ERROR, "Injecting nothing");
	}

    @Override
    public String getLaunchTarget()
    {
        return "";
    }

    @Override
    public String[] getLaunchArguments()
    {
        return new String[0];
    }

}
