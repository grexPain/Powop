package com.gr.griep.powop.blocks;

import javax.annotation.Nullable;

import com.gr.griep.powop.items.ItemCoordinateCache;
import com.gr.griep.powop.tileentity.TileEntityCoordTransporter;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCoordTransporter extends ModBlock implements ITileEntityProvider {

	public BlockCoordTransporter() {
		
		super(Material.GLASS);
		setSoundType(SoundType.GLASS);
	}

	@Override
	public boolean isOpaqueCube(IBlockState blockState) {
		return false;
	}

	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos,
			IBlockState state, EntityPlayer playerIn, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side,
			float hitX, float hitY, float hitZ) {
		
		if(heldItem != null){
			
			Item item = heldItem.getItem();
			if(item instanceof ItemCoordinateCache) {
				
				if(item.hasEffect(heldItem)) {
					
					TileEntityCoordTransporter tect = (TileEntityCoordTransporter)worldIn.getTileEntity(pos);
					tect.addEntry("poop", heldItem);
					heldItem.stackSize--;
					playerIn.addChatMessage(new TextComponentString("added coordinate cache"));
				}
				
				return true;
			}
		}
		
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		
		return new TileEntityCoordTransporter();
	}
	
	

}
