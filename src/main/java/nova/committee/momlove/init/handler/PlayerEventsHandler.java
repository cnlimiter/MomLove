package nova.committee.momlove.init.handler;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import nova.committee.momlove.Momlove;
import nova.committee.momlove.init.callbacks.PlayerEvents;

/**
 * Project: MomLove-fabric
 * Author: cnlimiter
 * Date: 2022/11/1 12:28
 * Description:
 */
public class PlayerEventsHandler {


    public static void init() {

        PlayerEvents.PLAYER_DROP_DEATH.register((source, player) -> Momlove.config.getUuidData().contains(player.getUUID()));

        ServerPlayerEvents.AFTER_RESPAWN.register((old, newPlayer, end) -> {
            if (Momlove.config.getUuidData().contains(newPlayer.getUUID())){
                newPlayer.getInventory().replaceWith(old.getInventory());
            }
        });

    }
}
