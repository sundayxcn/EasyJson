package easy.node;


import android.text.TextUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import easy.utils.Adapter;
import easy.utils.BaseTypeUtil;

import static easy.json.JsonBuild.ARRAY_END;
import static easy.json.JsonBuild.ARRAY_START;
import static easy.json.JsonBuild.COMMA;
import static easy.json.JsonBuild.DIV;
import static easy.json.JsonBuild.DOUBLE_Q;
import static easy.json.JsonBuild.NODE_END;
import static easy.json.JsonBuild.NODE_START;
import static easy.json.JsonBuild.STOP;

/**
 * @auth sunday
 * @Data 2019-07-08
 * @description
 */
public class NodeBuild {
    int TYPE_INT = 0;
    int TYPE_LONG = 1;
    int TYPE_DOUBLE = 2;
    public static final char BOOLEAN_TRUE_STAR = 't';
    public static final char BOOLEAN_FLASE_STAR = 'f';
    private BaseNode mRootNode;
    private BaseNode mParentNode;
    //private Vector<Char>
    private char[] jsonArray;
    private int arrayIndex;
    private int size;
    private String key;
    private String value;
    private boolean isKey = true;
    private boolean isArray;
    private char[] charString = new char[1024];

    public NodeBuild() {

    }

    public BaseNode parse(String json) {
        jsonArray = json.toCharArray();
        size = jsonArray.length;
        while (arrayIndex < size) {
            parse();
        }
        return mRootNode;
    }

    public void parse() {
        char current = jsonArray[arrayIndex];
        if (current == NODE_START) {
            if (arrayIndex == 0) {
                BaseNode baseNode = new TreeNode();
                baseNode.setParent(baseNode);
                mRootNode = mParentNode = baseNode;

            } else {
                BaseNode baseNode = new TreeNode();
                baseNode.setParent(mParentNode);
                linkNode(mParentNode, key, baseNode);
                mParentNode = baseNode;
            }
            resetArray();
            arrayIndex++;
        } else if (current == DOUBLE_Q) {
            arrayIndex++;
            String str = readString();
            if(isArray){
                mParentNode.add("",str);
            }else {
                if (isKey) {
                    key = str;
                } else {
                    value = str;
                    linkNode(mParentNode, key, value);
                }
            }
        } else if (charIsNumber(current)) {
            Number number = readNumber();
            linkNode(mParentNode, key, number);
        } else if (current == DIV) {
            arrayIndex++;
            setValue();
        } else if (current == COMMA) {
            arrayIndex++;
            resetKey();
        } else if(current == ARRAY_START){
            arrayIndex++;
            setArray();
            BaseNode arrayNode = new TreeArrayNode();
            arrayNode.setParent(mParentNode);
            linkNode(mParentNode,key,arrayNode);
            resetKey();
            mParentNode = arrayNode;
        }else if(current == NODE_END){
            //if(mParentNode.getParent() != null) {
                mParentNode = mParentNode.getParent();
            //}
            arrayIndex++;
        }else if(current == ARRAY_END){
            arrayIndex++;
            resetArray();
            mParentNode = mParentNode.getParent();
        }else if(!isKey){
            if(current == 't' || current == 'T' || current == 'f' || current == 'F'){
                Boolean o = readBoolean();
                linkNode(mParentNode,key,o);
                resetKey();
            }
            arrayIndex++;
        }else{
            arrayIndex++;
        }
    }

    private boolean charIsNumber(char v) {
        if(v > 47 && v < 58){
            return true;
        }else if(v == '-'){
            return true;
        }else {
            return false;
        }
    }


    private Boolean readBoolean(){
        int count = 4;
        char c = jsonArray[arrayIndex];
        if(c == 'F' || c == 'f'){
            count = 5;
        }
        String value = String.valueOf(jsonArray,arrayIndex,count);
        arrayIndex +=count;
        return value.toLowerCase().equals("true");
    }

