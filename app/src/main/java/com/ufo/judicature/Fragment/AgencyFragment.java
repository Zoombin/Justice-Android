package com.ufo.judicature.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.ufo.judicature.Base.BaseFragment;
import com.ufo.judicature.Entity.AgencyEntity;
import com.ufo.judicature.Entity.ServiceResult;
import com.ufo.judicature.Net.Api;
import com.ufo.judicature.Net.NetUtils;
import com.ufo.judicature.R;
import com.ufo.judicature.Widget.Toast;

import java.util.ArrayList;

/**
 * 服务机构
 */
public class AgencyFragment extends BaseFragment {

    private ExpandableListView lv_agency;
    private MyExpandableListViewAdapter adapter;
    private ArrayList<String> group_list= new ArrayList<>();
    private ArrayList<ArrayList<String>> item_list = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_agency, container, false);
        createView(v);
        initData();
        return v;
    }

    private void createView(View v) {
        lv_agency = (ExpandableListView) v.findViewById(R.id.lv_agency);
        lv_agency.setGroupIndicator(null);
        adapter = new MyExpandableListViewAdapter(mActivity);
        lv_agency.setAdapter(adapter);
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
            ItemHolder itemHolder = null;
            if (convertView == null) {
                convertView = (View) mActivity.getLayoutInflater().from(context).inflate(
                        R.layout.agency_item_sub, null);
                itemHolder = new ItemHolder();
                itemHolder.txt = (TextView) convertView.findViewById(R.id.tv_service);
                convertView.setTag(itemHolder);
            } else {
                itemHolder = (ItemHolder) convertView.getTag();
            }
            itemHolder.txt.setText(item_list.get(groupPosition).get(
                    childPosition));
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
        public TextView txt;
    }

    private void initData() {
        Api.getService(mActivity, new NetUtils.NetCallBack<ServiceResult>() {
            @Override
            public void success(ServiceResult rspData) {
                AgencyEntity entity = (AgencyEntity) rspData;
                ArrayList<AgencyEntity.Services> services = entity.getData();
                for (AgencyEntity.Services service : services) {
                    group_list.add(service.getTitle());

                    ArrayList<AgencyEntity.Service> servicesub = service.getServices();
                    if (servicesub != null) {
                        ArrayList<String> sub = new ArrayList<>();
                        for (AgencyEntity.Service service1 : servicesub) {
                            sub.add(service1.getName());
                        }
                        item_list.add(sub);
                    } else {
                        item_list.add(new ArrayList<String>());
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void failed(String msg) {
                Toast.show(mActivity, msg);
            }
        }, AgencyEntity.class);
    }
}
