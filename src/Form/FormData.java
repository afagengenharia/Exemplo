package Form;

import DataBase.*;

/**
 *  Pages DataBase Object Creator.
 *
 */

public class FormData implements DataBaseCreator.Interface {

	/**
	 *  Called on DataBase created.
	 */
	
	@Override
	public void onCreate(DataBaseCreatorTable table) {
		// Create Page Table
		table.addStringItem("formLocale");
		table.addStringItem("formEquipment");
		table.addStringItem("formConsumption");
		table.addStringItem("formHour");
	}
	
	/**
	 *  Name of Pages Data Base.
	 */
	
	@Override
	public String tableName() {
		return "form_data";
	}
}
