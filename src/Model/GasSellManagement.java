package Model;

import java.io.Serializable;
import java.util.Date;

public class GasSellManagement implements Serializable {
    private String ID;
    private String GasType;
    private String Date;
    private String Name;
    private Double Price;
    private Double totalRevenue;

    public GasSellManagement(String GasType, String ID, Double Price, String Date, Double totalRevenue, String Name){
        this.GasType = GasType;
        this.ID = ID;
        this.Date = Date;
        this.Name = Name;
        this.Price = Price;
        this.totalRevenue = totalRevenue;
    }

    public String getGasType() {
        return GasType;
    }

    public void setGasType(String gasType) {
        GasType = gasType;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public Double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(Double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

}
