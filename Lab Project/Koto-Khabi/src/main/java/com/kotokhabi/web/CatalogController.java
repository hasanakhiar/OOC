package com.kotokhabi.web;

import com.kotokhabi.storage.StorageRepository;
import io.javalin.http.Context;
import java.util.Map;

public class CatalogController {
    private final StorageRepository repository;

    public CatalogController(StorageRepository repository) {
        this.repository = repository;
    }

    public void listAll(Context ctx) {
        var restaurants = repository.getAllRestaurants();
        ctx.render("templates/index.html", Map.of("restaurants", restaurants));
    }

    public void detail(Context ctx) {
        String id = ctx.pathParam("id");
        var restaurant = repository.getRestaurantById(id);
        
        if (restaurant == null) {
            ctx.status(404).result("Restaurant not found");
            return;
        }

        ctx.render("templates/restaurant-detail.html", Map.of("restaurant", restaurant));
    }
}
