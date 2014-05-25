package com.igotyou.diamondMarker;

import java.io.File;
import java.io.IOException;

import org.lwjgl.input.Keyboard;

import com.igotyou.diamondMarker.utils.Location;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = DiamondMarker.MODID, name = "diamondMarker", version = DiamondMarker.VERSION)
public class DiamondMarker
{
    public static final String MODID = "diamondMarker";
    public static final String VERSION = "1.0";
        
    public static KeyBinding guiBinding;
    public static File settingsFile;
    public static Settings settings;
    
    
    
    @Mod.Instance("diamondMarker")
    public static DiamondMarker instance;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) 
    {
    	
    	File directory = event.getModConfigurationDirectory();
        if (!directory.isDirectory()) 
        {
          directory.mkdir();
        }
        settingsFile = new File(directory, "diamondMarker.json");
        if (!settingsFile.isFile())
        {
          try
          {
        	  settingsFile.createNewFile();
          }
          catch (IOException e)
          {
            e.printStackTrace();
          }
          settings = new Settings();
          settings.save(settingsFile);
        }
        else
        {
          settings = Settings.load(settingsFile);
          if (settings == null) 
          {
            settings = new Settings();
          }
          settings.save(settingsFile);
        }
        if(settings.getVeins().isEmpty())
        {
        	settings.addVein(new Vein("Default vein"));
        	settings.setSelectedVein(0);
        	settings.save(settingsFile);
        }
        saveConfiguration();
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
    	Listener listener = new Listener();
    	MinecraftForge.EVENT_BUS.register(listener);
    	FMLCommonHandler.instance().bus().register(listener);
    	
    	ClientRegistry.registerKeyBinding(DiamondMarker.guiBinding = new KeyBinding("Diamond marker settings", Keyboard.KEY_L, "diamondMarker"));
    	saveConfiguration();
    }

    public static void saveConfiguration()
    {
      settings.save(settingsFile);
    }
}
