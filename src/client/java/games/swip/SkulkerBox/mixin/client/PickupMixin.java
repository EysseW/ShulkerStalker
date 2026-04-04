package games.swip.SkulkerBox.mixin.client;

import games.swip.SkulkerBox.SkulkerBoxClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.game.ClientboundTakeItemEntityPacket;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPacketListener.class)
public class PickupMixin {
	@Inject(method = "handleTakeItemEntity", at = @At("HEAD"))
	public void onPickup(ClientboundTakeItemEntityPacket packet, CallbackInfo ci) {
		Minecraft mc = Minecraft.getInstance();

		// Check if the entity being picked up is an ItemEntity
		if (mc.level.getEntity(packet.getItemId()) instanceof ItemEntity itemEntity) {
			ItemStack stack = itemEntity.getItem();

			if (stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof ShulkerBoxBlock) {
				// It's a shulker! Kill the timer.
				SkulkerBoxClient.cooldownManager.kill();
			}
		}
	}
}
