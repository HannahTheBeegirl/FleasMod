package net.hannahthebeegirl.fleasmod.mixin;

import net.hannahthebeegirl.fleasmod.FleasMod;
import net.hannahthebeegirl.fleasmod.item.HatItem;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Slot.class)
public abstract class HatStuckOnMixin {
	@Shadow @Final public Inventory inventory;

	@Shadow @Final private int index;
	@Shadow public abstract ItemStack getStack();

	@Inject(method = "insertStack(Lnet/minecraft/item/ItemStack;I)Lnet/minecraft/item/ItemStack;", at = @At("HEAD"), cancellable = true)
	private void cancelInsert(ItemStack stack, int count, CallbackInfoReturnable<ItemStack> cir) {
		FleasMod.LOGGER.info("insertStack");
		if (this.inventory instanceof PlayerInventory && this.index == 39) {
			if (stack.getItem() instanceof HatItem && this.getStack().getItem() instanceof HatItem) return;
			cir.setReturnValue(stack); // cancel early
		}
	}
}