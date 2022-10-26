package com.dataintegrated.interview.repository;

import com.dataintegrated.interview.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface RouteRepository  extends JpaRepository<Route, BigInteger> {
    Route findByRouteId(BigInteger routeId);
}
