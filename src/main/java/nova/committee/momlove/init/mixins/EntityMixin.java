package nova.committee.momlove.init.mixins;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import nova.committee.momlove.init.callbacks.EntityExtensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Collection;

/**
 * Project: MomLove-fabric
 * Author: cnlimiter
 * Date: 2022/11/1 19:17
 * Description:
 */
@Mixin(Entity.class)
public abstract class EntityMixin implements EntityExtensions{
    @Unique
    private Collection<ItemEntity> captureDrops = null;


    @Inject(
            method = "spawnAtLocation(Lnet/minecraft/world/item/ItemStack;F)Lnet/minecraft/world/entity/item/ItemEntity;",
            locals = LocalCapture.CAPTURE_FAILHARD,
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/item/ItemEntity;setDefaultPickUpDelay()V",
                    shift = At.Shift.AFTER
            ),
            cancellable = true
    )
    public void port_lib$spawnAtLocation(ItemStack stack, float f, CallbackInfoReturnable<ItemEntity> cir, ItemEntity itemEntity) {
        if (captureDrops != null) {
            captureDrops.add(itemEntity);
            cir.setReturnValue(itemEntity);
        }
    }


    @Unique
    @Override
    public Collection<ItemEntity> captureDrops() {
        return captureDrops;
    }

    @Unique
    @Override
    public Collection<ItemEntity> captureDrops(Collection<ItemEntity> value) {
        Collection<ItemEntity> ret = captureDrops;
        captureDrops = value;
        return ret;
    }
}
