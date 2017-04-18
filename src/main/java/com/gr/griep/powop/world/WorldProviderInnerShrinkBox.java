package com.gr.griep.powop.world;

import com.gr.griep.powop.init.PowopBiomes;
import com.gr.griep.powop.init.PowopDimensions;
import com.gr.griep.powop.world.gen.ChunkProviderInnerShrinkBox;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeProviderSingle;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderInnerShrinkBox extends WorldProvider {

	@Override
	public DimensionType getDimensionType() {
		return PowopDimensions.TYPE_DIM_INNER_SHRINK_BOX;
	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkProviderInnerShrinkBox(this.worldObj);
	}
	
	@Override
	protected void createBiomeProvider() {
        this.biomeProvider = new BiomeProviderSingle(PowopBiomes.BIOME_SHRINK_BOX);
        //this.hasNoSky = true;
	}
	
	

//	public Biome getBiomeGenForCoords(BlockPos pos) {
//		return ;
//	}

	@Override
	protected void generateLightBrightnessTable() {
        float f = 0.1F;

        for (int i = 0; i <= 15; ++i)
        {
            float f1 = 1.0F - (float)i / 15.0F;
            this.lightBrightnessTable[i] = 1-i/30f;//1;//(1.0F - f1) / (f1 * 3.0F + 1.0F) * 0.9F + 0.1F;
        }
	}

	@Override
	public boolean canRespawnHere() {
		return false;
	}

	@Override
	public int getRespawnDimension(EntityPlayerMP player) {
		return DimensionType.OVERWORLD.getId();
	}

	@Override
	public boolean isSurfaceWorld() {
		return false;
	}

	@Override
	public float calculateCelestialAngle(long worldTime, float partialTicks) {
		return 0;
	}

	@Override
	public String getWelcomeMessage() {
		return "Shrinking in progress...";
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Vec3d getFogColor(float p_76562_1_, float p_76562_2_) {
		return new Vec3d(.3125, .28125, .3171);
	}
}
