package org.example.onetomany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        Configuration cfg = new Configuration()
                .configure()
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Book.class);

        SessionFactory sf = cfg.buildSessionFactory();

        // ---------------- CREATE ----------------
        Session session = sf.openSession();
        session.beginTransaction();

        Book b1 = new Book();
        b1.setBookId(201);
        b1.setTitle("Java Basics");

        Book b2 = new Book();
        b2.setBookId(202);
        b2.setTitle("Hibernate Guide");

        Student student = new Student();
        student.setRollNo(2);
        student.setsName("Harshul");
        student.setsAge(20);
        student.getBooks().add(b1);
        student.getBooks().add(b2);

        session.persist(student); // cascade saves books too

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
