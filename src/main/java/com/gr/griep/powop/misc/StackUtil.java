package com.gr.griep.powop.misc;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;

public class StackUtil {

    /** Checks to see if the two input stacks are equal in all but stack size. Note that this doesn't check anything
     * todo with stack size, so if you pass in two stacks of 64 cobblestone this will return true. If you pass in null
     * (at all) then this will only return true if both are null. */
    public static boolean canMerge(ItemStack a, ItemStack b) {
    	/*if(a==null||b==null)
    		return true;*/
        // Checks item, damage
        if (!ItemStack.areItemsEqual(a, b)) {
            return false;
        }
        // checks tags and caps
        return ItemStack.areItemStackTagsEqual(a, b);
    }
    
    public static ItemStack mergeStack(ItemStack stack, ItemStack[] stackarray, int start, int end) {
    	
    	if(stack == null)
    		return stack;
    	
    	int maxStackSize = stack.getMaxStackSize();
    	
    	for (int i = start; i <= end; i++) {
			if(stackarray[i] == null) {
				stackarray[i] = stack;
				return null;
			}
			if(canMerge(stack, stackarray[i])) {
				if(stackarray[i].stackSize == maxStackSize)
					continue;
				int totalStackSize = stackarray[i].stackSize + stack.stackSize;
				if(totalStackSize > maxStackSize) {
					stackarray[i].stackSize = maxStackSize;
					stack.stackSize = totalStackSize-maxStackSize;
				}
				else {
					stackarray[i].stackSize = totalStackSize;
					return null;
				}
			}
		}		
    	
    	return stack;
    }
}
