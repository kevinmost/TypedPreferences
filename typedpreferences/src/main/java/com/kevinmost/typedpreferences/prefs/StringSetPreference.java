package com.kevinmost.typedpreferences.prefs;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Set;

public class StringSetPreference extends BaseTypedPreference<Set<String>> {
  public StringSetPreference(@NonNull String key, @Nullable OnPreferenceSetListener<Set<String>>
      onPreferenceSetListener, @NonNull Set<String> defaultValue) {
    super(key, onPreferenceSetListener, defaultValue);
  }

  @Override
  public Set<String> get(SharedPreferences preferences) {
    return preferences.getStringSet(key, defaultValue);
  }

  @Override
  public void set(SharedPreferences.Editor editor, Set<String> value) {
    super.set(editor, value);
    editor.putStringSet(key, value);
  }
}
