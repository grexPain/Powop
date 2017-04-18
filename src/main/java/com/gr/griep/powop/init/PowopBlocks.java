package com.gr.griep.powop.init;


import java.util.HashSet;
import java.util.Set;

import com.gr.griep.powop.PowopMod;
import com.gr.griep.powop.blocks.AssemblyDesk;
import com.gr.griep.powop.blocks.BlockCoordTransporter;
import com.gr.griep.powop.blocks.GenericBlock;
import com.gr.griep.powop.blocks.ModBlockEmptyDrops;
import com.gr.griep.powop.blocks.ModBlockOre;
import com.gr.griep.powop.blocks.OreDictExchanger;
import com.gr.griep.powop.blocks.ShrinkBox;
import com.gr.griep.powop.blocks.ShrinkBoxPortal;
import com.gr.griep.powop.blocks.Shrinker;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEmptyDrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
//import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.IForgeRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

@ObjectHolder(PowopMod.MOD_ID)
public class PowopBlocks {

	@ObjectHolder("example_block")
	public static final Block EXAMPLE_BLOCK = new BlockCoordTransporter()
		.setName("example_block")
		.setCreativeTab(PowopMod.tabExample)
		.setHardness(4f);
	
	@ObjectHolder("purple_ore")
	public static final Block PURPLE_ORE = new ModBlockOre(3)
		.setName("purple_ore")
		.setCreativeTab(CreativeTabs.BUILDING_BLOCKS)
		.setHardness(3f);
	
	@ObjectHolder("shiny_purple_ore")
	public static final Block SHINY_PURPLE_ORE = new ModBlockOre(3)
		.setName("shiny_purple_ore")
		.setCreativeTab(CreativeTabs.BUILDING_BLOCKS)
		.setHardness(3f);
	
	@ObjectHolder("purple_block")
	public static final Block PURPLE_BLOCK = new GenericBlock(Material.ROCK)
		.setName("purple_block")
		.setCreativeTab(CreativeTabs.BUILDING_BLOCKS)
		.setHardness(5f)
		.setLightLevel(.2f);
	
	@ObjectHolder("asm_desk")
	public static final Block ASM_DESK = new AssemblyDesk()
		.setName("asm_desk")
		.setCreativeTab(CreativeTabs.DECORATIONS);
	
	@ObjectHolder("ore_exchanger")
	public static final Block ORE_EXCHANGER = new OreDictExchanger()
		.setName("ore_exchanger")
		.setCreativeTab(CreativeTabs.DECORATIONS);
	
	@ObjectHolder("shrink_box")
	public static final Block SHRINK_BOX = new ShrinkBox()
		.setName("shrink_box")
		.setCreativeTab(CreativeTabs.DECORATIONS);
	
	@ObjectHolder("shrink_box_shell")
	public static final Block SHRINK_BOX_SHELL = new ModBlockEmptyDrops(Material.ROCK)
		.setName("shrink_box_shell")
		.setBlockUnbreakable()
		.setResistance(6000000.0F)
		.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	
	@ObjectHolder("shrink_box_portal")
	public static final Block SHRINK_BOX_PORTAL = new ShrinkBoxPortal(Material.ROCK)
		.setName("shrink_box_portal")
		.setBlockUnbreakable()
		.setResistance(6000000.0F);
	
	@ObjectHolder("shrinker")
	public static final Block SHRINKER = new Shrinker(Material.ROCK)
		.setName("shrinker")
		.setCreativeTab(CreativeTabs.DECORATIONS);
	
	//static{init();}
	
//	public static void init() {
//		
//		example_block = new BlockCoordTransporter()
//			.setRegistryName(PowopMod.MOD_ID, "example_block")
//		//	.setUnlocalizedName("example_block")
//			.setCreativeTab(PowopMod.tabExample)
//			.setHardness(4f);
//		
//		purple_ore = new ModBlockOre(3)
//			.setRegistryName(PowopMod.MOD_ID, "purple_ore")
//		//	.setUnlocalizedName("purple_ore")
//			.setCreativeTab(CreativeTabs.BUILDING_BLOCKS)
//			.setHardness(3f);
//		
//		shiny_purple_ore = new ModBlockOre(3)
//			.setRegistryName(PowopMod.MOD_ID, "shiny_purple_ore")
//		//	.setUnlocalizedName("shiny_purple_ore")
//			.setCreativeTab(CreativeTabs.BUILDING_BLOCKS)
//			.setHardness(3f);
//		
//		purple_block = new GenericBlock(Material.ROCK)
//			.setRegistryName(PowopMod.MOD_ID, "purple_block")
//		//	.setUnlocalizedName("purple_block")
//			.setCreativeTab(CreativeTabs.BUILDING_BLOCKS)
//			.setHardness(5f)
//			.setLightLevel(.2f);
//		
//		asm_desk = new AssemblyDesk()
//			.setRegistryName(PowopMod.MOD_ID, "asm_desk")
//			.setCreativeTab(CreativeTabs.DECORATIONS);
//		
//		ore_exchanger = new OreDictExchanger()
//			.setRegistryName(PowopMod.MOD_ID, "ore_exchanger")
//			.setCreativeTab(CreativeTabs.DECORATIONS);
//		
//		shrink_box = new ShrinkBox()
//			.setRegistryName(PowopMod.MOD_ID, "shrink_box")
//			.setCreativeTab(CreativeTabs.DECORATIONS);
//		
//		shrink_box_shell = new BlockEmptyDrops(Material.ROCK)
//			.setRegistryName(PowopMod.MOD_ID, "shrink_box_shell")
//			.setBlockUnbreakable()
//			.setResistance(6000000.0F)
//			.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
//		
//		//registerRenders();
//	}
	
