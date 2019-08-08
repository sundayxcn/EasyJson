package easy;


import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import easy.json.JsonBuild;
import easy.node.BaseNode;
import easy.node.NodeBuild;
import easy.node.TreeArrayNode;
import easy.node.TreeNode;
import easy.utils.BaseTypeUtil;


/**
 * @auth sunday
 * @Data 2019-07-04
 * @description stringbuffer 本身会将基本类型装箱，所以对于任何类型都先转换成Object来处理
 */
public class EasyJson {
    public static final String NULL_STRING = "";
    private BaseNode mRootNode;
    private JsonBuild mJsonBuild;
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

            } else {
                easyJson.putAllKeyValue(o);
            }
        }
        return easyJson;
    }

    private void init(String json) {
        mNodeBuild = new NodeBuild();

        if (TextUtils.isEmpty(json)) {
            mRootNode = new TreeNode();
        } else {
            mRootNode = mNodeBuild.parse(json);
        }

        mJsonBuild = new JsonBuild(mRootNode);
    }

    public void put(String key, Object value) {
        BaseNode parentNode = getTargetNode(mRootNode, key);
        if (value instanceof List) {
            List<Object> list = (List<Object>) value;
            putList(parentNode, key, list);
        } else {
            put(parentNode, key, value);
        }

    }

    public void put(String key, String... value) {

        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(mRootNode, key);
            List<Object> list = new ArrayList<>();
            for (Object v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }
    }

    public void put(String key, int... value) {
        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(mRootNode, key);
            List<Object> list = new ArrayList<>();
            for (Object v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }
    }

    public void put(String key, byte... value) {
        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(mRootNode, key);
            List<Object> list = new ArrayList<>();
            for (Object v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }
    }

    public void put(String key, long... value) {
        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(mRootNode, key);
            List<Object> list = new ArrayList<>();
            for (Object v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }
    }

    public void put(String key, double... value) {
        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(mRootNode, key);
            List<Object> list = new ArrayList<>();
            for (Object v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }
    }

    public void put(String key, float... value) {
        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(mRootNode, key);
            List<Object> list = new ArrayList<>();
            for (Object v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }
    }

    public void put(String key, char... value) {
        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(mRootNode, key);
            List<Object> list = new ArrayList<>();
            for (Object v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }
    }

    public void put(String key, short... value) {
        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(mRootNode, key);
            List<Object> list = new ArrayList<>();
            for (int v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }
    }

    public void put(String key, boolean... value) {
        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(mRootNode, key);
            List<Object> list = new ArrayList<>();
            for (Object v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }
    }

    private void put(BaseNode parentNode, String key, Object value) {
        key = getFixKey(key);
        if (BaseTypeUtil.isBaseType(value)) {
            //BaseNode treeNode = generatorNode(key, value);
            parentNode.add(key, value);
        } else {
            BaseNode treeNode = generatorNullNode(key);
            parentNode.add(key, treeNode);
            putAllValue(treeNode, value);
        }

    }

    private void putList(BaseNode parentNode, String key, List<Object> value) {
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
    }

    public String build() {
        return mJsonBuild.build();
    }

    public JSONObject buildJsonObject() {
        try {
            return new JSONObject(build());
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    private String getFixKey(String key) {
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
    private BaseNode getTargetNode(BaseNode treeNode, String key) {
        if (TextUtils.isEmpty(key)) {
            return mRootNode;
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
    public void putAllValue(BaseNode parentNode, Object object) {
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
                if ((field.getModifiers() & Modifier.TRANSIENT) == 0) {
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



    public String getString(String key,String defaultValue){
        String value = getString(key);
        if(value.length() == 0){
            return defaultValue;
        }else{
            return value;
        }
    }


    public String getString(String key) {
        Object object = getObject(key);
        if (object == null) {
            return NULL_STRING;
        } else {
            return object.toString();
        }
    }



    public int getInt(String key,int defaultValue){
        Object object = getObject(key);
        if(object == null){
            return defaultValue;
        }else{
            return (int) object;
        }
    }


    public int getInt(String key) {
        Object object = getObject(key);
        if (object == null) {
            return 0;
        } else {
            return (int) object;
        }
    }


    public float getFloat(String key) {
        Object object = getObject(key);
        if (object == null) {
            return 0.0f;
        } else {
            return (float) object;
        }
    }

    public boolean getBoolean(String key,boolean defaultValue) {
        Object object = getObject(key);
        if (object == null) {
            return defaultValue;
        } else {
            return (boolean) object;
        }
    }

    public boolean getBoolean(String key) {
        Object object = getObject(key);
        if (object == null) {
            return false;
        } else {
            return (boolean) object;
        }
    }

    public double getDouble(String key) {
        Object object = getObject(key);
        if (object == null) {
            return 0.0;
        } else {
            return (double) object;
        }
    }

    private Object getObject(String key) {
        BaseNode parentNode = getTargetNode(mRootNode, key);
        key = getFixKey(key);
        if (parentNode != null && parentNode.getChildList().size() > 0) {
            Object object = parentNode.getChildList().get(key);
            return object;
        } else {
            return null;
        }
    }

    public <T> List<T> getList(String key, Class<T> tClass) {
        TreeArrayNode treeArrayNode = (TreeArrayNode) getObject(key);

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
                    T o = mNodeBuild.node2Bean(a, tClass);
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
            return mNodeBuild.node2Bean(mRootNode, tClass);
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


}
