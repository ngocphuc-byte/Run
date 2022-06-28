package com.example.thirdapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
        TextView txt;
        ImageButton btn;
        SeekBar skb1,skb2, skb3;
        CheckBox cb1, cb2, cb3;
        int Point = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();

        skb1.setEnabled(false);         //Khong cho cham vao thanh Seek Bar
        skb2.setEnabled(false);
        skb3.setEnabled(false);

        //CheckBox: chi duoc click 1 box-------------------------------------------------------------------------------------
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cb2.setChecked(false);
                    cb3.setChecked(false);
                    Toast.makeText(MainActivity.this, "You choose Turtle1", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "You unselect Turtle1", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cb1.setChecked(false);
                    cb3.setChecked(false);
                    Toast.makeText(MainActivity.this, "You choose Turtle2", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "You unselect Turtle2", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    cb1.setChecked(false);
                    cb2.setChecked(false);
                    Toast.makeText(MainActivity.this, "You Choose Turtle3", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "You unselect Turtle3", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //-------------------------------------------------CHECK BOX-------------------------------------------------------------



        //------------------------------------CountDownTime: dem nguoc thoi gian va chay-------------------------------------
        Random random = new Random();

        CountDownTimer countDownTimer = new CountDownTimer(60000, 300) {
            @Override
            public void onTick(long l) {
                txt.setText(Point+"");                      //Doi kieu du lieu cho Point
                int number = 5;                             //So random
                int one = random.nextInt(number);           //Tao random tu 0-5
                int two = random.nextInt(number);
                int three = random.nextInt(number);
                //Neu skb1 lay grogress hien hien >= max
                if(skb1.getProgress() >= skb1.getMax()){
                    this.cancel();                  //---> Chuong trinh ngung lai
                    btn.setVisibility(View.VISIBLE);//---> Hien lai nut Button
                    Toast.makeText(MainActivity.this, "Turtle ONE WON", Toast.LENGTH_SHORT).show();
                    if(cb1.isChecked()){            //cb1 khai bao nam trong if cua skb1
                        Point += 10;                //-> neu check(skb1==true) thi point +10
                        Toast.makeText(MainActivity.this,"Ban duoc +10 point",Toast.LENGTH_SHORT).show();
                    } else {
                        Point -= 10;                //-> nguoc lai se tru di 10
                        Toast.makeText(MainActivity.this,"Ban bi -10 point",Toast.LENGTH_SHORT).show();
                    }
                    txt.setText(String.valueOf(Point));//-> minh cai dat lai point sau khi tru
                    EnableCheckBox();               //Sau khi dung lai no se mo muc CheckBox
                } if(skb2.getProgress() >= skb2.getMax()){
                    this.cancel();
                    btn.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this,"Turtle TWO WON",Toast.LENGTH_SHORT).show();
                    if(cb2.isChecked()){
                        Point += 10;
                        Toast.makeText(MainActivity.this,"Ban duoc +10 point",Toast.LENGTH_SHORT).show();
                    } else {
                        Point -= 10;
                        Toast.makeText(MainActivity.this,"Ban bi -10 point",Toast.LENGTH_SHORT).show();
                    }
                    txt.setText(String.valueOf(Point));
                    EnableCheckBox();
                } if(skb3.getProgress() >= skb3.getMax()){
                    this.cancel();
                    btn.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this,"Turtle THREE WON",Toast.LENGTH_SHORT).show();
                    if(cb3.isChecked()){
                        Point += 10;
                        Toast.makeText(MainActivity.this,"Ban duoc +10 point",Toast.LENGTH_SHORT).show();
                    } else {
                        Point -= 10;
                        Toast.makeText(MainActivity.this,"Ban bi -10 point",Toast.LENGTH_SHORT).show();
                    }
                    txt.setText(String.valueOf(Point));
                    EnableCheckBox();
                }
                skb1.setProgress(skb3.getProgress() + one);     //Cho no chay random
                skb2.setProgress(skb2.getProgress() + two);
                skb3.setProgress(skb3.getProgress() + three);


            }

            @Override
            public void onFinish() {

            }
        };
        //-------------------------------Countdown game chay----------------------------------------//


        //------------------------Nut Button---------------------------------------------------//
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cb1.isChecked() || cb2.isChecked() || cb3.isChecked()) {
                    skb1.setProgress(0);            //Sau khi click vao Button --> Progress return 0 and run again
                    skb2.setProgress(0);
                    skb3.setProgress(0);
                    btn.setVisibility(View.INVISIBLE);  //An di nut Button khi click
                    countDownTimer.start();//Click vao de cho countdown dem tgian va hoat dong
                    DisableCheckBox();  //Sau khi chay no se dong tuy chinh
                    } else {
                    Toast.makeText(MainActivity.this, "Vui long dat cuoc!!!", Toast.LENGTH_SHORT).show();
                }
                }
        });
    }
        //------------------------------------------------------------------------------------//

    private void EnableCheckBox(){              //Cai dat Enable -> mo tuy chinh Check Box
        cb1.setEnabled(true);
        cb2.setEnabled(true);
        cb3.setEnabled(true);
    }
    private void DisableCheckBox(){             //Cai dat Disable -> dong tuy chinh Check Box
        cb1.setEnabled(false);
        cb2.setEnabled(false);
        cb3.setEnabled(false);
    }

    public void AnhXa(){
        txt = (TextView) findViewById(R.id.textView);
        btn = (ImageButton) findViewById(R.id.button);
        skb1 = (SeekBar) findViewById(R.id.seekBar1);
        skb2 = (SeekBar) findViewById(R.id.seekBar2);
        skb3 = (SeekBar) findViewById(R.id.seekBar3);
        cb1 = (CheckBox) findViewById(R.id.checkBox1);
        cb2 = (CheckBox) findViewById(R.id.checkBox2);
        cb3 = (CheckBox) findViewById(R.id.checkBox3);
    }
}