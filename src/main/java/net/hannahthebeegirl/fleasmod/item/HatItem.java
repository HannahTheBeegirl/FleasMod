package net.hannahthebeegirl.fleasmod.item;

import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.hannahthebeegirl.fleasmod.FleasMod;
import net.minecraft.block.DispenserBlock;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class HatItem
extends Item
implements DyeableItem {
    public int DEFAULT_COLOR = 11805920;
    public HatItem(Settings settings, int EarsColor) {
        super(settings);
        DEFAULT_COLOR = EarsColor;
        DispenserBlock.registerBehavior(this, ArmorItem.DISPENSER_BEHAVIOR);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> {if (hasColor(stack)) return getColor(stack); return DEFAULT_COLOR;}, this);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        EquipmentSlot equipmentSlot = MobEntity.getPreferredEquipmentSlot(itemStack);
        if (user.getEquippedStack(equipmentSlot).isEmpty()) {
            ItemStack itemStack2 = itemStack.split(1);
            user.equipStack(equipmentSlot, itemStack2);
            if (!world.isClient()) {
                user.incrementStat(Stats.USED.getOrCreateStat(this));
            }
            //itemStack.setCount(0);
            return TypedActionResult.success(itemStack, world.isClient());
        }
        return TypedActionResult.fail(itemStack);
    }
}
