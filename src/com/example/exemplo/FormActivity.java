package com.example.exemplo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import DataBase.DataBaseCreator;
import DataBase.DataBaseCreator.QueryResult;
import Form.FormData;
import Form.FormListAdapter;
import Form.FormModel;
import Form.FormNotifyDataChanged;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Form Activity
 * @author user
 *
 */
public class FormActivity extends Activity {
	
	// Menu Values
	String[] mLocalList = {"MenuItem", "MenuItem", "MenuItem", "MenuItem", "MenuItem", "MenuItem", "MenuItem"};
	String[] mEquipList = {"MenuItem", "MenuItem", "MenuItem", "MenuItem", "MenuItem", "MenuItem", "MenuItem"};
			
	
	// Variables
	List<FormModel> mFormValues = new ArrayList<FormModel>();
	
	/**
	 * Constructor
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//
		setContentView(R.layout.activity_form);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		
		// As list
		List<String> localValues = Arrays.asList(mLocalList);
		List<String> equipValues = Arrays.asList(mEquipList);

		// Read from db
		DataBaseCreator dbc = new DataBaseCreator(this, new FormData());
		QueryResult result = dbc.queryAll();
		while(!result.pointerOut()) {
			String locale = (String)result.getItem(0).getValue();
			String equipment = (String)result.getItem(1).getValue();
			String consumption = (String)result.getItem(2).getValue();
			String hour = (String)result.getItem(3).getValue();
			mFormValues.add(new FormModel(locale, equipment, consumption, hour));
			result.moveToNext();
		} 
		dbc.close();
		
		// Menus
		final ListView localList = (ListView) findViewById(R.id.localList);
		localList.setAdapter(new ArrayAdapter<String>(this, R.layout.top_listview_model, localValues));
		localList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		localList.setSelector(android.R.color.darker_gray);

		final ListView equipList = (ListView) findViewById(R.id.equipList);
		equipList.setAdapter(new ArrayAdapter<String>(this, R.layout.top_listview_model, equipValues));
		equipList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		equipList.setSelector(android.R.color.darker_gray);
		
		// Notify
		final FormNotifyDataChanged formNotifyDataChanged = new FormNotifyDataChanged() {
			
			@Override
			public void dataChanged() {
				boolean checked = false;
				for(final FormModel model : mFormValues) {
					if(model.getSelected()) {
						checked = true;
						break;
					}
				}
				if(checked) {
					((Button) findViewById(R.id.AF_button2)).setEnabled(true);
				} else {
					((Button) findViewById(R.id.AF_button2)).setEnabled(false);
				}
			}
		};
		
		// Form
		final ListView formList = (ListView) findViewById(R.id.formList);
		formList.setAdapter(new FormListAdapter(this, mFormValues));
		formList.setBackgroundColor(0xFFDDDDDD);
		formList.setChoiceMode(ListView.CHOICE_MODE_NONE);
		formList.setSelector(R.drawable.selector);
		((FormListAdapter) formList.getAdapter()).setFormNotifyDataChanged(formNotifyDataChanged);
		
		
		
		// Insert
		((Button) findViewById(R.id.AF_button1)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int localId = localList.getCheckedItemPosition();
				int equipId = equipList.getCheckedItemPosition();
				
				if(localId == -1) {
					Toast.makeText(FormActivity.this, "Selecione um local", Toast.LENGTH_SHORT).show();
					return;
				} else if(equipId == -1) {
					Toast.makeText(FormActivity.this, "Selecione um Equipamento", Toast.LENGTH_SHORT).show();
					return;
				}
				
				String consumption = ((EditText) findViewById(R.id.AF_textedit1)).getText().toString();
				String hour = ((EditText) findViewById(R.id.AF_textedit2)).getText().toString();
				
				if(consumption.length() == 0) {
					Toast.makeText(FormActivity.this, "Informe o consumo", Toast.LENGTH_SHORT).show();
					return;
				} else if(hour.length() == 0) {
					Toast.makeText(FormActivity.this, "Informe a quantidade de horas", Toast.LENGTH_SHORT).show();
					return;
				}
				
				try {
					Integer.parseInt(consumption);
				} catch (Exception e) {
					Toast.makeText(FormActivity.this, "Valor de consumo invalido", Toast.LENGTH_SHORT).show();
					return;
				}
				
				try {
					Integer.parseInt(hour);
				} catch (Exception e) {
					Toast.makeText(FormActivity.this, "Valor de quantidade de horas invalido", Toast.LENGTH_SHORT).show();
					return;
				}
				
				mFormValues.add(new FormModel(mLocalList[localId], mEquipList[equipId], consumption, hour));
				formList.setAdapter(new FormListAdapter(FormActivity.this, mFormValues));
				((FormListAdapter) formList.getAdapter()).setFormNotifyDataChanged(formNotifyDataChanged);
				
				localList.setItemChecked(localId, false);
				equipList.setItemChecked(equipId, false);
				
				((EditText) findViewById(R.id.AF_textedit1)).setText("");
				((EditText) findViewById(R.id.AF_textedit2)).setText("");
				
			}
		});
		
		// Delete
		((Button) findViewById(R.id.AF_button2)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Iterator<FormModel> itr = mFormValues.iterator();
				while(itr.hasNext()) {
					FormModel formModel = itr.next();
					if(formModel.getSelected())
						itr.remove();
				}
				formList.setAdapter(new FormListAdapter(FormActivity.this, mFormValues));
				((FormListAdapter) formList.getAdapter()).setFormNotifyDataChanged(formNotifyDataChanged);
				((Button) findViewById(R.id.AF_button2)).setEnabled(false);
			}
		});
		
		// Save to db
		((Button) findViewById(R.id.AF_button3)).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FormActivity.this.deleteDatabase((new FormData()).tableName() + ".db");

				DataBaseCreator dbc = new DataBaseCreator(FormActivity.this, new FormData());
				try {
					for(final FormModel data : mFormValues)
						dbc.insert(dbc.item(data.getLocale()), dbc.item(data.getEquipment()), dbc.item(data.getConsumption()), dbc.item(data.getHours()));
				} catch (Exception e) {
					Log.d("LogTest", "Err: "+ e.getMessage());
				}
				
				dbc.close();
				
				FormActivity.this.finish();
			}
		});
		
	}
	


}
