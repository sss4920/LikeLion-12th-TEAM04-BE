package com.likelion.neighbor.insurance.controller;

import com.likelion.neighbor.insurance.controller.dto.request.DentalInsuranceSaveReqDto;
import com.likelion.neighbor.insurance.controller.dto.response.DentalInsuranceInfoListResDto;
import com.likelion.neighbor.insurance.controller.dto.response.DentalInsuranceInfoResDto;
import com.likelion.neighbor.insurance.domain.DentalInsurance;
import com.likelion.neighbor.insurance.service.DentalInsuranceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dental-insurances")
public class DentalInsuranceController {

    private final DentalInsuranceService dentalInsuranceService;

    @GetMapping
    public ResponseEntity<DentalInsuranceInfoListResDto> getAllDentalInsurances() {
        List<DentalInsurance> dentalInsurances = dentalInsuranceService.findAll();
        DentalInsuranceInfoListResDto responseDto = DentalInsuranceInfoListResDto.fromEntityList(dentalInsurances);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DentalInsuranceInfoResDto> getDentalInsuranceById(@PathVariable Long id) {
        DentalInsurance dentalInsurance = dentalInsuranceService.findById(id);
        return ResponseEntity.ok(DentalInsuranceInfoResDto.fromEntity(dentalInsurance));
    }

    @PostMapping
    public ResponseEntity<DentalInsuranceInfoResDto> createDentalInsurance(@Valid @RequestBody DentalInsuranceSaveReqDto dto) {
        DentalInsurance savedDentalInsurance = dentalInsuranceService.save(dto.toEntity());
        return ResponseEntity.ok(DentalInsuranceInfoResDto.fromEntity(savedDentalInsurance));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DentalInsuranceInfoResDto> updateDentalInsurance(@PathVariable Long id, @Valid @RequestBody DentalInsuranceSaveReqDto dto) {
        DentalInsurance updatedDentalInsurance = dentalInsuranceService.update(id, dto.toEntity());
        return ResponseEntity.ok(DentalInsuranceInfoResDto.fromEntity(updatedDentalInsurance));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDentalInsurance(@PathVariable Long id) {
        dentalInsuranceService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
