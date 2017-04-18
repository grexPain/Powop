package com.gr.griep.powop.client.gui.inventory;

import com.gr.griep.powop.PowopMod;
import com.gr.griep.powop.inventory.ContainerOreDictExchanger;
import com.gr.griep.powop.tileentity.TileEntityOreDictExchanger;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GuiOreDictExchanger extends GuiContainer {
	
	private ResourceLocation oreExchangerBackdrop = new ResourceLocation(
			PowopMod.MOD_ID, "textures/gui/gui_ore_exchanger.png");
	
    private final InventoryPlayer playerInventory;
    private final TileEntityOreDictExchanger tileOreExchanger;

	public GuiOreDictExchanger(InventoryPlayer playerInventory, TileEntityOreDictExchanger exchangerInventory) {
		super(new ContainerOreDictExchanger(playerInventory, exchangerInventory));
		
		this.playerInventory = playerInventory;
		this.tileOreExchanger = exchangerInventory;
		
		xSize = 176;
		ySize = 210;
	}

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
	@Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = this.tileOreExchanger.getDisplayName().getUnformattedText();
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks,
			int mouseX, int mouseY) {
		
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
		
		mc.getTextureManager().bindTexture(oreExchangerBackdrop);
		
		drawTexturedModalRect(i, j, 0, 0, xSize, ySize);
		
	}

}
