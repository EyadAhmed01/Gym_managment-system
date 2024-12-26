/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gym.membership.management.system;


import java.time.LocalDate;

public class MemberClassRegistration implements Person {

    private String memberID;
    private String classID;
    private String status;
    private LocalDate registrationDate;

    public MemberClassRegistration(String memberID, String classID, LocalDate registrationDate, String status) {
        this.memberID = memberID;
        this.classID = classID;
        this.registrationDate = registrationDate;
        this.status = status;
    }

    public String getMemberID() {
        return memberID;
    }

    public String getClassID() {
        return classID;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public String getSearchKey() {
        return memberID + classID;
    }

    @Override
    public String lineRepresentation() {
        return memberID + "," + classID + "," + registrationDate + "," + status;
    }
    
    public void setRegistrationStatus(String newStatus) {
        this.status = newStatus;
    }

    public String getRegistrationStatus() {
        return status;
    }
}

