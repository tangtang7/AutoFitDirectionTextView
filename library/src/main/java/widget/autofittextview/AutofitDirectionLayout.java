package widget.autofittextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.WeakHashMap;

import me.grantland.widget.R;

/**
 * A {@link ViewGroup} that re-sizes the text of it's children to be no larger than the width of the
 * view.
 *
 * @attr ref R.styleable.AutofitDirectionTextView_sizeToFit
 * @attr ref R.styleable.AutofitDirectionTextView_minTextSize
 * @attr ref R.styleable.AutofitDirectionTextView_precision
 * @attr ref R.styleable.AutofitDirectionTextView_adaptDirection
 */
public class AutofitDirectionLayout extends FrameLayout {

    private boolean mEnabled;
    private float mMinTextSize;
    private float mPrecision;
    private int mAdaptDirection;
    private WeakHashMap<View, AutofitDirectionHelper> mHelpers = new WeakHashMap<View, AutofitDirectionHelper>();

    public AutofitDirectionLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public AutofitDirectionLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public AutofitDirectionLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        boolean sizeToFit = true;
        int minTextSize = -1;
        float precision = -1;
        int adaptDirection = AutofitDirectionHelper.ADAPT_DIRECTION_WIDTH;

        if (attrs != null) {
            TypedArray ta = context.obtainStyledAttributes(attrs,
                    R.styleable.AutofitDirectionTextView, defStyle, 0);
            sizeToFit = ta.getBoolean(R.styleable.AutofitDirectionTextView_sizeToFit, sizeToFit);
            minTextSize = ta.getDimensionPixelSize(R.styleable.AutofitDirectionTextView_minTextSize,
                    minTextSize);
            precision = ta.getFloat(R.styleable.AutofitDirectionTextView_precision, precision);
            adaptDirection = ta.getInt(R.styleable.AutofitDirectionTextView_adaptDirection, adaptDirection);
            ta.recycle();
        }

        mEnabled = sizeToFit;
        mMinTextSize = minTextSize;
        mPrecision = precision;
        mAdaptDirection = adaptDirection;
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        TextView textView = (TextView) child;
        AutofitDirectionHelper helper = AutofitDirectionHelper.create(textView)
                .setEnabled(mEnabled);
        if (mPrecision > 0) {
            helper.setPrecision(mPrecision);
        }
        if (mMinTextSize > 0) {
            helper.setMinTextSize(TypedValue.COMPLEX_UNIT_PX, mMinTextSize);
        }
        mHelpers.put(textView, helper);
    }

    /**
     * Returns the {@link AutofitDirectionHelper} for this child View.
     */
    public AutofitDirectionHelper getAutofitHelper(TextView textView) {
        return mHelpers.get(textView);
    }

    /**
     * Returns the {@link AutofitDirectionHelper} for this child View.
     */
    public AutofitDirectionHelper getAutofitHelper(int index) {
        return mHelpers.get(getChildAt(index));
    }
}
