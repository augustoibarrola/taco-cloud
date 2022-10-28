delete from Taco_Order_Tacos;
delete from Taco_Ingredients;
delete from Taco;
delete from Taco_Order;

delete from Ingredient; 

insert into Ingredient(id, name, INGREDIENT_TYPE )
	values ('FLTO', 'Flour Tortilla', 'WRAP' );
	
insert into Ingredient(id, name, INGREDIENT_TYPE )
	values('COTO','Corn Tortilla', 'WRAP');

insert into Ingredient(id, name, INGREDIENT_TYPE )
	values('GRBF', 'Ground Beef', 'PROTEIN');

insert into Ingredient(id, name, INGREDIENT_TYPE )
	values('CARN', 'Carnitas', 'PROTEIN');

insert into Ingredient(id, name, INGREDIENT_TYPE )
	values('TMTO', 'Diced Tomatoes', 'VEGGIES');
	
insert into Ingredient(id, name, INGREDIENT_TYPE )
	values('LETC', 'Lettuce', 'VEGGIES');
	
insert into Ingredient(id, name, INGREDIENT_TYPE )
	values('CHED', 'Cheddar', 'CHEESE');
	
insert into Ingredient(id, name, INGREDIENT_TYPE )
	values('JACK', 'Monterrey Jack', 'CHEESE');
	
insert into Ingredient(id, name, INGREDIENT_TYPE )
	values('SLSA', 'Salsa', 'SAUCE');

insert into Ingredient(id, name, INGREDIENT_TYPE )
	values('SRCR', 'Sour Cream', 'SAUCE');