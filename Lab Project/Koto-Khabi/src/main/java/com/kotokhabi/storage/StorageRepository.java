package com.kotokhabi.storage;

import java.util.List;

import com.kotokhabi.domain.Order;
import com.kotokhabi.domain.Restaurant;
import com.kotokhabi.domain.User;

public interface StorageRepository {
    // Restaurant Operations
    void saveRestaurant(Restaurant restaurant);

    List<Restaurant> getAllRestaurants();

    Restaurant getRestaurantById(String id);

    // User Operations
    void saveUser(User user);

    User getUserById(String id);

    // Order Operations
    void saveOrder(Order order);

    List<Order> getOrdersByUserId(String userId);
}
