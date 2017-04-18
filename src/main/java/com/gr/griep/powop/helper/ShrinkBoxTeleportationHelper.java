package com.gr.griep.powop.helper;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldServer;

import com.gr.griep.powop.blocks.teleporter.PowopTeleporter;
import com.gr.griep.powop.init.PowopDimensions;

public class ShrinkBoxTeleportationHelper {

	public static void teleportToCell(int index, EntityPlayerMP playerMP) {
		
		Vec3d entryPosition = ShrinkBoxCoordiationHelper.getEntryPosition(index);
		
        //EntityPlayerMP entityPlayerMP = (EntityPlayerMP) playerMP;
        MinecraftServer server = playerMP.getEntityWorld().getMinecraftServer();
        WorldServer worldServer = server.worldServerForDimension(PowopDimensions.ID_DIM_INNER_SHRINK_BOX);
        worldServer.getMinecraftServer().getPlayerList().transferPlayerToDimension(playerMP, PowopDimensions.ID_DIM_INNER_SHRINK_BOX, new PowopTeleporter(worldServer, entryPosition.xCoord, entryPosition.yCoord, entryPosition.zCoord));
        playerMP.setPositionAndUpdate(entryPosition.xCoord, entryPosition.yCoord, entryPosition.zCoord);
        playerMP.setRotationYawHead(180);
	}
	
}
