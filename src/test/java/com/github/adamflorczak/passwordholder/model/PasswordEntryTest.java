package com.github.adamflorczak.passwordholder.model;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordEntryTest {


    @Test
    public void test() {
        PasswordEntry passwordEntry = PasswordEntry.Builder.create()
                .withPassword("APSDA")
                .withLogin("xxxxx")
                .build();
        assertThat(passwordEntry)
                .hasFieldOrPropertyWithValue("password", "APSDA".toCharArray());
    }
}
