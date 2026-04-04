package games.swip.ShulkerStalker.mixin.client;

import games.swip.ShulkerStalker.ShulkerStalkerClient;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.world.entity.EntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class ItemSpawnMixin {

	@Inject(method = "handleAddEntity", at = @At("TAIL"))
	private void onEntitySpawn(ClientboundAddEntityPacket packet, CallbackInfo ci) {
		if (packet.getType() == EntityType.ITEM) {
			ShulkerStalkerClient.counterManager.assignEntityId(packet.getId());
		}
	}
}