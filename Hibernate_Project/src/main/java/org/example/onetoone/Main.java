package org.example.onetoone;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        Configuration cfg = new Configuration().configure();
        SessionFactory sf = cfg.buildSessionFactory();

        // ---------------- CREATE ----------------
        Session session = sf.openSession();
        session.beginTransaction();

        Laptop laptop = new Laptop();
        laptop.setLaptopId(101);
        laptop.setBrand("Dell");
        laptop.setModel("Inspiron 15");

        Student student = new Student();
        student.setRollNo(1);
        student.setsName("Harshul");
        student.setsAge(20);
        student.setLaptop(laptop); // link student with laptop

        session.persist(laptop);   // save laptop first
        session.persist(student);  // then save student

        session.getTransaction().commit();
        session.close();

        System.out.println("Inserted: " + student);

        // ---------------- READ ----------------
        Session readSession = sf.openSession();
        readSession.beginTransaction();

        Student fetched = readSession.find(Student.class, 1);
        System.out.println("Fetched: " + fetched);

        readSession.getTransaction().commit();
        readSession.close();

        sf.close();
    }
}
