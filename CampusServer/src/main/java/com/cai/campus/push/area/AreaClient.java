package com.cai.campus.push.area;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cai.campus.push.MobPushConfig;
import com.cai.campus.push.exception.ApiException;
import com.cai.campus.push.model.Area;
import com.cai.campus.push.utils.HttpUtils;
import com.cai.campus.push.utils.MobHelper;
import com.cai.campus.push.utils.MobPushResult;

/**
 * ClassName:AreaClient
 * TODO 获取地理位置列表信息
 */
public class AreaClient {

    /**
     * 获取地理位置列表 -- 中国下省份列表
     * @return List<Area>
     * @throws ApiException
     */
    public List<Area> getArea() throws ApiException {
        return getArea(null);
    }

    /**
     * 获取地理位置列表 -- 子级列表
     * @param parentId 如果查询最上级则传入null即可
     * @return List<Area>
     * @throws ApiException
     */
    public List<Area> getArea(String parentId) throws ApiException {
        List<Area> list = new ArrayList<Area>();
        if (parentId == null || parentId.trim().equals("")) {
            parentId = "0";
        }
        String path = MobPushConfig.baseUrl + "/area/" + parentId;
        HttpUtils.GetEntity entity = new HttpUtils.GetEntity(path, MobPushConfig.appkey, MobPushConfig.appSecret, null).invoke();
        MobPushResult result = null;
        if (entity.getStatusCode() == MobHelper.HTTP_STATUS_200) {
            result = JSON.toJavaObject(JSON.parseObject(entity.getResp()), MobPushResult.class);
        } else {
            result = JSON.toJavaObject(JSON.parseObject(entity.getResp()), MobPushResult.class);
            throw new ApiException(entity.getStatusCode(), result.getStatus(), result.getError());
        }
        if (result != null) {
            if (result.getRes() == null) {
                return list;
            }
            list = JSONArray.parseArray(JSON.toJSONString(result.getRes()), Area.class);
        }
        return list;
    }
}

