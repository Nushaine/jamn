package com.example.jamn.Registration;

//this is to check the username, password, and email
public class Registration {

    //to check if the string is longer than eight characters
    public static boolean checkLength(String word) {

        if (word.length() < 8){
            return false;
        } else {
            return true;
        }
    }

    //to check if there is an uppercase letter in the string
    public static boolean checkUppercase(String word) {

        if (word.matches(".*[A-Z].*"))
            return true;
        else
            return false;
    }

    //to check of there is lowercase letter in the string
    public static boolean checkLowercase(String word) {

        if (word.matches(".*[a-z].*"))
            return true;
        else
            return false;
    }

    //to check if there is a number in the string
    public static boolean checkNumber(String word) {

        if (word.matches(".*[1-9].*"))
            return true;
        else
            return false;
    }

    //to check if the email is a valid email
    public static boolean checkEmail (String email){

        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

}


