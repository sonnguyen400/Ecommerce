package com.nhs.individual.views;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nhs.individual.domain.Product;
import com.nhs.individual.domain.ProductItem;
import com.nhs.individual.domain.ShopOrder;
import com.nhs.individual.dto.ProductDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import java.sql.Date;

@Getter
@Setter
@Immutable
@Entity
@Subselect("select count(product_item.product_id) as number_products,\n" +
        "           product_id,date_format(shop_order.order_date,\"%Y-%m-%d\") as date from shop_order\n" +
        "    join order_line                                                                                                                join product_item\n" +
        "    where shop_order.id=order_line.order_id\n" +
        "      and order_line.product_item_id=product_item.id\n" +
        "    group by product_item.product_id")
public class MostProductOrder {
    @Id
    @Column(name = "date")
    private Date date;
    private Integer number_products;
    @OneToOne
    @JoinColumn(name = "product_id")
    Product product;
    @ManyToOne
    @JoinColumn(name = "date", insertable = false,updatable = false,referencedColumnName = "date")
    @JsonIgnore
    private OrderPerDay orderPerDay;

    public ProductDto getProduct() {
        return new ProductDto(product);
    }
}
