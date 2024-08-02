package com.likelion.neighbor.contract.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.likelion.neighbor.contract.domain.ContractInformation;

public interface ContractInformationRepository extends JpaRepository<ContractInformation, Long> {

}
