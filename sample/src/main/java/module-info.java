module org.openjfx {
    requires javafx.controls;
    requires java.desktop;
    requires org.json;
    requires org.locationtech.jts;
    requires org.geotools.main;
    exports org.openjfx;
    requires webcam.capture;

}