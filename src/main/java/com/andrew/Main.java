package com.andrew;

import com.andrew.Menu.Menu;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            Menu.getInstance().mainmenu();
        } else {
            Menu.getInstance().mainmenu(args);
        }
    }
}
