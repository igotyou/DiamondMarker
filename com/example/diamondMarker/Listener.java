package com.example.diamondMarker;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

import com.example.diamondMarker.MinedOre.Type;
import com.example.diamondMarker.gui.GuiDiamondMarkerSettings;
import com.example.diamondMarker.utils.Location;

import cpw.mods.fml.common.Mod;
import net.minecraftforge.client.event.MouseEvent;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.world.BlockEvent;

public class Listener 
{
	
	private static Queue queue;
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
			if(DiamondMarker.settings.getEnabled())
			{
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
					}
					if(queue != null)
					{
						for (int i = queue.size()-1; i >= 0; i--)
						{
							Packet packet = (Packet) queue.toArray()[i];
							if(packet.getClass() == S23PacketBlockChange.class)
							{
								S23PacketBlockChange blockChange = (S23PacketBlockChange) packet;
								int x = blockChange.func_148879_d();
								int y = blockChange.func_148878_e();
								int z = blockChange.func_148877_f();
								Location location = new Location(x, y, z, mc.theWorld.provider.dimensionId);
								System.out.println(location.toString());
								Block block = blockChange.func_148880_c();
								Type blockType = Type.getType(Block.getIdFromBlock(block));
								if(Block.getIdFromBlock(block) == 0)
								{
									for(int i2 = 0; i2 < tempOres.size(); i2++)
									{
										MinedOre ore = tempOres.get(i2);
										if(ore.getLocation().equals(location))
										{
											DiamondMarker.settings.getCurrentVein().addOre(ore);
											tempOres.remove(i2);
											DiamondMarker.saveConfiguration();
										}
									}
								}
								else if(blockType != null)
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
										System.out.println();
										MinedOre toAdd = new MinedOre(location,blockType);
										tempOres.add(toAdd);
									}
								}
							}
						}
					}
				}
			}
		}
	}		
	
	@SubscribeEvent
	public void onRenderWorldLast(RenderWorldLastEvent event)
	{
		if(queue == null)
		{
			try 
			{
				System.out.println("queue is null, initialising");
				Field f = mc.getNetHandler().getNetworkManager().getClass().getDeclaredField("field_150748_i");
				f.setAccessible(true);
				queue = (Queue) f.get(mc.getNetHandler().getNetworkManager());
			} 
			catch (Exception e) 
			{
				e.getStackTrace();
			}
		}
	}
}
