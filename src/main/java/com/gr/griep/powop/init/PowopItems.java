package com.gr.griep.powop.init;

import com.gr.griep.powop.PowopMod;
import com.gr.griep.powop.items.ChewedGum;
import com.gr.griep.powop.items.ChewingGum;
import com.gr.griep.powop.items.ImpactGrenade;
import com.gr.griep.powop.items.ItemCoordinateCache;
import com.gr.griep.powop.items.ItemModAxe;
import com.gr.griep.powop.items.ItemModPickaxe;
import com.gr.griep.powop.items.ModItem;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
//import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemFishFood.FishType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandom;

import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class PowopItems {

	public static Item example_item;

	// Materials
	public static Item purple_ingot;
	public static Item shiny_purple_ingot;
	public static Item shiny_purple_ingot_dense;
	public static Item shiny_purple_nugget;

	// Tools & Weapons
	public static Item purple_pickaxe;
	public static Item purple_battleaxe;
	
	// Foodstuff
	public static Item chewing_gum;
	public static Item chewed_gum;
	
	// 
	public static Item impact_grenade;
	
	public static void init () {
		
		example_item = new ItemCoordinateCache()
			.setRegistryName(PowopMod.MOD_ID, "example_item")
			.setUnlocalizedName("example_item")
			.setCreativeTab(PowopMod.tabExample)
			.setMaxStackSize(1);
		
		/////////////////////////////////////////////////////////////////
		// Materials
		////////////
		
		purple_ingot = new ModItem()
			.setRegistryName(PowopMod.MOD_ID, "purple_ingot")
			.setUnlocalizedName("purple_ingot")
			.setCreativeTab(CreativeTabs.MATERIALS);
		
		shiny_purple_ingot = new ModItem()
			.setRegistryName(PowopMod.MOD_ID, "shiny_purple_ingot")
			.setUnlocalizedName("shiny_purple_ingot")
			.setCreativeTab(CreativeTabs.MATERIALS);
		
		shiny_purple_ingot_dense = new ModItem()
			.setRegistryName(PowopMod.MOD_ID, "shiny_purple_ingot_dense")
			.setUnlocalizedName("shiny_purple_ingot_dense")
			.setCreativeTab(CreativeTabs.MATERIALS);
		
		shiny_purple_nugget = new ModItem()
			.setRegistryName(PowopMod.MOD_ID, "shiny_purple_nugget")
			.setUnlocalizedName("shiny_purple_nugget")
			.setCreativeTab(CreativeTabs.MATERIALS);
		
		
		/////////////////////////////////////////////////////////////////
		// Tool & Weapons
		/////////////////
		
		purple_pickaxe = new ItemModPickaxe(ToolMaterial.valueOf("PURPLE"), "purple_pickaxe")
			.setRegistryName(PowopMod.MOD_ID, "purple_pickaxe")
			.setCreativeTab(CreativeTabs.TOOLS);
		
		purple_battleaxe = new ItemModAxe(ToolMaterial.valueOf("BATTLE_PURPLE"), 9.0f, -2.0f)
			.setRegistryName(PowopMod.MOD_ID, "purple_battleaxe")
			.setCreativeTab(CreativeTabs.TOOLS);
		
		
		//////////////////////////////////////////////////////////////////
		// Foodstuff
		////////////
		
		chewing_gum = new ChewingGum()
			.setRegistryName(PowopMod.MOD_ID, "chewing_gum")
			.setCreativeTab(CreativeTabs.FOOD);
		
		chewed_gum = new ChewedGum()
		.setRegistryName(PowopMod.MOD_ID, "chewed_gum")
		.setCreativeTab(CreativeTabs.FOOD);
		
		
		////////////////////////////////////////////////////////////////////
		//
		//
		
		impact_grenade = new ImpactGrenade()
			.setRegistryName(PowopMod.MOD_ID, "impact_grenade")
			.setCreativeTab(CreativeTabs.COMBAT);
	}
	
	public static void register() {
		
		registerItem(example_item);
		
		// Materials
		registerItem(purple_ingot);
		registerItem(shiny_purple_ingot);
		registerItem(shiny_purple_ingot_dense);
		registerItem(shiny_purple_nugget);
		registerItem(chewed_gum);
		OreDictionary.registerOre("slimeball", chewed_gum);
		
		// Tools & Weapons
		registerItem(purple_pickaxe);
		registerItem(purple_battleaxe);
		
		//Foodstuff
		registerItem(chewing_gum);
		
		//
		registerItem(impact_grenade);
	}
	
	private static void registerItem(Item item) {

	    GameRegistry.register(item.setUnlocalizedName(item.getRegistryName().toString()));
		//GameRegistry.registerItem(item, item.getUnlocalizedName().substring(5));
	}
	
	public static void registerRenders() {

		registerRender(example_item);
		
		// Materials
		registerRender(purple_ingot);
		registerRender(shiny_purple_ingot);
		registerRender(shiny_purple_ingot_dense);
		registerRender(shiny_purple_nugget);
		registerRender(chewed_gum);
		
		// Tools & Weapons
		registerRender(purple_pickaxe);
		registerRender(purple_battleaxe);
		
		// Foodstuff
		registerRender(chewing_gum);
		
		//
		registerRender(impact_grenade);
	}
	
	public static void registerRender(Item item) {
		
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
	
	public static ToolMaterial PURPLE = EnumHelper.addToolMaterial("PURPLE", 4, 2400, 20, 2, 25);
	public static ToolMaterial BATTLE_PURPLE = EnumHelper.addToolMaterial("BATTLE_PURPLE", 3, 2400, 20, 7, 25);
}