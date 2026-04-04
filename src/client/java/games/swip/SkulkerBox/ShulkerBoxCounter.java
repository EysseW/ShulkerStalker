package games.swip.SkulkerBox;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShulkerBoxCounter {
	private static final Logger log = LoggerFactory.getLogger(ShulkerBoxCounter.class);
	private int ticksActive;
	private final int THRESHOLD = 60;
	private int trackedEntityId = -1;
	private final BlockPos minedPos;
	private CounterState state;

	public ShulkerBoxCounter(BlockPos pos) {
		this.minedPos = pos;
		this.ticksActive = 0;
		this.state = CounterState.WAITING;
	}

	public void setId(int id) {
		this.trackedEntityId = id;
		this.state = CounterState.COUNTING;
	}

	public boolean shouldDie(Minecraft client) {
		if (ticksActive >= THRESHOLD) {
			// NOTIFY PLAYER
			if (client.player != null) {
				client.player.sendOverlayMessage(
				 Component.literal("§cWarning: Shulker box not picked up!")
				);
				System.out.println("Timer done!");
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