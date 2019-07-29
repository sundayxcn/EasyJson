package sunday.easy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import easy.EasyJson;
import sunday.easy.bean.Company;
import sunday.easy.bean.Friend;
import sunday.easy.bean.Lover;
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
                parse();
                //paseFastJson();
                //build1();
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


    final int time = 1;//10 * 1000;
    public void build1(){
        float[] v = {0.89f, 0.34f};
        String[] names = new String[]{"name1","name2"};
//        easyTreeJson.put("float",v);
//        easyTreeJson.put("name",names);
//        easyTreeJson.put("ceshi","cresio");




        // String[] pro = {"贷款","放贷","面审"};


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
        String json = easyTreeJson.build();



        json = EasyJson.from(new Company()).build();

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
