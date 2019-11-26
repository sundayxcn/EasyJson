package easy;


import android.text.TextUtils;
import android.util.Pair;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import easy.json.JsonBuild;
import easy.node.BaseNode;
import easy.node.NodeBuild;
import easy.node.OperatorNode;
import easy.node.TreeArrayNode;
import easy.node.TreeNode;
import easy.utils.BaseTypeUtil;


/**
 * @auth sunday
 * @Data 2019-07-04
 * @description stringbuffer 本身会将基本类型装箱，所以对于任何类型都先转换成Object来处理
 */
public class EasyJson extends OperatorNode {
    public static final String NULL_STRING = "";
    private BaseNode mRootNode;
    private NodeBuild mNodeBuild;

    public EasyJson() {
        init(null);
    }

    public EasyJson(String json) {
        init(json);
    }

    public EasyJson(JSONObject jsonObject) {
        this(jsonObject.toString());
    }

    private static BaseNode generatorNode(String key, Object value) {
        String[] realKey = key.split("\\.");
        int size = realKey.length;
        TreeNode treeNode = new TreeNode(realKey[size - 1], value);
        return treeNode;
    }

    public static BaseNode generatorNullNode(String key) {
        return generatorNode(key, "");
    }

    public static BaseNode generatorNullArrayNode(String key) {
        TreeArrayNode treeArrayNode = new TreeArrayNode();
        treeArrayNode.setKey(key);
        return treeArrayNode;
    }

    public static EasyJson from(Object... object) {
        EasyJson easyJson = new EasyJson();
        for (Object o : object) {
            if (BaseTypeUtil.isBaseType(o)) {

            } else if( o instanceof List) {
                TreeArrayNode treeArrayNode = new TreeArrayNode();
                Object value = ((List) o).get(0);
                if (BaseTypeUtil.isBaseType(value)) {
                    treeArrayNode.setValue(((List) o));
                } else {
                    for (Object child : ((List) o)) {
                        TreeNode treeNode = new TreeNode();
                        putAllValue(treeNode, child);
                        treeArrayNode.add("", treeNode);
                    }
                }
                easyJson.mRootNode = treeArrayNode;
            }else{
                easyJson.putAllKeyValue(o);
            }
        }
        return easyJson;
    }

    public static BaseNode put(BaseNode parentNode, String key, Object value) {
        key = getFixKey(key);
        if (BaseTypeUtil.isBaseType(value)) {
            //BaseNode treeNode = generatorNode(key, value);
            parentNode.add(key, value);
            return parentNode;
        } else {
            BaseNode treeNode = generatorNullNode(key);
            parentNode.add(key, treeNode);
            putAllValue(treeNode, value);
            return treeNode;
        }

    }

