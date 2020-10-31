package com.example.september25quiz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.RED;
import static android.util.TypedValue.COMPLEX_UNIT_DIP;
import static android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Button redButton = findViewById(R.id.redButton), blueButton = findViewById(R.id.blueButton), sizeChanger = findViewById(R.id.sizeChanger);
		redButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((Button) v).setTextColor(RED);
			}
		});
		blueButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((Button) v).setTextColor(BLUE);
				String x = ((Button) v).getText().toString();
				((Button) v).setText(redButton.getText());
				redButton.setText(x);
			}
		});
		sizeChanger.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				((Button) v).setTextSize(COMPLEX_UNIT_DIP, ((Button) v).getTextSize() + 1);
				redButton.setTextSize(COMPLEX_UNIT_DIP, redButton.getTextSize() + 1);
				blueButton.setTextSize(COMPLEX_UNIT_DIP, blueButton.getTextSize() + 1);
			}
		});
	}
}