/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gym.membership.management.system;


import java.util.ArrayList;

public interface Database<X> {
    void readFromFile();
    X createRecordFrom(String line);
    ArrayList<X> returnAllRecords();
    boolean contains(String key);
    X getRecord(String key);
    void insertRecord(X record);
    void deleteRecord(String key);
    void saveToFile();
}
