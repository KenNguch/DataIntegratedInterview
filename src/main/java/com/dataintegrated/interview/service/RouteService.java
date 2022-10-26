package com.dataintegrated.interview.service;


import com.dataintegrated.interview.entity.Route;
import com.dataintegrated.interview.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class RouteService {

    public static final String KEY = "CATEGORIES";

    @Autowired
    private RouteRepository routeRepository;

    @Cacheable(value = KEY, key = "#routeId", unless = "#result == null")
    public Route fetchByRouteId( BigInteger routeId) {
        Route route = routeRepository.findByRouteId(routeId);

        if (route == null)
            throw new RuntimeException("Unexpected result!");

        return route;
    }

    @Cacheable(value = KEY, unless = "#result == null")
    public List<Route> fetchAllCategories() {
        return routeRepository.findAll();
    }
}
