package me.jordancarlson.isacceptance;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.genderSpinner)Spinner mGenderSpinner;
    @InjectView(R.id.is303Spinner)Spinner mIS303Spinner;
    @InjectView(R.id.gpa30EditText)EditText mGpa30EditText;
    @InjectView(R.id.gpaBYUEditText)EditText mGpaBYUEditText;
    @InjectView(R.id.gpaPrereqEditText)EditText mGpaPrereqEditText;
    @InjectView(R.id.is303EditText)EditText mGpaIs303EditText;
    @InjectView(R.id.submitButton)Button mSubmitButton;
    public Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        Log.d("MainActivity", "Inside onCreate");
    }

    @OnClick(R.id.submitButton)
    public void startResultsActivity(View view){
        Log.d("MainActivity", "Inside OnClick");
        if (mGenderSpinner != null && mIS303Spinner != null && mGpa30EditText != null && mGpaBYUEditText != null && mGpaPrereqEditText != null && mGpaIs303EditText!= null) {
            Log.d("MainActivity", "Inside If");
            mIntent = new Intent(this, ResultsActivity.class);
            mIntent.putExtra("gender", mGenderSpinner.getSelectedItem().toString());
            mIntent.putExtra("class", mIS303Spinner.getSelectedItem().toString());
            mIntent.putExtra("30", mGpa30EditText.getText().toString());
            mIntent.putExtra("byu", mGpaBYUEditText.getText().toString());
            mIntent.putExtra("pre", mGpaPrereqEditText.getText().toString());
            mIntent.putExtra("is", mGpaIs303EditText.getText().toString());
            startActivity(mIntent);
        } else {
            Log.d("MainActivity", "Inside else");
            Toast.makeText(this, "Please fill out everything before continuing.", Toast.LENGTH_LONG).show();
        }

    }
}
