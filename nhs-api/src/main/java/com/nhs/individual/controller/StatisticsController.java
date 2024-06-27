package com.nhs.individual.controller;

import com.nhs.individual.service.sub_service.AccountStatisticService;
import com.nhs.individual.service.sub_service.OrderPerdayService;
import com.nhs.individual.service.sub_service.ProspectiveUserService;
import com.nhs.individual.views.Accountstatisticsview;
import com.nhs.individual.views.OrderPerDay;
import com.nhs.individual.views.Prospectiveuser;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/statistic")
@AllArgsConstructor
@Secured("ADMIN")
public class StatisticsController {
    OrderPerdayService orderPerdayService;
    AccountStatisticService accountStatisticService;
    ProspectiveUserService prospectiveUserService;
    @GetMapping("/day/order")
    public List<OrderPerDay> getOrderStatisticPerDay(){
        return orderPerdayService.findAdd();
    }
    @GetMapping("/user")
    public Accountstatisticsview accountstatisticsview(){
        return accountStatisticService.findAll();
    }
    @GetMapping("/user/prospective")
    public List<Prospectiveuser> prospectiveuser(){
        return prospectiveUserService.findAll();
    }


}
