package com.BBsRs.vkaudiosync.Fragments;

import org.holoeverywhere.preference.Preference;
import org.holoeverywhere.preference.Preference.OnPreferenceClickListener;
import org.holoeverywhere.preference.PreferenceFragment;
import org.holoeverywhere.preference.PreferenceManager;
import org.holoeverywhere.preference.SharedPreferences;
import org.holoeverywhere.widget.Toast;

import android.content.Intent;
import android.os.Bundle;

import com.BBsRs.vkaudiosync.DirChooseActivity;
import com.BBsRs.vkaudiosync.LoaderActivity;
import com.BBsRs.vkaudiosync.R;
import com.BBsRs.vkaudiosync.VKApiThings.Constants;
import com.nostra13.universalimageloader.core.ImageLoader;

public class SettingsFragment extends PreferenceFragment {
	
	//preferences 
    SharedPreferences sPref;
    
    Preference chooseDir;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        addPreferencesFromResource(R.xml.settings);
        
      //set up preferences
        sPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        
        chooseDir = (Preference) findPreference ("preference_folder");
        chooseDir.setSummary(sPref.getString(Constants.DOWNLOAD_DIRECTORY, android.os.Environment.getExternalStorageDirectory()+"/Music")+"/");
        chooseDir.setOnPreferenceClickListener(new OnPreferenceClickListener(){
			@Override
			public boolean onPreferenceClick(Preference preference) {
				getActivity().startActivity(new Intent(getActivity(), DirChooseActivity.class));
				return false;
			}
        });
        
        Preference clearCache = (Preference) findPreference (Constants.PREFERENCE_IMAGELOADER_CLEAR_CACHE);
        clearCache.setOnPreferenceClickListener(new OnPreferenceClickListener(){
			@Override
			public boolean onPreferenceClick(Preference preference) {
				try {
				ImageLoader.getInstance().clearMemoryCache();
				ImageLoader.getInstance().clearDiskCache();
				} catch (Exception e){
					e.printStackTrace();
				} finally {
					Toast.makeText(getActivity(), getString(R.string.imageloader_clear_cache_success), Toast.LENGTH_LONG).show();
				}
				return false;
			}
        });
        
        Preference logOut = (Preference) findPreference ("preference_logout");
        logOut.setOnPreferenceClickListener(new OnPreferenceClickListener(){
			@Override
			public boolean onPreferenceClick(Preference preference) {
				sPref.edit().clear().commit();
				getActivity().startActivity(new Intent(getActivity(), LoaderActivity.class));
				getActivity().finish();
				return false;
			}
        });
	}
	
	@Override
	public void onResume() {
		super.onResume();
		getSupportActionBar().setTitle(getResources().getStringArray(R.array.slider_menu)[6]);
		getSupportActionBar().setSubtitle(null);
		
		sPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
		chooseDir.setSummary(sPref.getString(Constants.DOWNLOAD_DIRECTORY, android.os.Environment.getExternalStorageDirectory()+"/Music")+"/");
		
		sPref.edit().putBoolean(Constants.OTHER_FRAGMENT, true).commit();
	}
	

}
