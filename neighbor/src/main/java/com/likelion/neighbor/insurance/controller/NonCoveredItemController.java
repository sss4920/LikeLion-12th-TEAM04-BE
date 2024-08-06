package com.likelion.neighbor.insurance.controller;

import com.likelion.neighbor.contract.domain.NonCoveredItem;
import com.likelion.neighbor.global.exception.model.BaseResponse;
import com.likelion.neighbor.insurance.service.NonCoveredItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/non-covered")
public class NonCoveredItemController {

    private final NonCoveredItemService nonCoveredItemService;

    @Autowired
    public NonCoveredItemController(NonCoveredItemService nonCoveredItemService) {
        this.nonCoveredItemService = nonCoveredItemService;
    }

    @GetMapping("/check")
    public boolean checkNonCovered(@RequestParam String middleName, @RequestParam String shortName, @AuthenticationPrincipal String userId) {
        return nonCoveredItemService.isNonCovered(middleName, shortName, userId);
    }

    @GetMapping("/check/middleName")
    public boolean checkByMiddleName(@RequestParam String middleName, @AuthenticationPrincipal String userId ) {
        return nonCoveredItemService.existsByMiddleName(middleName, userId);
    }

    @GetMapping("/check/shortName")
    public BaseResponse<Boolean> checkByShortName(@RequestParam String shortName,  @AuthenticationPrincipal String userId) {
        return nonCoveredItemService.existsByShortName(shortName, userId);
    }

    @PostMapping("/add")
    public NonCoveredItem addNonCoveredItem(@RequestParam String middleName, @RequestParam String shortName,  @AuthenticationPrincipal String userId) {
        return nonCoveredItemService.addNonCoveredItem(middleName, shortName, userId);
    }
}
