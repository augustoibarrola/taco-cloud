package tacos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Ingredient {
	
//	private final String id;
//	private final String name;
//	private final Type type;
    
    private  String id;
    private  String name;
    private  Type type;
	
	public Ingredient(String id, String name, Type type) {
		
		this.id= id;
		this.name = name;
		this.type = type;
		
	}
	
	public static enum Type {
		
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
		
	}

    public Type getType() {
        // TODO Auto-generated method stub
        return this.type;
    }
    
    public void setType(Type type) {
        this.type = type;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
	
	

}
