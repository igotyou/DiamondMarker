package com.example.diamondMarker.gui;

import com.example.diamondMarker.DiamondMarker;
import com.example.diamondMarker.Settings;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiDiamondMarkerSettings extends GuiScreen
{
	private GuiButton enableButton;
	
	private Settings settings;
	
	public GuiDiamondMarkerSettings()
	{
		this.settings = DiamondMarker.settings;
	}
	
	public void initGui()
	{
		this.buttonList.add(new GuiButton(1, this.width/2-100, 60, 100, 20, "Veins"));
		if(settings.getEnabled())
		{
			this.buttonList.add(enableButton = new GuiButton(2, this.width/2, 60, 100, 20, "Disable"));
		}
		else if(!settings.getEnabled())
		{
			this.buttonList.add(enableButton = new GuiButton(2, this.width/2, 60, 100, 20, "Enable"));
		}
		this.buttonList.add(new GuiButton(100, this.width/2-100, this.height-20, 200, 20, "Exit"));
	}
	
	@Override
	public void drawScreen(int x, int y, float f)
	{
		drawCenteredString(this.fontRendererObj, "Diamond marker settings", this.width / 2, 15, 3333333);
		super.drawScreen(x,y,f);
	}
	
	public void actionPerformed(GuiButton button)
	{
		if (button.enabled)
		{
			if(button.id==1)
			{
				this.mc.displayGuiScreen(new GuiDiamondMarkerVeins(this));
			}
			else if(button.id==2)
			{
				if(settings.getEnabled())
				{
					enableButton.displayString = "Enable";
					settings.setEnabled(false);
					DiamondMarker.saveConfiguration();	
				}
				else if(!settings.getEnabled())
				{
					enableButton.displayString = "Disable";
					settings.setEnabled(true);
					DiamondMarker.saveConfiguration();	
				}
			}
			else if(button.id==100)
			{
				this.mc.displayGuiScreen(null);
			}
		}
	}
}
