package org.example.onetoone;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class Student {

    @Id
    private int rollNo;
    private String sName;
    private int sAge;

    @OneToOne
    @JoinColumn(name = "laptop_id") // foreign key column in Student table
    private Laptop laptop;

    // getters and setters
    public int getRollNo() { return rollNo; }
    public void setRollNo(int rollNo) { this.rollNo = rollNo; }

    public String getsName() { return sName; }
    public void setsName(String sName) { this.sName = sName; }

    public int getsAge() { return sAge; }
    public void setsAge(int sAge) { this.sAge = sAge; }

    public Laptop getLaptop() { return laptop; }
    public void setLaptop(Laptop laptop) { this.laptop = laptop; }

    @Override
    public String toString() {
        return "Student{" +
                "rollNo=" + rollNo +
                ", sName='" + sName + '\'' +
                ", sAge=" + sAge +
                ", laptop=" + laptop +
                '}';
    }
}
