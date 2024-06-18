package com.nhs.individual.specification;

import com.nhs.individual.Domain.User;
import com.nhs.individual.specification.SpecificationImp.AbstractSpecification;

public class UserSpecification extends AbstractSpecification<User> {
    public UserSpecification(DynamicSearch criteria) {
        super(criteria);
    }
}
