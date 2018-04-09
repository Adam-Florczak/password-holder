package com.github.adamflorczak.passwordholder.model;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PasswordSafe {



    public Map<Integer, PasswordEntry> entries = new HashMap<>();
    private PasswordEntry pe = new PasswordEntry();


    public char[] getPassword(final String serviceName) {
        return entries.values().stream()
                .filter(pe -> pe.getServiceName().equals(serviceName))
                .findFirst()
                .orElseThrow(RuntimeException::new)
                .getPassword();
    }

    public void addEntries(String serviceName, String login, String password){

        char[] passworded = password.toCharArray();


        Integer id = PasswordEntry.getAllIds();
        PasswordEntry pe = new PasswordEntry(serviceName, login, passworded);
        entries.put(id, pe);
        System.out.println("Potwierdzam - dodano");
        pe.setAllIds();

    }

    public void removeEntries(Integer id){

        entries.remove(id);
    }

    public void printMap(){

        for (Map.Entry<Integer, PasswordEntry> passwords : entries.entrySet()){
            System.out.println(passwords.getValue());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasswordSafe that = (PasswordSafe) o;
        return Objects.equals(entries, that.entries) &&
                Objects.equals(pe, that.pe);
    }

    @Override
    public int hashCode() {

        return Objects.hash(entries, pe);
    }

    //    public void printRegister() {
//        for (Map.Entry<Client, HashSet<Book>> position : libraryRegister.entrySet()) {
//            System.out.println(position.getKey() + " Ma wypożyczone:  ");
//            for (Book books : position.getValue()) {
//                System.out.println(books.getBookName() + " napisane przez " +books.getAuthor() + " ");
//            }
//            System.out.println();
//        }
//    }



    private static String convertCharsArrayToString(char[] password){

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < password.length; i++) {
            sb.append(password[i]);
        }
        return sb.toString();
    }

    public static void copyingPasswordToTheClipboard(char[] toConvert) {

        String converted = convertCharsArrayToString(toConvert);
        StringSelection selection = new StringSelection(converted);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);


    }


}
