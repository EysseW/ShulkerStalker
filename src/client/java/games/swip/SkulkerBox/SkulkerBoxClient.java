package games.swip.SkulkerBox;

import net.fabricmc.api.ClientModInitializer;

public class SkulkerBoxClient implements ClientModInitializer {
	public static final CooldownManager cooldownManager = new CooldownManager();
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}
}