    public static BaseNode putList(BaseNode parentNode, String key, List<Object> value) {
        //给对象数组中的每一个对象增加key-value
        key = getFixKey(key);
        if (parentNode instanceof TreeArrayNode) {
            List list = ((TreeArrayNode) parentNode).getList();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = (TreeNode) list.get(i);
                put(treeNode, key, value.get(i));
            }
        } else {
            TreeArrayNode treeArrayNode = new TreeArrayNode();
            treeArrayNode.setKey(key);
            Object o = value.get(0);
            if (BaseTypeUtil.isBaseType(o)) {
                treeArrayNode.setValue(value);
            } else {
                for (Object object : value) {
                    TreeNode treeNode = new TreeNode();
                    putAllValue(treeNode, object);
                    treeArrayNode.add("", treeNode);
                }
            }
            parentNode.add(key, treeArrayNode);
        }
        return parentNode;
    }

    public static String getFixKey(String key) {
        String[] group = key.split("\\.");
        if (group.length == 1) {
            return key;
        } else {
            return group[group.length - 1];
        }
    }

    /**
     * 查找当前节点应该插入到哪个节点之下，也就是找father
     **/
    public static BaseNode getTargetNode(BaseNode treeNode, String key) {
        if (TextUtils.isEmpty(key)) {
            return treeNode;
        }


        String[] group = key.split("\\.");
        if (group.length == 1) {
            return treeNode;
        } else {
            int index = key.indexOf(".");
            int size = key.length();
            String newKey = group[0];
            Object findNode = treeNode.getChildList().get(newKey);
            if (findNode == null) {
                BaseNode newNode = generatorNullNode(newKey);
                treeNode.add(newNode.getKey(), newNode);
                findNode = newNode;
                String fixKey = key.substring(index + 1, size);
                return getTargetNode((TreeNode) findNode, fixKey);
            } else if (findNode instanceof TreeNode) {
                String fixKey = key.substring(index + 1, size);
                return getTargetNode((TreeNode) findNode, fixKey);
            } else if (findNode instanceof TreeArrayNode) {
                return (TreeArrayNode) findNode;
            } else {
                return null;
            }
        }
    }

    /**
     * 反射对象，构建节点
     */
    public static void putAllValue(BaseNode parentNode, Object object) {
        Class cls = object.getClass();
        //得到所有属性
        Field[] fields = cls.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {//遍历
            try {
                //得到属性
                Field field = fields[i];
                //打开私有访问
                field.setAccessible(true);
                //获取属性
                String name = field.getName();
                //获取属性值
                Object value = field.get(object);

                int modifier = field.getModifiers();
                if(Modifier.isStatic(modifier) || Modifier.isTransient(modifier)){
                    //静态变量和不可持久化变量不生成json
                }else{
                    if (value == null) {
                        put(parentNode, name, "");
                    } else if (value.getClass().isArray()) {
                        List<Object> list = BaseTypeUtil.object2List(value);
                        putList(parentNode, name, list);
                    } else if (value instanceof List) {
                        List list = (List) value;
                        putList(parentNode, name, list);
                    } else {
                        put(parentNode, name, value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }

    public static Object getObject(BaseNode baseNode, String key) {
        BaseNode parentNode = getTargetNode(baseNode, key);
        key = getFixKey(key);
        if (parentNode != null && parentNode.getChildList().size() > 0) {
            Object object = parentNode.getChildList().get(key);
            return object;
        } else {
            return null;
        }
    }

    private void init(String json) {
        mNodeBuild = new NodeBuild();
        if (TextUtils.isEmpty(json)) {
            mRootNode = new TreeNode();
        } else {
            try {
                mRootNode = mNodeBuild.parse(json);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        thisNode = mRootNode;

    }

    public String build() {
        JsonBuild jsonBuild = new JsonBuild(mRootNode);
        return jsonBuild.build();
    }

    public JSONObject buildJsonObject() {
        try {
            return new JSONObject(build());
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    private void putAllKeyValue(Object object) {
        putAllValue(mRootNode, object);
    }

    /**
     * 删除节点，如果当前节点下有孩子，则全部会被删除
     */
    private void removeNode(BaseNode parent, String key) {
        String[] group = key.split("\\.");
        if (group.length == 1) {
            parent.removeNode(key);
        } else {
            int index = key.indexOf(".");
            int size = key.length();
            String newKey = group[0];
            Object findNode = parent.getChildList().get(newKey);
            if (findNode instanceof BaseNode) {
                String fixKey = key.substring(index + 1, size);
                removeNode((BaseNode) findNode, fixKey);
            } else {
                parent.removeNode(key);
            }
        }
    }

    public void remove(String key) {
        removeNode(mRootNode, key);
    }


    public List<Pair<String, Object>> getChildKeyAndValues() {
        return mRootNode.getChildKeyAndValues();
    }


    public <T> List<T> getList(String key, Class<T> tClass){
        return getList(mRootNode,key,tClass);
    }

    public static <T> List<T> getList(BaseNode baseNode,String key, Class<T> tClass) {
        TreeArrayNode treeArrayNode = (TreeArrayNode) getObject(baseNode, key);

        if (treeArrayNode == null) {
            return new ArrayList<>();
        }

        List list = treeArrayNode.getList();
        int size = list.size();
        if (size > 0 && list.get(0) instanceof TreeNode) {
            List<TreeNode> parent = list;
            List classlist = new ArrayList();
            try {
                for (TreeNode a : parent) {
                    T o = NodeBuild.node2Bean(a, tClass);
                    classlist.add(o);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } finally {
                return classlist;
            }


        } else {
            return (List<T>) treeArrayNode.getList();
        }
    }

    public <T> T toBean(Class<T> tClass) {
        try {
            return NodeBuild.node2Bean(mRootNode, tClass);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        return null;
    }

    public <T> T toBean(String key, Class<T> tClass) {
        Object o = getTargetNode(mRootNode, key);
        if (o == null) {
            return null;
        } else {
            if (BaseTypeUtil.isBaseType(o)) {
                return (T) o;
            } else {
                try {
                    BaseNode parent = (BaseNode) o;
                    return mNodeBuild.node2Bean(parent, tClass);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }

                return null;
            }
        }
    }

    public List<String> getKeys() {
        return getNodeKeys(mRootNode);
    }

    private List<String> getNodeKeys(BaseNode baseNode) {
        List<String> keyList = new ArrayList<>();
        Map<String, Object> map = baseNode.getChildList();
        Set<Map.Entry<String, Object>> set = map.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            keyList.add(entry.getKey());
        }
        return keyList;
    }

    public List<String> getKeys(String nodeKey) {
        BaseNode baseNode = getTargetNode(mRootNode, nodeKey);
        String key = getFixKey(nodeKey);
        Map<String, Object> map = baseNode.getChildList();
        Object object = map.get(key);
        if (object instanceof BaseNode) {
            return getNodeKeys((BaseNode) object);
        } else {
            return new ArrayList<>();
        }
    }

    private BaseNode getRootNode() {
        return mRootNode;
    }


    /**
     * 连接Json串，
     *
     * @param json 将json串插入到同一个层次的json串中
     */
    public EasyJson join(String json) {
        EasyJson easyJson = new EasyJson(json);

        Map<String, Object> map = easyJson.getRootNode().getChildList();
        Set<Map.Entry<String, Object>> set = map.entrySet();
        Iterator<Map.Entry<String, Object>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            mRootNode.add(entry.getKey(), entry.getValue());
        }

        return this;
    }


    /**
     * 连接Json串，将json插入到一个新key
     *
     * @param key  插入的key
     * @param json 插入的json串
     */
    public EasyJson join(String key, String json) {
        EasyJson easyJson = new EasyJson(json);
        String relKey = getFixKey(key);

        BaseNode parent = getTargetNode(mRootNode, key);
        parent.add(relKey, easyJson.getRootNode());

        return this;
    }


    public String buildKey(String key) {
        BaseNode node = getNode(key);
        if (node == null) {
            return null;
        } else {
            return node.build();
        }
    }

    public BaseNode getNode(String key) {
        BaseNode baseNode = getTargetNode(mRootNode, key);
        String realKey = getFixKey(key);
        Map<String, Object> map = baseNode.getChildList();
        Object object = map.get(realKey);
        if (object instanceof BaseNode) {
            return ((BaseNode) object);
        } else {
            return null;
        }
    }


    public BaseNode createNode(String key){
        return mRootNode.createNode(key);
    }


}
