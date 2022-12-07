package team.miohouse.freaworld.data;

import static team.miohouse.freaworld.ModEntry.MI;

import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.AddRecipesCallback;
import org.quiltmc.qsl.recipe.api.RecipeLoadingEvents.RemoveRecipesCallback;

public class RecipeTweaks implements RemoveRecipesCallback, AddRecipesCallback {

    @Override
    public void removeRecipes(RemoveRecipesCallback.RecipeHandler handler) {
        handler.removeIf(p -> p.getOutput().isOf(MI.asItem("forge_hammer")));
    }

    @Override
    public void addRecipes(AddRecipesCallback.RecipeHandler handler) {

    }

}
