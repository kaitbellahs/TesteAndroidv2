package com.resourceit.app.tools;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidatorTest {

    @Test
    public void Check_Is_Valid_Email() {
        String text = "user@domain.ltv";
        assertTrue(Validator.isValidEmail(text));
    }

    @Test
    public void Check_Is_InValid_Email() {
        String text = "user@domain";
        assertFalse(Validator.isValidEmail(text));
    }

    @Test
    public void Check_Is_Valid_CPF() {
        String text = "41704310067";
        assertTrue(Validator.isValidCPF(text));
    }

    @Test
    public void Check_Is_InValid_CPF() {
        String text = "41704310060";
        assertFalse(Validator.isValidCPF(text));
    }

    @Test
    public void Check_Is_Valid_Senha() {
        String text = "abCD12@#";
        assertTrue(Validator.isValidPassword(text));
    }

    @Test
    public void Check_Is_InValid_Senha_1() {
        String text = "abCD1234";
        assertFalse(Validator.isValidPassword(text));
    }

    @Test
    public void Check_Is_InValid_Senha_2() {
        String text = "abCD@@@@";
        assertFalse(Validator.isValidPassword(text));
    }

    @Test
    public void Check_Is_InValid_Senha_3() {
        String text = "abCD1@";
        assertFalse(Validator.isValidPassword(text));
    }

    @Test
    public void Check_Is_InValid_Senha_4() {
        String text = "abcd12@#";
        assertFalse(Validator.isValidPassword(text));
    }

}