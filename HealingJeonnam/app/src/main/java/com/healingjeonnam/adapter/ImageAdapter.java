package com.healingjeonnam.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.healingjeonnam.R;
import com.healingjeonnam.models.Forest;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by econo110 on 2015-10-16.
 */

public class ImageAdapter extends PagerAdapter {
    Context context;
    Forest forest;


    public ImageAdapter(Context context, Forest forest1){
        this.context=context;
        this.forest = forest1;
    }
    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        int padding = context.getResources().getDimensionPixelSize(R.dimen.abc_list_item_padding_horizontal_material);
        imageView.setPadding(0,0,0,0);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageLoader.getInstance().displayImage(forest.getImages().get(position), imageView);
        ((ViewPager) container).addView(imageView, 0);

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }
}