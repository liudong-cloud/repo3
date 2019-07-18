package com.itheima.domain;

import com.itheima.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class Orders {
    private String id;         //无意义、主键uuid
    private String orderNum;   //订单编号 不为空 唯一

    @DateTimeFormat(pattern = "yyyy-mm-dd HH:MM")
    private Date orderTime;    //下单时间
    private String orderTimeStr;
    private int orderStatus;   //订单状态(0 未支付 1 已支付)
    private String orderStatusStr;
    private int peopleCount;   //出行人数
    private Product product;   //产品信息
    private List<Traveller> travellers; //旅客信息
    private Member member;     //会员(联系人）
    private Integer payType;   //支付方式(0 支付宝 1 微信 2其它)
    private String payTypeStr;
    private String orderDesc;     //订单描述(其它信息）

    public String getOrderStatusStr() {
        if (orderStatus==0){
            orderStatusStr="未支付";
        }if (orderStatus==1){
            orderStatusStr="已支付";
        }
        return orderStatusStr;
    }

    public void setOrderStatusStr(String orderStatusStr) {
        this.orderStatusStr = orderStatusStr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNum() {

        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderTimeStr() {
        if(orderTime!=null){
            orderTimeStr= DateUtils.date2string(orderTime,"yyyy-mm-dd HH:MM");
        }
        return orderTimeStr;
    }

    public void setOrderTimeStr(String orderTimeStr) {
        this.orderTimeStr = orderTimeStr;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Traveller> getTravellers() {
        return travellers;
    }

    public void setTravellers(List<Traveller> travellers) {
        this.travellers = travellers;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public String getPayTypeStr() {
        //支付方式(0 支付宝 1 微信 2其它)
        if (payType!=null){
            if (payType==0){
               payTypeStr="支付宝";
            }else if(payType==1){
                payTypeStr="微信";
            }else {
                payTypeStr="其它";
            }
        }
        return payTypeStr;
    }

    public void setPayTypeStr(String payTypeStr) {
        this.payTypeStr = payTypeStr;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }
}
