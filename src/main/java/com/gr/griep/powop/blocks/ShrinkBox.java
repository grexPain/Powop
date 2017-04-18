package com.gr.griep.powop.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.gr.griep.powop.blocks.teleporter.PowopTeleporter;
import com.gr.griep.powop.helper.ShrinkBoxTeleportationHelper;
import com.gr.griep.powop.init.PowopDimensions;
import com.gr.griep.powop.tileentity.TileEntityOreDictExchanger;
import com.gr.griep.powop.tileentity.TileEntityShrinkBox;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ShrinkBox extends ModBlock {

    public static final PropertyDirection FACING = BlockHorizontal.FACING;

	public ShrinkBox() {
		super(Material.IRON);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
	}

    /**
     * Called by ItemBlocks just before a block is actually set in the world, to allow for adjustments to the
     * IBlockstate
     */
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
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

    @Override
	@Deprecated
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(.032, 0, .032, .968, 1, .968);
	}

	/**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }
    
    

//	@Override
//	@Deprecated
//	@Nullable
//	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos) {
//		double start = (double)6/16;
//		double end = (double)10/16;
//		return new AxisAlignedBB(start, 0, start, end, 1, end);// super.getCollisionBoundingBox(blockState, worldIn, pos);
//	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {

		if(!(playerIn instanceof EntityPlayerMP))
				return true;
		
		EntityPlayerMP playerMP = (EntityPlayerMP)playerIn;
		ShrinkBoxTeleportationHelper.teleportToCell(0, playerMP);
		
//		if(worldIn.isRemote)
//			return true;
//		
//		double x = 0;
//		double y = 150;
//		double z = 0;
//		
//        int oldDimension = playerIn.getEntityWorld().provider.getDimension();
//        EntityPlayerMP entityPlayerMP = (EntityPlayerMP) playerIn;
//        MinecraftServer server = playerIn.getEntityWorld().getMinecraftServer();
//        WorldServer worldServer = server.worldServerForDimension(PowopDimensions.ID_DIM_INNER_SHRINK_BOX);
//        worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension(entityPlayerMP, PowopDimensions.ID_DIM_INNER_SHRINK_BOX, new PowopTeleporter(worldServer, x, y, z));
//        playerIn.setPositionAndUpdate(x, y, z);
		return true;
	}

	@Override
	@Deprecated
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn) {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(.375, 0, .375, .625, 1, .625));
        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(.032, 0, .032, .968, .0625, .968));
		double outside = .0625;
		double inside = .1875;
		double distance = .3125;
		EnumFacing facing = ((EnumFacing)state.getValue(FACING));
		switch (((EnumFacing)state.getValue(FACING)).getIndex()) {
		case 2:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(distance, 0, 1-outside, 1-distance, .575, 1-inside));
			break;
		case 3:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(distance, 0, outside, 1-distance, .575, inside));
			break;
		case 4:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(1-outside, 0, distance, 1-inside, .575, 1-distance));
			break;
		case 5:
			addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(outside, 0, distance, inside, .575, 1-distance));
			break;
		}
		
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.CUTOUT;
	}

	@Override
	@Deprecated
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
		return worldIn.provider.getDimension() != PowopDimensions.ID_DIM_INNER_SHRINK_BOX;
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityShrinkBox();
	}

	
}
