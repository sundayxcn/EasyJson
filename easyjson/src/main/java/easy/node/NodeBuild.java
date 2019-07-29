package easy.node;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

import easy.utils.BaseTypeUtil;

import static easy.json.JsonBuild.ARRAY_END;
import static easy.json.JsonBuild.ARRAY_START;
import static easy.json.JsonBuild.COMMA;
import static easy.json.JsonBuild.DIV;
import static easy.json.JsonBuild.DOUBLE_Q;
import static easy.json.JsonBuild.NODE_END;
import static easy.json.JsonBuild.NODE_START;

/**
 * @auth sunday
 * @Data 2019-07-08
 * @description
 */
public class NodeBuild {
    int TYPE_INT = 0;
    int TYPE_LONG = 1;
    int TYPE_DOUBLE = 2;
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

    private Number readNumber() {
        int index = 0;
        int type = TYPE_INT;
        while (arrayIndex < size) {
            char v = jsonArray[arrayIndex];
            if (v == COMMA || v == ARRAY_END || v == NODE_END) {
                String value = String.valueOf(charString, 0, index);
                if (type == TYPE_INT) {
                    if (index >= 8) {
                        return Long.valueOf(value);
                    } else {
                        return Integer.valueOf(value);
                    }
                } else {
                    return Double.valueOf(value);
                }
            } else if (v == '.') {
                charString[index] = v;
                type = TYPE_DOUBLE;
                arrayIndex++;
            } else {
                charString[index] = v;
                arrayIndex++;
            }
            index++;
        }

        return Integer.valueOf("0");
    }

    private String readString() {
        int index = 0;
        while (arrayIndex < size) {
            char v = jsonArray[arrayIndex];
            arrayIndex++;
            if (v == DOUBLE_Q || v == COMMA) {
                String value = String.valueOf(charString, 0, index);
                return value;
            } else{
                charString[index] = v;
            }
            index++;
        }

        return String.valueOf(charString, 0, index);
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


    public <T> T node2Bean(BaseNode parent,Class<T> tClass) throws IllegalAccessException,InstantiationException {
        T o = tClass.newInstance();
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
                    field.set(o, value);
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
}
