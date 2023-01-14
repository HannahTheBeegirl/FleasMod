package net.hannahthebeegirl.fleasmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.hannahthebeegirl.fleasmod.FleasMod;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item RAT_EARS = registerItem("rat_ears",
            new HatItem(new FabricItemSettings().group(ModItemGroup.FLEAS_MOD)));
    public static final Item TINKERER_GOGGLES = registerItem("tinkerer_goggles",
            new HatItem(new FabricItemSettings().group(ModItemGroup.FLEAS_MOD)));
    public static final Item GAS_MASK = registerItem("gas_mask",
            new HatItem(new FabricItemSettings().group(ModItemGroup.FLEAS_MOD)));
    public static final Item PIGTAILS = registerItem("pigtails",
            new HatItem(new FabricItemSettings().group(ModItemGroup.FLEAS_MOD)));

    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier(FleasMod.MOD_ID, name), item);
    }
    public static void registerModItems(){
        FleasMod.LOGGER.info("Registering Mod Items for "+FleasMod.MOD_ID);
    }
}