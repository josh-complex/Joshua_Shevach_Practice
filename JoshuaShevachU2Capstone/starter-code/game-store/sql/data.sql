use game_store;

insert into game(title, esrb_rating, description, price, studio, quantity)
	values ('World of Goo', 'PG', 'puzzle video game', 12.99, '2D Boy', 25);

insert into console(model, manufacturer, memory_amount, processor, price, quantity)
	values ('Nintendo Switch', 'Nintendo', '4GB', 'ARM 4 Cortex-A57', 299.99, 7);
    
insert into tshirt (size, color, description, price, quantity)
	values ('Med', 'Yellow', 'Science', 14.95, 0);
    
insert into invoice
	(`name`, street, city, state, zipcode, item_type, item_id, unit_price, quantity, subtotal, tax, processing_fee, total)
	values('Eng',null,'Eagan','MN','55555','Console',1,249.99,1,249.99,10.00,2,261.99);