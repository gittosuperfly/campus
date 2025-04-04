/**
 * Project Name:mobpush-api-java-client
 * File Name:PushClient.java
 * Package Name:mob.push.api.push
 * Date: 2018年2月6日
 * Time: 上午10:45:21
 *
*/
package com.cai.campus.push.push;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cai.campus.push.MobPushConfig;
import com.cai.campus.push.exception.ApiException;
import com.cai.campus.push.model.PushWork;
import com.cai.campus.push.utils.*;

/**
 * ClassName:DeviceClient
 * 推送接口，包含查询和创建推送 
 */
public class PushClient {
	 
	/**
	 * 创建通知消息 -- 所有平台、广播
	 * @param workno
	 * @param content
	 * @return batchId (MobPush 推送消息唯一ID)
	 * @throws ApiException
	 */
	public String createPushDefaultNotify(String workno, String content) throws ApiException {
		PushWork work = new PushWork(MobPushConfig.appkey, workno, PlatEnum.all.getCode(), content, PushTypeEnum.notify.getCode()).buildTarget(TargetEnum.ALL.getCode(), null, null, null, null, null);
		return this.sendPush(work);
	}
	
	/**
	 * 自定义消息 -- 所有平台、广播
	 * @param workno
	 * @param content
	 * @return batchId (MobPush 推送消息唯一ID)
	 * @throws ApiException
	 */
	public String createPushByDefaultCustom(String workno, String content) throws ApiException{ 
		PushWork work = new PushWork(MobPushConfig.appkey, workno, PlatEnum.all.getCode(), content, PushTypeEnum.custom.getCode()).buildTarget(TargetEnum.ALL.getCode(), null, null, null, null, null);
  		return this.sendPush(work);
	}
	  
	/**
	 * 发送推送
	 * @param pushWork
	 * @return batchId (MobPush 推送消息唯一ID)
	 * @throws ApiException
	 */
	public String sendPush(PushWork pushWork) throws ApiException{
		String path = MobPushConfig.pushUrl + "/v2/push";
		if(pushWork.getAppkey() == null){
			 pushWork.setAppkey(MobPushConfig.appkey);
		}
		if(pushWork.getContent() == null){
			throw new ApiException(MobHelper.HTTP_STATUS_400, -1, "content is null");
		}else if(pushWork.getTarget() == null){
			throw new ApiException(MobHelper.HTTP_STATUS_400, -1, "target is null");
		}else if(pushWork.getType() == null){
			throw new ApiException(MobHelper.HTTP_STATUS_400, -1, "type is null");
		}
		HttpUtils.PostEntity entity = new HttpUtils.PostEntity(path, MobPushConfig.appkey, MobPushConfig.appSecret,
				JSON.toJSONString(pushWork)).invoke(false);
		MobPushResult result = null;
		if (entity.getStatusCode() == MobHelper.HTTP_STATUS_200) {
			result = JSON.toJavaObject(JSON.parseObject(entity.getResp()), MobPushResult.class);
		} else {
			result = JSON.toJavaObject(JSON.parseObject(entity.getResp()), MobPushResult.class);
			throw new ApiException(entity.getStatusCode(), result.getStatus(), result.getError());
		} 
		
		if (result != null) {
			if (result.getRes() == null) {
				return null;
			}
			JSONObject json = (JSONObject) result.getRes();
			return json.getString("batchId");
		}
		return null;
	}
	
	
	/**
	 * 推送详情（根据batchId查询）
	 * @param batchId
	 * @return PushWork
	 * @throws ApiException
	 */
	public PushWork getPushByBatchId(String batchId) throws ApiException{
		if (batchId == null || "".equals(batchId.trim())) {
			throw new ApiException(MobHelper.HTTP_STATUS_400, -1, "batchId is null");
		}
		String path = MobPushConfig.pushUrl + "/push/id/" + batchId;
		return this.pullPush(path);
	}
	
	/**
	 * 推送详情（根据workno查询）
	 * @param workno
	 * @return PushWork
	 * @throws ApiException
	 */
	public PushWork getPushByWorkno(String workno) throws ApiException{
		if (workno == null || "".equals(workno.trim())) {
			throw new ApiException(MobHelper.HTTP_STATUS_400, -1, "workno is null");
		}
		String path = MobPushConfig.pushUrl + "/push/workno/" + workno;
		return this.pullPush(path);
	}
	
	/**
	 * 拉取推送详情
	 * @param path
	 * @return PushWork
	 * @throws ApiException
	 */
	private PushWork pullPush(String path) throws ApiException{
		HttpUtils.GetEntity entity = new HttpUtils.GetEntity(path, MobPushConfig.appkey, MobPushConfig.appSecret, null)
				.invoke();
		MobPushResult result = null;
		if (entity.getStatusCode() == MobHelper.HTTP_STATUS_200) {
			result = JSON.toJavaObject(JSON.parseObject(entity.getResp()), MobPushResult.class);
		} else {
			result = JSON.toJavaObject(JSON.parseObject(entity.getResp()), MobPushResult.class);
			throw new ApiException(entity.getStatusCode(), result.getStatus(), result.getError());
		}
		if (result != null) {
			if (result.getRes() == null) {
				return null;
			}
			JSONObject json = (JSONObject) result.getRes();
			PushWork work = json.toJavaObject(PushWork.class);
			return work;
		}
		return null;
	}
	
	 
 
}
