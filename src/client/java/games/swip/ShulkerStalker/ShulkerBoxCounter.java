package games.swip.ShulkerStalker;

import games.swip.ShulkerStalker.config.Config;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ColorMapColorUtil;
import net.minecraft.world.level.GameType;
import org.apache.logging.log4j.core.config.plugins.convert.HexConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class ShulkerBoxCounter {
	private static final Logger log = LoggerFactory.getLogger(ShulkerBoxCounter.class);
	private int ticksActive;
	private int trackedEntityId = -1;
	private final BlockPos minedPos;
	private CounterState state;
	private boolean isGlowing;

	public ShulkerBoxCounter(BlockPos pos) {
		this.minedPos = pos;
		this.ticksActive = 0;
		this.state = CounterState.WAITING;
	}

	public void setId(int id) {
		this.trackedEntityId = id;
		this.state = CounterState.COUNTING;
	}

	public void tick(Minecraft client) {
		if (client.player != null && Config.enabled && client.player.gameMode() != GameType.CREATIVE) {
			if (ticksActive >= Config.initial_timer) {
				if (state == CounterState.REMINDING) {
					if ((ticksActive - Config.initial_timer) % Config.remind_timer == 0) {
						sendMessage(client);
						sendSound(client);
						applyGlowing(client);
					} else {
						sendMessage(client);
						applyGlowing(client);
					}
				} else {
					sendMessage(client);
					sendSound(client);
					applyGlowing(client);
					this.state = CounterState.REMINDING;
				}
			}
		}
		ticksActive++;
	}

	public CounterState getState() {
		return state;
	}

	public int getId() {
		return trackedEntityId;
	}

	public boolean isGlowing() {
		return isGlowing;
	}

	private void sendSound(Minecraft client) {
		if (Config.warning_sound) {
			client.player.playSound(ShulkerStalkerClient.SHULKER_WARNING_EVENT);
		}
	}

	private void sendMessage(Minecraft client) {
		if (Config.flash_message && ticksActive % 4 == 0) {
			client.player.sendOverlayMessage(Component.literal(""));
		} else {
			client.player.sendOverlayMessage(Component.literal(Config.message).withColor(Integer.decode(Config.message_color)).withStyle(ChatFormatting.BOLD));
		}
	}

	private void applyGlowing(Minecraft client) {
		if (Config.apply_glowing) {
			isGlowing = true;
		} else {
			isGlowing = false;
		}
	}
}