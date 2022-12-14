package nova.committee.momlove.core.cmds;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import nova.committee.momlove.Momlove;
import nova.committee.momlove.init.handler.ConfigHandler;

import java.util.Collection;

/**
 * Project: MomLove-fabric
 * Author: cnlimiter
 * Date: 2022/11/1 12:32
 * Description:
 */
public class SetLoveCMd {
    public static int execute(CommandContext<CommandSourceStack> context, Collection<ServerPlayer> players) {

        for (Player player : players){
            try {
                Momlove.config.getUuidData().add(player.getUUID());
                context.getSource().sendSuccess(new TranslatableComponent("momlove.love.success"), true);
            } catch (Exception e){
                context.getSource().sendFailure(new TranslatableComponent("momlove.love.failure"));
            }
        }
        ConfigHandler.onChange();
        return 0;
    }
}
