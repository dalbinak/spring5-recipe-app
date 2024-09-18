package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipe());
    }

    private List<Recipe> getRecipe() {
        List<Recipe> recipes = new ArrayList<>(2);

        // Get UOMs
        Optional<UnitOfMeasure> eachUnitOfMeasure = unitOfMeasureRepository.findByDescription("Each");
        if (!eachUnitOfMeasure.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> tablespoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if (!tablespoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> teaspoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if (!teaspoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> pinchUomOptional = unitOfMeasureRepository.findByDescription("Pinch");

        if (!pinchUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if (!cupUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> ounceUomOptional = unitOfMeasureRepository.findByDescription("Ounce");

        if (!ounceUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }
        Optional<UnitOfMeasure> poundsUomOptional = unitOfMeasureRepository.findByDescription("Pounds");

        if (!poundsUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found");
        }

        // Get optionals
        UnitOfMeasure eachUom = eachUnitOfMeasure.get();
        UnitOfMeasure tablespoonUom = tablespoonUomOptional.get();
        UnitOfMeasure teaspoonUom = teaspoonUomOptional.get();
        UnitOfMeasure pinchUom = pinchUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();
        UnitOfMeasure poundsUom = poundsUomOptional.get();

        // Get categories
        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if (!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found");
        }
        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if (!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found");
        }
        Optional<Category> chineseCategoryOptional = categoryRepository.findByDescription("Chinese");
        if (!chineseCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found");
        }
        Optional<Category> italianCategoryOptional = categoryRepository.findByDescription("Italian");
        if (!italianCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found");
        }
        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();

        // Guacamole
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("Cut the avocados:\n" +
                "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "Elise Bauer\n" +
                "Mash the avocado flesh:\n" +
                "Using a fork, roughly mash the avocado. Don't overdo it! The guacamole should be a little chunky.\n" +
                "\n" +
                "Elise Bauer\n" +
                "Add the remaining ingredients to taste:\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "Serve immediately:\n" +
                "If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)");
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Guacamole: A Classic Mexican Dish\n" +
                "The word \"guacamole\" and the dip, are both originally from Mexico, where avocados have been cultivated for thousands of years. The name is derived from two Aztec Nahuatl words—ahuacatl (avocado) and molli (sauce).");
        guacRecipe.setNotes(guacNotes);

        guacRecipe.addIngredient(new Ingredient("Ripe avocados", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Kosher salt", new BigDecimal("0.25"), teaspoonUom));
        guacRecipe.addIngredient(new Ingredient("Lime", new BigDecimal(1), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("onion", new BigDecimal(4), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("Jalepeno", new BigDecimal(2), eachUom));
        guacRecipe.addIngredient(new Ingredient("Cilantro", new BigDecimal(2), tablespoonUom));
        guacRecipe.addIngredient(new Ingredient("Pepper", new BigDecimal(1), pinchUom));
        guacRecipe.addIngredient(new Ingredient("Tomato", new BigDecimal("0.5"), eachUom));

        guacRecipe.getCategories().add(mexicanCategory);

        // add to return list
        recipes.add(guacRecipe);

        // Tacos recipe
        Recipe tacos = new Recipe();
        tacos.setDescription("Chicken Tacos");
        tacos.setPrepTime(10);
        tacos.setCookTime(240);
        tacos.setDifficulty(Difficulty.MEDIUM);
        tacos.setDirections("Combine the taco ingredients (except tortillas) in the slow cooker:\n" +
                "Combine the chicken, tomato sauce, honey, onion, lime juice, garlic powder, salt, and pepper to your slow cooker. Add 1 to 3 chopped chipotle peppers and 1 to 2 tablespoons of Adobo sauce, depending on your preferred level of heat. Mix together well.\n" +
                "\n" +
                "Nick Evans\n" +
                "Cover and cook:\n" +
                "Cook in the slow cooker on high for 4 hours or low for 6 to 8 hours on low.\n" +
                "\n" +
                "Nick Evans\n" +
                "Shred the chicken:\n" +
                "At the end of cooking time, use two forks to shred chicken in the pot. Mix the shredded chicken with the sauce.\n" +
                "\n" +
                "At this point, the chicken can be held on the \"warm\" setting, covered, for 1 to 2 hours. Stir additional water as needed if the pot starts to become dry.\n" +
                "\n" +
                "Nick Evans\n" +
                "Serve:\n" +
                "Set out a stack of tortillas and all the toppings. Assemble and serve the tacos from the slow cooker.\n" +
                "\n" +
                "Leftover chicken will keep refrigerated for up to 5 days or frozen for up to 3 months.");
        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("These tacos definitely bring the flavor! Made with shredded chicken, chipotle peppers, and honey, each bite has just enough sweetness and heat to wake up your taste buds. The chicken also stays really juicy when cooked in the slow cooker – after cooking, you'll be able to shred the meat with a fork!");
        tacos.setNotes(tacoNotes);
        tacos.addIngredient(new Ingredient("Chicken", new BigDecimal("1.5"), poundsUom));
        tacos.addIngredient(new Ingredient("Tomato sauce", new BigDecimal("0.5"), cupUom));
        tacos.addIngredient(new Ingredient("Honey", new BigDecimal("0.25"), cupUom));
        tacos.addIngredient(new Ingredient("Onions", new BigDecimal("1.5"), cupUom));
        tacos.addIngredient(new Ingredient("Lime", new BigDecimal(1), eachUom));
        tacos.addIngredient(new Ingredient("Chipotle pepper", new BigDecimal(3), eachUom));
        tacos.addIngredient(new Ingredient("Adobo sauce", new BigDecimal(2), tablespoonUom));
        
        tacos.getCategories().add(mexicanCategory);
        tacos.getCategories().add(americanCategory);

        recipes.add(tacos);


        return recipes;
    }
}
