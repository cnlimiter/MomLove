package nova.committee.momlove.init.handler;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import nova.committee.momlove.Momlove;
import nova.committee.momlove.init.callbacks.LivingEvents;
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


    public static void init() {

        PlayerEvents.PLAYER_DROP_DEATH.register((source, player) -> Momlove.config.contains(player.getUUID()));

        ServerPlayerEvents.AFTER_RESPAWN.register((old, newPlayer, end) -> {
            if (Momlove.config.contains(newPlayer.getUUID())){
                newPlayer.getInventory().replaceWith(old.getInventory());
            }
        });

    }
}
