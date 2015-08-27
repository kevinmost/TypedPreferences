package com.kevinmost.typedpreferences.preference;

import com.kevinmost.typedpreferences.OnPreferenceChangedListener;
import com.kevinmost.typedpreferences.Preferences;

public interface TypedPreference<T> {
  T get();
  void set(T value);

  void register(Preferences preferences);
  Preferences getRegisteredPreferences();

  final class Builder<T> {
    private String key;
    private OnPreferenceChangedListener<T> onPreferenceChangedListener;
    private T defaultValue;
    private Preferences preferencesToRegisterTo;


    public Builder<T> setDefaultValue(T defaultValue) {
      this.defaultValue = defaultValue;
      return this;
    }

    public Builder<T> setOnPreferenceChangedListener(OnPreferenceChangedListener<T> onPreferenceChangedListener) {
      this.onPreferenceChangedListener = onPreferenceChangedListener;
      return this;
    }

    public Builder<T> setKey(String key) {
      this.key = key;
      return this;
    }

    public Builder<T> setRegisterTo(Preferences preferences) {
      this.preferencesToRegisterTo = preferences;
      return this;
    }

    public TypedPreference<T> build() {
      return PreferenceUtil.from(key, onPreferenceChangedListener, defaultValue, preferencesToRegisterTo);
    }
  }
}
