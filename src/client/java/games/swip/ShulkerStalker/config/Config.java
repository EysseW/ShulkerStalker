package games.swip.ShulkerStalker.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class Config extends MidnightConfig {
	public static final String GENERAL = "General";
	@Entry(category = GENERAL, name= "Enabled") public static boolean enabled = true;
	@Entry(category = GENERAL, name = "Time to pickup shulkerbox") public static int timer = 400;
}