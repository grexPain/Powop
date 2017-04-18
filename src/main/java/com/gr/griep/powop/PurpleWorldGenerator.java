package com.gr.griep.powop;

import java.util.Random;

import com.gr.griep.powop.init.PowopBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class PurpleWorldGenerator implements IWorldGenerator {
	
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		
        switch(world.provider.getDimension()){
        case -1:
            generateNether(world, random, chunkX * 16, chunkZ * 16);
            break;
        case 0:
            generateSurface(world, random, chunkX * 16, chunkZ * 16);
            break;
        case 1:
            generateEnd(world, random, chunkX * 16, chunkZ * 16);
            break;
        }
	}

	private void generateEnd(World world, Random rand, int x, int z) {}

	private void generateSurface(World world, Random random, int x, int z) {

		addOreSpawn(PowopBlocks.PURPLE_ORE, world, random, x, z, 16, 16, 8, 20, 0, 48);
		addOreSpawn(PowopBlocks.SHINY_PURPLE_ORE, world, random, x, z, 16, 16, 5, 10, 40, 128);
	}

	private void generateNether(World world, Random rand, int x, int z) {

//		addOreSpawn(PowopBlocks.PURPLE_ORE, world, rand, x, z, 16, 16, 8, 30, 0, 128, Blocks.NETHERRACK);
	}

	
	public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY ) {
		
		for(int i = 0; i < chancesToSpawn; i++) {
			
			int posX = blockXPos + random.nextInt(maxX);
			int posY = minY + random.nextInt(maxY -minY);
			int posZ = blockZPos + random.nextInt(maxZ);
			
			new WorldGenMinable(block.getDefaultState(), maxVeinSize).generate(world, random, new BlockPos(posX, posY, posZ));
		}
	}

	
	public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY, Block replace ) {
		
		for(int i = 0; i < chancesToSpawn; i++) {
			
			int posX = blockXPos + random.nextInt(maxX);
			int posY = minY + random.nextInt(maxY -minY);
			int posZ = blockZPos + random.nextInt(maxZ);
			
			new WorldGenMinable(block.getDefaultState(), maxVeinSize, BlockMatcher.forBlock(replace)).generate(world, random, new BlockPos(posX, posY, posZ));
		}
	}
}
