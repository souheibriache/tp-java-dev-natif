package org.openjfx.models;

import org.geotools.geometry.jts.JTSFactoryFinder;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Point;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;

public class LocationModel {
    public String getLocation() throws Exception {
        URL ipApi = new URL("http://ip-api.com/json/");
        BufferedReader reader = new BufferedReader(new InputStreamReader(ipApi.openStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JSONObject json = new JSONObject(response.toString());
        return String.format("Lat: %.4f, Lon: %.4f",
                json.getDouble("lat"),
                json.getDouble("lon"));
    }
}