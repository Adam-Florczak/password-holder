package com.github.adamflorczak.passwordholder.businesslogic;

import com.github.adamflorczak.passwordholder.model.PasswordSafe;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * Ta klasa ma zarządzać dodawaniem nowych haseł, usuwaniem istniejących, pobieraniem istniejących. Dla nowego hasła,
 * powinna być opcja przyjęcia go od usera oraz w przyszłości wygenerowania nowego hasła.
 */

public class PasswordManagerController {

    PasswordSafe ps = new PasswordSafe();

    public void copyPasswordToTheClipboard(String serviceName, String userlogin) {

       String password = new String(ps.getPassword(serviceName, userlogin));

        StringSelection selection = new StringSelection(password);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }

    public void printPassword(String serviceName, String userlogin){

        String password = new String(ps.getPassword(serviceName, userlogin));
        System.out.println(password);

    }

    public void addPassword(Integer id, String serviceName, String userLogin, String password){

        if(ps.exists(serviceName, userLogin)){
            System.out.println("The user " + userLogin + " already exists in the " + serviceName);
        }

        ps.addEntries(id,serviceName,userLogin,password);

    }

}