    private Number readNumber() {
        //int index = 0;
        int type = TYPE_INT;
        int begin = arrayIndex;
        while (arrayIndex < size) {
            char v = jsonArray[arrayIndex];
            if (v == COMMA || v == ARRAY_END || v == NODE_END) {
                String value = String.valueOf(jsonArray, begin, arrayIndex - begin);
                if (type == TYPE_INT) {
                    if (arrayIndex - begin > 10) {
                        return Long.valueOf(value);
                    } else {
                        return Integer.valueOf(value);
                    }
                } else {
                    return Double.valueOf(value);
                }
            } else if (v == '.') {
                type = TYPE_DOUBLE;
                arrayIndex++;
            } {
                arrayIndex++;
            }
        }

        return Integer.valueOf("0");
    }


    private char[] riseArray(char[] oldArray){
        return Arrays.copyOf(oldArray,oldArray.length + 1024 * 2);
    }
    
    private String readString() {
        int index = 0;
        char[] target = charString;
        while (arrayIndex < size) {
            char v = jsonArray[arrayIndex];
            //扩容
            if(index > target.length){
                target = riseArray(target);
            }
            //解决字符串中存在逗号会中断
            if (v == DOUBLE_Q ){//|| v == COMMA) {
                String value = String.valueOf(target, 0, index);
                arrayIndex++;
                return value;
            } else if(v == STOP){//反斜杠过滤
                //去除“的反斜杠
                arrayIndex++;
                if(jsonArray[arrayIndex] == DOUBLE_Q){
                    arrayIndex++;
                    target[index++] = DOUBLE_Q;
                }else{

                }

            } else{
                target[index++] = v;
                arrayIndex++;
            }

        }

        return String.valueOf(jsonArray, index, arrayIndex - index - 1);
    }


    private void setValue() {
        isKey = false;
    }

    private void resetKey() {
        isKey = true;
        key = "";
        value = "";
    }

    private void setArray(){
        isArray = true;
    }

    private void resetArray(){
        isArray = false;
    }


    private void linkNode(BaseNode baseNode,
                          String key,
                          Object object) {
        baseNode.add(key, object);
        resetKey();
    }


    public static <T> T node2Bean(BaseNode parent,Class<T> tClass) throws IllegalAccessException,InstantiationException {
        T o = generatorBean(tClass);
        //得到所有属性
        Field[] fields = tClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {//遍历

            //得到属性
            Field field = fields[i];
            //打开私有访问
            field.setAccessible(true);
            //获取属性
            String name = field.getName();

            Class<?> c = field.getType();

            if ((field.getModifiers() & Modifier.TRANSIENT) == 0) {
                String type = c.getSimpleName();
                if (BaseTypeUtil.isStringBaseType(type)) {
                    Object value = parent.getChildList().get(name);
                    //找不到key，就从注解中找适配key
                    if (value == null) {
                        Adapter adapter = field.getAnnotation(Adapter.class);
                        if(adapter != null) {
                            String[] adNames = adapter.value();
                            for (String secName : adNames) {
                                if (!TextUtils.isEmpty(secName)) {
                                    value = parent.getChildList().get(secName);
                                    if (value != null) {
                                        field.set(o, value);
                                        break;
                                    }
                                }
                            }
                        }
                    }else{
                        field.set(o, value);
                    }
                }else {
                    Object t = parent.getChildList().get(name);
                    if(t instanceof TreeNode) {
                        BaseNode baseNode = (BaseNode)t;
                        Object value = node2Bean(baseNode, c);
                        field.set(o, value);
                    }else if(t instanceof TreeArrayNode){
                        TreeArrayNode node = (TreeArrayNode) t;
                        List list = node.getList();
                        field.set(o,list);
                    }
                }


            }

        }

        return o;
    }


    private static <T> T generatorBean(Class<T> tClass){
        T o = null;
        try {
            Constructor[] declaredContructors = tClass.getDeclaredConstructors();
            Constructor declaredContructor= declaredContructors[0];
            Class[] parameterTypes = declaredContructor.getParameterTypes();
            int count = parameterTypes.length;
            Object[] paramList = new Object[count];
            for(int i = 0; i < count;i++){
                Class cType = parameterTypes[i];
                String type = cType.getSimpleName();
                if(BaseTypeUtil.isStringBaseType(type)){
                    Object def = BaseTypeUtil.getDefaultValue(type);
                    paramList[i] = def;
                }else{
                    paramList[i] = null;
                }
            }
            o = (T) declaredContructor.newInstance(paramList);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }finally {
            return o;
        }
    }
}
