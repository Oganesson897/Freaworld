package team.miohouse.freaworld.data;

import static team.miohouse.freaworld.ModEntry.AR;
import static team.miohouse.freaworld.ModEntry.*;

import java.util.List;

import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;

import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.Identifier;
import team.miohouse.freaworld.Freaworld;
import team.miohouse.freaworld.registry.FRBlocks;

public class RecipeTweaks implements RemoveRecipesCallback, AddRecipesCallback {
	public static final String G = "ray_generator";

	private static final List<ItemConvertible> REMOVED_ITEMS = List.of(
			// Modern Industrialization
			MI.asItem("forge_hammer"),
			// Create
			AllBlocks.SHAFT.get(),
			AllBlocks.COGWHEEL.get(),
			AllBlocks.LARGE_COGWHEEL.get(),
			AllBlocks.MECHANICAL_PRESS.get(),
			AllBlocks.MECHANICAL_MIXER.get(),
			AllBlocks.MECHANICAL_SAW.get(),
			AllBlocks.ENCASED_FAN.get(),
			AllBlocks.MILLSTONE.get(),
			AllBlocks.DEPLOYER.get(),
			AllBlocks.BASIN.get(),
			AllItems.ANDESITE_ALLOY.get(),
			// Vanilla
			Items.SMITHING_TABLE,
			Items.PISTON,
			Blocks.CAULDRON,
			// AdvancedRoborn
			AR.asItem(G),
			AR.asItem(G + "_2"),
			AR.asItem(G + "_3"),
			AR.asItem(G + "_4"),
			AR.asItem(G + "_5"),
			AR.asItem(G + "_6"),
			AR.asItem(G + "_7"),
			AR.asItem(G + "_8"),
			AR.asItem(G + "_9"),
			AR.asItem(G + "_10"));

	@Override
	public void removeRecipes(RemoveRecipesCallback.RecipeHandler handler) {
		List<Item> items = REMOVED_ITEMS.stream().map(ItemConvertible::asItem).toList();
		handler.removeIf(r -> items.contains(r.getOutput().getItem()) && notFr(r));
	}

