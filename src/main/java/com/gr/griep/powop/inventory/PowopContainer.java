package com.gr.griep.powop.inventory;

import javax.annotation.Nullable;

import com.gr.griep.powop.misc.StackUtil;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

abstract class PowopContainer extends Container {

    @Nullable
    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickType, EntityPlayer player) {
    	if(clickType == ClickType.PICKUP_ALL)
    		return player.inventory.getItemStack();
        Slot slot = slotId < 0 ? null : this.inventorySlots.get(slotId);
        if (slot == null) {
            return super.slotClick(slotId, dragType, clickType, player);
        }
        
        ItemStack playerStack = player.inventory.getItemStack();
        if (slot instanceof ISlotProxy) {
        	ISlotProxy phantom = (ISlotProxy) slot;
            if (playerStack == null || playerStack.stackSize == 0) {
                slot.putStack(null);
            } else if (slot.getStack() == null || !StackUtil.canMerge(playerStack, slot.getStack())) {
                ItemStack copy = playerStack.copy();
                copy.stackSize = 1;
                slot.putStack(copy);
            } else if (phantom.canAdjustCount()) {
                ItemStack stack = slot.getStack();
                if (stack.stackSize < stack.getMaxStackSize()) {
                    ++stack.stackSize;
                    slot.putStack(stack);
                }
            }
            return playerStack;
        }
        return super.slotClick(slotId, dragType, clickType, player);
    }

}
