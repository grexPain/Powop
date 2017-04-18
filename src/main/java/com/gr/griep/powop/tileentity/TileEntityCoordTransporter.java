package com.gr.griep.powop.tileentity;


import java.util.ArrayList;
import java.util.List;

import com.gr.griep.powop.CoordEntry;
import com.gr.griep.powop.items.ItemCoordinateCache;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityCoordTransporter extends TileEntity {

	private List<CoordEntry> teleports = new ArrayList<CoordEntry>();
	
	public static final String KEY_TELEPORTS = "teleports";
	
	public void addEntry (String name, ItemStack coordCache) {
		
		NBTTagCompound root = coordCache.getTagCompound();		
		NBTTagCompound coords = root.getCompoundTag(ItemCoordinateCache.KEY_COORDS);
		
		int dimension = coords.getInteger(ItemCoordinateCache.KEY_DIM);
		int posX = coords.getInteger(ItemCoordinateCache.KEY_POS_X);
		int posY = coords.getInteger(ItemCoordinateCache.KEY_POS_Y);
		int posZ = coords.getInteger(ItemCoordinateCache.KEY_POS_Z);
		
		teleports.add(new CoordEntry(name, dimension, posX, posY, posZ));
	}
	
	public CoordEntry getEntry(int i) {
		
		if(i < teleports.size())
			return teleports.get(i);
		
		return null;
	}
	
	public void deleteEntry(int i) {
		
		if(i< teleports.size())
			teleports.remove(i);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		
		super.readFromNBT(compound);
		
		teleports = new ArrayList<CoordEntry>();
		
		NBTTagList entryList = (NBTTagList)compound.getTag(KEY_TELEPORTS);
		for (int i = 0; i < entryList.tagCount(); i++) {
			
			//NBTTagCompound entryCompound = entryList.getCompoundTagAt(i);
			
			//CoordEntry entry = CoordEntry.fromNBTTagCompound(entryCompound);
			teleports.add(null);
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {

		super.writeToNBT(compound);
		
		NBTTagList entryList = new NBTTagList();
		for (CoordEntry entry : teleports) {
			
			entryList.appendTag(entry.toNBTTagCompund());
		}
		
		compound.setTag(KEY_TELEPORTS, entryList);
		
		return compound;
	}
}
