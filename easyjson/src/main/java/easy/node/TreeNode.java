package easy.node;

import java.util.LinkedHashMap;

import easy.json.JsonBuild;


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

}
