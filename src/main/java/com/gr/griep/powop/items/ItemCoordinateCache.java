package com.gr.griep.powop.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import com.gr.griep.powop.PowopMod;

import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCoordinateCache extends Item {
	
	public static final String KEY_COORDS = "coordinates";
	
	public static final String KEY_NAME = "name";
	public static final String KEY_DIM = "dimension";
	public static final String KEY_POS_X = "positionX";
	public static final String KEY_POS_Y = "positionY";
	public static final String KEY_POS_Z = "positionZ";

	/**
	 * Called when a Block is right-clicked with this Item
	 * 
	 * @param pos
	 *            The block being right-clicked
	 * @param side
	 *            The side being right-clicked
	 */
	@Override
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		
		if(!playerIn.isSneaking()) {
			
			NBTTagCompound root = stack.getTagCompound();
			
			if(root == null) {
				
				root = new NBTTagCompound();
				stack.setTagCompound(root);
			}
			
			NBTTagCompound coords = new NBTTagCompound();
			coords.setString(KEY_NAME, worldIn.provider.getDimensionType().toString());
			coords.setInteger(KEY_DIM, playerIn.dimension);
			coords.setInteger(KEY_POS_X, pos.getX());
			coords.setInteger(KEY_POS_Y, pos.getY());
			coords.setInteger(KEY_POS_Z, pos.getZ());
			
			root.setTag(KEY_COORDS, coords);
			
			stack.clearCustomName();
			stack.setStackDisplayName(TextFormatting.AQUA + stack.getDisplayName());
		}
		return EnumActionResult.FAIL;
	}
	
	
	/*public boolean onItemUse(ItemStack stack, EntityPlayer playerIn,
			World worldIn, BlockPos pos, EnumFacing side, float hitX,
			float hitY, float hitZ) {
		return onItemUse(stack, playerIn, worldIn, pos, side, hitX, hitY, hitZ);
	}*/

	@Override
	public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World worldIn, EntityPlayer playerIn, EnumHand hand) {

		if(playerIn.isSneaking()) {
			
			NBTTagCompound root = stack.getTagCompound();
			
			if(root != null) {
			
				root.removeTag(KEY_COORDS);
				stack.clearCustomName();
			}
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer playerIn,
			List<String> tooltip, boolean advanced) {

		NBTTagCompound root = stack.getTagCompound();
		if(root == null)
			return;
		
		if(!root.hasKey(KEY_COORDS))
			return;
		
		NBTTagCompound coords = root.getCompoundTag(KEY_COORDS);
		String dimension = coords.getString(KEY_NAME);
		int posX = coords.getInteger(KEY_POS_X);
		int posY = coords.getInteger(KEY_POS_Y);
		int posZ = coords.getInteger(KEY_POS_Z);
		
		tooltip.add("Dimension " + dimension);
		tooltip.add("X: " + posX);		
		tooltip.add("Y: " + posY);		
		tooltip.add("Z: " + posZ);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasEffect(ItemStack stack) {

		NBTTagCompound root = stack.getTagCompound();
		if(root == null)
			return false;
		
		if(root.hasKey(KEY_COORDS))
			return true;
		
		return false;
	}

/*	@Override
	@SideOnly(Side.CLIENT)
	protected String getIconString() {
        return this.iconString == null ?
        		PowopMod.MOD_ID + ":" + (getUnlocalizedName().substring(5)) : this.iconString;
	}*/
}