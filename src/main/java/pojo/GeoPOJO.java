package pojo;


public class GeoPOJO {
    private double lat;
    private double lng;

    public double getLat() {
        return lat;
    }

    public void setLat(final double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(final double lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "GeoPOJO{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}

