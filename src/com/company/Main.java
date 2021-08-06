package com.company;

import com.company.FinalSystem.Final;
import com.company.PatientHandler.Entry;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Final<Integer, Integer> ds = new Final<>();
        Entry<Integer, Integer> entry;

        Scanner terminalScan = new Scanner(System.in);
        String command;
        int id , health;
        System.out.print("Enter your choice:\n\t1. Read a File\n\t2. Enter Commands\n--> ");
        int choice = terminalScan.nextInt();

        if (choice == 1){
            System.out.print("Enter the File location: ");
            terminalScan.nextLine();
            String fileLocation = terminalScan.nextLine();
            File file = new File(fileLocation);
            Scanner fileScan = new Scanner(file);
            Instant start = Instant.now();
            do{
                command = fileScan.next();
                switch (command){
                    case "Add":
                        id = fileScan.nextInt();
                        health = fileScan.nextInt();
                        ds.add(id, health);
                        break;
                    case "Update":
                        id = fileScan.nextInt();
                        health = fileScan.nextInt();
                        ds.update(id, health);
                        break;
                    case "Serve":
                        command = fileScan.next();
                        if (command.equalsIgnoreCase("First")){
                            entry = ds.serveFirst();
                            System.out.println(entry.getID() + " " + entry.getHealth());
                        } else if (command.equalsIgnoreCase("Sickest")) {
                            entry = ds.serveSickest();
                            System.out.println(entry.getID() + " " + entry.getHealth());
                        }
                        break;
                    default:
                        break;
                }
            }while (fileScan.hasNextLine());
        } else if (choice == 2){
            System.out.println("Enter your commands:");
            do{
                command = terminalScan.next();
                switch (command){
                    case "Add":
                        id = terminalScan.nextInt();
                        health = terminalScan.nextInt();
                        ds.add(id, health);
                        break;
                    case "Update":
                        id = terminalScan.nextInt();
                        health = terminalScan.nextInt();
                        ds.update(id, health);
                        break;
                    case "Serve":
                        command = terminalScan.next();
                        if (command.equalsIgnoreCase("First")){
                            entry = ds.serveFirst();
                            System.out.println(entry.getID() + " " + entry.getHealth());
                        } else if (command.equalsIgnoreCase("Sickest")) {
                            entry = ds.serveSickest();
                            System.out.println(entry.getID() + " " + entry.getHealth());
                        }
                        break;
                    default:
                        break;
                }
            }while (!ds.isEmpty());
        } else {
            throw new IllegalArgumentException("Invalid Input");
        }


    }
}
