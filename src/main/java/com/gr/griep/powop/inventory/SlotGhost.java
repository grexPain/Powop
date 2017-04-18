package com.gr.griep.powop.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotGhost extends SlotItemHandler implements ISlotProxy {

	private boolean adjustCount;
	public SlotGhost(IItemHandler inventoryIn, int index, int xPosition,
			int yPosition, boolean adjustCount) {
		super(inventoryIn, index, xPosition, yPosition);
		this.adjustCount = adjustCount;
	}

	@Override
	public boolean canAdjustCount() {
		return adjustCount;
	}

/*	@Override
	public boolean canTakeStack(EntityPlayer playerIn) {
		return false;
	}

	@Override
	public void onPickupFromSlot(EntityPlayer playerIn, ItemStack stack) {
		super.onPickupFromSlot(playerIn, null);
	}*/	
}
