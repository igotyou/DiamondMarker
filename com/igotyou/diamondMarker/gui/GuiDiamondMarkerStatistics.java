package com.igotyou.diamondMarker.gui;

import org.lwjgl.input.Keyboard;

import com.igotyou.diamondMarker.DiamondMarker;
import com.igotyou.diamondMarker.MinedOre.Type;
import com.igotyou.diamondMarker.Settings;
import com.igotyou.diamondMarker.Vein;
import com.igotyou.diamondMarker.utils.Location;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.gui.GuiTextField;

public class GuiDiamondMarkerStatistics extends GuiScreen
{
	private GuiScreen previousScreen;
	private FontRenderer fontRenderer;
	private GuiDiamondMarkerEmptySlot slotContianer;
	protected Settings settings;
	private Vein vein;
	private Type type;
	
	private GuiButton informationButton;
	
	public GuiDiamondMarkerStatistics(GuiScreen previousScreen, Vein veinToDisplay, Type type)
	{
		this.fontRenderer = Minecraft.getMinecraft().fontRenderer;
		this.previousScreen = previousScreen;
		this.settings = DiamondMarker.settings;
		this.vein = veinToDisplay;
		this.type = type;
	}
	
	public void initGui()
	{
		this.buttonList.add(new GuiButton(100, this.width/2-100, this.height-20, 200, 20, "Exit"));
		
		this.slotContianer = new GuiDiamondMarkerEmptySlot(this);
	}
	
	public void actionPerformed(GuiButton button)
	{
		if (button.enabled)
		{
			if(button.id==100)
			{
				this.mc.displayGuiScreen(this.previousScreen);
			}
		}
	}
		
	protected void mouseClicked(int par1, int par2, int par3)
	{
		super.mouseClicked(par1, par2, par3);
	}
	
	public void drawScreen(int x, int y, float f)
	{
		this.slotContianer.drawScreen(x, y, f);
		Location averageLocation = vein.getAverageLocation(type);
		int radius = vein.getRadiusFromLocation(averageLocation);
		float density = vein.orePerSquareMeter(radius,type);
		drawCenteredString(this.fontRendererObj, "Statistics", this.width / 2, 15, 3333333);
		drawCenteredString(this.fontRendererObj, "Average location: " + averageLocation.toStringNoWorld(), this.width / 2, 100, 3333333);
		drawCenteredString(this.fontRendererObj, "Radius: " + radius, this.width / 2, 120, 3333333);
		drawCenteredString(this.fontRendererObj, "Density(" + type.toString() + " per square meter): " + density, this.width / 2, 140, 3333333);
		super.drawScreen(x,y,f);
	}
}
