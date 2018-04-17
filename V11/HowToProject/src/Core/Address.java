package Core;





/**
 *
 * @author akoubaa
 */
public class Address implements Comparable<Address>{

    @Override
    public int compareTo(Address o) {
        int x = -1;
        if(this.City.equalsIgnoreCase(o.City)){
            if(this.Country.equalsIgnoreCase(Country)){
                if(this.getStreet().getName().equalsIgnoreCase(o.getStreet().getName())){
                    x = 0;
                }
            } 
        } 
        return x;
    }
    private Street street;
    private String City;
    private String Country;
    
    public Address (Street sn, String city, String country){
        street=sn;
        City=city;
        Country=country;
    }
    
    public void SetAddress (Street sn, String city, String country){
        street=sn;
        City=city;
        Country=country;
    }
    
    public Street getStreet(){
        return street;
    }
    
    public String getCity(){
        return City;
    }
    
    public String getCountry(){
        return Country;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", this.street, this.City, this.Country);
    }

}
