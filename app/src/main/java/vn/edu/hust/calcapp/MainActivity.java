package vn.edu.hust.calcapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvResult;
    TextView opDisplay;


    int op1, op2;   // gia tri 2 toan hang
    double double1, double2;
    int op;         // 1: add, 2: sub, 3: mul, 4: div
    int state;      // 1: nhap op1, 2: nhap op2
    boolean hasDecimal = false;
    boolean error = false;
    int temp1;
    double temp2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.text_result);
        opDisplay = findViewById(R.id.operatorDisplay);

        findViewById(R.id.btn_0).setOnClickListener(this);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.btn_6).setOnClickListener(this);
        findViewById(R.id.btn_7).setOnClickListener(this);
        findViewById(R.id.btn_8).setOnClickListener(this);
        findViewById(R.id.btn_9).setOnClickListener(this);

        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_sub).setOnClickListener(this);
        findViewById(R.id.btn_mul).setOnClickListener(this);
        findViewById(R.id.btn_div).setOnClickListener(this);

        findViewById(R.id.btn_equal).setOnClickListener(this);
        findViewById(R.id.btn_dot).setOnClickListener(this);

        findViewById(R.id.btn_rev).setOnClickListener(this);
        findViewById(R.id.btn_bs).setOnClickListener(this);
        findViewById(R.id.btn_ce).setOnClickListener(this);
        findViewById(R.id.btn_c).setOnClickListener(this);

        // Khoi tao cac gia tri
        op1 = 0;
        op2 = 0;
        double1 = 0;
        double2 = 0;
        state = 1;
        op = 0;

        tvResult.setText(String.valueOf(op1));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_0:
                addValue(0);
                break;
            case R.id.btn_1:
                addValue(1);
                break;
            case R.id.btn_2:
                addValue(2);
                break;
            case R.id.btn_3:
                addValue(3);
                break;
            case R.id.btn_4:
                addValue(4);
                break;
            case R.id.btn_5:
                addValue(5);
                break;
            case R.id.btn_6:
                addValue(6);
                break;
            case R.id.btn_7:
                addValue(7);
                break;
            case R.id.btn_8:
                addValue(8);
                break;
            case R.id.btn_9:
                addValue(9);
                break;
            case R.id.btn_add:
                selectOperator(1);
                break;
            case R.id.btn_sub:
                selectOperator(2);
                break;
            case R.id.btn_mul:
                selectOperator(3);
                break;
            case R.id.btn_div:
                selectOperator(4);
                break;
            case R.id.btn_equal:
                calcResult();
                break;
            case R.id.btn_rev:
                reverseOperand();
                break;
            case R.id.btn_dot:
                addDecimal();
                break;
            case R.id.btn_bs:
                removeDigit();
                break;
            case R.id.btn_ce:
                clearOperand();
                break;
            case R.id.btn_c:
                clearOperator();
                break;
        }


    }

    private void  addDecimal() {
                //op1 = 0;
        String tmp = tvResult.getText()+"";
        if (tmp.contains(".")) tvResult.setText(tmp+"");
        else tvResult.setText(tmp+".");
//        if (state == 1) {
//            double1 = Double.parseDouble(tmp)
//        } else  {
//
//        }

    }

    private void clearOperator() {
        // Khoi tao cac gia tri
        op1 = 0;
        op2 = 0;
        double1 = 0;
        double2 = 0;
        temp1 = 0;
        temp2 = 0;
        state = 1;
        op = 0;
        error = false;
        tvResult.setText(String.valueOf(op1));
        opDisplay.setText(String.valueOf(op1));
    }

    private void clearOperand() {
        if (state == 1) {
            op1 = 0;
            double1 = 0;
            tvResult.setText(String.valueOf(op1));
        } else {
            op2 = 0;
            double2 = 0;
            tvResult.setText(String.valueOf(op2));
        }
    }

    private void removeDigit() {
        String tmp = tvResult.getText()+"";
        if (state == 1) {
            op1 = op1 / 10;
            if (tmp.contains(".")) {
                String s = tmp.substring(0, tmp.length() - 1);
                double1 = Double.parseDouble(s);
                tvResult.setText(s);
            }
            else tvResult.setText(String.valueOf(op1));
        } else {
            if (tmp.contains(".")) {
                String s = tmp.substring(0, tmp.length() - 1);
                double2 = Double.parseDouble(s);
                tvResult.setText(s);
            }
            else tvResult.setText(String.valueOf(op2));
        }
    }

    private void reverseOperand() {
        if (state == 1) {
            op1 = -op1;
            tvResult.setText(String.valueOf(op1));
        } else {
            op2 = -op2;
            tvResult.setText(String.valueOf(op2));
        }
    }

    private void calcResult() {
        int result = 0;
        double result2 = 0;
        String tmp = opDisplay.getText()+"";
        String tmp2 = tvResult.getText()+"";
        String ops = " +-*/";
        if (tmp2.contains(".")) {
            switch (op) {
                case 1: result2 = double1 + double2; break;
                case 2: result2 = double1 - double2; break;
                case 3: result2 = double1 * double2; break;
                case 4: {
                    if (double2 != 0) {result2 = double1 / double2; }
                    else error = true;
                    break;
                }
                case 0: result2 = Double.parseDouble(tmp2);break;
            }
        }
        else {
            switch (op) {
                case 1: result = op1 + op2; break;
                case 2: result = op1 - op2; break;
                case 3: result = op1 * op2; break;
                case 4: {
                    if (op2 != 0) {result = op1 / op2; }
                    else error = true;
                    break;
                }
                case 0: result = Integer.parseInt(tmp2);break;
            }
        }

        temp1 = result;
        temp2 = result2;
        if (op==0) {
            if (tmp2.contains(".")) opDisplay.setText(String.valueOf(result2));
            else opDisplay.setText(String.valueOf(result));
        }
        else {
            if (tmp2.contains(".")) opDisplay.setText(Double.toString(double1) +ops.charAt(op)+Double.toString(double2));
            else  opDisplay.setText(String.valueOf(op1)+ops.charAt(op)+String.valueOf(op2));
        }
        if (tmp2.contains(".")) tvResult.setText(error ? "ERROR" :String.valueOf(result2));
        else tvResult.setText(error ? "ERROR" :String.valueOf(result));

        state = 1;
        op1 = op2 = 0;
        double1 = double2 = 0;
        op = 0;
    }

    private void selectOperator(int p) {
        String tmp = opDisplay.getText()+"";
        if (tmp.contains("+") || tmp.contains("-") || tmp.contains("*") || tmp.contains("/")) {op1 = temp1;double1 = temp2;}
        op = p;
        state = 2;
        String ops = " +-*/";
        op2 = 0;
        double2 = 0;
        String tmp2 = tvResult.getText()+"";
        if (tmp2.contains(".")) {
            double1 = Double.parseDouble(tmp2);
            tvResult.setText(Double.toString(double2));
            opDisplay.setText(tmp+ops.charAt(op));
        }else {
            tvResult.setText(String.valueOf(op2));
            opDisplay.setText(tmp+ops.charAt(op));
        }

    }

    private void addValue(int c) {
        if (state == 1) {

            String tmp = tvResult.getText()+String.valueOf(c);
            if (tmp.contains(".")) {
                double1 = Double.parseDouble(tmp);
                tvResult.setText(String.valueOf(double1));
            } else {
                op1 = Integer.parseInt(tmp);
                tvResult.setText(String.valueOf(op1));
            }
        } else {
            String tmp = tvResult.getText()+String.valueOf(c);
            if (tmp.contains(".")) {
                double2 = Double.parseDouble(tmp);
                tvResult.setText(String.valueOf(double2));
            } else {
                op2 = Integer.parseInt(tmp);
                tvResult.setText(String.valueOf(op2));
            }

        }

    }
}
