package com.gr.griep.powop.handler;

import com.gr.griep.powop.client.gui.inventory.GuiOreDictExchanger;
import com.gr.griep.powop.gui.AssemblyScreen;
import com.gr.griep.powop.gui.ExampleGuiScreen;
import com.gr.griep.powop.inventory.ContainerOreDictExchanger;
import com.gr.griep.powop.tileentity.TileEntityOreDictExchanger;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class PowopGuiHandler implements IGuiHandler {
	
	public static final int ID_GUI_ORE_EXCHANGER = 0;
	public static final int ID_GUI_TUT = 1;
	public static final int ID_GUI_ASM = 2;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if (ID == ID_GUI_ORE_EXCHANGER)
			return new ContainerOreDictExchanger(player.inventory, (TileEntityOreDictExchanger) world.getTileEntity(new BlockPos(x, y, z)));
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {

		switch(ID){
		case ID_GUI_TUT:
			return new ExampleGuiScreen();
		case ID_GUI_ASM:
			return new AssemblyScreen();
		case ID_GUI_ORE_EXCHANGER:
			return new GuiOreDictExchanger(player.inventory, (TileEntityOreDictExchanger)world.getTileEntity(new BlockPos(x, y, z)));
				//return new GuiOreDictExchanger(new ContainerOreDictExchanger(player.inventory, (TileEntityOreDictExchanger) world.getTileEntity(new BlockPos(x, y, z))));
		}
		
		return null;
	}

}
