package com.gr.griep.powop.helper;

import java.util.Random;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RandomHelper {
	
	@SideOnly(Side.SERVER)
	public static int getRandomInt(int max){
		return new Random().nextInt(max);
	}

}
