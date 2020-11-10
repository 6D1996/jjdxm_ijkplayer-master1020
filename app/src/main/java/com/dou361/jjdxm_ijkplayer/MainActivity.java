package com.dou361.jjdxm_ijkplayer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PowerManager;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.bean.VideoijkBean;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.dou361.jjdxm_ijkplayer.utlis.MediaUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends Activity implements View.OnClickListener , MyRadioGroup.OnCheckedChangeListener {

    public VideoRequest videoRequest;
    public VideoReply videoReply;
    private PlayerView player;
    private Context mContext;
    private List<VideoijkBean> list;
    private PowerManager.WakeLock wakeLock;
    View rootView;
    private MyRadioGroup videoRatioGroup;
    private RadioButton buttonFront, buttonBack, buttonLeft, buttonRight, channelGodPerspective;
    private HashMap<String, RadioButton> channels = new HashMap<>(5);
    private Button lightControllerButton;

    private TextView replyTextView,requestTextView;

    @SuppressLint("InvalidWakeLockTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mContext = this;
        rootView = getLayoutInflater().from(this).inflate(R.layout.activity_main, null);
        setContentView(rootView);
//        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        lightControllerButton=(Button) findViewById(R.id.lightController);
        lightControllerButton.setOnClickListener(this);

        buttonFront = (RadioButton) findViewById(R.id.front_Click);
        buttonBack = (RadioButton)findViewById(R.id.back_Click);
        buttonLeft = (RadioButton)findViewById(R.id.left_Click);
        buttonRight = (RadioButton)findViewById(R.id.right_Click);
        channelGodPerspective = (RadioButton)findViewById(R.id.god_perspective_Click);
        buttonFront.setOnClickListener(this);
        buttonBack.setOnClickListener(this);
        buttonLeft.setOnClickListener(this);
        buttonRight.setOnClickListener(this);
        channelGodPerspective.setOnClickListener(this);
        channels.put("Channel_Front",buttonFront);
        channels.put("Channel_Back",buttonBack);
        channels.put("Channel_Left",buttonLeft);
        channels.put("Channel_Right",buttonRight);
        channels.put("Channel_God_Perspective", channelGodPerspective);

        replyTextView=findViewById(R.id.replyTextView);
        requestTextView=findViewById(R.id.requestTextView);


            /**虚拟按键的隐藏方法*/
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {

                //比较Activity根布局与当前布局的大小
                int heightDiff = rootView.getRootView().getHeight() - rootView.getHeight();
                if (heightDiff > 100) {
                    //大小超过100时，一般为显示虚拟键盘事件
                    rootView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                } else {
                    //大小小于100时，为不显示虚拟键盘或虚拟键盘隐藏
                    rootView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

                }
            }
        });

        /**常亮*/
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK, "liveTAG");
        wakeLock.acquire();


        videoRatioGroup = (MyRadioGroup)findViewById(R.id.radiogroup);
        videoRatioGroup.setOnCheckedChangeListener(new MyRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(MyRadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.left_Click:
                        Toast leftToast =Toast.makeText(MainActivity.this, "左", Toast.LENGTH_SHORT);
                        new ShowMyToast().showMyToast(leftToast,100);
                        break;
                    case R.id.right_Click:
                        Toast rightToast =Toast.makeText(MainActivity.this, "右", Toast.LENGTH_SHORT);
                        new ShowMyToast().showMyToast(rightToast,100);
                        break;
                    case R.id.front_Click:
                        Toast frontToast =Toast.makeText(MainActivity.this, "前", Toast.LENGTH_SHORT);
                        new ShowMyToast().showMyToast(frontToast,100);
                        break;
                    case R.id.back_Click:
                        Toast backToast =Toast.makeText(MainActivity.this, "后", Toast.LENGTH_SHORT);
                        new ShowMyToast().showMyToast(backToast,100);
                        break;
                    case R.id.god_perspective_Click:
                        Toast godPerspectiveToast =Toast.makeText(MainActivity.this, "上帝", Toast.LENGTH_SHORT);
                        new ShowMyToast().showMyToast(godPerspectiveToast,100);
                        break;
                }
            }
        });

    }

    @OnClick({R.id.front_Click, R.id.back_Click, R.id.left_Click, R.id.right_Click,R.id.god_perspective_Click,R.id.lightController})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lightController:
                {   
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                videoRequest = new VideoRequest();
                                videoRequest.setUserId("6D");
                                videoRequest.setVin("test");
                                videoRequest.setVideo_type("1");
                                videoRequest.setServicetype("1");
                                String videoRequestJson = JSON.toJSONString(videoRequest);
                                requestTextView.setText(videoRequestJson);
                                Log.d("Request",videoRequestJson );
                                OkHttpClient videoClient=new OkHttpClient();
                                Request videoRequest= new Request.Builder()
                                        .url("http://192.168.137.132:18086/appBackend/videoRequest")
                                        .post(RequestBody.create(MediaType.parse("application/json"),videoRequestJson))
                                        .build();//创造HTTP请求
                                replyTextView.setText("12");
                                //执行发送的指令
                                Response videoResponse = videoClient.newCall(videoRequest).execute();
                                replyTextView.setText("13");
                                String videoResponseString=videoResponse.body().string();
                                Log.d("Reply",videoResponseString );
                                replyTextView.setText(videoResponseString+"\n123");
                                videoReply = new VideoReply();
                                videoReply = JSON.parseObject(videoResponseString,VideoReply.class);


                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this,"請求視頻中",Toast.LENGTH_LONG).show();
                                        replyTextView.setText(videoReply.toString());
                                    }
                                });
                            }catch (Exception e){
                                e.printStackTrace();
                                Log.d("POST失敗", "onClick: "+e.toString());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this,"請求視頻！",Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }
                    }).start();


                    Toast.makeText(MainActivity.this,"測試視頻",Toast.LENGTH_SHORT).show();
                    list = new ArrayList<VideoijkBean>();
                    //有部分视频加载有问题，这个视频是有声音显示不出图像的，没有解决http://fzkt-biz.oss-cn-hangzhou.aliyuncs.com/vedio/2f58be65f43946c588ce43ea08491515.mp4
                    //这里模拟一个本地视频的播放，视频需要将testvideo文件夹的视频放到安卓设备的内置sd卡根目录中
                    String url1 = "rtmp://150.158.176.170/live/1";
                    String url2 = "rtmp://150.158.176.170/live/test";
                    VideoijkBean m1 = new VideoijkBean();
                    m1.setStream("原始视频");
                    m1.setUrl(url1);
                    VideoijkBean m2 = new VideoijkBean();
                    m2.setStream("融合视频");
                    m2.setUrl(url2);
                    list.add(m1);
                    list.add(m2);
                    player = new PlayerView(this, rootView) {
                        //                        @Override
//                        public PlayerView toggleProcessDurationOrientation() {
////                            hideSteam(getScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                            return setProcessDurationOrientation(getScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT ? PlayStateParams.PROCESS_PORTRAIT : PlayStateParams.PROCESS_LANDSCAPE);
//                        }
                        @Override
                        public PlayerView setPlaySource(List<VideoijkBean> list) {
                            return super.setPlaySource(list);
                        }
                    }
                            //.setTitle("前摄像")
                            .setProcessDurationOrientation(PlayStateParams.PROCESS_PORTRAIT)
                            .setScaleType(PlayStateParams.fillparent) //视频界面剪裁设置
                            .forbidTouch(false)
                            .hideSteam(false)
                            .hideMenu(true)
                            .hideCenterPlayer(true)
                            .setNetWorkTypeTie(false)
                            .hideRotation(true) //隐藏旋转按钮
                            .setChargeTie(true,480)//设置最长播放时间
                            .showThumbnail(new OnShowThumbnailListener() {
                                @Override
                                public void onShowThumbnail(ImageView ivThumbnail) {
//                                 加载前显示的缩略图
                                    Glide.with(mContext)
                                            .load("http://pic2.nipic.com/20090413/406638_125424003_2.jpg")
                                            .placeholder(R.color.cl_default) //加载成功之前占位图
                                            .error(R.color.cl_error)//加载错误之后的错误图
                                            .into(ivThumbnail);
                                }
                            })
                            .setPlaySource(list)
                            .startPlay();
                    }

            break;

            case R.id.back_Click:
