package com.example.visit_counter_api.controller;


import com.example.visit_counter_api.service.VisitService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/visits")
@CrossOrigin(origins = "*")
public class VisitController {


    private final VisitService visitService;

    public VisitController(VisitService visitService){
        this.visitService = visitService;
    }

    @GetMapping
    public long registerVisit(HttpServletRequest request){
        String clientIp = request.getRemoteAddr();

        return visitService.registerVisit(clientIp);
    }

}
