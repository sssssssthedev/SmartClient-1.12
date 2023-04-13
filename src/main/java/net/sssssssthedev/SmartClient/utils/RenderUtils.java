package net.sssssssthedev.SmartClient.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL11.*;

public class RenderUtils {

    public RenderUtils() {
        throw new IllegalStateException("You can't instantiate a utility class.");
    }

    public static void drawImage(float x, float y, float u, float v, float width, float height, float textureWidth, float textureHeight) {
        float wScale = 1.0F / textureWidth;
        float hScale = 1.0F / textureHeight;

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufferBuilder = tessellator.getBuffer();

        bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        bufferBuilder.pos(x, y + height, 0.0D).tex(u * wScale, (v + height) * hScale).endVertex();
        bufferBuilder.pos(x + width, y + height, 0.0D).tex((u + width) * wScale, ((v + height) * hScale)).endVertex();
        bufferBuilder.pos(x + width, y, 0.0D).tex((u + width) * wScale, v * hScale).endVertex();
        bufferBuilder.pos(x, y, 0.0D).tex(u * wScale, v * hScale).endVertex();
        tessellator.draw();
    }

    public static void drawImage(ResourceLocation image, int x, int y, int width, int height, float opacity) {
        glPushMatrix();
        glDisable(GL_DEPTH_TEST);
        glEnable(GL_BLEND);
        glDepthMask(false);
        OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, 1, 0);
        glColor4f(1.0f, 1.0f, 1.0f, opacity / 255);
        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, width, height, width, height);
        glDepthMask(true);
        glDisable(GL_BLEND);
        glEnable(GL_DEPTH_TEST);
        glPopMatrix();
    }

    public static boolean isHovered(int x, int y, int w, int h, int mouseX, int mouseY) {
        return (mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h);
    }
}
