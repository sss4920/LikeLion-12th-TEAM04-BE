package com.likelion.neighbor.insurance.domain.repository;

import com.likelion.neighbor.contract.domain.NonCoveredItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NonCoveredItemRepository extends JpaRepository<NonCoveredItem, Long> {
    boolean existsByMiddleNameAndShortName(String middleName, String shortName);
    boolean existsByMiddleName(String middleName);
    boolean existsByShortName(String shortName);
}
