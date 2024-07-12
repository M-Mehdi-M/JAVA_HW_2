package org.example;

import java.util.*;

public class Curs<T extends Student> {
    private String nume;
    private int capacitateMaxima;
    private List<Student> studentiInscrisi;

    public Curs(String nume, int capacitateMaxima) {
        this.nume = nume;
        this.capacitateMaxima = capacitateMaxima;
        this.studentiInscrisi = new ArrayList<>();
    }

    public String getNume() {
        return nume;
    }

    public int getCapacitateMaxima() {
        return capacitateMaxima;
    }

    public List<Student> getStudentiInscrisi() {
        return studentiInscrisi;
    }

    public void adaugaStudent(Student student) {
        studentiInscrisi.add(student);
    }
}
