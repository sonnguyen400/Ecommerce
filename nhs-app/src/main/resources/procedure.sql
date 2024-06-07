DELIMITER //
CREATE PROCEDURE setDefaultUserAddress(
    IN user_id_param INT,
    IN address_id_param INT
)
begin
    declare err tinyint default false;
    declare continue  handler for sqlexception
        set err=true;
    start transaction;
    update user_address set is_default=false where user_id=user_id_param;
    update user_address set is_default=true where address_id=address_id_param and user_id=user_id_param;
    if err then
        rollback;
    else
        commit;
        select * from user_address where address_id=address_id_param;
    end if;
end //

