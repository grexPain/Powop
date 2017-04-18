package com.gr.griep.powop;

import com.gr.griep.powop.items.ItemCoordinateCache;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public class CoordEntry {

	private String name;
	private int dimension;
	private BlockPos position;
	
	public CoordEntry(String name, int dimension, int x, int y, int z) {
		
		this.name = name;
		this.dimension = dimension;
		this.position = new BlockPos(x, y, z);
	}

	public String getName() {
		return name;
	}

	public int getDimension() {
		return dimension;
	}
	
	public BlockPos getPosition() {
		
		return position;
	}
	
	public NBTTagCompound toNBTTagCompund() {
		
		NBTTagCompound coords = new NBTTagCompound();
		
		coords.setString(ItemCoordinateCache.KEY_NAME, name);
		coords.setInteger(ItemCoordinateCache.KEY_DIM, dimension);
		coords.setInteger(ItemCoordinateCache.KEY_POS_X, position.getX());
		coords.setInteger(ItemCoordinateCache.KEY_POS_Y, position.getY());
		coords.setInteger(ItemCoordinateCache.KEY_POS_Z, position.getZ());
		
		return coords;
	}
	
	public static CoordEntry fromNBTTagCompound(NBTTagCompound compound) {
		
		return new CoordEntry(
				compound.getString(ItemCoordinateCache.KEY_NAME),
				compound.getInteger(ItemCoordinateCache.KEY_DIM),
				compound.getInteger(ItemCoordinateCache.KEY_POS_X),
				compound.getInteger(ItemCoordinateCache.KEY_POS_Y),
				compound.getInteger(ItemCoordinateCache.KEY_POS_Z));
	}
}
