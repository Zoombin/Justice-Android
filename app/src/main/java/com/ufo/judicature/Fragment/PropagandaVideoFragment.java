package com.ufo.judicature.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ufo.judicature.Base.BaseFragment;
import com.ufo.judicature.Entity.ServiceResult;
import com.ufo.judicature.Entity.VideosEntity;
import com.ufo.judicature.Net.Api;
import com.ufo.judicature.Net.NetUtils;
import com.ufo.judicature.R;
import com.ufo.judicature.Widget.OnRefreshListener;
import com.ufo.judicature.Widget.RefreshListView;
import com.ufo.judicature.Widget.Toast;

import java.util.ArrayList;

/**
 * 宣传普法(视频)
 */
public class PropagandaVideoFragment extends BaseFragment implements OnRefreshListener {

    private RefreshListView lv_video;
    private int page = 0;
    private VideoListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_propaganda_video, container, false);
        createView(v);
        initData();
        return v;
    }

    private void createView(View v) {
        lv_video = (RefreshListView) v.findViewById(R.id.lv_video);
        adapter = new VideoListViewAdapter(mActivity);
        lv_video.setAdapter(adapter);
        lv_video.setOnRefreshListener(this);
    }

    private void initData() {
        getData();
    }

    private void getData() {
        Api.getVideos(mActivity, page, new NetUtils.NetCallBack<ServiceResult>() {
            @Override
            public void success(ServiceResult rspData) {
                if (page == 0) {
                    adapter.clearData();
                }
                lv_video.hideHeaderView();
                lv_video.hideFooterView();
                VideosEntity entity = (VideosEntity) rspData;
                ArrayList<VideosEntity.VideoEntity> videos = entity.getData();
                adapter.addVideosList(videos);
                page++;
            }

            @Override
            public void failed(String msg) {
                lv_video.hideHeaderView();
                lv_video.hideFooterView();
                Toast.show(mActivity, msg);
            }
        }, VideosEntity.class);
    }

    @Override
    public void onDownPullRefresh() {
        page = 0;
        getData();
    }

    @Override
    public void onLoadingMore() {
        getData();
    }

    public class VideoListViewAdapter extends BaseAdapter {

        private Context context;
        private ArrayList<VideosEntity.VideoEntity> videos = new ArrayList<>();

        public VideoListViewAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return videos.size();
        }

        @Override
        public Object getItem(int position) {
            return videos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void clearData() {
            videos.clear();
            notifyDataSetChanged();
        }

        public void addVideosList(ArrayList<VideosEntity.VideoEntity> videos) {
            this.videos.addAll(videos);
            notifyDataSetChanged();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final VideosEntity.VideoEntity videoEntity = videos.get(position);
            ViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(
                        R.layout.videos_listview_item, parent, false);

                holder = new ViewHolder();

                holder.rl_video = (RelativeLayout) convertView.findViewById(R.id.rl_video);
                holder.image_video = (ImageView) convertView.findViewById(R.id.image_video);
                holder.tv_video_title = (TextView) convertView.findViewById(R.id.tv_video_title);
                holder.tv_video_content = (TextView) convertView.findViewById(R.id.tv_video_content);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.rl_video.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(Uri.parse(videoEntity.getStream()), "video/*");
                    startActivity(intent);
                }
            });
            ImageLoader.getInstance().displayImage(videoEntity.getImage(), holder.image_video);
            holder.tv_video_title.setText(videoEntity.getTitle());
            holder.tv_video_content.setText(videoEntity.getContent());

            return convertView;
        }

        public class ViewHolder {
            RelativeLayout rl_video;
            ImageView image_video;
            TextView tv_video_title;
            TextView tv_video_content;
        }
    }
}
