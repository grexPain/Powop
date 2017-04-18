package com.gr.griep.powop.world.biome;

import com.gr.griep.powop.init.PowopBlocks;

import net.minecraft.world.biome.Biome;

public class BiomeShrinkBox extends Biome {

	public BiomeShrinkBox() {
		this(new BiomeProperties("ShrinkBox").setHeightVariation(0).setBaseHeight(0).setRainDisabled().setTemperature(0.7f));
	}

	public BiomeShrinkBox(BiomeProperties properties) {
		super(properties);
		this.fillerBlock = PowopBlocks.SHRINK_BOX_SHELL.getDefaultState();
		this.topBlock = PowopBlocks.ORE_EXCHANGER.getDefaultState();
	}
	
	
}
