package com.nhs.individual.Specification;

import com.nhs.individual.Domain.Product;
import com.nhs.individual.Specification.SpecificationImp.AbstractSpecification;

public class ProductSpecification extends AbstractSpecification<Product> {
    public ProductSpecification(DynamicSearch criteria) {
        super(criteria);
    }
}
