package easy.json;

import android.text.TextUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import easy.node.BaseNode;
import easy.node.TreeArrayNode;
import easy.node.TreeNode;


/**
 * @auth sunday
 * @Data 2019-07-05
 * @description 遍历tree，将所有节点变成字符串
 */
public class JsonBuild {
    public static final char NODE_START = '{';
    public static final char NODE_END = '}';
    public static final char ARRAY_START = '[';
    public static final char ARRAY_END = ']';
    public static final char COMMA = ',';
    public static final char DOUBLE_Q = '\"';
    public static final char DIV = ':';
    public static final char STOP = 92;//反斜杠
    private BaseNode mRootNode;
    private StringBuilder stringBuffer = new StringBuilder();

    public JsonBuild(){

    }

    public JsonBuild(BaseNode rootNode){
        mRootNode = rootNode;
    }

    public void setRootNode(BaseNode rootNode){
        mRootNode = rootNode;
    }



    public void addString(String name,Object value){
        addKey(name);
        stringBuffer.append(DOUBLE_Q);
        stringBuffer.append(value);
        stringBuffer.append(DOUBLE_Q);
        stringBuffer.append(COMMA);
    }

    public void addNumber(String name,Object value){
        addKey(name);
        stringBuffer.append(value);
        stringBuffer.append(COMMA);
    }

    public void addBoolean(String name,Object value){
        addKey(name);
        stringBuffer.append(value);
        stringBuffer.append(COMMA);
    }



    public String build(){
        stringBuffer = new StringBuilder();
        buildChild("",mRootNode);
        int length = stringBuffer.length();
        stringBuffer.deleteCharAt(length-1);
        return stringBuffer.toString();
    }


    private void addKey(String key){
        if(!TextUtils.isEmpty(key)) {
            stringBuffer.append(DOUBLE_Q);
            stringBuffer.append(key);
            stringBuffer.append(DOUBLE_Q);
            stringBuffer.append(DIV);
        }
    }


    private void replaceLastChar(char ch){
        int length = stringBuffer.length();
        stringBuffer.replace(length - 1, length, String.valueOf(ch));
        stringBuffer.append(COMMA);
    }

    private void buildChild(String key, Object object){
        //StringBuilder stringBuffer = new StringBuilder();
        if(object instanceof TreeNode){
            addKey(key);
            stringBuffer.append(NODE_START);
            TreeNode treeNode = (TreeNode) object;
            Set<Map.Entry<String, Object>> set = treeNode.getChildList().entrySet();
            Iterator<Map.Entry<String, Object>> iterator = set.iterator();
            while (iterator.hasNext()){
                Map.Entry<String, Object> entry = iterator.next();
                buildChild(entry.getKey(),entry.getValue());
            }
            replaceLastChar(NODE_END);

        }else if(object instanceof TreeArrayNode){
            List<Object> list = ((TreeArrayNode) object).getList();
            addKey(key);
            stringBuffer.append(ARRAY_START);
            for(Object o : list){
                buildChild(null,o);
            }
            replaceLastChar(ARRAY_END);
        }else if(object instanceof String){
            addString(key, object);
        }else if(object instanceof Number){
            addNumber(key,object);
        }else if(object instanceof Boolean){
            addBoolean(key,object);
        }
    }

}
