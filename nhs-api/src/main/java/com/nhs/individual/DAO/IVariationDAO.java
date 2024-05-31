package com.nhs.individual.DAO;

import com.nhs.individual.Domain.Variation;
import com.nhs.individual.Domain.VariationOption;
import com.nhs.individual.Domain.VariationOption_;
import com.nhs.individual.Domain.Variation_;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public interface IVariationDAO extends Specification<Variation> {
    static Specification<Variation> hasValue(String name,Variation value){
        return (root,query,cb)->{
            Join<Variation, VariationOption> variationOptionJoin=root.join("variation_option");
            return cb.and(cb.equal(root.get(Variation_.NAME),name),
                    cb.equal(variationOptionJoin.get(VariationOption_.VALUE),value));
        };
    }
}
