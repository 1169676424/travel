package cn.itcast.travel.domain;

import java.io.Serializable;

/**
 * 旅游线路图片实体类
 */
public class RouteImg implements Serializable {
    private Integer rgid;//商品图片id
    private Integer rid;//旅游商品id
    private String bigPic;//详情商品大图
    private String smallPic;//详情商品小图

    /**
     * 无参构造方法
     */
    public RouteImg() {
    }

    /**
     * 有参构造方法
     * @param rgid
     * @param rid
     * @param bigPic
     * @param smallPic
     */


    public RouteImg(Integer rgid, Integer rid, String bigPic, String smallPic) {
        this.rgid = rgid;
        this.rid = rid;
        this.bigPic = bigPic;
        this.smallPic = smallPic;
    }

    public Integer getRgid() {
        return rgid;
    }

    public void setRgid(Integer rgid) {
        this.rgid = rgid;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getBigPic() {
        return bigPic;
    }

    public void setBigPic(String bigPic) {
        this.bigPic = bigPic;
    }

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
    }
}
