package com.kevinmost.typedpreferences.prefs;

import android.content.SharedPreferences;

import java.util.Collections;
import java.util.Set;

public class PreferenceUtil {

  @SuppressWarnings("unchecked")
  static <T> TypedPreference<T> from(String key,
                                     OnPreferenceSetListener<T> listener,
                                     T defaultValue) {
    final Class<?> clazz = defaultValue.getClass();
    if (clazz.isAssignableFrom(String.class)) {
      return (TypedPreference<T>) new StringPreference(key, (OnPreferenceSetListener<String>) listener, (String) defaultValue);
    } else if (clazz.isAssignableFrom(Boolean.class)) {
      return (TypedPreference<T>) new BooleanPreference(key, (OnPreferenceSetListener<Boolean>) listener, (Boolean) defaultValue);
    } else if (clazz.isAssignableFrom(Integer.class)) {
      return (TypedPreference<T>) new IntegerPreference(key, (OnPreferenceSetListener<Integer>) listener, (Integer) defaultValue);
    } else if (clazz.isAssignableFrom(Float.class)) {
      return (TypedPreference<T>) new FloatPreference(key, (OnPreferenceSetListener<Float>) listener, (Float) defaultValue);
    } else if (clazz.isAssignableFrom(Long.class)) {
      return (TypedPreference<T>) new LongPreference(key, (OnPreferenceSetListener<Long>) listener, (Long) defaultValue);
    } else if (clazz.isAssignableFrom(Set.class)) {
      final Set castValue = (Set) defaultValue;
      final Set<String> checkedDefaultValue;
      if (castValue.isEmpty()) {
        checkedDefaultValue = Collections.emptySet();
      } else {
        if (castValue.iterator().next() instanceof String) {
          checkedDefaultValue = castValue;
        } else {
          throw new IllegalArgumentException("defaultValue given was a set, but set didn't contain elements of type String");
        }
      }
      return (TypedPreference<T>) new StringSetPreference(key, (OnPreferenceSetListener<Set<String>>) listener, checkedDefaultValue);
    } else {
      throw new IllegalArgumentException("defaultValue given was of type " + clazz.getName());
    }
  }
}
