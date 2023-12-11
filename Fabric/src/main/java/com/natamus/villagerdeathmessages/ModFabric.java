package com.natamus.villagerdeathmessages;

import com.natamus.collective.check.RegisterMod;
import com.natamus.collective.fabric.callbacks.CollectiveEntityEvents;
import com.natamus.villagerdeathmessages.events.VillagerEvent;
import com.natamus.villagerdeathmessages.util.Reference;
import net.fabricmc.api.ModInitializer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

public class ModFabric implements ModInitializer {
	
	@Override
	public void onInitialize() {
		setGlobalConstants();
		ModCommon.init();

		loadEvents();

		RegisterMod.register(Reference.NAME, Reference.MOD_ID, Reference.VERSION, Reference.ACCEPTED_VERSIONS);
	}

	private void loadEvents() {
		CollectiveEntityEvents.LIVING_ENTITY_DEATH.register((Level world, Entity entity, DamageSource source) -> {
			VillagerEvent.villagerDeath(world, entity, source);
			return true;
		});
	}

	private static void setGlobalConstants() {

	}
}
