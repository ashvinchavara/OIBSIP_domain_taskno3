package com.example.calc;

import static java.lang.String.copyValueOf;
import static java.lang.String.valueOf;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    int isOn = 0, isSec = 0, i = 0, isDec1 = 0, isDec2 = 0;
    float num1, num2;
    String in = "", op = "", num = "", oper = "";
    Button buttonon, buttonper, buttonSign, buttonroot, button7, button8, button9, buttondiv, button4, button5, button6, buttonmul, button1, button2, button3, buttonsub, button0, buttonequal,buttonadd, buttondot;
    LinearLayout output_text, stand, output_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        output_text = findViewById(R.id.output_text);
        stand = findViewById(R.id.stand_text);
        stand.setVisibility(View.INVISIBLE);
        output_view = findViewById(R.id.output_view);
        buttonon = findViewById(R.id.buttonon);
        buttonon.setOnClickListener(this);
        buttonper = findViewById(R.id.buttonPer);
        buttonper.setOnClickListener(this);
        button1 = findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(this);
        button5 = findViewById(R.id.button5);
        button5.setOnClickListener(this);
        button6 = findViewById(R.id.button6);
        button6.setOnClickListener(this);
        button7 = findViewById(R.id.button7);
        button7.setOnClickListener(this);
        button8 = findViewById(R.id.button8);
        button8.setOnClickListener(this);
        button9 = findViewById(R.id.button9);
        button9.setOnClickListener(this);
        button0 = findViewById(R.id.button0);
        button0.setOnClickListener(this);
        buttondot = findViewById(R.id.buttondot);
        buttondot.setOnClickListener(this);
        buttonadd = findViewById(R.id.buttonadd);
        buttonadd.setOnClickListener(this);
        buttonsub = findViewById(R.id.buttonsub);
        buttonsub.setOnClickListener(this);
        buttonmul = findViewById(R.id.buttonmul);
        buttonmul.setOnClickListener(this);
        buttondiv = findViewById(R.id.buttondiv);
        buttondiv.setOnClickListener(this);
        buttonroot = findViewById(R.id.buttonroot);
        buttonroot.setOnClickListener(this);
        buttonequal = findViewById(R.id.buttoneq);
        buttonequal.setOnClickListener(this);
        buttonSign = findViewById(R.id.buttonSign);
        buttonSign.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        String btnText = btn.getText().toString();


        if (btnText.equals("ON/OFF")) {
            if(isOn ==0){
                isOn =1;
                stand.setVisibility(View.VISIBLE);
            }
            else {
                output_text.removeAllViews();
                output_view.removeAllViews();
                isSec = 0;
                in = "";
                num = "";
                num1 = 0;
                num2 = 0;
                oper = "";
                op = "";
                isDec1 = 0;

            }
        }
        else if (isOn == 1) {
            if ("%√+-=×÷".contains(btnText)) {
                oper = btnText;
                if (!("=√").contains(btnText) && !valueOf(num1).isEmpty()) {
                    i++;
                    isSec = 1;
                    isDec1 = 0;
                    op(btnText);
                } else if (valueOf(num1).isEmpty() && !btnText.equals("=")) {
                    op(btnText);
                    i++;
                } else
                    op(btnText);
            } else if (btnText.equals(".")) {
                isDec1 = 1;
                in(".");
                in = in + btnText;

            } else if (btnText.equals("sign") && num1 != 0) {
                op(btnText);

            } else if (btnText.equals("sign") && num1 == 0) {
                Toast.makeText(this,"Enter a number", Toast.LENGTH_SHORT).show();

            } else {
                if (isSec == 1) {
                    num1 = 0;
                    output_text.removeAllViews();
                    isSec = 0;
                }
                num1 = num1 * 10 + java.lang.Integer.parseInt(btnText);
                if (isDec1 == 1)
                    num1 = num1 * ((float) 1 / 10);
                in(btnText);
                if (isDec1 == 1)
                    in = num + " " + num1;
                else
                    in = "(" + num + " " + oper + " " + num1 + ")";
            }
            if (output_view.getChildCount() > 0)
                output_view.removeViewAt(output_view.getChildCount() - 1);
            inView(in);
        }
    }

    public void op(String op1){

        num = in;
        if ("sign".equals(op1)){
            if (num1 != 0) {
                num1 *= -1;
                num2 = num1;
                isSec = 1;
            }
        }
        else if(op.isEmpty()) {
            num2 = num1;
            op = op1;
        }
        else {
            switch (op) {
                case "+":
                    num2 = num1 + num2;
                    break;
                case "-":
                    num2 = num2 - num1;
                    break;
                case "×":
                    num2 = num1 * num2;
                    break;
                case "÷":
                    num2 = num2 / num1;
                    break;
                case "%":
                    num2 = (num2 / num1) * 100;
                    break;
                case "√":
                    num2 = (float) Math.sqrt(num1);
            }
            op = op1;
        }
        output_text.removeAllViews();
        for (char c : String.valueOf(num2).toCharArray()) {
            in(String.valueOf(c));
        }
        if("=".equals(op1)){
            output_text.removeAllViews();
            in = "Ans = " + num2;
            op = "";
            num = "";
            for (char c : String.valueOf(num2).toCharArray()) {
                in(String.valueOf(c));
            }
            isSec = 2;
            inView(in);
            in = "";
            num1 = 0;
            num2 = 0;
            oper = "";
            inView(in);
        }
    }
    public void in(String in){
        float width = 33f;
        float height = 64f;
        int widthInPixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, width, getResources().getDisplayMetrics());
        int heightInPixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, getResources().getDisplayMetrics());
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(widthInPixels,heightInPixels);
        ImageView inDig = new ImageView(this);
        inDig.setLayoutParams(layoutParams);
        if(in.equals("-")) {
            inDig.setImageResource(getResources().getIdentifier("digit_minus", "drawable", getPackageName()));
            output_text.addView(inDig);
        }
        else if (in.equals("."))
            isDec2 = 1;
        else if (isDec2 == 1) {
            inDig.setImageResource(getResources().getIdentifier("dec_digit" + in, "drawable", getPackageName()));
            output_text.addView(inDig);
            isDec2 = 0;
        }
        else {
            inDig.setImageResource(getResources().getIdentifier("digit" + in, "drawable", getPackageName()));
            output_text.addView(inDig);
        }

    }
    public void inView(String s){
        TextView text = new TextView(this);
        text.setTextSize(30);
        text.setGravity(Gravity.END);
        text.setTextColor(getColor(R.color.black));
        text.setText(s);
        output_view.addView(text);
    }
}