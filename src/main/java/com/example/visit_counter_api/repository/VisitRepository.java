package com.example.visit_counter_api.repository;


import com.example.visit_counter_api.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    Optional<Visit> findByIp(String ip);


}
