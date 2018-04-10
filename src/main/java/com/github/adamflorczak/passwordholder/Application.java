package com.github.adamflorczak.passwordholder;

import com.github.adamflorczak.passwordholder.businesslogic.FileSafeController;
import com.github.adamflorczak.passwordholder.model.PasswordEntry;
import com.github.adamflorczak.passwordholder.model.PasswordSafe;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Application {

    public static void main(String[] args) {

        PasswordSafe passwordSafe = new PasswordSafe();


        passwordSafe.addEntries(1, "facebook", "adam", "bomba");
        passwordSafe.addEntries(2, "google", "mateusz", "twardy");


        FileSafeController fileSafeController = new FileSafeController(passwordSafe);

        fileSafeController.saveToFilePro("tajneprzezpoufne.xxx");


        //    PasswordSafe.copyingPasswordToTheClipboard(passwordSafe.getPassword("facebook"));

        fileSafeController.readFromFilePrint("tajneprzezpoufne.xxx");

    }


}
