package nova.committee.momlove.init.mixins;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import nova.committee.momlove.init.callbacks.EntityExtensions;
import nova.committee.momlove.init.callbacks.LivingEvents;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Project: MomLove-fabric
 * Author: cnlimiter
 * Date: 2022/11/1 14:15
 * Description:
 */
@Mixin(value = LivingEntity.class, priority = 500)
public abstract class LivingEntityMixin extends Entity implements EntityExtensions {

    public LivingEntityMixin(EntityType<?> entityType, Level world) {
        super(entityType, world);
    }

    @ModifyArgs(
            method = "dropAllDeathLoot",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/LivingEntity;dropCustomDeathLoot(Lnet/minecraft/world/damagesource/DamageSource;IZ)V"
            )
    )
    private void modifyLootingLevel(Args args) {
        DamageSource source = args.get(0);
        int originalLevel = args.get(1);
        boolean recentlyHit = args.get(2);
        int modifiedLevel = LivingEvents.LOOTING_LEVEL.invoker().modifyLootingLevel(source, (LivingEntity) (Object) this, originalLevel, recentlyHit);
        args.set(1, modifiedLevel);
    }


    @Inject(method = "dropAllDeathLoot", at = @At(value = "JUMP", opcode = Opcodes.IFLE, ordinal = 0))
    public void fixNullDrops(DamageSource damageSource, CallbackInfo ci) {
        captureDrops(new ArrayList<>());
    }

    @Inject(method = "dropAllDeathLoot", at = @At("TAIL"))
    private void spawnDropsTAIL(DamageSource source, CallbackInfo ci) {
        Collection<ItemEntity> drops = this.captureDrops(null);
        if (!LivingEvents.LIVING_DROPS.invoker().onDrop((LivingEntity) (Object) this, source, drops))
            drops.forEach(e -> level.addFreshEntity(e));
    }
}
