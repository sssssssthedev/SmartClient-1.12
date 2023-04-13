package net.sssssssthedev.SmartClient.ui.mainmenu;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.util.ResourceLocation;
import net.sssssssthedev.SmartClient.utils.BuildInfo;
import net.sssssssthedev.SmartClient.utils.RenderUtils;

import java.io.IOException;

public class MainMenuScreen extends GuiScreen {

    public ResourceLocation background, singleplayer, multiplayer, settings, quit;

    public MainMenuScreen() {
        background = new ResourceLocation("smartclient/images/mainmenu.jpg");
        singleplayer = new ResourceLocation("smartclient/images/singleplayer.png");
        multiplayer = new ResourceLocation("smartclient/images/multiplayer.png");
        settings = new ResourceLocation("smartclient/images/settings.png");
        quit = new ResourceLocation("smartclient/images/quit.png");
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        Minecraft.getMinecraft().getTextureManager().bindTexture(background);
        RenderUtils.drawImage(0, 0, 0, 0, width, height, width, height);
        drawGradientRect(0, height - 100, width, height, 0x00000000, 0xff000000);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        RenderUtils.drawImage(singleplayer, scaledResolution.getScaledWidth() / 2 - 12 - 32, scaledResolution.getScaledHeight() / 2 - 30, 24, 24, 255);
        RenderUtils.drawImage(multiplayer, scaledResolution.getScaledWidth() / 2 - 12, scaledResolution.getScaledHeight() / 2 - 30, 24, 24, 255);
        RenderUtils.drawImage(settings, scaledResolution.getScaledWidth() / 2 - 12 + 32, scaledResolution.getScaledHeight() / 2 - 30, 24, 24, 255);
        RenderUtils.drawImage(quit, scaledResolution.getScaledWidth() / 2 - 12, scaledResolution.getScaledHeight() / 2, 24, 24, 255);
        GlStateManager.pushMatrix();
        GlStateManager.translate(width/2f, height/2f, 0);
        GlStateManager.scale(3, 3, 1);
        GlStateManager.translate(-(width/2f), -(height/2f), 0);
        drawCenteredString(Minecraft.getMinecraft().fontRendererObj, String.format("%s §c%s", BuildInfo.getName().replace("S", "§cS§f"), BuildInfo.getVersion()), width/2, height/2 - 20 - Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT/2, -1);
        GlStateManager.popMatrix();
    }
}
