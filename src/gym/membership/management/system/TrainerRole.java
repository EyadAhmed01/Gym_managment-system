/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gym.membership.management.system;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class TrainerRole {
    
    private MemberDatabase memberDatabase;
    private ClassDatabase classDatabase;
    private MemberClassRegistrationDatabase registrationDatabase;
    
    public TrainerRole() {
        this.memberDatabase = new MemberDatabase("members.txt");
        this.classDatabase = new ClassDatabase("classes.txt");
        this.registrationDatabase = new MemberClassRegistrationDatabase("registrations.txt");

        memberDatabase.readFromFile();
        classDatabase.readFromFile();
        registrationDatabase.readFromFile();
    }

    public void addMember(String memberID, String name, String membershipType, String email, String phoneNumber, String status) {
        if (!memberDatabase.contains(memberID)) {
            Member newMember = new Member(memberID, name, membershipType, email, phoneNumber, status);
            memberDatabase.insertRecord(newMember);
            System.out.println("Member added successfully.");
        } else {
            System.out.println("Member already exists with ID: " + memberID);
        }
    }

    public ArrayList<Member> getListOfMembers() {
        return memberDatabase.returnAllRecords();
    }

    public void addClass(String classID, String className, String trainerID, int duration, int maxParticipants) {
        if (!classDatabase.contains(classID)) {
            Class newClass = new Class(classID, className, trainerID, duration, maxParticipants);
            classDatabase.insertRecord(newClass);
            System.out.println("Class added successfully.");
        } else {
            System.out.println("Class already exists with ID: " + classID);
        }
    }

    public ArrayList<Class> getListOfClasses() {
        return classDatabase.returnAllRecords();
    }

    public boolean registerMemberForClass(String memberID, String classID, LocalDate registrationDate) {
        Class classObj = classDatabase.getRecord(classID);
        if (classObj != null && classObj.getAvailableSeats() > 0) {
            if (!registrationDatabase.contains(memberID + classID)) {
                MemberClassRegistration newRegistration = new MemberClassRegistration(memberID, classID, registrationDate, "active");
                registrationDatabase.insertRecord(newRegistration);
                classObj.setAvailableSeats(classObj.getAvailableSeats() - 1); 
                classDatabase.saveToFile();
                return true;
            }
        }
        return false;
    }

    public boolean cancelRegistration(String memberID, String classID) {
        MemberClassRegistration registration = registrationDatabase.getRecord(memberID + classID);
        if (registration != null && registration.getRegistrationDate().plus(3, ChronoUnit.DAYS).isAfter(LocalDate.now())) {
            registration.setRegistrationStatus("canceled");
            Class classObj = classDatabase.getRecord(classID);
            if (classObj != null) {
                classObj.setAvailableSeats(classObj.getAvailableSeats() + 1);
                classDatabase.saveToFile();
                registrationDatabase.saveToFile();
                return true;
            }
        }
        return false;
    }

    public ArrayList<MemberClassRegistration> getListOfRegistrations() {
        return registrationDatabase.returnAllRecords();
    }

    public void logout() {
        memberDatabase.saveToFile();
        classDatabase.saveToFile();
        registrationDatabase.saveToFile();
        System.out.println("Successfully logged out and saved all data.");
    }
}

