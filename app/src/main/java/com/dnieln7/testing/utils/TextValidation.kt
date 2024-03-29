package com.dnieln7.testing.utils

object TextValidation {

    /**
     * Validates if the provided string is a valid representation of an email address.
     */
    fun isEmail(text: String): Boolean {
        return text.matches(Regex("(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"))
    }

    /**
     * Validates if the provided string follows the rules listed below:
     * - 8 characters long.
     * - One uppercase letter.
     * - One number.
     * - One special symbol.
     */
    fun isPassword(text: String): Boolean {
        return text.matches(Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@\$%^&(){}\\[\\]:;<>,.?/~_+-=|\\\\]).{8,32}\$"))
    }
}