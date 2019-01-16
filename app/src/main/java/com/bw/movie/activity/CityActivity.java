package com.bw.movie.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.adapter.HaoHanAdapter;
import com.bw.movie.bean.City;
import com.bw.movie.bean.CityBean;
import com.bw.movie.bean.CityEventBusManager;
import com.bw.movie.fragment.CinemaFregment;
import com.bw.movie.utils.ToastUtil;
import com.bw.movie.view.QuickIndexBar;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 侧边索引(快速索引):音乐APP,即时通讯,电商选择城市,短信验证选择城市都有这个类型自定义控件
 * 实现步骤:
 *  1.绘制A-Z的字母列表 (自绘式自定义控件)
 *  2.响应触摸事件
 *  3.提供监听回调
 *  4.获取汉字的拼音,首字母    (pinying4J通过汉字得到他的拼音,只能一个字符一个字符去转换成拼音)
 *  作业:提高自学能力,要求使用TingPinying:http://promeg.io/2017/03/18/tinypinyin-part-1/
 *  5.根据拼音排序
 *  6.根据首字母分组
 *  7.把监听回调和ListVIew结合起来
 *
 *  掌握解决问题的思路:把复杂的东西简单话,把复杂的东西分成尽可能小的模块,把我模块关键点,一步一个脚印的去做,最终就可以实现复杂的效果
 *  */

public class CityActivity extends AppCompatActivity {

    private ListView lv;
    private ArrayList<CityBean> persons;
    private HaoHanAdapter mHaoHanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        QuickIndexBar bar = (QuickIndexBar) findViewById(R.id.bar);



        //C.打印按下的吐司
        bar.setOnLetterUpdateListener(new QuickIndexBar.OnLetterUpdateListener() {
            @Override
            public void onLetterUpdate(String letter) {
                ToastUtil.showToast(CityActivity.this,letter);
            }
        });
        /* //测试有没有获取到拼音
        System.out.println(PinyinUtil.getPinyin("易宸锋"));
        System.out.println(PinyinUtil.getPinyin("易宸锋$%^$^D"));
        System.out.println(PinyinUtil.getPinyin("易    宸  锋  "));*/

        //D.View层
        lv = (ListView) findViewById(R.id.lv);

        //D.model层,创建集合
        persons = new ArrayList<>();
        //D.填充并排列数据
        fillAndSortData(persons);
        //D.Controller层,设置适配器
        mHaoHanAdapter = new HaoHanAdapter(persons,this);
        lv.setAdapter(mHaoHanAdapter);

        //根据用户按住的字符,自动跳到对应的ListView条目上
        bar.setOnLetterUpdateListener(new QuickIndexBar.OnLetterUpdateListener() {
            @Override
            public void onLetterUpdate(String letter) {
                for(int x=0; x<persons.size(); x++){
                    String l = persons.get(x).getPinyin().charAt(0) + "";
                    if (TextUtils.equals(letter,l)){
                        //找到第一个首字母是letter条目
                        lv.setSelection(x);
                        break;
                    }
                }
            }
        });

        //mHaoHanAdapter.getPersons()

        /**
         * 传递地址名称
         */
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CityActivity.this, "position"+mHaoHanAdapter.getItem(position).getName(), Toast.LENGTH_SHORT).show();
                String name = mHaoHanAdapter.getItem(position).getName();
                EventBus.getDefault().postSticky(new CityEventBusManager(name));
                finish();
            }
        });

    }

    /**
     * 填充数据,并进行排序
     * @param persons
     */
    private void fillAndSortData(ArrayList<CityBean> persons) {
        //填充
        for(int x=0; x<Cheeses.NAMES.length; x++){
            String name = Cheeses.NAMES[x];
            persons.add(new CityBean(name));
        }

        //排序
        Collections.sort(persons);
    }


}

