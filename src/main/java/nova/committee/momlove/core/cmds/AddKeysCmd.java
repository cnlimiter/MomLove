package nova.committee.momlove.core.cmds;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.TranslatableComponent;
import nova.committee.momlove.Momlove;
import nova.committee.momlove.init.handler.ConfigHandler;

/**
 * Project: MomLove-fabric
 * Author: cnlimiter
 * Date: 2022/11/3 2:20
 * Description:
 */
public class AddKeysCmd {
    public static int execute(CommandContext<CommandSourceStack> context, String keyWords) {
        try{
            Momlove.config.getKeyWordsData().add(keyWords);
            context.getSource().sendSuccess(new TranslatableComponent("momlove.keys.add.success"), true);
        }
        catch (Exception e){
            context.getSource().sendSuccess(new TranslatableComponent("momlove.keys.failure"), true);
        }
        ConfigHandler.onChange();
        return 0;
    }
}
