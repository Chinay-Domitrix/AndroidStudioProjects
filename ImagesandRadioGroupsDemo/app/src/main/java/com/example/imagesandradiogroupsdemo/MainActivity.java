package com.example.imagesandradiogroupsdemo;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;
import static com.example.imagesandradiogroupsdemo.R.drawable.icon_sample;
import static com.example.imagesandradiogroupsdemo.R.id.imageView2;
import static com.example.imagesandradiogroupsdemo.R.id.radio;
import static com.example.imagesandradiogroupsdemo.R.id.radioButton;
import static com.example.imagesandradiogroupsdemo.R.id.radioButton2;
import static com.example.imagesandradiogroupsdemo.R.layout.activity_main;

public class MainActivity extends AppCompatActivity {
	ImageView imageView;
	RadioGroup radioGroup;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(activity_main);
		imageView = findViewById(imageView2);
		imageView.setImageResource(icon_sample);
		System.out.println(imageView.getId() + "\n" + icon_sample + "\n" + imageView2);
		radioGroup = findViewById(radio);
		radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
			if (checkedId == radioButton) {
//					do something
			} else if (checkedId == radioButton2)
				makeText(MainActivity.this, "Hello", LENGTH_SHORT).show();
		});
	}
}