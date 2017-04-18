package com.gr.griep.powop.init;

import com.gr.griep.powop.tileentity.TileEntityOreDictExchanger;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class PowopTileEntities {
	
	public static final String ID_TILE_ENTITY_ORE_EXCHANGER = "powop:ore_exchanger";
	
	public static void init() {
		GameRegistry.registerTileEntity(TileEntityOreDictExchanger.class, ID_TILE_ENTITY_ORE_EXCHANGER);
	}

}
