package net.sssssssthedev.SmartClient.ui.alts;

import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.litarvan.openauth.microsoft.model.response.MinecraftProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import net.sssssssthedev.SmartClient.SmartClient;
import net.sssssssthedev.SmartClient.providers.AltsProvider;
import net.sssssssthedev.SmartClient.ui.mainmenu.MainMenuScreen;
import net.sssssssthedev.SmartClient.utils.RenderUtils;
import org.lwjgl.input.Keyboard;

public class AltsScreen extends GuiScreen {

    public ResourceLocation background;
    public static GuiTextField email;
    public static GuiTextField password;

    public AltsScreen() {
        background = new ResourceLocation("smartclient/images/altsbackground.jpg");
    }

    public void initGui() {
        email = new GuiTextField( height / 4 + 24, mc.fontRendererObj, width / 2  - 45 - mc.fontRendererObj.FONT_HEIGHT / 2, height / 2, 100, 20);
        email.setFocused(true);
        password = new GuiTextField(height / 4 + 24, mc.fontRendererObj, width / 2 - 45 - mc.fontRendererObj.FONT_HEIGHT / 2, height / 2 + 40, 100, 20);

    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        mc.getTextureManager().bindTexture(background);
        RenderUtils.drawImage(0, 0, 0, 0, width, height, width, height);
        drawGradientRect(0, height - 100, width, height, 0x00000000, 0xff000000);
        email.drawTextBox();
        password.drawTextBox();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.pushMatrix();
        GlStateManager.translate(width / 2f, height / 2f, 0);
        GlStateManager.scale(3, 3, 1);
        GlStateManager.translate(-(width / 2f), -(height / 2f), 0);
        drawCenteredString(mc.fontRendererObj, "Alt Login", width / 2, height / 2 - 20 - mc.fontRendererObj.FONT_HEIGHT / 2, -1);
        GlStateManager.popMatrix();
        drawCenteredString(mc.fontRendererObj, "Email", width / 2, height / 2 - 10 - mc.fontRendererObj.FONT_HEIGHT / 2, -1);
        drawCenteredString(mc.fontRendererObj, "Password", width / 2, height / 2 + 30 - mc.fontRendererObj.FONT_HEIGHT / 2, -1);
        if (AltsProvider.getInstance().prof == null) {
            drawCenteredString(mc.fontRendererObj, "§c§lClick here to login", width / 2, height / 2 + 80 - mc.fontRendererObj.FONT_HEIGHT / 2, -1);
        } else {
            drawCenteredString(mc.fontRendererObj, "§c§lClick here to login", width / 2, height / 2 + 80 - mc.fontRendererObj.FONT_HEIGHT / 2, -1);
            drawCenteredString(mc.fontRendererObj, String.format("§b§lLogged in as %s", AltsProvider.getInstance().prof.getName()), width / 2, height / 2 + 100 - mc.fontRendererObj.FONT_HEIGHT / 2, -1);
        }
    }


    protected void keyTyped(char character, int key) {
        if (character == '\t') {
            email.setFocused(!email.isFocused());
            password.setFocused(!password.isFocused());
        }
        if (key == Keyboard.KEY_ESCAPE) {
            Minecraft.getMinecraft().displayGuiScreen(new MainMenuScreen());
        }
        email.textboxKeyTyped(character, key);
        password.textboxKeyTyped(character, key);
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        email.mouseClicked(mouseX, mouseY, mouseButton);
        password.mouseClicked(mouseX, mouseY, mouseButton);
        ScaledResolution scaledResolution = new ScaledResolution(mc);

        if (RenderUtils.isHovered( scaledResolution.getScaledWidth() / 2 - mc.fontRendererObj.getStringWidth("§c§lClick here to login") / 2, scaledResolution.getScaledHeight() / 2 + 80 - mc.fontRendererObj.FONT_HEIGHT / 2, scaledResolution.getScaledWidth() / 2 - mc.fontRendererObj.getStringWidth("§c§lClick here to login") / 2, 12, mouseX, mouseY)) {
            if (!email.getText().isEmpty() && !password.getText().isEmpty()) {
                SmartClient.getInstance().executor.execute(AltsProvider.getInstance().getLoginTask());
            } else {
                SmartClient.Logger.info("provide");
            }
        }
    }

    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    public void updateScreen() {
        email.updateCursorCounter();
        password.updateCursorCounter();
    }
}
