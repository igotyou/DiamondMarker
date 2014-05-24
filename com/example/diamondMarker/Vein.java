package com.example.diamondMarker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.example.diamondMarker.MinedOre.Type;
import com.example.diamondMarker.utils.Location;

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

