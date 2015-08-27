package com.kevinmost.typedpreferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.kevinmost.typedpreferences.preference.TypedPreference;

public final class Preferences {

  private final SharedPreferences sharedPreferences;

  private SharedPreferences.Editor editor;

  public Preferences(Context context) {
    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
  }

  public Preferences(SharedPreferences preferences) {
    this.sharedPreferences = preferences;
  }

  public SharedPreferences getPreferences() {
    return sharedPreferences;
  }

  public SharedPreferences.Editor getEditor() {
    if (editor == null) {
      discardChanges();
    }
    return editor;
  }

  public void registerPreference(TypedPreference<?> preference) {
    preference.register(this);
  }

  public boolean commitChanges() {
    return editor.commit();
  }

  @SuppressLint("CommitPrefEdits")
  public void discardChanges() {
    editor = sharedPreferences.edit();
  }
}
