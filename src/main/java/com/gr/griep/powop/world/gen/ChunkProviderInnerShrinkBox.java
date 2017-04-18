package com.gr.griep.powop.world.gen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.gr.griep.powop.init.PowopBlocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;

;

public class ChunkProviderInnerShrinkBox implements IChunkGenerator {

	public static final int SIZE_X = 3;
	public static final int SIZE_Z = 3;
	public static final int ENTRY_HEIGHT = 128;
	protected static final IBlockState AIR = Blocks.AIR.getDefaultState();
	protected static final IBlockState SHRINK_BOX_SHELL = PowopBlocks.SHRINK_BOX_SHELL.getDefaultState();
	protected static final IBlockState SHRINK_BOX_PORTAL = PowopBlocks.SHRINK_BOX_PORTAL.getDefaultState();

	private final World worldObj;

	// private Random random;
	// private final boolean mapFeaturesEnabled;
	// private Biome[] biomesForGeneration;

	public ChunkProviderInnerShrinkBox(World worldObjIn) {
		this.worldObj = worldObjIn;
		// this.mapFeaturesEnabled = mapFeaturesEnabledIn;
		// this.random = new Random(seed);

		// net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextEnd ctx
		// =
		// new
		// net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextEnd(lperlinNoise1,
		// lperlinNoise2, perlinNoise1, noiseGen5, noiseGen6, islandNoise);
		// ctx =
		// net.minecraftforge.event.terraingen.TerrainGen.getModdedNoiseGenerators(worldObjIn,
		// this.rand, ctx);
	}

	@Override
	public Chunk provideChunk(int x, int z) {
		ChunkPrimer primer = new ChunkPrimer();
		buildShell(x, z, primer);

		Chunk chunk = new Chunk(worldObj, primer, x, z);
		Biome[] abiome = worldObj.getBiomeProvider().getBiomes((Biome[]) null, x * 16, z * 16, 16, 16);
		byte[] abyte = chunk.getBiomeArray();

		for (int i = 0; i < abyte.length; ++i) {
			abyte[i] = (byte) Biome.getIdForBiome(abiome[i]);
		}

		//chunk.resetRelightChecks();

		return chunk;
	}

	private void buildShell(int x, int z, ChunkPrimer primer) {
		boolean isShellChunk = x % (SIZE_X + 1) == 0 || z % (SIZE_Z + 1) == 0;

		if (isShellChunk)
			fillChunk(primer, SHRINK_BOX_SHELL);
		else {
			fillLayer(primer, SHRINK_BOX_SHELL, 0);
			fillLayer(primer, SHRINK_BOX_SHELL, 255);
		}

		boolean isEntryChunk = isShellChunk && Math.abs(x) % (SIZE_X + 1) == ((SIZE_X + 1) / 2)
				&& z % (SIZE_Z + 1) == 0;

		if (isEntryChunk)
			putEntry(primer);
	}

	private void putEntry(ChunkPrimer primer) {
		fillArea(primer, 7, ENTRY_HEIGHT, 0, 8, ENTRY_HEIGHT+2, 0, AIR);
		fillArea(primer, 0, ENTRY_HEIGHT-8, 1, 15, ENTRY_HEIGHT+10, 9, AIR);
		fillArea(primer, 7, ENTRY_HEIGHT, 1, 8, ENTRY_HEIGHT+2, 1, SHRINK_BOX_PORTAL);
	}

	private void fillArea(ChunkPrimer primer, int x1, int y1, int z1, int x2, int y2, int z2, IBlockState blockState) {
		for (int x = x1; x <= x2; x++) {
			for (int y = y1; y <= y2; y++) {
				for (int z = z1; z <= z2; z++) {
					primer.setBlockState(x, y, z, blockState);
				}
			}
		}
	}

	private void fillChunk(ChunkPrimer primer, IBlockState blockState) {
		for (int x = 0; x < 16; x++) {
			for (int y = 0; y < 256; y++) {
				for (int z = 0; z < 16; z++) {
					primer.setBlockState(x, y, z, blockState);
				}
			}
		}
	}

	private void fillLayer(ChunkPrimer primer, IBlockState blockState, int y) {
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				primer.setBlockState(x, y, z, blockState);
			}
		}
	}

	@Override
	public void populate(int x, int z) {

	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		return new ArrayList<SpawnListEntry>();
	}

	@Override
	@Nullable
	public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position) {
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
	}

}
