package com.github.adamflorczak.passwordholder.model;


import com.github.adamflorczak.passwordholder.businesslogic.WrongUserAndServiceException;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.*;
import java.util.stream.Collectors;

public class PasswordSafe {

    private static Integer allIds = 1;

    private Map<String, ArrayList<PasswordEntry>> entries = new HashMap<>();
    private PasswordEntry pe = new PasswordEntry();
    private TextIO textIO = TextIoFactory.getTextIO();


    public char[] getPassword(final String serviceName, final String login) {
        return entries.values().stream()
                .filter(pe -> pe.listIterator()
                        .next()
                        .getServiceName().equals(serviceName)
                        && pe.listIterator().next().getLogin()
                        .equals(login)).findFirst()
                        .orElseThrow(RuntimeException::new)
                        .listIterator()
                        .next()
                        .getPassword();
    }



    public void addEntries(String serviceName, String login, String password) throws IllegalArgumentException {
        if (exists(serviceName, login)) {
            throw new WrongUserAndServiceException(login + " user in " + serviceName + " already exist. Try another one. \n");
        }
        PasswordEntry pe = PasswordEntry.Builder.create().withId(allIds)
                                                         .withService(serviceName)
                                                         .withLogin(login)
                                                         .withPassword(password)
                                                         .build();

        if (!entries.containsKey(serviceName.toLowerCase())) {
            entries.put(serviceName.toLowerCase(), new ArrayList<PasswordEntry>());
        }
        entries.get(serviceName.toLowerCase()).add(pe);
        textIO.getTextTerminal().println("\nUser added to the Password Holder \n");
        allIds++;
    }

    public boolean exists(String serviceName, String login) {
        if (!entries.containsKey(serviceName.toLowerCase())) {
            return false;
        } else {
            for (PasswordEntry pe : entries.get(serviceName.toLowerCase())) {
                if (pe.getLogin().equals(login)) {
                    return true;
                }
            }
            return false;
        }
    }


    public void removeEntries(String serviceName, String login)  {
        boolean isRemoved = false;
        if (!entries.containsKey(serviceName)) {
            textIO.getTextTerminal().println("\nThis service not found. Try another one. \n");
        } else {

            entries.values().stream().forEach(v -> v.removeIf(e -> e.getLogin().equals(login)
                    && e.getServiceName().equals(serviceName)));
            entries.entrySet().removeIf(e -> e.getValue().isEmpty());
            textIO.getTextTerminal().println("\nThe user has been succesfully removed \n");
                   isRemoved = true;
            }
            if(!isRemoved) {
                textIO.getTextTerminal().println("\nThis user was not found. \n");
            }
        }



    public void printMap() {

        for (Map.Entry<String, ArrayList<PasswordEntry>> passwords : entries.entrySet()) {
            String entry = String.valueOf(passwords.getValue() + "\n");
            textIO.getTextTerminal().println(entry);
        }
    }

    public Collection<PasswordEntry> getUserData() {

        return entries.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
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

    public void copyPasswordToTheClipboard(String serviceName, String userlogin) {

        String password = new String(getPassword(serviceName, userlogin));

        StringSelection selection = new StringSelection(password);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }

    public void printPassword(String serviceName, String userlogin){

        String password = new String(getPassword(serviceName, userlogin));
        System.out.println(password);

    }


}
