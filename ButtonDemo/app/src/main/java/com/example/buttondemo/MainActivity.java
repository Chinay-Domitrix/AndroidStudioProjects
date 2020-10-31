package com.example.buttondemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
	Button b;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		/*final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int selectedId = radioGroup.getCheckedRadioButtonId();
				if (selectedId == -1)
					makeText(getApplicationContext(), "Nothing selected", LENGTH_SHORT).show();
				if (selectedId == 1)
					makeText(getApplicationContext(), "Toast Selected", LENGTH_LONG).show();
			}
		});*/
		b = findViewById(R.id.button);
		b.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
	}

	public void clickedButton(View v) {
		((Button) v).setText("Clicked");
	}
}