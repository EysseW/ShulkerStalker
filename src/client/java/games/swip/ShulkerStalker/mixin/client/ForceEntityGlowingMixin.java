package games.swip.ShulkerStalker.mixin.client;

import games.swip.ShulkerStalker.ShulkerStalkerClient;
import games.swip.ShulkerStalker.config.Config;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class ForceEntityGlowingMixin {
	@Inject(method = "isCurrentlyGlowing", at = @At("HEAD"), cancellable = true)
	private void forceGlow(CallbackInfoReturnable<Boolean> cir) {
		Entity entity = (Entity) (Object) this;
		// Check if this specific entity is our tracked shulker
		if (ShulkerStalkerClient.counterManager.isTarget(entity.getId()) && Config.apply_glowing && Config.enabled) {
			System.out.println("Glowing!");
			cir.setReturnValue(true);
		}
	}
}