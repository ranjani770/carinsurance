package com.carinsurance.carinsurance.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carinsurance.carinsurance.model.Policy;
import com.carinsurance.carinsurance.service.PolicyService;

@RestController
@RequestMapping("/api/policies")
@CrossOrigin(origins = "*")
public class PolicyController {

	private final PolicyService policyService;

	public PolicyController(PolicyService policyService) {
		this.policyService = policyService;
	}

	@GetMapping
	public List<Policy> getAllPolicies() {
		return policyService.getAllPolicies();
	}

	@GetMapping("/policy/{policyId}")
	public Policy getPolicy(@PathVariable Long policyId) {

		return policyService.getPolicy(policyId);
	}

	@PostMapping
	public Policy createPolicy(@RequestBody Policy policy) {
		return policyService.createPolicy(policy);
	}
}
