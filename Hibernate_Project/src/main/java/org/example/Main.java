package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {

        // Load Hibernate configuration (hibernate.cfg.xml)
        Configuration cfg = new Configuration().configure();

        // Build SessionFactory (heavy object, usually created once per app)
        SessionFactory sf = cfg.buildSessionFactory();

        // ---------------- CREATE ----------------
        Session createSession = sf.openSession();
        createSession.beginTransaction();

        Address addr = new Address();
        addr.setStreet("vigyan nagar");
        addr.setCity("Kota");
        addr.setZip("324005");

        Student s1 = new Student();
        s1.setsName("Dwivedi");   // set student name
        s1.setRollNo(2699);       // set roll number (primary key)
        s1.setsAge(19);// set student age
        s1.setAddress(addr);

        createSession.merge(s1); // insert new student
        createSession.getTransaction().commit(); // commit changes
        createSession.close();

        System.out.println("Inserted: " + s1);

        // ---------------- READ ----------------
        Session readSession = sf.openSession();
        readSession.beginTransaction();

        // Fetch student by primary key (rollNo = 2699)
        Student fetched = readSession.find(Student.class, 2699);
        System.out.println("Fetched: " + fetched);

        readSession.getTransaction().commit();
        readSession.close();

        // ---------------- UPDATE ----------------
        Session updateSession = sf.openSession();
        updateSession.beginTransaction();

        // Fetch existing student
        Student toUpdate = updateSession.find(Student.class, 2699);
        toUpdate.setsAge(20); // change age
        toUpdate.setsName("Soumya Dwivedi"); // change name
        toUpdate.getAddress().setStreet("Talwandi"); // ✅ update embedded field

        updateSession.merge(toUpdate); // update record
        updateSession.getTransaction().commit();
        updateSession.close();

        System.out.println("Updated: " + toUpdate);

        // ---------------- DELETE ----------------
        Session deleteSession = sf.openSession();
        deleteSession.beginTransaction();

        // Fetch student to delete
        Student toDelete = deleteSession.find(Student.class, 2699);

        if (toDelete != null) {
            deleteSession.remove(toDelete); // delete record
            System.out.println("Deleted: " + toDelete);
        }

        deleteSession.getTransaction().commit();
        deleteSession.close();

        // Close SessionFactory (cleanup resources)
        sf.close();
    }
}
