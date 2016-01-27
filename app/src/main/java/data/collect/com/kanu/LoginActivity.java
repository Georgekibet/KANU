package data.collect.com.kanu;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    @Bind(data.collect.com.kanu.R.id.password) EditText editTextpassword;
    @Bind(data.collect.com.kanu.R.id.submit_button) Button loginButton;
    @Bind(data.collect.com.kanu.R.id.email) AutoCompleteTextView autoCompleteTextViewEmail;
    @Bind(data.collect.com.kanu.R.id.register)TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(data.collect.com.kanu.R.layout.login_layout);
        ButterKnife.bind(this);
        Button button= (Button)findViewById(data.collect.com.kanu.R.id.submit_button);
        //addNoteButton.PaintFlags = addNoteButton.PaintFlags | PaintFlags.UnderlineText;
       // addNoteButton.SetTextColor(Resources.GetColor(Resource.Color.color_accent));
             register.setPaintFlags(register.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
             register.setTextColor(getResources().getColor(android.R.color.primary_text_dark));
        register.setOnClickListener(onLoginClick());

        loginButton.setOnClickListener(onLoginClick());
    }

    @NonNull
    public View.OnClickListener onLoginClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(view.getId()== data.collect.com.kanu.R.id.register){
                    startActivity(new Intent(LoginActivity.this,SignupActivity.class));
                return;}
                  if(autoCompleteTextViewEmail.getText().toString().trim().length()<=1){
                      autoCompleteTextViewEmail.setError("Cannot be empty");
                      return;
                  }
                         if( editTextpassword.getText().toString().trim().length()<=1){
                             editTextpassword.setError("Cannot be empty");
                             return;
                         }

                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        };
    }

}
