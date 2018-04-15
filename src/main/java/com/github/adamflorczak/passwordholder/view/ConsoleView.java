package com.github.adamflorczak.passwordholder.view;


import com.github.adamflorczak.passwordholder.businesslogic.Encryption;
import com.github.adamflorczak.passwordholder.businesslogic.FileSafeController;
import com.github.adamflorczak.passwordholder.businesslogic.PasswordManagerController;
import com.github.adamflorczak.passwordholder.model.PasswordSafe;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

import java.io.File;

public class ConsoleView {

    private TextIO textIO = TextIoFactory.getTextIO();
    private FileSafeController fsc = new FileSafeController(new PasswordSafe());
    private PasswordManagerController pmc = new PasswordManagerController();
    private PasswordSafe ps = new PasswordSafe();
    private Encryption encryption = new Encryption();
    private File file = new File("Password Holder.pwh");


    public int chooseFunctionality() {

        int functionality = textIO.newIntInputReader()
                .withMinVal(1)
                .withMaxVal(8)
                .read("Welcome in Password Holder 1.01 \n" +
                        "Please choose functionality from the list below : \n" +
                        "1) Add value to database \n" +
                        "2) Show all entries \n" +
                        "3) Remove values \n" +
                        "4) Copy to clipboard \n" +
                        "5) Save values to file \n" +
                        "6) Read from file \n" +
                        "7) Encrypt file \n" +
                        "8) Decrypt file \n");
        if (functionality > 0 && functionality < 9) {
            return functionality;
        } else
            throw new IllegalArgumentException("This functionality is not defined in the application");
    }

    public void menu() {

        Integer functionality;

            do {
                 functionality  = chooseFunctionality();
                if (functionality == 1) {
                    ps.addEntries(textIO.newStringInputReader().withMinLength(3).read("Enter name of the service"),
                            textIO.newStringInputReader().withMinLength(3).read("Enter your login"),
                            textIO.newStringInputReader().withMinLength(6)
                                    .withInputMasking(true).read("Enter your password"));
                }
                if (functionality == 2) {
                    ps.printMap();
                }
                if (functionality == 3) {
                    ps.removeEntries(textIO.newStringInputReader().read("Enter service name"),
                            textIO.newStringInputReader().read("Enter login"));
                }
                if (functionality == 4) {
                    ps.copyPasswordToTheClipboard(textIO.newStringInputReader().read("Enter service name "),
                            textIO.newStringInputReader().read("Enter login"));
                }
                if (functionality == 5) {
                    fsc.saveToFilePro(ps, file);
                }
                if (functionality == 6){
                    fsc.readFromFilePrint(file);
                }
                if (functionality == 7){
                    encryption.fileEncryption(file);
                }
                if (functionality == 8){
                    encryption.fileDecryption(file);
                }
            }
            while (functionality != 0);
        }
    }





