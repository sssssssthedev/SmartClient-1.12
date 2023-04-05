package net.sssssssthedev.SmartClient;

import lombok.Getter;
import lombok.Setter;
import net.sssssssthedev.SmartClient.utils.BuildInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

@Getter
@Setter
public class SmartClient {

    public static final Logger Logger = LogManager.getLogger();
    @Getter
    @Setter
    public static SmartClient Instance = new SmartClient();

    public void init() {
        Logger.info(String.format("Starting up %s %s (%s)", BuildInfo.GetName(), BuildInfo.GetVersion(), BuildInfo.GetCommit()));
        Display.setTitle(String.format("%s %s (%s)", BuildInfo.GetName(), BuildInfo.GetVersion(), BuildInfo.GetCommit()));

    }
}
