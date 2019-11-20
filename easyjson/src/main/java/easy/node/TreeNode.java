package easy.node;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import easy.EasyJson;
import easy.json.JsonBuild;

import static easy.EasyJson.NULL_STRING;
import static easy.EasyJson.getObject;
import static easy.EasyJson.getTargetNode;
import static easy.EasyJson.putList;


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
        key = EasyJson.getFixKey(key);
        BaseNode treeNode = EasyJson.generatorNullNode(key);
        add(key, treeNode);
        return treeNode;
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


    public BaseNode put(String key, Object value) {
        BaseNode parentNode = getTargetNode(this, key);
        if (value instanceof List) {
            List<Object> list = (List<Object>) value;
            putList(parentNode, key, list);
        } else {
            EasyJson.put(parentNode, key, value);
        }
        return this;
    }

    public BaseNode put(String key, String... value) {

        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(this, key);
            List<Object> list = new ArrayList<>();
            for (Object v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }
        return this;
    }

    public BaseNode put(String key, int... value) {
        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(this, key);
            List<Object> list = new ArrayList<>();
            for (Object v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }
        return this;
    }

    public BaseNode put(String key, byte... value) {
        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(this, key);
            List<Object> list = new ArrayList<>();
            for (Object v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }
        return this;
    }

    public BaseNode put(String key, long... value) {
        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(this, key);
            List<Object> list = new ArrayList<>();
            for (Object v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }
        return this;
    }

    public BaseNode put(String key, double... value) {
        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(this, key);
            List<Object> list = new ArrayList<>();
            for (Object v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }
        return this;
    }

    public BaseNode put(String key, float... value) {
        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(this, key);
            List<Object> list = new ArrayList<>();
            for (Object v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }

        return this;
    }

    public BaseNode put(String key, char... value) {
        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(this, key);
            List<Object> list = new ArrayList<>();
            for (Object v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }
        return this;
    }

    public BaseNode put(String key, short... value) {
        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(this, key);
            List<Object> list = new ArrayList<>();
            for (int v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }
        return this;
    }

    public BaseNode put(String key, boolean... value) {
        if (value.length == 1) {
            put(key, value[0]);
        } else {
            BaseNode parentNode = getTargetNode(this, key);
            List<Object> list = new ArrayList<>();
            for (Object v : value) {
                list.add(v);
            }
            putList(parentNode, key, list);
        }
        return this;
    }

    public String getString(String key, String defaultValue) {

        Object object = getObject(this, key);
        if (object == null) {
            return defaultValue;
        } else {
            if (object instanceof BaseNode) {
                return ((BaseNode) object).build();
            } else {
                String value = object.toString();
                if (TextUtils.isEmpty(value)) {
                    return defaultValue;
                } else {
                    return value;
                }
            }
        }

    }

    @Override
    public String getString(String key) {
        return getString(key, "");
    }

    @Override
    public int getInt(String key, int defaultValue) {
        Object object = getObject(this, key);
        if (object == null) {
            return defaultValue;
        } else {
            return (int) object;
        }
    }

    @Override
    public int getInt(String key) {
        return getInt(key, 0);
    }

    @Override
    public byte getByte(String key) {
        byte b = '0';
        return getByte(key, b);
    }

    @Override
    public byte getByte(String key, byte defaultValue) {
        Object object = getObject(this, key);
        if (object == null) {
            return defaultValue;
        } else {
            return (byte) object;
        }
    }

    @Override
    public long getLong(String key) {
        return getLong(key, 0);
    }

    @Override
    public long getLong(String key, long defaultValue) {
        Object object = getObject(this, key);
        if (object == null) {
            return defaultValue;
        } else {
            return (long) object;
        }
    }

    @Override
    public double getDouble(String key) {
        return getDouble(key, 0);
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        Object object = getObject(this, key);
        if (object == null) {
            return defaultValue;
        } else {
            return (double) object;
        }
    }

    @Override
    public float getFloat(String key) {
        return getFloat(key, 0f);
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        Object object = getObject(this, key);
        if (object == null) {
            return defaultValue;
        } else {
            return (float) object;
        }
    }

    @Override
    public char getChar(String key) {
        return getChar(key, '0');
    }

    @Override
    public char getChar(String key, char defaultValue) {
        Object object = getObject(this, key);
        if (object == null) {
            return defaultValue;
        } else {
            return (char) object;
        }
    }

    @Override
    public short getShort(String key) {
        short v = '0';
        return getShort(key, v);
    }

    @Override
    public short getShort(String key, short defaultValue) {
        Object object = getObject(this, key);
        if (object == null) {
            return defaultValue;
        } else {
            return (short) object;
        }
    }


    public boolean getBoolean(String key, boolean defaultValue) {
        Object object = getObject(this, key);
        if (object == null) {
            return defaultValue;
        } else {
            return (boolean) object;
        }
    }

    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

}
