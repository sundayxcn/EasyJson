package easy;


import android.util.Pair;

import java.util.List;

import easy.node.BaseNode;

public interface BaseOperator {

    BaseNode put(String key, Object value);

    BaseNode put(String key, String... value);

    BaseNode put(String key, int... value);

    BaseNode put(String key, byte... value);

    BaseNode put(String key, long... value);

    BaseNode put(String key, double... value);

    BaseNode put(String key, float... value);

    BaseNode put(String key, char... value);

    BaseNode put(String key, short... value);

    BaseNode put(String key, boolean... value);

    String getString(String key, String defaultValue);

    String getString(String key);

    int getInt(String key, int defaultValue);

    int getInt(String key);

    byte getByte(String key);

    byte getByte(String key, byte defaultValue);

    long getLong(String key);

    long getLong(String key, long defaultValue);

    double getDouble(String key);

    double getDouble(String key, double defaultValue);

    float getFloat(String key);

    float getFloat(String key, float defaultValue);

    char getChar(String key);

    char getChar(String key, char defaultValue);

    short getShort(String key);

    short getShort(String key, short defaultValue);

    boolean getBoolean(String key, boolean defaultValue);

    boolean getBoolean(String key);

    List<String> getStringList(String key);

    String[] getStringArray(String key);

    int[] getIntArray(String key);

    byte[] getByteArray(String key);

    long[] getLongArray(String key);

    double[] getDoubleArray(String key);

    float[] getFloatArray(String key);

    char[] getCharArray(String key);

    short[] getShortArray(String key);

    boolean[] getBooleanArray(String key);

}
