package team.miohouse.freaworld.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

@Mixin(AbstractFireBlock.class)
public class AbstractFireBlockMixin {
    /*
     * Fuck you nether portals
     */
    @Overwrite
    private static boolean shouldLightPortalAt(World world, BlockPos pos, Direction direction) {
        return false;
    }
}
