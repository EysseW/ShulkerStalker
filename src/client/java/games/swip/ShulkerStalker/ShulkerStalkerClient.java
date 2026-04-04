package games.swip.ShulkerStalker;

import eu.midnightdust.lib.config.MidnightConfig;
import games.swip.ShulkerStalker.config.Config;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShulkerStalkerClient implements ClientModInitializer {
	public static final CounterManager counterManager = new CounterManager();
	private static final Logger log = LoggerFactory.getLogger(ShulkerStalkerClient.class);

	@Override
	public void onInitializeClient() {
		MidnightConfig.init("modid", Config.class);
		log.debug("SkulkerBox loaded!");
	}
}