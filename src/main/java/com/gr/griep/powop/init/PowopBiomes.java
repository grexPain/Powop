package com.gr.griep.powop.init;

import com.gr.griep.powop.PowopMod;
import com.gr.griep.powop.world.biome.BiomeShrinkBox;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.common.registry.IForgeRegistry;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

@ObjectHolder(PowopMod.MOD_ID)
public class PowopBiomes {

	@ObjectHolder("biome_shrink_box")
	public static final Biome BIOME_SHRINK_BOX = new BiomeShrinkBox();
	
	public static void init() {
		
		//biomeShrinkBox = new BiomeShrinkBox();
		
		//registerBiomes();
	}

	
	private static void registerBiomes() {
		//BiomeManager.addBiome(BiomeType.DESERT, new BiomeEntry(biomeShrinkBox, 1));
	}

	@Mod.EventBusSubscriber
	public static class RegistrationHandler{
		@SubscribeEvent
		public static void registerBiomes(RegistryEvent.Register<Biome> event) {
			final IForgeRegistry<Biome> registry = event.getRegistry();
	
			registerBiome(registry, BIOME_SHRINK_BOX, "biome_shrink_box", BiomeManager.BiomeType.DESERT, 1000, false, DRY);
		}
	
		private static <T extends Biome> T registerBiome(IForgeRegistry<Biome> registry, T biome, String biomeName, BiomeManager.BiomeType biomeType, int weight, boolean addEntry, BiomeDictionary.Type... types) {
			registry.register(biome.setRegistryName(PowopMod.MOD_ID, biomeName));
			BiomeDictionary.registerBiomeType(biome, types);
			
			if(addEntry)
				BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(biome, weight));
	
			return biome;
		}
	}
}
