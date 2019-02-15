package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {

	//sudo systemctl enable mongod
	//sudo systemctl start mongod

	private MongoOperations mongoOperations;
	private MongoTemplate mongoTemplate;

	public RecipeServiceImpl(MongoOperations mongoOperations, MongoTemplate mongoTemplate) {
		this.mongoOperations = mongoOperations;
		this.mongoTemplate = mongoTemplate;
	}

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
		return null;
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

}
