package com.nhs.individual.DAO;

import com.nhs.individual.Constant.OrderStatus;
import com.nhs.individual.Domain.ShopOrder;
import com.nhs.individual.Domain.ShopOrderStatus;
import com.nhs.individual.Domain.ShopOrderStatus_;
import com.nhs.individual.Domain.ShopOrder_;
import jakarta.persistence.criteria.*;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface IShopOrderDAO extends Specification<ShopOrder> {
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
        return (shopOrder,cp,cq)->{
            Subquery<Integer> orderStatusSubquery=cp.subquery(Integer.class);
            Root<ShopOrderStatus> shoporderStatusRoot=orderStatusSubquery.from(ShopOrderStatus.class);
            orderStatusSubquery.select(shoporderStatusRoot.get(ShopOrderStatus_.STATUS))
                    .where(cq.equal(shopOrder.get(ShopOrder_.ID),shoporderStatusRoot.get(ShopOrderStatus_.SHOP_ORDER_ID)),
                            cq.equal(shoporderStatusRoot.get(ShopOrderStatus_.STATUS),status));
            return cq.exists(orderStatusSubquery);
        };
    }
    static Specification<ShopOrder> byUser(Integer userId){
        return (shopOrder,cp,cq)->cq.equal(shopOrder.get(ShopOrder_.USER_ID),userId);
    }

}
