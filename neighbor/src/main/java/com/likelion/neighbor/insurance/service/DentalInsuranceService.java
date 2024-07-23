package com.likelion.neighbor.insurance.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likelion.neighbor.insurance.domain.DentalInsurance;
import com.likelion.neighbor.insurance.domain.repository.DentalInsuranceRepository;
import com.likelion.neighbor.global.exception.NotFoundException;
import com.likelion.neighbor.global.exception.model.Error;
import com.likelion.neighbor.user.domain.User;
import com.likelion.neighbor.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DentalInsuranceService {
	private final DentalInsuranceRepository dentalInsuranceRepository;
	private final UserRepository userRepository;

	private User findUserInInsuranceService(String userId){
		return userRepository.findById(Long.parseLong(userId))
			.orElseThrow(
				() -> new NotFoundException(Error.MEMBERS_NOT_FOUND_EXCEPTION,
					Error.MEMBERS_NOT_FOUND_EXCEPTION.getMessage())
			);
	}

	private boolean hasPermission(User user, DentalInsurance dentalInsurance){
		// return user.equals(dentalInsurance.getUser());
		return true;
	}

}
