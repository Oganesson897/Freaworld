package team.miohouse.freaworld.data.ore_processing;

import org.jetbrains.annotations.Nullable;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.minecraft.block.Block;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.AlternativeEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class OreProcessingLootTableTweaks implements LootTableEvents.Replace {

    @Override
    public @Nullable LootTable replaceLootTable(ResourceManager resourceManager, LootManager lootManager, Identifier id,
            LootTable original, LootTableSource source) {
        for (OreProcessingEntry entry : OreProcessingEntry.values()) {
            if (entry.getOres().stream().noneMatch(b -> b.getLootTableId().equals(id)))
                continue;
            Block ore = entry.getOres().stream().filter(b -> b.getLootTableId().equals(id)).findFirst().get();
            // fine
            Identifier oreId = Registry.BLOCK.getId(ore);
            boolean isDeepslate = oreId.getPath().contains("deepslate");
            // fine
            LootTable.Builder builder = LootTable.builder();
            builder.type(LootContextTypes.BLOCK);
            builder.pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
                    .bonusRolls(ConstantLootNumberProvider.create(0)).with(AlternativeEntry.builder()));
            return builder.build();
        }
        return original;
    }

}
