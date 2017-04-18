package com.gr.griep.powop.items;

import java.util.List;

import javax.annotation.Nullable;

import com.gr.griep.powop.PowopMod;
import com.gr.griep.powop.helper.LangHelper;
import com.gr.griep.powop.init.PowopItems;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ChewingGum extends ItemFood {

	public ChewingGum() {
		super(0, false);
		maxStackSize = 1;
		setMaxDamage(10);
		setAlwaysEdible();
	}
	
	@Override
	@Nullable
	public ItemStack onItemUseFinish(ItemStack stack, World worldIn,
			EntityLivingBase entityLiving) {

        if (entityLiving instanceof EntityPlayer){
        	EntityPlayer player = (EntityPlayer)entityLiving;
        	if(stack.getItemDamage() == 9)
        		--stack.stackSize;
        	else
        		stack.damageItem(1, entityLiving);
        		
        	if(!player.inventory.addItemStackToInventory(new ItemStack(PowopItems.chewed_gum)))
        		player.dropItem(new ItemStack(PowopItems.chewed_gum), false);
        	worldIn.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
        }
		
		return stack;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 16;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List<String> list, boolean advanved) {
		int amount = 10 - stack.getItemDamage();
		StringBuilder sb = new StringBuilder("\u00A7o");
		switch(amount) {
		case 11:
			sb.append("Tastes like carro... wait... are those pieces of metal?");
			break;
		case 10:
			sb.append(I18n.format(LangHelper.TOOLTIP_FULL_PACKAGE));
			break;
		case 1:
			sb.append(I18n.format(LangHelper.TOOLTIP_ONE_PIECE_LEFT));
			break;
		default:
			sb.append(I18n.format(LangHelper.TOOLTIP_PIECES_LEFT, amount));
			break;
		}
		list.add(sb.toString());
	}
}
