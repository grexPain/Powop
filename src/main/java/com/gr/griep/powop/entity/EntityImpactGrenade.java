package com.gr.griep.powop.entity;

import com.gr.griep.powop.achievements.ModAchievements;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityImpactGrenade extends EntityThrowable {

	public EntityImpactGrenade(World worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub
	}
	
	public EntityImpactGrenade(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onImpact(RayTraceResult result) {
		
		if(worldObj.isRemote)
			return;
		float strength = 3;
		if(result.entityHit != null) {
			if(result.entityHit instanceof EntityCreeper) {
				strength = 16;
				EntityLivingBase thrower = getThrower();
				if(thrower != null && thrower instanceof EntityPlayer)
					((EntityPlayer)thrower).addStat(ModAchievements.achieve_creeper_grenade, 1);
			}
			worldObj.createExplosion((Entity)null, result.entityHit.posX, result.entityHit.posY, result.entityHit.posZ, strength, true);
		}
		else {
			BlockPos pos = result.getBlockPos();
			pos = pos.offset(result.sideHit);
			worldObj.createExplosion(this, pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5, strength, true);
		}

		//worldObj.createExplosion((Entity)null, result.hitVec.xCoord, result.hitVec.yCoord, result.hitVec.zCoord, strength, true);

        if (!this.worldObj.isRemote) {
            this.setDead();
        }	
	}

}
