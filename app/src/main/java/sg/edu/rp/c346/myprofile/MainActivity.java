package sg.edu.rp.c346.myprofile;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    EditText etGPA;
    RadioGroup rgGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.editTextName);
        etGPA = findViewById(R.id.editTextGPA);
        rgGender = findViewById(R.id.radioGroupGender);

    }

    @Override
    protected void onPause() {
        super.onPause();
        //Step 1: Get the user input from the EditText and store it in a variable
        String strName = etName.getText().toString();
        Float flGPA = Float.parseFloat(etGPA.getText().toString());
        Integer chGender = rgGender.getCheckedRadioButtonId();

        //Step 1a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Step 1b: Obtain an instance of the SharedPreference Editor for update later
        SharedPreferences.Editor prefEdit = prefs.edit();
        //Step 1c: Add the key-value pair
        prefEdit.putString("name", strName);
        prefEdit.putFloat("gpa", flGPA);
        prefEdit.putInt("gender", chGender);
        //Step 1d: Call commit() method to save the changes into the SharedPreferences
        prefEdit.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //Step 2a: Obtain an instance of the SharedPreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Step 2b: Retrieve the saved data with the key "greeting from the SharedPreferences object
        String name = prefs.getString("name", "Invalid Name!");
        Float gpa = prefs.getFloat("gpa", 0);
        Integer gender = prefs.getInt("gender", 0);

        etName.setText(name);
        etGPA.setText(gpa.toString());
        rgGender.check(gender);

        //test new line

        Toast toast = Toast.makeText(getApplicationContext(), "Name: " + name + "\nGPA: " + gpa + "\nGender: " + gender, Toast.LENGTH_LONG);
        toast.show();
    }
}
