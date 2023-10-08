package com.andrew.Menu;

import java.util.*;

public class Menu {
    private static Menu instance;
    private int optionIndex = 1;
    private int userOption = -1;
    private final HashMap<Integer, String> menuOptions = new HashMap<>();

    private Menu() {
        for (MenuElements elements : MenuElements.values()) {
            menuOptions.put(optionIndex++, elements.getTitle());
        }
    }

    public static Menu getInstance(){
        if(instance == null){
            instance = new Menu();
        }
        return  instance;
    }
    public void mainmenu() {
        printMenuElements();
        getUserAnswer();
    }

    public void mainmenu(String[] args) {
        //Згідно умови отримуємо аргументи типу command filePath key.

    }

    public void printMenuElements() {
        System.out.println("Choose an option:");
        for (Map.Entry<Integer, String> option : menuOptions.entrySet()) {
            System.out.printf("%d. %s\n", option.getKey(), option.getValue());
        }
    }

    public void getUserAnswer() {
        Scanner consoleScanner = new Scanner(System.in);
        String consoleInput;
        do {
            if (consoleScanner.hasNextInt()) {
                consoleInput = consoleScanner.nextLine();
                if (menuOptions.containsKey(Integer.parseInt(consoleInput))) {
                    userOption = Integer.parseInt(consoleInput);
                } else
                    printMenuElements();

            } else {
                consoleInput = consoleScanner.nextLine();
                for (Map.Entry<Integer, String> option : menuOptions.entrySet()) {
                    if (consoleInput.equals(option.getValue())) {
                        userOption = option.getKey();
                    } else {
                        printMenuElements();
                    }
                }
            }
        } while (userOption == -1);
    }

}

