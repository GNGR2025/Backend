package com.gngr.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gngr.backend.entities.OtpRequests;

@Repository
public interface OtpRepository extends JpaRepository<OtpRequests,Long> {

    // OtpRequests findTopByPhoneNumberOrderByCreatedAtDesc(String phoneNumber);
    

}
