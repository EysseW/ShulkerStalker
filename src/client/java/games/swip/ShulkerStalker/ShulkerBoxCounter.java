package games.swip.ShulkerStalker;

import games.swip.ShulkerStalker.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.GameType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
				if (state == CounterState.REMINDING && (ticksActive - Config.initial_timer) % Config.remind_timer == 0) {
					notifyPlayer(client);
				} else if (state == CounterState.COUNTING) {
					notifyPlayer(client);
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

	private void notifyPlayer(Minecraft client) {
		if (Config.flash_mesage && ticksActive % 4 == 0) {
			client.player.sendOverlayMessage(Component.literal(""));
		} else {
			client.player.sendOverlayMessage(Component.literal("§cWarning: Shulker box not picked up!"));
		}
		if (Config.warning_sound) {
			client.player.playSound(ShulkerStalkerClient.SHULKER_WARNING_EVENT);
		}
		Entity entity = client.level.getEntity(this.trackedEntityId);
		if (entity != null) isGlowing = true;
	}
}