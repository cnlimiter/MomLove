package nova.committee.momlove.init.handler;

import nova.committee.momlove.init.callbacks.ServerLevelEvents;

/**
 * Project: MomLove-fabric
 * Author: cnlimiter
 * Date: 2022/11/2 20:12
 * Description:
 */
public class ChatEventHandler {
    public static void init() {
        ServerLevelEvents.Server_Chat.register((player, message, component) -> {

        });
    }
}
