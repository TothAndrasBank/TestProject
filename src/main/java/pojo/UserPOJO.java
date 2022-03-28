package pojo;


public class UserPOJO {

    private int id;
    private String name;
    private String username;
    private String email;
    private AddressPOJO address;
    private String phone;
    private String website;
    private CompanyPOJO company;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public AddressPOJO getAddress() {
        return address;
    }

    public void setAddress(final AddressPOJO address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(final String website) {
        this.website = website;
    }

    public CompanyPOJO getCompany() {
        return company;
    }

    public void setCompany(final CompanyPOJO company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "UserPOJO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", phone='" + phone + '\'' +
                ", website='" + website + '\'' +
                ", company=" + company +
                '}';
    }
}

