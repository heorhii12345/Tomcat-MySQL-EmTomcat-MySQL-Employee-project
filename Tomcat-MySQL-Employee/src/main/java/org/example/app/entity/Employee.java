package org.example.app.entity;

public class Employee {

    private Long id;
    private final String nameofemployee;
    private final String position;
    private final double phone;

    public Employee(String nameofemployee, String position, double phone) {
        this.nameofemployee = nameofemployee;
        this.position = position;
        this.phone = phone;
    }

    public Employee(Long id, String nameofemployee, String position, double phone) {
        this.id = id;
        this.nameofemployee = nameofemployee;
        this.position = position;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public String getNameofemployee() {
        return nameofemployee;
    }

    public String getPosition() {
        return position;
    }

    public double getPhone() {
        return phone;
    }
}
