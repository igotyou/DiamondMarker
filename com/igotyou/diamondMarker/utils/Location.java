package com.igotyou.diamondMarker.utils;

public class Location 
{
	private int x;
	private int y;
	private int z;
	private int dimensionId;
	
	public Location(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.dimensionId = 1;
	}
	
	public Location(int x, int y, int z, int dimensionId)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.dimensionId = dimensionId;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getZ()
	{
		return z;
	}
	
	public int getDimensionId()
	{
		return dimensionId;
	}
	
	public boolean equals(Location otherLocation)
	{
		if (otherLocation.x == x && otherLocation.y == y && otherLocation.z == z && otherLocation.dimensionId == this.dimensionId)
		{
			return true;
		}
		return false;
	}
	
	public boolean equals(int x, int y, int z, int dimensionId)
	{
		if (x == this.x && y == this.y && z == this.z && dimensionId == this.dimensionId)
		{
			return true;
		}
		return false;
	}
	
	public static Location subtract(Location location1, Location location2)
	{
		int tempX = location1.getX() - location2.getX();
		int tempY = location1.getY() - location2.getY();
		int tempZ = location1.getZ() - location2.getZ();
		return new Location(tempX, tempY, tempZ);
	}
	
	public static Location add(Location location1, Location location2)
	{
		int tempX = location1.getX() + location2.getX();
		int tempY = location1.getY() + location2.getY();
		int tempZ = location1.getZ() + location2.getZ();
		return new Location(tempX, tempY, tempZ);
	}
	
	public static Location multiply(Location location1, Location location2)
	{
		int tempX = location1.getX() * location2.getX();
		int tempY = location1.getY() * location2.getY();
		int tempZ = location1.getZ() * location2.getZ();
		return new Location(tempX, tempY, tempZ);
	}
	
	public static Location multiply(Location location1, float number)
	{
		int tempX = Math.round(location1.getX() * number);
		int tempY = Math.round(location1.getY() * number);
		int tempZ = Math.round(location1.getZ() * number);
		return new Location(tempX, tempY, tempZ);
	}
	
	public static Location divide(Location location1, Location location2)
	{
		int tempX = location1.getX() / location2.getX();
		int tempY = location1.getY() / location2.getY();
		int tempZ = location1.getZ() / location2.getZ();
		return new Location(tempX, tempY, tempZ);
	}
	
	public static Location divide(Location location1, float number)
	{
		int tempX = Math.round(location1.getX() / number);
		int tempY = Math.round(location1.getY() / number);
		int tempZ = Math.round(location1.getZ() / number);
		return new Location(tempX, tempY, tempZ);
	}
	
	public static float getDistance(Location location1, Location location2)
	{
		return (float) Math.sqrt(((location2.getX()-location1.getX())*(location2.getX()-location1.getX())) + ((location2.getY()-location1.getY())*(location2.getY()-location1.getY())) + ((location2.getZ()-location1.getZ())*(location2.getZ()-location1.getZ())));
	}
	
	public Location subtract(Location otherLocation)
	{
		int tempX = x - otherLocation.getX();
		int tempY = y - otherLocation.getY();
		int tempZ = z - otherLocation.getZ();
		return new Location(tempX, tempY, tempZ);
	}
	
	public Location add(Location otherLocation)
	{
		int tempX = x + otherLocation.getX();
		int tempY = y + otherLocation.getY();
		int tempZ = z + otherLocation.getZ();
		return new Location(tempX, tempY, tempZ);
	}
	
	public Location multiply(Location otherLocation)
	{
		int tempX = x * otherLocation.getX();
		int tempY = y * otherLocation.getY();
		int tempZ = z * otherLocation.getZ();
		return new Location(tempX, tempY, tempZ);
	}
	
	public Location multiply(float number)
	{
		int tempX = Math.round(x * number);
		int tempY = Math.round(y * number);
		int tempZ = Math.round(z * number);
		return new Location(tempX, tempY, tempZ);
	}
	
	public Location divide(Location otherLocation)
	{
		int tempX = x / otherLocation.getX();
		int tempY = y / otherLocation.getY();
		int tempZ = z / otherLocation.getZ();
		return new Location(tempX, tempY, tempZ);
	}
	
	public Location divide(float number)
	{
		int tempX = Math.round(x / number);
		int tempY = Math.round(y / number);
		int tempZ = Math.round(z / number);
		return new Location(tempX, tempY, tempZ);
	}
	
	public float getDistance(Location otherLocation)
	{
		return (float) Math.sqrt( ((otherLocation.getX()-x)*(otherLocation.getX()-x)) + ((otherLocation.getY()-y)*(otherLocation.getY()-y)) + ((otherLocation.getZ()-z)*(otherLocation.getZ()-z)) );
	}
	
	public String toString()
	{
		return "world: " + dimensionId + " X: " + x + " Y: " + y + " Z: " + z;
	}

	public void setY(int y) 
	{
		this.y = y;
	}
}
