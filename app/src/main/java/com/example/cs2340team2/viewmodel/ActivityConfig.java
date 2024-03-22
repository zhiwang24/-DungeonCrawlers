package com.example.cs2340team2.viewmodel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cs2340team2.R;

public class ActivityConfig extends AppCompatActivity {

    private ImageView imageView;
    private Button next;
    private Button enterConfig;
    private int i = 0;
    private int[] sprites = {R.drawable.sprite1, R.drawable.sprite2, R.drawable.sprite3};
    private EditText editText;
    private EditText username;
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);

        editText = (EditText) findViewById(R.id.nameEdit);
        imageView = findViewById(R.id.imageView);
        next = findViewById(R.id.next);
        enterConfig = (Button) findViewById(R.id.SubmitConfig);
        username = (EditText) findViewById(R.id.nameEdit);
        radioGroup = (RadioGroup) findViewById(R.id.difficultyRadioGroup);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // traverses through the sprites and loops back to start sprite
                imageView.setImageResource(sprites[i]);
                i++;
                if (i == 3) {
                    i = 0;
                }
            }
        });

        enterConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check to ensure the name is not blank
                if (TextUtils.isEmpty(editText.getText().toString())) {
                    Toast.makeText(ActivityConfig.this,
                            "No empty text field allowed", Toast.LENGTH_LONG).show();
                } else {
                    // sets username as string
                    enterConfig.setEnabled(true);
                    Intent intent = new Intent(ActivityConfig.this, ActivityMap.class);
                    intent.putExtra("NAME", username.getText().toString());

                    int selectedId = radioGroup.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(selectedId);
                    intent.putExtra("DIFFICULTY", radioButton.getText().toString());

                    if (radioButton.getText().toString().equals("Easy")) {
                        intent.putExtra("HP", "100");
                        intent.putExtra("SCORE", "100");
                    } else if (radioButton.getText().toString().equals("Medium")) {
                        intent.putExtra("HP", "70");
                        intent.putExtra("SCORE", "70");
                    } else {
                        intent.putExtra("HP", "40");
                        intent.putExtra("SCORE", "40");
                    }

                    // selects the corresponding sprite depending on the chosen sprite
                    if (i == 0) {
                        intent.putExtra("IMAGE", R.drawable.sprite3);
                    } else if (i == 2) {
                        intent.putExtra("IMAGE", R.drawable.sprite2);
                    } else {
                        intent.putExtra("IMAGE", R.drawable.sprite1);
                    }
                    intent.putExtra("leaderboard",
                            getIntent().getParcelableArrayListExtra("leaderboard"));
                    startActivity(intent);
                }


            }
        });


    }
}
