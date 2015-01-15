package com.igotyou.diamondMarker.gui;

import org.lwjgl.opengl.GL11;

import com.igotyou.diamondMarker.DiamondMarker;
import com.igotyou.diamondMarker.Settings;
import com.igotyou.diamondMarker.Vein;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.Tessellator;

public class GuiDiamondMarkerVeins extends GuiScreen
{
	private GuiScreen previousScreen;
	private GuiDiamondMarkerVeinsSlot slotContianer;
	private GuiButton addVein;
	private GuiButton selectVein;
	private GuiButton editVein;
	private GuiButton deleteVein;
	protected Settings settings;
	
	
	public GuiDiamondMarkerVeins(GuiScreen previousScreen)
	{
		this.previousScreen = previousScreen;
		this.settings = DiamondMarker.settings;
	}
	
	public void initGui()
	{
		this.buttonList.add(this.addVein = new GuiButton(1, this.width/2-100, this.height-60,100,20,"Add"));
		this.buttonList.add(this.selectVein = new GuiButton(2, this.width/2, this.height-60,100,20,"Select"));
		this.buttonList.add(this.addVein = new GuiButton(3, this.width/2-100, this.height-40,100,20,"Delete"));
		this.buttonList.add(this.selectVein = new GuiButton(4, this.width/2, this.height-40,100,20,"Edit"));
		this.buttonList.add(new GuiButton(100, this.width/2-100, this.height-20, 200, 20, "Done"));
		this.slotContianer = new GuiDiamondMarkerVeinsSlot(this);
	}
	
	public void actionPerformed(GuiButton button)
	{
		if (button.enabled)
		{
			if(button.id == 1)
			{
				Vein vein = new Vein(String.valueOf(settings.getVeins().size()));
				settings.addVein(vein);
				DiamondMarker.saveConfiguration();
			}
			else if(button.id == 2)
			{
				settings.setSelectedVein(this.slotContianer.selectedIndex);
				DiamondMarker.saveConfiguration();
			}
			else if(button.id == 3)
			{
				if(settings.getVeinListSize()>1)
				{
					settings.removeVein(this.slotContianer.selectedIndex);
					if(this.slotContianer.selectedIndex == 0)
					{
						settings.setSelectedVein(0);
					}
					else
					{
						settings.setSelectedVein(this.slotContianer.selectedIndex-1);
					}
					DiamondMarker.saveConfiguration();	
				}
			}
			else if(button.id == 4)
			{
				this.previousScreen.mc.displayGuiScreen(new GuiDiamondMarkerVein(this, this.settings.getVeins().get(slotContianer.selectedIndex)));	
			}
			else if(button.id==100)
			{
				mc.displayGuiScreen(previousScreen);
			}
		}
	}
	
	@Override
	public void drawScreen(int x, int y, float f)
	{
		this.slotContianer.drawScreen(x, y, f);
		drawCenteredString(this.fontRendererObj, "Veins", this.width / 2, 15, 3333333);
		drawCenteredString(this.fontRendererObj, "Current vein: " + settings.getCurrentVein().getName(), this.width/2, 32, 16777215);
		super.drawScreen(x,y,f);
	}
}
