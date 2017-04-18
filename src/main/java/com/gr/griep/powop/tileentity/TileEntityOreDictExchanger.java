package com.gr.griep.powop.tileentity;

import javax.annotation.Nullable;

import com.gr.griep.powop.misc.StackUtil;

import scala.Array;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.oredict.OreDictionary;

public class TileEntityOreDictExchanger extends TileEntity implements /*Inventory,*/ IItemHandlerModifiable {

	public static final int ID_SLOT_INPUT = 0;
	public static final int ID_SLOT_OUTPUT_FIRST = 1;
	public static final int ID_SLOT_OUTPUT_LAST = 27;
	public static final int ID_SLOT_CONFIG_FIRST = 28;
	public static final int ID_SLOT_CONFIG_LAST = 36;

	private String customName;
	private ItemStack[] itemStacks = new ItemStack[this.getSlots()];

	//@Override
	public String getName() {
		return this.hasCustomName() ? this.customName : "container.powop:ore_exchanger";
	}

	@Override
	public ITextComponent getDisplayName() {
		return (ITextComponent) (this.hasCustomName() ? new TextComponentString(this.getName())
				: new TextComponentTranslation(this.getName(), new Object[0]));
	}

//	@Override
	public boolean hasCustomName() {
		return this.customName != null && !this.customName.isEmpty();
	}

//	@Override
//	public int getSizeInventory() {
//		return 37;
//	}

	@Override
	@Nullable
	public ItemStack getStackInSlot(int index) {
		return this.itemStacks[index];
	}

//	@Override
//	@Nullable
//	public ItemStack decrStackSize(int index, int count) {
//		return ItemStackHelper.getAndSplit(this.itemStacks, index, count);
//	}
//
//	@Override
//	@Nullable
//	public ItemStack removeStackFromSlot(int index) {
//		return ItemStackHelper.getAndRemove(this.itemStacks, index);
//	}
//
//	@Override
//	public void setInventorySlotContents(int index, @Nullable ItemStack stack) {
//		if (index == ID_SLOT_INPUT) {
//			this.itemStacks[index] = stack;
//
//			if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
//				stack.stackSize = this.getInventoryStackLimit();
//			}
//
//			exchange();
//			this.markDirty();
//		} else if (index >= ID_SLOT_CONFIG_FIRST && index <= ID_SLOT_CONFIG_LAST) {
//			this.itemStacks[index] = stack;
//			if (stack != null)
//				stack.stackSize = 1;
//		}
//
//	}

	private void exchange() {
		int index = findMatch();
		if (index < 0)
			return;

		/*
		 * ItemStack copy = itemStacks[ID_SLOT_INPUT].copy();
		 * copy.setItem(itemStacks[index].getItem());
		 */

		ItemStack changed = new ItemStack(itemStacks[index].getItem(), itemStacks[ID_SLOT_INPUT].stackSize);
		ItemStack rest = StackUtil.mergeStack(changed, itemStacks, ID_SLOT_OUTPUT_FIRST, ID_SLOT_OUTPUT_LAST);
		if (rest == null)
			itemStacks[ID_SLOT_INPUT] = null;
		else
			itemStacks[ID_SLOT_INPUT].stackSize = rest.stackSize;
	}

	private int findMatch() {
		if (itemStacks[ID_SLOT_INPUT] == null)
			return -1;
		int[] ids = OreDictionary.getOreIDs(itemStacks[ID_SLOT_INPUT]);
		if (ids.length == 0)
			return -1;

		for (int i = ID_SLOT_CONFIG_FIRST; i <= ID_SLOT_CONFIG_LAST; i++) {
			ItemStack comparedStack = itemStacks[i];
			if (comparedStack == null)
				continue;

			int[] comparedIds = OreDictionary.getOreIDs(comparedStack);
			if (comparedIds.length == 0)
				continue;

			for (int j = 0; j < comparedIds.length; j++) {
				for (int k = 0; k < ids.length; k++) {
					return i;
				}
			}
		}
		return -1;
	}

//	@Override
//	public int getInventoryStackLimit() {
//		return 64;
//	}

	//@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return this.worldObj.getTileEntity(this.pos) != this ? 
				false :
				player.getDistanceSq((double) this.pos.getX() + 0.5D,
						(double) this.pos.getY() + 0.5D,
						(double) this.pos.getZ() + 0.5D) <= 64.0D;
	}

