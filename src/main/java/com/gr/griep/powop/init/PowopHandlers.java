package com.gr.griep.powop.init;

import com.gr.griep.powop.PowopMod;
import com.gr.griep.powop.handler.LootTableLoadEventHandler;
import com.gr.griep.powop.handler.PowopEventHandler;
import com.gr.griep.powop.handler.PowopGuiHandler;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class PowopHandlers {

	public static void registerHandlers(){
		NetworkRegistry.INSTANCE.registerGuiHandler(PowopMod.INSTANCE, new PowopGuiHandler());
		FMLCommonHandler.instance().bus().register(new PowopEventHandler());
		FMLCommonHandler.instance().bus().register(new LootTableLoadEventHandler());
	}
	
}
