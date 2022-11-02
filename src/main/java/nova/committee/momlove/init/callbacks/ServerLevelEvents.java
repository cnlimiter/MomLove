package nova.committee.momlove.init.callbacks;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

/**
 * Project: MomLove-fabric
 * Author: cnlimiter
 * Date: 2022/11/2 12:08
 * Description:
 */
public final class ServerLevelEvents {
    public ServerLevelEvents() {
    }

    public static final Event<ServerLevelEvents.Server_Chat> Server_Chat = EventFactory.createArrayBacked(ServerLevelEvents.Server_Chat.class, callbacks -> (player, message, component) -> {
        for (ServerLevelEvents.Server_Chat callback : callbacks) {
            callback.onChat(player, message, component);
        }
    });


    @FunctionalInterface
    public interface Server_Chat {
        void onChat(ServerPlayer player, String message, Component component);
    }


}
