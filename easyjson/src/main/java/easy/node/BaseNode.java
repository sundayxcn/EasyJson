package easy.node;

import java.util.LinkedHashMap;

/**
 * @auth sunday
 * @Data 2019-07-04
 * @description
 */
public interface BaseNode {
    void add(String key,Object object);

    LinkedHashMap<String, Object> getChildList();

    int size();

    Object getValue();

    String getKey();

    void removeNode(String key);

    void setParent(BaseNode parent);

    BaseNode getParent();
}
