package com.andrew.Caesar;

import com.andrew.FileOperating.FileOperating;

import java.nio.file.Path;

public class Caesar {

    public static void encrypt(int key, Path path) {
        String line = FileOperating.readFile(path);
        char[] chars = new char[line.length()];
        for (int i = 0; i < line.length(); i++) {
            if (Character.isLetter(line.charAt(i)) && Character.isUpperCase(line.charAt(i)))
                chars[i] = (char) (((((int) (line.charAt(i))) - 65 + key) % 26) + 65);
            else if (Character.isLetter(line.charAt(i)) && Character.isLowerCase(line.charAt(i)))
                chars[i] = (char) (((((int) (line.charAt(i))) - 97 + key) % 26) + 97);
            else
                chars[i] = line.charAt(i);
        }
        FileOperating.writeFile(String.valueOf(chars), Path.of(path + "[ENCRYPTED].txt"));
    }
}
