package com.igotyou.diamondMarker;

import java.util.Date;

import com.igotyou.diamondMarker.utils.Location;

public class MinedOre 
{
	public static enum Type {QUARTZ(153), EMERALD(129), REDSTONE(73), DIAMOND(56), LAPIS(21), COAL(16), IRON(15), GOLD(14);
	
	private int id;
	
    private Type(int id) {
            this.id = id;
    }	
    
    public int getId(){return id;}
    public boolean compare(int i){return id == i;}
    public static Type getType(int _id)
    {
        Type[] As = Type.values();
        for(int i = 0; i < As.length; i++)
        {
            if(As[i].compare(_id))
                return As[i];
        }
        return null;
    }    
    
	};
	private Location location;
	private Type type;
	private Date date;
	
	public MinedOre(Location location, Type type)
	{
		this.location = location;
		this.type = type;
		this.date = new Date();
	}
	
	public Location getLocation()
	{
		return location;
	}
	
	public Type getBlockType()
	{
		return type;
	}
	
	public Date getDate()
	{
		return date;
	}
	
	public String toString()
	{
		return location.toString() + " type:" + type.toString() + " date:" + date.toGMTString();
	}
}
