package com.gr.griep.powop.init;

import net.minecraftforge.fml.common.registry.GameRegistry;

import com.gr.griep.powop.tileentity.TileEntityCoordTransporter;


public class TeleportTileEntities {
	
	public static void register() {
		
		GameRegistry.registerTileEntity(TileEntityCoordTransporter.class, "tmCoordTransporter");
	}

}
