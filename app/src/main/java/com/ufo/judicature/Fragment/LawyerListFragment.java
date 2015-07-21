package com.ufo.judicature.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easemob.chatuidemo.DemoHXSDKHelper;
import com.easemob.chatuidemo.activity.ChatActivity;
import com.easemob.chatuidemo.activity.LoginActivity;
import com.easemob.chatuidemo.domain.User;
import com.ufo.judicature.Base.BaseFragment;
import com.ufo.judicature.Entity.LawyersEntity;
import com.ufo.judicature.Entity.ServiceResult;
import com.ufo.judicature.Net.Api;
import com.ufo.judicature.Net.NetUtils;
import com.ufo.judicature.R;
import com.ufo.judicature.Widget.Toast;

import java.util.ArrayList;

/**
 * 律师在线
 */
public class LawyerListFragment extends BaseFragment {

    private ExpandableListView listView;
    private MyExpandableListViewAdapter adapter;
    private ArrayList<String> group_list = new ArrayList<>();
    public static ArrayList<ArrayList<User>> item_list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_lawyer_list, container, false);

        listView = (ExpandableListView) v.findViewById(R.id.list);
        listView.setGroupIndicator(null);
        getContactList();
        return v;
    }

    // 刷新ui
    public void refresh() {
        try {
            // 可能会在子线程中调到这方法
            mActivity.runOnUiThread(new Runnable() {
                public void run() {
                    getContactList();
                    adapter.notifyDataSetChanged();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取联系人列表
     */
    private void getContactList() {
        Api.getLawyers(mActivity, new NetUtils.NetCallBack<ServiceResult>() {
            @Override
            public void success(ServiceResult rspData) {
                LawyersEntity lawyersEntity = (LawyersEntity) rspData;
                ArrayList<LawyersEntity.LawyerGroup> lawyerGroups = lawyersEntity.getData();

                for (LawyersEntity.LawyerGroup lawyerGroup : lawyerGroups) {
                    group_list.add(lawyerGroup.getName());
                    ArrayList<User> users = new ArrayList<User>();
                    if (lawyerGroup.getLawyers() != null) {
                        for (LawyersEntity.Lawyer lawyer : lawyerGroup.getLawyers()) {
                            User user = new User();
                            user.setUsername(lawyer.getAccount());
                            user.setNick(lawyer.getNickname());
                            users.add(user);
                        }

                    }
                    item_list.add(users);
                }

                adapter = new MyExpandableListViewAdapter(mActivity);
                listView.setAdapter(adapter);
            }

            @Override
            public void failed(String msg) {
                Toast.show(mActivity, msg);
            }
        }, LawyersEntity.class);
    }

    class MyExpandableListViewAdapter extends BaseExpandableListAdapter {

        private Context context;

        public MyExpandableListViewAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getGroupCount() {
            return group_list.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return item_list.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return group_list.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return item_list.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded,
                                 View convertView, ViewGroup parent) {
            GroupHolder groupHolder = null;
            if (convertView == null) {
                convertView = (View) mActivity.getLayoutInflater().from(context).inflate(
                        R.layout.agency_item, null);
                groupHolder = new GroupHolder();
                groupHolder.txt = (TextView) convertView.findViewById(R.id.tv_services);
                groupHolder.img = (ImageView) convertView.findViewById(R.id.iv_arrow);
                convertView.setTag(groupHolder);
            } else {
                groupHolder = (GroupHolder) convertView.getTag();
            }
            groupHolder.txt.setText(group_list.get(groupPosition));
            if (isExpanded) {
                groupHolder.img.setImageResource(R.drawable.arrow_down);
            } else {
                groupHolder.img.setImageResource(R.drawable.arrow_right);
            }
            return convertView;
        }

        @Override
        public View getChildView(int groupPosition, int childPosition,
                                 boolean isLastChild, View convertView, ViewGroup parent) {
            final User user = item_list.get(groupPosition).get(
                    childPosition);

            ItemHolder itemHolder = null;
            if (convertView == null) {
                convertView = (View) mActivity.getLayoutInflater().from(context).inflate(
                        R.layout.agency_item_sub, null);
                itemHolder = new ItemHolder();
                itemHolder.ly_service_sub = (LinearLayout) convertView.findViewById(R.id.ly_service_sub);
                itemHolder.txt = (TextView) convertView.findViewById(R.id.tv_service);
                convertView.setTag(itemHolder);
            } else {
                itemHolder = (ItemHolder) convertView.getTag();
            }

            itemHolder.ly_service_sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!DemoHXSDKHelper.getInstance().isLogined()) {
                        startActivity(new Intent(mActivity, LoginActivity.class));
                        return;
                    }

                    String username = user.getUsername();
                    Intent intent = new Intent(mActivity, ChatActivity.class);
                    intent.putExtra("userId", username);
                    intent.putExtra("userNick", user.getNick());
                    startActivity(intent);
                }
            });
            itemHolder.txt.setText(user.getNick());
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }
    }

    class GroupHolder {
        public TextView txt;
        public ImageView img;
    }

    class ItemHolder {
        public LinearLayout ly_service_sub;
        public TextView txt;
    }
}
