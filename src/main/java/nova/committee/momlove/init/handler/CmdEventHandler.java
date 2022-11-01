package nova.committee.momlove.init.handler;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import nova.committee.momlove.core.cmds.SetLoveCMd;
import nova.committee.momlove.core.cmds.UnLoveCMd;


/**
 * Project: MomLove-fabric
 * Author: cnlimiter
 * Date: 2022/11/1 12:08
 * Description:
 */
public class CmdEventHandler {
    public static void init() {
        CommandRegistrationCallback.EVENT.register((dispatcher, commandBuildContext) -> {
            dispatcher.register(
                    Commands.literal("mom")
                            .requires(source -> source.hasPermission(2))
                            .then(Commands.literal("love")
                                    .then(Commands.argument("targets", EntityArgument.players())
                                            .executes((commandContext) -> SetLoveCMd.execute(commandContext, EntityArgument.getPlayers(commandContext, "targets")
                                             )))
                            )
                            .then(Commands.literal("unlove")
                                    .then(Commands.argument("targets", EntityArgument.players())
                                            .executes((commandContext) -> UnLoveCMd.execute(commandContext, EntityArgument.getPlayers(commandContext, "targets")
                                            )))
                            )

            );


        });
    }
}
