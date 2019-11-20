package easy.node;

import java.util.LinkedHashMap;

import easy.BaseOperator;

/**
 * @auth sunday
 * @Data 2019-07-04
 * @description
 */
public interface BaseNode extends BaseOperator {
    void add(String key,Object object);

    LinkedHashMap<String, Object> getChildList();

    int size();

    Object getValue();

    String getKey();

    void removeNode(String key);

    void setParent(BaseNode parent);

    BaseNode getParent();

    String build();

    BaseNode createNode(String key);
}
