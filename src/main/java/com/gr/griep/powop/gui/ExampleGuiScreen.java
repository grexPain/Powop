package com.gr.griep.powop.gui;

import java.io.IOException;

import com.gr.griep.powop.PowopMod;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class ExampleGuiScreen extends GuiScreen {

	public static final int ID_BTN_INC = 1;
	public static final int ID_BTN_DEC = 2;
	
	private ResourceLocation exampleGuiBackdrop = new ResourceLocation(
			PowopMod.MOD_ID, "textures/gui/mcGuiEmpty.png");

	private int counter = 0;

	public ExampleGuiScreen() {

	}

	@Override
	public void initGui() {

		buttonList.add(new GuiButton(ID_BTN_INC, width/2+15, height/2-10, 20, 20, "+"));
		buttonList.add(new GuiButton(ID_BTN_DEC, width/2-15, height/2-10, 20, 20, "-"));
		
		super.initGui();
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {

		if(typedChar == 'e')
			mc.thePlayer.closeScreen();
		
		super.keyTyped(typedChar, keyCode);
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {

		switch(button.id){
		case ID_BTN_INC:
			counter++;
			break;
		case ID_BTN_DEC:
			counter--;
			break;
		}
		
		super.actionPerformed(button);
	}

	@Override
	public void updateScreen() {
		// TODO Auto-generated method stub
		super.updateScreen();
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {

		int gLeft = width/2-96;
		int gTop = height/2-68;
		int gWidth = 192;
		int gHeight = 136;
		
		this.drawDefaultBackground();
		
		mc.getTextureManager().bindTexture(exampleGuiBackdrop);
		
		drawTexturedModalRect(gLeft, gTop, 0, 0, 256, 256);
		
		fontRendererObj.drawString(""+counter, gLeft + 10, gTop + 10, 0x000000);
		
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

}
