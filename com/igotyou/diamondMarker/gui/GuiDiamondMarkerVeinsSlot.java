package com.igotyou.diamondMarker.gui;

import java.util.Set;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import com.igotyou.diamondMarker.DiamondMarker;
import com.igotyou.diamondMarker.Vein;
import com.igotyou.diamondMarker.MinedOre.Type;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererChestHelper;
import net.minecraft.item.ItemStack;

public class GuiDiamondMarkerVeinsSlot extends GuiSlot
{
	private final GuiDiamondMarkerVeins previousScreen;
	protected int selectedIndex = -1;
	private FontRenderer fontRenderer;
	private RenderItem renderItem;
	private TextureManager renderEngine = Minecraft.getMinecraft().renderEngine;
	private Tessellator tess;
	
	public GuiDiamondMarkerVeinsSlot(GuiDiamondMarkerVeins previousScreen) 
	{
		super(Minecraft.getMinecraft(), previousScreen.width , previousScreen.height , 50, previousScreen.height-60, 32);
		this.fontRenderer = Minecraft.getMinecraft().fontRenderer;
		this.renderItem = new RenderItem();
		this.previousScreen = previousScreen;
		this.selectedIndex = this.previousScreen.settings.getCurrentIndex();
		this.tess = Tessellator.instance;
	}

	protected int getSize() 
	{
		return previousScreen.settings.getVeinListSize();
	}

	protected int getContentHeight()
	  {
	    return getSize() * 32;
	  }
	
	protected void elementClicked(int var1, boolean var2, int var3, int var4) 
	{
		if(var1 < previousScreen.settings.getVeinListSize() && var1 >= 0)
		{
			if(var2)
			{
				this.previousScreen.mc.displayGuiScreen(new GuiDiamondMarkerVein(this.previousScreen, this.previousScreen.settings.getVeins().get(var1)));
			}
			else
			{
				this.selectedIndex = var1;
			}
		}
	}

	protected boolean isSelected(int var1) 
	{
		return var1 == this.selectedIndex;
	}
	
	protected void drawSlot(int var1, int var2, int var3, int var4, Tessellator tessellator, int var6, int var7)
	{
		if(var1 < previousScreen.settings.getVeinListSize() && var1 >= 0)
		{
			Vein vein = previousScreen.settings.getVeins().get(var1);
			String veinName = vein.getName();
			String oresInVein = String.valueOf(vein.getOresInVein()) + " ores";
			int stringWidth = fontRenderer.getStringWidth(veinName);
			int string2Width = fontRenderer.getStringWidth(oresInVein);
			Set<Type> oreSet = vein.getOrderMap().keySet();
			if (!oreSet.isEmpty())
			{
				GL11.glEnable(32826);
				RenderHelper.enableGUIStandardItemLighting();
				this.drawItemStack(var2, var3, new ItemStack(Block.getBlockById((((Type) oreSet.toArray()[0]).getId()))));
				RenderHelper.disableStandardItemLighting();
				GL11.glDisable(32826);
			}
			this.previousScreen.drawString(this.fontRenderer, veinName, this.previousScreen.width/2-(stringWidth/2), var3, 3333333);
			this.previousScreen.drawString(this.fontRenderer, oresInVein, this.previousScreen.width/2-(string2Width/2), var3+12, 16777215);
		}
	}

	protected void drawBackground() {}
	
	private void drawItemStack(int x, int y, ItemStack itemStack)
	{
		drawItemStackSlot(x, y);
	    if ((itemStack != null) && (itemStack.getItem() != null))
	    {
	    	GL11.glEnable(32826);
	    	RenderHelper.enableGUIStandardItemLighting();
	    	this.renderItem.renderItemAndEffectIntoGUI(this.fontRenderer, this.renderEngine, itemStack, x + 2, y + 2);
	    	RenderHelper.disableStandardItemLighting();
	    	GL11.glDisable(32826);
	    }
	  }
	  
	  private void drawItemStackSlot(int x, int y)
	  {
	    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	    this.renderEngine.bindTexture(Gui.statIcons);
	    tess.startDrawingQuads();
	    tess.addVertexWithUV(x + 1 + 0, y + 1 + 18, 0.0D, 0.0D, 0.140625D);
	    tess.addVertexWithUV(x + 1 + 18, y + 1 + 18, 0.0D, 0.140625D, 0.140625D);
	    tess.addVertexWithUV(x + 1 + 18, y + 1 + 0, 0.0D, 0.140625D, 0.0D);
	    tess.addVertexWithUV(x + 1 + 0, y + 1 + 0, 0.0D, 0.0D, 0.0D);
	    tess.draw();
	  }
}
