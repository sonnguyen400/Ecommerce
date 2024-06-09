package com.nhs.individual.DAO.DAOImp;

import com.nhs.individual.Constant.OrderStatus;
import com.nhs.individual.DAO.IShopOrderDAO;
import com.nhs.individual.Domain.ShopOrder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class ShopOrderDAO{
    @PersistenceContext
    EntityManager entityManager;

    public List<ShopOrder> findAll(Integer userId, String dateFrom, String dateTo, Integer page,Integer size,OrderStatus orderStatus,String orderBy,Sort.Direction direction) {
        CriteriaBuilder cb=entityManager.getCriteriaBuilder();
        CriteriaQuery<ShopOrder> cq=cb.createQuery(ShopOrder.class);
        Root<ShopOrder> shopOrder=cq.from(ShopOrder.class);

        List<Predicate> predicates=new ArrayList<Predicate>();
        if(dateFrom!=null&&!dateFrom.isEmpty()){
            predicates.add(IShopOrderDAO.fromToDate(dateFrom,dateTo).toPredicate(shopOrder,cq,cb));
        }
        if(userId!=null){
            predicates.add(IShopOrderDAO.byUser(userId).toPredicate(shopOrder,cq,cb));
        }
        if(orderStatus!=null){
            predicates.add(IShopOrderDAO.withStatus(orderStatus).toPredicate(shopOrder,cq,cb));
        }
        Predicate[] predicateArr = new Predicate[predicates.size()];
        cq.where(predicates.toArray(predicateArr));


        Order order=null;
        if (orderBy != null) {
            if(direction==Sort.Direction.ASC) order=cb.asc(shopOrder.get(orderBy));
            else if(direction==Sort.Direction.DESC) order=cb.desc(shopOrder.get(orderBy));
        }
        if(order!=null) cq.orderBy(order);
        return entityManager.createQuery(cq).setFirstResult(page*size
        ).setMaxResults(size).getResultList();
    }
}
