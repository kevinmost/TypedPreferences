package com.kevinmost.typedpreferences.prefs;

import android.content.SharedPreferences;

import java.util.Collections;
import java.util.Set;

public class PreferenceUtil {
  static void saveToPreferencesUnsafe(SharedPreferences.Editor editor, String key, Object value) {
    final Class<?> valueClass = value.getClass();
    if (valueClass.isAssignableFrom(Integer.class)) {
      editor.putInt(key, (int) value);
    } else if (valueClass.isAssignableFrom(String.class)) {
      editor.putString(key, (String) value);
    } else if (valueClass.isAssignableFrom(Float.class)) {
      editor.putFloat(key, (float) value);
    } else if (valueClass.isAssignableFrom(Long.class)) {
      editor.putLong(key, (long) value);
    } else if (valueClass.isAssignableFrom(Boolean.class)) {
      editor.putBoolean(key, (boolean) value);
    } else if (valueClass.isAssignableFrom(Set.class)) {
      final Set<?> castValue = (Set<?>) value;
      if (castValue.isEmpty()) {
        editor.putStringSet(key, Collections.<String>emptySet());
      }
      final Object firstElement = castValue.iterator().next();
      if (firstElement instanceof String) {
        editor.putStringSet(key, (Set<String>) castValue);
      } else {
        throw new IllegalArgumentException("A Set was passed, but did not contain elements of type String.");
      }
    } else {
      throw new IllegalArgumentException("Attempted to save value of type " + value.getClass().getName() + " to SharedPreferences");
    }
  }
}
