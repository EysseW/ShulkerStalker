package games.swip.SkulkerBox;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class CooldownManager {
	private int ticksActive = 0;
	private boolean isWaiting = false;
	private final int THRESHOLD = 60; // 3 seconds

	public void start() {
		this.ticksActive = 0;
		this.isWaiting = true;
	}

	public void kill() {
		this.isWaiting = false;
		this.ticksActive = 0;
	}

	public void tick(Minecraft client) {
		if (!isWaiting) return;

		ticksActive++;

		if (ticksActive >= THRESHOLD) {
			// NOTIFY PLAYER
			if (client.player != null) {
				client.player.sendSystemMessage(
				 Component.literal("§cWarning: Shulker box not picked up!")
				);
			}
			kill(); // Stop the timer after notifying
		}
	}
}
