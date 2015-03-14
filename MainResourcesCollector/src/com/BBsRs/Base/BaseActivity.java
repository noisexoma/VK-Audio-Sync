package com.BBsRs.Base;

import org.holoeverywhere.app.Activity;
import org.holoeverywhere.widget.TextView;

import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.View;

import com.BBsRs.Fonts.HelvFont;
import com.BBsRs.vkaudiosync.resources.collector.R;

public class BaseActivity extends Activity{
    
	@Override
	public void onResume(){
		super.onResume();
		getSupportActionBar().setSubtitle(null);
		getSupportActionBar().setTitle(null);
	}
	
    public void setTitle(String title){
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayShowCustomEnabled(true);
		View actionTitle = getLayoutInflater().inflate(R.layout.action_bar, null);
		//set font
		HelvFont.HELV_LIGHT.apply(this, ((TextView)actionTitle.findViewById(R.id.titleActionBar)));
		((TextView)actionTitle.findViewById(R.id.titleActionBar)).setText(title);
		((TextView)actionTitle.findViewById(R.id.subtitleActionBar)).setVisibility(View.GONE);
		actionBar.setCustomView(actionTitle,
		        new ActionBar.LayoutParams(
		                ActionBar.LayoutParams.WRAP_CONTENT,
		                ActionBar.LayoutParams.MATCH_PARENT,
		                Gravity.CENTER
		        )
		);
    }
    
    public void setTitleSubtitle(String title, String subtitle){
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayShowCustomEnabled(true);
		View actionTitle = getLayoutInflater().inflate(R.layout.action_bar, null);
		//set font
		HelvFont.HELV_LIGHT.apply(this, ((TextView)actionTitle.findViewById(R.id.titleActionBar)));
		HelvFont.HELV_LIGHT.apply(this, ((TextView)actionTitle.findViewById(R.id.subtitleActionBar)));
		((TextView)actionTitle.findViewById(R.id.titleActionBar)).setText(title);
		((TextView)actionTitle.findViewById(R.id.subtitleActionBar)).setText(subtitle);
		actionBar.setCustomView(actionTitle,
		        new ActionBar.LayoutParams(
		                ActionBar.LayoutParams.WRAP_CONTENT,
		                ActionBar.LayoutParams.MATCH_PARENT,
		                Gravity.CENTER
		        )
		);
    }
}
