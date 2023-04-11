package net.sssssssthedev.SmartClient.ui.splash;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.ResourceLocation;
import net.sssssssthedev.SmartClient.utils.ShaderUtils;
import org.lwjgl.opengl.GL20;


public class SplashScreen {

    public void drawShader() {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        int i = scaledResolution.getScaleFactor();
        Framebuffer framebuffer = new Framebuffer(scaledResolution.getScaledWidth() * i, scaledResolution.getScaledHeight() * i, true);
        framebuffer.bindFramebuffer(false);
        GlStateManager.matrixMode(5889);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.0D, scaledResolution.getScaledWidth(), scaledResolution.getScaledHeight(), 0.0D, 1000.0D, 3000.0D);
        GlStateManager.matrixMode(5888);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0.0F, 0.0F, -2000.0F);
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        GlStateManager.disableDepth();
        GlStateManager.enableTexture2D();
        ShaderUtils.loadShader(new ResourceLocation("smartclient/shaders/shader.fsh"));
        ShaderUtils.renderShader(scaledResolution.getScaledWidth() + i, scaledResolution.getScaledHeight() + i, (float)(System.currentTimeMillis() - System.currentTimeMillis()) / 1000.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().draw((scaledResolution.getScaledWidth() - 256) / 2, (scaledResolution.getScaledHeight() - 256) / 2, 0, 0, 256, 256, 255, 255, 255, 255);
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        framebuffer.unbindFramebuffer();
        framebuffer.framebufferRender(scaledResolution.getScaledWidth() * i, scaledResolution.getScaledHeight() * i);
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.1F);
        Minecraft.getMinecraft().updateDisplay();
        GL20.glUseProgram(0);
    }
}
