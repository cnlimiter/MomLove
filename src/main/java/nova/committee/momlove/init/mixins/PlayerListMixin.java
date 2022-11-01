package nova.committee.momlove.init.mixins;

import net.minecraft.network.Connection;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import nova.committee.momlove.init.callbacks.PlayerEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Project: MomLove-fabric
 * Author: cnlimiter
 * Date: 2022/11/1 12:08
 * Description:
 */
@Mixin(value = PlayerList.class, priority = 1001)
public class PlayerListMixin {
    @Inject(method = "placeNewPlayer", at = @At(value = "TAIL"))
    public void PlayerList_placeNewPlayer(Connection connection, ServerPlayer player, CallbackInfo ci) {
        PlayerEvents.PLAYER_LOGGED_IN.invoker().onPlayerLoggedIn(player.level, player);
    }

    @Inject(method = "remove", at = @At(value = "HEAD"))
    public void PlayerList_remove(ServerPlayer player, CallbackInfo ci) {
        PlayerEvents.PLAYER_LOGGED_OUT.invoker().onPlayerLoggedOut(player.level, player);
    }

    @Inject(method = "respawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;setHealth(F)V"))
    public void PlayerList_respawn(ServerPlayer serverPlayer, boolean bl, CallbackInfoReturnable<ServerPlayer> cir) {
        PlayerEvents.PLAYER_RESPAWN.invoker().onRespawn(serverPlayer, bl);
    }
}
