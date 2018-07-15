package com.example.rui.app;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.RingtonePreference;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.zip.Inflater;

/**
 * A {@link PreferenceActivity} that presents a set of application settings. On
 * handset devices, settings are presented as a single list. On tablets,
 * settings are split by category, with category headers shown to the left of
 * the list of settings.
 * <p>
 * See <a href="http://developer.android.com/design/patterns/settings.html">
 * Android Design: Settings</a> for design guidelines and the <a
 * href="http://developer.android.com/guide/topics/ui/settings.html">Settings
 * API Guide</a> for more information on developing a Settings UI.
 */
public class setting extends AppCompatPreferenceActivity {

    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */
    private static Preference.OnPreferenceChangeListener sBindPreferenceSummaryToValueListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

            if (preference instanceof ListPreference) {
                // For list preferences, look up the correct display value in
                // the preference's 'entries' list.
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                // Set the summary to reflect the new value.
                preference.setSummary(
                        index >= 0
                                ? listPreference.getEntries()[index]
                                : null);

            } else if (preference instanceof RingtonePreference) {
                // For ringtone preferences, look up the correct display value
                // using RingtoneManager.
                if (TextUtils.isEmpty(stringValue)) {
                    // Empty values correspond to 'silent' (no ringtone).
                    preference.setSummary(R.string.pref_ringtone_silent);

                } else {
                    Ringtone ringtone = RingtoneManager.getRingtone(
                            preference.getContext(), Uri.parse(stringValue));

                    if (ringtone == null) {
                        // Clear the summary if there was a lookup error.
                        preference.setSummary(null);
                    } else {
                        // Set the summary to reflect the new ringtone display
                        // name.
                        String name = ringtone.getTitle(preference.getContext());
                        preference.setSummary(name);
                    }
                }

            } else {
                // For all other preferences, set the summary to the value's
                // simple string representation.
                preference.setSummary(stringValue);
            }
            return true;
        }
    };

    /**
     * Helper method to determine if the device has an extra-large screen. For
     * example, 10" tablets are extra-large.
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Override home navigation button to call onBackPressed (b/35152749).
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private static boolean isXLargeTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    /**
     * Binds a preference's summary to its value. More specifically, when the
     * preference's value is changed, its summary (line of text below the
     * preference title) is updated to reflect the value. The summary is also
     * immediately updated upon calling this method. The exact display format is
     * dependent on the type of preference.
     *
     * @see #sBindPreferenceSummaryToValueListener
     */
    private static void bindPreferenceSummaryToValue(Preference preference) {
        // Set the listener to watch for value changes.
        preference.setOnPreferenceChangeListener(sBindPreferenceSummaryToValueListener);

        // Trigger the listener immediately with the preference's
        // current value.
        sBindPreferenceSummaryToValueListener.onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupActionBar();
    }
//    public boolean onOptionItemSelected(MenuItem item){
//        if(item.getItemId()==R.id.homeAsUp){
//            finish();
//            return true;
//        }
//        return  super.onOptionsItemSelected(item);
//    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onIsMultiPane() {
        return isXLargeTablet(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.header_setting, target);
    }

    /**
     * This method stops fragment injection in malicious applications.
     * Make sure to deny any unknown fragments here.
     */
    protected boolean isValidFragment(String fragmentName) {
        return PreferenceFragment.class.getName().equals(fragmentName)
                || BlackListPreferenceFragment.class.getName().equals(fragmentName)
                || DataSyncPreferenceFragment.class.getName().equals(fragmentName)
                || WhiteListPreferenceFragment.class.getName().equals(fragmentName)
                || PictureBrightnessPreferenceFragment.class.getName().equals(fragmentName)
                || PlateNumberPreferenceFragment.class.getName().equals(fragmentName)
//                || PictureCompoundPreferenceFragment.class.getName().equals(fragmentName)
                || PicturePreferenceFragment.class.getName().equals(fragmentName)
                || PictureNamePreferenceFragment.class.getName().equals(fragmentName)
                || FillLightPreferenceFragment.class.getName().equals(fragmentName)
                || CameraRestartPreferenceFragment.class.getName().equals(fragmentName)
                || CameraCOMPortPreferenceFragment.class.getName().equals(fragmentName)
//                || CameraFTPPreferenceFragment.class.getName().equals(fragmentName)
//                || DataManagePreferenceFragment.class.getName().equals(fragmentName)
                || GPSPreferenceFragment.class.getName().equals(fragmentName)
                || LocalNetworkPreferenceFragment.class.getName().equals(fragmentName);
//                || LocalFTPPreferenceFragment.class.getName().equals(fragmentName)
//                || RemoteFTPPreferenceFragment.class.getName().equals(fragmentName);
    }

    /**
     * This fragment shows general preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class BlackListPreferenceFragment extends PreferenceFragment {
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
////            addPreferencesFromResource(R.xml.black_list);
////            Intent intent = new Intent(getActivity().getApplicationContext(),setting.class);
////            startActivity(intent);
////            LayoutInflater.from(getContext()).inflate(R.layout.activity_main,null);
//            setHasOptionsMenu(true);
//
//            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
//            // to their values. When their values change, their summaries are
//            // updated to reflect the new value, per the Android Design
//            // guidelines.
//            bindPreferenceSummaryToValue(findPreference("example_text"));
//            bindPreferenceSummaryToValue(findPreference("example_list"));
//        }


        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.black_list,container,false);
        }
    }

    /**
     * This fragment shows notification preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class WhiteListPreferenceFragment extends PreferenceFragment {
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            addPreferencesFromResource(R.xml.white_list);
//            setHasOptionsMenu(true);
//
//            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
//            // to their values. When their values change, their summaries are
//            // updated to reflect the new value, per the Android Design
//            // guidelines.
//            bindPreferenceSummaryToValue(findPreference("notifications_new_message_ringtone"));
//        }
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.white_list,container,false);
}
    }

    /**
     * This fragment shows data and sync preferences only. It is used when the
     * activity is showing a two-pane settings UI.
     */

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class DataSyncPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.data_example);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
            bindPreferenceSummaryToValue(findPreference("sync_frequency"));
        }
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class PicturePreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.picture_preference);
            setHasOptionsMenu(true);
            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
