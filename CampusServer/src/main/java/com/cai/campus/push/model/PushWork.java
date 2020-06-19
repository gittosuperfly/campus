
package com.cai.campus.push.model;

import com.cai.campus.push.utils.AndroidNotifyStyleEnum;
import com.cai.campus.push.utils.MobHelper;
import com.cai.campus.push.utils.TargetEnum;

/**
 * ClassName:PushWork
 * MobPush 推送实体
 */
public class PushWork {

    /**
     * id: MobPush 生产唯一编号
     */
    public String id;

    /**
     * appkey: 应用APPKEY
     */
    public String appkey;

    /**
     * workno:调用方提供的唯一编号，需要在当前appkey下唯一不可重复
     */
    public String workno;

    /**
     * plats：接受平台，1、android ； 2、ios ；如包含ios和android则为[1,2]
     */
    public Integer[] plats;

    /**
     * target:推送范围:1广播；2别名；3标签；4regid；5地理位置;6用户分群
     * TargetEnum
     */
    public Integer target;

    /**
     * tags:设置推送标签集合["tag1","tag2"]，target=2则必选
     */
    public String[] tags;

    /**
     * alias: 设置推送别名集合["alias1","alias2"]，target=3则必选
     */
    public String[] alias;

    /**
     * registrationIds:设置推送Registration Id集合["id1","id2"]，target=4则必选
     */
    public String[] registrationIds;

    /**
     * city: 推送地理位置(城市)，target=5则必选
     */
    public String city;

    /**
     * block: 用户分群ID，target=6则必选
     */
    public String block;

    /**
     * content: 推送内容
     */
    public String content;

    /**
     * unlineTime: 离线时间为0，或者在1~10天以内，单位天， 默认1天
     */
    public Integer unlineTime = 1;

    /**
     * type: 推送类型：1通知；2自定义
     * PushTypeEnum
     */
    public Integer type;

    /**
     * extras:附加字段键值对的方式，扩展数据 json
     */
    public String extras;

    /**
     * 推送任务状态：0删除；1创建中；2正在发送；3部分发送完成；4发送完成；5发送失败；6停止发送； WorkDetailStatusEnum
     */
    private Integer status;

    /**
     * 是否重新发送,true是，默认false否
     */
    private Boolean repate;

    /**
     * 通知标题
     */
    private String androidTitle;

    /**
     * 显示样式标识
     */
    private Integer androidstyle = AndroidNotifyStyleEnum.normal.getCode();

    /**
     * content:样式具体内容： 0、默认通知无； 1、长内容则为内容数据； 2、大图则为图片地址； 3、横幅则为多行内容
     */
    private String[] androidContent;

    /**
     * warn:  提醒类型： 提示音；
     */
    private Boolean androidVoice;

    /**
     * androidShake:震动
     */
    private Boolean androidShake;

    /**
     * androidLight:指示灯
     */
    private Boolean androidLight;

    /**
     * moblink功能的的uri
     */
    private String scheme;
    /**
     * moblink功能的参数
     */
    private String data;

    public PushWork() {

    }

    public PushWork(Integer[] plats, String content, Integer type) {
        this.plats = plats;
        this.content = content;
        this.type = type;
    }

    public PushWork(String workno, Integer[] plats, String content, Integer type) {
        this.workno = workno;
        this.plats = plats;
        this.content = content;
        this.type = type;
    }

    public PushWork(String appkey, String workno, Integer[] plats, String content, Integer type) {
        this.appkey = appkey;
        this.workno = workno;
        this.plats = plats;
        this.content = content;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getWorkno() {
        return workno;
    }

    public void setWorkno(String workno) {
        this.workno = workno;
    }

    public Integer[] getPlats() {
        return plats;
    }

    public void setPlats(Integer[] plats) {
        this.plats = plats;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public String[] getAlias() {
        return alias;
    }

    public void setAlias(String[] alias) {
        this.alias = alias;
    }

    public String[] getRegistrationIds() {
        return registrationIds;
    }

    public void setRegistrationIds(String[] registrationIds) {
        this.registrationIds = registrationIds;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getUnlineTime() {
        return unlineTime;
    }

    public void setUnlineTime(Integer unlineTime) {
        this.unlineTime = unlineTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getRepate() {
        return repate;
    }

    public void setRepate(Boolean repate) {
        this.repate = repate;
    }

    public String getAndroidTitle() {
        return androidTitle;
    }

    public void setAndroidTitle(String androidTitle) {
        this.androidTitle = androidTitle;
    }

    public Integer getAndroidstyle() {
        return androidstyle;
    }

    public void setAndroidstyle(Integer androidstyle) {
        this.androidstyle = androidstyle;
    }

    public String[] getAndroidContent() {
        return androidContent;
    }

    public void setAndroidContent(String[] androidContent) {
        this.androidContent = androidContent;
    }

    public Boolean getAndroidVoice() {
        return androidVoice;
    }

    public void setAndroidVoice(Boolean androidVoice) {
        this.androidVoice = androidVoice;
    }

    public Boolean getAndroidShake() {
        return androidShake;
    }

    public void setAndroidShake(Boolean androidShake) {
        this.androidShake = androidShake;
    }

    public Boolean getAndroidLight() {
        return androidLight;
    }

    public void setAndroidLight(Boolean androidLight) {
        this.androidLight = androidLight;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    /**
     * 设置扩展信息
     *
     * @return PushWork
     */
    public PushWork buildExtra(Integer unlineTime, String extras) {
        if (unlineTime != null)
            this.unlineTime = unlineTime;
        if (MobHelper.isNotBlank(extras))
            this.extras = extras;
        return this;
    }

    /**
     * 设置推送范围
     *
     * @return PushWork
     */
    public PushWork buildTarget(Integer target, String[] tags, String[] alias, String[] registrationIds, String city, String block) {
        if (target == null || target.intValue() == TargetEnum.ALL.getCode()) {
            this.target = target;
            return this;
        }
        if (target.intValue() == TargetEnum.PHONE.getCode() && alias != null) {
            this.alias = alias;
        } else if (target.intValue() == TargetEnum.GROUP.getCode() && tags != null) {
            this.tags = tags;
        }
        this.target = target;
        return this;
    }

    /**
     * 设置Android信息
     *
     * @return PushWork
     */
    public PushWork buildAndroid(String androidTitle, Integer androidstyle, String[] androidContent,
                                 Boolean androidVoice, Boolean androidShake, Boolean androidLight) {
        if (MobHelper.isNotBlank(androidTitle))
            this.androidTitle = androidTitle;
        if (androidstyle != null)
            this.androidstyle = androidstyle;
        if (androidContent != null)
            this.androidContent = androidContent;
        if (androidVoice != null)
            this.androidVoice = androidVoice;
        if (androidShake != null)
            this.androidShake = androidShake;
        if (androidLight != null)
            this.androidLight = androidLight;
        return this;
    }
}
