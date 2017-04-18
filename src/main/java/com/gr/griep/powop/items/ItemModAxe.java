package com.gr.griep.powop.items;

import com.gr.griep.powop.PowopMod;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemAxe;

public class ItemModAxe extends ItemAxe {

	public ItemModAxe(ToolMaterial material, float damage, float speed) {
		super(material, damage, speed);
		//setUnlocalizedName(unlocalizedName);
	}

/*	@Override
	@SideOnly(Side.CLIENT)
	protected String getIconString() {
        return this.iconString == null ?
        		PowopMod.MOD_ID + ":" + (getUnlocalizedName().substring(5)) : this.iconString;
	}*/

}
