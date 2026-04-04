package games.swip.ShulkerStalker;

import eu.midnightdust.lib.config.MidnightConfig;
import games.swip.ShulkerStalker.config.Config;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShulkerStalkerClient implements ClientModInitializer {
	private static final String MOD_ID = "shulker-stalker";
	public static final CounterManager counterManager = new CounterManager();

	// Sound
	private static final Logger log = LoggerFactory.getLogger(ShulkerStalkerClient.class);
	public static final Identifier WARNING_SOUND_ID = Identifier.fromNamespaceAndPath(MOD_ID, "shulker_warning");
	public static final SoundEvent SHULKER_WARNING_EVENT = SoundEvent.createVariableRangeEvent(WARNING_SOUND_ID);

	@Override
	public void onInitializeClient() {
		MidnightConfig.init(MOD_ID, Config.class);
		Registry.register(BuiltInRegistries.SOUND_EVENT, WARNING_SOUND_ID, SHULKER_WARNING_EVENT);
		log.debug("SkulkerBox loaded!");
	}
}