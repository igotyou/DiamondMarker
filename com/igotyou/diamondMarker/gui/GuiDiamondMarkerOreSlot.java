package com.igotyou.diamondMarker.gui;

import java.util.Collection;
import java.util.Map.Entry;
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

public class GuiDiamondMarkerOreSlot extends GuiSlot
{
	private final GuiDiamondMarkerOre slotParent;
	protected int selectedIndex = 1;
	private FontRenderer fontRenderer;
	private RenderItem renderItem;
	private TextureManager renderEngine = Minecraft.getMinecraft().renderEngine;
	private Tessellator tess;
	private Vein vein;
	private Type type;
	
	public GuiDiamondMarkerOreSlot(GuiDiamondMarkerOre slotParent, Vein veinToDisplay, Type type) 
	{
		super(Minecraft.getMinecraft(), slotParent.width , slotParent.height , 50, slotParent.height-60, 32);
		this.fontRenderer = Minecraft.getMinecraft().fontRenderer;
		this.renderItem = new RenderItem();
		this.slotParent = slotParent;
		this.tess = Tessellator.instance;
		this.vein = veinToDisplay;
		this.type = type;
	}

	protected int getSize() 
	{
		return this.vein.getOresByY(type).size();
	}

	protected int getContentHeight()
	  {
	    return getSize() * 32;
	  }
	
	protected void elementClicked(int var1, boolean var2, int var3, int var4) 
	{
		if(var1 < this.vein.getOrderMap().size() && var1 >= 0)
		{
			if(var2)
			{
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
		if(var1 <  this.getSize() && var1 >= 0)
		{
			Set<Integer> ySet = vein.getOresByY(type).keySet();
			Collection<Integer> valueSet = vein.getOresByY(type).values();
			int y = (Integer) ySet.toArray()[var1];
			String oreAmount = valueSet.toArray()[var1] + " " + type.toString();
			String outputString = "Y " + String.valueOf(y) + ": " + oreAmount; 
			int stringWidth = fontRenderer.getStringWidth(outputString);
			
			GL11.glEnable(32826);
			RenderHelper.enableGUIStandardItemLighting();
			this.drawItemStack(var2, var3, new ItemStack(Block.getBlockById(type.getId())));
			RenderHelper.disableStandardItemLighting();
			GL11.glDisable(32826);
			
			this.slotParent.drawString(this.fontRenderer, outputString, this.slotParent.width/2-(stringWidth/2), var3, 3333333);
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
