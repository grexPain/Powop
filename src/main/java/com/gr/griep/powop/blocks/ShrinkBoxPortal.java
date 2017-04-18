package com.gr.griep.powop.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.gr.griep.powop.blocks.teleporter.PowopTeleporter;
import com.gr.griep.powop.init.PowopDimensions;

import net.minecraft.block.BlockPortal;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class ShrinkBoxPortal extends ModBlockEmptyDrops {

	public ShrinkBoxPortal(Material materialIn) {
		super(materialIn);
	}

	@Override
	@Deprecated
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox,
			List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn) {
        addCollisionBoxToList(pos, entityBox, collidingBoxes, new AxisAlignedBB(.1, 0, .1, .9, 1, .9));
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		
		if(worldIn.isRemote)
			return;
		
		if(!(entityIn instanceof EntityPlayer))
			return;
		
		EntityPlayer playerIn = (EntityPlayer)entityIn;
				
		double x = 0;
		double y = 74;
		double z = 0;
		
        int oldDimension = playerIn.getEntityWorld().provider.getDimension();
        EntityPlayerMP entityPlayerMP = (EntityPlayerMP) playerIn;
        MinecraftServer server = playerIn.getEntityWorld().getMinecraftServer();
        WorldServer worldServer = server.worldServerForDimension(DimensionType.OVERWORLD.getId());
        worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension(entityPlayerMP, DimensionType.OVERWORLD.getId(), new PowopTeleporter(worldServer, x, y, z));
        playerIn.setPositionAndUpdate(x, y, z);
	}

	@Override
	@Deprecated
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return new AxisAlignedBB(0, 0, 0, 0, 0, 0);
	}
	
	
	
	
	
	
}
