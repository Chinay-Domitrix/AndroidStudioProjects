package com.example.listviewdemo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import static com.example.listviewdemo.R.id.adapter_button;
import static com.example.listviewdemo.R.id.adapter_textView;
import static com.example.listviewdemo.R.id.listView;
import static com.example.listviewdemo.R.layout.activity_main;
import static com.example.listviewdemo.R.layout.adapter_list;

public class MainActivity extends AppCompatActivity {
	ListView listView;
	ArrayList<String> stringArrayList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(activity_main);
		listView = findViewById(R.id.listView);
		stringArrayList = new ArrayList<>();
		stringArrayList.add("Billy");
		stringArrayList.add("Bob");
		stringArrayList.add("Joe");
		stringArrayList.add("Steve");
		stringArrayList.add("Richard");
		listView.setAdapter(new ListAdapter(this, adapter_list, stringArrayList));
	}

	class ListAdapter extends ArrayAdapter<String> {
		@NonNull private final Context context;
		private final int resource;
		@NonNull private final List<String> objects;

		public ListAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
			super(context, resource, objects);
			this.context = context;
			this.resource = resource;
			this.objects = objects;
		}

		@NonNull
		@Override
		public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
			View view = ((LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE)).inflate(resource, null);
			Button button = view.findViewById(adapter_button);
			TextView textView = view.findViewById(adapter_textView);
			textView.setText(objects.get(position));
			String s = "Position " + position;
			button.setText(s);
			return view;
//			return super.getView(position, convertView, parent);
		}
	}
}