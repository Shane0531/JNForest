package com.healingjeonnam.base;

import android.app.Application;

import com.healingjeonnam.models.Forest;
import com.healingjeonnam.models.Mountain;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by econo110 on 2015-10-17.
 */
public class ForestApp extends Application{
    public final static List<Forest> forest = new ArrayList<>();
    public final static List<Mountain> mountain = new ArrayList<>();
    @Override
    public void onCreate(){
        super.onCreate();
        DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.ic_stub) // resource or drawable
//                .showImageForEmptyUri(R.drawable.ic_empty) // resource or drawable
//                .showImageOnFail(R.drawable.ic_error) // resource or drawable
                .resetViewBeforeLoading(true)  // default
//                .delayBeforeLoading(1000)
                .cacheInMemory(true) // default
                .cacheOnDisk(true) // default
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).memoryCache(new WeakMemoryCache()).defaultDisplayImageOptions(options).memoryCacheSizePercentage(30).build();
        ImageLoader.getInstance().init(config);

    }

}
