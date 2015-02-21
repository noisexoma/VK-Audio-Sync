package com.BBsRs.vkaudiosync;

import org.holoeverywhere.addon.AddonSlider;
import org.holoeverywhere.addon.Addons;
import org.holoeverywhere.app.Activity;
import org.holoeverywhere.preference.PreferenceManager;
import org.holoeverywhere.preference.SharedPreferences;
import org.holoeverywhere.slider.SliderMenu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.BBsRs.vkaudiosync.Fragments.AboutFragment;
import com.BBsRs.vkaudiosync.Fragments.DownloadManagerFragment;
import com.BBsRs.vkaudiosync.Fragments.FriendsGroupsListFragment;
import com.BBsRs.vkaudiosync.Fragments.MusicListFragment;
import com.BBsRs.vkaudiosync.Fragments.SettingsFragment;
import com.BBsRs.vkaudiosync.VKApiThings.Account;
import com.BBsRs.vkaudiosync.VKApiThings.Constants;
import com.perm.kate.api.Api;

@Addons(AddonSlider.class)
public class ContentShowActivity extends Activity {
	public AddonSlider.AddonSliderA addonSlider() {
	      return addon(AddonSlider.class);
	}
	
	//preferences 
    SharedPreferences sPref;
	
	SliderMenu sliderMenu;
	
    /*----------------------------VK API-----------------------------*/
    Account account=new Account();
    Api api;
    /*----------------------------VK API-----------------------------*/
    
    //for retrieve data from activity
    Bundle bundle;
    
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	   	/*----------------------------VK API-----------------------------*/
    	//retrieve old session
        account.restore(getApplicationContext());
        /*----------------------------VK API-----------------------------*/
        
	    //init slider menu
        sliderMenu = addonSlider().obtainDefaultSliderMenu(R.layout.menu);
        
        //set up preferences
        sPref = PreferenceManager.getDefaultSharedPreferences(this);
        
        Bundle bundleMyMusic = new Bundle();
        bundleMyMusic.putLong(Constants.BUNDLE_USER_ID, account.user_id);
        bundleMyMusic.putInt(Constants.BUNDLE_MUSIC_TYPE, Constants.MAIN_MUSIC_USER);
        bundleMyMusic.putInt(Constants.BUNDLE_MAIN_WALL_TYPE, Constants.MAIN_MUSIC);
        
        Bundle bundleFriends = new Bundle();
        bundleFriends.putLong(Constants.BUNDLE_USER_ID, account.user_id);
        bundleFriends.putInt(Constants.BUNDLE_FRIENDS_GROUPS_TYPE, Constants.FRIENDS);
        
        Bundle bundleGroups = new Bundle();
        bundleGroups.putLong(Constants.BUNDLE_USER_ID, account.user_id);
        bundleGroups.putInt(Constants.BUNDLE_FRIENDS_GROUPS_TYPE, Constants.GROUPS);
        
        sliderMenu.add(getResources().getStringArray(R.array.slider_menu)[0].toUpperCase()).setCustomLayout(R.layout.custom_slider_menu_item).clickable(false).setTextAppereance(1);
        sliderMenu.add(getResources().getStringArray(R.array.slider_menu)[1], MusicListFragment.class, bundleMyMusic, new int[]{R.color.slider_menu_custom_color_black, R.color.slider_menu_custom_color_orange}).setTextAppereanceInverse(1);
        sliderMenu.add(getResources().getStringArray(R.array.slider_menu)[2], FriendsGroupsListFragment.class, bundleFriends, new int[]{R.color.slider_menu_custom_color_black, R.color.slider_menu_custom_color_orange}).setTextAppereanceInverse(1);
        sliderMenu.add(getResources().getStringArray(R.array.slider_menu)[3], FriendsGroupsListFragment.class, bundleGroups, new int[]{R.color.slider_menu_custom_color_black, R.color.slider_menu_custom_color_orange}).setTextAppereanceInverse(1);
        sliderMenu.add(getResources().getStringArray(R.array.slider_menu)[4].toUpperCase()).setCustomLayout(R.layout.custom_slider_menu_item).clickable(false).setTextAppereance(1);
        sliderMenu.add(getResources().getStringArray(R.array.slider_menu)[5], DownloadManagerFragment.class, new int[]{R.color.slider_menu_custom_color_black, R.color.slider_menu_custom_color_orange}).setTextAppereanceInverse(1);
        sliderMenu.add(getResources().getStringArray(R.array.slider_menu)[6], SettingsFragment.class, new int[]{R.color.slider_menu_custom_color_black, R.color.slider_menu_custom_color_orange}).setTextAppereanceInverse(1);
        sliderMenu.add(getResources().getStringArray(R.array.slider_menu)[7], AboutFragment.class, new int[]{R.color.slider_menu_custom_color_black, R.color.slider_menu_custom_color_orange}).setTextAppereanceInverse(1);
        
        bundle = getIntent().getExtras(); 

        if (bundle != null) {
        	if (savedInstanceState == null)
                sliderMenu.setCurrentPage(bundle.getInt(Constants.INITIAL_PAGE));
        } else {
        	if (savedInstanceState == null)
                sliderMenu.setCurrentPage(1);
        }
        
        
	}
	
	@Override
	public void onPause() {
		super.onPause();
		unregisterReceiver(openMenuDrawer);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		//turn up download receiver
        registerReceiver(openMenuDrawer, new IntentFilter(Constants.OPEN_MENU_DRAWER));
	}
	
	private BroadcastReceiver openMenuDrawer = new BroadcastReceiver() {

	    @Override
	    public void onReceive(Context context, Intent intent) {
	    	if (addonSlider().isDrawerOpen(addonSlider().getLeftView()))
	    		  addonSlider().closeLeftView();
	    	  else 
	    		  addonSlider().openLeftView();
	    }
	};

}
