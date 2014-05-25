package com.igotyou.diamondMarker;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import com.igotyou.diamondMarker.MinedOre.Type;
import com.igotyou.diamondMarker.gui.GuiDiamondMarkerSettings;
import com.igotyou.diamondMarker.utils.Location;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class Listener 
{
	
	private static Minecraft mc;
	private List<MinedOre> tempOres;
	
	public Listener()
	{
		mc = Minecraft.getMinecraft();
		tempOres = new ArrayList<MinedOre>();
	}
	
	@SubscribeEvent
	public void onTick(TickEvent.ClientTickEvent event)
	{
		if(event.phase == TickEvent.Phase.START)
		{
			if(DiamondMarker.guiBinding.getIsKeyPressed() && mc.inGameHasFocus)
			{
				mc.displayGuiScreen(new GuiDiamondMarkerSettings());
			}
			synchronized(tempOres)
			{
				for(int i = 0; i < tempOres.size(); i++)
				{
					MinedOre ore = tempOres.get(i);
					Date now = new Date();
					if((TimeUnit.MILLISECONDS.toSeconds(now.getTime() - ore.getDate().getTime())) >= 10)
					{
						tempOres.remove(i);
					}
					Location location = ore.getLocation();
					if(Block.getIdFromBlock(mc.theWorld.getBlock(location.getX(), location.getY(), location.getZ())) == 0)
					{
						DiamondMarker.settings.getCurrentVein().addOre(ore);
						tempOres.remove(i);
						DiamondMarker.saveConfiguration();
					}
				}	
			}
		}
	}		
	@SubscribeEvent
	public void onBreakSpeed(PlayerEvent.BreakSpeed event)
	{
		if(DiamondMarker.settings.getEnabled())
		{
			synchronized(tempOres)
			{
				int x = event.x;
				int y = event.y;
				int z = event.z;
				Location location = new Location(x, y, z, mc.theWorld.provider.dimensionId);
				Block block = event.block;
				int blockId = block.getIdFromBlock(block);
				if(blockId == 74){blockId = 73;}
				Type blockType = Type.getType(blockId);
				if(blockType != null)
				{
					boolean found = false;
					for(int i2 = 0; i2 < tempOres.size(); i2++)
					{
						MinedOre ore = tempOres.get(i2);
						if(ore.getLocation().equals(location))
						{
							found = true;
							break;
						}
					}
					if(!found)
					{
						MinedOre toAdd = new MinedOre(location,blockType);
						tempOres.add(toAdd);
					}
				}
			}
		}
	}
}
