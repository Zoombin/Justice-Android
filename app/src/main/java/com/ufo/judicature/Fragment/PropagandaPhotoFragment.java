package com.ufo.judicature.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ufo.judicature.Activity.PhotoDetailActivity;
import com.ufo.judicature.Base.BaseFragment;
import com.ufo.judicature.Entity.PhotosEntity;
import com.ufo.judicature.Entity.ServiceResult;
import com.ufo.judicature.Net.Api;
import com.ufo.judicature.Net.NetUtils;
import com.ufo.judicature.R;
import com.ufo.judicature.Widget.OnRefreshListener;
import com.ufo.judicature.Widget.RefreshListView;
import com.ufo.judicature.Widget.Toast;

import java.util.ArrayList;

/**
 * 宣传普法（图片）
 */
public class PropagandaPhotoFragment extends BaseFragment implements OnRefreshListener {

    private RefreshListView lv_photo;
    private int page = 0;
    private PhotoListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_propaganda_photo, container, false);
        createView(v);
        initData();
        return v;
    }

    private void createView(View v) {
        lv_photo = (RefreshListView) v.findViewById(R.id.lv_photo);
        adapter = new PhotoListViewAdapter(mActivity);
        lv_photo.setAdapter(adapter);
        lv_photo.setOnRefreshListener(this);
    }

    @Override
    public void onDownPullRefresh() {
        page = 0;
        adapter.clearData();
        getData();
    }

    @Override
    public void onLoadingMore() {
        getData();
    }

    private void initData() {
        getData();
    }

    private void getData() {
        Api.getPhotos(mActivity, page, new NetUtils.NetCallBack<ServiceResult>() {
            @Override
            public void success(ServiceResult rspData) {
                lv_photo.hideHeaderView();
                lv_photo.hideFooterView();
                PhotosEntity entity = (PhotosEntity) rspData;
                ArrayList<PhotosEntity.PhotoEntity> photos = entity.getData();
                adapter.addPhotosList(photos);
                page++;
            }

            @Override
            public void failed(String msg) {
                lv_photo.hideHeaderView();
                lv_photo.hideFooterView();
                Toast.show(mActivity, msg);
            }
        }, PhotosEntity.class);
    }

    public class PhotoListViewAdapter extends BaseAdapter {

        private Context context;
        private ArrayList<PhotosEntity.PhotoEntity> photos = new ArrayList<>();

        public PhotoListViewAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return photos.size();
        }

        @Override
        public Object getItem(int position) {
            return photos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        public void clearData() {
            photos.clear();
            notifyDataSetChanged();
        }

        public void addPhotosList(ArrayList<PhotosEntity.PhotoEntity> photos) {
            this.photos.addAll(photos);
            notifyDataSetChanged();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final PhotosEntity.PhotoEntity photoEntity = photos.get(position);
            ViewHolder holder;

            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(
                        R.layout.photos_listview_item, parent, false);

                holder = new ViewHolder();

                holder.rl_photo = (RelativeLayout) convertView.findViewById(R.id.rl_photo);
                holder.image_photo = (ImageView) convertView.findViewById(R.id.image_photo);
                holder.tv_photo_title = (TextView) convertView.findViewById(R.id.tv_photo_title);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.rl_photo.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity, PhotoDetailActivity.class);
                    intent.putExtra(PhotoDetailActivity.EXTRA_PHOTODETAIL, photoEntity.getPhotos());
                    startActivity(intent);
                }
            });
            ImageLoader.getInstance().displayImage(photoEntity.getCover(), holder.image_photo);
            holder.tv_photo_title.setText(photoEntity.getTitle());

            return convertView;
        }

        public class ViewHolder {
            RelativeLayout rl_photo;
            ImageView image_photo;
            TextView tv_photo_title;
        }
    }
}
