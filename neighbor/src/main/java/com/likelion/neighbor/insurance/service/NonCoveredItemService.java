package com.likelion.neighbor.insurance.service;

import com.likelion.neighbor.contract.domain.NonCoveredItem;
import com.likelion.neighbor.insurance.domain.repository.NonCoveredItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NonCoveredItemService {

    private final NonCoveredItemRepository nonCoveredItemRepository;

    @Autowired
    public NonCoveredItemService(NonCoveredItemRepository nonCoveredItemRepository) {
        this.nonCoveredItemRepository = nonCoveredItemRepository;
    }

    public boolean isNonCovered(String middleName, String shortName) {
        return nonCoveredItemRepository.existsByMiddleNameAndShortName(middleName, shortName);
    }

    public boolean existsByMiddleName(String middleName) {
        return nonCoveredItemRepository.existsByMiddleName(middleName);
    }

    public boolean existsByShortName(String shortName) {
        return nonCoveredItemRepository.existsByShortName(shortName);
    }

    public NonCoveredItem addNonCoveredItem(String middleName, String shortName) {
        NonCoveredItem newItem = new NonCoveredItem(null, middleName, shortName);
        return nonCoveredItemRepository.save(newItem);
    }
}