	@Override
	public void addRecipes(AddRecipesCallback.RecipeHandler handler) {
		/*
		 * example:
		 * handler.register(recipeId("crafting", "smithing_table"),
		 * id -> VanillaRecipeBuilders.shapedRecipe("AAA", "AOA", "AAA")
		 * .ingredient('A', CR.asItem("iron_sheet"))
		 * .ingredient('O', Items.CRAFTING_TABLE)
		 * .output(Items.SMITHING_TABLE.getDefaultStack())
		 * .build(id, ""));
		 */
		handler.register(recipeId("crafting", "iron_sheet"),
				id -> VanillaRecipeBuilders.shapedRecipe("AAA")
						.ingredient('A', Items.IRON_INGOT)
						.output(AllItems.IRON_SHEET.get().getDefaultStack())
						.build(id, ""));

		handler.register(recipeId("crafting", "copper_sheet"),
				id -> VanillaRecipeBuilders.shapedRecipe("AAA")
						.ingredient('A', Items.COPPER_INGOT)
						.output(AllItems.COPPER_SHEET.get().getDefaultStack())
						.build(id, ""));

		handler.register(recipeId("crafting", "smithing_table"),
				id -> VanillaRecipeBuilders.shapedRecipe("AAA", "AOA", "AAA")
						.ingredient('A', AllItems.IRON_SHEET.get())
						.ingredient('O', Items.CRAFTING_TABLE)
						.output(Items.SMITHING_TABLE.getDefaultStack())
						.build(id, ""));

		handler.register(recipeId("crafting", "cauldron"),
				id -> VanillaRecipeBuilders.shapedRecipe("AOA", "AOA", "AAA")
						.ingredient('A', AllItems.IRON_SHEET.get())
						.ingredient('O', Items.AIR)
						.output(Blocks.CAULDRON.asItem().getDefaultStack())
						.build(id, ""));

		handler.register(recipeId("crafting", "cauldron"),
				id -> VanillaRecipeBuilders.shapedRecipe("AOA", "ABA", "AAA")
						.ingredient('A', AllItems.ANDESITE_ALLOY.get())
						.ingredient('O', Items.AIR)
						.ingredient('B', Blocks.CAULDRON)
						.output(AllBlocks.BASIN.asStack())
						.build(id, ""));

		handler.register(recipeId("crafting", "shaft"),
				id -> VanillaRecipeBuilders.shapedRecipe("A", "A")
						.ingredient('A', AllItems.ANDESITE_ALLOY.get())
						.output(AllBlocks.SHAFT.asStack(4))
						.build(id, ""));

		handler.register(recipeId("crafting", "cogwheel"),
				id -> VanillaRecipeBuilders.shapedRecipe("AOA")
						.ingredient('A', ItemTags.WOODEN_SLABS)
						.ingredient('O', AllBlocks.SHAFT.get())
						.output(AllBlocks.COGWHEEL.asStack())
						.build(id, ""));

		handler.register(recipeId("crafting", "large_cogwheel"),
				id -> VanillaRecipeBuilders.shapedRecipe("AOA", "OEO", "AOA")
						.ingredient('A', Items.AIR)
						.ingredient('O', ItemTags.PLANKS)
						.ingredient('E', AllBlocks.COGWHEEL.get())
						.output(AllBlocks.LARGE_COGWHEEL.asStack())
						.build(id, ""));

		handler.register(recipeId("crafting", "piston"),
				id -> VanillaRecipeBuilders.shapedRecipe("AOA", "OEO", "ODO")
						.ingredient('A', ItemTags.PLANKS)
						.ingredient('O', AllItems.IRON_SHEET.get())
						.ingredient('E', AllBlocks.SHAFT.get())
						.ingredient('D', Items.REDSTONE)
						.output(Items.PISTON.getDefaultStack())
						.build(id, ""));

		handler.register(recipeId("crafting", "andesite_machine_casing"),
				id -> VanillaRecipeBuilders.shapedRecipe("AOA", "DRD", "AOA")
						.ingredient('A', AllItems.COPPER_SHEET.get())
						.ingredient('O', AllBlocks.SHAFT.get())
						.ingredient('R', AllBlocks.ANDESITE_CASING.get())
						.ingredient('D', AllBlocks.LARGE_COGWHEEL.get())
						.output(FRBlocks.ANDESITE_MACHINE_CASING.asItem().getDefaultStack())
						.build(id, ""));

		handler.register(recipeId("smithing", "mechanical_press"),
				id -> new SmithingRecipe(id, Ingredient.ofItems(FRBlocks.ANDESITE_MACHINE_CASING.asItem()),
						MC.asIngredient("piston"),
						AllBlocks.MECHANICAL_PRESS.asStack()));

		handler.register(recipeId("smithing", "mechanical_mixer"),
				id -> new SmithingRecipe(id, Ingredient.ofItems(FRBlocks.ANDESITE_MACHINE_CASING.asItem()),
						Ingredient.ofItems(AllItems.WHISK.get()), AllBlocks.MECHANICAL_PRESS.asStack()));

		handler.register(recipeId("smithing", "encased_fan"),
				id -> new SmithingRecipe(id, Ingredient.ofItems(FRBlocks.ANDESITE_MACHINE_CASING.asItem()),
						Ingredient.ofItems(AllItems.PROPELLER.get()), AllBlocks.ENCASED_FAN.asStack()));
	}

	public static boolean notFr(Recipe<?> recipe) {
		return !recipe.getId().getNamespace().equals(Freaworld.ID);
	}

	private static Identifier recipeId(String type, String name) {
		return Freaworld.id(type + "/" + name);
	}

}
