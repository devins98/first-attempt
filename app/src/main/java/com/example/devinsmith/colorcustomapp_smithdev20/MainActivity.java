package com.example.devinsmith.colorcustomapp_smithdev20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
/**
 * <!--Main Activity-->
 *     Author
 *     @ Devin Smith
 *     @Version 2-14-18
 *
 */


public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemSelectedListener{

    private String[] hairStyles = {"Bald", "Long Hair", "Short Hair"};
    private Spinner spinnerHairStyle;

    private RadioGroup choice;
    private RadioButton eyeButton;
    private RadioButton hairButton;
    private RadioButton skinButton;

    private Button randomButton;

    private Face myFace;

    private TextView redVal;
    private TextView greenVal;
    private TextView blueVal;

    private SeekBar redBar;
    private SeekBar blueBar;
    private SeekBar greenBar;


    /*
        this method calls face depending on seekbar, random, and radio button input
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myFace = (Face)findViewById(R.id.surfaceView);

        //button randomizes face when pressed
        randomButton = (Button)findViewById(R.id.randomFaceButton);

        //spinner to select hairstyle
        final ArrayAdapter<String> hairAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, hairStyles);
        spinnerHairStyle = (Spinner) findViewById(R.id.hairStyleSpinner);
        spinnerHairStyle.setAdapter(hairAdapter);


        //text views that display value of each color
        redVal = (TextView) findViewById(R.id.textViewRedVal);
        blueVal = (TextView) findViewById(R.id.textViewBlueVal);
        greenVal = (TextView) findViewById(R.id.textViewGreenVal);

        //seekbars to select color
        redBar = (SeekBar) findViewById(R.id.seekBarRed);
        greenBar = (SeekBar) findViewById(R.id.seekBarGreen);
        blueBar = (SeekBar) findViewById(R.id.seekBarBlue);

        //radio button to select which feature to edit
        eyeButton = (RadioButton) findViewById(R.id.radioButtonEyes);
        hairButton = (RadioButton) findViewById(R.id.radioButtonHair);
        skinButton = (RadioButton) findViewById(R.id.radioButtonSkin);

        choice = (RadioGroup) findViewById(R.id.radioGroup);

        spinnerHairStyle.setOnItemSelectedListener(this);


        /*
            External Citation:
            Problem: I was not sure how to implement the seekbar listener
            Solution: I used some of the code in a youtube tutorial
            Source: https://www.youtube.com/watch?v=G03ZR0jKtVs&index=2&list=LL04b_8vL5k_x4Hm9KjPE4gw


            External Citation:
            Problem: I was having issues with radioButtons

            Solution: I needed radio group
            Source: https://www.youtube.com/watch?v=cTlWwuAvRpE&index=1&t=11s&list=LL04b_8vL5k_x4Hm9KjPE4gw

                    https://stackoverflow.com/questions/8323778/how-to-set-onclicklistener-on-a-radiobutton-in-android

            Solution: not declaring radioButtons as final
            Source: Mathew Yuen, CS tutor
         */

        //changes color based on seekbar progress
        spinnerHairStyle.setOnItemSelectedListener(this);


        //red seekbar
        redBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                redVal.setText("" + progress);
                if(eyeButton.isChecked()){
                    myFace.setEyeRedNum(progress);
                }
                else if(hairButton.isChecked()){
                    if(spinnerHairStyle.getSelectedItem()== "Bald"){
                        myFace.setSpinnerChoice(1);
                    }
                    else if(spinnerHairStyle.getSelectedItem()== "Long Hair"){
                        myFace.setSpinnerChoice(2);
                    }
                    else
                    {
                        myFace.setSpinnerChoice(3);
                    }
                    myFace.setHairRedNum(progress);
                }
                else{
                    myFace.setSkinRedNum(progress);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        //green seekbar
        greenBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                greenVal.setText("" + progress);

                if(eyeButton.isChecked()){
                    myFace.setEyeGreenNum(progress);
                }
                else if(hairButton.isChecked()){
                    myFace.setHairGreenNum(progress);
                }
                else {
                    myFace.setSkinGreenNum(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        //blues seekbar
        blueBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
               blueVal.setText("" + progress);

                if(eyeButton.isChecked()){
                    myFace.setEyeBlueNum(progress);
                }
                else if(hairButton.isChecked()){
                    myFace.setHairBlueNum(progress);
                }
                else {
                    myFace.setSkinBlueNum(progress);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        choice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(eyeButton.isChecked()){
                    redBar.setProgress(myFace.getRedEyeProg());
                    blueBar.setProgress(myFace.getBlueEyeProg());
                    greenBar.setProgress(myFace.getGreenEyeProg());
                }
                else if(hairButton.isChecked()){
                    redBar.setProgress(myFace.getRedHairProg());
                    blueBar.setProgress(myFace.getBlueHairProg());
                    greenBar.setProgress(myFace.getGreenHairProg());
                }
                else {
                    redBar.setProgress(myFace.getRedSkinProg());
                    blueBar.setProgress(myFace.getBlueSkinProg());
                    greenBar.setProgress(myFace.getGreenSkinProg());
                }
            }
        });

        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (randomButton.isPressed()) {
                    myFace.colorSelect(1);

                }
            }
        });
    }

    @Override
    public void onClick(View view) {
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
