package com.dataintegrated.interview.service;


import com.dataintegrated.interview.entity.Route;
import com.dataintegrated.interview.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {

    public static final String KEY = "Routes";

    private final RouteRepository routeRepository;

    @Cacheable(value = KEY, key = "#routeId", unless = "#result == null")
    public Route fetchByRouteId(BigInteger routeId) {
        Route route = routeRepository.findByRouteId(routeId);

        if (route == null)
            throw new RuntimeException("Unexpected result!");

        return route;
    }

    @Cacheable(value = KEY, unless = "#result == null")
    public List<Route> fetchAllRoutes() {
        return routeRepository.findAll();
    }

    @Async
    public void postRoute(Route route) {
        routeRepository.saveAndFlush(route);
    }

    @Async
    @CachePut(value = KEY, key = "#routeId")
    public void delete(BigInteger routeId) {
        routeRepository.deleteById(routeId);
    }

    @Async
    @CacheEvict(value = KEY, key = "#routeId")
    public void update(Route books, BigInteger routeId) {
        routeRepository.save(books);
    }

}
