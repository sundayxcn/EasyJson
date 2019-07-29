package easy.node;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @auth sunday
 * @Data 2019-07-04
 * @description 数组节点
 */
public class TreeArrayNode implements BaseNode {
    private String key;
    private List<Object> list = new ArrayList<>();
    private BaseNode parent;
    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void removeNode(String key) {

    }

    @Override
    public void setParent(BaseNode parent) {
        this.parent = parent;
    }

    @Override
    public BaseNode getParent() {
        return parent;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return "TreeArrayNode";
    }


    public List<Object> getList(){
        return list;
    }

    public void setValue(List<Object> value) {
        list = value;
    }

    @Override
    public void add(String key,Object treeNode) {
        list.add(treeNode);
    }

    @Override
    public LinkedHashMap<String, Object> getChildList() {
        return null;
    }

    @Override
    public int size() {
        return list.size();
    }

}



