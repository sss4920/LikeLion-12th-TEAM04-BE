package com.likelion.neighbor.contract.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.likelion.neighbor.contract.application.response.ContractResponseDto;
import com.likelion.neighbor.contract.domain.ContractInformation;
import com.likelion.neighbor.contract.domain.repository.ContractInformationRepository;
import com.likelion.neighbor.global.exception.NotFoundException;
import com.likelion.neighbor.global.exception.model.Error;
import com.likelion.neighbor.user.domain.User;
import com.likelion.neighbor.user.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ContractService {

	private final ContractInformationRepository contractInformationRepository;
	private final UserRepository userRepository;

	public List<ContractResponseDto> getContractInformationList(String userId){
		User user = userRepository.findById(Long.valueOf(userId)).orElseThrow(
			() -> new NotFoundException(Error.MEMBERS_NOT_FOUND_EXCEPTION, Error.MEMBERS_NOT_FOUND_EXCEPTION.getMessage())
		);

		return contractInformationRepository.findAllByUser(user)
			.stream()
			.map((ContractResponseDto::from))
			.toList();
	}

	public List<ContractResponseDto> getSimpleInformationList(String userId){
		User user = userRepository.findById(Long.valueOf(userId)).orElseThrow(
			() -> new NotFoundException(Error.MEMBERS_NOT_FOUND_EXCEPTION, Error.MEMBERS_NOT_FOUND_EXCEPTION.getMessage())
		);
		return contractInformationRepository.findAllByUser(user)
			.stream()
			.map((ContractResponseDto::makeSimleResponse))
			.toList();
	}



}
