package challenge;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component
public abstract class RecipeComponent {

    public static Query buscaRecipePorId(String id){
        return new Query(Criteria.where("_id").is(id));
    }
}
