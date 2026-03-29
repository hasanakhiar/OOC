# Koto Khabi

Koto Khabi is a Java 21 food delivery web application with XML persistence, a modular package structure, and SOAP integration.

## Features

- Browse restaurants and menus
- Add items to cart and place orders
- Backoffice dashboard for restaurant and menu management
- SOAP/WSDL endpoint for restaurant queries
- Persistent storage using XML files

## Stack

- Java 21
- Maven
- Javalin
- Thymeleaf
- JAXB
- Jakarta XML WS

## Project Structure

```text
src/main/java/com/kotokhabi/
├── KotoKhabiApplication.java
├── web/       # HTTP controllers (catalog, cart flow, auth, backoffice)
├── domain/    # Core entities
├── storage/   # Storage abstraction + XML implementation
└── api/       # SOAP API
```

## Data Location

- Runtime data folder: `storage-data/`
- Sample restaurant files:
  - `storage-data/koto_khabi_restaurant_1.xml`
  - `storage-data/koto_khabi_restaurant_2.xml`

## Currency

All UI currency labels are shown in `BDT`.

## Run (CLI)

From project root:

```bash
mvn compile exec:java -Dexec.mainClass="com.kotokhabi.KotoKhabiApplication"
```

Access points:

- App: http://localhost:7070
- Backoffice: http://localhost:7070/admin
- WSDL: http://localhost:8081/services/restaurant?wsdl

## Run In IntelliJ IDEA

1. Open the project folder.
2. Import/reload Maven project.
3. Set Project SDK and Module SDK to Java 21.
4. Create an `Application` run configuration:
   - Main class: `com.kotokhabi.KotoKhabiApplication`
   - Working directory: project root
5. Run and open the URLs listed above.

## Notes

- Maven is required for dependencies and execution.
- Ensure ports `7070` and `8081` are available.
- If `storage-data/` has no restaurants, startup seeds sample data automatically.
