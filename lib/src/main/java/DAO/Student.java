package DAO;

public class Student {
    int id;
    String name;
    String address;
    public String getName() {
        return name;
    }
    public void setName(String st){
        this.name = st;
    }
    public String geAaddress() {
        return address;
    }
    public void setaddress(String x) {
        address = x;
    }
    public void display(){
        System.out.println(this.id + " : " + this.name + " : " + this.address);
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
}

