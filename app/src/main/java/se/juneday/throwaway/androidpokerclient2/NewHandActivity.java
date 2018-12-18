package se.juneday.throwaway.androidpokerclient2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class NewHandActivity extends AppCompatActivity {

	public Button selectDate;
	public TextView date;
	final DatePickerDialog[] datePickerDialog = new DatePickerDialog[1];
	final int[] year = new int[1];
	final int[] month = new int[1];
	final int[] dayOfMonth = new int[1];
	final Calendar[] calendar = new Calendar[1];
	public Spinner dropdownBlinds;
	public static int blindLevel;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newhand);
		initViews();
		}

		private void onDateButtonClick(){
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

		private void onNextClick(){
			final Button nextButton = findViewById(R.id.button_next);
			nextButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(NewHandActivity.this, Preflop.class);
					startActivity(intent);
				}
			});
		}

		private void onEditPlayersClick(){
			final Button editPlayersButton = findViewById(R.id.button_edit);
			editPlayersButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(NewHandActivity.this, EditPlayersActivity.class);
					startActivity(intent);
				}
			});
		}

		void initViews(){
			//get the spinner from the xml.
			Spinner dropdownLocation = findViewById(R.id.spinner1);
			//create a list of items for the spinner.
			String[] locations = new String[]{"Location", "Göteborg", "Stockholm", "Malmö", "Sundsvall"};
			//create an adapter to describe how the items are displayed.
			ArrayAdapter<String> locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, locations);
			//set the spinners adapter to the previously created one.
			dropdownLocation.setAdapter(locationAdapter);

			Spinner dropdownGametype = findViewById(R.id.spinner2);
			String[] gametypes = new String[]{"Gametype", "Cashgame"};
			ArrayAdapter<String> gametypeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, gametypes);
			dropdownGametype.setAdapter(gametypeAdapter);

			dropdownBlinds = findViewById(R.id.spinner3);
			String[] blinds = new String[]{"Blinds", "10/10", "25/25", "50/50"};
			ArrayAdapter<String> blindsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, blinds);
			dropdownBlinds.setAdapter(blindsAdapter);

			selectDate = findViewById(R.id.btnDate);
			date = findViewById(R.id.tvSelectedDate);

			onDateButtonClick();
			onEditPlayersClick();
			onNextClick();
			onBlindSpinnerSelected();
		}

		private void onBlindSpinnerSelected(){
			dropdownBlinds.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
					blindLevel = convertBlindsToInt();
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
					//Not needed
				}
			});
		}

	@IntDef({Blinds.BLINDS_DEFAULT, Blinds.BLINDS_FIRST, Blinds.BLINDS_SECOND, Blinds.BLINDS_THIRD}) // Denotes that the annotated element is of integer type.
	public @interface Blinds {
		int BLINDS_DEFAULT = 0;
		int BLINDS_FIRST = 1;
		int BLINDS_SECOND = 2;
		int BLINDS_THIRD = 3;

	}

	/**
	 * Helper method to convert strings of blinds to ints.
	 *
	 * @return The mapped int.
	 */
	public int convertBlindsToInt() {
		String selectedBlinds = dropdownBlinds.getSelectedItem().toString();

		int selectedBlind = 0;

		switch (selectedBlinds) {
			case  "Blinds" :
				selectedBlind = Blinds.BLINDS_FIRST;
				break;
			case "10/10" :
				selectedBlind = Blinds.BLINDS_FIRST;
				break;
			case "25/25":
				selectedBlind = Blinds.BLINDS_SECOND;
				break;
			case "50/50":
				selectedBlind = Blinds.BLINDS_THIRD;
				break;
		}
		return selectedBlind;
	}

	}