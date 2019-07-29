package easy.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @auth sunday
 * @Data 2019-07-05
 * @description
 */
public class BaseTypeUtil {

    public static List<Object> object2List(Object value){
        if(value instanceof int[]){
            return base2Object((int[])value);
        }else if(value instanceof byte[]){
            return base2Object((byte[])value);
        }else if(value instanceof long[]){
            return base2Object((long[])value);
        }else if(value instanceof double[]){
            return base2Object((double[])value);
        }else if(value instanceof float[]){
            return base2Object((float[])value);
        }else if(value instanceof short[]){
            return base2Object((short[])value);
        }else if(value instanceof boolean[]){
            return base2Object((boolean[])value);
        }else if(value instanceof char[]){
            return base2Object((char[])value);
        }else if(value instanceof String[]){
            return base2Object((String[])value);
        }else{
            return new ArrayList<>();
        }
    }


    public static List<Object> base2Object(int... value){
        List<Object> list = new ArrayList<>();
        for(Object v : value){
            list.add(v);
        }
        return list;
    }

    public static List<Object> base2Object(byte... value){
        List<Object> list = new ArrayList<>();
        for(Object v : value){
            list.add(v);
        }
        return list;
    }

    public static List<Object> base2Object(long... value){
        List<Object> list = new ArrayList<>();
        for(Object v : value){
            list.add(v);
        }
        return list;
    }

    public static List<Object> base2Object(double... value){
        List<Object> list = new ArrayList<>();
        for(Object v : value){
            list.add(v);
        }
        return list;
    }

    public static List<Object> base2Object(float... value){
        List<Object> list = new ArrayList<>();
        for(Object v : value){
            list.add(v);
        }
        return list;
    }

    public static List<Object> base2Object(short... value){
        List<Object> list = new ArrayList<>();
        for(Object v : value){
            list.add(v);
        }
        return list;
    }

    public static List<Object> base2Object(boolean... value){
        List<Object> list = new ArrayList<>();
        for(Object v : value){
            list.add(v);
        }
        return list;
    }

    public static List<Object> base2Object(char... value){
        List<Object> list = new ArrayList<>();
        for(Object v : value){
            list.add(v);
        }
        return list;
    }

    public static List<Object> base2Object(String... value){
        List<Object> list = new ArrayList<>();
        for(Object v : value){
            list.add(v);
        }
        return list;
    }


    public static  boolean isBaseType(Object value) {
        Class className = value.getClass();
        if(className.equals(String.class)){
            return true;
        }else if(className.equals(Integer.class)) {
            return true;
        } else if(className.equals(Byte.class)) {
            return true;
        } else if(className.equals(Long.class)) {
            return true;
        } else if(className.equals(Double.class)) {
            return true;
        } else if(className.equals(Float.class)) {
            return true;
        } else if(className.equals(Character.class)) {
            return true;
        } else if(className.equals(Short.class)) {
            return true;
        } else if(className.equals(Boolean.class)) {
            return true;
        }else {
            return false;
        }
    }


    public static HashMap<String,Boolean> baseType = new HashMap<>();
    static {
        baseType.put("String",true);
        baseType.put("Integer",true);
        baseType.put("Byte",true);
        baseType.put("Long",true);
        baseType.put("Double",true);
        baseType.put("Float",true);
        baseType.put("Character",true);
        baseType.put("Short",true);
        baseType.put("Boolean",true);
    }

    public static boolean isStringBaseType(String type){
        return baseType.get(type) != null;
    }

}
