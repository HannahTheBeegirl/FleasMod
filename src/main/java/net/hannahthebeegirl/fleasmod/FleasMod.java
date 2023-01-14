package net.hannahthebeegirl.fleasmod;

import net.fabricmc.api.ModInitializer;
import net.hannahthebeegirl.fleasmod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FleasMod implements ModInitializer {
	public static final String MOD_ID = "fleasmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
	}
}
