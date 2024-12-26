/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gym.membership.management.system;

import java.time.LocalDate;
import java.util.Scanner;

public class GymMembershipManagementSystem {

public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AdminRole adminRole = new AdminRole();
        TrainerRole trainerRole = new TrainerRole();

        while (true) {
            System.out.println("Welcome to the Gym Membership Management System");
            System.out.println("Please select your role:");
            System.out.println("1. Admin");
            System.out.println("2. Trainer");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 3) {
                System.out.println("Exiting the system. Goodbye!");
                break;
            }

            if (choice == 1) {
                while (true) {
                    System.out.println("\n--- Admin Menu ---");
                    System.out.println("1. Add Trainer");
                    System.out.println("2. Get List of Trainers");
                    System.out.println("3. Remove Trainer");
                    System.out.println("4. Logout");
                    System.out.println("5. Return to Main Menu");
                    System.out.print("Enter your choice: ");
                    int adminChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (adminChoice == 5) {
                        break; 
                    }

                    if (adminChoice == 4) {
                        adminRole.logout();
                        break;
                    }

                    if (adminChoice == 1) {
                        System.out.print("Enter Trainer ID: ");
                        String trainerId = scanner.nextLine();
                        System.out.print("Enter Trainer Name: ");
                        String trainerName = scanner.nextLine();
                        System.out.print("Enter Specialty: ");
                        String specialty = scanner.nextLine();
                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter Phone Number: ");
                        String phoneNumber = scanner.nextLine();
                        adminRole.addTrainer(trainerId, trainerName, specialty, email, phoneNumber);
                    } else if (adminChoice == 2) {
                        System.out.println("Listing all trainers:");
                        for (Trainer trainer : adminRole.getListOfTrainers()) {
                            System.out.println(trainer.lineRepresentation());
                        }
                    } else if (adminChoice == 3) {
                        System.out.print("Enter Trainer ID to remove: ");
                        String trainerId = scanner.nextLine();
                        adminRole.removeTrainer(trainerId);
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                }
            } else if (choice == 2) {
                while (true) {
                    System.out.println("\n--- Trainer Menu ---");
                    System.out.println("1. Add Member");
                    System.out.println("2. Get List of Members");
                    System.out.println("3. Add Class");
                    System.out.println("4. Get List of Classes");
                    System.out.println("5. Register Member for Class");
                    System.out.println("6. Cancel Member Registration");
                    System.out.println("7. Get List of Registrations");
                    System.out.println("8. Logout");
                    System.out.println("9. Return to Main Menu");
                    System.out.print("Enter your choice: ");
                    int trainerChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (trainerChoice == 9) {
                        break;
                    }

                    if (trainerChoice == 8) {
                        trainerRole.logout();
                        break;
                    }

                    if (trainerChoice == 1) {
                        System.out.print("Enter Member ID: ");
                        String memberId = scanner.nextLine();
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Membership Type: ");
                        String membershipType = scanner.nextLine();
                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter Phone Number: ");
                        String phoneNumber = scanner.nextLine();
                        System.out.print("Enter Status: ");
                        String status = scanner.nextLine();
                        trainerRole.addMember(memberId, name, membershipType, email, phoneNumber, status);
                    } else if (trainerChoice == 2) {
                        System.out.println("Listing all members:");
                        for (Member member : trainerRole.getListOfMembers()) {
                            System.out.println(member.lineRepresentation());
                        }
                    } else if (trainerChoice == 3) {
                        System.out.print("Enter Class ID: ");
                        String classId = scanner.nextLine();
                        System.out.print("Enter Class Name: ");
                        String className = scanner.nextLine();
                        System.out.print("Enter Trainer ID: ");
                        String trainerId = scanner.nextLine();
                        System.out.print("Enter Duration (in minutes): ");
                        int duration = scanner.nextInt();
                        System.out.print("Enter Maximum Participants: ");
                        int maxParticipants = scanner.nextInt();
                        scanner.nextLine();
                        trainerRole.addClass(classId, className, trainerId, duration, maxParticipants);
                    } else if (trainerChoice == 4) {
                        System.out.println("Listing all classes:");
                        for (Class classObj : trainerRole.getListOfClasses()) {
                            System.out.println(classObj.lineRepresentation());
                        }
                    } else if (trainerChoice == 5) {
                        System.out.print("Enter Member ID: ");
                        String memberId = scanner.nextLine();
                        System.out.print("Enter Class ID: ");
                        String classId = scanner.nextLine();
                        System.out.print("Enter Registration Date (YYYY-MM-DD): ");
                        LocalDate registrationDate = LocalDate.parse(scanner.nextLine());
                        if (trainerRole.registerMemberForClass(memberId, classId, registrationDate)) {
                            System.out.println("Member registered successfully.");
                        } else {
                            System.out.println("Registration failed. Class may be full or member already registered.");
                        }
                    } else if (trainerChoice == 6) {
                        System.out.print("Enter Member ID: ");
                        String memberId = scanner.nextLine();
                        System.out.print("Enter Class ID: ");
                        String classId = scanner.nextLine();
                        if (trainerRole.cancelRegistration(memberId, classId)) {
                            System.out.println("Registration canceled successfully.");
                        } else {
                            System.out.println("Cancellation failed. Either registration does not exist or it is past the cancellation period.");
                        }
                    } else if (trainerChoice == 7) {
                        System.out.println("Listing all registrations:");
                        for (MemberClassRegistration registration : trainerRole.getListOfRegistrations()) {
                            System.out.println(registration.lineRepresentation());
                        }
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                }
            } else {
                System.out.println("Invalid 1choice. Please try again.");
            }
        }
        scanner.close();
    }
}
