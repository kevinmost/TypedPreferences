package com.kevinmost.typedpreferences.prefs;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public final class Preferences {

  private final SharedPreferences sharedPreferences;

  private SharedPreferences.Editor editor;

  private Preferences(Application app) {
    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(app);
  }

  public SharedPreferences.Editor getEditor() {
    if (editor == null) {
      discardChanges();
    }
    return editor;
  }

  public boolean commitChanges() {
    return editor.commit();
  }

  @SuppressLint("CommitPrefEdits")
  public void discardChanges() {
    editor = sharedPreferences.edit();
  }

  public <T> void set(TypedPreference<T> preference, T value) {
    preference.set(getEditor(), value);
  }

  public <T> T get(TypedPreference<T> preference) {
    return preference.get(sharedPreferences);
  }
}