//           bindPreferenceSummaryToValue(findPreference("picture_preference"));
        }
    }
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    public static class PictureCompoundPreferenceFragment extends PreferenceFragment {
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            addPreferencesFromResource(R.xml.black_list);
//            setHasOptionsMenu(true);
//
//            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
//            // to their values. When their values change, their summaries are
//            // updated to reflect the new value, per the Android Design
//            // guidelines.
//            bindPreferenceSummaryToValue(findPreference("example_text"));
//            bindPreferenceSummaryToValue(findPreference("example_list"));
//        }
//    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class PictureNamePreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.picture_name_preference);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
        }
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class FillLightPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.fill_light_preference);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
            bindPreferenceSummaryToValue(findPreference("fill_light_brightness_1"));
            bindPreferenceSummaryToValue(findPreference("fill_light_brightness_2"));
            bindPreferenceSummaryToValue(findPreference("fill_light_brightness_3"));
        }
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class CameraRestartPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.camera_restart_preference);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
        }
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class CameraCOMPortPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.camera_com_port_preference);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
            bindPreferenceSummaryToValue(findPreference("baud_rate"));
            bindPreferenceSummaryToValue(findPreference("data_amount"));
            bindPreferenceSummaryToValue(findPreference("data_stop"));
            bindPreferenceSummaryToValue(findPreference("flow_control"));


        }
    }
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    public static class CameraFTPPreferenceFragment extends PreferenceFragment {
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            addPreferencesFromResource(R.xml.black_list);
//            setHasOptionsMenu(true);
//
//            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
//            // to their values. When their values change, their summaries are
//            // updated to reflect the new value, per the Android Design
//            // guidelines.
//            bindPreferenceSummaryToValue(findPreference("example_text"));
//            bindPreferenceSummaryToValue(findPreference("example_list"));
//        }
//    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class PlateNumberPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.plate_number_preference);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
            bindPreferenceSummaryToValue(findPreference("select_province"));
        }
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class PictureBrightnessPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.picture_brightness_preference);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
            bindPreferenceSummaryToValue(findPreference("picture_brightness"));
        }
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class GPSPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.gps_setting);
            setHasOptionsMenu(true);

            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
        }
    }
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    public static class DataManagePreferenceFragment extends PreferenceFragment {
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            addPreferencesFromResource(R.xml.data_manage_preference);
//            setHasOptionsMenu(true);
//            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
//            // to their values. When their values change, their summaries are
//            // updated to reflect the new value, per the Android Design
//            // guidelines.
//            bindPreferenceSummaryToValue(findPreference("example_text"));
//            bindPreferenceSummaryToValue(findPreference("example_list"));
//        }
//    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static class LocalNetworkPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.local_network_preference);
            setHasOptionsMenu(true);
            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
            // to their values. When their values change, their summaries are
            // updated to reflect the new value, per the Android Design
            // guidelines.
//            bindPreferenceSummaryToValue(findPreference("example_text"));
//            bindPreferenceSummaryToValue(findPreference("example_list"));
        }
    }
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    public static class LocalFTPPreferenceFragment extends PreferenceFragment {
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            addPreferencesFromResource(R.xml.black_list);
//            setHasOptionsMenu(true);
//
//            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
//            // to their values. When their values change, their summaries are
//            // updated to reflect the new value, per the Android Design
//            // guidelines.
//            bindPreferenceSummaryToValue(findPreference("example_text"));
//            bindPreferenceSummaryToValue(findPreference("example_list"));
//        }
//    }
//    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    public static class RemoteFTPPreferenceFragment extends PreferenceFragment {
//        @Override
//        public void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            addPreferencesFromResource(R.xml.black_list);
//            setHasOptionsMenu(true);
//
//            // Bind the summaries of EditText/List/Dialog/Ringtone preferences
//            // to their values. When their values change, their summaries are
//            // updated to reflect the new value, per the Android Design
//            // guidelines.
//            bindPreferenceSummaryToValue(findPreference("example_text"));
//            bindPreferenceSummaryToValue(findPreference("example_list"));
//        }
//    }
}
