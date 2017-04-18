package com.gr.griep.powop;

import com.gr.griep.powop.achievements.ModAchievements;
import com.gr.griep.powop.init.PowopBiomes;
import com.gr.griep.powop.init.PowopDimensions;
import com.gr.griep.powop.init.PowopEntities;
import com.gr.griep.powop.init.PowopHandlers;
import com.gr.griep.powop.init.PowopBlocks;
import com.gr.griep.powop.init.PowopItems;
import com.gr.griep.powop.init.PowopTileEntities;
import com.gr.griep.powop.init.RecipeRegistry;
import com.gr.griep.powop.init.TeleportTileEntities;
import com.gr.griep.powop.proxy.ClientProxy;
import com.gr.griep.powop.proxy.CommonProxy;

import net.minecraft.stats.Achievement;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = PowopMod.MOD_ID, version = PowopMod.MOD_VERSION, name = PowopMod.MOD_NAME)
public class PowopMod
{
    public static final String MOD_ID = "powop";
    public static final String MOD_VERSION = "1.0";
    public static final String MOD_NAME = "Power of Purple";
    @Instance(MOD_ID)
    public static PowopMod INSTANCE;

    public static final String CLIENT_PROXY_CLASS = "com.gr.griep.powop.proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "com.gr.griep.powop.proxy.CommonProxy";
    
    @SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = SERVER_PROXY_CLASS)
    public static CommonProxy proxy;
    
    public static final ExampleTab tabExample = new ExampleTab("tabExample");
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	
//    	PowopBlocks.init();
//    	PowopBlocks.register();
//    	
    	PowopItems.init();
    	PowopItems.register();
    	
    	PowopTileEntities.init();
    	TeleportTileEntities.register();
    	PowopEntities.registerEntities();
    	
    	
    	RecipeRegistry.registerRecipes();

    	GameRegistry.registerWorldGenerator(new PurpleWorldGenerator(), 0);
    	
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
    	
    	PowopHandlers.registerHandlers();
    	
    	proxy.registerRenders();
    	proxy.registerRenderingHandlers();
    	
    	ModAchievements.registerAchievements();
    	
    	PowopBiomes.init();
    	PowopDimensions.init();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    	
    }
}
