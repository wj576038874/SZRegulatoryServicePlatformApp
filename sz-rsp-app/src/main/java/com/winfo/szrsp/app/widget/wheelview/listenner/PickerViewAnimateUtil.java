package com.winfo.szrsp.app.widget.wheelview.listenner;


import android.view.Gravity;

import com.winfo.szrsp.app.R;


/**
 * Created by Sai on 15/8/9.
 */
public class PickerViewAnimateUtil {
    private static final int INVALID = -1;
    /**
     * Get default animation resource when not defined by the user
     *
     * @param gravity       the gravity of the dialog
     * @param isInAnimation determine if is in or out animation. true when is is
     * @return the id of the animation resource
     */
    public static int getAnimationResource(int gravity, boolean isInAnimation) {
        switch (gravity) {
            case Gravity.BOTTOM:
                return isInAnimation ? R.anim.slide_in_bottom : R.anim.slide_out_bottom;
            case Gravity.CENTER:
                return isInAnimation ? R.anim.slide_in_center : R.anim.slide_out_center;
        }
        return INVALID;
    }
}
