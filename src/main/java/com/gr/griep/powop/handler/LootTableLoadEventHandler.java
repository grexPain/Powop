package com.gr.griep.powop.handler;

import com.gr.griep.powop.helper.LootHelper;

import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class LootTableLoadEventHandler {
	
    @SubscribeEvent
    @SuppressWarnings("unused")
    public void onLootTableLoad(LootTableLoadEvent event) {

    	LootHelper.injectCustomLoot(event);
    }
    
}
