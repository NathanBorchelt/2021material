
public class Snack {
    
    private int id;
    private String name;
    private int quantity;
    private double cost;
    private String sURL;

    public Snack(int id, String name,int quantity, double cost, String sURL){
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
        this.sURL = sURL;
    }
    public void setID(int id){
        this.id = id;
    }       

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setsURL(String sURL) {
        this.sURL = sURL;
    }

    public double getCost() {
        return cost;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public String getsURL() {
        return sURL;
    }
}
