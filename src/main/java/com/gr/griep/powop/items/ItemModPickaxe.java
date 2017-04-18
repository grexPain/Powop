package com.gr.griep.powop.items;

import com.gr.griep.powop.PowopMod;

import net.minecraft.item.ItemPickaxe;

public class ItemModPickaxe extends ItemPickaxe {

	public ItemModPickaxe(ToolMaterial material, String unlocalizedName) {
		super(material);
		
		//setUnlocalizedName(unlocalizedName);
	}

/*	@Override
	@SideOnly(Side.CLIENT)
	protected String getIconString() {
        return this.iconString == null ?
        		PowopMod.MOD_ID + ":" + (getUnlocalizedName().substring(5)) : this.iconString;
	}*/
	
	

}
