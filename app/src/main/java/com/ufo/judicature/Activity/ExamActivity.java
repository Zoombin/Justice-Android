package com.ufo.judicature.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ufo.judicature.Base.BaseActivity;
import com.ufo.judicature.Entity.ExamEntity;
import com.ufo.judicature.Entity.QuestionEntity;
import com.ufo.judicature.Entity.ServiceResult;
import com.ufo.judicature.Net.Api;
import com.ufo.judicature.Net.NetUtils;
import com.ufo.judicature.R;
import com.ufo.judicature.Utils.Config;
import com.ufo.judicature.Widget.Toast;
import com.ufo.judicature.Widget.viewpager.RecyclingPagerAdapter;

import java.util.ArrayList;

/**
 * exam page
 *
 */
public class ExamActivity extends BaseActivity {

    private TextView tv_title;
    private TextView tv_commit;
    private ViewPager pager_questions;
    private QuestionViewPagerAdapter adapter;
    private String examination_id;

    private Integer[] scores;
    // 单题得分
    private int onesource = 10;

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
                upLoad();
            }
        });
        tv_title = (TextView) findViewById(R.id.tv_title);
        pager_questions = (ViewPager) findViewById(R.id.pager_questions);
        pager_questions.setOffscreenPageLimit(1000);
        adapter = new QuestionViewPagerAdapter(this);
        pager_questions.setAdapter(adapter);
        pager_questions.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_title.setText("第" + (position + 1) + "/" +  scores.length + "题");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
        public View getView(final int position, View convertView, ViewGroup container) {
            final QuestionEntity.QuestionInfo questionInfo = questionInfos.get(position);

            final ViewHolder holder;

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

            String type = questionInfo.getType();
            String typename = "";
            if (type.equals("1")) {
                typename = "单选题";
                holder.ly_singleselect.setVisibility(View.VISIBLE);
                holder.ly_multiselect.setVisibility(View.GONE);
                holder.ly_judge.setVisibility(View.GONE);
                holder.rb_single_a.setText(questionInfo.getA());
                holder.rb_single_a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        String aw = "";
                        if (isChecked) {
                            aw = "a";
                            answers[position] = "a";
                        } else {
                            return;
                        }
                        if (aw.equals(questionInfo.getAnswer())) {
                            scores[position] = onesource;
                        } else {
                            scores[position] = 0;
                        }
                    }
                });
                holder.rb_single_b.setText(questionInfo.getB());
                holder.rb_single_b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        String aw = "";
                        if (isChecked) {
                            aw = "b";
                            answers[position] = "b";
                        } else {
                            return;
                        }
                        if (aw.equals(questionInfo.getAnswer())) {
                            scores[position] = onesource;
                        } else {
                            scores[position] = 0;
                        }
                    }
                });
                holder.rb_single_c.setText(questionInfo.getC());
                holder.rb_single_c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        String aw = "";
                        if (isChecked) {
                            aw = "c";
                            answers[position] = "c";
                        } else {
                            return;
                        }
                        if (aw.equals(questionInfo.getAnswer())) {
                            scores[position] = onesource;
                        } else {
                            scores[position] = 0;
                        }
                    }
                });
                holder.rb_single_d.setText(questionInfo.getD());
                holder.rb_single_d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        String aw = "";
                        if (isChecked) {
                            aw = "d";
                            answers[position] = "d";
                        } else {
                            return;
                        }
                        if (aw.equals(questionInfo.getAnswer())) {
                            scores[position] = onesource;
                        } else {
                            scores[position] = 0;
                        }
                    }
                });
            } else if (type.equals("2")) {
                typename = "多选题";
                holder.ly_singleselect.setVisibility(View.GONE);
                holder.ly_multiselect.setVisibility(View.VISIBLE);
                holder.ly_judge.setVisibility(View.GONE);
                holder.rb_multi_a.setText(questionInfo.getA());
                holder.rb_multi_a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        String aw = "";
                        if (holder.rb_multi_a.isChecked()) {
                            aw += "a,";
                        }
                        if (holder.rb_multi_b.isChecked()) {
                            aw += "b,";
                        }
                        if (holder.rb_multi_c.isChecked()) {
                            aw += "c,";
                        }
                        if (holder.rb_multi_d.isChecked()) {
                            aw += "d,";
                        }
                        answers[position] = aw;
                        if (TextUtils.isEmpty(aw)) {
                            return;
                        }
                        aw = aw.substring(0, aw.length() - 1);
                        if (aw.equals(questionInfo.getAnswer())) {
                            scores[position] = onesource;
                        } else {
                            scores[position] = 0;
                        }
                    }
                });
                holder.rb_multi_b.setText(questionInfo.getB());
                holder.rb_multi_b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        String aw = "";
                        if (holder.rb_multi_a.isChecked()) {
                            aw += "a,";
                        }
                        if (holder.rb_multi_b.isChecked()) {
                            aw += "b,";
                        }
                        if (holder.rb_multi_c.isChecked()) {
                            aw += "c,";
                        }
                        if (holder.rb_multi_d.isChecked()) {
                            aw += "d,";
                        }
                        answers[position] = aw;
                        if (TextUtils.isEmpty(aw)) {
                            return;
                        }
                        aw = aw.substring(0, aw.length() - 1);
                        if (aw.equals(questionInfo.getAnswer())) {
                            scores[position] = onesource;
                        } else {
                            scores[position] = 0;
                        }
                    }
                });
                holder.rb_multi_c.setText(questionInfo.getC());
                holder.rb_multi_c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        String aw = "";
                        if (holder.rb_multi_a.isChecked()) {
                            aw += "a,";
                        }
                        if (holder.rb_multi_b.isChecked()) {
                            aw += "b,";
                        }
                        if (holder.rb_multi_c.isChecked()) {
                            aw += "c,";
                        }
                        if (holder.rb_multi_d.isChecked()) {
                            aw += "d,";
                        }
                        answers[position] = aw;
                        if (TextUtils.isEmpty(aw)) {
                            return;
                        }
                        aw = aw.substring(0, aw.length() - 1);
                        if (aw.equals(questionInfo.getAnswer())) {
                            scores[position] = onesource;
                        } else {
                            scores[position] = 0;
                        }
                    }
                });
                holder.rb_multi_d.setText(questionInfo.getD());
                holder.rb_multi_d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        String aw = "";
                        if (holder.rb_multi_a.isChecked()) {
                            aw += "a,";
                        }
                        if (holder.rb_multi_b.isChecked()) {
                            aw += "b,";
                        }
                        if (holder.rb_multi_c.isChecked()) {
                            aw += "c,";
                        }
                        if (holder.rb_multi_d.isChecked()) {
                            aw += "d,";
                        }
                        answers[position] = aw;
                        if (TextUtils.isEmpty(aw)) {
                            return;
                        }
                        aw = aw.substring(0, aw.length() - 1);
                        if (aw.equals(questionInfo.getAnswer())) {
                            scores[position] = onesource;
                        } else {
                            scores[position] = 0;
                        }
                    }
                });
            } else if (type.equals("0")) {
                typename = "判断题";
                holder.ly_singleselect.setVisibility(View.GONE);
                holder.ly_multiselect.setVisibility(View.GONE);
                holder.ly_judge.setVisibility(View.VISIBLE);
                holder.rb_yes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        String aw = "";
                        if (questionInfo.getYes_or_no().equals("1")) {
                            aw = "1";
                            scores[position] = onesource;
                            answers[position] = "1";
                        } else {
                            aw = "0";
                            scores[position] = 0;
                        }
                    }
                });
                holder.rb_no.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        String aw = "";
                        if (questionInfo.getYes_or_no().equals("0")) {
                            aw = "1";
                            scores[position] = onesource;
                            answers[position] = "2";
                        } else {
                            aw = "0";
                            scores[position] = 0;
                        }
                    }
                });
            }

            holder.tv_question.setText("(" + typename + ") " + (position + 1) + ". " + questionInfo.getQuestion());
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

    String[] answers;

    private void initData() {
        Api.getQuestion(self, new NetUtils.NetCallBack<ServiceResult>() {
            @Override
            public void success(ServiceResult rspData) {
                QuestionEntity questionEntity = (QuestionEntity) rspData;
                ArrayList<QuestionEntity.QuestionInfo> questionInfos = questionEntity.getData();
                examination_id = questionInfos.get(0).getExamination_id();
                if (questionInfos != null) {
                    scores = new Integer[questionInfos.size()];
                    for(int i=0; i<questionInfos.size(); i++) {
                        scores[i] = 0;
                    }

                    answers = new String[questionInfos.size()];
                    for(int i=0; i<questionInfos.size(); i++) {
                        answers[i] = "";
                    }

                    adapter.addQuestionList(questionInfos);
                    tv_title.setText("第1/" + scores.length + "题");
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

    private void upLoad() {
        for (int i=0; i<answers.length; i++) {
            if (TextUtils.isEmpty(answers[i])) {
                Toast.show(self, "第" + (i+1) + "题未作答，无法提交！");
                return;
            }
        }

        int s1 = 0;
        for (int s : scores) {
            s1 += s;
        }
        Api.addMyScore(self, Config.getUserId(), Integer.toString(s1), examination_id, new NetUtils.NetCallBack<ServiceResult>() {
            @Override
            public void success(ServiceResult rspData) {
                ExamEntity examEntity = (ExamEntity) rspData;
                Toast.show(self, examEntity.getMsg());
                finish();
            }

            @Override
            public void failed(String msg) {
                Toast.show(self, msg);
                finish();
            }
        }, ExamEntity.class);
    }
}
