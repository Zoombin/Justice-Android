package com.ufo.judicature.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ufo.judicature.Base.BaseActivity;
import com.ufo.judicature.Entity.NewsEntity;
import com.ufo.judicature.Entity.QuestionEntity;
import com.ufo.judicature.Entity.ServiceResult;
import com.ufo.judicature.Net.Api;
import com.ufo.judicature.Net.NetUtils;
import com.ufo.judicature.R;
import com.ufo.judicature.Widget.Toast;
import com.ufo.judicature.Widget.viewpager.RecyclingPagerAdapter;

import java.util.ArrayList;

/**
 * exam page
 *
 */
public class ExamActivity extends BaseActivity {

    private TextView tv_commit;
    private ViewPager pager_questions;
    private QuestionViewPagerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        initView();
        initData();
    }

    private void initView() {
        ((ImageView)findViewById(R.id.image_back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((TextView)findViewById(R.id.tv_commit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        pager_questions = (ViewPager) findViewById(R.id.pager_questions);
        pager_questions.setOffscreenPageLimit(1000);
        adapter = new QuestionViewPagerAdapter(this);
        pager_questions.setAdapter(adapter);
    }

    public class QuestionViewPagerAdapter extends RecyclingPagerAdapter {
        private Context context;
        private ArrayList<QuestionEntity.QuestionInfo> questionInfos = new ArrayList<>();

        public QuestionViewPagerAdapter(Context context) {
            this.context = context;
        }

        public void clearData() {
            questionInfos.clear();
            notifyDataSetChanged();
        }

        public void addQuestionList(ArrayList<QuestionEntity.QuestionInfo> questionInfos) {
            this.questionInfos = questionInfos;
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return questionInfos.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            final QuestionEntity.QuestionInfo questionInfo = questionInfos.get(position);

            ViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(
                        R.layout.question_viewpager_item, container, false);

                holder = new ViewHolder();

                holder.tv_question = (TextView) convertView.findViewById(R.id.tv_question);
                holder.ly_singleselect = (LinearLayout) convertView.findViewById(R.id.ly_singleselect);
                holder.ly_multiselect = (LinearLayout) convertView.findViewById(R.id.ly_multiselect);
                holder.ly_judge = (LinearLayout) convertView.findViewById(R.id.ly_judge);
                holder.rg_singleselect = (RadioGroup) convertView.findViewById(R.id.rg_singleselect);
                holder.rg_judge = (RadioGroup) convertView.findViewById(R.id.rg_judge);
                holder.rb_single_a = (RadioButton) convertView.findViewById(R.id.rb_single_a);
                holder.rb_single_b = (RadioButton) convertView.findViewById(R.id.rb_single_b);
                holder.rb_single_c = (RadioButton) convertView.findViewById(R.id.rb_single_c);
                holder.rb_single_d = (RadioButton) convertView.findViewById(R.id.rb_single_d);
                holder.rb_multi_a = (CheckBox) convertView.findViewById(R.id.rb_multi_a);
                holder.rb_multi_b = (CheckBox) convertView.findViewById(R.id.rb_multi_b);
                holder.rb_multi_c = (CheckBox) convertView.findViewById(R.id.rb_multi_c);
                holder.rb_multi_d = (CheckBox) convertView.findViewById(R.id.rb_multi_d);
                holder.rb_yes = (RadioButton) convertView.findViewById(R.id.rb_yes);
                holder.rb_no = (RadioButton) convertView.findViewById(R.id.rb_no);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tv_question.setText((position + 1) + ". " + questionInfo.getQuestion());
            String type = questionInfo.getType();
            if (type.equals("0")) {
                holder.ly_singleselect.setVisibility(View.VISIBLE);
                holder.ly_multiselect.setVisibility(View.GONE);
                holder.ly_judge.setVisibility(View.GONE);
                holder.rb_single_a.setText(questionInfo.getA());
                holder.rb_single_b.setText(questionInfo.getB());
                holder.rb_single_c.setText(questionInfo.getC());
                holder.rb_single_d.setText(questionInfo.getD());
            } else if (type.equals("1")) {
                holder.ly_singleselect.setVisibility(View.GONE);
                holder.ly_multiselect.setVisibility(View.VISIBLE);
                holder.ly_judge.setVisibility(View.GONE);
                holder.rb_multi_a.setText(questionInfo.getA());
                holder.rb_multi_b.setText(questionInfo.getB());
                holder.rb_multi_c.setText(questionInfo.getC());
                holder.rb_multi_d.setText(questionInfo.getD());
            } else if (type.equals("2")) {
                holder.ly_singleselect.setVisibility(View.GONE);
                holder.ly_multiselect.setVisibility(View.GONE);
                holder.ly_judge.setVisibility(View.VISIBLE);
            }

            return convertView;
        }

        public class ViewHolder {
            TextView tv_question;
            LinearLayout ly_singleselect;
            LinearLayout ly_multiselect;
            LinearLayout ly_judge;
            RadioGroup rg_singleselect;
            RadioButton rb_single_a;
            RadioButton rb_single_b;
            RadioButton rb_single_c;
            RadioButton rb_single_d;
            CheckBox rb_multi_a;
            CheckBox rb_multi_b;
            CheckBox rb_multi_c;
            CheckBox rb_multi_d;
            RadioGroup rg_judge;
            RadioButton rb_yes;
            RadioButton rb_no;
        }
    }

    private void initData() {
        Api.getQuestion(self, new NetUtils.NetCallBack<ServiceResult>() {
            @Override
            public void success(ServiceResult rspData) {
                QuestionEntity questionEntity = (QuestionEntity) rspData;
                ArrayList<QuestionEntity.QuestionInfo> questionInfos = questionEntity.getData();
                if (questionInfos != null) {
                    adapter.addQuestionList(questionInfos);
                } else {
                    Toast.show(self, "无试卷信息！");
                    finish();
                }
            }

            @Override
            public void failed(String msg) {
                Toast.show(self, msg);
            }
        }, QuestionEntity.class);
    }
}