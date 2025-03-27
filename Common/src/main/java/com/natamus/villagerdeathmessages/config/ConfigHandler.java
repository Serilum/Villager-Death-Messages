package com.natamus.villagerdeathmessages.config;

import com.natamus.collective.config.DuskConfig;
import com.natamus.villagerdeathmessages.util.Reference;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ConfigHandler extends DuskConfig {
	public static HashMap<String, List<String>> configMetaData = new HashMap<String, List<String>>();

	@Entry public static boolean showLocation = true;
	@Entry public static boolean mentionModdedVillagers = true;

	public static void initConfig() {
		configMetaData.put("showLocation", Arrays.asList(
			"If enabled, shows the location of the villager in the death message."
		));
		configMetaData.put("mentionModdedVillagers", Arrays.asList(
			"If enabled, also shows death messages of modded villagers. If you've found a 'villager'-entity that isn't named let me know by opening an issue so I can add it in."
		));

		DuskConfig.init(Reference.NAME, Reference.MOD_ID, ConfigHandler.class);
	}
}