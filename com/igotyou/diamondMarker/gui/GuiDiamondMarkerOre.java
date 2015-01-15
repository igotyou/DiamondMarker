package com.igotyou.diamondMarker.gui;

import org.lwjgl.input.Keyboard;

import com.igotyou.diamondMarker.DiamondMarker;
import com.igotyou.diamondMarker.MinedOre.Type;
import com.igotyou.diamondMarker.Settings;
import com.igotyou.diamondMarker.Vein;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

public class GuiDiamondMarkerOre extends GuiScreen
{
	private GuiScreen previousScreen;
	protected Settings settings;
	private Vein vein;
	private Type type;
	
	private GuiButton informationButton;
	private GuiDiamondMarkerOreSlot slotContianer;
	
	public GuiDiamondMarkerOre(GuiScreen previousScreen, Vein veinToDisplay, Type type)
	{
		this.previousScreen = previousScreen;
		this.settings = DiamondMarker.settings;
		this.vein = veinToDisplay;
		this.type = type;
	}
	
	public void initGui()
	{
		this.buttonList.add(this.informationButton = new GuiButton(1, this.width / 2 - 50, 15, 100, 20, "Statistics"));
		this.buttonList.add(new GuiButton(100, this.width/2-100, this.height-20, 200, 20, "Exit"));
		
		this.slotContianer = new GuiDiamondMarkerOreSlot(this, vein, type);
		System.out.println(vein.getAverageLocation(type).toString());
	}
	
	public void actionPerformed(GuiButton button)
	{
		if (button.enabled)
		{
			if(button.id==1)
			{
				this.mc.displayGuiScreen(new GuiDiamondMarkerStatistics(this, vein, type));
			}
			else if(button.id==100)
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
		super.drawScreen(x,y,f);
	}
}
