package com.andrew.Menu;

enum MenuElements { // Поле з елементами меню
    ENCRYPT("Encrypt"),
    DECRYPT("Decrypt"),
    BRUT_FORCE("BRUT_FORCE"),
    EXIT("EXIT");

    private final String title;

    MenuElements(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}
