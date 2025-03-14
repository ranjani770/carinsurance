 package com.carinsurance.carinsurance.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carinsurance.carinsurance.Repository.PolicyRepository;
import com.carinsurance.carinsurance.Repository.UserRepository;
import com.carinsurance.carinsurance.model.Customer;
import com.carinsurance.carinsurance.model.Policy;

@Service
public class PolicyService {

	private final PolicyRepository policyRepository;

	private final UserRepository userRepository;

	public PolicyService(PolicyRepository policyRepository,UserRepository userRepository) {
		this.policyRepository = policyRepository;
		this.userRepository= userRepository;
	}

	public List<Policy> getAllPolicies() {
		return policyRepository.findAll();
	}

	public Policy createPolicy(Policy policy) {
		Long cusId=policy.getCustomer().getId();
		Customer cus=userRepository.findById(cusId)
				.orElseThrow(() -> new RuntimeException("Customer not found"));
		policy.setCustomer(cus);
		return policyRepository.save(policy);
	}

	public Policy getPolicy(Long id) {
		return policyRepository.findById(id).
				orElseThrow(() -> new RuntimeException("Policy not found"));
	}

}