//	@Override
//	public void openInventory(EntityPlayer player) {
//
//	}
//
//	@Override
//	public void closeInventory(EntityPlayer player) {
//
//	}
//
//	@Override
//	public boolean isItemValidForSlot(int index, ItemStack stack) {
//		return index < ID_SLOT_OUTPUT_FIRST || index > ID_SLOT_OUTPUT_LAST;
//	}

	public int getField(int id) {
		return 0;
	}

	public void setField(int id, int value) {
	}

	public int getFieldCount() {
		return 0;
	}

//	@Override
//	public void clear() {
//		for (int i = 0; i < this.itemStacks.length; ++i) {
//			this.itemStacks[i] = null;
//		}
//	}

	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		NBTTagList nbttaglist = compound.getTagList("Items", 10);
		this.itemStacks = new ItemStack[this.getSlots()];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound.getByte("Slot");

			if (j >= 0 && j < this.itemStacks.length) {
				this.itemStacks[j] = ItemStack.loadItemStackFromNBT(nbttagcompound);
			}
		}

		if (compound.hasKey("CustomName", 8)) {
			this.customName = compound.getString("CustomName");
		}
	}

	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < this.itemStacks.length; ++i) {
			if (this.itemStacks[i] != null) {
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				nbttagcompound.setByte("Slot", (byte) i);
				this.itemStacks[i].writeToNBT(nbttagcompound);
				nbttaglist.appendTag(nbttagcompound);
			}
		}

		compound.setTag("Items", nbttaglist);

		if (this.hasCustomName()) {
			compound.setString("CustomName", this.customName);
		}
		return compound;
	}

	public void setCustomInventoryName(String customName) {
		this.customName = customName;
	}

	/*
	 * @Override public int[] getSlotsForFace(EnumFacing side) { int[] slots =
	 * new int[2+ID_SLOT_OUTPUT_LAST-ID_SLOT_OUTPUT_FIRST]; slots[0] =
	 * ID_SLOT_INPUT; int j = 0; for (int i = ID_SLOT_OUTPUT_FIRST; i <=
	 * ID_SLOT_OUTPUT_LAST; i++) { slots[++j] = i; } return slots; }
	 * 
	 * @Override public boolean canInsertItem(int index, ItemStack itemStackIn,
	 * EnumFacing direction) { if(index == ID_SLOT_INPUT) return true; return
	 * false; }
	 * 
	 * @Override public boolean canExtractItem(int index, ItemStack stack,
	 * EnumFacing direction) { if(index >= ID_SLOT_OUTPUT_FIRST && index <=
	 * ID_SLOT_OUTPUT_LAST) return true; return false; }
	 */

	@Override
	public int getSlots() {
		return 37;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) this : super.getCapability(
				capability, facing));
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? true : super.hasCapability(capability,
				facing));
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
		if (itemStacks[ID_SLOT_INPUT] == null) {
			if (!simulate)
				itemStacks[ID_SLOT_INPUT] = stack;
			return null;
		}

		int currentStacksize = itemStacks[ID_SLOT_INPUT].stackSize;
		int maxStacksize = stack.getMaxStackSize();

		if (!StackUtil.canMerge(stack, itemStacks[ID_SLOT_INPUT]) || currentStacksize == maxStacksize)
			return stack;

		int newStacksize = currentStacksize + stack.stackSize;
		if (newStacksize > maxStacksize) {
			if (!simulate)
				itemStacks[ID_SLOT_INPUT].stackSize = maxStacksize;
			ItemStack copy = stack.copy();
			copy.stackSize = newStacksize - maxStacksize;
			return copy;
		}

		if (!simulate)
			itemStacks[ID_SLOT_INPUT].stackSize = newStacksize;
		return null;
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate) {
		if (slot < ID_SLOT_OUTPUT_FIRST || slot > ID_SLOT_OUTPUT_LAST)
			return null;

		if (itemStacks[slot] == null)
			return null;

		ItemStack stack = itemStacks[slot];

		amount = Math.max(amount, stack.getMaxStackSize());
		int leftover = stack.stackSize - amount;
		if (leftover <= 0) {
			if (!simulate)
				itemStacks[slot] = null;
			return stack;
		}

		if (!simulate)
			stack.stackSize -= amount;

		ItemStack copy = stack.copy();
		copy.stackSize = amount;

		return copy;
	}

	@Override
	public void setStackInSlot(int slot, ItemStack stack) {
		itemStacks[slot] = stack;
		exchange();
	}

	/*
	 * @Override public boolean shouldRefresh(World world, BlockPos pos,
	 * IBlockState oldState, IBlockState newState) {
	 * 
	 * return (oldState.getBlock() != newState.getBlock());
	 * 
	 * }
	 */

}
