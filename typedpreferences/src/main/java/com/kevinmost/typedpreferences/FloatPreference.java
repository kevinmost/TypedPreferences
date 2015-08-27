package com.kevinmost.typedpreferences;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

final class FloatPreference extends BaseTypedPreference<Float> {
  FloatPreference(@NonNull String key, @Nullable OnPreferenceChangedListener<Float>
      onPreferenceChangedListener, @NonNull Float defaultValue) {
    super(key, onPreferenceChangedListener, defaultValue);
  }

  @Override
  protected Float get(SharedPreferences preferences) {
    return preferences.getFloat(key, defaultValue);
  }

  @Override
  protected void set(SharedPreferences.Editor editor, Float value) {
    super.set(editor, value);
    editor.putFloat(key, value);
  }
}
