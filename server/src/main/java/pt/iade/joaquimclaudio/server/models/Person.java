package pt.iade.joaquimclaudio.server.models;

public class Person {
    private static int next_id = 1;
    private int id;
    private String name;
    private String email;
    private float height;

    public Person(){
    }

    public Person(String name, String email, float height){
        this.name = name;
        this.email = email;
        this.height = height;
        this.id = next_id;
        next_id++;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
