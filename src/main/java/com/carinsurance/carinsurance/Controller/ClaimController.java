package com.carinsurance.carinsurance.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carinsurance.carinsurance.model.Claim;
import com.carinsurance.carinsurance.model.ClaimStatus;
import com.carinsurance.carinsurance.service.ClaimService;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

	private final ClaimService claimService;

	public ClaimController(ClaimService claimService) {
		this.claimService = claimService;
	}

	@GetMapping
	public List<Claim> getAllClaims() {
		return claimService.getAllClaims();
	}

	@PostMapping("/file/{policyId}")
	public Claim fileClaim(@RequestBody Claim claim, @PathVariable Long policyId) {
		return claimService.fileClaim(claim, policyId);
	}

	@PutMapping("/process/{claimId}")
	public Claim processClaim(@PathVariable Long claimId, @RequestParam ClaimStatus status) {
		return claimService.processClaim(claimId, status);
	}
}
