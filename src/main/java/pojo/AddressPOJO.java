package pojo;


public class AddressPOJO {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private GeoPOJO geo;

    public String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(final String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(final String zipcode) {
        this.zipcode = zipcode;
    }

    public GeoPOJO getGeo() {
        return geo;
    }

    public void setGeo(final GeoPOJO geo) {
        this.geo = geo;
    }

    @Override
    public String toString() {
        return "AddressPOJO{" +
                "street='" + street + '\'' +
                ", suite='" + suite + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", geo=" + geo +
                '}';
    }
}
