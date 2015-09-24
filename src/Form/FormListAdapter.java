package Form;

import java.util.List;

import com.example.exemplo.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

/**
 * Bottom List Adapter
 * @author user
 *
 */
final public class FormListAdapter extends BaseAdapter {
	
	/**
	 * Holder
	 * @author user
	 *
	 */
    final class FormListHolder {
        TextView textView1;
        TextView textView2;
        TextView textView3;
        TextView textView4;
        CheckBox checkBox;
    }
    
	// Final Variables
	final private LayoutInflater mInflater;
	final private List<FormModel> mFormValues;
	
	// Variables
	private FormNotifyDataChanged mFormNotifyDataChanged;
	
    /**
     * Constructor
     * 
     * @param context
     */
    public FormListAdapter(final Context context, final List<FormModel> formValues) {
        mInflater = LayoutInflater.from(context);
        mFormValues = formValues;
    }
    
    /**
     * Set Form Notify Data Changed
     * @param formNotifyDataChanged
     */
    public void setFormNotifyDataChanged(final FormNotifyDataChanged formNotifyDataChanged) {
    	mFormNotifyDataChanged = formNotifyDataChanged;
    }

    /**
     * Get Count
     */
    public int getCount() {
        return mFormValues.size();
    }

    /**
     * Get position
     */
    public Object getItem(int position) {
        return mFormValues.get(position);
    }
    
    /**
     * Get position
     */
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get View
     */
    @SuppressLint("InflateParams")
    public View getView(final int position, View convertView, ViewGroup parent) {
    	FormListHolder holder;
        // If not created
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.bottom_listview_model, parent, false);
            
            holder = new FormListHolder();
            holder.textView1 = (TextView) convertView.findViewById(R.id.BLM_textView1);
            holder.textView2 = (TextView) convertView.findViewById(R.id.BLM_textView2);
            holder.textView3 = (TextView) convertView.findViewById(R.id.BLM_textView3);
            holder.textView4 = (TextView) convertView.findViewById(R.id.BLM_textView4);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.BLM_checkBox);
            convertView.setTag(holder);
        } else {
            holder = (FormListHolder) convertView.getTag();
        }
        
        holder.checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				mFormValues.get(position).setSelected(isChecked);
				if(mFormNotifyDataChanged != null)
					mFormNotifyDataChanged.dataChanged();
			}
		});
        
        holder.textView1.setText(mFormValues.get(position).getLocale());
        holder.textView2.setText(mFormValues.get(position).getEquipment());
        holder.textView3.setText(mFormValues.get(position).getConsumption() + " kw/h");
        holder.textView4.setText(mFormValues.get(position).getHours() + " horas");
        holder.checkBox.setChecked(mFormValues.get(position).getSelected());
        
        return convertView;
    }

    

}