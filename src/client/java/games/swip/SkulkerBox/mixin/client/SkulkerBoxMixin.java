package games.swip.SkulkerBox.mixin.client;

import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MultiPlayerGameMode.class)
public class SkulkerBoxMixin {
	@Inject(at = @At("HEAD"), method = "destroyBlock")
	private void init(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
		// This code is injected into the start of Minecraft.run()V
		System.out.println("Local player mined block at: " + pos.toShortString());
	}
}