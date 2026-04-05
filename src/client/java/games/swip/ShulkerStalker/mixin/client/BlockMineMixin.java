package games.swip.ShulkerStalker.mixin.client;

import games.swip.ShulkerStalker.ShulkerStalkerClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MultiPlayerGameMode.class)
public class BlockMineMixin {
	@Inject(at = @At("HEAD"), method = "destroyBlock")
	private void init(BlockPos pos, CallbackInfoReturnable<Boolean> cir) {

		// This code is injected into the start of Minecraft.run()V
		System.out.println("Local player mined block at: " + pos.toShortString());
		Minecraft mc = Minecraft.getInstance();
		BlockState state = mc.level.getBlockState(pos);

		if (state.getBlock() instanceof ShulkerBoxBlock && !(mc.player.gameMode() == GameType.CREATIVE)) {
			ShulkerStalkerClient.counterManager.registerCounter(pos);
		}
	}
}