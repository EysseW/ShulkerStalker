package games.swip.SkulkerBox;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class SkulkerBoxClient implements ClientModInitializer {
	public static final CooldownManager cooldownManager = new CooldownManager();
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player != null) {
				cooldownManager.tick(client);
			}
		});
	}
}