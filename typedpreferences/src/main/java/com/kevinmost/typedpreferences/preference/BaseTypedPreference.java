package com.kevinmost.typedpreferences.preference;

import android.content.SharedPreferences;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.kevinmost.typedpreferences.OnPreferenceChangedListener;
import com.kevinmost.typedpreferences.Preferences;

abstract class BaseTypedPreference<T> implements TypedPreference<T> {

  @NonNull
  protected final String key;

  @Nullable
  protected final OnPreferenceChangedListener<T> onPreferenceChangedListener;

  @NonNull
  protected final T defaultValue;

  private Preferences preferences;

  BaseTypedPreference(@NonNull String key,
                      @Nullable OnPreferenceChangedListener<T> onPreferenceChangedListener,
                      @NonNull T defaultValue) {
    this.key = key;
    this.onPreferenceChangedListener = onPreferenceChangedListener;
    this.defaultValue = defaultValue;
  }

  @Override
  public final void set(T value) {
    ensurePreferencesSet();
    set(preferences.getEditor(), value);
  }

  @Override
  public final T get() {
    ensurePreferencesSet();
    return get(preferences.getPreferences());
  }

  @Override
  public final void register(Preferences preferences) {
    this.preferences = preferences;
  }

  @Override
  public final Preferences getRegisteredPreferences() {
    return preferences;
  }

  @CallSuper
  protected void set(SharedPreferences.Editor editor, T value) {
    if (onPreferenceChangedListener != null) {
      onPreferenceChangedListener.onPreferenceChanged(value);
    }
  }

  protected abstract T get(SharedPreferences preferences);

  private void ensurePreferencesSet() {
    if (preferences == null) {
      final String errorMsg =
          String.format("TypedPreference %s has not yet been registered to a Preferences object",
          key);
      throw new IllegalStateException(errorMsg);
    }
  }
}
