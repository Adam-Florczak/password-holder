package com.github.adamflorczak.passwordholder;

import com.github.adamflorczak.passwordholder.model.PasswordSafe;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.HashMap;
import java.util.Map;

public class Application {

    public static void main(String[] args) {

        PasswordSafe passwordSafe = new PasswordSafe();

        passwordSafe.addEntries("facebook","adam", "bomba");
        passwordSafe.addEntries("roksa.pl","mateusz", "twardy");

        passwordSafe.printMap();

   //     PasswordSafe.copyingPasswordToTheClipboard(passwordSafe.getPassword("facebook"));

    }


}
