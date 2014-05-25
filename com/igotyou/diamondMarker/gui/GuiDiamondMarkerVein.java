package com.igotyou.diamondMarker.gui;

import org.lwjgl.input.Keyboard;

import com.igotyou.diamondMarker.DiamondMarker;
import com.igotyou.diamondMarker.Settings;
import com.igotyou.diamondMarker.Vein;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

public class GuiDiamondMarkerVein extends GuiScreen
{
	private GuiScreen previousScreen;
	protected Settings settings;
	private Vein vein;
	
	private GuiButton renameButton;
	private GuiTextField veinName;
	private GuiDiamondMarkerVeinSlot slotContianer;
	
	public GuiDiamondMarkerVein(GuiScreen previousScreen, Vein veinToDisplay)
	{
		this.previousScreen = previousScreen;
		this.settings = DiamondMarker.settings;
		this.vein = veinToDisplay;
	}
	
	public void initGui()
	{
		Keyboard.enableRepeatEvents(true);
		
		veinName = new GuiTextField(this.fontRendererObj, this.width/2 -100, 15, 200, 20);
		veinName.setFocused(true);
		veinName.setText(vein.getName());
		
		this.buttonList.add(this.renameButton = new GuiButton(1, this.width / 2 + 110, 15, 100, 20, "Rename"));
		this.buttonList.add(new GuiButton(100, this.width/2-100, this.height-20, 200, 20, "Exit"));
		this.slotContianer = new GuiDiamondMarkerVeinSlot(this,vein);
	}
	
	public void actionPerformed(GuiButton button)
	{
		if (button.enabled)
		{
			if(button.id==1)
			{
				this.vein.setName(this.veinName.getText().trim());
				DiamondMarker.saveConfiguration();
			}
			else if(button.id==100)
			{
				this.mc.displayGuiScreen(this.previousScreen);
			}
		}
	}
	
	protected void keyTyped(char par1, int par2)
	{
		if(this.veinName.isFocused())
		{
			this.veinName.textboxKeyTyped(par1, par2);
		}
		if(par2 == 28)
		{
			if(!this.veinName.getText().trim().isEmpty())
			{
				this.vein.setName(this.veinName.getText().trim());
				DiamondMarker.saveConfiguration();
			}
		}
	}
	
	public void updateScreen()
	{
		this.veinName.updateCursorCounter();
	}
	
	protected void mouseClicked(int par1, int par2, int par3)
	{
		super.mouseClicked(par1, par2, par3);
		this.veinName.mouseClicked(par1, par2, par3);
	}
	
	public void drawScreen(int x, int y, float f)
	{
		this.slotContianer.drawScreen(x, y, f);
		this.veinName.drawTextBox();
		super.drawScreen(x,y,f);
	}
}
