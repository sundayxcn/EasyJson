package sunday.easy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import easy.EasyJson;
import easy.node.NodeBuild;
import sunday.easy.bean.Company;
import sunday.easy.bean.Friend;
import sunday.easy.bean.Lover;
import sunday.easy.bean.Product;
import sunday.java.Easy.R;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "sunday";
    List<Lover> pro = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //final String ssws = Data.data6;

        pro.add(new Lover());
        pro.add(new Lover());
        pro.add(new Lover());




        Button button = findViewById(R.id.build);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long start = System.currentTimeMillis();
                //parse();
                //paseFastJson();
                //int i = Integer.valueOf("1234567890");
                build1();
                //buildFastJson();
                long useTime = System.currentTimeMillis() - start;
                Log.i(TAG,"json1--useTime = "+ useTime);
            }
        });

    }


    public void paseFastJson(){
        try {
            for(int i =  0; i< 1000 ; i++){
                //EasyJson easyJson = new EasyJson(Data.data2);
                //JSONObject jsonObject = (JSONObject) JSON.parse(Data.data2);
                //String json = easyJson.build();
                //Log.i("sunday","json = " + json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parse(){

        try {
            for(int i =  0; i< 1 ; i++){
                final String json = "{\"friend\":[\"haizeng\",\"娜美\"],\"name\":\"part\"}";
                EasyJson easyJson = new EasyJson(json);
                String name = easyJson.getString("name");
                int age = easyJson.getInt("age");
                List<String> strings = easyJson.getList("friend",String.class);

                Company company = easyJson.toBean(Company.class);
                Friend friend = easyJson.toBean("friend", Friend.class);
                //String json = easyJson.build();
                Log.i("sunday","json = " + json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void buildFastJson(){
        String json1 = null;
//        NetworkOption networkOption = new NetworkOption();
//        networkOption.setFriend(new Friend());

//        Friend friend = new Friend();
//        Lover lover = new Lover();
        for(int i = 0; i< 1;i++) {
             //json1 = JSON.toJSON(networkOption).toString();
//              json1 = JSON.toJSONString(pro);
            //easyTreeJson.put("network", new NetworkOption());
//            Object object = JSON.toJSON(new NetworkOption());
            //json1 = easyTreeJson.build();
        }

        Log.i("sunday","json1 = " + json1);
    }

    final String URL = "http://ms.hualao3.credoo.org/microService/exec/5d37cc4128359a690815e95e/5d37cc7428359a690815e95f/ItemsList";
    final int time = 1;//10 * 1000;
    public void build1(){


        //Product product2 = NodeBuild.generatorBean(Product.class);



        float[] v = {0.89f, 0.34f};
        String[] names = new String[]{"name1","name2"};
//        easyTreeJson.put("float",v);
//        easyTreeJson.put("name",names);
//        easyTreeJson.put("ceshi","cresio");

//        JSONObject jsonObject = new JSONObject();
//        EasyJson easyJson = new EasyJson(jsonObject);
//        easyJson.buildJsonObject();


//        List<String> vv = new ArrayList<>();
//        vv.add("111");
//        vv.add("222");

//        String json = "{\"url\":\"http://ms.hualao3.credoo.org/microService/exec/5d37cc4128359a690815e95e/5d37cc7428359a690815e95f/allApplyItems\",\"name\":\"进件\",\"isMine\":false,\"type\":1}";
//        Gson gson = new Gson();
//        Product product2 = gson.fromJson(json,Product.class);
//        EasyJson easyJson = new EasyJson(json);
        //easyJson.put("data.image",names);
//        List<Product> productList = new ArrayList<>();
////        productList.add(new Product());
////        productList.add(new Product());
////        EasyJson easyJson = new EasyJson();
////        easyJson.put("data",productList);
////        String[] vv = new String[]{"111","222"};
////        easyJson.put("data.pop",vv);
//        List<Integer> typeList = new ArrayList<>();
//        for(int i = 0 ; i< 20;i++) {
//            typeList.add(5);
//        }
        //int[] typeList = {9,9,9,9,9,9,9,9,9,9,9,9};

        final String jj = "{\"data\":[{\"url\":\"http://ms.hualao3.credoo.org\/microService\/exec\/5d37cc4128359a690815e95e/5d37cc7428359a690815e95f/allApplyItems\",\"name\":\"进件\",\"isMine\":true,\"type\":1},{\"url\":\"http://ms.hualao3.credoo.org/microService/exec/5d37cc4128359a690815e95e/5d37cc7428359a690815e95f/allSignItems\",\"name\":\"签章\",\"isMine\":true,\"type\":2},{\"url\":\"http://ms.hualao3.credoo.org/microService/exec/5d37cc4128359a690815e95e/5d37cc7428359a690815e95f/allFaceItems\",\"name\":\"人脸识别\",\"isMine\":true,\"type\":3},{\"url\":\"http://ms.hualao3.credoo.org/microService/exec/5d37cc4128359a690815e95e/5d37cc7428359a690815e95f/allSignRecordItems\",\"name\":\"双录\",\"isMine\":true,\"type\":4},{\"url\":\"http://ms.hualao3.credoo.org/microService/exec/5d37cc4128359a690815e95e/5d37cc7428359a690815e95f/allOCRItems\",\"name\":\"OCR\",\"isMine\":false,\"type\":5},{\"name\":\"个人中心\",\"isMine\":true,\"type\":6},{\"name\":\"设备信息\",\"isMine\":false,\"type\":7},{\"name\":\"AR\",\"isMine\":false,\"type\":8}]}";
        final String jo = "{\"url\":\"http://ms.hualao3.credoo.org/microService/exec/5d37cc4128359a690815e95e/5d37cc7428359a690815e95f/allApplyItems\",\"name\":\"进件\",\"isMine\":true,\"type\":1}";
        EasyJson easyJson = new EasyJson(jo);
        //easyJson.put("data.image",typeList);
        Product product = easyJson.toBean(Product.class);
        String json = easyJson.build();
        // String[] pro = {"贷款","放贷","面审"};

//        EasyJson easyJson1 = new EasyJson()
//        Friend friend = new Friend();
//        Lover lover = new Lover();
//
        String json1 = null;
        //Integer
        //for(int i = 0; i< time;i++) {
        String[] friend = new String[]{"fanbingbing","angelbaby","gaoyuanyuan"};
        EasyJson easyTreeJson = new EasyJson();
        easyTreeJson.put("name", "sunday");
        easyTreeJson.put("age", 28);
        easyTreeJson.put("company", new Company());
        easyTreeJson.put("company.childCompany.address", "shanghai");
        easyTreeJson.put("friend",friend);


        int age = easyTreeJson.getInt("age");
        String name = easyTreeJson.getString("name");

//        String json = easyTreeJson.build();

 //       json = EasyJson.from(new Company()).build();

        //EasyJson easyJson = EasyJson.from(new Lover());
        //json = easyJson.build();
        //easyJson.remove("first");
        //json = easyJson.build();
//            easyTreeJson.put("pro", pro);
//            easyTreeJson.put("project", "贷款", "AI", "面审");
//            easyTreeJson.put("friend", friend);
//            easyTreeJson.put("friend.man", "普通");
//            easyTreeJson.put("friend.ss.lover", lover);
            //easyTreeJson.put("network", new NetworkOption());
//            Object object = JSON.toJSON(new NetworkOption());
//            json1 = easyTreeJson.build();


        Log.i("sunday","json1 = " + json);



    }



}
