package com.github.adamflorczak.passwordholder;

import com.github.adamflorczak.passwordholder.businesslogic.FileSafeController;
import com.github.adamflorczak.passwordholder.model.PasswordSafe;
import com.github.adamflorczak.passwordholder.view.ConsoleView;


public class Application {

    public static void main(String[] args) {

        PasswordSafe passwordSafe = new PasswordSafe();
        FileSafeController fileSafeController = new FileSafeController(passwordSafe);

        passwordSafe.addEntries(1,"facebook", "adam", "bomba");
        passwordSafe.addEntries(2,"facebook", "mateusz", "twardy");

//        passwordSafe.printMap();
        fileSafeController.saveToFilePro("tajneprzezpoufne.xxx");

        fileSafeController.readFromFilePrint("tajneprzezpoufne.xxx");





    }


}
