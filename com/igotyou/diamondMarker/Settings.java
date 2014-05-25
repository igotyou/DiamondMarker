package com.igotyou.diamondMarker;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.igotyou.diamondMarker.utils.Location;

public class Settings 
{
	private boolean enabled;
	private List<Vein> veins;
	private int currentVein;
	
	public Settings()
	{
		veins = new ArrayList<Vein>();
		enabled = true;
	}
	
	public void addVein(Vein vein)
	{
		veins.add(vein);
	}
	
	public void removeVein(int index)
	{
		veins.remove(index);
	}
	
	public void removeVein(Vein vein)
	{
		veins.remove(vein);
	}
	
	public Vein getCurrentVein()
	{
		return veins.get(currentVein);
	}
	
	public int getCurrentIndex()
	{
		return currentVein;
	}
	
	public void setSelectedVein(int index)
	{
		currentVein = index;
	}
	
	public int getVeinListSize()
	{
		return veins.size();
	}
	
	public List<Vein> getVeins()
	{
		return veins;
	}
	
	public boolean getEnabled()
	{
		return enabled;
	}
	
	public void setEnabled(boolean value)
	{
		enabled = value;
	}
	
	public void save(File file)
	{
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		try
		{
			String json = gson.toJson(this);
			
			FileWriter writer = new FileWriter(file);
			writer.write(json);
			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static Settings load(File file)
	{
		Gson gson = new Gson();
	    try
	    {
	      return (Settings)gson.fromJson(new FileReader(file), Settings.class);
	    }
	    catch (Exception e)
	    {
	      e.printStackTrace();
	    }
	    return new Settings();
	}
}
