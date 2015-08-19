package com.kevinmost.typedpreferences.prefs;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

public final class Preferences {

  private final SharedPreferences sharedPreferences;

  private SharedPreferences.Editor editor;

  private Preferences(Application app) {
    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(app);
  }

  public static Preferences of(@NonNull Application app) {
    return new Preferences(app);
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
}
