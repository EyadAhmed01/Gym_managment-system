/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gym.membership.management.system;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class TrainerDatabase implements Database<Trainer> {

    private ArrayList<Trainer> records;
    private String filename;

    public TrainerDatabase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
        readFromFile();
    }


    @Override
    public void readFromFile() {
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            if (!scanner.hasNextLine()){
                System.out.println("Empty file.");

            }
            else {
                while (scanner.hasNextLine())
                {
                    String s = scanner.nextLine();
                    Trainer t1= createRecordFrom(s);
                    if(!contains(t1.getSearchKey()))
                    {  records.add(t1);}



                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("Error reading file.");
            e.printStackTrace();
        }

    }

    @Override
    public Trainer createRecordFrom(String line) {
        String[] parts = line.split(",");
        if (parts.length == 5) {
            String trainerId = parts[0];
            String name = parts[1];
            String email = parts[2];
            String speciality = parts[3];
            String phoneNumber = parts[4];
            return new Trainer(trainerId, name, email, speciality, phoneNumber);
        } else {
            System.out.println("Invalid trainer data format.");
            return null;
        }
    }

    @Override
    public ArrayList<Trainer> returnAllRecords() {
        return records;
    }

    @Override
    public boolean contains(String key) {
        for (Trainer trainer : records) {
            if (trainer.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Trainer getRecord(String key) {
        for (Trainer trainer : records) {
            if (trainer.getSearchKey().equals(key)) {
                return trainer;
            }
        }
        return null;
    }

    @Override
    public void insertRecord(Trainer record) {
        if (!contains(record.getSearchKey())) {
            records.add(record);
        } else {
            System.out.println("Trainer with ID " + record.getSearchKey() + " already exists.");
        }
    }

    @Override
    public void deleteRecord(String key) {
        Trainer trainerToRemove = getRecord(key);
        if (trainerToRemove != null) {
            records.remove(trainerToRemove);
        } else {
            System.out.println("Trainer with ID " + key + " not found.");
        }
        saveToFile();
    }

    @Override
    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Trainer trainer : records) {
                bw.write(trainer.lineRepresentation());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
