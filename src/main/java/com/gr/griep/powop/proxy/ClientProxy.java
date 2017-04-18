package com.gr.griep.powop.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import com.gr.griep.powop.entity.EntityImpactGrenade;
import com.gr.griep.powop.init.PowopBlocks;
import com.gr.griep.powop.init.PowopItems;

public class ClientProxy extends CommonProxy {

	
	@Override
	public void registerRenders() {
		
		PowopItems.registerRenders();
		PowopBlocks.registerRenders();
	}
	
	@Override
	public void registerRenderingHandlers(){
		RenderingRegistry.registerEntityRenderingHandler(EntityImpactGrenade.class, new IRenderFactory<EntityImpactGrenade>() {
			
			@Override
			public Render<? super EntityImpactGrenade> createRenderFor(RenderManager manager) 
			{
				return new RenderSnowball<EntityImpactGrenade>(manager, PowopItems.impact_grenade, Minecraft.getMinecraft().getRenderItem());
			}
		});
		//RenderingRegistry.registerEntityRenderingHandler(EntityImpactGrenade.class, new RenderSnowball<Entity>(Minecraft.getMinecraft().getRenderManager(), PowopItems.impact_greande, Minecraft.getMinecraft().getRenderItem()));
	}
}
