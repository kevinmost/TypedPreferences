package com.kevinmost.typedpreferences.prefs;

import android.content.SharedPreferences;

public interface TypedPreference<T> {
  T get(SharedPreferences preferences);
  void set(SharedPreferences.Editor editor, T value);

  class Builder<T> {
    private String key;
    private OnPreferenceSetListener<T> onPreferenceSetListener;
    private T defaultValue;


    public Builder(T defaultValue) {
      this.defaultValue = defaultValue;
    }

    public Builder<T> setDefaultValue(T defaultValue) {
      this.defaultValue = defaultValue;
      return this;
    }

    public Builder<T> setOnPreferenceSetListener(OnPreferenceSetListener<T> onPreferenceSetListener) {
      this.onPreferenceSetListener = onPreferenceSetListener;
      return this;
    }

    public Builder<T> setKey(String key) {
      this.key = key;
      return this;
    }

    public TypedPreference<T> build() {
      return new BaseTypedPreference<T>(key, onPreferenceSetListener, defaultValue) {
        @SuppressWarnings("unchecked")
        @Override
        public T get(SharedPreferences preferences) {
          return (T) preferences.getAll().get(key);
        }

        @Override
        public void set(SharedPreferences.Editor editor, T value) {
          if (onPreferenceSetListener != null) {
            onPreferenceSetListener.onPreferenceSet(value);
          }
          PreferenceUtil.saveToPreferencesUnsafe(editor, key, value);
        }
      };
    }
  }
}
