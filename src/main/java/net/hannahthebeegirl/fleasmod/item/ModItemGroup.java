package net.hannahthebeegirl.fleasmod.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.hannahthebeegirl.fleasmod.FleasMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final ItemGroup FLEAS_MOD = FabricItemGroupBuilder.build(new Identifier(FleasMod.MOD_ID, "fleasmod"),
            () -> new ItemStack(ModItems.RAT_EARS));
}
