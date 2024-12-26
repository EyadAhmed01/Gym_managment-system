/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gym.membership.management.system;


import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class MemberClassRegistrationDatabase implements Database<MemberClassRegistration> {
    private ArrayList<MemberClassRegistration> records;
    private String filename;

    public MemberClassRegistrationDatabase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }

    @Override
    public void readFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                MemberClassRegistration record = createRecordFrom(line);
                records.add(record);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + filename);
        }
    }
    
    @Override
    public MemberClassRegistration createRecordFrom(String line) {
        String[] parts = line.split(",");
        String memberID = parts[0];
        String classID = parts[1];
        LocalDate registrationDate = LocalDate.parse(parts[2]); 
        String status = parts[3];

        return new MemberClassRegistration(memberID, classID, registrationDate, status);
    }

    @Override
    public ArrayList<MemberClassRegistration> returnAllRecords() {
        return records;
    }

    @Override
    public boolean contains(String key) {
        for (MemberClassRegistration record : records) {
            if (record.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public MemberClassRegistration getRecord(String key) {
        for (MemberClassRegistration record : records) {
            if (record.getSearchKey().equals(key)) {
                return record;
            }
        }
        return null;
    }

    @Override
    public void insertRecord(MemberClassRegistration record) {
        if (!contains(record.getSearchKey())) {
            records.add(record);
        }
    }

    @Override
    public void deleteRecord(String key) {
        MemberClassRegistration Record = getRecord(key);
        if (Record != null) {
            records.remove(Record);
        } else {
            System.out.println("Member Class Registration with ID " + key + " not found.");
        }
        saveToFile(); 
    }

    @Override
    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (MemberClassRegistration record : records) {
                bw.write(record.lineRepresentation());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + filename);
        }
    }
}

