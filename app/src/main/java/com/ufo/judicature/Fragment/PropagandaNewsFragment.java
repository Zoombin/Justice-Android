package com.ufo.judicature.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ufo.judicature.Base.BaseFragment;
import com.ufo.judicature.Entity.NewsEntity;
import com.ufo.judicature.R;
import com.ufo.judicature.Widget.OnRefreshListener;
import com.ufo.judicature.Widget.RefreshListView;

import java.util.ArrayList;

/**
 * 宣传普法（趣闻）
 */
public class PropagandaNewsFragment extends BaseFragment implements OnRefreshListener {

    private RefreshListView lv_news;
    private int currentItem;
    private View header;
    private ViewPager guidePages;
    private LinearLayout viewGroup;
    private NewsListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_propaganda_news, container, false);
        createView(v);
        initData();
        return v;
    }

    private void createView(View v) {
        lv_news = (RefreshListView) v.findViewById(R.id.lv_news);

        header = LayoutInflater.from(mActivity).inflate(R.layout.viewpage_header, null);
        guidePages = (ViewPager) header.findViewById(R.id.guidePages);
        viewGroup = (LinearLayout) header.findViewById(R.id.viewGroup);

        adapter = new NewsListViewAdapter(mActivity);
        lv_news.addHeaderView(header);
        lv_news.setAdapter(adapter);//
        lv_news.setOnRefreshListener(this);

        guidePages.addOnPageChangeListener(new NavigationPageChangeListener());
    }

    private void initData() {

    }

    @Override
    public void onDownPullRefresh() {

    }

    @Override
    public void onLoadingMore() {

    }

    class NavigationPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int state) {

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
//            currentItem = arg0;
//            for (int i = 0; i < imageViews.length; i++) {
//                if (arg0 == i) {
//                    imageViews[i].setImageDrawable(getResources().getDrawable(R.drawable.page_focused));
//                } else {
//                    imageViews[i].setImageDrawable(getResources().getDrawable(R.drawable.page_unfocused));
//                }
//            }
        }
    }

    public class NewsListViewAdapter extends BaseAdapter {

        private Context context;
        private ArrayList<NewsEntity> news = new ArrayList<>();

        public NewsListViewAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return news.size();
        }

        @Override
        public Object getItem(int position) {
            return news.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void clearData() {
            news.clear();
        }

        public void addGoodsList(ArrayList<NewsEntity> news) {
            this.news = news;
            notifyDataSetChanged();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final NewsEntity new1 = news.get(position);
            ViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(
                        R.layout.news_listview_item, parent, false);

                holder = new ViewHolder();

                holder.rl_new = (RelativeLayout) convertView.findViewById(R.id.rl_new);
                holder.image_new = (ImageView) convertView.findViewById(R.id.image_new);
                holder.tv_new_title = (TextView) convertView.findViewById(R.id.tv_new_title);
                holder.tv_new_content = (TextView) convertView.findViewById(R.id.tv_new_content);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.rl_new.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
//                    String goodsid = good.getGoodsid();
//                    Intent intent = new Intent(context, ProductDetailActivity.class);
//                    intent.putExtra(ProductDetailActivity.EXTRA_GOODSID, goodsid);
//                    context.startActivity(intent);
                }
            });
//            ImageLoader.getInstance().displayImage(new1.getGoodsimage(), holder.image_new);
//            holder.category_productlist_list_item_title.setText(new1.getGoodsname());
//            holder.category_productlist_list_item_price.setText(new1.getPrice());

            return convertView;
        }

        public class ViewHolder {
            RelativeLayout rl_new;
            ImageView image_new;
            TextView tv_new_title;
            TextView tv_new_content;
        }
    }
}
