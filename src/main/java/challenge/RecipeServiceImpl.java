package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

	//sudo systemctl enable mongod
	//sudo systemctl start mongod

	@Autowired
	private MongoOperations mongoOperations;

	@Override
	public Recipe save(Recipe recipe) {
		mongoOperations.insert(recipe);
		return recipe;
	}

	@Override
	public void update(String id, Recipe recipe) {
		mongoOperations.updateFirst(
				RecipeComponent.buscaRecipePorId(id),

				Update.update("title", recipe.getTitle())
						.set("description", recipe.getDescription())
						.set("ingredients", recipe.getIngredients()),

				Recipe.class);
	}

	@Override
	public void delete(String id) {
		mongoOperations.remove(RecipeComponent.buscaRecipePorId(id), Recipe.class);
	}

	@Override
	public Recipe get(String id) {
		return mongoOperations.findOne(RecipeComponent.buscaRecipePorId(id), Recipe.class);
	}

	@Override
	public List<Recipe> listByIngredient(String ingredient) {
		return mongoOperations.find(
				new Query(Criteria.where("ingredients")
						.in(ingredient))
						.with(new Sort(Sort.Direction.ASC, "title")),
				Recipe.class);
	}

	@Override
	public List<Recipe> search(String search) {
		return null;
	}

	@Override
	public void like(String id, String userId) {

	}

	@Override
	public void unlike(String id, String userId) {

	}

	@Override
	public RecipeComment addComment(String id, RecipeComment comment) {
		return null;
	}

	@Override
	public void updateComment(String id, String commentId, RecipeComment comment) {

	}

	@Override
	public void deleteComment(String id, String commentId) {

	}

	@Override
	public List<Recipe> todos(){
		return mongoOperations.findAll(Recipe.class);
	}
}
