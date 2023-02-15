package com.a2r.immobilierdz.repository.specs;

import com.a2r.immobilierdz.entity.Address;
import com.a2r.immobilierdz.entity.Address_;
import com.a2r.immobilierdz.entity.House;
import com.a2r.immobilierdz.entity.House_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class HouseSpecification implements Specification<House> {


    private final List<SearchCriteria> list;


    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    public HouseSpecification(List<SearchCriteria> criterias) {
        this.list = criterias;
    }


    @Override
    public Predicate toPredicate(Root<House> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        //create a new predicate list
        List<Predicate> predicate = new ArrayList<>();
        Join<House, Address> addressJoin = root.join(House_.address);


        //add criteria to predicate
        for (SearchCriteria criteria : list) {

            if (criteria.getValue() == null) {
                continue;
            }
            switch (criteria.getKey()) {
                case Address_.CITY:
                    predicate.add(builder
                            .equal(addressJoin.get(Address_.city),
                                    criteria.getValue()));
                    break;
                case House_.TYPE:
                    predicate.add(builder
                            .equal(root.get(House_.type),
                                    criteria.getValue()));
                    break;
                default:
                    switch (criteria.getOperation()) {
                        case EQUAL -> predicate.add(builder
                                .equal(root.get(criteria.getKey()),
                                        criteria.getValue()));
                        case GREATER_THAN -> predicate.add(builder.greaterThan(root.get(criteria.getKey()),
                                Integer.parseInt(criteria.getValue().toString())));
                        case GREATER_THAN_EQUAL ->
                                predicate.add(builder.greaterThanOrEqualTo(root.get(criteria.getKey()), (Integer) criteria.getValue()));
                        case LESS_THAN -> predicate.add(builder.lessThan(root.get(criteria.getKey()),
                                (Integer) criteria.getValue()));
                        case LESS_THAN_EQUAL ->
                                predicate.add(builder.lessThanOrEqualTo(root.get(criteria.getKey()), (Integer) criteria.getValue()));
                        default -> predicate.add(null);
                    }
                    break;
            }
        }
        return builder.and(predicate.toArray(new Predicate[0]));
    }
}
