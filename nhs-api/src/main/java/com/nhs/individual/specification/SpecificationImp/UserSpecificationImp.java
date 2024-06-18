package com.nhs.individual.specification.SpecificationImp;

import org.springframework.stereotype.Repository;

@Repository
public class UserSpecificationImp extends GeneralSpecificationImp {
//    public List<User> findUser(String name, Address address, AccountProvider provider, AccountStatus status, AccountRole role,Integer page,Integer size){
//        CriteriaBuilder cb = this.entityManager.getCriteriaBuilder();
//        CriteriaQuery<User> cq = cb.createQuery(User.class);
//        Root<User> root=cq.from(User.class);
//        List<Predicate> predicates=new ArrayList<>();
//        if(name!=null&&!name.isEmpty()){
//            predicates.add(IUserSpecification.byName(name).toPredicate(root,cq,cb));
//        }
//        if(address!=null){
//            predicates.add(IUserSpecification.byAddress(address).toPredicate(root,cq,cb));
//        }
//        if(provider!=null){
//            predicates.add(IUserSpecification.byProvider(provider).toPredicate(root,cq,cb));
//        }
//        if(status!=null){
//            predicates.add(IUserSpecification.byStatus(status).toPredicate(root,cq,cb));
//        }
//        Predicate[] predicateArr=new Predicate[predicates.size()];
//        cq.where(cb.or(predicates.toArray(predicateArr)));
//        return entityManager.createQuery(cq).setFirstResult(page*size).setMaxResults(size).getResultList();
//    }
}
