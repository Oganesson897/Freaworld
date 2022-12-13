package team.miohouse.freaworld.data.ore_processing;

import static team.miohouse.freaworld.data.RecipeTweaks.notFr;
import static team.miohouse.freaworld.data.RecipeTweaks.testAll;

import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;

import com.simibubi.create.content.contraptions.components.millstone.MillingRecipe;
import com.simibubi.create.content.contraptions.processing.ProcessingOutput;

import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import team.miohouse.freaworld.Freaworld;
import team.miohouse.freaworld.data.util.FreePRP;
import team.miohouse.freaworld.data.util.JRecipeUtil;

public class OreProcessingRecipeTweaks implements AddRecipesCallback, RemoveRecipesCallback {
	@Override
	public void removeRecipes(RemoveRecipesCallback.RecipeHandler handler) {
		handler.removeIf(r -> {
			if (!(notFr(r) && r instanceof AbstractCookingRecipe cooking))
				return false;
			for (OreProcessingEntry entry : OreProcessingEntry.values()) {
				if (entry.getOres().stream().anyMatch(ore -> testAll(ore, cooking.getIngredients())))
					return true;
				if (testAll(entry.getCrushedOre(), cooking.getIngredients())
						|| testAll(entry.getRawOre(), cooking.getIngredients())
						|| testAll(entry.getRawOreBlock(), cooking.getIngredients()))
					return true;
			}
			return false;
		});
	}

	@Override
	public void addRecipes(AddRecipesCallback.RecipeHandler handler) {
		for (OreProcessingEntry entry : OreProcessingEntry.values()) {
			handler.register(recipeId(entry, "smelting", entry.getCrushedOreId().getPath()),
					id -> VanillaRecipeBuilders.smeltingRecipe(id, "", Ingredient.ofItems(entry.getCrushedOre()),
							new ItemStack(entry.getNugget(), 3), 0.125F, 400));
			handler.register(recipeId(entry, "blasting", entry.getCrushedOreId().getPath()),
					id -> VanillaRecipeBuilders.blastingRecipe(id, "", Ingredient.ofItems(entry.getCrushedOre()),
							new ItemStack(entry.getNugget(), 3), 0.125F, 200));

			for (Identifier ore : entry.getOreIds()) {
				Item item = Registry.ITEM.get(ore);
				handler.register(recipeId(entry, "smelting", ore.getPath()),
						id -> VanillaRecipeBuilders.smeltingRecipe(id, "", Ingredient.ofItems(item),
								new ItemStack(entry.getNugget(), 6), 0.125F, 400));
				handler.register(recipeId(entry, "blasting", ore.getPath()),
						id -> VanillaRecipeBuilders.blastingRecipe(id, "", Ingredient.ofItems(item),
								new ItemStack(entry.getNugget(), 6), 0.125F, 200));
			}

			handler.register(recipeId(entry, "milling", entry.getCrushedOreId().getPath()),
					id -> new MillingRecipe(FreePRP.of(id).setIngredient(Ingredient.ofItems(entry.getCrushedOre()))
							.setResult(new ProcessingOutput(entry.getDust().getDefaultStack(), 1))
							.setProcessingTime(350)));
			handler.register(recipeId(entry, "melting", entry.getCrushedOreId().getPath()), id -> JRecipeUtil
					.generateMelting(id, entry.getCrushedOreId(), entry.getFluidId(), FluidConstants.INGOT, null, 0));
		}
	}

	private static Identifier recipeId(OreProcessingEntry entry, String type, String name) {
		return Freaworld.id("ore_processing/" + entry.getId().getPath() + "/" + type + "/" + name);
	}
}
