package com.gr.griep.powop;

import com.gr.griep.powop.init.PowopItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ExampleTab extends CreativeTabs {

	public ExampleTab(String label) {
		super(label);
		this.setBackgroundImageName("example.png");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Item getTabIconItem() {
		return PowopItems.example_item;
	}

}
