/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gym.membership.management.system;


public class Member implements Person{

    private String memberID;
    private String name;
    private String membershipType;
    private String email;
    private String phoneNumber;
    private String status; 

    public Member(String memberID, String name, String membershipType, String email, String phoneNumber, String status) {
        this.memberID = memberID;
        this.name = name;
        this.membershipType = membershipType;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }
    
    @Override
    public String lineRepresentation() {
        return memberID + "," + name + "," + membershipType + "," + email + "," + phoneNumber + "," + status;
    }

    @Override
    public String getSearchKey() {
        return memberID;
    }
}
