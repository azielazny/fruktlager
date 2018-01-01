package com.fruktlager.ui;

import java.util.Scanner;

public class MenuScreen {

    private Scanner scanner;
    private MemberData memberData = new MemberData();

    public MenuScreen(Scanner scanner) {
        this.scanner = scanner;
    }

    public void show() {
        Menu menu = new Menu(scanner);
        menu.setTitleLabel("MAIN MENU...");
        menu.addItem("Show Profile", () -> memberData.show());
        menu.setLastItemLabel("Quit");
        menu.show();
    }

}
