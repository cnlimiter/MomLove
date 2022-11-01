package nova.committee.momlove.init.handler;

import net.minecraft.world.entity.player.Inventory;
import nova.committee.momlove.Momlove;
import nova.committee.momlove.init.callbacks.PlayerEvents;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Project: MomLove-fabric
 * Author: cnlimiter
 * Date: 2022/11/1 12:28
 * Description:
 */
public class PlayerEventsHandler {

    private static Inventory inventory = null;

    public static void init() {


        PlayerEvents.PLAYER_DEATH.register((source, player) -> {
            for (UUID id : Momlove.config.stream().toList()){
                if (player.getUUID() == id){
                   inventory = player.getInventory();
                }
            }
        });

        PlayerEvents.PLAYER_RESPAWN.register((player, end) -> {
            for (UUID id : Momlove.config.stream().toList()){
                if (player.getUUID() == id){
                    player.getInventory().replaceWith(inventory);
                }
            }
        });

    }
}
