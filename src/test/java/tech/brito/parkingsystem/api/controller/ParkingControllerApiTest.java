package tech.brito.parkingsystem.api.controller;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import tech.brito.parkingsystem.dto.CheckInRequest;
import tech.brito.parkingsystem.dto.CheckOutRequest;
import tech.brito.parkingsystem.service.ParkingService;
import tech.brito.parkingsystem.util.DatabaseCleaner;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.port;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static tech.brito.parkingsystem.core.Constants.ONE_HOUR_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
class ParkingControllerApiTest {

    private static String LICENSE_FIRST_VEHICLE = "KAOS-9555";

    @LocalServerPort
    private int randomPort;

    private UUID vehicleId;

    @Autowired
    private DatabaseCleaner databaseCleaner;
    @Autowired
    private ParkingService parkingService;

    @BeforeEach
    public void setUpTest() {
        port = randomPort;
        databaseCleaner.clearTables();
        configureData();
    }

    private void configureData() {
        var bmw = createCheckInRequest(LICENSE_FIRST_VEHICLE).toEntity();
        vehicleId = parkingService.checkIn(bmw).getId();
    }

    private CheckInRequest createCheckInRequest(String licence) {
        var checkInRequest = new CheckInRequest();
        checkInRequest.setColor("BLACK");
        checkInRequest.setModel("BMW");
        checkInRequest.setLicense(licence);
        return checkInRequest;
    }


    @Test
    public void whenParkedVehiclesCheckResult() {
        given().when().get("/parking/vehicles").then().statusCode(HttpStatus.OK.value()).body("", hasSize(1));
    }

    @Test
    public void whenCheckInCheckResult() {

        given()
                .contentType(ContentType.JSON)
                .body(createCheckInRequest("KAOS-3033"))
                .when()
                .post("/parking/checkin")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    public void whenGetVehicleCheckResult() {
        given()
                .pathParam("id", vehicleId)
                .when()
                .get("/parking/vehicles/{id}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("license", equalTo(LICENSE_FIRST_VEHICLE));
    }

    @Test
    public void whenCheckOutCheckResult() {

        var checkOutRequest = new CheckOutRequest(vehicleId);

        given()
                .contentType(ContentType.JSON)
                .body(checkOutRequest)
                .when()
                .post("/parking/checkout")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("billTotal", equalTo(5));
    }

}