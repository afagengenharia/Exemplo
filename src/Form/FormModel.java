package Form;

/**
 * Form Model
 * 
 * @author user
 *
 */
final public class FormModel {

	// Variables
	private String mLocale;
	private String mEquipment;
	private String mConsumption;
	private String mHours;
	private boolean mSelected = false;
	
	/**
	 * Constructor
	 * 
	 * @param l
	 * @param e
	 * @param c
	 * @param h
	 */
	public FormModel(final String l, final String e, final String c, final String h) {
		mLocale = l;
		mEquipment = e;
		mConsumption = c;
		mHours = h;
	}
	
	/**
	 * Get Locale
	 * @return
	 */
	final public String getLocale() {
		return mLocale;
	}
	
	/**
	 * Get Equipment
	 * @return
	 */
	final public String getEquipment() {
		return mEquipment;
	}
	
	/**
	 * Get Consumption
	 * @return
	 */
	final public String getConsumption() {
		return mConsumption;
	}
	
	/**
	 * Get Hours
	 * @return
	 */
	final public String getHours() {
		return mHours;
	}
	
	/**
	 * Get Selected
	 * @return
	 */
	final public boolean getSelected() {
		return mSelected;
	}
	
	/**
	 * Set Selected
	 */
	final public void setSelected(boolean selected) {
		mSelected = selected;
	}
}
