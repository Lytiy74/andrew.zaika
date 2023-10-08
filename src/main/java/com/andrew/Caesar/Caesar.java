package com.andrew.Caesar;

import com.andrew.FileOperating.FileOperating;

import java.nio.file.Path;
import java.util.Scanner;

public class Caesar {
    private int key = -1;
    private FileOperating fileOperating;
    private final String line;
    private final char[] chars;

    public Caesar() {
        this.fileOperating = new FileOperating();
        this.line = fileOperating.readFile();
        this.chars = new char[line.length()];
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter key:");
        if (scanner.hasNextInt()) {
            this.key = scanner.nextInt();
        }
    }
    public Caesar(int key, Path path) {
        this.fileOperating  = new FileOperating(path);
        this.line = fileOperating.readFile();
        this.chars = new char[line.length()];
        this.key = key;
    }

    public void encrypt() {
        for (int i = 0; i < line.length(); i++) {
                if (Character.isLetter(line.charAt(i)) && Character.isUpperCase(line.charAt(i)))
                    chars[i] = (char) (((((int) (line.charAt(i))) - 65 + key) % 26) + 65);
                else if (Character.isLetter(line.charAt(i)) && Character.isLowerCase(line.charAt(i)))
                    chars[i] = (char) (((((int) (line.charAt(i))) - 97 + key) % 26) + 97);
                else
                    chars[i] = line.charAt(i);
        }
        fileOperating.writeFile(String.valueOf(chars),"[ENCRYPTED].txt");
    }
    public void decrypt() {
        for (int i = 0; i < line.length(); i++) {
            if (Character.isLetter(line.charAt(i)) && Character.isUpperCase(line.charAt(i)))
                chars[i] = (char) (((((int) (line.charAt(i))) - 90 - key) % 26) + 90);
            else if (Character.isLetter(line.charAt(i)) && Character.isLowerCase(line.charAt(i)))
                chars[i] = (char) (((((int) (line.charAt(i))) - 122 - key) % 26) + 122);
            else
                chars[i] = line.charAt(i);
        }
        fileOperating.writeFile(String.valueOf(chars),"[DECRYPTED].txt");
    }
}
