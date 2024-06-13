package com.nhs.individual.Controller;

import com.nhs.individual.Domain.PaymentMethod;
import com.nhs.individual.Domain.UserPayment;
import com.nhs.individual.Service.PaymentService;
import com.nhs.individual.Service.UserPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(value = "/api/v1/payment")
@AllArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final UserPaymentService userPaymentService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<PaymentMethod> findAllSupportedPaymentsMethods(){
        return paymentService.findAll();
    }
    @PreAuthorize("#id==authentication.principal.userId or hasRole('ADMIN')")
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public Collection<UserPayment> findAllUserPayments(@PathVariable Integer id){
        return userPaymentService.findAllByUserId(id);
    }

}
