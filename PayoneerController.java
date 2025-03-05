package com.example.payoneer.controller;

import com.example.payoneer.service.PayoneerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payoneer")
@CrossOrigin(origins = "*")  // Allow Vue.js frontend to access this API
public class PayoneerController {

    private final PayoneerService payoneerService;

    public PayoneerController(PayoneerService payoneerService) {
        this.payoneerService = payoneerService;
    }

    @PostMapping("/pay")
    public ResponseEntity<String> createPayment(@RequestParam String payeeId, @RequestParam Double amount) {
        String response = payoneerService.createPayment(payeeId, amount);
        return ResponseEntity.ok(response);
    }
}