//                /**竖屏播放器*/
//                startActivity(PlayerActivity.class);
            {
                list = new ArrayList<VideoijkBean>();
                //有部分视频加载有问题，这个视频是有声音显示不出图像的，没有解决http://fzkt-biz.oss-cn-hangzhou.aliyuncs.com/vedio/2f58be65f43946c588ce43ea08491515.mp4
                //这里模拟一个本地视频的播放，视频需要将testvideo文件夹的视频放到安卓设备的内置sd卡根目录中
                String url1 = "http://ivi.bupt.edu.cn/hls/cctv2.m3u8";
                String url2 = "rtmp://202.69.69.180:443/webcast/bshdlive-pc";
                VideoijkBean m1 = new VideoijkBean();
                m1.setStream("原始视频");
                m1.setUrl(url1);
                VideoijkBean m2 = new VideoijkBean();
                m2.setStream("融合视频");
                m2.setUrl(url2);
                list.add(m1);
                list.add(m2);
                player = new PlayerView(this, rootView) {
                    @Override
                    public PlayerView toggleProcessDurationOrientation() {
                        hideSteam(getScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        return setProcessDurationOrientation(getScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT ? PlayStateParams.PROCESS_PORTRAIT : PlayStateParams.PROCESS_LANDSCAPE);
                    }

                    @Override
                    public PlayerView setPlaySource(List<VideoijkBean> list) {
                        return super.setPlaySource(list);
                    }
                }
                        .setTitle("后摄像")
                        .setProcessDurationOrientation(PlayStateParams.PROCESS_PORTRAIT)
                        .setScaleType(PlayStateParams.fillparent)
                        .forbidTouch(false)
                        .hideSteam(false)
                        .hideRotation(true) //隐藏旋转按钮
                        .hideCenterPlayer(true)
//                        .showThumbnail(new OnShowThumbnailListener() {
//                            @Override
//                            public void onShowThumbnail(ImageView ivThumbnail) {
//                                Glide.with(mContext)
//                                        .load("http://pic2.nipic.com/20090413/406638_125424003_2.jpg")
//                                        .placeholder(R.color.cl_default)
//                                        .error(R.color.cl_error)
//                                        .into(ivThumbnail);
//                            }
//                        })
                        .setPlaySource(list)
//                        .setChargeTie(true,60)
                        .startPlay();
            }
                break;
            case R.id.left_Click:
//                /**竖屏直播播放器*/
//                startActivity(PlayerLiveActivity.class);
            {
                list = new ArrayList<VideoijkBean>();
                //有部分视频加载有问题，这个视频是有声音显示不出图像的，没有解决http://fzkt-biz.oss-cn-hangzhou.aliyuncs.com/vedio/2f58be65f43946c588ce43ea08491515.mp4
                //这里模拟一个本地视频的播放，视频需要将testvideo文件夹的视频放到安卓设备的内置sd卡根目录中
                String url1 = "http://ivi.bupt.edu.cn/hls/cctv3.m3u8";
                String url2 = "rtmp://202.69.69.180:443/webcast/bshdlive-pc";
                VideoijkBean m1 = new VideoijkBean();
                m1.setStream("原始视频");
                m1.setUrl(url1);
                VideoijkBean m2 = new VideoijkBean();
                m2.setStream("融合视频");
                m2.setUrl(url2);
                list.add(m1);
                list.add(m2);
                player = new PlayerView(this, rootView) {
                    @Override
                    public PlayerView toggleProcessDurationOrientation() {
                        hideSteam(getScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        return setProcessDurationOrientation(getScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT ? PlayStateParams.PROCESS_PORTRAIT : PlayStateParams.PROCESS_LANDSCAPE);
                    }

                    @Override
                    public PlayerView setPlaySource(List<VideoijkBean> list) {
                        return super.setPlaySource(list);
                    }
                }
                        .setTitle("左摄像")
                        .setProcessDurationOrientation(PlayStateParams.PROCESS_PORTRAIT)
                        .setScaleType(PlayStateParams.fillparent)
                        .forbidTouch(false)
                        .hideSteam(false)
                        .hideRotation(true) //隐藏旋转按钮
                        .hideCenterPlayer(true)
//                        .showThumbnail(new OnShowThumbnailListener() {
//                            @Override
//                            public void onShowThumbnail(ImageView ivThumbnail) {
//                                Glide.with(mContext)
//                                        .load("http://pic2.nipic.com/20090413/406638_125424003_2.jpg")
//                                        .placeholder(R.color.cl_default)
//                                        .error(R.color.cl_error)
//                                        .into(ivThumbnail);
//                            }
//                        })
                        .setPlaySource(list)
//                        .setChargeTie(true,60)
                        .startPlay();
            }
                break;
            case R.id.right_Click:
//                /**ijkplayer原生的播放器*/
//                startActivity(OriginPlayerActivity.class);
            {
                list = new ArrayList<VideoijkBean>();
                //有部分视频加载有问题，这个视频是有声音显示不出图像的，没有解决http://fzkt-biz.oss-cn-hangzhou.aliyuncs.com/vedio/2f58be65f43946c588ce43ea08491515.mp4
                //这里模拟一个本地视频的播放，视频需要将testvideo文件夹的视频放到安卓设备的内置sd卡根目录中
                String url1 = "http://ivi.bupt.edu.cn/hls/cctv4.m3u8";
                String url2 = "rtmp://202.69.69.180:443/webcast/bshdlive-pc";
                VideoijkBean m1 = new VideoijkBean();
                m1.setStream("原始视频");
                m1.setUrl(url1);
                VideoijkBean m2 = new VideoijkBean();
                m2.setStream("融合视频");
                m2.setUrl(url2);
                list.add(m1);
                list.add(m2);
                player = new PlayerView(this, rootView) {
                    @Override
                    public PlayerView toggleProcessDurationOrientation() {
                        hideSteam(getScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        return setProcessDurationOrientation(getScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT ? PlayStateParams.PROCESS_PORTRAIT : PlayStateParams.PROCESS_LANDSCAPE);
                    }

                    @Override
                    public PlayerView setPlaySource(List<VideoijkBean> list) {
                        return super.setPlaySource(list);
                    }
                }
                        .setTitle("右摄像")
                        .setProcessDurationOrientation(PlayStateParams.PROCESS_PORTRAIT)
                        .setScaleType(PlayStateParams.fillparent)
                        .forbidTouch(false)
                        .hideSteam(false)
                        .hideRotation(true) //隐藏旋转按钮
                        .hideCenterPlayer(true)
//                        .showThumbnail(new OnShowThumbnailListener() {
//                            @Override
//                            public void onShowThumbnail(ImageView ivThumbnail) {
//                                Glide.with(mContext)
//                                        .load("http://pic2.nipic.com/20090413/406638_125424003_2.jpg")
//                                        .placeholder(R.color.cl_default)
//                                        .error(R.color.cl_error)
//                                        .into(ivThumbnail);
//                            }
//                        })
                        .setPlaySource(list)
//                        .setChargeTie(true,60)
                        .startPlay();
            }
                break;
            case R.id.god_perspective_Click:
//                /**半屏播放器*/
//                startActivity(HPlayerActivity.class);
            {
                list = new ArrayList<VideoijkBean>();
                //有部分视频加载有问题，这个视频是有声音显示不出图像的，没有解决http://fzkt-biz.oss-cn-hangzhou.aliyuncs.com/vedio/2f58be65f43946c588ce43ea08491515.mp4
                //这里模拟一个本地视频的播放，视频需要将testvideo文件夹的视频放到安卓设备的内置sd卡根目录中
                String url1 = "http://ivi.bupt.edu.cn/hls/cctv7.m3u8";
                String url2 = "rtmp://202.69.69.180:443/" +
                        "webcast/bshdlive-pc";
                VideoijkBean m1 = new VideoijkBean();
                m1.setStream("原始视频");
                m1.setUrl(url1);
                VideoijkBean m2 = new VideoijkBean();
                m2.setStream("融合视频");
                m2.setUrl(url2);
                list.add(m1);
                list.add(m2);
                player = new PlayerView(this, rootView) {
                    @Override
                    public PlayerView toggleProcessDurationOrientation() {
                        hideSteam(getScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                        return setProcessDurationOrientation(getScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT ? PlayStateParams.PROCESS_PORTRAIT : PlayStateParams.PROCESS_LANDSCAPE);
                    }

                    @Override
                    public PlayerView setPlaySource(List<VideoijkBean> list) {
                        return super.setPlaySource(list);
                    }
                }
                        .setTitle("上帝视角")
                        .setProcessDurationOrientation(PlayStateParams.PROCESS_PORTRAIT)
                        .setScaleType(PlayStateParams.fillparent)
                        .forbidTouch(false)
                        .hideSteam(false)
                        .hideMenu(false)
                        .hideRotation(true) //隐藏旋转按钮
                        .hideCenterPlayer(true)
//                        .showThumbnail(new OnShowThumbnailListener() {
//                            @Override
//                            public void onShowThumbnail(ImageView ivThumbnail) {
//                                Glide.with(mContext)
//                                        .load("http://pic2.nipic.com/20090413/406638_125424003_2.jpg")
//                                        .placeholder(R.color.cl_default)
//                                        .error(R.color.cl_error)
//                                        .into(ivThumbnail);
//                            }
//                        })
                        .setPlaySource(list)
//                        .setChargeTie(true,60)
                        .startPlay();
            }
            break;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
        /**demo的内容，恢复系统其它媒体的状态*/
        MediaUtils.muteAudioFocus(mContext, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
        /**demo的内容，暂停系统其它媒体的状态*/
        MediaUtils.muteAudioFocus(mContext, false);
        /**demo的内容，激活设备常亮状态*/
        if (wakeLock != null) {
            wakeLock.acquire();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        }
        super.onBackPressed();
        /**demo的内容，恢复设备亮度状态*/
        if (wakeLock != null) {
            wakeLock.release();
        }
    }

    @Override
    public void onCheckedChanged(MyRadioGroup group, int checkedId) {

    }

    public void postVideoRequest(){}

    public void playVideo(){}
}
