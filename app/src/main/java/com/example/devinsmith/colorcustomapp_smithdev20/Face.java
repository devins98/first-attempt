package com.example.devinsmith.colorcustomapp_smithdev20;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import java.util.Random;

/**
 * <!--Class Face-->
 *     draws face based on input by user
 * Author @ Devin Smith on 2/7/18.
 *
 */

public class Face extends SurfaceView {
    private Paint hairColor;
    private Paint skinColor;
    private Paint eyeColor;
    private int hairStyle;

    private int redEyeNum;
    private int greenEyeNum;
    private int blueEyeNum;

    private int redHairNum;
    private int greenHairNum;
    private int blueHairNum;

    private int redSkinNum;
    private int greenSkinNum;
    private int blueSkinNum;

    private int redEyeProg;
    private int greenEyeProg;
    private int blueEyeProg;

    private int redHairProg;
    private int greenHairProg;
    private int blueHairProg;

    private int redSkinProg;
    private int greenSkinProg;
    private int blueSkinProg;

    private int buttonPressed;


    public Face(Context context) {
        super(context);
        generalInit();
    }

    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        generalInit();
    }

    public Face(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        generalInit();
    }

    /*
        Draws the face
     */
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint mouth = new Paint();
        mouth.setColor(Color.RED);
        Paint background = new Paint();
        background.setColor(Color.BLACK);

        eyeColor = new Paint();
        hairColor = new Paint();
        skinColor = new Paint();

        setEyeColor();
        setHairColor();
        setSkinColor();


        if(getColorSelect() == 1 ){
            random();
        }

        if (hairStyle == 1) {
            canvas.drawRect(0.0f, 0.0f, 800.0f, 1500.0f, background);
        }
        else if (hairStyle == 2) {
            drawLongHair(canvas);
        }
        else if (hairStyle == 3) {
            canvas.drawRect(0.0f, 0.0f, 800.0f, 1500.0f, background);
            drawShortHair(canvas);
        }


        drawSkin(canvas);

        canvas.drawArc(300.0f, 500.0f, 500.0f, 600.0f, 0f, 180f, true, mouth);

        drawEyes(canvas);

        buttonPressed = 0;

    }

    public void drawSkin(Canvas canvas){
        canvas.drawCircle(400.0f, 400.0f, 300.0f, skinColor);
    }

    public void drawEyes(Canvas canvas){
       canvas.drawCircle( 300.0f, 275.0f, 25.0f, eyeColor);
       canvas.drawCircle( 500.0f, 275.0f, 25.0f, eyeColor);
    }

    public void drawShortHair(Canvas canvas){
        canvas.drawArc(0.0f, 50.0f, 800.0f, 800.0f, 0f, -180, true, hairColor );
    }
    public void drawLongHair(Canvas canvas){
        canvas.drawArc(0.0f, 75.0f, 800.0f, 1300.0f, 0f, -180, true, hairColor );
    }

    //determines if random or not
    public void colorSelect( int isPressed){

        buttonPressed = isPressed;
        /**
         * External Citation
         * Problem: Surface view only redrew if tablet was rotated
         * Solution: invalidate()
         * Source: Mathew Yuen, CS tutor
         *
         */
        invalidate();
    }
    public int getColorSelect() {
        return this.buttonPressed;
    }

    //sets the spinner variable
    public void setSpinnerChoice(int spinnerChoice){
        this.hairStyle = spinnerChoice;
    }

    /*
       sets the color based on seekbar numbers
     */
    public void setEyeColor() {
        eyeColor.setColor(Color.rgb(getEyeRedNum(), getEyeGreenNum(), getEyeBlueNum()));
    }

    public void setSkinColor() {
        skinColor.setColor(Color.rgb(getSkinRedNum(), getSkinGreenNum(), getSkinBlueNum()));
    }

    public void setHairColor() {
        hairColor.setColor(Color.rgb(getHairRedNum(), getHairGreenNum(), getHairBlueNum()));
    }


    public void random(){
        Random rand = new Random();
        int redEye = rand.nextInt(256);
        redEyeProg = redEye;
        int greenEye = rand.nextInt(256);
        greenEyeProg = greenEye;
        int blueEye = rand.nextInt(256);
        blueEyeProg = blueEye;

        int redHair = rand.nextInt(256);
        redHairProg = redHair;
        int greenHair= rand.nextInt(256);
        greenHairProg = greenHair;
        int blueHair = rand.nextInt(256);
        blueHairProg = blueHair;

        int redSkin = rand.nextInt(256);
        redSkinProg = redSkin;
        int greenSkin = rand.nextInt(256);
        greenSkinProg = greenSkin;
        int blueSkin = rand.nextInt(256);
        blueSkinProg = blueSkin;


        eyeColor.setColor(Color.rgb(redEye, greenEye, blueEye));
        skinColor.setColor(Color.rgb(redHair, greenHair, blueHair));
        hairColor.setColor(Color.rgb(redSkin, greenSkin, blueSkin));
        hairStyle = rand.nextInt(3+1);
    }

    public String getHairStyle() {

        if (this.hairStyle == 1) {
            return "Bald";
        } else if (this.hairStyle == 2) {
            return "Long Hair";
        } else if (this.hairStyle ==3) {
            return "Short Hair";
        }
        else{
            return null;
        }
    }

    //takes values from seekbars and sets them
    private void generalInit() {
       setWillNotDraw(false);
    }

    //red eye value
    public void setEyeRedNum(int redValue) {
        redEyeNum = redValue;
        this.redEyeProg = redValue;
        invalidate();
    }
    public int getEyeRedNum() {
        return this.redEyeNum;
    }

    public int getRedEyeProg(){
        return redEyeProg;
    }

    //green eye value
    public void setEyeGreenNum(int greenValue) {
        greenEyeNum = greenValue;
        this.greenEyeProg = greenValue;
        invalidate();
    }
    public int getEyeGreenNum() {
        return this.greenEyeNum;
    }

    public int getGreenEyeProg(){
        return greenEyeProg;
    }

    //blue eye value
    public void setEyeBlueNum(int blueValue) {
        blueEyeNum = blueValue;
        blueEyeProg = blueValue;
        invalidate();
    }
    public int getEyeBlueNum() {
        return blueEyeNum;
    }

    public int getBlueEyeProg(){
        return this.blueEyeProg;
    }

    //red skin value
    public void setSkinRedNum(int redValue) {
        redSkinNum = redValue;
        redSkinProg = redValue;
        invalidate();
    }
    public int getSkinRedNum() {
        return this.redSkinNum;
    }

    public int getRedSkinProg(){
        return redSkinProg;
    }

    //green skin value
    public void setSkinGreenNum(int greenValue) {
        greenSkinNum = greenValue;
        greenSkinProg = greenValue;
        invalidate();
    }
    public int getSkinGreenNum() {
        return this.greenSkinNum;
    }

    public int getGreenSkinProg(){
        return this.greenSkinProg;
    }

    //blue skin value
    public void setSkinBlueNum(int blueValue) {
        blueSkinNum = blueValue;
        blueSkinProg = blueValue;
        invalidate();
    }
    public int getSkinBlueNum() {
        return this.blueSkinNum;
    }

    public int getBlueSkinProg(){
        return this.blueSkinProg;
    }

    //red hair value
    public void setHairRedNum(int redValue) {
        redHairNum = redValue;
        redHairProg = redValue;
        invalidate();
    }
    public int getHairRedNum() {
        return this.redHairNum;
    }

    public int getRedHairProg(){
        return this.redHairProg;
    }

    //green hair values
    public void setHairGreenNum(int greenValue) {
        greenHairNum = greenValue;
        greenHairProg = greenValue;
        invalidate();
    }
    public int getHairGreenNum() {
        return this.greenHairNum;
    }

    public int getGreenHairProg(){
        return this.greenHairProg;
    }

    //blue hair values
    public void setHairBlueNum(int blueValue) {
        blueHairNum = blueValue;
        blueHairProg = blueValue;
        invalidate();
    }
    public int getHairBlueNum() {
        return this.blueHairNum;
    }

    public int getBlueHairProg(){
        return this.blueHairProg;
    }

}
