package CustomViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Space View
 * 
 * @author user
 *
 */
public class SpaceView extends View {
	
	/**
	 * Constructor
	 * @param context
	 */
	public SpaceView(Context context) {
		super(context);
	}
	
	/**
	 * Constructor
	 * @param context
	 */
	public SpaceView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	/**
	 * Constructor
	 * @param context
	 */
	public SpaceView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	/**
	 * Measure
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
	}
}
