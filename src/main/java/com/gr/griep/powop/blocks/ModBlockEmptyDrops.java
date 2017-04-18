package com.gr.griep.powop.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEmptyDrops;
import net.minecraft.block.material.Material;

import com.gr.griep.powop.PowopMod;

public class ModBlockEmptyDrops extends BlockEmptyDrops {

	public ModBlockEmptyDrops(Material materialIn) {
		super(materialIn);
	}

	public Block setName(String blockName) {
		setRegistryName(PowopMod.MOD_ID, blockName);
		setUnlocalizedName(getRegistryName().toString());
		return this;
	}
}
