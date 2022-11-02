package nova.committee.momlove.init.callbacks;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;

import java.util.Collection;

/**
 * Project: MomLove-fabric
 * Author: cnlimiter
 * Date: 2022/11/1 14:06
 * Description:
 */
public class LivingEvents {
    public static final Event<Living_Drop> LIVING_DROPS = EventFactory.createArrayBacked(Living_Drop.class, callbacks -> (entity, source, drops) -> {
        for (Living_Drop callback : callbacks) {
            if (callback.onDrop(entity, source, drops)) {
                return true;
            }
        }
        return false;
    });

    public static final Event<Living_LootingLevel> LOOTING_LEVEL = EventFactory.createArrayBacked(Living_LootingLevel.class, callbacks -> (source, target, level, recent) -> {
        for (Living_LootingLevel callback : callbacks) {
            int lootingLevel = callback.modifyLootingLevel(source, target, level, recent);
            if (lootingLevel != level) {
                return lootingLevel;
            }
        }

        return level;
    });
    @FunctionalInterface
    public interface Living_Drop {
        boolean onDrop(LivingEntity entity, DamageSource source, Collection<ItemEntity> drops);
    }

    @FunctionalInterface
    public interface Living_LootingLevel {
        int modifyLootingLevel(DamageSource source, LivingEntity target, int currentLevel, boolean recentlyHit);
    }


}
