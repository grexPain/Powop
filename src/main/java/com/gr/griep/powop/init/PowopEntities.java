package com.gr.griep.powop.init;

import com.gr.griep.powop.PowopMod;
import com.gr.griep.powop.entity.EntityImpactGrenade;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class PowopEntities {

	public static void registerEntities() {
		registerEntity(EntityImpactGrenade.class, "impact_grenade", ID_ENTITY_IMPACT_GRENADE, 128, 10, true);
	}
	
	private static void registerEntity(Class<? extends Entity> entityClass, String entityName, int id, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
		
		EntityRegistry.registerModEntity(entityClass, entityName, id, PowopMod.INSTANCE, trackingRange, updateFrequency, sendsVelocityUpdates);
		
	}
	
	public static final int ID_ENTITY_IMPACT_GRENADE = 1;
	
}
