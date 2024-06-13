package com.nhs.individual.Specification;

import com.nhs.individual.Domain.User;
import com.nhs.individual.Specification.SpecificationImp.AbstractSpecification;

public class UserSpecification extends AbstractSpecification<User> {
    public UserSpecification(DynamicSearch criteria) {
        super(criteria);
    }
}
