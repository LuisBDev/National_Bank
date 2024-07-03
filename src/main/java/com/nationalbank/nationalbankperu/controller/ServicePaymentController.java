package com.nationalbank.nationalbankperu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nationalbank.nationalbankperu.model.ServicePayment;
import com.nationalbank.nationalbankperu.service.IServicePaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/service-payment")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ServicePaymentController {

    @Autowired
    private IServicePaymentService servicePaymentService;

    @GetMapping("/servicesDisponibles")
    public ResponseEntity<List<Map<String, Object>>> getServices() throws IOException {
        ClassPathResource jsonResource = new ClassPathResource("services.json");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Map<String, Object>> services = objectMapper.readValue(jsonResource.getInputStream(), List.class);
        return ResponseEntity.ok(services);
    }

    @PostMapping("/payService/{userId}")
    public ResponseEntity payService(@PathVariable Long userId, @RequestBody ServicePayment servicePayment) {
        servicePaymentService.payService(userId, servicePayment);
        return ResponseEntity.ok("Service Payment guardado con éxito");
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(servicePaymentService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicePayment> findById(@PathVariable Long id) {
        ServicePayment pago = servicePaymentService.findById(id);
        if (pago == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pago);
    }

    //    update by id
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ServicePayment servicePayment) {
        try {
            ServicePayment pago = servicePaymentService.findById(id);
            if (pago == null) {
                return ResponseEntity.badRequest().body("Error: Service Payment no encontrado!");
            }
            return ResponseEntity.ok("Service Payment actualizado con éxito");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            servicePaymentService.deleteById(id);
            return ResponseEntity.ok("Service Payment eliminado con éxito");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
