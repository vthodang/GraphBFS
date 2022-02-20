/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ex2_graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;


/**
 * Read the graph from matrix file then do the exercise
 * @author VNHS
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        writeToFile("GraphMatrix.txt");

        // read file to graph matrix
        Graph g = new Graph(readFromFile("GraphMatrix.txt"));
        /*
        Integer[][] e = readFromFile("GraphMatrix.txt");
        char v = 'B';
        String s = "";
        s += (char) ('A' + 2) + ",";
        int i =  v - 'A';
        */
        System.out.println(g.BFS('B'));
        if (g.isConnected())
            System.out.println("G is connected.");
        else
            System.out.println("G is not connected.");
        System.out.println("Degree of the vertice A:" + g.degree('A'));
    }
    
    public static Integer[][] readFromFile(String fname) {
        
        // File variable
        File file = new File(fname);
        
        try {
            // read number of lines
            LineNumberReader lines = new LineNumberReader(new FileReader(file));
            lines.skip(Integer.MAX_VALUE); //skip to the end - last line
            int noOfLines = lines.getLineNumber();
            lines.close(); // close lines number
            
            Integer[][] e = new Integer[noOfLines][noOfLines]; // graph edge matrix
            
            Scanner dataReader = new Scanner(file); //reader object
            for (int i = 0; i < noOfLines; i++) {
                String line = dataReader.nextLine();
//                System.out.println(line.substring(1, line.length() -1));
                String[] elements = line.substring(1, line.length() -1).split(",");
                for (int j = 0; j < noOfLines; j++) {
                    e[i][j] = Integer.parseInt(elements[j].trim());
                }
            }
            
            dataReader.close();  // close reader
//            System.out.println("number of lines: "+ noOfLines);

            return e;
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException ex) {
            System.out.println("Cannot read the file");
        }
        return null;
    }
    
    public static void writeToFile(String filename) {
        
        // File object - the reference to the file location
        File file = new File(filename);
        
        // check if the file not exist then create
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("new file created");
            } catch (IOException ex) {
                System.out.println("File creation error");
            }
        }
        
         // Filewriter variable to write to the file
        try {
            FileWriter fw = new FileWriter(file, true); //true to append data
            //write content
            fw.write("Test 1.\n"); //append the file
            fw.write("Test 2.");
            // close the stream
            fw.close();
            System.out.println("Writed to file.");
        } catch (IOException ex) {
            System.out.println("Write to file error");
        }
    }
}
