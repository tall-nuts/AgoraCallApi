package com.basetools.model;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import java.io.Serializable;

/**
 * @author gaopengfei on 2019/12/17.
 */
public class Gift implements Serializable {
    private String giftId;//礼物编号
    private String giftName;//礼物名字
    private String giftDesc;//描述
    private int giftType;//类型	目前只有一类
    private int price;//价格
    private String giftUrl;//图片地址
    private String english;//英文名
    private String taditional;//繁体名
    private String svgaUrl;//svgaUrl

    /** 用于逻辑控制，非返回字段 */
    private boolean selected;

    public Gift() {
    }

    public Gift(String giftId) {
        this.giftId = giftId;
    }

    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getGiftDesc() {
        return giftDesc;
    }

    public void setGiftDesc(String giftDesc) {
        this.giftDesc = giftDesc;
    }

    public int getGiftType() {
        return giftType;
    }

    public void setGiftType(int giftType) {
        this.giftType = giftType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getGiftUrl() {
        return giftUrl;
    }

    public void setGiftUrl(String giftUrl) {
        this.giftUrl = giftUrl;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getTaditional() {
        return taditional;
    }

    public void setTaditional(String taditional) {
        this.taditional = taditional;
    }

    public String getSvgaUrl() {
        return svgaUrl;
    }

    public void setSvgaUrl(String svgaUrl) {
        this.svgaUrl = svgaUrl;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Gift && !TextUtils.isEmpty(giftId)) {
            final Gift gift = (Gift) obj;
            return giftId.equals(gift.getGiftId());
        }
        return super.equals(obj);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
