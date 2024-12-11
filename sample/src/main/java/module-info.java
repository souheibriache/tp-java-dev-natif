module org.openjfx {
    requires javafx.controls;
    requires java.desktop;
    requires org.json;
    requires org.locationtech.jts;
    requires org.geotools.main;
    requires webcam.capture;

    exports org.openjfx;
    exports org.openjfx.models;
    exports org.openjfx.controller;
    exports org.openjfx.view;
}