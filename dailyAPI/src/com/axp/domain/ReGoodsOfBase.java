package com.axp.domain;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 重构项目的基础商品表；
 * 用来记录基础的商品数据，就是在投放各个商城前的商品；
 *
 * @author Administrator
 */

public class ReGoodsOfBase extends ReBaseDomain {

	private String sign = UUID.randomUUID().toString();//商品的唯一编号；
	private String name;//商品名称；

	private String coverPic;//商品的封面图片；
    private String descriptionPics;//商品简述图；1，最多五张；2，使用“&&&”符号隔开，拼成字符串保存，不关联任何表；

	private String type;//商品的类别；1，商品的类别是由我们定好的，商家不能自定义类别；2，最多可以选择三个商品类别；3，使用json的格式记录商品的类别；
	private String lables;//商品的标签；1，标签也是预先定义好的；2，同样使用json的格式记录标签数据；
	private Seller seller;//商品对应的商家id值；//商品对应的商家id值；
	private String intro;//商品的描述文字；
	private String details;//商品的详细介绍内容；
	private Integer snapshotId;//此商品目前对应的最新的快照区域的商品id值；
	private Integer checkStatus = 0;//审核状态；0：表示未审核，1：审核通过；2：审核未通过；
	private String launchMall = "000000000";//该商品投放到哪个商城；使用七个字符的组成的字符串表示；
	
	private Integer salesVolume = 0;
	
	private String coverPicOne = "";
	private String lablesChar;
	private String rightsProtect;
	private Integer commodityTypeId;
	private String dpi = "nomal";

	//==============针对投放商城的一些方法======================================================================================
	public static final int sellerMall = 1;//独立商城
	public static final int scoreMall = 2;//积分商城
	public static final int seckillMall = 3;//秒杀商城
	public static final int localSpecialtyMall = 4;//各地特产商城
	public static final int nineNineMall = 5;//99特惠商城
	public static final int memberMall = 6;//会员免单商城
	public static final int changeMall=7; //换货会商城
	public static final int teamMall=10;  //拼团
	public static final int yhqMall=9;  //拼团
	public static final Integer lockMall = 8;//积分抽奖
	/**
	 * 根据商城的编号，获取商城的名称；
	 * @param mallId 商城的编号值；
	 * @return
	 */
	public String getMallName(Integer mallId) {
		switch (mallId) {
		case 1:
			return "独立商城";
		case 2:
			return "积分商城";
		case 3:
			return "秒杀商城";
		case 4:
			return "各地特产商城";
		case 5:
			return "99特惠商城";
		case 6:
			return "会员免单商城";
		case 7:
			return "换回会商城";
		case 10:
			return "拼团商城";
		case 8:
        	return "游戏专区";
		default:
			return "未知参数";
		}
	}

	/**
	 * 上传到某个商城，或者从某个商城下架；
     *
	 * @param mall 商城对应的值，就是定义的六个值； 
	 * @param isLaunch 是否是上架，若为否，则是下架，不填默认为true，即上架；
	 * @throws Exception 
	 */
	public String changeLaunchMall(String launchMall, Integer mall, Boolean isLaunch) throws Exception {
		if (mall > 9 || mall < 1) {//检查是否超出定义界限；
			throw new Exception("您输入的商城不在系统定义的商城范围中");
		}
		if (isLaunch == null) {//isLaunch默认值为True；
			isLaunch = true;
		}
		byte[] bs = launchMall.getBytes();//更改字段中对应位置的状态值；
		if (isLaunch) {//商品上架；
			bs[mall - 1] = '1';
		} else {//商品下架；
			bs[mall - 1] = '0';
		}
		return new String(bs);
	}

