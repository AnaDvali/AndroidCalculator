package com.example.ana.calculator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    private TextView screen;
    private String display="";
    private String operator="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screen = (TextView)findViewById(R.id.screen);
        screen.setText(display);
    }

    public void getNumbers(View view){
        Button b = (Button)view;
        display+=b.getText().toString();
        screen.setText(display);
    }

    public String lastCharacter(){
        String last = display.substring(display.length() - 1);
        return last;
    }

    public void getOperator(View view){
        Button b = (Button)view;
        if(display.isEmpty()){
            display = "";
        }
        else if(!(lastCharacter().equals("+")||lastCharacter().equals("-")
                ||lastCharacter().equals("*")||lastCharacter().equals("/"))){
            display += b.getText();
            operator = b.getText().toString();
        }
        screen.setText(display);
    }

    private double calculations(String first, String second, String operation){
        switch(operation){
            case "+": return Double.valueOf(first) + Double.valueOf(second);
            case "-": return Double.valueOf(first) - Double.valueOf(second);
            case "*": return Double.valueOf(first) * Double.valueOf(second);
            case "/": return Double.valueOf(first) / Double.valueOf(second);
                default: return -1;
        }
    }

    public void equals(View view){
        String[] op = display.split(Pattern.quote(operator));
        Double res = calculations(op[0], op[1], operator);
        screen.setText(String.valueOf(res));
        display = res.toString();
    }

    public void delete(View view){
        display="";
        operator="";
        screen.setText(display);
    }
}
