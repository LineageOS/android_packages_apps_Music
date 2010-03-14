/**
 * Android Music Settings by cyanogen (Steve Kondik)
 * 
 * Released under the Apache 2.0 license
 */
package com.android.music;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class MusicSettingsActivity extends PreferenceActivity {

	static final String KEY_SCREEN_ON_WHILE_PLUGGED_IN = "screen_on_while_plugged_in";
	
	static final String KEY_UNPAUSE_ON_HEADSET_PLUG = "unpause_on_headset_plug";
	
	static final String KEY_DOUBLETAP_TRACKBALL_SKIP = "doubletap_trackball_skip";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
	}

}
