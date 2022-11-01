package nova.committee.momlove.init.callbacks;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.item.ItemEntity;

import java.util.Collection;

/**
 * Project: MomLove-fabric
 * Author: cnlimiter
 * Date: 2022/11/1 14:18
 * Description:
 */
public interface EntityExtensions {
    default Collection<ItemEntity> captureDrops() {
        throw new RuntimeException("this should be overridden via mixin. what?");
    }

    default Collection<ItemEntity> captureDrops(Collection<ItemEntity> value) {
        throw new RuntimeException("this should be overridden via mixin. what?");
    }

}
