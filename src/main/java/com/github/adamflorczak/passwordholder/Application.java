package com.github.adamflorczak.passwordholder;

import com.github.adamflorczak.passwordholder.businesslogic.Encryption;
import com.github.adamflorczak.passwordholder.businesslogic.FileSafeController;
import com.github.adamflorczak.passwordholder.model.PasswordSafe;
import com.github.adamflorczak.passwordholder.view.ConsoleView;

import java.io.File;


public class Application {

    public static void main(String[] args) {
//        File file = new File("pentagon.pwh");
//
//        PasswordSafe passwordSafe = new PasswordSafe();
//        FileSafeController fileSafeController = new FileSafeController(passwordSafe);
//        Encryption encryption = new Encryption();
//
////        passwordSafe.addEntries("facebook", "adam", "bomba");
////        passwordSafe.addEntries("facebook", "mateusz", "twardy");
////
//        fileSafeController.saveToFilePro(passwordSafe, file);
//
//      encryption.fileEncryption(file);
//
//        encryption.fileDecryption(file);

        ConsoleView view = new ConsoleView();

        view.menu();


    }


}
