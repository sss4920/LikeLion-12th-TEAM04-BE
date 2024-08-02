package com.likelion.neighbor.insurance.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.likelion.neighbor.insurance.domain.DentalInsurance;
import com.likelion.neighbor.user.domain.User;

public interface DentalInsuranceRepository extends JpaRepository<DentalInsurance,Long> {
//	List<DentalInsurance> findAllByUser(User user);
}
