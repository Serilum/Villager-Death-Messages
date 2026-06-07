package com.natamus.villagerdeathmessages.events;
import net.minecraft.network.chat.Component;
import com.natamus.collective.functions.MessageFunctions;

import com.natamus.collective.functions.EntityFunctions;
import com.natamus.collective.functions.StringFunctions;
import com.natamus.villagerdeathmessages.config.ConfigHandler;
import net.minecraft.ChatFormatting;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.npc.villager.VillagerData;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class VillagerEvent {
	public static void villagerDeath(Level world, Entity entity, DamageSource source) {
		if (world.isClientSide()) {
			return;
		}
		
		boolean goname = false;
		if (!(entity instanceof Villager)) {
			if (ConfigHandler.mentionModdedVillagers) {
				if (EntityFunctions.isModdedVillager(entity)) {
					goname = true;
				}
			}
			
			if (!goname) {
				return;
			}
		}
		
		boolean modded = false;
		String prefix = "";
		
		if (goname) {
			modded = true;
		}
		else {
			Villager villager = (Villager)entity;
			VillagerData d = villager.getVillagerData();
			VillagerProfession prof = d.profession().value();
			
			if (prof != null) {
				String profession = prof.name().getString();

				if (!profession.equals("Villager")) {
					prefix = "A " + profession + " villager";
					if (villager.hasCustomName()) {
						prefix = villager.getName().getString() + " the " + profession;
					}
				}
				else {
					prefix = "A villager";
					if (villager.hasCustomName()) {
						prefix = villager.getName().getString();
					}
				}
			}
			else {
				modded = true;
			}
		}
		
		if (modded) {
			prefix = "A special villager";
			if (entity.hasCustomName()) {
				prefix = entity.getName().getString();
			}
		}
		
		// Damage source
		String imsourcename = source.getMsgId();
		String sourcename = "";
		
		Entity truesource = source.getEntity();
		if (truesource != null) {
			sourcename = truesource.getName().getString();
		}
		if (!sourcename.equals("") && imsourcename.equals("player")) {
			imsourcename = sourcename;
		}
		else if (imsourcename.contains(".")) {
			imsourcename = imsourcename.split("\\.")[0];
		}
		
		// Position
		Component locationComponent = Component.empty();
		if (ConfigHandler.showLocation) {
			Vec3 loc = entity.position();
			locationComponent = Component.translatable("collective.villagerdeathmessages.message.location", (int)loc.x, (int)loc.y, (int)loc.z);
		}

		MessageFunctions.broadcastTranslatableMessage(world, "collective.villagerdeathmessages.message.diedby", ChatFormatting.DARK_GREEN, prefix, locationComponent, imsourcename);
	}
}
