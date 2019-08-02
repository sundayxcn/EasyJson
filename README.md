## 使用方法
1. 在根目录build.grade中增加
`maven { url 'https://www.jitpack.io' }`
```xml
allprojects {
    repositories {
        google()
        jcenter()
        maven { url 'https://www.jitpack.io' }
    }
}
```
2. 添加引用
目前还不稳定，所以需要引用SNAPSHOT
```xml
implementation 'com.github.sundayxcn:EasyJson:-SNAPSHOT'
```



### 组装Json

#### 各种类型

包括 基本类型，基本类型包装对象，对象，数组，List。
举例如下：
一个个人信息的json串包含了名字，年龄，公司，朋友等信息。
```java
public class Company {
    String name = "part";
}

public void demo{
    String[] friend = new String[]{"fanbingbing","angelbaby","gaoyuanyuan"};
    EasyJson easyTreeJson = new EasyJson();
    easyTreeJson.put("name", "sunday");
    easyTreeJson.put("age", 28);
    easyTreeJson.put("company", new Company());
    easyTreeJson.put("friend",friend);
    String json = easyTreeJson.build();
}


         
```
这样的代码生成的json串如下：

```xml
{
    "name":"sunday",
    "age":28,
    "company":{
        "name":"part"
    },
    "friend":[
        "fanbingbing",
        "angelbaby",
        "gaoyuanyuan"
    ]
}

```
#### 层级添加
对于如上信息，可能不满足我们上传到后台的json格式。比如上面的Company 是别人的类，我们不能在Company里面添加属性，但是需求要求我们在company里面有地址信息。那么可以这么做：
```java
easyTreeJson.put("company.address", "shanghai");
```
重新build之后，json串如下：
```xml
{
    "name":"sunday",
    "age":28,
    "company":{
        "name":"part",
        "address":"shanghai"
    },
    "friend":[
        "fanbingbing",
        "angelbaby",
        "gaoyuanyuan"
    ]
}
```
支持自动生成子层级，比如希望将`address`添加到`company`下面的`childCompany`中。
```java
easyTreeJson.put("company.childCompany.address", "shanghai");
```
重新build之后，json串如下：
```xml
{
    "name":"sunday",
    "age":28,
    "company":{
        "name":"part",
        "childCompany":{
            "address":"shanghai"
        }
    },
    "friend":[
        "fanbingbing",
        "angelbaby",
        "gaoyuanyuan"
    ]
}
```
#### 对象组合
如果只有一个对象，可以使用简单的方法如下：
```java
json = EasyJson.from(new Company()).build();
```
生成Json如下：
```xml
{
    "name":"part"
}
```
如果需要将多个对象的属性组合在同一层级，使用方式如下：
```java
public class Type {
    int level = 666;
    int count = 999;
}

json = EasyJson.from(new Company(),new Type()).build();
```
生成Json如下：
```xml
{
    "name":"part",
    "count":999,
    "level":666
}
```
#### 数组添加
如果有一个json数组串，里面类型是对象，但是现在有一个需求是往每一个对象里面插入属性，那么可以这么做
```java
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());
        EasyJson easyJson = new EasyJson();
        easyJson.put("data",productList);
        String json = easyJson.build();
```
生成的Json如下：
```xml
{
    "data":[
        {
            "age":18,
            "name":"sunday",
            "url":"http"
        },
        {
            "age":18,
            "name":"sunday",
            "url":"http"
        }
    ]
}
```
现在需要给每个对象增加pop属性的值，那么这样做：
```java
        String[] vv = new String[]{"111","222"};
        easyJson.put("data.pop",vv);
        String json = easyJson.build();
```
最终json如下：
```java
{
    "data":[
        {
            "age":18,
            "name":"sunday",
            "url":"http",
            "pop":"111"
        },
        {
            "age":18,
            "name":"sunday",
            "url":"http",
            "pop":"222"
        }
    ]
}
```
**Tips：**  后添加的vv数组或者list的长度一定要大于等于原json数组长度，否则数组越界访问，导致json串生成失败。

#### 删除节点
如果不希望把一个对象中某个属性序列化，那么方法有两种
1. 在属性前面设置`transient`关键字，如下代码：
```java
public class Lover {
    public String first = "fanbingbing";
    transient public String second = "baby";
    public String fird = "haha";
}

  EasyJson easyJson = EasyJson.from(new Lover());
  json = easyJson.build();
```
生成的json如下：
```xml
//会忽略掉second属性
{
    "fird":"haha",
    "first":"fanbingbing"
}
```
2. 使用`remove`去除节点，如下代码：
```java
        EasyJson easyJson = EasyJson.from(new Lover());
        easyJson.remove("first");
        json = easyJson.build();
```
生成的json如下：
```xml
{
    "fird":"haha"
}
```

### 解析json

简单的获取value：
```java
final String json = "{\"friend\":[\"haizeng\",\"娜美\"],\"name\":\"part\"}";
EasyJson easyJson = new EasyJson(json);
String name = easyJson.getString("name");
int age = easyJson.getInt("age");
List<String> strings = easyJson.getList("friend",String.class);
```


如果整个json对应的是一个对象，可以使用如下方式:
```java
Company company = easyJson.toBean(Company.class);
```

如果一个json中的一个字段对应一个对象，使用如下方式:

```java
Friend friend = easyJson.toBean("friend", Friend.class);
```
### 兼容
为了减少大家的切换复杂度，支持android 原生的`JSONObject`导入 和导出
```java
JSONObject jsonObject = new JSONObject();
//导入
EasyJson easyJson = new EasyJson(jsonObject);
//导出
easyJson.buildJsonObject();
```



如果觉得好用，请给一个`star`,谢谢
