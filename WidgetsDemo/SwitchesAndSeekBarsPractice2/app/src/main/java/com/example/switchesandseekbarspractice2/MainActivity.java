package com.example.switchesandseekbarspractice2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.switchmaterial.SwitchMaterial;

import static android.graphics.Color.BLUE;
import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static com.example.switchesandseekbarspractice2.R.id.switch1;
import static com.example.switchesandseekbarspractice2.R.id.switch2;
import static com.example.switchesandseekbarspractice2.R.id.switch3;
import static com.example.switchesandseekbarspractice2.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
	SwitchMaterial[] switches;
	TextView textView;
	int color;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(activity_main);
		switches = new SwitchMaterial[]{
				findViewById(switch1),
				findViewById(switch2),
				findViewById(switch3)};
		textView = findViewById(R.id.textView);
		color = textView.getCurrentTextColor();
		switches[0].setOnCheckedChangeListener((buttonView, isChecked) -> setTextViewColor());
		switches[1].setOnCheckedChangeListener((buttonView, isChecked) -> setTextViewColor());
		switches[2].setOnCheckedChangeListener((buttonView, isChecked) -> setTextViewColor());
	}

	private void setTextViewColor() {
		if (switches[0].isChecked() && switches[1].isChecked() && switches[2].isChecked())
			textView.setTextColor(BLUE);
		else if (switches[0].isChecked() && switches[2].isChecked()) textView.setTextColor(RED);
		else if (switches[1].isChecked() && switches[2].isChecked()) textView.setTextColor(color);
		else if (switches[2].isChecked()) textView.setTextColor(GREEN);
		else textView.setTextColor(color);
	}
}