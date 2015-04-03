package me.jordancarlson.isacceptance;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class MainActivity extends ActionBarActivity{

    @InjectView(R.id.genderSpinner)Spinner mGenderSpinner;
    @InjectView(R.id.is303Spinner)Spinner mIS303Spinner;
    @InjectView(R.id.gpa30EditText)EditText mGpa30EditText;
    @InjectView(R.id.gpaBYUEditText)EditText mGpaBYUEditText;
    @InjectView(R.id.gpaPrereqEditText)EditText mGpaPrereqEditText;
    @InjectView(R.id.is303EditText)EditText mGpaIs303EditText;
    @InjectView(R.id.gpa30HelperTextView)TextView mGpa30HelperTextView;
    @InjectView(R.id.gpaBYUHelperTextView)TextView mGpaBYUHelperTextView;
    @InjectView(R.id.gpaPrereqHelperTextView)TextView mGpaPrereqHelperTextView;
    @InjectView(R.id.is303HelperTextView)TextView mGpaIs303HelperTextView;

    public Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        Log.d("MainActivity", "Inside onCreate");

        // Set a toolbar to replace the action bar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Call some material design APIs here
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.primary_dark));
        } else {
            // Implement this feature without material design
        }


        mGpa30EditText.addTextChangedListener(new TextValidator(mGpa30EditText) {
            @Override
            public void validate(EditText editText, String text) {
                validateGpa(editText, text);
            }
        });
        mGpaBYUEditText.addTextChangedListener(new TextValidator(mGpaBYUEditText) {
            @Override
            public void validate(EditText editText, String text) {
                validateGpa(editText, text);
            }
        });
        mGpaPrereqEditText.addTextChangedListener(new TextValidator(mGpaPrereqEditText) {
            @Override
            public void validate(EditText editText, String text) {
                validateGpa(editText, text);
            }
        });
        mGpaIs303EditText.addTextChangedListener(new TextValidator(mGpaIs303EditText) {
            @Override
            public void validate(EditText editText, String text) {
                validateGpa(editText, text);
            }
        });

    }
    public void validateGpa(EditText editText, String text) {
        //validation goes  here
        String txt = text.toString();
        if (!txt.isEmpty()) {
            double d = Double.parseDouble(txt);

            if (d > 4.0 || d < 0) {
                //error
                editText.setTextColor(Color.parseColor("#FFFF0000"));
                editText.getBackground().setColorFilter(Color.parseColor("#FFFF0000"), PorterDuff.Mode.SRC_ATOP);
            } else {
                editText.setTextColor(Color.BLACK);
                editText.getBackground().setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
            }
        }
    }

    public void validationColors(String key, String value, EditText editText, TextView textView){
        if (!value.isEmpty()) {
            mIntent.putExtra(key, editText.getText().toString());
            textView.setTextColor(Color.parseColor("#80000000"));
            editText.getBackground().setColorFilter(Color.parseColor("#FF7e7e7e"), PorterDuff.Mode.SRC_ATOP);
        } else {
            textView.setTextColor(Color.parseColor("#FFFF0000"));
            editText.getBackground().setColorFilter(Color.parseColor("#FFFF0000"), PorterDuff.Mode.SRC_ATOP);
        }
    }

    @OnClick(R.id.submitButton)
    public void startResultsActivity(View view){
        String gpa30 = mGpa30EditText.getText().toString();
        String gpaByu = mGpaBYUEditText.getText().toString();
        String gpaPre = mGpaPrereqEditText.getText().toString();
        String gpaIs303 = mGpaIs303EditText.getText().toString();
        mIntent = new Intent(this, ResultsActivity.class);

        validationColors("30", gpa30, mGpa30EditText, mGpa30HelperTextView);
        validationColors("byu", gpaByu, mGpaBYUEditText, mGpaBYUHelperTextView);
        validationColors("pre", gpaPre, mGpaPrereqEditText, mGpaPrereqHelperTextView);
        validationColors("is", gpaIs303, mGpaIs303EditText, mGpaIs303HelperTextView);


        if (!gpa30.isEmpty() && !gpaByu.isEmpty() && !gpaPre.isEmpty() && !gpaIs303.isEmpty()) {
            mIntent.putExtra("gender", mGenderSpinner.getSelectedItem().toString());
            mIntent.putExtra("class", mIS303Spinner.getSelectedItem().toString());

            startActivity(mIntent);
        } else {
            Toast.makeText(this, "Please fill out everything before continuing.", Toast.LENGTH_LONG).show();
        }

    }
}
