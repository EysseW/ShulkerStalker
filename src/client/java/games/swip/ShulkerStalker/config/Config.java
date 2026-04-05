package games.swip.ShulkerStalker.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class Config extends MidnightConfig {
	public static final String GENERAL = "General";
	@Entry(category = GENERAL, name= "Enabled") public static boolean enabled = true;
	@Entry(category = GENERAL, name = "Time before warning") public static int initial_timer = 100;
	@Entry(category = GENERAL, name = "Time in between reminders") public static int remind_timer = 20;
	@Entry(category = GENERAL, name = "Apply glowing to dropped item") public static boolean apply_glowing = true;
	@Entry(category = GENERAL, name = "Enable warning sound") public static boolean warning_sound = true;
	@Entry(category = GENERAL, name = "Flash warning message") public static boolean flash_mesage = false;
}