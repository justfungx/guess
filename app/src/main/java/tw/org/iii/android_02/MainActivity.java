package tw.org.iii.android_02;

import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private View guess;
    private TextView info;
    private EditText intput;
    private String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        answer = createAnswer(4);
        intput = (EditText)findViewById(R.id.input);
        info = (TextView)findViewById(R.id.infoText);

        guess = findViewById(R.id.guess);
        guess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doGuess();
            }
        });

    }

       private  void doGuess(){
           String strInput = intput.getText().toString();
           String result = checkAB(answer,strInput);
           info.append(strInput + ":" + result + "\n" );
           intput.setText("");
       }

    static String createAnswer(int n){
        // 洗牌
        int[] poker = new int[n];
        for (int i=0; i<poker.length; i++){
            int temp;
            // 檢查機制
            boolean isRepeat;
            do {
                temp = (int)(Math.random()*10);
                isRepeat = false;
                for (int j=0; j<i; j++){
                    if (temp == poker[j]){
                        isRepeat = true;
                        break;
                    }
                }
            }while(isRepeat);
            poker[i] = temp;
        }
        String ret = "";
        for (int p : poker)ret += p;

        return ret;
    }
    static String checkAB(String a, String g){
        int A, B; A = B = 0;
        for (int i=0; i<g.length(); i++){
            if (g.charAt(i) == a.charAt(i)){
                A++;
            }else if (a.indexOf(g.charAt(i))!=-1){
                B++;
            }
        }
        return A + "A" + B + "B";
    }



}