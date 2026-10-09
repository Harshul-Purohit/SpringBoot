package org.example.onetomany;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

    @Id
    private int rollNo;

    @Column(nullable = false)
    private String sName;

    @Column(nullable = false)
    private int sAge;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    // Getters and setters
    public int getRollNo() { return rollNo; }
    public void setRollNo(int rollNo) { this.rollNo = rollNo; }

    public String getsName() { return sName; }
    public void setsName(String sName) { this.sName = sName; }

    public int getsAge() { return sAge; }
    public void setsAge(int sAge) { this.sAge = sAge; }

    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }

    @Override
    public String toString() {
        return "Student{" +
                "rollNo=" + rollNo +
                ", sName='" + sName + '\'' +
                ", sAge=" + sAge +
                ", books=" + books +
                '}';
    }
}
