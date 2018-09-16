package com.company.project.android.ui.fragment.adsfragment;

import android.net.Uri;

import com.company.project.android.api.ApiEngine;
import com.company.project.android.bean.DataResponse;
import com.company.project.android.bean.User;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @author Administrator
 * @name MyMvp
 * @class name：com.company.project.android.ui.fragment.adsfragment
 * @class describe
 * @time 2018/3/12 17:38
 * @change
 * @class describe
 */


public class AdsModel implements AdsContract.Model {
    @Override
    public Observable<DataResponse> getConfigInfo(String device_id, String version, String ads_support, long timetoken) {
        return ApiEngine.getInstance().getApiService().getConfigInfoApi(device_id,version,ads_support,timetoken);
    }

    @Override
    public Observable<User> uploadFile(File file,String fileType,Map<String, Object> map) {
        //Map<String,Object> map = new HashMap<>();
        RequestBody requestBody= RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData(fileType,file.getName(),requestBody);
        return  ApiEngine.getInstance().getApiService().uploadFile(part,map);
    }

    @Override
    public Observable<User> uploadFilesWithParts(File file, String fileType, Map<String, Object> map) {
        RequestBody requestBody= RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData(fileType,file.getName(),requestBody);
        List<MultipartBody.Part> partList=new ArrayList<>();
        partList.add(part);
        return  ApiEngine.getInstance().getApiService().uploadFilesWithParts(partList,map);
    }


    /**
     * 封装请求体，可以看到这里和OkHttp的请求体封装基本上是一样的
     * @param partName
     * @param path
     * @return
     */
    public static MultipartBody.Part prepareFilePart(String partName, String path) {
        File file = new File(path);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }

    private void uploadFile(Uri fileUri) {
        File file=new File("/sdcard/1.txt");
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        Map<String, RequestBody> params=new HashMap<>() ;
        params.put("file\"; filename=\""+ file.getName(), requestBody);
       // ApiEngine.getInstance().getApiService().postBookString(params);
        ApiEngine.getInstance().getApiService().postBookString(prepareFilePart(file.getName(),file.getAbsolutePath()),null,null);
    }



}