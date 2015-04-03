package me.jordancarlson.isacceptance;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Jordan on 4/3/2015.
 */
public abstract class TextValidator implements TextWatcher {
    private final EditText mEditText;

    public TextValidator(EditText editText) {
        this.mEditText = editText;
    }

    public abstract void validate(EditText editText, String text);

    @Override
    final public void afterTextChanged(Editable s) {
        String text = mEditText.getText().toString();
        validate(mEditText, text);
    }

    @Override
    final public void beforeTextChanged(CharSequence s, int start, int count, int after) { /* Don't care */ }

    @Override
    final public void onTextChanged(CharSequence s, int start, int before, int count) { /* Don't care */ }
}