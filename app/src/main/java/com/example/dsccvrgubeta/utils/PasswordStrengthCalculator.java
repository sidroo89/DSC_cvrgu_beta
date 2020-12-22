package com.example.dsccvrgubeta.utils;


import android.text.Editable;
import android.text.TextWatcher;

import androidx.lifecycle.MutableLiveData;

import com.example.dsccvrgubeta.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordStrengthCalculator implements TextWatcher {

    public MutableLiveData<StrengthLevel> strengthLevel=new MutableLiveData<>();

    public MutableLiveData<Integer> strengthColour=new MutableLiveData<>();

    MutableLiveData<Integer>  lowerCase=new MutableLiveData<>(0);
    MutableLiveData<Integer>  upperCase=new MutableLiveData<>(0);
    MutableLiveData<Integer>  digit=new MutableLiveData<>(0);
    MutableLiveData<Integer>  specialCharacter=new MutableLiveData<>(0);




    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }
    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onTextChanged(CharSequence chars, int i, int i1, int i2) {

        if (chars!=null)
        {

            if (hasLowerCase(chars))
            {
                lowerCase.setValue(1);
            }
            else {
                lowerCase.setValue(0);
            }

            if (hasUpperCase(chars))
            {
                upperCase.setValue(1);
            }
            else {
                upperCase.setValue(0);
            }

            if (hasDigit(chars))
            {
                digit.setValue(1);
            }
            else {
                digit.setValue(0);
            }

            if (hasSpecialChar(chars))
            {
                specialCharacter.setValue(1);
            }
            else {
                specialCharacter.setValue(0);
            }

            calculateStrength(chars);

        }





    }

    private void calculateStrength(CharSequence password) {

        if (password.length()==0)
        {
            strengthColour.setValue(R.color.weak);
            strengthLevel.setValue(StrengthLevel.NOTHING);

        }
        if (password.length()>0 && password.length()<=4)
        {
            strengthColour.setValue(R.color.weak);
            strengthLevel.setValue(StrengthLevel.WEAK);

        }
        else if(password.length()>=5 && password.length()<=8)
        {
            if(lowerCase.getValue()==1 || upperCase.getValue()==1 || digit.getValue()==1 || specialCharacter.getValue()==1)
            {
                strengthColour.setValue(R.color.medium);
                strengthLevel.setValue(StrengthLevel.MEDIUM);

            }
        }else if (password.length()>=9 && password.length()<=12)
        {
            if(lowerCase.getValue()==1 || upperCase.getValue()==1 || digit.getValue()==1 || specialCharacter.getValue()==1)
            {
                if (lowerCase.getValue()==1 && upperCase.getValue()==1)
                {
                    strengthColour.setValue(R.color.strong);
                    strengthLevel.setValue(StrengthLevel.AVERAGE);

                }
            }

        }else if(password.length()>=13)
        {
            if (lowerCase.getValue()==1 && upperCase.getValue()==1 && digit.getValue()==1 && specialCharacter.getValue()==1)
            {
                strengthColour.setValue(R.color.bulletproof);
                strengthLevel.setValue(StrengthLevel.STRONG);
            }
        }
    }


    boolean hasLowerCase(CharSequence chars)
    {
        Pattern pattern=Pattern.compile("[a-z]");

        Matcher hasLowerCase=pattern.matcher(chars);
        return  hasLowerCase.find();

    }
    boolean hasUpperCase(CharSequence chars)
    {
        Pattern pattern=Pattern.compile("[A-Z]");

        Matcher hasUpperCase=pattern.matcher(chars);
        return  hasUpperCase.find();

    }

    boolean hasDigit(CharSequence chars)
    {
        Pattern pattern=Pattern.compile("[0-9]");

        Matcher hasDigit=pattern.matcher(chars);
        return  hasDigit.find();

    }
    boolean hasSpecialChar(CharSequence chars)
    {
        Pattern pattern=Pattern.compile("[!@#$%^&*()_=+{}/.<>|\\[\\]~-]");

        Matcher hasSpecialChar=pattern.matcher(chars);
        return  hasSpecialChar.find();

    }




}
