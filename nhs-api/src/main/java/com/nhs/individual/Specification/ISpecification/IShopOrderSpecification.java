package com.nhs.individual.Specification.ISpecification;

import com.nhs.individual.Constant.OrderStatus;
import com.nhs.individual.Domain.ShopOrder;
import com.nhs.individual.Domain.ShopOrderStatus;
import com.nhs.individual.Domain.ShopOrderStatus_;
import com.nhs.individual.Domain.ShopOrder_;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

public interface IShopOrderSpecification extends GeneralSpecification<ShopOrder>{
    static Specification<ShopOrder> fromToDate(String from, String to){
        return (shopOrder,cp,cq)->cq.between(shopOrder.get("order_date"),from,to);
    }

//    static Specification<Order> orderBy(String propertyName, Sort.Direction direction){
//        return (shopOrder,cp,cq)->{
//            if(direction== Sort.Direction.ASC) return cq.desc(shopOrder.get(propertyName));
//            else return cq.desc(shopOrder.get(propertyName));
//        };
//    }

    static Specification<ShopOrder> withStatus(OrderStatus status){
        return (shopOrder,cq,cb)->{
            Subquery<Integer> orderStatusSubquery=cb.createQuery().subquery(Integer.class);
            Root<ShopOrderStatus> orderStatusRoot=orderStatusSubquery.from(ShopOrderStatus.class);
            orderStatusSubquery.select(cb.max(orderStatusRoot.get(ShopOrderStatus_.ID)))
                    .where(cb.equal(orderStatusRoot.get(ShopOrderStatus_.SHOP_ORDER_ID),shopOrder.get(ShopOrder_.ID)));

            Subquery<Integer> orderStatusSubquery1=cq.subquery(Integer.class);
            Root<ShopOrderStatus> orderStatusRoot1=orderStatusSubquery1.from(ShopOrderStatus.class);
            orderStatusSubquery1
                    .select(orderStatusRoot1.get(ShopOrderStatus_.ID))
                    .where(cb.equal(orderStatusRoot1.get(ShopOrderStatus_.STATUS),status),
                            orderStatusRoot1.get(ShopOrderStatus_.ID).in(orderStatusSubquery));
            return cb.exists(orderStatusSubquery1);
        };
    }
    static Specification<ShopOrder> byUser(Integer userId){
        return (shopOrder,cp,cq)->cq.equal(shopOrder.get(ShopOrder_.USER_ID),userId);
    }
}
