package com.igotyou.diamondMarker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.igotyou.diamondMarker.MinedOre.Type;
import com.igotyou.diamondMarker.utils.Location;

public class Vein 
{
	private List<MinedOre> ores;
	private String name;
	
	public Vein(String name)
	{
		this.name = name;
		ores = new ArrayList<MinedOre>();
	}
	
	public void addOre(MinedOre ore)
	{
		ores.add(ore);
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getOresInVein()
	{
		return ores.size();
	}
	
	public int getOreHeight()
	{
		int lowestY = 0;
		int highestY = 0;
		for(MinedOre ore : ores)
		{
			if(lowestY > ore.getLocation().getY() || lowestY == 0)
			{
				lowestY = ore.getLocation().getY();
			}
			if(highestY < ore.getLocation().getY() || highestY == 0)
			{
				highestY = ore.getLocation().getY();
			}
		}
		return highestY-lowestY;
	}
	
	public int getOreHeight(Type type)
	{
		int lowestY = 0;
		int highestY = 0;
		for(MinedOre ore : ores)
		{
			if(ore.getBlockType() == type)
			{
				if(lowestY > ore.getLocation().getY() || lowestY == 0)
				{
					lowestY = ore.getLocation().getY();
				}
				if(highestY < ore.getLocation().getY() || highestY == 0)
				{
					highestY = ore.getLocation().getY();
				}	
			}
		}
		return highestY-lowestY;
	}
	
	public TreeMap<Type, Integer> getOrderMap()
	{
		HashMap<Type, Integer> tempMap = new HashMap<Type, Integer>();
		ValueComparator bvc =  new ValueComparator(tempMap);
		TreeMap<Type, Integer> resultMap = new TreeMap<Type, Integer>(bvc);

		for(MinedOre ore : ores)
		{
			if(tempMap.get(ore.getBlockType()) != null)
			{
				tempMap.put(ore.getBlockType(), tempMap.get(ore.getBlockType())+1);
			}
			else
			{
				tempMap.put(ore.getBlockType(), 1);
			}
		}
		
		resultMap.putAll(tempMap);
		
		return resultMap;
	}
	
	public HashMap<Integer, Integer> getOresByY(Type type)
	{
		HashMap<Integer, Integer> tempMap = new HashMap<Integer, Integer>();

		for(MinedOre ore : ores)
		{
			if(ore.getBlockType() == type)
			{
				int y = ore.getLocation().getY();
				int amount = 0;
				if(tempMap.get(y) != null)
				{
					amount = tempMap.get(y);
				}
				tempMap.put(y, amount+1);
			}
		}
		return tempMap;
	}
	
	public List<MinedOre> getOresOfType(Type type)
	{
		List<MinedOre> tempOres = new ArrayList<MinedOre>();
		for(MinedOre ore : ores)
		{
			if(ore.getBlockType() == type)
			{
				tempOres.add(ore);
			}
		}
		return tempOres;
	}
	
	public List<MinedOre> getOresOfType(Type type, int y)
	{
		List<MinedOre> tempOres = new ArrayList<MinedOre>();
		for(MinedOre ore : ores)
		{
			if(ore.getBlockType() == type && ore.getLocation().getY() == y)
			{
				tempOres.add(ore);
			}
		}
		return tempOres;
	}

	public Location getAverageLocation()
	{
		Location location = new Location(0,0,0);
		int i;
		for(i=0;i<ores.size();i++)
		{
			location = location.add(ores.get(i).getLocation());
		}
		location = location.divide(i+1);
		return location;
	}
	
	public Location getAverageLocation(Type type)
	{
		Location location = new Location(0,0,0);
		int i2 = 0;
		for(int i=0;i<ores.size();i++)
		{
			if(ores.get(i).getBlockType()==type)
			{
				i2++;
				location = location.add(ores.get(i).getLocation());
			}
		}
		location = location.divide(i2);
		return location;
	}
	
	public int getRadiusFromLocation(Location location)
	{
		int distance = 0;
		for(MinedOre ore : ores)
		{
			if(ore.getLocation().getDistance(location)>distance)
			{
				distance = Math.round(ore.getLocation().getDistance(location));
			}
		}
		return distance;
	}
	
	public float orePerSquareMeter(int radius,Type type)
	{
		return (float) (getOresOfType(type).size()/(Math.PI*(radius*radius)));
	}
	
	private class ValueComparator implements Comparator<Type> 
	{

	    Map<Type, Integer> base;
	    public ValueComparator(Map<Type, Integer> base) {
	        this.base = base;
	    }
	    
	    public int compare(Type a, Type b) {
	        if (base.get(a) >= base.get(b)) {
	            return -1;
	        } else {
	            return 1;
	        }
	    }
	}
}

