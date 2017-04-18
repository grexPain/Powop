package com.gr.griep.powop.init;

import com.gr.griep.powop.world.WorldProviderInnerShrinkBox;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class PowopDimensions {

	private static final int ID_DIM_BASE_POWOP = -1615231516;
	private static int requestedIds = 0;
	public static final int ID_DIM_INNER_SHRINK_BOX = getNewId();
    public static DimensionType utilsDimensionType;
    public static final DimensionType TYPE_DIM_INNER_SHRINK_BOX = DimensionType.register("Inner Shrink Box", "_shrinkbox", ID_DIM_INNER_SHRINK_BOX, WorldProviderInnerShrinkBox.class, false);
    
    private static int getNewId() {
    	return ID_DIM_BASE_POWOP + requestedIds++;
    }
	
	public static void init() {
		registerDimensions();
	}

    private static void registerDimensions() {
    	DimensionManager.registerDimension(ID_DIM_INNER_SHRINK_BOX, TYPE_DIM_INNER_SHRINK_BOX);
    }
}