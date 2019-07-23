package com.wangchao.meta;

import java.math.BigDecimal;

/**
 * @author wangchao
 * @date 2019/2/20
 */

public class Content {


    private Integer id;
    private String title;
    private String cAbstract;
    private String text;
    private BigDecimal price;
    private String imgPath;
    /**
     * 发布者（卖家）的用户Id
     */
    private Integer sellerId;
    /**
     * 逻辑删除字段，默认0有效，
     */
    private Boolean beDeleted;
    /**
     * 内容是否被购买，0表示未被购买(默认)，大于0表示已被购买
     * 为防止数据库查出null，一般不建议使用基本数据类型，
     * 但是mybatis映射，处理int基本数据类型时，默认将null处理为0
     * 该字段，表中不存在，通过联合orders表，赋值
     *
     */
    private int bePurchased;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getcAbstract() {
        return cAbstract;
    }

    public void setcAbstract(String cAbstract) {
        this.cAbstract = cAbstract;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Boolean getBeDeleted() {
        return beDeleted;
    }

    public void setBeDeleted(Boolean beDeleted) {
        this.beDeleted = beDeleted;
    }

    public int getBePurchased() {
        return bePurchased;
    }

    public void setBePurchased(int bePurchased) {
        this.bePurchased = bePurchased;
    }
}
