package net.sssssssthedev.SmartClient.ui.mainmenu;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.sssssssthedev.SmartClient.utils.BuildInfo;
import net.sssssssthedev.SmartClient.utils.RenderUtils;

import java.io.IOException;

public class MainMenuScreen extends GuiScreen {

    public ResourceLocation background, singleplayer, multiplayer, settings, quit, login;

    public MainMenuScreen() {
        background = new ResourceLocation("smartclient/images/mainmenu.jpg");
        singleplayer = new ResourceLocation("smartclient/images/singleplayer.png");
        multiplayer = new ResourceLocation("smartclient/images/multiplayer.png");
        settings = new ResourceLocation("smartclient/images/settings.png");
        quit = new ResourceLocation("smartclient/images/quit.png");
        login = new ResourceLocation("smartclient/images/login.png");
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        // Yes I know there is a better way to do this than to hardcode the coords, but i'm to bored it to do it another way
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        // SIngleplayer button
        if (RenderUtils.isHovered( scaledResolution.getScaledWidth() / 2 - 12 - 32, scaledResolution.getScaledHeight() / 2 - 30, 24, 24, mouseX, mouseY)) {
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            Minecraft.getMinecraft().displayGuiScreen(new GuiWorldSelection(this));
        }
        // Multiplayer button
        if (RenderUtils.isHovered( scaledResolution.getScaledWidth() / 2 - 12, scaledResolution.getScaledHeight() / 2 - 30, 24, 24, mouseX, mouseY)) {
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            Minecraft.getMinecraft().displayGuiScreen(new GuiMultiplayer(this));
        }
        // Settings button
        if (RenderUtils.isHovered( scaledResolution.getScaledWidth() / 2 - 12 + 32, scaledResolution.getScaledHeight() / 2 - 30, 24, 24, mouseX, mouseY)) {
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            Minecraft.getMinecraft().displayGuiScreen(new GuiOptions(this, mc.gameSettings));
        }
        // Quit button
        if (RenderUtils.isHovered( scaledResolution.getScaledWidth() / 2 - 12, scaledResolution.getScaledHeight() / 2, 24, 24, mouseX, mouseY)) {
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
            mc.shutdown();
        }
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
        RenderUtils.drawImage(login, scaledResolution.getScaledWidth() / 14, scaledResolution.getScaledHeight() /2 + 210, 24, 24, 255);
        GlStateManager.pushMatrix();
        GlStateManager.translate(width/2f, height/2f, 0);
        GlStateManager.scale(3, 3, 1);
        GlStateManager.translate(-(width/2f), -(height/2f), 0);
        drawCenteredString(Minecraft.getMinecraft().fontRendererObj, String.format("%s §c%s", BuildInfo.getName().replace("S", "§cS§f"), BuildInfo.getVersion()), width/2, height/2 - 20 - Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT/2, -1);
        GlStateManager.popMatrix();
        drawCenteredString(Minecraft.getMinecraft().fontRendererObj, "§cCopyright (C) sssssss.dev 2023-2024", width / 2, height/2 + 230, -1);
        drawCenteredString(Minecraft.getMinecraft().fontRendererObj, String.format("§e%s/%s", BuildInfo.getBranch(), BuildInfo.getCommit()), width / 2 + 420, height/2 + 230, -1);
    }
}
