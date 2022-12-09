package team.miohouse.freaworld.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.item.EnderEyeItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;

@Mixin(EnderEyeItem.class)
public class EnderEyeItemMixin {
    /*
     * Fuck u ender portals
     */
    @Overwrite
    public ActionResult useOnBlock(ItemUsageContext context) {
        return ActionResult.PASS;
    }
}
