package org.example;

import java.io.*;

public class Main {

    public static String readFile(String file) { // reads .in file and returns a string
        StringBuilder content = new StringBuilder();
        String filePath = "src/main/resources/" + file + "/" + file + ".in";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return content.toString();
    }

    public static void executeCommand(String[] command, String test) { // executes commands
        Secretariat secretariat = new Secretariat();
        String notes = null;
        String sortedNotes = null;
        for (int i = 0; i < command.length; i++) {
            if (command[i].contains("adauga_student")) { // if command is adauga_student
                String[] subline2 = command[i].split(" - ");
                try {
                    secretariat.adaugaStudent(subline2[2], subline2[1]);
                } catch (Secretariat.StudentExistsException e) {
                    secretariat.writeOutFile(test, e.getMessage());
                }
            } else if (command[i].contains("citeste_mediile")) { // if command is citeste_mediile
                notes = secretariat.readNoteFilesInFolder("src/main/resources/" + test + "/");
                sortedNotes = secretariat.sortLinesByGradeAndName(notes.trim());
                for (String line : sortedNotes.split("\n")) {
                    String[] subLine = line.split(" - ");
                    secretariat.addStudentNote(subLine[0], subLine[1]);
                }
            } else if (command[i].contains("posteaza_mediile")) { // if command is posteaza_mediile
                secretariat.writeOutFile(test, sortedNotes);
            } else if (command[i].contains("contestatie")) { // if command is contestatie
                sortedNotes = secretariat.updateStudentMedie(sortedNotes, command[i]);
                sortedNotes = secretariat.sortLinesByGradeAndName(sortedNotes.trim());
                String[] subLine = command[i].split(" - ");
                secretariat.addStudentNote(subLine[1], subLine[2]);
            } else if (command[i].contains("adauga_curs")) { // if command is adauga_curs
                secretariat.adaugaCurs(command[i]);
            } else if (command[i].contains("adauga_preferinte")) { // if command is adauga_preferinte
                String[] cursuri = command[i].split(" - ");
                for (int j = 2; j < cursuri.length; j++) {
                    secretariat.addPreferinte(cursuri[1],cursuri[j]);
                }
            } else if (command[i].contains("repartizeaza")) { // if command is repartizeaza
                secretariat.repartizare(sortedNotes);
            } else if (command[i].contains("posteaza_curs")) { // if command is posteaza_curs
                String response = secretariat.afiseazaCurs(command[i].split(" - ")[1]);
                secretariat.writeOutFile(test, response);
            } else if (command[i].contains("posteaza_student")){ // if command is posteaza_student
                String response = secretariat.posteaza(command[i].split(" - ")[1]);
                secretariat.writeOutFile(test, response);
            }
        }
    }

    public static void main(String[] args) {
        String file;
        String[] lines;
        switch(args[0]) { // calling executeCommand function base on the test string
            case "01-posteaza_medii":
                file = "01-posteaza_medii";
                lines = readFile(file).split("\n");
                executeCommand(lines, file);
                break;

            case "02-posteaza_medii_contestatii":
                file = "02-posteaza_medii_contestatii";
                lines = readFile(file).split("\n");
                executeCommand(lines, file);
                break;

            case "03-posteaza_medii_contestatii":
                file = "03-posteaza_medii_contestatii";
                lines = readFile(file).split("\n");
                executeCommand(lines, file);
                break;

            case "04-posteaza_medii_contestatii":
                file = "04-posteaza_medii_contestatii";
                lines = readFile(file).split("\n");
                executeCommand(lines, file);
                break;

            case "05-inroleaza_simplu":
                file = "05-inroleaza_simplu";
                lines = readFile(file).split("\n");
                executeCommand(lines, file);
                break;

            case "06-inroleaza_ciclu_studii":
                file = "06-inroleaza_ciclu_studii";
                lines = readFile(file).split("\n");
                executeCommand(lines, file);
                break;

            case "07-inroleaza_ciclu_studii_contestatii":
                file = "07-inroleaza_ciclu_studii_contestatii";
                lines = readFile(file).split("\n");
                executeCommand(lines, file);
                break;

            case "08-coliziuni_medie":
                file = "08-coliziuni_medie";
                lines = readFile(file).split("\n");
                executeCommand(lines, file);
                break;

            case "09-coliziuni_ciclu_studii":
                file = "09-coliziuni_ciclu_studii";
                lines = readFile(file).split("\n");
                executeCommand(lines, file);
                break;

            case "10-coliziuni_ciclu_studii_contestatii":
                file = "10-coliziuni_ciclu_studii_contestatii";
                lines = readFile(file).split("\n");
                executeCommand(lines, file);
                break;

            case "11-exceptie_simplu":
                file = "11-exceptie_simplu";
                lines = readFile(file).split("\n");
                executeCommand(lines, file);
                break;

            case "12-toate_functionalitatile":
                file = "12-toate_functionalitatile";
                lines = readFile(file).split("\n");
                executeCommand(lines, file);
                break;

            default:
                System.out.println("code");
        }
    }
}
