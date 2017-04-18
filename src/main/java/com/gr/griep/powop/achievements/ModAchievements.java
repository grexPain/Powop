package com.gr.griep.powop.achievements;

import com.gr.griep.powop.init.PowopItems;
import com.gr.griep.powop.items.ModItem;

import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;

public class ModAchievements {

	public static Achievement achieve_creeper_grenade;
	
	public static void registerAchievements() {		
		achieve_creeper_grenade = new Achievement("achievement.powop:creeper_grenade", "powop:creeper_grenade", 0, 0, PowopItems.impact_grenade, (Achievement)null).registerStat();
		AchievementPage.registerAchievementPage(new AchievementPage("Power of Purple", achieve_creeper_grenade));
	}
	
}
