package com.andrew.FileOperating;

import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;

public class FileOperating {
    public static String readFile(Path path) {
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

    public static void writeFile(String line, Path path) {
        try (FileWriter out = new FileWriter(path.toString());
             BufferedWriter writer = new BufferedWriter(out)) {
            writer.write(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Path getPath() {
        Scanner consoleScanner = new Scanner(System.in);
        System.out.println("Write path to file: ");
        String consoleInput = consoleScanner.nextLine();
        return Path.of(consoleInput);
    }
}
