package cn.bistu.edu.cs.mycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.lang.Math;
import java.math.BigDecimal;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //创建Button对象
    Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_point;
    Button btn_mul, btn_div, btn_add, btn_sub;
    Button btn_sin, btn_cos, btn_sqrt, btn_switch, btn_percent;
    Button btn_clr, btn_del, btn_eq;
    EditText et_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //实例化对象
        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);

        btn_add = findViewById(R.id.btn_add);
        btn_sub = findViewById(R.id.btn_sub);
        btn_mul = findViewById(R.id.btn_mul);
        btn_div = findViewById(R.id.btn_div);

        btn_sin = findViewById(R.id.btn_sin);
        btn_cos = findViewById(R.id.btn_cos);
        btn_sqrt = findViewById(R.id.btn_sqrt);
        btn_switch = findViewById(R.id.btn_switch);
        btn_percent = findViewById(R.id.btn_percent);

        btn_point = findViewById(R.id.btn_point);
        btn_clr = findViewById(R.id.btn_clr);
        btn_del = findViewById(R.id.btn_del);
        btn_eq = findViewById(R.id.btn_eq);
        et_input = findViewById(R.id.et_input);

        //给按钮设置的点击事件(Activity本身作为事件监听器）
        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_sub.setOnClickListener(this);
        btn_mul.setOnClickListener(this);
        btn_div.setOnClickListener(this);
        btn_sin.setOnClickListener(this);
        btn_cos.setOnClickListener(this);
        btn_sqrt.setOnClickListener(this);
        btn_switch.setOnClickListener(this);
        btn_percent.setOnClickListener(this);
        btn_clr.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_eq.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str = et_input.getText().toString();
        switch (v.getId()) {
            case R.id.btn_del:
                if (str.length() > 1) {
                    str = str.substring(0, str.length() - 1);//逐一删除
                } else {
                    str = "0";
                }
                et_input.setText(str);
                break;
            case R.id.btn_clr:
                str = "0";
                et_input.setText(str);//全删除
                break;
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
                if (str.equals("0")) {
                    //输入区为0，则替换为所点击的数字
                    str = ((Button) v).getText().toString();
                } else {
                    //否则为输入区内容+所点击的数字
                    str += ((Button) v).getText().toString();
                }
                et_input.setText(str);
                break;

            case R.id.btn_add:
            case R.id.btn_sub:
            case R.id.btn_mul:
            case R.id.btn_div:
                //实现加减乘除前，判断其前边是否已包含其他运算符
                if (str.charAt(str.length() - 1) == '+' || str.charAt(str.length() - 1) == '-' || str.charAt(str.length() - 1) == '×'
                        || str.charAt(str.length() - 1) == '÷' || str.charAt(str.length() - 1) == '.') {
                    Toast.makeText(MainActivity.this, "输入错误!", Toast.LENGTH_SHORT).show();
                    et_input.setText(str);
                } else {
                    str += ((Button) v).getText().toString();
                    et_input.setText(str);
                }
                break;

            case R.id.btn_sin:
                if ((str.contains("+") || str.contains("-") || str.contains("×") || str.contains("÷"))&&str.charAt(0) != '-') {
                        Toast.makeText(MainActivity.this, "运算符不能被sin!", Toast.LENGTH_SHORT).show();
                        et_input.setText("0");
                }else {
                    double sin = Double.parseDouble(str);
                    sin = Math.toRadians(sin);//角度转换为弧度
                    sin = Math.sin(sin);
                    String sin1 = String.format("%.9f", sin);//规避极小误差
                    sin1 = sin1.replaceAll("0+?$", "");//去掉多余的0
                    sin1 = sin1.replaceAll("[.]$", "");//如最后一位是.则去掉
                    et_input.setText(sin1);
                }
                break;

            case R.id.btn_cos:
                if ((str.contains("+") || str.contains("-") || str.contains("×") || str.contains("÷"))&&str.charAt(0) != '-') {
                        Toast.makeText(MainActivity.this, "运算符不能被cos!", Toast.LENGTH_SHORT).show();
                        et_input.setText("0");
                    }
                else {
                    double cos = Double.parseDouble(str);
                    cos = Math.toRadians(cos);
                    cos = Math.cos(cos);
                    String cos1 = String.format("%.9f", cos);
                    cos1 = cos1.replaceAll("0+?$", "");
                    cos1 = cos1.replaceAll("[.]$", "");
                    et_input.setText(cos1);
                }
                break;

            case R.id.btn_sqrt:
                if (str.charAt(0) == '-') {
                    Toast.makeText(MainActivity.this, "负数不能开平方根!", Toast.LENGTH_SHORT).show();
                    et_input.setText("0");
                } else if (str.contains("+") || str.contains("-") || str.contains("×") || str.contains("÷")) {
                    Toast.makeText(MainActivity.this, "运算符不能开平方根!", Toast.LENGTH_SHORT).show();
                    et_input.setText("0");
                } else {
                    double x = Double.parseDouble(str);
                    x = Math.sqrt(x);
                    String x2 = String.format("%.9f", x);
                    x2 = x2.replaceAll("0+?$", "");
                    x2 = x2.replaceAll("[.]$", "");
                    et_input.setText(x2);
                }
                break;

            case R.id.btn_switch:
                if ((str.contains("+") || str.contains("-") || str.contains("×") || str.contains("÷"))&&str.charAt(0) != '-') {
                        Toast.makeText(MainActivity.this, "运算符不能切换!", Toast.LENGTH_SHORT).show();
                        et_input.setText("0");
                }else if (str.charAt(0) >= '0' && str.charAt(0) <= '9') {
                    if (str.equals("0")) {
                        et_input.setText("0");
                    } else {
                        et_input.setText("-" + str);
                    }
                } else if (str.charAt(0) == '-')
                    et_input.setText(str.substring(1, str.length()));
                else
                    et_input.setText(str);

                break;
            default:
                break;

            case R.id.btn_percent:
                if ((str.contains("+") || str.contains("-") || str.contains("×") || str.contains("÷"))&&str.charAt(0) != '-') {
                        Toast.makeText(MainActivity.this, "运算符不能求百分号!", Toast.LENGTH_SHORT).show();
                        et_input.setText("0");
                    }else{
                        double per = Double.parseDouble(str);
                        per = per / 100;
                        String per1 = "" + per;
                        per1 = per1.replaceAll("0+?$", "");
                        per1 = per1.replaceAll("[.]$", "");
                        et_input.setText(per1);
                    }

                break;

            case R.id.btn_point:
                //判断小数点是否可以继续输入
                if (str.charAt(str.length() - 1) == '.' || str.charAt(str.length() - 1) == '+' || str.charAt(str.length() - 1) == '-'
                        || str.charAt(str.length() - 1) == '×' || str.charAt(str.length() - 1) == '÷') {
                    Toast.makeText(MainActivity.this, "输入错误!", Toast.LENGTH_SHORT).show();
                    et_input.setText(str);
                } else {
                    int i = str.length() - 1;
                    char ch = str.charAt(i);
                    while (ch <= '9' && ch >= '0') {
                        if ((i - 1) >= 0) {
                            ch = str.charAt(--i);
                            continue;}
                        break;
                    }
                    if (ch == '.') {
                        Toast.makeText(MainActivity.this, "小数点重复!", Toast.LENGTH_SHORT).show();
                        et_input.setText(str);
                    } else {
                        str += ".";
                        et_input.setText(str);
                    }
                }
                break;

            case R.id.btn_eq:
                //判断输入完整性
                if (str.charAt(str.length() - 1) == '+' || str.charAt(str.length() - 1) == '-' || str.charAt(str.length() - 1) == '×' || str.charAt(str.length() - 1) == '÷') {
                    Toast.makeText(MainActivity.this, "请输入完整的式子!", Toast.LENGTH_SHORT).show();
                    et_input.setText(str);
                } else {
                    String re = getResult();
                    if (re.contains("Infinity")) {
                        //结果为无穷时，报错0不能做除数
                        Toast.makeText(MainActivity.this, "0不能做被除数!", Toast.LENGTH_SHORT).show();
                        et_input.setText("0");
                    } else {
                        et_input.setText(re);//显示结果
                    }
                }
                break;
        }
    }

    public String getResult() {
        String infix = et_input.getText().toString();
        StringBuffer postfixStr = toPostfix(infix);
        Double result = toValue(postfixStr);
        String re = String.format("%.9f", result);//规避极小误差
        re = re.replaceAll("0+?$", "");
        re = re.replaceAll("[.]$", "");
        return re;
    }

    //将中缀表达式转换成后缀表达式
    public static StringBuffer toPostfix(String infix) {
        Stack<String> stack = new Stack<String>();   //运算符栈-顺序栈
        StringBuffer postfixStr = new StringBuffer(infix.length() * 2);   //postfixStr后缀表达式字符串
        int i = 0;
        while (i < infix.length()) {
            char ch = infix.charAt(i);
            switch (ch) {
                case '+':
                case '-':
                    while (!stack.isEmpty() ) //如果栈不为空则出栈
                        postfixStr.append(stack.pop());   //且添加到后缀表达式串
                    stack.push(ch + ""); //栈为空，ch放入运算符栈
                    i++;
                    break;
                case '×':
                case '÷':
                    while (!stack.isEmpty() && (stack.peek().equals("×") || stack.peek().equals("÷")))  //栈顶元素不为空且栈顶元素是"*"或是"/"时,则出栈
                        postfixStr.append(stack.pop());
                    stack.push(ch + "");
                    i++;
                    break;
                default:
                    while ((i < infix.length() && ch >= '0' && ch <= '9') || (i < infix.length() && ch == '.')) {
                        postfixStr.append(ch);    //如果是数字或小数点直接添加到后缀表达式串
                        i++;
                        if (i < infix.length())
                            ch = infix.charAt(i);
                    }
                    postfixStr.append(" ");//空格作分隔符
            }
        }
        while (!stack.isEmpty())       //所有运算符出栈
            postfixStr.append(stack.pop());   //添加到后缀表达式中
        return postfixStr;
    }

    //计算后缀表达式
    public static Double toValue(StringBuffer postfixStr) {
        Stack<Double> stack = new Stack<Double>();   //操作数栈-顺序栈
        double value = 0;//存放字符转换的数值
        int j=0;//记录字符串长度
        for (int i = 0; i < postfixStr.length(); i++) {
            j=i;
            char ch = postfixStr.charAt(i);
            if ((ch >= '0' && ch <= '9') || ch == '.') {
                value = 0;
                while (ch != ' ') {
                    while (ch != ' ' && ch != '.') {
                        value = value * 10 + ch - '0';// 将字符转换为整数值
                        j++;
                        ch = postfixStr.charAt(++i);
                    }
                    if ( ch == '.') {
                        ch = postfixStr.charAt(++i);
                    }
                    while(ch!=' '&&(i>=j+1)){
                        /*浮点数在计算机中会失去精确度
                        double b = Math.pow(10,i-j);
                        value = value+value/b;
                        */
                        BigDecimal valueBD=new BigDecimal(Double.toString(value));
                        BigDecimal chBD=new BigDecimal(Double.toString(ch-'0'));//一个数字字符的ASCII值变成对应的字符的值
                        BigDecimal nBD=new BigDecimal(Double.toString(Math.pow(10,i-j)));
                        double temp=chBD.divide(nBD).doubleValue();
                        BigDecimal tempBD=new BigDecimal(Double.toString(temp));
                        value=valueBD.add(tempBD).doubleValue();
                        ch=postfixStr.charAt(++i);
                    }
                    stack.push(value);// 将转换好的数值放入栈内
                }
            } else {
                if (ch != ' ') {
                    Double y = stack.pop();
                    Double x;
                    if (stack.isEmpty()) {
                        x = 0.0;
                    } else {
                        x = stack.pop();
                    }
                    switch (ch) {
                        case '+':
                            value = x + y;
                            break;
                        case '-':
                            value = x - y;
                            break;
                        case '×':
                            value = x * y;
                            break;
                        case '÷':
                            value = x / y;
                            break;
                    }
                    stack.push(value);// 运算结果入栈
                }
            }

        }
        return stack.pop();
    }
}