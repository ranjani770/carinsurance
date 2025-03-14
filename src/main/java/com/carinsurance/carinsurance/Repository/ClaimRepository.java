package com.carinsurance.carinsurance.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carinsurance.carinsurance.model.Claim;

@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {
}
