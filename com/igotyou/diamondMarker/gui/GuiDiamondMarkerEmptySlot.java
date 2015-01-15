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
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererChestHelper;
import net.minecraft.item.ItemStack;

public class GuiDiamondMarkerEmptySlot extends GuiSlot
{
	private final GuiScreen slotParent;
	
	public GuiDiamondMarkerEmptySlot(GuiScreen slotParent) 
	{
		super(Minecraft.getMinecraft(), slotParent.width , slotParent.height , 50, slotParent.height-60, 32);
		this.slotParent = slotParent;	
	}

	protected int getSize() 
	{
		return 0;
	}

	protected int getContentHeight()
	  {
	    return getSize() * 32;
	  }

	@Override
	protected void elementClicked(int p_148144_1_, boolean p_148144_2_,
			int p_148144_3_, int p_148144_4_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isSelected(int p_148131_1_) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void drawBackground() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void drawSlot(int p_148126_1_, int p_148126_2_, int p_148126_3_,
			int p_148126_4_, Tessellator p_148126_5_, int p_148126_6_,
			int p_148126_7_) {
		// TODO Auto-generated method stub
		
	}
}
