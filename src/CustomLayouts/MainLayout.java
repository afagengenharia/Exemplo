package CustomLayouts;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Layout usado na Atividade Principal, ficando mais pratico que editar todas propriedades no xml
 * @author user
 *
 */
public class MainLayout extends LinearLayout {

	/**
	 * Constructor
	 * @param context
	 * @param attributes
	 */
	public MainLayout(Context context, AttributeSet attributes){
		super(context, attributes);
	}
	
	/**
	 * Constructor
	 * @param context
	 */
	public MainLayout(Context context) {
		super(context);
	}
	
	/**
	 * Measure
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int size = (int)(MeasureSpec.getSize(heightMeasureSpec) / 2.7f);
		int wspec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
		int hspec = MeasureSpec.makeMeasureSpec(size, MeasureSpec.EXACTLY);
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int fitSpace = width - size * 2 - (int)(0.2f * width);
		setPadding(fitSpace/2, 0, fitSpace/2, 0);
		setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
		for(int i=0; i<getChildCount(); i++)
			getChildAt(i).measure(wspec, hspec);
	}
	
	/**
	 * Layout
	 */
	public void onLayout(boolean changed, int l, int t, int r, int b) {
		int childWidth = (int)(getMeasuredHeight() / 2.7f);
		int spaceWidth = (getMeasuredWidth() - getPaddingLeft() - getPaddingRight()) / getChildCount();
		int top = (getMeasuredHeight() - childWidth) / 2;
		int bottom = top + childWidth;
		for(int i=0; i<getChildCount(); i++) {
			int left = getPaddingLeft() + (i + 1) * spaceWidth - (spaceWidth + childWidth) / 2;
			int right = left + childWidth;
			getChildAt(i).layout(left, top, right, bottom);
		}
		
	}
}
