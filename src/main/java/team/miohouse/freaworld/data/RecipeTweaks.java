package team.miohouse.freaworld.data;

import static team.miohouse.freaworld.ModEntry.CR;
import static team.miohouse.freaworld.ModEntry.MC;
import static team.miohouse.freaworld.ModEntry.MI;

import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;
import org.quiltmc.qsl.recipe.api.builder.VanillaRecipeBuilders;

import net.minecraft.item.Items;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import team.miohouse.freaworld.Freaworld;

public class RecipeTweaks implements RemoveRecipesCallback, AddRecipesCallback {

    @Override
    public void removeRecipes(RemoveRecipesCallback.RecipeHandler handler) {
        handler.removeIf(r -> r.getOutput().isOf(MI.asItem("forge_hammer")) && notFr(r));
        handler.removeIf(r -> r.getOutput().isOf(CR.asItem("mechanical_press")) && notFr(r));
        handler.removeIf(RecipeType.CRAFTING, r -> r.getOutput().isOf(MC.asItem("smithing_table")) && notFr(r));
    }

    @Override
    public void addRecipes(AddRecipesCallback.RecipeHandler handler) {
        /*
        example:
        handler.register(recipeId("crafting", "smithing_table"),
                id -> VanillaRecipeBuilders.shapedRecipe("AAA", "AOA", "AAA")
                        .ingredient('A', CR.asItem("iron_sheet"))
                        .ingredient('O', Items.CRAFTING_TABLE)
                        .output(Items.SMITHING_TABLE.getDefaultStack())
                        .build(id, ""));
        */
        handler.register(recipeId("crafting", "smithing_table"),
                id -> VanillaRecipeBuilders.shapedRecipe("AAA", "AOA", "AAA")
                        .ingredient('A', CR.asItem("iron_sheet"))
                        .ingredient('O', Items.CRAFTING_TABLE)
                        .output(Items.SMITHING_TABLE.getDefaultStack())
                        .build(id, ""));
    }

    private static boolean notFr(Recipe<?> recipe) {
        return !recipe.getId().getNamespace().equals(Freaworld.ID);
    }

    private static Identifier recipeId(String type, String name) {
        return Freaworld.id(type + "/" + name);
    }

}