	@Mod.EventBusSubscriber
	public static class RegistrationHandler{
		public static final Set<ItemBlock> ITEM_BLOCKS = new HashSet<ItemBlock>();

		@SubscribeEvent
		public static void registerBlocks(RegistryEvent.Register<Block> event) {
			IForgeRegistry<Block> registry = event.getRegistry();
			
			registry.registerAll(EXAMPLE_BLOCK,
								 PURPLE_ORE,
								 SHINY_PURPLE_ORE,
								 PURPLE_BLOCK,
								 ASM_DESK,
								 ORE_EXCHANGER,
								 SHRINK_BOX,
								 SHRINK_BOX_SHELL,
								 SHRINK_BOX_PORTAL,
								 SHRINKER);
			
			OreDictionary.registerOre("orePurple", PURPLE_ORE);
			OreDictionary.registerOre("oreShinyPurple", SHINY_PURPLE_ORE);
		}
		
		@SubscribeEvent
		public static void registerItemBlocks(RegistryEvent.Register<Item> event) {

			final ItemBlock[] itemBlocks = {
					new ItemBlock(EXAMPLE_BLOCK),
					new ItemBlock(PURPLE_ORE),
					new ItemBlock(SHINY_PURPLE_ORE),
					new ItemBlock(PURPLE_BLOCK),
					new ItemBlock(ASM_DESK),
					new ItemBlock(ORE_EXCHANGER),
					new ItemBlock(SHRINK_BOX),
					new ItemBlock(SHRINK_BOX_SHELL),
					new ItemBlock(SHRINK_BOX_PORTAL),
					new ItemBlock(SHRINKER)
			};

			final IForgeRegistry<Item> registry = event.getRegistry();

			for (final ItemBlock itemBlock : itemBlocks) {
				registry.register(itemBlock.setRegistryName(itemBlock.getBlock().getRegistryName()));
				ITEM_BLOCKS.add(itemBlock);
			}
		}
	
		
		
		
		
		
		
		
//		public static void register() {
//	
//			//GameRegistry.registerBlock(example_block, example_block.getUnlocalizedName().substring(5));
//			register(EXAMPLE_BLOCK);
//			//GameRegistry.registerBlock(purple_ore, purple_ore.getUnlocalizedName().substring(5));
//			register(PURPLE_ORE);
//			OreDictionary.registerOre("orePurple", PURPLE_ORE);
//			
//			//GameRegistry.registerBlock(shiny_purple_ore, shiny_purple_ore.getUnlocalizedName().substring(5));
//			register(SHINY_PURPLE_ORE);
//			OreDictionary.registerOre("oreShinyPurple", SHINY_PURPLE_ORE);
//			
//			//GameRegistry.registerBlock(purple_block, purple_block.getUnlocalizedName().substring(5));
//			register(PURPLE_BLOCK);
//			
//			register(ASM_DESK);
//			register(ORE_EXCHANGER);
//			register(SHRINK_BOX);
//			register(SHRINK_BOX_SHELL);
//			
//			//GameRegistry.registerBlock(asm_desk, asm_desk.getUnlocalizedName().substring(5));
//		}
//		
//		public static void register(Block block){
//			
//		    GameRegistry.register(block.setUnlocalizedName(block.getRegistryName().toString()));
//		    ItemBlock itemBlock = new ItemBlock(block);
//		    itemBlock.setRegistryName(block.getRegistryName());
//		    GameRegistry.register(itemBlock.setUnlocalizedName(itemBlock.getRegistryName().toString()));
//		}
	}
	
	public static void registerRenders() {

		registerRender(EXAMPLE_BLOCK);
		registerRender(PURPLE_ORE);
		registerRender(SHINY_PURPLE_ORE);
		registerRender(PURPLE_BLOCK);
		registerRender(ASM_DESK);
		registerRender(ORE_EXCHANGER);
		registerRender(SHRINK_BOX);
		registerRender(SHRINK_BOX_SHELL);
		registerRender(SHRINK_BOX_PORTAL);
		registerRender(SHRINKER);
	}
	
	public static void registerRender(Block block) {
		
		Item item = Item.getItemFromBlock(block);
		//registerIcons(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}
		
	 @SideOnly(Side.CLIENT)
	 public static void registerIcons(Block block)
	 {
		 //block.setBlockTextureName(PowopMod.MOD_ID + ":" + (block.getUnlocalizedName().substring(5)));
		 
		 ///*this.blockIcon = */par1IconRegister.registerIcon(PowopMod.MOD_ID + ":" + (block.getUnlocalizedName().substring(5)));
	 }
	
}
