package org.example.sudoku;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.content.Context;
import android.preference.PreferenceManager;

public class Prefs extends PreferenceActivity {
	// Option names and default values
	private static final String OPT_MUSIC = "music";
	private static final boolean OPT_MUSIC_DEF = true;
	private static final String OPT_HINTS = "hints";
	private static final boolean OPT_HINTS_DEF = true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
	}
	
	/** Get the current value of the music option */
	public static boolean getMusic(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
		.getBoolean(OPT_MUSIC, OPT_MUSIC_DEF);
	}
	
	/** Get the current value of the hints option */
	public static boolean getHints(Context context) {
		return PreferenceManager.getDefaultSharedPreferences(context)
		.getBoolean(OPT_HINTS, OPT_HINTS_DEF);
	}
}