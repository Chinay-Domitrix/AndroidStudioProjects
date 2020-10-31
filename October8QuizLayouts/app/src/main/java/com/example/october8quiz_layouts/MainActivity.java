package com.example.october8quiz_layouts;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static android.view.View.OnClickListener;
import static com.example.october8quiz_layouts.R.id.button;
import static com.example.october8quiz_layouts.R.id.button2;
import static com.example.october8quiz_layouts.R.id.textView;
import static com.example.october8quiz_layouts.R.id.textView2;

public class MainActivity extends AppCompatActivity {
	boolean[] isClicked = {false, false};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(button).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				isClicked[0] = !isClicked[0];
				((TextView) findViewById(textView)).setText(isClicked[0] ? "Clicked" : "Not Clicked");
			}
		});
		findViewById(button2).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				isClicked[1] = !isClicked[1];
				((TextView) findViewById(textView2)).setText(isClicked[1] ? "Clicked" : "Not Clicked");
			}
		});
	}
}