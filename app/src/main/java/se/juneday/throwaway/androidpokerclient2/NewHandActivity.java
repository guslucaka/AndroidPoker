package se.juneday.throwaway.androidpokerclient2;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class NewHandActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);

		//get the spinner from the xml.
		Spinner dropdownLocation = findViewById(R.id.spinner1);
		//create a list of items for the spinner.
		String[] locations = new String[]{"Location", "Göteborg", "Stockholm", "Malmö", "Sundsvall"};
		//create an adapter to describe how the items are displayed, adapters are used in several places in android.
		//There are multiple variations of this, but this is the basic variant.
		ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, locations);
		//set the spinners adapter to the previously created one.
		dropdownLocation.setAdapter(locationAdapter);

		//get the spinner from the xml.
		Spinner dropdownGametype = findViewById(R.id.spinner2);
		//create a list of items for the spinner.
		String[] gametypes = new String[]{"Gametype", "Cashgame"};
		//create an adapter to describe how the items are displayed, adapters are used in several places in android.
		//There are multiple variations of this, but this is the basic variant.
		ArrayAdapter<String> gametypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, gametypes);
		//set the spinners adapter to the previously created one.
		dropdownGametype.setAdapter(gametypeAdapter);

		Spinner dropdownBlinds = findViewById(R.id.spinner3);
		String[] blinds = new String[]{"Blinds", "10/10", "25/25", "50/50"};
		ArrayAdapter<String> blindsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, blinds);
		dropdownBlinds.setAdapter(blindsAdapter);


		Button selectDate;
		final TextView date;
        final DatePickerDialog[] datePickerDialog = new DatePickerDialog[1];
        final int[] year = new int[1];
        final int[] month = new int[1];
        final int[] dayOfMonth = new int[1];
        final Calendar[] calendar = new Calendar[1];

		selectDate = findViewById(R.id.btnDate);
		date = findViewById(R.id.tvSelectedDate);

		selectDate.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					calendar[0] = Calendar.getInstance();
					year[0] = calendar[0].get(Calendar.YEAR);
					month[0] = calendar[0].get(Calendar.MONTH);
					dayOfMonth[0] = calendar[0].get(Calendar.DAY_OF_MONTH);
					datePickerDialog[0] = new DatePickerDialog(NewHandActivity.this,
							new DatePickerDialog.OnDateSetListener() {
								@Override
								public void onDateSet(DatePicker datePicker, int year, int month, int day) {
									String dateValue = day + "/" + (month+1) + "/" + year;

									date.setText(dateValue);
								}
							}, year[0], month[0], dayOfMonth[0]);
					datePickerDialog[0].show();
				}
			});
		}
	}