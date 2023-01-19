package net.hannahthebeegirl.fleasmod.mixin;

import net.hannahthebeegirl.fleasmod.item.CosmeticShieldItem;
import net.hannahthebeegirl.fleasmod.item.HatItem;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class getPreferredMixin {
	@Shadow public abstract int getItemUseTime();

	@Inject(method = "getPreferredEquipmentSlot", at = @At("HEAD"), cancellable = true)
	private static void injectPreferredSlotMethod(ItemStack stack, CallbackInfoReturnable info){
		//switch(instanceof stack.getItem())
		if (stack.getItem() instanceof HatItem) {
			info.setReturnValue(EquipmentSlot.HEAD);
		}
		if (stack.getItem() instanceof CosmeticShieldItem){
			info.setReturnValue(EquipmentSlot.OFFHAND);
		}
	}
}