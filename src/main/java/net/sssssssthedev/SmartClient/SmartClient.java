package net.sssssssthedev.SmartClient;

import lombok.Getter;
import lombok.Setter;
import net.sssssssthedev.SmartClient.utils.BuildInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;
import viamcp.ViaMCP;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Getter
@Setter
public class SmartClient {

    public static final Logger Logger = LogManager.getLogger();
    @Getter
    @Setter
    public static SmartClient Instance = new SmartClient();
    public ExecutorService executor = Executors.newFixedThreadPool(8);

    public void init() {
        Logger.info(String.format("Starting up %s %s (%s/%s)", BuildInfo.getName(), BuildInfo.getVersion(), BuildInfo.getBranch(), BuildInfo.getCommit()));
        Display.setTitle(String.format("%s %s (%s/%s)", BuildInfo.getName(), BuildInfo.getVersion(), BuildInfo.getBranch(), BuildInfo.getCommit()));
        executor.execute(viaMCP);
    }

    Runnable viaMCP = () -> {
        try
        {
            ViaMCP.getInstance().start();
            ViaMCP.getInstance().initAsyncSlider();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    };
}
