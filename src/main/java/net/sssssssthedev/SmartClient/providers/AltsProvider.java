package net.sssssssthedev.SmartClient.providers;

import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.litarvan.openauth.microsoft.model.response.MinecraftProfile;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.util.Session;
import net.sssssssthedev.SmartClient.SmartClient;
import net.sssssssthedev.SmartClient.ui.alts.AltsScreen;

import java.util.function.BiConsumer;

@Getter
@Setter
public class AltsProvider {

    @Getter
    @Setter
    public static AltsProvider Instance = new AltsProvider();

    // Microsoft Authentication
    public MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
    public MicrosoftAuthResult result;
    public Session session;
    public MinecraftProfile prof;

    BiConsumer<String, String> login = (String email, String password) -> {
        try {
            result = authenticator.loginWithCredentials(email, password);
            //new Session(result.getProfile().getName(), result.getProfile().getId(), result.getAccessToken(), "microsoft");
            prof = result.getProfile();
            session = new Session(
                    prof.getName(),
                    prof.getId(),
                    result.getAccessToken(),
                    "MSA"
            );
            SmartClient.Logger.info("Logged in as " + prof.getName() + " (" + prof.getId() + ")");
        } catch (MicrosoftAuthenticationException e) {
            SmartClient.Logger.info("An error occurred while authenticating to microsoft: " + e.getMessage());
            if (e.getCause() != null) {
                e.getCause().printStackTrace();
            }
        }
    };

    Runnable loginTask = () -> login.accept(AltsScreen.email.getText(), AltsScreen.password.getText());
}
