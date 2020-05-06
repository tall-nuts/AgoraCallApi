package com.basetools.model;

public class Platform {

    // 渠道号
    private long fid;
    // 标注底包
    private int baseProduct;
    // 产品号
    private int product;
    // 包名
    private String packageName;
    // 版本号
    private String version;
    // 版本编码
    private int versionCode;
    // 发版时间
    private String release;

    // 设备号 Android传androidId,ios传idfa
    private String pid;
    // imei
    private String imsi;
    // 操作系统
    private int platform = 3;
    // 系统版本
    private String systemVersion;
    // 屏宽
    private int w;
    // 屏高
    private int h;
    // 网络
    private int netType;
    // 国家或地区
    private String country;
    // 语言
    private String language;
    // 手机品牌型号
    private String phonetype;
    // 网络运营商
    private String operator;
    // 安全联盟oaid
    private String oaid;

    public Platform() {
    }

    public long getFid() {
        return fid;
    }

    public void setFid(long fid) {
        this.fid = fid;
    }

    public int getBaseProduct() {
        return baseProduct;
    }

    public void setBaseProduct(int baseProduct) {
        this.baseProduct = baseProduct;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getNetType() {
        return netType;
    }

    public void setNetType(int netType) {
        this.netType = netType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPhonetype() {
        return phonetype;
    }

    public void setPhonetype(String phonetype) {
        this.phonetype = phonetype;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOaid() {
        return oaid;
    }

    public void setOaid(String oaid) {
        this.oaid = oaid;
    }

    @Override
    public String toString() {
        return "Platform{" +
                "fid=" + fid +
                ", baseProduct=" + baseProduct +
                ", product=" + product +
                ", packageName='" + packageName + '\'' +
                ", version='" + version + '\'' +
                ", versionCode=" + versionCode +
                ", release='" + release + '\'' +
                ", pid='" + pid + '\'' +
                ", imsi='" + imsi + '\'' +
                ", platform=" + platform +
                ", systemVersion='" + systemVersion + '\'' +
                ", w=" + w +
                ", h=" + h +
                ", netType=" + netType +
                ", country='" + country + '\'' +
                ", language='" + language + '\'' +
                ", phonetype='" + phonetype + '\'' +
                ", operator='" + operator + '\'' +
                ", oaid='" + oaid + '\'' +
                '}';
    }
}
