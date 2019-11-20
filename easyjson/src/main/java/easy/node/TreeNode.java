package easy.node;

import android.text.TextUtils;

import java.util.LinkedHashMap;

import easy.EasyJson;
import easy.json.JsonBuild;

import static easy.EasyJson.NULL_STRING;
import static easy.EasyJson.getObject;


/**
 * @auth sunday
 * @Data 2019-07-04
 * @description 节点
 */
public class TreeNode implements BaseNode {

    private LinkedHashMap<String, Object> childList = new LinkedHashMap<>();
    private String key;
    private Object value;
    private BaseNode parent;

    public TreeNode() {

    }

    public TreeNode(String key, Object value) {
        this.key = key;
        this.value = value;
    }


    @Override
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public void removeNode(String key) {
        childList.remove(key);
    }

    @Override
    public BaseNode getParent() {
        return parent;
    }

    @Override
    public String build() {
        JsonBuild jsonBuild = new JsonBuild(this);
        return jsonBuild.build();
    }


    @Override
    public void setParent(BaseNode parent) {
        this.parent = parent;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public void add(String key, Object treeNode) {
        childList.put(key, treeNode);
        value = null;
    }

    public Object getChildNode(String key) {
        return childList.get(key);
    }

    public int size() {
        return childList.size();
    }

    public LinkedHashMap<String, Object> getChildList() {
        return childList;
    }


    public String getString(String key,String defaultValue){

        Object object = getObject(this,key);
        if (object == null) {
            return defaultValue;
        } else {
            if(object instanceof BaseNode){
                return ((BaseNode)object).build();
            }else {
                String value = object.toString();
                if(TextUtils.isEmpty(value)){
                    return defaultValue;
                }else {
                    return value;
                }
            }
        }

    }


    public String getString(String key) {
        return getString(key,"");
    }


    public int getInt(String key,int defaultValue){
        Object object = getObject(this,key);
        if(object == null){
            return defaultValue;
        }else{
            return (int) object;
        }
    }


    public int getInt(String key) {
        return getInt(key,0);
    }


    public boolean getBoolean(String key,boolean defaultValue) {
        Object object = getObject(this,key);
        if (object == null) {
            return defaultValue;
        } else {
            return (boolean) object;
        }
    }

    public boolean getBoolean(String key) {
        return getBoolean(key,false);
    }

}
