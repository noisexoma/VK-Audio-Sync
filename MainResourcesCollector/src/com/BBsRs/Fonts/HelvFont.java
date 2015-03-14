package com.BBsRs.Fonts;

import org.holoeverywhere.widget.Button;
import org.holoeverywhere.widget.EditText;
import org.holoeverywhere.widget.TextView;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.SearchView.SearchAutoComplete;

public class HelvFont {
	public static final HelvFont HELV_LIGHT = new HelvFont("fonts/HelveticaNeueCyr-Light.otf");
	public static final HelvFont HELV_ROMAN = new HelvFont("fonts/HelveticaNeueCyr-Roman.otf");
	public static final HelvFont HELV_MEDIUM = new HelvFont("fonts/HelveticaNeueCyr-Medium.otf");
	public static final HelvFont HELV_THIN = new HelvFont("fonts/HelveticaNeueCyr-Thin.otf");
	private final String assetName;
	private volatile Typeface typeface;

	public HelvFont(String assetName) {
		this.assetName = assetName;
	}

	public void apply(Context context, TextView textView) {
		if (typeface == null) {
			synchronized (this) {
				if (typeface == null) {
					typeface = Typeface.createFromAsset(context.getAssets(), assetName);
				}
			}
		}
		textView.setTypeface(typeface);
	}
	
	public void apply(Context context, Button button) {
		if (typeface == null) {
			synchronized (this) {
				if (typeface == null) {
					typeface = Typeface.createFromAsset(context.getAssets(), assetName);
				}
			}
		}
		button.setTypeface(typeface);
	}
	
	public void apply(Context context, SearchAutoComplete mQueryTextView) {
		if (typeface == null) {
			synchronized (this) {
				if (typeface == null) {
					typeface = Typeface.createFromAsset(context.getAssets(), assetName);
				}
			}
		}
		mQueryTextView.setTypeface(typeface);
	}
	
	public void apply(Context context, EditText mEditText) {
		if (typeface == null) {
			synchronized (this) {
				if (typeface == null) {
					typeface = Typeface.createFromAsset(context.getAssets(), assetName);
				}
			}
		}
		mEditText.setTypeface(typeface);
	}

}
