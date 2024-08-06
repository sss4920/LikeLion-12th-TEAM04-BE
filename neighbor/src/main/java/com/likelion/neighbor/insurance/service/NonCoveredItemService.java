package com.likelion.neighbor.insurance.service;

import com.likelion.neighbor.contract.domain.NonCoveredItem;
import com.likelion.neighbor.global.exception.NotFoundException;
import com.likelion.neighbor.global.exception.model.Error;
import com.likelion.neighbor.insurance.domain.repository.NonCoveredItemRepository;
import com.likelion.neighbor.user.domain.User;
import com.likelion.neighbor.user.domain.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NonCoveredItemService {

    private final NonCoveredItemRepository nonCoveredItemRepository;
    private final UserRepository userRepository;

    @Autowired
    public NonCoveredItemService(NonCoveredItemRepository nonCoveredItemRepository, UserRepository userRepository) {
        this.nonCoveredItemRepository = nonCoveredItemRepository;
        this.userRepository = userRepository;

    }

    public boolean isNonCovered(String middleName, String shortName, String userId) {
        hasUserPermission(userId);
        return nonCoveredItemRepository.existsByMiddleNameAndShortName(middleName, shortName);
    }

    public boolean existsByMiddleName(String middleName, String userId) {
        hasUserPermission(userId);
        return nonCoveredItemRepository.existsByMiddleName(middleName);
    }

    public boolean existsByShortName(String shortName, String userId) {
        hasUserPermission(userId);
        return nonCoveredItemRepository.existsByShortName(shortName);
    }

    public NonCoveredItem addNonCoveredItem(String middleName, String shortName, String userId) {
        hasUserPermission(userId);
        NonCoveredItem newItem = new NonCoveredItem(null, middleName, shortName);
        return nonCoveredItemRepository.save(newItem);
    }

    private User hasUserPermission(String userId){
        return userRepository.findById(Long.parseLong(userId)).orElseThrow(
            () -> new NotFoundException(Error.MEMBERS_NOT_FOUND_EXCEPTION, Error.MEMBERS_NOT_FOUND_EXCEPTION.getMessage())
        );
    }
}