	/**
	 * 获取该商品已在哪些商城投放，返回这些商城的编号值；
	 * @return
	 */
	public List<Integer> getLaunchMalls() {
		List<Integer> list = new ArrayList<>();

		//将状态为1，即已上架的商城,返回编号；
		byte[] bytes = this.launchMall.getBytes();
		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] == '1') {
				list.add(i + 1);
			}
		}
		return list;
	}

	/**
	 * 获取该商品没有在哪些商城商家，返回这些商城的编号值；
	 * @return
	 */
	public List<Integer> getUnLaunchMalls() {
		List<Integer> list = new ArrayList<>();

		//将状态为0，即为未上架商城，返回编号；
		byte[] bytes = this.launchMall.getBytes();
		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] == '0') {
				list.add(i + 1);
			}
		}
		return list;
	}
	
	
	/**
	 * sem4141 转成 4141
	 * @param goodsOrder
	 * @return
	 */
	public static Integer getGoodsIdByGoodsOrder(String goodsOrder){
		
		return Integer.parseInt(goodsOrder.substring(3,goodsOrder.length()));
	}

	//===================================================================================================================

	//=================get/set==================
	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCoverPic() {
		return coverPic.replace("nomal/", dpi+"/");
	}

	public void setCoverPic(String coverPic) {
		this.coverPic = coverPic;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLables() {
		return lables;
	}

	public void setLables(String lables) {
		this.lables = lables;
	}

	public String getDescriptionPics() {
		return descriptionPics;
	}

	public void setDescriptionPics(String descriptionPics) {
		this.descriptionPics = descriptionPics;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getLaunchMall() {
		return launchMall;
	}

	public void setLaunchMall(String launchMall) {
		this.launchMall = launchMall;
	}

	public Integer getSnapshotId() {
		return snapshotId;
	}

	public void setSnapshotId(Integer snapshotId) {
		this.snapshotId = snapshotId;
	}
	
	    /**
     * 将以json存储的lables标签内容，转换为前台展示的字符串形式；
     *
     * @return
     */
    public String getLablesString() {
        StringBuilder text = new StringBuilder();
        try {
            JSONArray list = JSONObject.parseArray(this.lables);
            for (int i = 0; i < list.size(); i++) {
                text.append(list.getJSONObject(i).getString("lableName")).append(" ");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return text.toString();
    }

	public String getCoverPicOne() {
		JSONArray list =  JSONObject.parseArray(this.coverPic.replace("nomal/", dpi+"/"));
		return list.getJSONObject(0).getString("imgUrl");
	}

	public void setCoverPicOne(String coverPicOne) {
		this.coverPicOne = coverPicOne;
	}

	public Integer getSalesVolume() {
		return salesVolume;
	}

	public void setSalesVolume(Integer salesVolume) {
		this.salesVolume = salesVolume;
	}

	public String getDpi() {
		return dpi;
	}

	public void setDpi(String dpi) {
		this.dpi = dpi;
	}

	public String getRightsProtect() {
		return rightsProtect;
	}

	public void setRightsProtect(String rightsProtect) {
		this.rightsProtect = rightsProtect;
	}
	
	
	public Integer getCommodityTypeId() {
		return commodityTypeId;
	}

	public void setCommodityTypeId(Integer commodityTypeId) {
		this.commodityTypeId = commodityTypeId;
	}

	/**
	 * 默认取第一个子类别  
	 * @return
	 */
	public Integer getChildTypeId(){
		 JSONArray typeArray=JSONArray.parseArray(this.type);
		 
		 if(typeArray.size()>0){
			 JSONObject obj=typeArray.getJSONObject(0);
			 return obj.getInteger("childTypeId");
		 }
		return null; 
		 
	}
	
	
	/**
	 * 添加资源路径前缀
	 * @param prefix
	 * @return
	 */
	public List<String> getPrefixCoverPic(String prefix){
		
		JSONArray picArray=JSONArray.parseArray( this.descriptionPics);
		List<String> list=new ArrayList<>();
		for (int i = 0; i < picArray.size(); i++) {
			JSONObject obj = picArray.getJSONObject(i);
			list.add(prefix+obj.getString("imgUrl"));
		}
		return list;
	}
	
	public JSONObject getFirstType(){
		try {
			
			if(StringUtils.isBlank(type)||(StringUtils.isNotBlank(type)&&type.length()<5)){
				return null;
			}
			
			JSONArray jsonTypeArray=JSONArray.parseArray( this.type);
			if(jsonTypeArray.size()>0){
				return jsonTypeArray.getJSONObject(0);
			}
		} catch (Exception e) {
			return JSONObject.parseObject(this.type);
		}
		
		return null;
	}

}
