package com.gr.griep.powop.init;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.gr.griep.powop.PurpleWorldGenerator;
import com.jcraft.jorbis.Block;

public class RecipeRegistry {
	
	private enum CommonRecipe {
	
		Pickaxe,
		Shovel,
		Axe,
		Hoe,
		Sword,
		Helmet,
		Chestplate,
		Leggings,
		Boots,
		CompressNine,
		CompressFour
	}

	public static void registerRecipes() {
		
		registerSmeltingRecipes();
		registerCraftingRecipes();
	}

	private static void registerCraftingRecipes() {
    	
    	// craft purple ingot -> purple block
//    	GameRegistry.addShapedRecipe(new ItemStack(ExampleBlocks.purple_block),
//    		    "PPP",
//    		    "PPP",
//    		    "PPP",
//    		    'P', new ItemStack(ExampleItems.purple_ingot));
		
		registerCommonRecipe(CommonRecipe.CompressNine, new ItemStack(PowopBlocks.PURPLE_BLOCK), new ItemStack(PowopItems.purple_ingot));
		registerCommonRecipe(CommonRecipe.CompressNine, new ItemStack(PowopItems.shiny_purple_ingot), new ItemStack(PowopItems.shiny_purple_nugget));
		registerCommonRecipe(CommonRecipe.CompressNine, new ItemStack(PowopItems.shiny_purple_ingot_dense), new ItemStack(PowopItems.shiny_purple_ingot));
		registerCommonRecipe(CommonRecipe.Pickaxe, new ItemStack(PowopItems.purple_pickaxe), new ItemStack(PowopItems.purple_ingot));
		
		
		// Purple Battle-Axe
		GameRegistry.addShapedRecipe(new ItemStack(PowopItems.purple_battleaxe),
    		    "MPP",
    		    "MPS",
    		    "M S",
    		    'M', new ItemStack(PowopItems.shiny_purple_ingot_dense), 'P', new ItemStack(PowopItems.purple_ingot), 'S', new ItemStack(Items.STICK));
		
		GameRegistry.addShapedRecipe(new ItemStack(PowopItems.purple_battleaxe),
    		    "PPM",
    		    "SPM",
    		    "S M",
    		    'M', new ItemStack(PowopItems.shiny_purple_ingot_dense), 'P', new ItemStack(PowopItems.purple_ingot), 'S', new ItemStack(Items.STICK));
		
		// Assembly Desk
		GameRegistry.addShapedRecipe(new ItemStack(PowopBlocks.ASM_DESK),
    		    "WWW",
    		    "  S",
    		    "SSS",
    		    'S', new ItemStack(Items.STICK), 'W', new ItemStack(Blocks.WOODEN_SLAB));

		GameRegistry.addShapedRecipe(new ItemStack(PowopBlocks.ASM_DESK),
    		    "WWW",
    		    "S  ",
    		    "SSS",
    		    'S', new ItemStack(Items.STICK), 'W', new ItemStack(Blocks.WOODEN_SLAB));

		
    	// craft purple block -> purple ingot
    	GameRegistry.addShapelessRecipe(new ItemStack(PowopItems.purple_ingot, 9), new ItemStack(PowopBlocks.PURPLE_BLOCK));
    	
    	GameRegistry.addShapelessRecipe(new ItemStack(PowopItems.shiny_purple_ingot, 9), new ItemStack(PowopItems.shiny_purple_ingot_dense));

    	GameRegistry.addShapelessRecipe(new ItemStack(PowopItems.shiny_purple_nugget, 9), new ItemStack(PowopItems.shiny_purple_ingot));

    	GameRegistry.addShapelessRecipe(new ItemStack(PowopItems.impact_grenade), new ItemStack(Items.EGG), new ItemStack(Items.FLINT), new ItemStack(Items.GUNPOWDER));
	}
	
	private static void registerCommonRecipe(CommonRecipe recipe, ItemStack output, ItemStack material) {
		
		registerCommonRecipe(recipe, output, material, new ItemStack(Items.STICK));
	}
	
	private static void registerCommonRecipe(CommonRecipe recipe, ItemStack output, ItemStack material, ItemStack specialStick) {
		
		switch (recipe) {
		case Pickaxe:
	    	GameRegistry.addShapedRecipe(output,
	    		    "MMM",
	    		    " S ",
	    		    " S ",
	    		    'M', material, 'S', specialStick);
			break;
		case Shovel:
	    	GameRegistry.addShapedRecipe(output,
				    "M",
				    "S",
				    "S",
				    'M', material, 'S', specialStick);			
			break;
		case Axe:
			GameRegistry.addShapedRecipe(output,
	    		    "MM",
	    		    "MS",
	    		    " S",
	    		    'M', material, 'S', specialStick);
			GameRegistry.addShapedRecipe(output,
	    		    "MM",
	    		    "SM ",
	    		    "S ",
	    		    'M', material, 'S', specialStick);
			break;
		case Hoe:
			GameRegistry.addShapedRecipe(output,
	    		    "MM",
	    		    " S",
	    		    " S",
	    		    'M', material, 'S', specialStick);
			GameRegistry.addShapedRecipe(output,
	    		    "MM",
	    		    "S ",
	    		    "S ",
	    		    'M', material, 'S', specialStick);
			break;
		case Sword:
			GameRegistry.addShapedRecipe(output,
	    		    "M",
	    		    "M",
	    		    "S",
	    		    'M', material, 'S', specialStick);
			break;
		case Helmet:
			GameRegistry.addShapedRecipe(output,
	    		    "MMM",
	    		    "M M",
	    		    'M', material);
			break;
		case Chestplate:
			GameRegistry.addShapedRecipe(output,
	    		    "M M",
	    		    "MMM",
	    		    "MMM",
	    		    'M', material);
			break;
		case Leggings:
			GameRegistry.addShapedRecipe(output,
	    		    "MMM",
	    		    "M M",
	    		    "M M",
	    		    'M', material);
			break;
		case Boots:
			GameRegistry.addShapedRecipe(output,
	    		    "M M",
	    		    "M M",
	    		    'M', material);
			break;
		case CompressNine:
			GameRegistry.addShapedRecipe(output,
	    		    "MMM",
	    		    "MMM",
	    		    "MMM",
	    		    'M', material);
			break;
		case CompressFour:
			GameRegistry.addShapedRecipe(output,
	    		    "MM",
	    		    "MM",
	    		    'M', material);
			break;

		default:
			break;
		}
	}

	private static void registerSmeltingRecipes() {
		
		// smelt purple ore -> purple ingot
    	GameRegistry.addSmelting(PowopBlocks.PURPLE_ORE, new ItemStack(PowopItems.purple_ingot), 0.5f);		
    	GameRegistry.addSmelting(PowopBlocks.SHINY_PURPLE_ORE, new ItemStack(PowopItems.shiny_purple_nugget), 0.5f);
	}
}
