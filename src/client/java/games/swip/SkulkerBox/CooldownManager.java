package games.swip.SkulkerBox;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CooldownManager {
	private static final Logger log = LoggerFactory.getLogger(CooldownManager.class);
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
				System.out.println("Timer done!");
			}
			kill(); // Stop the timer after notifying
		}
	}
}
