package games.swip.ShulkerStalker;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class CounterManager {
	private final List<ShulkerBoxCounter> counters = new ArrayList<>();

	public CounterManager() {
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player != null) {
				this.tick(client);
			}
		});
	}

	public void tick(Minecraft client) {
		counters.removeIf(counter -> counter.shouldDie(client));
	}

	public void registerCounter(BlockPos pos) {
		counters.add(new ShulkerBoxCounter(pos));
	}

	public void assignEntityId(int id) {
		for (ShulkerBoxCounter counter : counters) {
			if (counter.getState() == CounterState.WAITING) counter.setId(id);
		}
	}

	public void handlePickup(int id) {
		counters.removeIf(counter -> counter.getId() == id);
	}
}
