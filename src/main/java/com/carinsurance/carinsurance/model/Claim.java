package com.carinsurance.carinsurance.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Claim {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String claimNumber;
	private String description;
	private Double claimAmount;
	private LocalDate dateOfClaim;

	@Enumerated(EnumType.STRING)
	private ClaimStatus status;

	@ManyToOne
	private Policy policy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getClaimAmount() {
		return claimAmount;
	}

	public void setClaimAmount(Double claimAmount) {
		this.claimAmount = claimAmount;
	}

	public LocalDate getDateOfClaim() {
		return dateOfClaim;
	}

	public void setDateOfClaim(LocalDate dateOfClaim) {
		this.dateOfClaim = dateOfClaim;
	}

	public ClaimStatus getStatus() {
		return status;
	}

	public void setStatus(ClaimStatus status) {
		this.status = status;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
}
