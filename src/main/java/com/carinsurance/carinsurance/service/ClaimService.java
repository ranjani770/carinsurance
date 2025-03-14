package com.carinsurance.carinsurance.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.carinsurance.carinsurance.Repository.ClaimRepository;
import com.carinsurance.carinsurance.Repository.PolicyRepository;
import com.carinsurance.carinsurance.model.Claim;
import com.carinsurance.carinsurance.model.ClaimStatus;
import com.carinsurance.carinsurance.model.Policy;

@Service
public class ClaimService {

	private final ClaimRepository claimRepository;
	private final PolicyRepository policyRepository;

	public ClaimService(ClaimRepository claimRepository, PolicyRepository policyRepository) {
		this.claimRepository = claimRepository;
		this.policyRepository = policyRepository;
	}

	public List<Claim> getAllClaims() {
		return claimRepository.findAll();
	}

	public Claim fileClaim(Claim claim, Long policyId) {
		Policy policy = policyRepository.findById(policyId)
				.orElseThrow(() -> new RuntimeException("Policy not found"));

		if (policy.getEndDate().isBefore(LocalDate.now())) {
			throw new RuntimeException("Policy is expired. Claim cannot be processed.");
		}

		claim.setPolicy(policy);
		claim.setClaimNumber("CLM-" + System.currentTimeMillis());
		claim.setStatus(ClaimStatus.PENDING);
		claim.setDateOfClaim(LocalDate.now());

		return claimRepository.save(claim);
	}

	public Claim processClaim(Long claimId, ClaimStatus status) {
		Claim claim = claimRepository.findById(claimId)
				.orElseThrow(() -> new RuntimeException("Claim not found"));

		claim.setStatus(status);
		return claimRepository.save(claim);
	}
}
