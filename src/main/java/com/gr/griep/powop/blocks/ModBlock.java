package com.gr.griep.powop.blocks;


import com.gr.griep.powop.PowopMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;


public class ModBlock extends Block {

	public ModBlock(Material mat) {
		super(mat);
	}

/*	@Override
	@SideOnly(Side.CLIENT)
	protected String getTextureName() {
        return this.textureName == null ?
        		PowopMod.MOD_ID + ":" + (getUnlocalizedName().substring(5)) : this.textureName;
	}*/
	

	public Block setName(String blockName) {
		setRegistryName(PowopMod.MOD_ID, blockName);
		setUnlocalizedName(getRegistryName().toString());
		return this;
	}
	
	
}
