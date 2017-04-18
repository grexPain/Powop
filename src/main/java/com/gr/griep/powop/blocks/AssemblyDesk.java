package com.gr.griep.powop.blocks;

import javax.annotation.Nullable;

import com.gr.griep.powop.PowopMod;
import com.gr.griep.powop.handler.PowopGuiHandler;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class AssemblyDesk extends ModBlock {
	
	//private IIcon[] icons;
    public static final PropertyDirection FACING = BlockHorizontal.FACING;

	public AssemblyDesk() {
		super(Material.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		//setUnlocalizedName("asm_desk");
	}

	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos,
			IBlockState state, EntityPlayer playerIn, EnumHand hand,
			@Nullable ItemStack heldItem, EnumFacing side, float hitX,
			float hitY, float hitZ) {

		playerIn.openGui(PowopMod.INSTANCE, PowopGuiHandler.ID_GUI_ASM, worldIn, pos.getX(), pos.getY(), pos.getZ());
		
		return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem,
				side, hitX, hitY, hitZ);
	}















	@Override
	@Deprecated
	public boolean isOpaqueCube(IBlockState state) {
		// TODO Auto-generated method stub
		return false;
	}

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {	
        EnumFacing enumfacing = placer.getHorizontalFacing().rotateY();
        
		return super.onBlockPlaced(worldIn, pos, enumfacing, hitX, hitY, hitZ, meta, placer).withProperty(FACING, enumfacing);
    }

	@Override
	protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {FACING});
	}
	
	/**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }
	
    

/*	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		// TODO Auto-generated method stub
		return side==1?icons[1]:icons[0];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		icons = new IIcon[2];
		icons[0]=iconRegister.registerIcon("minecraft:planks_oak");
		icons[1]=iconRegister.registerIcon(getTextureName()+"_top");
	}
	*/
	

}
