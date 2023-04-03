package com.example.viikko10ver2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class AddUserActivity extends AppCompatActivity {

    private EditText firstNameInput;
    private EditText lastNameInput;
    private EditText emailInput;
    ArrayList<EditText> textFields;

    UserStorage userStorage = UserStorage.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        firstNameInput = findViewById(R.id.editFirstName);
        lastNameInput = findViewById(R.id.editLastName);
        emailInput = findViewById(R.id.editEmail);

        textFields = new ArrayList<>(Arrays.asList(firstNameInput, lastNameInput, emailInput));
    }

    public void addUser(View view) {
        int image = 0;
        String degreeProgram = "";
        RadioGroup rgStudyField = findViewById(R.id.rgStudyField);
        RadioGroup rgImageSelect = findViewById(R.id.rgImageSelect);

        CheckBox cbKandi = findViewById(R.id.cbKandi);
        CheckBox cbDi = findViewById(R.id.cbDi);
        CheckBox cbTkt = findViewById(R.id.cbTkt);
        CheckBox cbUima = findViewById(R.id.cbUimamaisteri);

        ArrayList<String> degrees = new ArrayList<>();
        //System.out.println("Metodissa ollaan!");

        //try {
        boolean allFieldsFilled;
        boolean emptyString = isEmptyStrings();
        allFieldsFilled = !emptyString;
        //System.out.println("'emptyString' ennen testiä: " + String.valueOf(emptyString));

        //System.out.println("'emptyString' testin jälkeen: " + emptyString);
        //System.out.println("'noEmptyFields' testin jälkeen: " + allFieldsFilled);

        switch (rgStudyField.getCheckedRadioButtonId()) {
            case R.id.rbEnTe:
                degreeProgram = "Energiatekniikka";
                break;
            case R.id.rbLaTe:
                degreeProgram = "Laskennallinen tekniikka";
                break;
            case R.id.rbSäTe:
                degreeProgram = "Sähkötekniikka";
                break;
            case R.id.rbTiTe:
                degreeProgram = "Tietotekniikka";
                break;
            default:
                Toast.makeText(this, "Valitse ensin ala.", Toast.LENGTH_LONG).show();
                System.out.println("Valitse ensin ala.");
                allFieldsFilled = false;
        }

        switch (rgImageSelect.getCheckedRadioButtonId()) {
            case R.id.rbKirby:
                image = R.drawable.kirby;
                break;
            case R.id.rbLink:
                image = R.drawable.link;
                break;
            case R.id.rbLuigi:
                image = R.drawable.luigi;
                break;
            case R.id.rbMario:
                image = R.drawable.mario;
                break;
            case R.id.rbNoImage:
                image = 0;
                break;
            default:
                Toast.makeText(this, "Valitse ensin kuva.", Toast.LENGTH_LONG).show();
                System.out.println("Valitse ensin kuva.");
                allFieldsFilled = false;
                break;
        }

        if (cbKandi.isChecked())  {
            degrees.add("Kandidaatin tutkinto");
        }   if (cbDi.isChecked())  {
            degrees.add("Diplomi-insinöörin tutkinto");
        }   if (cbTkt.isChecked())  {
            degrees.add("Tekniikan tohtorin tutkinto");
        }   if (cbUima.isChecked())  {
            degrees.add("Uimamaisteri");
        }


        if (allFieldsFilled) {
            String firstName = firstNameInput.getText().toString();
            String lastName = lastNameInput.getText().toString();
            String email = emailInput.getText().toString();
            userStorage.addUser(new User(firstName, lastName, email, degreeProgram, image, degrees));
            userStorage.saveUsers(this);
            Toast.makeText(this, "Käyttäjä tallennettu.", Toast.LENGTH_LONG).show();
            System.out.println("Käyttäjä tallennettu.");

        } else {
            if (emptyString) {
                Toast.makeText(this, "Täytä kaikki tekstikentät.", Toast.LENGTH_LONG).show();
                System.out.println("Täytä kaikki tekstikentät.");
            }
            Toast.makeText(this, "Käyttäjää ei tallennettu.", Toast.LENGTH_LONG).show();
            System.out.println("Käyttäjää ei tallennettu.");
        }

        //} catch (Exception e) {
        //Toast.makeText(this, "Täytä kaikki kentät oikein.", Toast.LENGTH_LONG).show();
        //System.out.println("Täytä kaikki kentät oikein. Tuntematon poikkeus: " + e.toString());
    //}

}

    public boolean isEmptyStrings() {
        boolean emptyString = false;
        for (EditText textField : textFields) {
            emptyString = textField.getText().toString().isEmpty();
            //System.out.println("Yksittäinen 'emptyString': " + String.valueOf(emptyString));
            if (emptyString) {
                break;
            }
        }
        return emptyString;
    }

}