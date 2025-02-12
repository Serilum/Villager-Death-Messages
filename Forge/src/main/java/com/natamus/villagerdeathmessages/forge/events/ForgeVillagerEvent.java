package com.natamus.villagerdeathmessages.forge.events;

import com.natamus.villagerdeathmessages.events.VillagerEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ForgeVillagerEvent {
	@SubscribeEvent
	public static void villagerDeath(LivingDeathEvent e) {
		LivingEntity livingEntity = e.getEntity();
		VillagerEvent.villagerDeath(livingEntity.level(), livingEntity, e.getSource());
	}
}
