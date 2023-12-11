package com.natamus.villagerdeathmessages.neoforge.events;

import com.natamus.villagerdeathmessages.events.VillagerEvent;
import net.minecraft.world.entity.LivingEntity;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class NeoForgeVillagerEvent {
	@SubscribeEvent
	public static void villagerDeath(LivingDeathEvent e) {
		LivingEntity livingEntity = e.getEntity();
		VillagerEvent.villagerDeath(livingEntity.level(), livingEntity, e.getSource());
	}
}
