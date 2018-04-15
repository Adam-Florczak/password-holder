package com.github.adamflorczak.passwordholder.businesslogic;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Encryption {
    private SecretKeySpec secretKey;
    private Cipher cipher;
    private TextIO textIO = TextIoFactory.getTextIO();

    public boolean isEncrypted() {
        return isEncrypted;
    }

    public void setEncrypted(boolean encrypted) {
        isEncrypted = encrypted;
    }

    private boolean isEncrypted = false;

    public Encryption() {

    }

    public Encryption(String secret, int length, String algorithm)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException {
        byte[] key = new byte[length];
        key = fixSecret(secret, length);
        this.secretKey = new SecretKeySpec(key, algorithm);
        this.cipher = Cipher.getInstance(algorithm);
    }

    private byte[] fixSecret(String s, int length) throws UnsupportedEncodingException {
        if (s.length() < length) {
            int missingLength = length - s.length();
            for (int i = 0; i < missingLength; i++) {
                s += " ";
            }
        }
        return s.substring(0, length).getBytes("UTF-8");
    }

    public void encryptFile(File f)
            throws InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        textIO.getTextTerminal().println("\nEncrypting file: " + f.getName() + "\n");
        this.cipher.init(Cipher.ENCRYPT_MODE, this.secretKey);
        this.writeToFile(f);
    }

    public void decryptFile(File f)
            throws InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        textIO.getTextTerminal().println("\nDecrypting file: " + f.getName() + "\n");
        this.cipher.init(Cipher.DECRYPT_MODE, this.secretKey);
        this.writeToFile(f);
    }

    public void writeToFile(File f) throws IOException, IllegalBlockSizeException, BadPaddingException {
        FileInputStream in = new FileInputStream(f);
        byte[] input = new byte[(int) f.length()];
        in.read(input);

        FileOutputStream out = new FileOutputStream(f);
        byte[] output = this.cipher.doFinal(input);
        out.write(output);

        out.flush();
        out.close();
        in.close();
    }

    public void fileEncryption(File file) {


        Encryption ske;
        try {
            ske = new Encryption("!@#$MySecr3tPassw0rd", 16, "AES");

            try {
                ske.encryptFile(file);
            } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException
                    | IOException e) {
                textIO.getTextTerminal().println("\nCouldn't encrypt " + file.getName() + ": " + e.getMessage() + "\n");
            }
            textIO.getTextTerminal().println("\nFiles encrypted successfully\n");
            setEncrypted(true);

        } catch (UnsupportedEncodingException ex) {
            textIO.getTextTerminal().println("Couldn't create key: " + ex.getMessage() + "\n");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            textIO.getTextTerminal().println("\n" + e.getMessage() + "\n");
        }
    }

    public void fileDecryption(File file) {

        if (!isEncrypted()) {
            try {
                throw new UnsupportedEncodingException();
            } catch (UnsupportedEncodingException e) {
                textIO.getTextTerminal().println("\nThis file is not encrypted. Encrypt it first or choose a different one.\n");
            }
        }
        Encryption ske;
        try {
            ske = new Encryption("!@#$MySecr3tPassw0rd", 16, "AES");

            try {
                ske.decryptFile(file);
            } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException
                    | IOException e) {
                textIO.getTextTerminal().println("\nCouldn't decrypt " + file.getName() + ": " + e.getMessage() + "\n");
            }
            textIO.getTextTerminal().println("Files decrypted successfully");

        } catch (UnsupportedEncodingException ex) {
            textIO.getTextTerminal().println("\nCouldn't create key: " + ex.getMessage() + "\n");
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            textIO.getTextTerminal().println("\n" + e.getMessage() + "\n");
        }

    }
}

