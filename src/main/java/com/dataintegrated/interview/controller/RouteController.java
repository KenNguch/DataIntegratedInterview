package com.dataintegrated.interview.controller;

import com.dataintegrated.interview.entity.Route;
import com.dataintegrated.interview.models.ResponseModel;
import com.dataintegrated.interview.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.NonNull;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(path = "${api_prefix}/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;


    /**
     * Get all routes
     *
     * @return List of routes
     */
    @GetMapping({"", "/"})
    public ResponseModel<List<Route>> getCategories() {
        return new ResponseModel<>(routeService.fetchAllRoutes());
    }


    /**
     * Get route by routeId
     *
     * @param routeId routeId
     * @return Route object with routeId
     */
    @GetMapping({"/{id}", "/{id}/"})
    public ResponseModel<Route> getRoute(@NonNull @PathVariable("id") BigInteger routeId) {
        return new ResponseModel<>(routeService.fetchByRouteId(routeId));
    }


    /**
     * Post route
     *
     * @param route Route object
     * @return Route object
     */
    @PostMapping({"", "/"})
    public ResponseModel<String> postRoute(@RequestBody Route route) {
        routeService.postRoute(route);

        return new ResponseModel<>("Route added successfully");
    }

    /**
     * Delete route by routeId
     *
     * @param routeId routeId
     * @return Route object with routeId
     */

    @DeleteMapping({"/{id}", "/{id}/"})
    public ResponseModel<String> deleteRoute(@NonNull @PathVariable("id") BigInteger routeId) {
        routeService.delete(routeId);
        return new ResponseModel<>("Route deleted successfully");
    }

    /**
     * Update route by routeId
     *
     * @param route   Route object
     * @param routeId routeId
     * @return Route object with routeId
     */
    @PutMapping({"/{id}", "/{id}/"})
    public ResponseModel<String> updateRoute(@RequestBody Route route, @NonNull @PathVariable("id") BigInteger routeId) {
        routeService.update(route, routeId);
        return new ResponseModel<>("Route updated successfully");
    }
}
