package com.gr.griep.powop.inventory;

import javax.annotation.Nullable;

import com.gr.griep.powop.tileentity.TileEntityOreDictExchanger;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerOreDictExchanger extends PowopContainer {
	
	TileEntityOreDictExchanger exchangerInventory;
	
	public ContainerOreDictExchanger(InventoryPlayer playerInventory, TileEntityOreDictExchanger exchangerInventory) {
		this.exchangerInventory = exchangerInventory;

		
		// input SLOT 0
		addSlotToContainer(new SlotItemHandler(exchangerInventory, TileEntityOreDictExchanger.ID_SLOT_INPUT, 80, 17));
		
		// output SLOT 1 - 27
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new SlotOutput(exchangerInventory, 1 + x + y * 9, 8 + x * 18, 61 + y * 18));
			}			
		}
		
		// player inventar SLOT 28 - 54
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlotToContainer(new Slot(playerInventory, 9 + x + y * 9, 8 + x * 18, 128 + y * 18));
			}
		}

		// hotbar SLOT 55 - 63
		for (int x = 0; x < 9; x++) {
			addSlotToContainer(new Slot(playerInventory, x, 8 + x * 18, 186));
		}
		
		// config SLOT 64 - 72 
		for (int x = 0; x < 9; x++) {
			addSlotToContainer(new SlotGhost(exchangerInventory, TileEntityOreDictExchanger.ID_SLOT_CONFIG_FIRST + x, 8 + x * 18, 39, false));
		}
	}

//	@Override
//    public void addListener(IContainerListener listener)
//    {
//        super.addListener(listener);
//        listener.sendAllWindowProperties(this, this.exchangerInventory);
//    }

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return exchangerInventory.isUseableByPlayer(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
	    ItemStack previous = null;
	    Slot slot = (Slot) this.inventorySlots.get(fromSlot);

	    if (slot != null && slot.getHasStack()) {
	        ItemStack current = slot.getStack();
	        previous = current.copy();
	        
	        if (fromSlot <= 27) {
	            // From In-/Output to Player Inventory
	            if (!this.mergeItemStack(current, 28, 64, true))
	                return null;
	        } else if(fromSlot >=28 && fromSlot <=63) {
	        	// From Player Inventory to Input
	            if (!this.mergeItemStack(current, 0, 1, true))
	                return null;
	        }

	        if (current.stackSize == 0)
	            slot.putStack((ItemStack) null);
	        else
	            slot.onSlotChanged();

	        if (current.stackSize == previous.stackSize)
	            return null;
	        slot.onPickupFromSlot(playerIn, current);
	    }
	    return previous;
	}

}
