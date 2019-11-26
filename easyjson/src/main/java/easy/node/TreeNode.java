package easy.node;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import easy.EasyJson;
import easy.json.JsonBuild;



/**
 * @auth sunday
 * @Data 2019-07-04
 * @description 节点
 */
public class TreeNode extends OperatorNode implements BaseNode {

    private LinkedHashMap<String, Object> childList = new LinkedHashMap<>();
    private String key;
    private Object value;
    private BaseNode parent;

    public TreeNode() {
        this("","");
    }

    public TreeNode(String key, Object value) {
        this.key = key;
        this.value = value;
        this.thisNode = this;
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
    public void setParent(BaseNode parent) {
        this.parent = parent;
    }

    @Override
    public String build() {
        JsonBuild jsonBuild = new JsonBuild(this);
        return jsonBuild.build();
    }

    @Override
    public BaseNode createNode(String key) {
        BaseNode parentNode = EasyJson.getTargetNode(this,key);
        Object targetNode = parentNode.getChildList().get(key);
        if(targetNode == null || !(targetNode instanceof BaseNode)){
            key = EasyJson.getFixKey(key);
            BaseNode treeNode = EasyJson.generatorNullNode(key);
            parentNode.add(key, treeNode);
            return treeNode;
        }else{
            return (BaseNode) targetNode;
        }
    }

    @Override
    public List<Pair<String, Object>> getChildKeyAndValues() {
        List list = new ArrayList<Pair<String,Object>>();
        Set<Map.Entry<String, Object>> set = childList.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            Pair<String,Object> value = new Pair<>(entry.getKey(),entry.getValue());
            list.add(value);
        }
        return list;
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
