package nova.committee.momlove;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.MinecraftServer;
import nova.committee.momlove.init.handler.CmdEventHandler;
import nova.committee.momlove.init.handler.ConfigHandler;
import nova.committee.momlove.init.ModConfig;
import nova.committee.momlove.init.handler.PlayerEventsHandler;
import nova.committee.momlove.utils.FileUtils;
import java.nio.file.Path;

/**
 * Project: MomLove-fabric
 * Author: cnlimiter
 * Date: 2022/11/1 12:01
 * Description:
 */
public class Momlove implements ModInitializer {
    public static MinecraftServer SERVER = null;
    public static Path CONFIG_FOLDER;
    public static ModConfig config;

    @Override
    public void onInitialize() {
        CONFIG_FOLDER = FabricLoader.getInstance().getConfigDir().resolve("botapi");
        FileUtils.checkFolder(CONFIG_FOLDER);
        ServerLifecycleEvents.SERVER_STARTING.register(this::onServerPreStart);

        ServerLifecycleEvents.SERVER_STARTED.register(this::onServerStarted);

        ServerLifecycleEvents.SERVER_STOPPING.register(this::onServerStopping);
        CmdEventHandler.init();
        PlayerEventsHandler.init();
    }
    public MinecraftServer getServer() {
        return SERVER;
    }

    private void onServerPreStart(MinecraftServer server){
        SERVER = server;
    }
    private void onServerStarted(MinecraftServer server) {
        config = ConfigHandler.load();//读取配置
    }

    private void onServerStopping(MinecraftServer server) {
        ConfigHandler.save(config);

    }
}
