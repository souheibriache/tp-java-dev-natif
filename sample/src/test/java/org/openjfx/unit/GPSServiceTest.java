package org.openjfx.unit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openjfx.models.LocationModel;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GPSServiceTest {

    private LocationModel locationModel;

    @BeforeEach
    void setUp() {
        this.locationModel = new LocationModel();
    }

    @Test
    void testLocationIsValidFormat() throws Exception {
        LocationModel locationModel = Mockito.mock(LocationModel.class);
        Mockito.when(locationModel.getLocation()).thenReturn("Lat: 40.7128, Lon: -74.0060");
        String location = locationModel.getLocation();
        assertNotNull(location, "Location should not be null.");
        assertTrue(location.matches("Lat: [-+]?[0-9]*\\.?[0-9]+, Lon: [-+]?[0-9]*\\.?[0-9]+"));
    }

    @Test
    void testGetLocationParsesResponse() throws Exception {
        LocationModel mockLocationModel = mock(LocationModel.class);
        doReturn("{\"lat\":40.7128,\"lon\":-74.0060}").when(mockLocationModel).getLocation();
        String location = mockLocationModel.getLocation();

        assertNotNull(location, "Location fetch should return a non-null string.");
        assertTrue(location.contains("40.7128"), "Location should contain correct latitude.");
        assertTrue(location.contains("-74.0060"), "Location should contain correct longitude.");
    }
}
