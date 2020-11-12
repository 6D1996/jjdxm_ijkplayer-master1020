package com.dou361.jjdxm_ijkplayer;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.CountDownTimer;
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

    public String hostURL="http://10.6.206.20:30549/appBackend/";
    public CountDownTimer countDownTimer;
    public VideoRequest videoRequest;
    public VideoReply videoReply,videoReply2;
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
                videoReply=new VideoReply("原始");
                videoReply2=new VideoReply("融合");
                replyTextView.setText("当前选择"+checkedId);
                switch (checkedId){
                    case R.id.left_Click:
                        try2play(3);
                        break;
                    case R.id.right_Click:
                        try2play(4);
                        break;
                    case R.id.front_Click:
                        try2play(0);
                        break;
                    case R.id.back_Click:
                        try2play(2);
                        break;
                    case R.id.god_perspective_Click:
                        try2play(5);
                        break;
                    default:
                        Toast defualt =Toast.makeText(MainActivity.this, "上帝", Toast.LENGTH_SHORT);
                        new ShowMyToast().showMyToast(defualt,100);
                        break;
                }
            }
        });

    }

    private void try2play(final int videoNum) {
        videoReply.initialVideoReply();
        videoReply2.initialVideoReply();
        if (videoNum!=0){
        countDownTimer=new CountDownTimer(10000,1000) {
            int i=0;
            @Override
            public void onTick(long millisUntilFinished) {
                i=i+1000;
                replyTextView.setText("执行第"+i+"次请求："+videoReply.toString());
                if (videoReply.getCode().equals("InitialString")){
                    videoReply = postVideoRequest(videoNum);
                    videoReply.setCode("00"+i);
                }
                else if(videoReply.getCode().equals("003000"))//请求成功播放视频取消轮询
                {playVideo(videoNum);
                    cancel();}
                else videoReply.setCode("00"+i);
            }

            @Override
            public void onFinish() {
                replyTextView.setText("请求超时!");
            }
        }.start();}
        else {//播放前视角视频时有四种情况
            countDownTimer=new CountDownTimer(10000,1000) {
                int i=0;
                @Override
                public void onTick(long millisUntilFinished) {
                    i=i+1000;
                    replyTextView.setText("执行第"+i+"次请求："+videoReply.toString());
                    if (videoReply.getCode().equals("InitialString")){//原始視頻為初始數據
                        if (videoReply2.getCode().equals("InitialString")){//均為初始數據
                        videoReply = postVideoRequest(videoNum);
                        videoReply2=postVideoRequest(videoNum+5);//兩個視頻都請求
                        videoReply.setCode("00"+i);
                        }else {//融合視頻有數據
                            videoReply=postVideoRequest(videoNum);//繼續請求原始視頻
                        }
                    }
                    else if(videoReply.getCode().equals("003000")){//原始視頻已請求
                        videoReply2.setCode("003000");
                            if (videoReply2.getCode().equals("InitialString")){//融合視頻為初始數據
                                videoReply2=postVideoRequest(videoNum+5);
                                videoReply2.setCode("00"+i);
                            }else {//全部有數據
                                onFinish();//對code的四種狀態進行判斷
                                cancel();
                            }
                    }
                    else videoReply.setCode("00"+i);
                }

                @Override
                public void onFinish() {
                    if(videoReply.getCode().equals("003000")){//原始視頻可用
                        if (videoReply2.getCode().equals("003000")){
                            playVideo(0);//最理想情況，同時可播倆視頻
                        }else{
                            playVideo(1);//融合視頻不可用，播放原始視頻
                        }
                    }
                    else{//原始視頻不可用
                        if (videoReply2.getCode().equals("003000")){
                            playVideo(6);//只播融合視頻
                        }else{
                            replyTextView.setText("WRONG!");
                        }
                    }
                }
            }.start();
        }
    }

    public VideoReply postVideoRequest(int videoNum){
        videoRequest = new VideoRequest();
        videoRequest.setUserId("6D");
        videoRequest.setVin("test");
        videoRequest.setVideo_type(Integer.toString(videoNum));
        videoRequest.setServicetype("1");
        final String videoRequestJson = JSON.toJSONString(videoRequest);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    requestTextView.setText(videoRequestJson);
                    OkHttpClient videoClient=new OkHttpClient();
                    Request videoRequest= new Request.Builder()
                            .url(hostURL+"videoRequest")
                            .post(RequestBody.create(MediaType.parse("application/json"),videoRequestJson))
                            .build();//创造HTTP请求
                    //执行发送的指令
                    Response videoResponse = videoClient.newCall(videoRequest).execute();
                    String videoResponseString=videoResponse.body().string();
                    Log.d("Reply",videoResponseString);
                    replyTextView.setText(videoResponseString);
                    videoReply = JSON.parseObject(videoResponseString,VideoReply.class);
                }catch (Exception e){
                    e.printStackTrace();
                    Log.d("POST失敗", "onClick: "+e.toString());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,"請求視頻失败！",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }).start();
        return videoReply;
    }

    public void playVideoUrl( String url){
      Toast.makeText(MainActivity.this, "請求成功！", Toast.LENGTH_SHORT).show();
      player = new PlayerView(MainActivity.this, rootView)
              .setPlaySource(url)
              //.setTitle("前摄像")
              .setProcessDurationOrientation(PlayStateParams.PROCESS_PORTRAIT)
              .setScaleType(PlayStateParams.fillparent) //视频界面剪裁设置
              .forbidTouch(false)
              .hideSteam(true)
              .hideMenu(true)
              .hideCenterPlayer(false)
              .setNetWorkTypeTie(false)
              .hideRotation(true) //隐藏旋转按钮
              .setChargeTie(true, 10)//设置最长播放时间
              .showThumbnail(new OnShowThumbnailListener() {
                  @Override
                  public void onShowThumbnail(ImageView ivThumbnail) {
//                                 加载前显示的缩略图
                      Glide.with(mContext)
                              .load(R.mipmap.pic_before_video)//"http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg"
                              .placeholder(R.mipmap.pic_before_video) //加载成功之前占位图
                              .error(R.color.cl_error)//加载错误之后的错误图
                              .into(ivThumbnail);
                  }
              })
              .setPlaySource(list)
              .startPlay();

    }

    public void playVideo(int videoNum){

        switch (videoNum){
            case 0:
                //前视角原始视频与融合视频在一个按钮，通过视频窗口内部按钮切换
                Toast.makeText(MainActivity.this,"請求成功！",Toast.LENGTH_SHORT).show();
                list = new ArrayList<VideoijkBean>();
                //有部分视频加载有问题，这个视频是有声音显示不出图像的，没有解决http://fzkt-biz.oss-cn-hangzhou.aliyuncs.com/vedio/2f58be65f43946c588ce43ea08491515.mp4
                //这里模拟一个本地视频的播放，视频需要将testvideo文件夹的视频放到安卓设备的内置sd卡根目录中
                String url1 = "http://ivi.bupt.edu.cn/hls/cctv1.m3u8";//"rtmp://150.158.176.170/live/1";
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
                                        .load(R.mipmap.pic_before_video)//"http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg"
                                        .placeholder(R.mipmap.pic_before_video) //加载成功之前占位图
                                        .error(R.color.cl_error)//加载错误之后的错误图
                                        .into(ivThumbnail);
                            }
                        })
                        .setPlaySource(list)
                        .startPlay();
                break;
            case 1:
                //只播原始視頻
                playVideoUrl("http://ivi.bupt.edu.cn/hls/cctv1.m3u8");
                break;
            case 2:
                //后视角
                playVideoUrl("http://ivi.bupt.edu.cn/hls/cctv2.m3u8");
                break;
            case 3:
                //左视角
                playVideoUrl("http://ivi.bupt.edu.cn/hls/cctv3.m3u8");
                break;
            case 4:
                //右视角
                playVideoUrl("http://ivi.bupt.edu.cn/hls/cctv4.m3u8");
                break;
            case 5:
                //上帝视角
                playVideoUrl("http://ivi.bupt.edu.cn/hls/cctv5.m3u8");
                break;
            case 6:
                //只有融合視頻
                playVideoUrl("http://ivi.bupt.edu.cn/hls/cctv6.m3u8");
                break;

            default:
                break;
        }
    }


    @OnClick({R.id.lightController})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lightController:
                {   
                    requestTextView.setText("請求開燈/關燈");
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
}
