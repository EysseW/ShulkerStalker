package games.swip.SkulkerBox;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkulkerBoxClient implements ClientModInitializer {
	public static final CounterManager counterManager = new CounterManager();
	private static final Logger log = LoggerFactory.getLogger(SkulkerBoxClient.class);

	@Override
	public void onInitializeClient() {
		log.debug("SkulkerBox loaded!");
	}
}