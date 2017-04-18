package com.gr.griep.powop.items;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import org.apache.commons.codec.language.bm.Languages.LanguageSet;

import com.gr.griep.powop.PowopMod;
import com.gr.griep.powop.helper.LangHelper;
import com.gr.griep.powop.helper.RandomHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.Language;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.translation.LanguageMap;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ChewedGum extends ItemFood {

	private static final int CHANCE_SWALLOW_ONE_OF = 100;

	public ChewedGum() {
		super(1, 0, false);
		setAlwaysEdible();
	}

	@Override
	@Nullable
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn,
			EntityLivingBase entityLiving) {

		if (!worldIn.isRemote) {
			if (new Random().nextInt(CHANCE_SWALLOW_ONE_OF) == 0) {
				if (entityLiving instanceof EntityPlayer) {
					EntityPlayer player = (EntityPlayer) entityLiving;
					--stack.stackSize;
					player.getFoodStats().addStats(this, stack);
					worldIn.playSound((EntityPlayer) null, player.posX,
							player.posY, player.posZ,
							SoundEvents.ENTITY_PLAYER_BURP,
							SoundCategory.PLAYERS, 0.5F,
							worldIn.rand.nextFloat() * 0.1F + 0.9F);
				}
			}
		}
		return stack;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 32;
	}

	@Override
	public void addInformation(ItemStack stack, EntityPlayer player,
			List<String> list, boolean advanved) {
		list.add("\u00A7o" + I18n.format(LangHelper.TOOLTIP_FLAVOR_CHEWED_GUM));
	}
}