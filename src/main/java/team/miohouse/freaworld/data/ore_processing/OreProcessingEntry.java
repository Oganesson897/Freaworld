package team.miohouse.freaworld.data.ore_processing;

import static team.miohouse.freaworld.ModEntry.*;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public enum OreProcessingEntry {

	IRON(MC.asId("iron"), MC.asId("iron_ingot"), MC.asId("iron_nugget"), MI.asId("iron_dust"), MC.asId("raw_iron"), MC.asId("raw_iron_block"),
			CR.asId("crushed_iron_ore"), TC.asId("molten_iron"), MC.asId("iron_ore"), MC.asId("deepslate_iron_ore"));

	private final Identifier id;
	private final Identifier ingotId;
	private final Identifier nuggetId;
	private final Identifier dustId;
	private final Identifier rawOreId;
	private final Identifier rawOreBlockId;
	private final Identifier crushedOreId;
	private final Identifier fluidId;
	private final List<Identifier> oreIds;

	OreProcessingEntry(Identifier id, Identifier ingot, Identifier nugget, Identifier dust, Identifier raw, Identifier rawBlock,
			Identifier crushed, Identifier fluid, Identifier... ore) {
		this.id = id;
		this.ingotId = ingot;
		this.nuggetId = nugget;
		this.dustId = dust;
		this.rawOreId = raw;
		this.rawOreBlockId = rawBlock;
		this.crushedOreId = crushed;
		this.fluidId = fluid;
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

	public Identifier getRawOreBlockId() {
		return this.rawOreBlockId;
	}

	public Identifier getCrushedOreId() {
		return this.crushedOreId;
	}

	public Identifier getFluidId() {
		return this.fluidId;
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
		return Registry.ITEM.get(rawOreId);
	}

	public Block getRawOreBlock() {
		return Registry.BLOCK.get(rawOreBlockId);
	}

	public Item getCrushedOre() {
		return Registry.ITEM.get(crushedOreId);
	}

	public Fluid getFluid() {
		return Registry.FLUID.get(fluidId);
	}

	public List<Block> getOres() {
		return this.oreIds.stream().map(Registry.BLOCK::get).toList();
	}

}
