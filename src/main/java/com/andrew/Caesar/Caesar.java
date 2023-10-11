package com.andrew.Caesar;

import com.andrew.FileOperating.FileOperating;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Scanner;

public class Caesar {
    private int key = -1;
    private FileOperating fileOperating;
    private final String line;
    private final char[] chars;
    private final String ALPHABET_UKR_LOWERCASE = "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя";
    private final String ALPHABET_UKR_UPPERCASE = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯ";
    private final String ALPHABET_SYMBOLS = ".,«»\"\\:!? ";

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
        this.fileOperating = new FileOperating(path);
        this.line = fileOperating.readFile();
        this.chars = new char[line.length()];
        this.key = key;
    }

    public void encrypt() {
        for (int i = 0; i < line.length(); i++) {
            if (ALPHABET_UKR_UPPERCASE.contains(Character.toString(line.charAt(i))) || ALPHABET_UKR_LOWERCASE.contains(Character.toString(line.charAt(i)))) {
                encryptUkr(i);
            } else if (ALPHABET_SYMBOLS.contains(Character.toString(line.charAt(i)))) {
                encryptSymbols(i);
            } else if (Character.isLetter(line.charAt(i))) {
                encryptEng(i);
            } else {
                chars[i] = line.charAt(i);
            }
        }
        fileOperating.writeFile(String.valueOf(chars), "[ENCRYPTED].txt");
    }

    public void decrypt() {
        for (int i = 0; i < line.length(); i++) {
            if (ALPHABET_UKR_UPPERCASE.contains(Character.toString(line.charAt(i))) || ALPHABET_UKR_LOWERCASE.contains(Character.toString(line.charAt(i)))) {
                decryptUkr(i);
            } else if (ALPHABET_SYMBOLS.contains(Character.toString(line.charAt(i)))) {
                decryptSymbols(i);
            } else if (Character.isLetter(line.charAt(i))) {
                decryptEng(i);
            } else {
                chars[i] = line.charAt(i);
            }
        }
        fileOperating.writeFile(String.valueOf(chars), "[DECRYPTED].txt");
    }

    private void encryptEng(int i) {
        if (Character.isLetter(line.charAt(i)) && Character.isUpperCase(line.charAt(i)))
            chars[i] = (char) (((((int) (line.charAt(i))) - 65 + key) % 26) + 65);
        else if (Character.isLetter(line.charAt(i)) && Character.isLowerCase(line.charAt(i)))
            chars[i] = (char) (((((int) (line.charAt(i))) - 97 + key) % 26) + 97);
    }

    private void encryptUkr(int i) {
        if (Character.isLetter(line.charAt(i)) && Character.isUpperCase(line.charAt(i)))
            chars[i] = ALPHABET_UKR_UPPERCASE.charAt((ALPHABET_UKR_UPPERCASE.indexOf(line.charAt(i)) + key) % 33);
        else if (Character.isLetter(line.charAt(i)) && Character.isLowerCase(line.charAt(i)))
            chars[i] = ALPHABET_UKR_LOWERCASE.charAt((ALPHABET_UKR_LOWERCASE.indexOf(line.charAt(i)) + key) % 33);

    }

    private void encryptSymbols(int i) {
        chars[i] = ALPHABET_SYMBOLS.charAt((ALPHABET_SYMBOLS.indexOf(line.charAt(i)) + key) % 10);
    }

    private void decryptEng(int i) {
        if (Character.isLetter(line.charAt(i)) && Character.isUpperCase(line.charAt(i)))
            chars[i] = (char) (((((int) (line.charAt(i))) - 90 - key) % 26) + 90);
        else if (Character.isLetter(line.charAt(i)) && Character.isLowerCase(line.charAt(i)))
            chars[i] = (char) (((((int) (line.charAt(i))) - 122 - key) % 26) + 122);
    }

    private void decryptUkr(int i) {
        int currentIndex;
        if (Character.isLetter(line.charAt(i)) && Character.isUpperCase(line.charAt(i))) {
            currentIndex = ALPHABET_UKR_UPPERCASE.indexOf(line.charAt(i)) - key;
            if (currentIndex < 0) {
                currentIndex += ALPHABET_UKR_UPPERCASE.length();
            }
            chars[i] = ALPHABET_UKR_UPPERCASE.charAt(currentIndex);
        } else if (Character.isLetter(line.charAt(i)) && Character.isLowerCase(line.charAt(i))) {
            currentIndex = ALPHABET_UKR_UPPERCASE.indexOf(line.charAt(i)) - key;
            if (currentIndex < 0) {
                currentIndex += ALPHABET_UKR_UPPERCASE.length();
            }
            chars[i] = ALPHABET_UKR_UPPERCASE.charAt(currentIndex);
        }
    }

    private void decryptSymbols(int i) {
        int currentIndex = ALPHABET_SYMBOLS.indexOf(line.charAt(i)) - key;
        if (currentIndex < 0) {
            currentIndex += ALPHABET_SYMBOLS.length();
        }
        chars[i] = ALPHABET_SYMBOLS.charAt(currentIndex);
    }
}
