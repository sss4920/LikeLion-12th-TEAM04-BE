package com.likelion.neighbor.insurance.controller;

import com.likelion.neighbor.contract.domain.NonCoveredItem;
import com.likelion.neighbor.insurance.service.NonCoveredItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/non-covered")
public class NonCoveredItemController {

    private final NonCoveredItemService nonCoveredItemService;

    @Autowired
    public NonCoveredItemController(NonCoveredItemService nonCoveredItemService) {
        this.nonCoveredItemService = nonCoveredItemService;
    }

    @GetMapping("/check")
    public boolean checkNonCovered(@RequestParam String middleName, @RequestParam String shortName) {
        return nonCoveredItemService.isNonCovered(middleName, shortName);
    }

    @GetMapping("/check/middleName")
    public boolean checkByMiddleName(@RequestParam String middleName) {
        return nonCoveredItemService.existsByMiddleName(middleName);
    }

    @GetMapping("/check/shortName")
    public boolean checkByShortName(@RequestParam String shortName) {
        return nonCoveredItemService.existsByShortName(shortName);
    }

    @PostMapping("/add")
    public NonCoveredItem addNonCoveredItem(@RequestParam String middleName, @RequestParam String shortName) {
        return nonCoveredItemService.addNonCoveredItem(middleName, shortName);
    }
}
