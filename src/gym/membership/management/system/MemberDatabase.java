/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gym.membership.management.system;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MemberDatabase implements Database<Member> {

    private ArrayList<Member> records;
    private String filename;

    public MemberDatabase(String filename) {
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
                    Member c1= createRecordFrom(s);
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
    public Member createRecordFrom(String line) {
        String[] data = line.split(",");
        return new Member(data[0], data[1], data[2], data[3], data[4], data[5]);
    }

    @Override
    public ArrayList<Member> returnAllRecords() {
        return records;
    }

    @Override
    public boolean contains(String key) {
        for (Member member : records) {
            if (member.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Member getRecord(String key) {
        for (Member member : records) {
            if (member.getSearchKey().equals(key)) {
                return member;
            }
        }
        return null;
    }

    @Override
    public void insertRecord(Member record) {
        if (!contains(record.getSearchKey())) {
            records.add(record);
        } else {
            System.out.println("Member with ID " + record.getSearchKey() + " already exists.");
        }
    }

    @Override
    public void deleteRecord(String key) {
        Member memberToRemove = getRecord(key);
        if (memberToRemove != null) {
            records.remove(memberToRemove);
        } else {
            System.out.println("Member with ID " + key + " not found.");
        }
        saveToFile();
    }

    @Override
    public void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Member member : records) {
                bw.write(member.lineRepresentation());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }
}
