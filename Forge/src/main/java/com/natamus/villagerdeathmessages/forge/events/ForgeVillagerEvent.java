package com.natamus.villagerdeathmessages.forge.events;

import com.natamus.villagerdeathmessages.events.VillagerEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;

import java.lang.invoke.MethodHandles;

public class ForgeVillagerEvent {
	public static void registerEventsInBus() {
		// BusGroup.DEFAULT.register(MethodHandles.lookup(), ForgeVillagerEvent.class);

		LivingDeathEvent.BUS.addListener(ForgeVillagerEvent::villagerDeath);
	}

	@SubscribeEvent
	public static void villagerDeath(LivingDeathEvent e) {
		LivingEntity livingEntity = e.getEntity();
		VillagerEvent.villagerDeath(livingEntity.level(), livingEntity, e.getSource());
	}
}
