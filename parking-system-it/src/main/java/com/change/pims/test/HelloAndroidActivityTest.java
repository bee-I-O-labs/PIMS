package com.change.pims.test;

import android.test.ActivityInstrumentationTestCase2;
import com.change.pims.*;

public class HelloAndroidActivityTest extends ActivityInstrumentationTestCase2<SplashScreenActivity> {

    public HelloAndroidActivityTest() {
        super(SplashScreenActivity.class);
    }

    public void testActivity() {
        SplashScreenActivity activity = getActivity();
        assertNotNull(activity);
    }
}

