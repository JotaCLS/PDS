public class Place {
    
    private final String name;
    private final String address;
    private final String city;
    private final String country;
    
    public Place(final String name, final String address, final String city, final String country) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
    }
    
    public String getName() {
        return name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getCity() {
        return city;
    }
    
    public String getCountry() {
        return country;
    }
    
    public String toString() {
        return name + " (" + address + ", " + city + ", " + country + ")";
    }
}
