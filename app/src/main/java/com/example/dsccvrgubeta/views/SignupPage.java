package com.example.dsccvrgubeta.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dsccvrgubeta.R;
import com.example.dsccvrgubeta.utils.PasswordStrengthCalculator;
import com.example.dsccvrgubeta.utils.StrengthLevel;

public class SignupPage extends AppCompatActivity {


    private EditText etName,etEmail,etPassword,etConfirmPassword;

    private TextView tvStrengthLevel,tvPasswordHint;

    private ProgressBar strengthLevelProgressBar;

    private ImageButton saveBTN;

    private  int color=R.color.weak;

    PasswordStrengthCalculator password1,password2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        etName=findViewById(R.id.signUpName);
        etEmail=findViewById(R.id.signUpEmail);
        etPassword=findViewById(R.id.signUpPassword);
        etConfirmPassword=findViewById(R.id.signUpConfirmPassword);

        tvStrengthLevel=findViewById(R.id.signUpStrengthLevel);
        tvPasswordHint=findViewById(R.id.signUpPasswordHint);

        strengthLevelProgressBar=findViewById(R.id.signUpProgressBar);

        saveBTN=findViewById(R.id.saveBTN);
        saveBTN.setEnabled(false);

         password1=new PasswordStrengthCalculator();
         password2=new PasswordStrengthCalculator();

        etPassword.addTextChangedListener(password1);
        etConfirmPassword.addTextChangedListener(password2);

        password1.strengthLevel.observe(this, new Observer<StrengthLevel>() {
            @Override
            public void onChanged(StrengthLevel strengthLevel) {
                tvStrengthLevel.setVisibility(View.VISIBLE);
                strengthLevelProgressBar.setVisibility(View.VISIBLE);
                displayStrengthLevel(strengthLevel,"p1");
            }
        });
        password1.strengthColour.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer!=null)
                    color=integer.intValue();
            }
        });

        password2.strengthLevel.observe(this, new Observer<StrengthLevel>() {
            @Override
            public void onChanged(StrengthLevel strengthLevel) {
                tvStrengthLevel.setVisibility(View.VISIBLE);
                strengthLevelProgressBar.setVisibility(View.VISIBLE);
                displayStrengthLevel(strengthLevel,"p2");
            }
        });
        password2.strengthColour.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer!=null)
                    color=integer.intValue();
            }
        });




    }

    private void displayStrengthLevel(StrengthLevel s,String check) {

        String p1=etPassword.getText().toString();
        String p2=etConfirmPassword.getText().toString();

        if (s==StrengthLevel.NOTHING)
        {
            tvStrengthLevel.setVisibility(View.INVISIBLE);
            strengthLevelProgressBar.setVisibility(View.INVISIBLE);
        }

        tvStrengthLevel.setText(s.name());

        tvStrengthLevel.setTextColor(ContextCompat.getColor(this,color));
        strengthLevelProgressBar.getProgressDrawable().setColorFilter(ContextCompat.getColor(this,color), PorterDuff.Mode.SRC_IN);
//        strengthLevelIndicator.setBackgroundColor(ContextCompat.getColor(this,color));


        if (s==StrengthLevel.WEAK)
        {
            strengthLevelProgressBar.setProgress(25);





            if (check.equals("p2"))
            {
                if (s!=StrengthLevel.NOTHING)
                {
                    if(p1.equals(p2))
                    {
                        tvPasswordHint.setText("password matched ! but Please enter a strong password.");
                        saveBTN.setImageResource(R.drawable.crossed);
                    }
                    else
                    {
                        tvPasswordHint.setText("Please enter a strong password.");
                        saveBTN.setImageResource(R.drawable.crossed);
                    }
                }
            }else
            {
                tvPasswordHint.setText("Please enter a strong password.");
                saveBTN.setImageResource(R.drawable.crossed);

            }

        }
        if (s==StrengthLevel.MEDIUM)
        {
            strengthLevelProgressBar.setProgress(50);


            if (check.equals("p2"))
            {
                if (s!=StrengthLevel.NOTHING)
                {
                    if(p1.equals(p2))
                    {
                        tvPasswordHint.setText("password matched ! but Please enter a little bit more strong password.");
                        saveBTN.setImageResource(R.drawable.crossed);
                    }
                    else
                    {
                        tvPasswordHint.setText("Please enter a little bit more strong password.");
                        saveBTN.setImageResource(R.drawable.crossed);
                    }
                }
            }else
            {
                tvPasswordHint.setText("Please enter a little bit more strong password.");
                saveBTN.setImageResource(R.drawable.crossed);

            }

        }
        if (s==StrengthLevel.AVERAGE)
        {
            strengthLevelProgressBar.setProgress(75);


            if (check.equals("p2"))
            {
                if (s!=StrengthLevel.NOTHING)
                {
                    if(p1.equals(p2))
                    {
                        tvPasswordHint.setText("password matched ! but try to secure it more.");

                        saveBTN.setImageResource(R.drawable.tick_inside_circle);
                        saveBTN.setEnabled(true);
                    }
                    else
                    {
                        tvPasswordHint.setText("You are almost there.");
                        saveBTN.setImageResource(R.drawable.crossed);
                    }
                }
            }else
            {
                tvPasswordHint.setText("You are almost there");
                saveBTN.setImageResource(R.drawable.crossed);

            }

        }
        if (s==StrengthLevel.STRONG)
        {
            strengthLevelProgressBar.setProgress(100);



            if (check.equals("p2"))
            {
                if (s!=StrengthLevel.NOTHING)
                {
                    if(p1.equals(p2))
                    {
                        tvPasswordHint.setText("We are ready to kick off into a whole " +
                                "new world.");
                        saveBTN.setImageResource(R.drawable.tick_inside_circle);
                        saveBTN.setEnabled(true);
                    }
                    else
                    {
                        tvPasswordHint.setText("Strong! but password not matched.");
                        saveBTN.setImageResource(R.drawable.crossed);
                    }
                }
            }else
            {
                tvPasswordHint.setText("Strong like Bullet Proof");
                saveBTN.setImageResource(R.drawable.crossed);

            }

        }


    }

    public void SaveData(View view) {
        if(!validateFullName() |  !validateEmail())
            return;

        String name=etName.getText().toString().trim();
        String email=etEmail.getText().toString().trim();
        String password=etPassword.getText().toString().trim();


        Toast.makeText(this, "Name: "+name+"\n email: "+email
                , Toast.LENGTH_SHORT).show();
    }

    private boolean validateFullName()
    {
        String val=etName.getText().toString().trim();
        if(val.isEmpty())
        {
            etName.setError("field cannot be empty");
            return false;
        }
        else {
            etName.setError(null);

            return true;

        }
    }
    private boolean validateEmail()
    {
        String val=etEmail.getText().toString().trim();
        String checkEmail="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty())
        {
            etEmail.setError("field cannot be empty");
            return false;
        }

        else if (!val.matches(checkEmail))
        {
            etEmail.setError("Invalid Email");
            return false;
        }
        else {
            etEmail.setError(null);

            return true;

        }
    }
}