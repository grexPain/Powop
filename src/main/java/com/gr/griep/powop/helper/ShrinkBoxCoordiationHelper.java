package com.gr.griep.powop.helper;

import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

import com.gr.griep.powop.util.Vector2i;
import com.gr.griep.powop.world.gen.ChunkProviderInnerShrinkBox;

public class ShrinkBoxCoordiationHelper {
	
	

	public static Vector2i getCellCoordinate(int index) {

//		int size = 0;
//		while (Math.pow(size, 2) <= index) {
//			size += 2;
//		}
//		index -= Math.pow(size - 1, 2);
//		int edge = index / 4;
//		index %= 4;
//		int edgeStartX;
//		int edgeStartZ;
//		int edgeDeltaX;
//		int edgeDeltaZ;
//		switch (edge) {
//		case 0:
//			edgeStartX = 
//			break;
//
//		default:
//			break;
//		}
		
		// TODO proper cell distribution

		return new Vector2i(index, 0);
	}
	
	public static int getIndex(Vector2i cellCoordinate) {
		// TODO proper cell distribution
		
		return cellCoordinate.getX();
	}
	
	public static Vector2i getCenterChunk(Vector2i cellCoordinate) {
		int distanceX = ChunkProviderInnerShrinkBox.SIZE_X + 1;
		int distanceZ = ChunkProviderInnerShrinkBox.SIZE_Z + 1;
		return new Vector2i(distanceX*cellCoordinate.getX() + (distanceX/2),
							distanceZ*cellCoordinate.getZ() + (distanceZ/2));
	}
	
	public static Vector2i getEntryChunk(Vector2i cellCoordinate) {
		int distanceX = ChunkProviderInnerShrinkBox.SIZE_X + 1;
		int distanceZ = ChunkProviderInnerShrinkBox.SIZE_Z + 1;
		return new Vector2i(distanceX*cellCoordinate.getX() + (distanceX/2),
							distanceZ*cellCoordinate.getZ() + distanceZ);
	}
	
	public static Vec3d getEntryPosition(Vector2i cellCoordinate) {
		Vector2i entryChunk = getEntryChunk(cellCoordinate);
		return new Vec3d(entryChunk.getX() * 16 + 8, ChunkProviderInnerShrinkBox.ENTRY_HEIGHT, entryChunk.getZ() * 16 + .5);		
	}
	
	public static Vec3d getEntryPosition(int index) {
		return getEntryPosition(getCellCoordinate(index));
	}

}