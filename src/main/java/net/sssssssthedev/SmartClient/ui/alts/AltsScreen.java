package net.sssssssthedev.SmartClient.ui.alts;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.sssssssthedev.SmartClient.ui.mainmenu.MainMenuScreen;
import net.sssssssthedev.SmartClient.utils.RenderUtils;
import org.lwjgl.input.Keyboard;

public class AltsScreen extends GuiScreen {

    public ResourceLocation background;
    public GuiTextField username;
    public GuiTextField password;

    public AltsScreen() {
        background = new ResourceLocation("smartclient/images/altsbackground.jpg");
    }

    public void initGui() {
        username = new GuiTextField( height / 4 + 24, mc.fontRendererObj, width / 2 - 12, height / 2, 200, 20);
        username.setFocused(true);
        password = new GuiTextField(height / 4 + 24, mc.fontRendererObj, width / 2 - 12, height / 2, 200, 20);
        password.setFocused(true);

    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        mc.getTextureManager().bindTexture(background);
        RenderUtils.drawImage(0, 0, 0, 0, width, height, width, height);
        drawGradientRect(0, height - 100, width, height, 0x00000000, 0xff000000);
        username.drawTextBox();
        password.drawTextBox();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.translate(width / 2f, height / 2f, 0);
        GlStateManager.scale(3, 3, 1);
        GlStateManager.translate(-(width / 2f), -(height / 2f), 0);
        drawCenteredString(mc.fontRendererObj, "Alt Login", width / 2, height / 2 - 20 - mc.fontRendererObj.FONT_HEIGHT / 2, -1);
        GlStateManager.popMatrix();
        if (username.getText().isEmpty()) {
            drawString(mc.fontRendererObj, "Username:", width / 2 - 12, height / 2, -1);
        }

        if (password.getText().isEmpty()) {
            drawString(mc.fontRendererObj, "Password:", width / 2 - 12, height / 2 + 20, -1);
        }
    }


    protected void keyTyped(char character, int key) {
        if (character == '\t') {
            username.setFocused(!username.isFocused());
            password.setFocused(!password.isFocused());
        }
        if (key == Keyboard.KEY_ESCAPE) {
            Minecraft.getMinecraft().displayGuiScreen(new MainMenuScreen());
        }
        username.textboxKeyTyped(character, key);
        password.textboxKeyTyped(character, key);
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        username.mouseClicked(mouseX, mouseY, mouseButton);
        password.mouseClicked(mouseX, mouseY, mouseButton);
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    public void updateScreen() {
        username.updateCursorCounter();
        password.updateCursorCounter();
    }
}
