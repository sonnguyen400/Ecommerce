//package com.nhs.individual.DAO;
//
//import com.nhs.individual.Constant.OrderStatus;
//import com.nhs.individual.Domain.ShopOrder;
//import jakarta.persistence.criteria.*;
//import jakarta.persistence.metamodel.EntityType;
//import org.springframework.data.jpa.domain.Specification;
//import org.springframework.stereotype.Repository;
//
//import java.util.Date;
//
//@Repository
//public interface IShopOrderDAO extends Specification<ShopOrder> {
//    static Specification<ShopOrder> from(Date from, Date to){
//        return (shopOrder,cp,cq)->cq.between(shopOrder.get("order_date"),from,to);
//    }
//    static Specification<ShopOrder> shipMethod(String shipMethod){
//        return (shopOrder,cp,cq)->{
//            EntityType<ShopOrder> entityType=shopOrder.getModel();
//            cq.equal(shopOrder.getModel().,shipMethod)
//        };
//    }
//    static Specification<ShopOrder> status(OrderStatus status){
//        return (shopOrder,cp,cq)->{
//
//            shopOrder.join(OrderStatus_)
//        };
//    }
//
//}
