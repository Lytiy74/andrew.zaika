package com.andrew.FileOperating;

import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

public class FileOperating {
    private Path path;
    public FileOperating(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file path: ");
        this.path = Path.of(scanner.nextLine());
    }
    public FileOperating(Path path){
        this.path = path;
    }
    public String readFile() {
        String line = "";
        try (FileReader in = new FileReader(path.toString() + ".txt");
             BufferedReader reader = new BufferedReader(in)) {
            while (reader.ready()) {
                line = reader.readLine();
            }
            return line;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeFile(String line, String type) {
        try (FileWriter out = new FileWriter(path.toString()+type);
             BufferedWriter writer = new BufferedWriter(out)) {
            writer.write(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
