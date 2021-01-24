package com.example.imageviewquiz;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

import static com.example.imageviewquiz.R.drawable.number_one;
import static com.example.imageviewquiz.R.drawable.number_two;
import static com.example.imageviewquiz.R.id.cScore;
import static com.example.imageviewquiz.R.id.go;
import static com.example.imageviewquiz.R.id.image;
import static com.example.imageviewquiz.R.id.input;
import static com.example.imageviewquiz.R.id.pScore;
import static com.example.imageviewquiz.R.id.radio;
import static com.example.imageviewquiz.R.layout.activity_main;
import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

public class MainActivity extends AppCompatActivity {
	RadioGroup radioGroup;
	ImageView imageView;
	TextView[] textViews;
	Button button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(activity_main);
		radioGroup = findViewById(radio);
		imageView = findViewById(image);
		textViews = new TextView[]{findViewById(input), findViewById(pScore), findViewById(cScore)};
		button = findViewById(go);
		radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
			if (checkedId == R.id.radioButton) textViews[0].setText("1");
			else if (checkedId == R.id.radioButton2) textViews[0].setText("2");
		});
		button.setOnClickListener(v -> {
			Toast x = new Toast(MainActivity.this);
			if (textViews[0].getText().equals("")) {
				x.setText("Error; Invalid Input");
				x.show();
			} else {
				int roll = new Random().nextInt(2);
				if (roll == 0) imageView.setImageResource(number_one);
				else if (roll == 1) imageView.setImageResource(number_two);
				if (parseInt(valueOf(textViews[0].getText())) == (roll + 1)) {
					x.setText("Win!");
					x.show();
					textViews[1].setText(valueOf(parseInt(valueOf(textViews[1].getText())) + 1));
				} else if (parseInt(valueOf(textViews[0].getText())) != roll + 1) {
					x.setText("Lose!");
					x.show();
					textViews[2].setText(valueOf(parseInt(valueOf(textViews[2].getText())) + 1));
				}
			}
		});
	}
}