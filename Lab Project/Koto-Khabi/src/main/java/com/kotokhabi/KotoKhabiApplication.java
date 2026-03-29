package com.kotokhabi;

import com.kotokhabi.domain.Restaurant;
import com.kotokhabi.storage.StorageRepository;
import com.kotokhabi.storage.XmlRepository;

import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinThymeleaf;

public class KotoKhabiApplication {
    public static void main(String[] args) {
        StorageRepository repository = new XmlRepository();

        // Initialize some sample data if the repository is empty
        if (repository.getAllRestaurants().isEmpty()) {
            Restaurant res1 = new Restaurant("1", "Koto Khabi Kitchen", "12 Dhanmondi Lake Rd", "9:00 AM - 10:00 PM");
            res1.getMenu().add(new com.kotokhabi.domain.MenuItem("m1", "Kacchi Biryani", 5.99,
                    "Traditional kacchi biryani with aromatic rice"));
            res1.getMenu()
                    .add(new com.kotokhabi.domain.MenuItem("m2", "Beef Tehari", 4.49, "Spiced beef tehari served hot"));
            repository.saveRestaurant(res1);

            Restaurant res2 = new Restaurant("2", "Bengal Bites", "45 Gulshan Avenue", "10:00 AM - 11:00 PM");
            res2.getMenu().add(new com.kotokhabi.domain.MenuItem("m3", "Chicken Rezala Platter", 12.99,
                    "Rezala chicken platter with naan"));
            res2.getMenu().add(
                    new com.kotokhabi.domain.MenuItem("m4", "Fuchka Combo", 5.50, "Crispy fuchka set with chutney"));
            repository.saveRestaurant(res2);
        } else {
            // Ensure restaurants have menus if they were created empty previously
            for (Restaurant res : repository.getAllRestaurants()) {
                if (res.getMenu().isEmpty()) {
                    if (res.getName().equals("Koto Khabi Kitchen")) {
                        res.getMenu().add(new com.kotokhabi.domain.MenuItem("m1", "Kacchi Biryani", 5.99,
                                "Traditional kacchi biryani with aromatic rice"));
                        res.getMenu().add(new com.kotokhabi.domain.MenuItem("m2", "Beef Tehari", 4.49,
                                "Spiced beef tehari served hot"));
                    } else if (res.getName().equals("Bengal Bites")) {
                        res.getMenu().add(new com.kotokhabi.domain.MenuItem("m3", "Chicken Rezala Platter", 12.99,
                                "Rezala chicken platter with naan"));
                        res.getMenu().add(new com.kotokhabi.domain.MenuItem("m4", "Fuchka Combo", 5.50,
                                "Crispy fuchka set with chutney"));
                    }
                    repository.saveRestaurant(res);
                }
            }
        }

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/static");
            config.fileRenderer(new JavalinThymeleaf());
        }).start(7070);

        // Controllers
        var restaurantController = new com.kotokhabi.web.CatalogController(repository);
        var cartController = new com.kotokhabi.web.CartFlowController(repository);
        var userController = new com.kotokhabi.web.AuthController();
        var adminController = new com.kotokhabi.web.BackofficeController(repository);

        // Home Page Route
        app.get("/", restaurantController::listAll);

        // Restaurant Detail Route
        app.get("/restaurant/{id}", restaurantController::detail);

        // Cart Routes
        app.post("/cart/add", cartController::addToCart);
        app.get("/cart", cartController::viewCart);

        // User & Checkout Routes
        app.get("/login", userController::showLogin);
        app.post("/login", userController::login);
        app.post("/checkout", cartController::placeOrder);

        // Admin Routes
        app.get("/admin", adminController::dashboard);
        app.post("/admin/restaurant/add", adminController::addRestaurant);
        app.post("/admin/menu/add", adminController::addMenuItem);

        // SOAP / WSDL API Requirement
        com.kotokhabi.api.KotoKhabiSoapApi.setRepository(repository);
        jakarta.xml.ws.Endpoint.publish("http://localhost:8081/services/restaurant",
                new com.kotokhabi.api.KotoKhabiSoapApi());

        System.out.println("Server started at http://localhost:7070");
        System.out.println("WSDL API available at http://localhost:8081/services/restaurant?wsdl");
    }
}
