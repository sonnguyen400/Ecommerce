use ecommerce;

insert into role(name) values ("USER"),("ADMIN");
insert into order_status(value) values ("CANCEL"),("PURCHASING"),("PENDING"),("PREPARING"),("DELIVERING"),("DELIVERED"),("RETURN"),("EXCHANGE"),("REFUNDED");
insert into shipping_method(name,price) values("STANDARD",5),("FAST",10),("EXPRESS",20);

delimiter //
create trigger initial_order
    after insert on shop_order
    for each row
begin
    declare purchasing_id int;
    select id into purchasing_id from order_status where value="PURCHASING";
    insert into shop_order_status(shop_order_id,order_status_id) value(new.id,purchasing_id);
end//