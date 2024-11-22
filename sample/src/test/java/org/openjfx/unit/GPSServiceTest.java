package org.openjfx.unit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openjfx.models.LocationModel;

public class GPSServiceTest {

    private LocationModel locationModel;

    @BeforeEach
    void setUp() {
        this.locationModel = new LocationModel();
    }

    @Test
    void testGetCoordinatesReturnsNonNullValue() {
        try {
            String coordinates = this.locationModel.getLocation();
            assertNotNull(coordinates, "Coordinates should not be null.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception occurred while fetching the coordinates: " + e.getMessage());
        }
    }
}
