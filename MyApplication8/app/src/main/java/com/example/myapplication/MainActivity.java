package com.example.myapplication;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pieChart = findViewById(R.id.pieChart);
        int data_l = 0,data_nl=0;
        make_chart(pieChart,data_l,data_nl);
        work();

    }

    private void work() {

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                // 生成一个随机数
                int randomNumber1 = (int) (Math.random() * 100);
                int randomNumber2 = (int) (Math.random() * 100);

                chart_update(pieChart,3,randomNumber1,randomNumber2);
                // 打印随机数
                System.out.println("随机数: " + randomNumber1);
            }
        };

        // 每隔 3 秒执行一次任务
        timer.schedule(task, 0, 2000);
    }

    public void make_chart(PieChart chart,int listen, int n_listen){

        List<PieEntry> entries = new ArrayList<PieEntry>();
        entries.add(new PieEntry(listen, "上课"));
        entries.add(new PieEntry(n_listen, "不在上课"));
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(Color.GREEN, Color.RED);
        dataSet.setSliceSpace(3f);

        PieData data = new PieData(dataSet);
        data.setValueTextSize(14f);
        data.setValueTextColor(Color.BLACK);

        chart.setDrawEntryLabels(false);
        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.TRANSPARENT);
        chart.setTransparentCircleRadius(58f);
        chart.setCenterText("上课情况");
        chart.setCenterTextSize(18f);
        chart.setDrawCenterText(true);
        chart.getDescription().setEnabled(false);
        chart.setData(data);
        chart.invalidate();
    }

    public void chart_update(PieChart chart, int order, int data_l_input, int data_nl_input){
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(data_l_input, "上课"));
        entries.add(new PieEntry(data_nl_input, "不在上课"));
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(Color.GREEN, Color.RED);
        dataSet.setSliceSpace(3f);
        PieData data = new PieData(dataSet);
        System.out.println(data_l_input);
        if(order >= 1){
            data.setValueTextSize(14f);
            data.setValueTextColor(Color.BLACK);
            chart.setDrawEntryLabels(false);
            chart.setDrawHoleEnabled(true);
            chart.setHoleColor(Color.TRANSPARENT);
            chart.setTransparentCircleRadius(58f);
            chart.setCenterText("上课情况");
            chart.setCenterTextSize(18f);
            chart.setDrawCenterText(true);
            chart.getDescription().setEnabled(false);
            chart.setData(data);
            chart.setData(data);
            chart.notifyDataSetChanged();
            chart.invalidate();
        }
    }


}