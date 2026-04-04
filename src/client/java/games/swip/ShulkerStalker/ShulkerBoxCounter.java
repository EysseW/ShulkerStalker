package games.swip.ShulkerStalker;

import games.swip.ShulkerStalker.config.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.GameType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShulkerBoxCounter {
	private static final Logger log = LoggerFactory.getLogger(ShulkerBoxCounter.class);
	private int ticksActive;
	private final int threshold;
	private int trackedEntityId = -1;
	private final BlockPos minedPos;
	private CounterState state;

	public ShulkerBoxCounter(BlockPos pos) {
		this.minedPos = pos;
		this.ticksActive = 0;
		this.state = CounterState.WAITING;
		this.threshold = Config.timer;
	}

	public void setId(int id) {
		this.trackedEntityId = id;
		this.state = CounterState.COUNTING;
	}

	public boolean shouldDie(Minecraft client) {
		if (ticksActive >= threshold) {
			// NOTIFY PLAYER
			if (client.player != null && Config.enabled && client.player.gameMode() != GameType.CREATIVE) {

				client.player.sendOverlayMessage(Component.literal("§cWarning: Shulker box not picked up!"));
				if (Config.warning_sound) {
					client.player.playSound(ShulkerStalkerClient.SHULKER_WARNING_EVENT);
				}
			}
			return true;
		}
		ticksActive++;
		return false;
	}

	public CounterState getState() {
		return state;
	}

	public int getId() {
		return trackedEntityId;
	}
}