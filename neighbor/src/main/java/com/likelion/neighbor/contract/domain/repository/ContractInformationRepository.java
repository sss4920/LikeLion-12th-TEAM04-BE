package com.likelion.neighbor.contract.domain.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.likelion.neighbor.contract.domain.ContractInformation;
import com.likelion.neighbor.user.domain.User;

public interface ContractInformationRepository extends JpaRepository<ContractInformation, Long> {
	List<ContractInformation> findAllByUser(User user);

	List<ContractInformation> findAllByUserNot(User user);

}
