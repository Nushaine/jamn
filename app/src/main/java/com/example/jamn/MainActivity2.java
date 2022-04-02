package com.example.jamn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jamn.Registration.Registration;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity2 extends AppCompatActivity {
    String email = "student@gmail.com";
    String username = "Student2022";
    String password = "Student2022";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        //setContentView(R.layout.registration);


    }

    // this is onClick for the create account button on the Registration page
    public void onClick(View view) {

        Boolean passwordCheck = false;
        Boolean usernameCheck = false;

        //these are to get the text from the boxes

        EditText usernameView = (EditText) findViewById(R.id.usernameBox2);
        String newUsername = usernameView.getText().toString();

        EditText passwordView = (EditText) findViewById(R.id.passwordBox2);
        String newPassword = passwordView.getText().toString();


        //to check the username
        if (Registration.checkLength(newUsername) == false) {
            ((TextView) findViewById(R.id.usernameError2)).setText("Username Must Be At Least Eight Characters");
        } else {
            ((TextView) findViewById(R.id.usernameError2)).setText("");
            usernameCheck = true;
        }


        //to check the password
        if (Registration.checkLength(newPassword) == false) {
            ((TextView) findViewById(R.id.passwordError2)).setText("Password Must Be At Least Eight Characters");
        } else if (Registration.checkUppercase(newPassword) == false) {
            ((TextView) findViewById(R.id.passwordError2)).setText("Password Must Include An Uppercase Letter");
        } else if (Registration.checkLowercase(newPassword) == false) {
            ((TextView) findViewById(R.id.passwordError2)).setText("Password Must Include A Lowercase Letter");
        } else if (Registration.checkNumber(newPassword) == false) {
            ((TextView) findViewById(R.id.passwordError2)).setText("Password Must Include A Number");
        } else {
            ((TextView) findViewById(R.id.passwordError2)).setText("");
            passwordCheck = true;
        }




        //if there is no issue with the new username, new email, new password then they are set as the username, password, email
        //this should also take the user to the login/home page
        if (passwordCheck == true && usernameCheck == true) {

            username = newUsername;
            password = newPassword;
            showSnackbar(view, "Account Created", Snackbar.LENGTH_LONG);
            setContentView(R.layout.home);
        }
    }

    //this method of for the little pop ups
    private void showSnackbar(View view, String message, int lengthLong) {
        Snackbar.make(view, message, lengthLong).show();
    }


    //this is for the create account button on the home/login page
    //should take user to registration page
    public void onClick2(View view) {

        setContentView(R.layout.registration);
    }


    //these are for when the user clicks the login button in the home/login page
    public void onClick3(View view) {


        //these are to get the text from the boxes
        EditText usernameView = (EditText) findViewById(R.id.usernameBox);
        String newUsername = usernameView.getText().toString();

        EditText passwordView = (EditText) findViewById(R.id.passwordBox);
        String newPassword = passwordView.getText().toString();


        //to check of the username inputted matched the username saved
        if (newUsername.equals(username)) {
            ((TextView) findViewById(R.id.usernameError)).setText("");
        } else {
            ((TextView) findViewById(R.id.usernameError)).setText("Wrong Username");
        }


        //to check if password entered matches the password saved
        if (newPassword.equals(password)) {
            ((TextView) findViewById(R.id.passwordError)).setText("");
        } else {
            ((TextView) findViewById(R.id.passwordError)).setText("Wrong Password");
        }


        //if both username and password matches, then the user will login
        if (newPassword.equals(password) && newUsername.equals(username)) {


            //at this point, login is successful and should take user to next page
            showSnackbar(view, "Login Successful", Snackbar.LENGTH_LONG);



            Intent intent = new Intent(this, MainActivity4.class);
            startActivity(intent);
        }
    }
}