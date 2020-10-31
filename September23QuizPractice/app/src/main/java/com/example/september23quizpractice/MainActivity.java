package com.example.september23quizpractice;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int rand = new Random().nextInt(12);
				switch (rand) {
					case 0:
						((Button) v).setBackgroundColor(Color.RED);
						break;
					case 1:
						((Button) v).setBackgroundColor(Color.GREEN);
						break;
					case 2:
						((Button) v).setBackgroundColor(Color.BLUE);
						break;
					case 3:
						((Button) v).setBackgroundColor(Color.BLACK);
						break;
					case 4:
						((Button) v).setBackgroundColor(Color.CYAN);
						break;
					case 5:
						((Button) v).setBackgroundColor(Color.DKGRAY);
						break;
					case 6:
						((Button) v).setBackgroundColor(Color.GRAY);
						break;
					case 7:
						((Button) v).setBackgroundColor(Color.LTGRAY);
						break;
					case 8:
						((Button) v).setBackgroundColor(Color.MAGENTA);
						break;
					case 9:
						((Button) v).setBackgroundColor(Color.TRANSPARENT);
						break;
					case 10:
						((Button) v).setBackgroundColor(Color.WHITE);
						break;
					case 11:
						((Button) v).setBackgroundColor(Color.YELLOW);
						break;
					default:
						throw new IllegalStateException("Unexpected value: " + rand);
				}
			}
		});
		findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String x = ((Button) v).getText().toString();
				((Button) v).setText(((Button) findViewById(R.id.button1)).getText());
				((Button) findViewById(R.id.button1)).setText(x);
			}
		});
	}
}