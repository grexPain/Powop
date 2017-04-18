package com.gr.griep.powop.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class ModBlockOre extends ModBlock {

	public ModBlockOre(int harvestLevel) {
		super(Material.ROCK);
		
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		setSoundType(SoundType.STONE);

		setHarvestLevel("pickaxe", harvestLevel);
	}

	
}
