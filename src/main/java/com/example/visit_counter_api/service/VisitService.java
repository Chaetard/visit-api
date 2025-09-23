package com.example.visit_counter_api.service;


import com.example.visit_counter_api.entity.Visit;
import com.example.visit_counter_api.repository.VisitRepository;
import org.springframework.stereotype.Service;

@Service
public class VisitService {

    private final VisitRepository repository;

    public VisitService(VisitRepository visitRepository){
        this.repository = visitRepository;
    }


    public long registerVisit(String ip){
        if (repository.findByIp(ip).isEmpty()){
            repository.save(new Visit(ip));
        }
        return repository.count();
    }


}
