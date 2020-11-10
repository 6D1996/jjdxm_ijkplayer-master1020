package com.dou361.jjdxm_ijkplayer;

public class VideoRequest {
    private String requestId,userId,vin,video_type, servicetype;

    public VideoRequest() {
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getVideo_type() {
        return video_type;
    }

    public void setVideo_type(String video_type) {
        this.video_type = video_type;
    }

    public String getServicetype() {
        return servicetype;
    }

    public void setServicetype(String servicetype) {
        this.servicetype = servicetype;
    }

    @Override
    public String toString() {
        return "VideoRequest{" +
                "requestId='" + requestId + '\'' +
                ", userId='" + userId + '\'' +
                ", vin='" + vin + '\'' +
                ", video_type='" + video_type + '\'' +
                ", serviceType='" + servicetype + '\'' +
                '}';
    }
}
