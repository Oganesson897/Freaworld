package team.miohouse.freaworld.data.ore_processing;

import static team.miohouse.freaworld.ModEntry.CR;
import static team.miohouse.freaworld.ModEntry.MC;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum OreProcessingEntry {

    IRON(MC.asId("iron"), MC.asId("iron_ingot"), MC.asId("iron_nugget"), MC.asId("iron_dust"), MC.asId("raw_iron_ore"),
            CR.asId("crushed_iron_ore"), MC.asId("iron_ore"), MC.asId("deepslate_iron_ore"));

    private final Identifier id;
    private final Identifier ingotId;
    private final Identifier nuggetId;
    private final Identifier dustId;
    private final Identifier rawOreId;
    private final Identifier crushedOreId;
    private final List<Identifier> oreIds;

    OreProcessingEntry(Identifier id, Identifier ingot, Identifier nugget, Identifier dust, Identifier raw,
            Identifier crushed, Identifier... ore) {
        this.id = id;
        this.ingotId = ingot;
        this.nuggetId = nugget;
        this.dustId = dust;
        this.rawOreId = raw;
        this.crushedOreId = crushed;
        this.oreIds = List.of(ore);
    }

    public Identifier getId() {
        return this.id;
    }

    public Identifier getIngotId() {
        return this.ingotId;
    }

    public Identifier getNuggetId() {
        return this.nuggetId;
    }

    public Identifier getDustId() {
        return this.dustId;
    }

    public Identifier getRawOreId() {
        return this.rawOreId;
    }

    public Identifier getCrushedOreId() {
        return this.crushedOreId;
    }

    public List<Identifier> getOreIds() {
        return this.oreIds;
    }

    public Item getIngot() {
        return Registry.ITEM.get(ingotId);
    }

    public Item getNugget() {
        return Registry.ITEM.get(nuggetId);
    }

    public Item getDust() {
        return Registry.ITEM.get(dustId);
    }

    public Item getRawOre() {
        return Registry.ITEM.get(crushedOreId);
    }

    public List<Block> getOres() {
        return this.oreIds.stream().map(Registry.BLOCK::get).toList();
    }

}
