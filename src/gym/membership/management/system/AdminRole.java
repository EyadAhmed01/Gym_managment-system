/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gym.membership.management.system;


import java.util.ArrayList;

public class AdminRole {
    
    private TrainerDatabase database;

    public AdminRole() {
        database = new TrainerDatabase("Trainers.txt");
    }

    public void addTrainer(String trainerId, String name, String email, String specialty, String phoneNumber) {
        if (!database.contains(trainerId)) {
            Trainer newTrainer = new Trainer(trainerId, name, email, specialty, phoneNumber);
            database.insertRecord(newTrainer);
            System.out.println("Trainer added successfully.");
        } else {
            System.out.println("Trainer with ID " + trainerId + " already exists.");
        }
    }

    public ArrayList<Trainer> getListOfTrainers() {
        return database.returnAllRecords();
    }

    public void removeTrainer(String key) {
        if (database.contains(key)) {
            database.deleteRecord(key);
            System.out.println("Trainer with ID " + key + " removed successfully.");
        } else {
            System.out.println("Trainer with ID " + key + " not found.");
        }
    }

    public void logout() {
        database.saveToFile();
        System.out.println("Successfully logged out and saved all data.");
    }
}

