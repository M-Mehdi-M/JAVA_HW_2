package org.example;

import java.io.*;
import java.util.*;
import java.nio.file.*;

public class Secretariat {
    private List<Student> studenti;
    private List<Curs<?>> cursuri;

    public Secretariat() {
        this.studenti = new ArrayList<>();
        this.cursuri = new ArrayList<>();
    }

    public void writeOutFile(String file, String content) { // writes content in and makes .out file
        try {
            FileWriter myWriter = new FileWriter("src/main/resources/" + file + "/" + file + ".out", true);
            myWriter.write("***\n" + content + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void adaugaStudent(String nume, String studentTip) throws StudentExistsException { // adding studnet to student list
        if (checkIfStudentExists(nume)) {
            throw new StudentExistsException("Student duplicat: " + nume);
        }
        Student student = new Student(nume, studentTip);
        studenti.add(student);
    }

    public String readNoteFilesInFolder(String folderPath) { // reading note files in the folderPath
        StringBuilder result = new StringBuilder();
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();
        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.getName().contains("note_")) {
                    try {
                        result.append(Files.readString(file.toPath()));
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        return result.toString();
    }

    public String sortLinesByGradeAndName(String notes) { // sorting notes String after note and student name
        String[] noteLines = notes.split("\n");
        Arrays.sort(noteLines, (line1, line2) -> {
            double grade1 = Double.parseDouble(line1.substring(line1.indexOf(" - ") + 3));
            double grade2 = Double.parseDouble(line2.substring(line2.indexOf(" - ") + 3));
            int gradeComparison = Double.compare(grade2, grade1);
            if (gradeComparison != 0) {
                return gradeComparison;
            } else {
                String name1 = line1.substring(0, line1.indexOf(" - "));
                String name2 = line2.substring(0, line2.indexOf(" - "));
                return name1.compareTo(name2);
            }
        });
        return String.join("\n", noteLines);
    }

    private boolean checkIfStudentExists(String nume) { // checking if a student already exists
        for (Student student : studenti) {
            if (student.getNume().equals(nume)) {
                return true;
            }
        }
        return false;
    }

    public String updateStudentMedie(String notes, String contestation) { // sets new medie for student
        String[] noteLines = notes.split("\n");
        String[] contestationInfo = contestation.split(" - ");
        for (int i = 0; i < noteLines.length; i++) {
            String[] noteInfo = noteLines[i].split(" - ");
            if (noteInfo[0].equals(contestationInfo[1])) {
                noteLines[i] = noteInfo[0] + " - " + contestationInfo[2];
                break;
            }
        }
        return String.join("\n", noteLines);
    }

    public void addStudentNote(String nume, String medie) { // sets student medie
        for (Student student : studenti) {
            if (student.getNume().equals(nume)) {
                student.setMedie(Double.parseDouble(medie));
            }
        }
    }

    public void addPreferinte(String student, String curs) { // adding students preferinte
        for (Student info : studenti) {
            if (info.getNume().equals(student)) {
                info.setPreferinte(curs);
            }
        }
    }

    public void adaugaCurs(String command) { // adding a new curs
        String[] subline = command.split(" - ");
        String programStudiu = subline[1];
        String numeCurs = subline[2];
        int capacitateMaxima = Integer.parseInt(subline[3]);
        Curs<Student> curs = new Curs<>(numeCurs + ", " + programStudiu.toLowerCase(), capacitateMaxima);
        cursuri.add(curs);
    }

    public static class StudentExistsException extends Exception { // exception for duplicate student
        public StudentExistsException(String message) {
            super(message);
        }
    }

    public void adaugaStudentLaCurs(String numeStudent, String numeCurs) { // adding a student to a curs
        Student student = gasesteStudentDupaNume(numeStudent);
        Curs<?> curs = gasesteCursDupaNume(numeCurs);

        if (student.getStudentTip().equals(curs.getNume().split(", ")[1]) && !student.getAreUnCurs()) {
            if (curs.getStudentiInscrisi().size() < curs.getCapacitateMaxima()) {
                curs.adaugaStudent(student);
                student.setAreUnCurs(true);
            } else {
                List<Student> studentiInscrisi = curs.getStudentiInscrisi();
                double notaMinima = 11.0;
                for (Student info : studentiInscrisi) {
                    double nota = info.getMedie();
                    if (nota < notaMinima) {
                        notaMinima = nota;
                    }
                }
                if (notaMinima == student.getMedie()) {
                    curs.adaugaStudent(student);
                    student.setAreUnCurs(true);
                }
            }
        }
    }

    public void repartizare(String notele) { // organizing students prefering and add students to curs base on notes
        String[] list = notele.split("\n");
        for (String info : list) {
            String nume = info.split(" - ")[0];
            Student student = gasesteStudentDupaNume(nume);
            String[] preferinte = student.getPreferinte().split(", ");
            for (String preferint : preferinte) {
                if (!student.getAreUnCurs()) {
                    adaugaStudentLaCurs(nume, preferint);
                }
            }
        }
    }

    private Student gasesteStudentDupaNume(String nume) { // find a student after student name
        for (Student student : studenti) {
            if (student.getNume().equals(nume)) {
                return student;
            }
        }
        return null;
    }

    private Curs<?> gasesteCursDupaNume(String nume) { // find a curs after curs name
        for (Curs<?> curs : cursuri) {
            if (curs.getNume().split(", ")[0].equals(nume)) {
                return curs;
            }
        }
        return null;
    }

    public String posteaza(String numeStudent) { // returns student information
        String result = "";
        boolean firstTime = false;

        for (Curs<?> curs : cursuri) {
            List<Student> studentiInscrisi = curs.getStudentiInscrisi();
            for (Student student : studentiInscrisi) {
                if (student.getNume().equals(numeStudent)) {
                    if (!firstTime) {
                        String tupe = student.getStudentTip();
                        String type1 = tupe.substring(0, 1).toUpperCase() + tupe.substring(1);
                        result += "Student " + type1 + ": " + student.getNume() + " - " +
                                student.getMedie() + " - " + curs.getNume().split(", ")[0];
                        firstTime = true;
                    }
                }
            }
        }
        return result;
    }

    public String afiseazaCurs(String info) { // returns curs information
        String result = "";
        for (Curs<?> curs : cursuri) {
            if (curs.getNume().split(", ")[0].equals(info)) {
                result += curs.getNume().split(", ")[0] + " (" + curs.getCapacitateMaxima() + ")\n" +
                        showInscrisi(curs.getNume().split(", ")[0]);
            }
        }
        return result;
    }

    public String showInscrisi(String cursName) { // returns curs students
        String result = "";
        for (Curs<?> curs : cursuri) {
            if (curs.getNume().split(", ")[0].equals(cursName)) {
                List<Student> studentiInscrisi = curs.getStudentiInscrisi();
                Collections.sort(studentiInscrisi, Comparator.comparing(Student::getNume));
                for (Student student : studentiInscrisi) {
                    result += student.getNume() + " - " + student.getMedie() + "\n";
                }
            }
        }
        return result.trim();
    }
}
