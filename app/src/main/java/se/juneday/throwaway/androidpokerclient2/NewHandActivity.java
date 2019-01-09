package se.juneday.throwaway.androidpokerclient2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.IntDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class NewHandActivity extends AppCompatActivity {

	private static final String LOG_TAG = NewHandActivity.class.getName();
	public Button selectDate;
	public TextView date;
	final DatePickerDialog[] datePickerDialog = new DatePickerDialog[1];
	final int[] year = new int[1];
	final int[] month = new int[1];
	final int[] dayOfMonth = new int[1];
	final Calendar[] calendar = new Calendar[1];
	public Spinner dropdownSmallBlind;
	public Spinner dropdownBigBlind;
	public static int blindLevel;

	@Override
	protected void onStart() {
		super.onStart();
		setContentView(R.layout.activity_newhand);
		initViews();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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

			Spinner dropdownSmallBlind = findViewById(R.id.spinner3);
			Integer[] smallBlinds = new Integer[]{10, 25, 50};
			ArrayAdapter<Integer> smallBlindAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, smallBlinds);
			dropdownSmallBlind.setAdapter(smallBlindAdapter);

			Spinner dropdownBigBlind = findViewById(R.id.spinner4);
			Integer[] bigBlinds = new Integer[]{10, 25, 50};
			ArrayAdapter<Integer> bigBlindAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, bigBlinds);
			dropdownBigBlind.setAdapter(bigBlindAdapter);

			selectDate = findViewById(R.id.btnDate);
			date = findViewById(R.id.tvSelectedDate);

			if (Session.getInstance().handInfo != null) {
				Log.d(LOG_TAG, Session.getInstance().handInfo.toString());
				dropdownLocation.setSelection(locationAdapter.getPosition(Session.getInstance().handInfo.location()));
				dropdownGametype.setSelection(gametypeAdapter.getPosition(Session.getInstance().handInfo.gametype()));
				dropdownSmallBlind.setSelection(smallBlindAdapter.getPosition(Session.getInstance().handInfo.smallBlind()));
				dropdownBigBlind.setSelection(bigBlindAdapter.getPosition(Session.getInstance().handInfo.bigBlind()));
			}

			onDateButtonClick();
			onEditPlayersClick();
			onNextClick();
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
				Session session = Session.getInstance();
				Log.d(LOG_TAG, (session.handInfo.toString() + " * " +  session.players.toString()));
				Intent intent = new Intent(NewHandActivity.this, PreflopActivity.class);
				startActivity(intent);
			}
		});
	}

	private void onEditPlayersClick(){
		final Button editPlayersButton = findViewById(R.id.button_edit);

		editPlayersButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Session session = Session.getInstance();
				Log.d(LOG_TAG, (session.handInfo != null ? session.players.toString() : "null players"));
				String location = ((Spinner)findViewById(R.id.spinner1)).getSelectedItem().toString();
				String gameType = ((Spinner)findViewById(R.id.spinner2)).getSelectedItem().toString();
				int smallBlind = Integer.parseInt(((Spinner)findViewById(R.id.spinner3)).getSelectedItem().toString());
				int bigBlind = Integer.parseInt(((Spinner)findViewById(R.id.spinner4)).getSelectedItem().toString());
				session.handInfo = new HandInfo(location, gameType, smallBlind, bigBlind);
				Intent intent = new Intent(NewHandActivity.this, EditPlayersActivity.class);
				startActivity(intent);
			}
		});
	}
}