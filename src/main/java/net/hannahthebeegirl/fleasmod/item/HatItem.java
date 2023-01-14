package net.hannahthebeegirl.fleasmod.item;

import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Wearable;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

public class HatItem
extends Item
implements Wearable {
    private static final EquipmentSlot slot = EquipmentSlot.HEAD;
    public static final DispenserBehavior DISPENSER_BEHAVIOR = new ItemDispenserBehavior(){
        @Override
        protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
        return dispenseHat(pointer, stack) ? stack : super.dispenseSilently(pointer, stack);
        }
    };

    public HatItem(Settings settings) {
        super(settings);
        DispenserBlock.registerBehavior(this, DISPENSER_BEHAVIOR);
    }

    public static boolean dispenseHat(BlockPointer pointer, ItemStack item) {
        BlockPos blockPos = pointer.getPos().offset(pointer.getBlockState().get(DispenserBlock.FACING));
        List<LivingEntity> list = pointer.getWorld().getEntitiesByClass(LivingEntity.class, new Box(blockPos), EntityPredicates.EXCEPT_SPECTATOR.and(new EntityPredicates.Equipable(item)));
        if (list.isEmpty()) {
            return false;
        }
        LivingEntity livingEntity = list.get(0);
        EquipmentSlot equipmentSlot = slot;
        if (livingEntity.getEquippedStack(equipmentSlot).isEmpty()) {
            ItemStack itemStack = item.split(1);
            livingEntity.equipStack(equipmentSlot, itemStack);
            if (livingEntity instanceof MobEntity) {
                ((MobEntity) livingEntity).setEquipmentDropChance(equipmentSlot, 2.0f);
                ((MobEntity) livingEntity).setPersistent();
            }
            return true;
        }
        return false;
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        EquipmentSlot equipmentSlot = slot;
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
