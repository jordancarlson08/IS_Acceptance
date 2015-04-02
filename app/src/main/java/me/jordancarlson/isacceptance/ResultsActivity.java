package me.jordancarlson.isacceptance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class ResultsActivity extends Activity {

    @InjectView(R.id.resultsId) TextView mResultsId;
    @InjectView(R.id.textView) TextView mText;
    public String mResult;
    public String mSubText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.inject(this);

        Intent intent = getIntent();
        String gender = intent.getStringExtra("gender");
        int genderInt;
        String isClass = intent.getStringExtra("class");
        int isys303;
        int cs142;

        double gpa30 = Double.parseDouble(intent.getStringExtra("30"));
        double byu = Double.parseDouble(intent.getStringExtra("byu"));
        double pre = Double.parseDouble(intent.getStringExtra("pre"));
        double is = Double.parseDouble(intent.getStringExtra("is"));

        if (gender.equals("Male")) {
            genderInt = 0;
        } else {
            genderInt = 1;
        }

        if (isClass.equals("IS 303")) {
            isys303 = 0;
            cs142 = 0;
        } else if (isClass.equals("I SYS 303")) {
            isys303 = 1;
            cs142 = 0;
        } else {
            isys303 = 0;
            cs142 = 1;
        }

        double logit;

        logit = -34.1 + (0.381 * genderInt) + (1.725 * gpa30) + (2.745 * byu) + (3.4 * pre) + (2.136 * is) + (1.211 * cs142) + (1.446 * isys303);

        final double p = 1 / (1 + (Math.pow(2.718,-logit)));

        Log.e(">>>>>>>RESULTS>>>>", p+"");

        double pRound = Math.round(p * 100.0) / 100.0;


        if (p > 0.7394) {
            mResult = "accepted!";
            mSubText = "Hopefully we are right! :)";
        } else {
            mResult = "rejected. Sorry about that.";
            mSubText = "We could be wrong though. Your predicted probability was " + pRound + ". The cut off is .75 ";
        }



        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mResultsId.setText(String.format(getResources().getString(R.string.results_string), mResult));
                mText.setText(mSubText);

            }
        });



    }
}
