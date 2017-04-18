package com.gr.griep.powop.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.gr.griep.powop.init.PowopItems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.conditions.RandomChance;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.SetDamage;
import net.minecraftforge.event.LootTableLoadEvent;


public class LootHelper {
	
	private static void registerLoot() {		
		registerLoot(LootTableList.GAMEPLAY_FISHING_JUNK, createDamagedLootEntryItem(PowopItems.chewing_gum, 20, 0, .4f, 1f));
		//registerLoot(LootTableList.ENTITIES_ZOMBIE, createDamagedLootEntryItem(PowopItems.chewing_gum, 1, 0, .4f, .9f));		
	}
	
	private static void registerPools() {
		registerPool(LootTableList.ENTITIES_ZOMBIE, new LootPool(new LootEntry[]{createDamagedLootEntryItem(PowopItems.chewing_gum, 1, 0, .4f, .9f)}, new LootCondition[]{new RandomChance(.25f)}, new RandomValueRange(1), new RandomValueRange(0), ID_SEPARATE_LOOT_POOL));
	}
	
	static{
		additionalLoot = new HashMap<String, List<LootEntry>>();
		registerLoot();
		additionalPools = new HashMap<String, List<LootPool>>();
		registerPools();
	}

    public static final String ID_VANILLA_LOOT_POOL = "main";
    //public static final String ID_VANILLA_SECOND_LOOT_POOL = "pool1";
    public static final String ID_SEPARATE_LOOT_POOL = "powop";

	private static Map<String, List<LootEntry>> additionalLoot;
	private static Map<String, List<LootPool>> additionalPools;
	
	public static void registerLoot(ResourceLocation location, LootEntry lootEntry) {
		String locationName = location.toString();
		
		List<LootEntry> loot;
		if(!additionalLoot.containsKey(location.toString())){
			loot = new ArrayList<LootEntry>();
			additionalLoot.put(locationName, loot);
		} else {
			loot = additionalLoot.get(locationName);
		}
		
		loot.add(lootEntry);
	}

	public static void registerPool(ResourceLocation location, LootPool lootPool) {
		String locationName = location.toString();
		
		List<LootPool> pools;
		if(!additionalPools.containsKey(locationName)){
			pools = new ArrayList<LootPool>();
			additionalPools.put(locationName, pools);
		} else {
			pools = additionalPools.get(locationName);
		}
		
		pools.add(lootPool);
	}
	
	public static void injectCustomLoot(LootTableLoadEvent event){
		
		String tableName = event.getName().toString();
		LootTable lootTable = event.getTable();
		
		injectLoot(tableName, lootTable);

		injectPools(tableName, lootTable);
	}

	private static void injectLoot(String tableName, LootTable lootTable) {
		if(!additionalLoot.containsKey(tableName))
			return;

		//createPoolIfNotExists(event.getTable(), lootPoolId);
        final LootPool lootPool = lootTable.getPool(ID_VANILLA_LOOT_POOL);

        for (LootEntry lootEntry : additionalLoot.get(tableName)) {
            lootPool.addEntry(lootEntry);
        }
	}
	
	private static void injectPools(String tableName, LootTable lootTable) {
		if(!additionalPools.containsKey(tableName))
			return;
		
		for (LootPool lootPool : additionalPools.get(tableName)) {
			lootTable.addPool(lootPool);
		}
	}

    private static void createPoolIfNotExists(LootTable lootTable, String poolId) {
        if (poolId.equals(ID_VANILLA_LOOT_POOL) || lootTable.getPool(poolId) != null) return;

        lootTable.addPool(new LootPool(new LootEntry[] {}, new LootCondition[] {}, new RandomValueRange(1), new RandomValueRange(0), poolId));

    }

    private static LootEntryItem createLootEntryItem(Item item, int weight, int quality) {
        return new LootEntryItem(item, weight, quality, new LootFunction[]{}, new LootCondition[]{}, "powop." + item.getUnlocalizedName());
    }

    private static LootEntryItem createDamagedLootEntryItem(Item item, int weight, int quality, float minDurability, float maxDurability) {
        return new LootEntryItem(item, weight, quality, new LootFunction[]{new SetDamage(new LootCondition[]{}, new RandomValueRange(minDurability, maxDurability))}, new LootCondition[]{}, "powop." + item.getUnlocalizedName());
    }
	
	
	
/*   public static final Set<CustomLootPool> additionalLootPools = new HashSet<CustomLootPool>();

    static {
        // LootTable for GAMEPLAY_FISHING_JUNK
        final ArrayList<LootEntryItem> gameplayFishingJunkEntries = new ArrayList<LootEntryItem>();
        gameplayFishingJunkEntries.add(createLootEntryItem(CropRegistry.getFood(CropRegistry.SEAWEED), 10, 0));

        additionalLootPools.add(new CustomLootPool(
                LootTableList.GAMEPLAY_FISHING_JUNK,
                gameplayFishingJunkEntries, false));
        }
    }


    private static LootEntryItem createLootEntryItem(Item item, int weight, int quality) {
        return new LootEntryItem(item, weight, quality, lootFunctions, lootConditions, "harvestcraft." + item.getUnlocalizedName());
    }

    private static LootEntryItem createLootEntryItem(Item item, int weight, int quality, LootFunction[] lootFunctions, LootCondition... lootConditions) {
        return new LootEntryItem(item, weight, quality, lootFunctions, lootConditions, "harvestcraft." + item.getUnlocalizedName());
    }*/
}