use ecommerce;

insert into role(name) values ("USER"),("ADMIN");
insert into order_status(value) values ("CANCEL"),("PURCHASING"),("PENDING"),("PREPARING"),("DELIVERING"),("DELIVERED"),("RETURN"),("EXCHANGE"),("REFUNDED");
insert into shipping_method(name,price) values("STANDARD",5),("FAST",10),("EXPRESS",20);
