package com.kevinmost.typedpreferences.preference;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.kevinmost.typedpreferences.OnPreferenceChangedListener;

import java.util.Set;

final class StringSetPreference extends BaseTypedPreference<Set<String>> {
  StringSetPreference(@NonNull String key, @Nullable OnPreferenceChangedListener<Set<String>>
      onPreferenceChangedListener, @NonNull Set<String> defaultValue) {
    super(key, onPreferenceChangedListener, defaultValue);
  }

  @Override
  protected Set<String> get(SharedPreferences preferences) {
    return preferences.getStringSet(key, defaultValue);
  }

  @Override
  protected void set(SharedPreferences.Editor editor, Set<String> value) {
    super.set(editor, value);
    editor.putStringSet(key, value);
  }
}
