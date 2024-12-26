/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gym.membership.management.system;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class
ClassDatabase implements Database<Class> {

    private ArrayList<Class> records;
    private String filename;

    public ClassDatabase(String filename) {
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
                    Class c1= createRecordFrom(s);
                    if(!contains(c1.getSearchKey()))
                    {  records.add(c1);}



                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("Error reading file.");
            e.printStackTrace();
        }

    }



    @Override
    public Class createRecordFrom(String line) {
        String[] data = line.split(",");
        String classID = data[0];
        String className = data[1];
        String trainerID = data[2];
        int duration = Integer.parseInt(data[3]);
        int availableSeats = Integer.parseInt(data[4]);
        return new Class(classID, className, trainerID, duration, availableSeats);
    }

    @Override
    public ArrayList<Class> returnAllRecords() {
        return records;
    }

    @Override
    public boolean contains(String key) {
        for (Class classRecord : records) {
            if (classRecord.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Class getRecord(String key) {
        for (Class classRecord : records) {
            if (classRecord.getSearchKey().equals(key)) {
                return classRecord;
            }
        }
        return null;
    }

    @Override
    public void insertRecord(Class record) {
        if (!contains(record.getSearchKey())) {
            records.add(record);
        } else {
            System.out.println("Class with ID " + record.getSearchKey() + " already exists.");
        }
    }

    @Override
    public void deleteRecord(String key) {
        Class classRecord = getRecord(key);
        if (classRecord != null) {
            records.remove(classRecord);
        } else {
            System.out.println("Class with ID " + key + " not found.");
        }
        saveToFile(); 
    }

    @Override
    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Class classRecord : records) {
                bw.write(classRecord.lineRepresentation());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }
}

