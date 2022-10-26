package com.dataintegrated.interview.controller;

import com.dataintegrated.interview.entity.Route;
import com.dataintegrated.interview.models.ResponseModel;
import com.dataintegrated.interview.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.NonNull;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(path = "${api_prefix}/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    @GetMapping({"", "/"})
    public ResponseModel<List<Route>> getCategories() {
        return new ResponseModel<>(routeService.fetchAllCategories());
    }

    @GetMapping({"/{id}", "/{id}/"})
    public ResponseModel<Route> getRoute(@NonNull @PathVariable("id") BigInteger routeId) {
        return new ResponseModel<>(routeService.fetchByRouteId(routeId));
    }
}
