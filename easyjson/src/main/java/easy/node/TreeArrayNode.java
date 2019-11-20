package easy.node;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import easy.json.JsonBuild;

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

    @Override
    public String build() {
        JsonBuild jsonBuild = new JsonBuild(this);
        return jsonBuild.build();
    }

    @Override
    public void put(String key, Object object) {

    }

    @Override
    public void put(String key, String... value) {

    }

    @Override
    public void put(String key, int... value) {

    }

    @Override
    public void put(String key, byte... value) {

    }

    @Override
    public void put(String key, long... value) {

    }

    @Override
    public void put(String key, double... value) {

    }

    @Override
    public void put(String key, float... value) {

    }

    @Override
    public void put(String key, char... value) {

    }

    @Override
    public void put(String key, short... value) {

    }

    @Override
    public void put(String key, boolean... value) {

    }

    @Override
    public String getString(String key, String defaultValue) {
        return null;
    }

    @Override
    public String getString(String key) {
        return null;
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return 0;
    }

    @Override
    public int getInt(String key) {
        return 0;
    }

    @Override
    public byte getByte(String key) {
        return 0;
    }

    @Override
    public byte getByte(String key, byte defaultValue) {
        return 0;
    }

    @Override
    public long getLong(String key) {
        return 0;
    }

    @Override
    public long getLong(String key, long defaultValue) {
        return 0;
    }

    @Override
    public double getDouble(String key) {
        return 0;
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        return 0;
    }

    @Override
    public float getFloat(String key) {
        return 0;
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        return 0;
    }

    @Override
    public char getChar(String key) {
        return 0;
    }

    @Override
    public char getChar(String key, char defaultValue) {
        return 0;
    }

    @Override
    public short getShort(String key) {
        return 0;
    }

    @Override
    public short getShort(String key, short defaultValue) {
        return 0;
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return false;
    }

    @Override
    public boolean getBoolean(String key) {
        return false;
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



