package tacos.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import tacos.domain.Ingredient;

@Repository
public class JDBCIngredientRepository implements IngredientRepository {
    
    private JdbcTemplate jdbc;
    
    @Autowired
    public JDBCIngredientRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        
        return jdbc.query("select id, name, type from Ingredient", 
                this::mapRowToIngredient);
        
    }

    @Override
    public Ingredient findOne(String id) {

        return jdbc.queryForObject("select id, name, type from Ingredient where id=?", this::mapRowToIngredient, id);
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        
        jdbc.update(
                "insert into Ingredient (id, name type) values (?, ?, ?)", 
                ingredient.getId(), 
                ingredient.getName(), 
                ingredient.getType().toString());
       
        return ingredient;
    }

    private Ingredient mapRowToIngredient(ResultSet resultset, int rowNumber) throws SQLException {
        
        
        return new Ingredient(resultset.getString("id"), resultset.getString("name"), Ingredient.Type.valueOf(resultset.getString("type")));
    }

}
