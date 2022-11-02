package nova.committee.momlove.init.handler;

import net.minecraft.network.chat.TranslatableComponent;
import nova.committee.momlove.Momlove;
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
            Momlove.config.getKeyWordsData().forEach(s -> {
                if (message.contains(s)){
                    Momlove.config.getUuidData().add(player.getUUID());
                    ConfigHandler.onChange();
                    player.displayClientMessage(new TranslatableComponent("momlove.keys.success"), true);
                }
            });

        });
    }
}
