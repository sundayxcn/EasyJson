package easy.node;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import easy.BaseOperator;
import easy.EasyJson;

import static easy.EasyJson.getObject;
import static easy.EasyJson.getTargetNode;
import static easy.EasyJson.putList;

/**
 * @auth sunday
 * @Data 2019-11-26
 * @description
 */
public class OperatorNode implements BaseOperator {

    protected BaseNode thisNode;

    public BaseNode put(String key, Object value) {
        BaseNode parentNode = getTargetNode(thisNode, key);
        if (value instanceof List) {
            List<Object> list = (List<Object>) value;
            putList(parentNode, key, list);
        } else {
            EasyJson.put(parentNode, key, value);
        }
        return thisNode;
    }

    public BaseNode put(String key, String... value) {

        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(thisNode, key);
            List<Object> list = new ArrayList<>();
            for (Object v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }
        return thisNode;
    }

    public BaseNode put(String key, int... value) {
        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(thisNode, key);
            List<Object> list = new ArrayList<>();
            for (Object v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }
        return thisNode;
    }

    public BaseNode put(String key, byte... value) {
        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(thisNode, key);
            List<Object> list = new ArrayList<>();
            for (Object v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }
        return thisNode;
    }

    public BaseNode put(String key, long... value) {
        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(thisNode, key);
            List<Object> list = new ArrayList<>();
            for (Object v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }
        return thisNode;
    }

    public BaseNode put(String key, double... value) {
        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(thisNode, key);
            List<Object> list = new ArrayList<>();
            for (Object v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }
        return thisNode;
    }

    public BaseNode put(String key, float... value) {
        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(thisNode, key);
            List<Object> list = new ArrayList<>();
            for (Object v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }

        return thisNode;
    }

    public BaseNode put(String key, char... value) {
        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(thisNode, key);
            List<Object> list = new ArrayList<>();
            for (Object v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }
        return thisNode;
    }

    public BaseNode put(String key, short... value) {
        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(thisNode, key);
            List<Object> list = new ArrayList<>();
            for (int v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }
        return thisNode;
    }

    public BaseNode put(String key, boolean... value) {
        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(thisNode, key);
            List<Object> list = new ArrayList<>();
            for (Object v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }
        return thisNode;
    }

    public String getString(String key, String defaultValue) {

        Object object = getObject(thisNode, key);
        if (object == null) {
            return defaultValue;
        } else {
            if (object instanceof BaseNode) {
                return ((BaseNode) object).build();
            } else {
                String value = object.toString();
                if (TextUtils.isEmpty(value)) {
                    return defaultValue;
                } else {
                    return value;
                }
            }
        }

    }

    @Override
    public String getString(String key) {
        return getString(key, "");
    }

    @Override
    public int getInt(String key, int defaultValue) {
        Object object = getObject(thisNode, key);
        if (object == null) {
            return defaultValue;
        } else {
            return (int) object;
        }
    }

    @Override
    public int getInt(String key) {
        return getInt(key, 0);
    }

    @Override
    public byte getByte(String key) {
        byte b = '0';
        return getByte(key, b);
    }

    @Override
    public byte getByte(String key, byte defaultValue) {
        Object object = getObject(thisNode, key);
        if (object == null) {
            return defaultValue;
        } else {
            return (byte) object;
        }
    }

    @Override
    public long getLong(String key) {
        return getLong(key, 0);
    }

    @Override
    public long getLong(String key, long defaultValue) {
        Object object = getObject(thisNode, key);
        if (object == null) {
            return defaultValue;
        } else {
            return (long) object;
        }
    }

    @Override
    public double getDouble(String key) {
        return getDouble(key, 0);
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        Object object = getObject(thisNode, key);
        if (object == null) {
            return defaultValue;
        } else {
            return (double) object;
        }
    }

//    @Override
//    public float getFloat(String key) {
//        return getFloat(key, 0f);
//    }
//
//    @Override
//    public float getFloat(String key, float defaultValue) {
//        Object object = getObject(thisNode, key);
//        if (object == null) {
//            return defaultValue;
//        } else {
//            return (float) object;
//        }
//    }

    @Override
    public char getChar(String key) {
        return getChar(key, '0');
    }

    @Override
    public char getChar(String key, char defaultValue) {
        Object object = getObject(thisNode, key);
        if (object == null) {
            return defaultValue;
        } else {
            return (char) object;
        }
    }

    @Override
    public short getShort(String key) {
        short v = '0';
        return getShort(key, v);
    }

    @Override
    public short getShort(String key, short defaultValue) {
        Object object = getObject(thisNode, key);
        if (object == null) {
            return defaultValue;
        } else {
            return (short) object;
        }
    }


    public boolean getBoolean(String key, boolean defaultValue) {
        Object object = getObject(thisNode, key);
        if (object == null) {
            return defaultValue;
        } else {
            return (boolean) object;
        }
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    @Override
    public List<String> getStringList(String key) {
        List<String> list = EasyJson.getList(thisNode,key,String.class);
        return list;
    }

    @Override
    public String[] getStringArray(String key) {
        List<String> list = EasyJson.getList(thisNode,key,String.class);
        String[] strings = new String[0];
        strings =  list.toArray(strings);
        return strings;
    }

    @Override
    public int[] getIntArray(String key) {
        List<Integer> list = EasyJson.getList(thisNode,key,Integer.class);
        int size = list.size();
        int[] ints = new int[size];
        for(int i = 0; i < list.size();i++){
            ints[i] = list.get(i);
        }
        return ints;
    }

    @Override
    public byte[] getByteArray(String key) {
        List<Byte> list = EasyJson.getList(thisNode,key,Byte.class);
        int size = list.size();
        byte[] ints = new byte[size];
        for(int i = 0; i < list.size();i++){
            ints[i] = list.get(i);
        }
        return ints;
    }

    @Override
    public long[] getLongArray(String key) {
        List<Long> list = EasyJson.getList(thisNode,key,Long.class);
        int size = list.size();
        long[] ints = new long[size];
        for(int i = 0; i < list.size();i++){
            ints[i] = list.get(i);
        }
        return ints;
    }

    @Override
    public double[] getDoubleArray(String key) {
        List<Double> list = EasyJson.getList(thisNode,key,Double.class);
        int size = list.size();
        double[] ints = new double[size];
        for(int i = 0; i < list.size();i++){
            ints[i] = list.get(i);
        }
        return ints;
    }

//    @Override
//    public float[] getFloatArray(String key) {
//        List<Float> list = EasyJson.getList(thisNode,key,Float.class);
//        int size = list.size();
//        float[] ints = new float[size];
//        for(int i = 0; i < list.size();i++){
//            ints[i] = list.get(i);
//        }
//        return ints;
//    }

    @Override
    public char[] getCharArray(String key) {
        List<Character> list = EasyJson.getList(thisNode,key,Character.class);
        int size = list.size();
        char[] ints = new char[size];
        for(int i = 0; i < list.size();i++){
            ints[i] = list.get(i);
        }
        return ints;
    }

    @Override
    public short[] getShortArray(String key) {
        List<Short> list = EasyJson.getList(thisNode,key,Short.class);
        int size = list.size();
        short[] ints = new short[size];
        for(int i = 0; i < list.size();i++){
            ints[i] = list.get(i);
        }
        return ints;
    }

    @Override
    public boolean[] getBooleanArray(String key) {
        List<Boolean> list = EasyJson.getList(thisNode,key,Boolean.class);
        int size = list.size();
        boolean[] ints = new boolean[size];
        for(int i = 0; i < list.size();i++){
            ints[i] = list.get(i);
        }
        return ints;
    }


}
