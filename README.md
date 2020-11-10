# [jjdxm_ijkplayer][project] #

### Copyright notice ###

我在网上写的文章、项目都可以转载，但请注明出处，这是我唯一的要求。当然纯我个人原创的成果被转载了，不注明出处也是没有关系的，但是由我转载或者借鉴了别人的成果的请注明他人的出处，算是对前辈们的一种尊重吧！

虽然我支持写禁止转载的作者，这是他们的成果，他们有这个权利，但我不觉得强行扭转用户习惯会有一个很好的结果。纯属个人的观点，没有特别的意思。可能我是一个版权意识很差的人吧，所以以前用了前辈们的文章、项目有很多都没有注明出处，实在是抱歉！有想起或看到的我都会逐一补回去。

从一开始，就没指望从我写的文章、项目上获得什么回报，一方面是为了自己以后能够快速的回忆起曾经做过的事情，避免重复造轮子做无意义的事，另一方面是为了锻炼下写文档、文字组织的能力和经验。如果在方便自己的同时，对你们也有很大帮助，自然是求之不得的事了。要是有人转载或使用了我的东西觉得有帮助想要打赏给我，多少都行哈，心里却很开心，被人认可总归是件令人愉悦的事情。

站在了前辈们的肩膀上，才能走得更远视野更广。前辈们写的文章、项目给我带来了很多知识和帮助，我没有理由不去努力，没有理由不让自己成长的更好。写出好的东西于人于己都是好的，但是由于本人自身视野和能力水平有限，错误或者不好的望多多指点交流。

项目中如有不同程度的参考借鉴前辈们的文章、项目会在下面注明出处的，纯属为了个人以后开发工作或者文档能力的方便。如有侵犯到您的合法权益，对您造成了困惑，请联系协商解决，望多多谅解哈！若您也有共同的兴趣交流技术上的问题加入交流群QQ： 548545202

感谢作者[tcking][author]、[Bilibili][author1]，本项目借鉴了[GiraffePlayer][url]项目，项目一开始的灵感来源于[GiraffePlayer][url]项目，后期做纯粹做了视频播放器的界面的定制，基于[ijkplayer][url1]项目进行的播放器界面UI封装。

## Introduction ##

当前项目是基于[ijkplayer][url1]项目进行的播放器界面UI封装。
是一个适用于 Android 的 RTMP 直播播放 SDK，可高度定制化和二次开发。特色是同时支持 H.264 软编／硬编和 AAC 软编／硬编。主要是支持RIMP、HLS、MP4、M4A等视频格式的播放。

## Features ##

1. 基于ijkplayer封装的视频播放器界面,支持 RTMP , HLS (http & https) , MP4,M4A 等；
2. 可根据需求去定制部分界面样式；
3. 常用的手势操作左边上下亮度，右边上下声音，左右滑动播放进度调整；
4. 支持多种分辨率流的切换播放；
5. 播放出错尝试重连；
6. 界面裁剪显示样式；

## Screenshots ##

<img src="https://raw.githubusercontent.com/jjdxmashl/jjdxm_ijkplayer/master/screenshots/icon01.gif" width="300"> 
<img src="https://raw.githubusercontent.com/jjdxmashl/jjdxm_ijkplayer/master/screenshots/icon02.gif" width="300"> 
<img src="https://raw.githubusercontent.com/jjdxmashl/jjdxm_ijkplayer/master/screenshots/icon03.gif" width="300"> 
<img src="https://raw.githubusercontent.com/jjdxmashl/jjdxm_ijkplayer/master/screenshots/icon04.gif" width="300"> 
<img src="https://raw.githubusercontent.com/jjdxmashl/jjdxm_ijkplayer/master/screenshots/icon01.png" width="300"> 
<img src="https://raw.githubusercontent.com/jjdxmashl/jjdxm_ijkplayer/master/screenshots/icon02.png" width="300"> 
 
## Download ##

[demo apk下载][downapk]

Download or grab via Maven:

	<dependency>
	  <groupId>com.dou361.ijkplayer</groupId>
	  <artifactId>jjdxm-ijkplayer</artifactId>
	  <version>x.x.x</version>
	</dependency>

or Gradle:

	compile 'com.dou361.ijkplayer:jjdxm-ijkplayer:x.x.x'


历史版本：

    compile 'com.dou361.ijkplayer:jjdxm-ijkplayer:1.0.6'
	compile 'com.dou361.ijkplayer:jjdxm-ijkplayer:1.0.5'
	compile 'com.dou361.ijkplayer:jjdxm-ijkplayer:1.0.4'
	compile 'com.dou361.ijkplayer:jjdxm-ijkplayer:1.0.3'
	compile 'com.dou361.ijkplayer:jjdxm-ijkplayer:1.0.2'
	compile 'com.dou361.ijkplayer:jjdxm-ijkplayer:1.0.1'
	compile 'com.dou361.ijkplayer:jjdxm-ijkplayer:1.0.0'


jjdxm-ijkplayer requires at minimum Java 9 or Android 2.3.

[架包的打包引用以及冲突解决][jaraar]

## Proguard ##

根据你的混淆器配置和使用，您可能需要在你的proguard文件内配置以下内容：

	-keep com.dou361.ijkplayer.** {
    *;
	}


[AndroidStudio代码混淆注意的问题][minify]

## Get Started ##

#### step1: ####
依赖本项目类库

该项目是基于ijkplayer项目进行的视频UI的二次封装，目前只是默认在：

	compile 'com.dou361.ijkplayer:jjdxm-ijkplayer:1.0.5' 

中加入了以下依赖：

	compile 'tv.danmaku.ijk.media:ijkplayer-java:0.6.0'
    compile 'tv.danmaku.ijk.media:ijkplayer-armv7a:0.6.0'

如果你的项目中已经有依赖了v4或者v7包并且使用的版本不一样可能会造成冲突，可以类似下面的方式进行引入依赖

	compile('com.dou361.ijkplayer:jjdxm-ijkplayer:1.0.5') {
	    exclude group: 'com.android.support', module: 'appcompat-v7'
	}


如果要支持多种ABI类型的机型，可以根据需要添加以下依赖：

	# required, enough for most devices.
    compile 'tv.danmaku.ijk.media:ijkplayer-java:0.6.0'
    compile 'tv.danmaku.ijk.media:ijkplayer-armv7a:0.6.0'

    # Other ABIs: optional
    compile 'tv.danmaku.ijk.media:ijkplayer-armv5:0.6.0'
    compile 'tv.danmaku.ijk.media:ijkplayer-arm64:0.6.0'  //最小版本21
    compile 'tv.danmaku.ijk.media:ijkplayer-x86:0.6.0'
    compile 'tv.danmaku.ijk.media:ijkplayer-x86_64:0.6.0'  //最小版本21

ijkplayer打包不同的ABI后，应该是对EXO支持才把部分ABI的最小版本设置为21，考虑到部分机型需要64的支持，然而项目最小版本又不行改到21，当前在项目中加入x86、x86_64、arm64文件，以下是提供最小版本为9的compile依赖出来

	//对应ijkplayer的  compile 'tv.danmaku.ijk.media:ijkplayer-armv7a:0.6.0'
	compile 'com.dou361.ijkplayer-armv7a:jjdxm-ijkplayer-armv7a:1.0.0'  
	//对应ijkplayer的  compile 'tv.danmaku.ijk.media:ijkplayer-armv5:0.6.0'
	compile 'com.dou361.ijkplayer-armv5:jjdxm-ijkplayer-armv5:1.0.0' 
	//对应ijkplayer的  compile 'tv.danmaku.ijk.media:ijkplayer-arm64:0.6.0'
	compile 'com.dou361.ijkplayer-arm64:jjdxm-ijkplayer-arm64:1.0.0' 
	//对应ijkplayer的  compile 'tv.danmaku.ijk.media:ijkplayer-x86:0.6.0'
	compile 'com.dou361.ijkplayer-x86:jjdxm-ijkplayer-x86:1.0.0' 
	//对应ijkplayer的  compile 'tv.danmaku.ijk.media:ijkplayer-x86_64:0.6.0'
	compile 'com.dou361.ijkplayer-x86_64:jjdxm-ijkplayer-x86_64:1.0.0'


demo中原来的jniLibs目录下的文件，已经移除，都是使用上面的依赖方式，如果网络环境差compile不下来，可以到项目的release目录中去下载 

#### step2: ####

多种分辨率流切换的案例，例如播放器的标清、高清、超清、720P等。

#### 1.简单的播放器实现 ####

    rootView = getLayoutInflater().from(this).inflate(R.layout.simple_player_view_player, null);
	setContentView(rootView);
	String url = "http://9890.vod.myqcloud.com/9890_9c1fa3e2aea011e59fc841df10c92278.f20.mp4";
    player = new PlayerView(this,rootView)
            .setTitle("什么")
            .setScaleType(PlayStateParams.fitparent)
            .hideMenu(true)
            .forbidTouch(false)
            .showThumbnail(new OnShowThumbnailListener() {
                @Override
                public void onShowThumbnail(ImageView ivThumbnail) {
                    Glide.with(mContext)
                            .load("http://pic2.nipic.com/20090413/406638_125424003_2.jpg")
                            .placeholder(R.color.cl_default)
                            .error(R.color.cl_error)
                            .into(ivThumbnail);
                }
            })
            .setPlaySource(url)
            .startPlay();

#### 2.多种不同的分辨率流的播放器实现 ####
在布局中使用simple_player_view_player.xml布局

	<include
        layout="@layout/simple_player_view_player"
        android:layout_width="match_parent"
        android:layout_height="180dp"/>

代码中创建一个播放器对象

	/**播放资源*/
	ist<VideoijkBean> list = new ArrayList<VideoijkBean>();
	String url1 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
    String url2 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f30.mp4";
    VideoijkBean m1 = new VideoijkBean();
    m1.setStream("标清");
    m1.setUrl(url1);
    VideoijkBean m2 = new VideoijkBean();
    m2.setStream("高清");
    m2.setUrl(url2);
    list.add(m1);
    list.add(m2);
	/**播放器*/
	rootView = getLayoutInflater().from(this).inflate(你的布局, null);
	setContentView(rootView);
	player = new PlayerView(this,rootView)
                .setTitle("什么")
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
						/**加载前显示的缩略图*/
                        Glide.with(mContext)
                                .load("http://pic2.nipic.com/20090413/406638_125424003_2.jpg")
                                .placeholder(R.color.cl_default)
                                .error(R.color.cl_error)
                                .into(ivThumbnail);
                    }
                })
                .setPlaySource(list)
                .startPlay();

#### step3: ####

配置生命周期方法,为了让播放器同步Activity生命周期，建议以下方法都去配置，注释的代码，主要作用是播放时屏幕常亮和暂停其它媒体的播放。

	 @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
        /**demo的内容，恢复系统其它媒体的状态*/
        //MediaUtils.muteAudioFocus(mContext, true);
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
        //if (wakeLock != null) {
        //    wakeLock.acquire();
        //}
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
        //if (wakeLock != null) {
        //    wakeLock.release();
        //}
    }


## More Actions ##

1.视频界面裁剪设置，可通过方法setScaleType(int type)去设置

	1. PlayStateParams.fitParent:可能会剪裁,保持原视频的大小，显示在中心,当原视频的大小超过view的大小超过部分裁剪处理
    2. PlayStateParams.fillParent:可能会剪裁,等比例放大视频，直到填满View为止,超过View的部分作裁剪处理
    3. PlayStateParams.wrapcontent:将视频的内容完整居中显示，如果视频大于view,则按比例缩视频直到完全显示在view中
    4. PlayStateParams.fitXY:不剪裁,非等比例拉伸画面填满整个View
    5. PlayStateParams.f16_9:不剪裁,非等比例拉伸画面到16:9,并完全显示在View中
    6. PlayStateParams.f4_3:不剪裁,非等比例拉伸画面到4:3,并完全显示在View中

2.自定义视频界面，可以复制以下布局内容到自己的项目中，注意已有的id不能修改或删除，可以增加view，可以对以下布局内容调整显示位置或者自行隐藏

	<?xml version="1.0" encoding="utf-8"?>
	<RelativeLayout
	    android:id="@+id/app_video_box"
	    xmlns:android="http://schemas.android.com/apk/res/android"
	    android:layout_width="match_parent"
	    android:layout_height="match_parent"
	    android:background="@android:color/black"
	    android:orientation="vertical">
	
	
	    <com.dou361.ijkplayer.widget.IjkVideoView
	        android:id="@+id/video_view"
	        android:layout_width="match_parent"
	        android:layout_height="match_parent"/>
	
	    <LinearLayout
	        android:id="@+id/ll_bg"
	        android:layout_width="match_parent"
	        android:layout_height="match_parent"
	        android:background="@android:color/black"
	        android:orientation="vertical">
	
	        <!-- 封面显示-->
	        <ImageView
	            android:id="@+id/iv_trumb"
	            android:layout_width="match_parent"
	            android:layout_height="match_parent"
	            android:scaleType="fitXY"
	            android:visibility="visible"/>
	    </LinearLayout>
	
	    <!--重新播放-->
	    <LinearLayout
	        android:id="@+id/app_video_replay"
	        android:layout_width="match_parent"
	        android:layout_height="match_parent"
	        android:background="#33000000"
	        android:gravity="center"
	        android:orientation="vertical"
	        android:visibility="gone">
	        <!-- 播放状态-->
	        <TextView
	            android:id="@+id/app_video_status_text"
	            android:layout_width="wrap_content"
	            android:layout_height="wrap_content"
	            android:text="@string/small_problem"
	            android:textColor="@android:color/white"
	            android:textSize="14dp"/>
	
	        <ImageView
	            android:id="@+id/app_video_replay_icon"
	            android:layout_width="wrap_content"
	            android:layout_height="wrap_content"
	            android:layout_centerHorizontal="true"
	            android:layout_marginTop="8dp"
	            android:src="@drawable/simple_player_circle_outline_white_36dp"/>
	    </LinearLayout>
	    <!-- 网络提示-->
	    <LinearLayout
	        android:id="@+id/app_video_netTie"
	        android:layout_width="match_parent"
	        android:layout_height="match_parent"
	        android:background="#33000000"
	        android:gravity="center"
	        android:orientation="vertical"
	        android:visibility="gone">
	
	        <TextView
	            android:layout_width="wrap_content"
	            android:layout_height="wrap_content"
	            android:layout_marginBottom="8dp"
	            android:gravity="center"
	            android:paddingLeft="8dp"
	            android:paddingRight="8dp"
	            android:text="您正在使用移动网络播放视频\n可能产生较高流量费用"
	            android:textColor="@android:color/white"/>
	
	        <TextView
	            android:id="@+id/app_video_netTie_icon"
	            android:layout_width="wrap_content"
	            android:layout_height="wrap_content"
	            android:background="@drawable/simple_player_btn"
	            android:gravity="center"
	            android:paddingLeft="8dp"
	            android:paddingRight="8dp"
	            android:text="继续"
	            android:textColor="@android:color/white"/>
	    </LinearLayout>
	
	    <!--加载中-->
	    <LinearLayout
	        android:id="@+id/app_video_loading"
	        android:layout_width="match_parent"
	        android:layout_height="match_parent"
	        android:gravity="center"
	        android:orientation="vertical"
	        android:visibility="gone">
	
	        <ProgressBar
	            android:layout_width="50dp"
	            android:layout_height="50dp"
	            android:indeterminateBehavior="repeat"
	            android:indeterminateOnly="true"/>
	        <TextView
	            android:id="@+id/app_video_speed"
	            android:layout_width="wrap_content"
	            android:layout_marginTop="4dp"
	            android:layout_height="wrap_content"
	            android:gravity="center"
	            android:visibility="gone"
	            android:text="188Kb/s"
	            android:textColor="@android:color/white"/>
	    </LinearLayout>
	
	    <!-- 中间触摸提示-->
	    <include
	        layout="@layout/simple_player_touch_gestures"
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"
	        android:layout_centerInParent="true"/>
	
	    <!-- 顶部栏-->
	    <include layout="@layout/simple_player_topbar"/>
	    <!-- 底部栏-->
	    <include
	        layout="@layout/simple_player_controlbar"
	        android:layout_width="match_parent"
	        android:layout_height="wrap_content"
	        android:layout_alignParentBottom="true"/>
	
	    <!--声音亮度控制-->
	    <LinearLayout
	        android:id="@+id/simple_player_settings_container"
	        android:layout_width="250dp"
	        android:layout_height="match_parent"
	        android:layout_alignParentLeft="true"
	        android:background="#80000000"
	        android:gravity="center_vertical"
	        android:orientation="vertical"
	        android:visibility="visible">
	
	        <LinearLayout
	            android:id="@+id/simple_player_volume_controller_container"
	            android:layout_width="match_parent"
	            android:layout_height="wrap_content"
	            android:gravity="center"
	            android:orientation="horizontal">
	
	            <ImageView
	                android:layout_width="30dp"
	                android:layout_height="30dp"
	                android:src="@drawable/qcloud_player_icon_audio_vol_mute"/>
	
	            <SeekBar
	                android:id="@+id/simple_player_volume_controller"
	                style="?android:attr/progressBarStyleHorizontal"
	                android:layout_width="150dp"
	                android:layout_height="wrap_content"/>
	
	            <ImageView
	                android:layout_width="30dp"
	                android:layout_height="30dp"
	                android:src="@drawable/qcloud_player_icon_audio_vol"/>
	        </LinearLayout>
	
	        <LinearLayout
	            android:id="@+id/simple_player_brightness_controller_container"
	            android:layout_width="match_parent"
	            android:layout_height="wrap_content"
	            android:layout_marginTop="20dp"
	            android:gravity="center"
	            android:orientation="horizontal">
	
	            <ImageView
	                android:layout_width="30dp"
	                android:layout_height="30dp"
	                android:padding="5dp"
	                android:src="@drawable/qcloud_player_icon_brightness"/>
	
	            <SeekBar
	                android:id="@+id/simple_player_brightness_controller"
	                style="?android:attr/progressBarStyleHorizontal"
	                android:layout_width="150dp"
	                android:layout_height="wrap_content"/>
	
	            <ImageView
	                android:layout_width="30dp"
	                android:layout_height="30dp"
	                android:src="@drawable/qcloud_player_icon_brightness"/>
	        </LinearLayout>
	
	    </LinearLayout>
	
	
	    <!--分辨率选择-->
	    <LinearLayout
	        android:id="@+id/simple_player_select_stream_container"
	        android:layout_width="150dp"
	        android:layout_height="match_parent"
	        android:layout_alignParentRight="true"
	        android:background="#80000000"
	        android:gravity="center_vertical"
	        android:visibility="gone">
	
	        <ListView
	            android:id="@+id/simple_player_select_streams_list"
	            android:layout_width="150dp"
	            android:layout_height="wrap_content"/>
	    </LinearLayout>
	
	
	    <ImageView
	        android:id="@+id/play_icon"
	        android:layout_width="wrap_content"
	        android:layout_height="wrap_content"
	        android:layout_centerInParent="true"
	        android:layout_marginTop="8dp"
	        android:src="@drawable/simple_player_center_play"/>
	
	</RelativeLayout>



3.播放器PlayerView对象的方法如下：

	PlayerView(Activity activity)

	//生命周期方法回调
	PlayerView onPause()
	PlayerView onResume()
	PlayerView onDestroy()
	PlayerView onConfigurationChanged(final Configuration newConfig)
	boolean onBackPressed()
	//显示缩略图
	PlayerView showThumbnail(OnShowThumbnailListener onShowThumbnailListener)
	//设置播放信息监听回调
	PlayerView setOnInfoListener(IMediaPlayer.OnInfoListener onInfoListener)
	//设置播放器中的返回键监听
	PlayerView setPlayerBackListener(OnPlayerBackListener listener)
	//设置控制面板显示隐藏监听
	PlayerView setOnControlPanelVisibilityChangListenter(OnControlPanelVisibilityChangeListener listener)
	//百分比显示切换
	PlayerView toggleAspectRatio()
	//设置播放区域拉伸类型
	PlayerView setScaleType(int showType)
	//旋转角度
	PlayerView setPlayerRotation()
	//旋转指定角度
	PlayerView setPlayerRotation(int rotation)
	//设置播放地址包括视频清晰度列表对应地址列表
	PlayerView setPlaySource(List<VideoijkBean> list)
	//设置播放地址单个视频VideoijkBean
	PlayerView setPlaySource(VideoijkBean videoijkBean)
	//设置播放地址单个视频地址时带流名称
	PlayerView setPlaySource(String stream, String url)
	//设置播放地址单个视频地址时
	PlayerView setPlaySource(String url)
	//自动播放
	PlayerView autoPlay(String path)
	//开始播放
	PlayerView startPlay()
	//设置视频名称
	PlayerView setTitle(String title)
	//选择要播放的流
	PlayerView switchStream(int index)
	//暂停播放
	PlayerView pausePlay()
	//停止播放
	PlayerView stopPlay()
	//设置播放位置
	PlayerView seekTo(int playtime)
	//获取当前播放位置
	int getCurrentPosition()
	//获取视频播放总时长
	long getDuration()
	//设置2/3/4/5G和WiFi网络类型提示 true为进行2/3/4/5G网络类型提示 false 不进行网络类型提示
	PlayerView setNetWorkTypeTie(boolean isGNetWork)
	//是否仅仅为全屏
	PlayerView setOnlyFullScreen(boolean isFull)
	//设置是否禁止双击
	PlayerView setForbidDoulbeUp(boolean flag)
	//当前播放的是否是直播
	boolean isLive()
	//是否禁止触摸
	PlayerView forbidTouch(boolean forbidTouch)
	//隐藏所有状态界面
	PlayerView hideAllUI()
	获取顶部控制barview
	View getTopBarView()
	//获取底部控制barview
	View getBottonBarView()
	//获取旋转view
	ImageView getRationView()
	//获取返回view
	ImageView getBackView()
	//获取菜单view
	ImageView getMenuView()
	//获取全屏按钮view
	ImageView getFullScreenView()
	//获取底部bar的播放view
	ImageView getBarPlayerView()
	//获取中间的播放view
	ImageView getPlayerView()
	//隐藏返回键，true隐藏，false为显示
	PlayerView hideBack(boolean isHide)
	//隐藏菜单键，true隐藏，false为显示
	PlayerView hideMenu(boolean isHide)
	//隐藏分辨率按钮，true隐藏，false为显示
	PlayerView hideSteam(boolean isHide)
	//隐藏旋转按钮，true隐藏，false为显示
	PlayerView hideRotation(boolean isHide)
	//隐藏全屏按钮，true隐藏，false为显示
	PlayerView hideFullscreen(boolean isHide)
	//隐藏中间播放按钮,ture为隐藏，false为不做隐藏处理，但不是显示
	PlayerView hideCenterPlayer(boolean isHide)
	//显示或隐藏操作面板
	PlayerView operatorPanl()
	//全屏切换
	PlayerView toggleFullScreen()
	//设置自动重连的模式或者重连时间，isAuto true 出错重连，false出错不重连，connectTime重连的时间
	setAutoReConnect(boolean isAuto, int connectTime)
	//进度条和时长显示的方向切换
	PlayerView toggleProcessDurationOrientation()
	//设置进度条和时长显示的方向，默认为上下显示，PlayStateParams.PROCESS_PORTRAIT为上下显示PlayStateParams.PROCESS_LANDSCAPE为左右显示PlayStateParams.PROCESS_CENTER为中间两边样式
	setProcessDurationOrientation(int portrait)
	//显示菜单设置
	showMenu()
	//获取界面方向
	int getScreenOrientation()
	//显示加载网速
	PlayerView setShowSpeed(boolean isShow)
	//是否隐藏topbar，true为隐藏，false为不隐藏，但不一定是显示
	PlayerView hideHideTopBar(boolean isHide)
	//是否隐藏bottonbar，true为隐藏，false为不隐藏，但不一定是显示
	PlayerView hideBottonBar(boolean isHide)
	//是否隐藏上下bar，true为隐藏，false为不隐藏，但不一定是显示
	PlayerView hideControlPanl(boolean isHide)
	//设置是否禁止隐藏bar,优先级低于hideControlPanl
	PlayerView setForbidHideControlPanl(boolean flag)


4.全屏隐藏虚拟按键方法

参考HPlayerActivity类，获取Activity的根目录

	main = getLayoutInflater().from(this).inflate(R.layout.activity_h, null);

然在在oncreate()方法中设置监听

		/**虚拟按键的隐藏方法*/
        main.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {

                //比较Activity根布局与当前布局的大小
                int heightDiff = main.getRootView().getHeight() - main.getHeight();
                if (heightDiff > 100) {
                    //大小超过100时，一般为显示虚拟键盘事件
                    main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                } else {
                    //大小小于100时，为不显示虚拟键盘或虚拟键盘隐藏
                    main.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

                }
            }
        });

5.半屏视频，横竖屏切换时不填满问题

1.确保Activity中调用生命周期方法

	@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
    }

2.确保清单文件中配置属性

	android:configChanges="orientation|keyboardHidden|screenSize"
            android:screenOrientation="portrait"

例如

	<activity
            android:name="com.dou361.jjdxm_ijkplayer.HPlayerActivity"
            android:configChanges="orientation|keyboardHidden|screenSize"
            android:screenOrientation="portrait"
            android:theme="@style/AppTheme"/>


#### 关于定制 ####

#### 隐藏部分不想要的界面 ####

	//隐藏返回键，true隐藏，false为显示
	PlayerView hideBack(boolean isHide)
	//隐藏菜单键，true隐藏，false为显示
	PlayerView hideMenu(boolean isHide)
	//隐藏分辨率按钮，true隐藏，false为显示
	PlayerView hideSteam(boolean isHide)
	//隐藏旋转按钮，true隐藏，false为显示
	PlayerView hideRotation(boolean isHide)
	//隐藏全屏按钮，true隐藏，false为显示
	PlayerView hideFullscreen(boolean isHide)
	//隐藏中间播放按钮,ture为隐藏，false为不做隐藏处理，但不是显示
	PlayerView hideCenterPlayer(boolean isHide)


#### 加载时显示网速 ####
默认加载时不显示网速，可以通过setShowSpeed(boolean isShow)设置加载时是否需要显示，true为显示，false为不显示

#### 播放器底部bar播放进度条样式定制 ####
默认的进度样式是竖屏为上下样式，即进度条在播放时长的上面，横屏为左右样式，即进度条在播放时长的中间。样式定制主要是两个方法搭配使用toggleProcessDurationOrientation方法和setProcessDurationOrientation方法，横竖屏切换2中情况，和3种进度条样式

	/**上下样式*/
    PlayStateParams.PROCESS_PORTRAIT
    /**左右样式*/
    PlayStateParams.PROCESS_LANDSCAPE
    /**中间两边样式*/
    PlayStateParams.PROCESS_CENTER

总共有2的3次方中样式，下面只罗列几种样式

1.横竖屏都为上下样式

    rootView = getLayoutInflater().from(this).inflate(你的布局, null);
	setContentView(rootView);
	player = new PlayerView(this,rootView) {
            @Override
            public PlayerView toggleProcessDurationOrientation() {
                return setProcessDurationOrientation(PlayStateParams.PROCESS_PORTRAIT);
            }
        }
                .setTitle("什么")
                .setProcessDurationOrientation(PlayStateParams.PROCESS_PORTRAIT)
                .setScaleType(PlayStateParams.fitparent)
                .forbidTouch(false)
                .hideCenterPlayer(true)
                .setPlaySource(list)
                .startPlay();

2.横竖屏都为左右样式

    rootView = getLayoutInflater().from(this).inflate(你的布局, null);
	setContentView(rootView);
	player = new PlayerView(this,rootView) {
            @Override
            public PlayerView toggleProcessDurationOrientation() {
                return setProcessDurationOrientation(PlayStateParams.PROCESS_LANDSCAPE);
            }
        }
                .setTitle("什么")
                .setProcessDurationOrientation(PlayStateParams.PROCESS_LANDSCAPE)
                .setScaleType(PlayStateParams.fitparent)
                .forbidTouch(false)
                .hideCenterPlayer(true)
                .setPlaySource(list)
                .startPlay();

3.横屏为上下样式竖屏为左右样式

    rootView = getLayoutInflater().from(this).inflate(你的布局, null);
	setContentView(rootView);
	player = new PlayerView(this,rootView) {
            @Override
            public PlayerView toggleProcessDurationOrientation() {
                return setProcessDurationOrientation(getScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE?PlayStateParams.PROCESS_LANDSCAPE:PlayStateParams.PROCESS_PORTRAIT);
            }
        }
                .setTitle("什么")
                .setProcessDurationOrientation(PlayStateParams.PROCESS_LANDSCAPE)
                .setScaleType(PlayStateParams.fitparent)
                .forbidTouch(false)
                .hideCenterPlayer(true)
                .setPlaySource(list)
                .startPlay();

4.横屏为左右样式竖屏为上下样式

    rootView = getLayoutInflater().from(this).inflate(你的布局, null);
	setContentView(rootView);
	player = new PlayerView(this,rootView) {
            @Override
            public PlayerView toggleProcessDurationOrientation() {
                return setProcessDurationOrientation(getScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT?PlayStateParams.PROCESS_PORTRAIT:PlayStateParams.PROCESS_LANDSCAPE);
            }
        }
                .setTitle("什么")
                .setProcessDurationOrientation(PlayStateParams.PROCESS_CENTER)
                .setScaleType(PlayStateParams.fitparent)
                .forbidTouch(false)
                .hideCenterPlayer(true)
                .setPlaySource(list)
                .startPlay();

5.横屏为左右样式竖屏为中间两边样式

    rootView = getLayoutInflater().from(this).inflate(你的布局, null);
	setContentView(rootView);
	player = new PlayerView(this,rootView) {
            @Override
            public PlayerView toggleProcessDurationOrientation() {
                return setProcessDurationOrientation(getScreenOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT?PlayStateParams.PROCESS_CENTER:PlayStateParams.PROCESS_LANDSCAPE);
            }
        }
                .setTitle("什么")
                .setProcessDurationOrientation(PlayStateParams.PROCESS_CENTER)
                .setScaleType(PlayStateParams.fitparent)
                .forbidTouch(false)
                .hideCenterPlayer(true)
                .setPlaySource(list)
                .startPlay();


3.ijkplayer封装的视频播放信息返回码监听，可以通过setOnInfoListener去监听


	/*
     * Do not change these values without updating their counterparts in native
     */
    int MEDIA_INFO_UNKNOWN = 1;//未知信息
    int MEDIA_INFO_STARTED_AS_NEXT = 2;//播放下一条
    int MEDIA_INFO_VIDEO_RENDERING_START = 3;//视频开始整备中
    int MEDIA_INFO_VIDEO_TRACK_LAGGING = 700;//视频日志跟踪
    int MEDIA_INFO_BUFFERING_START = 701;//开始缓冲中
    int MEDIA_INFO_BUFFERING_END = 702;//缓冲结束
    int MEDIA_INFO_NETWORK_BANDWIDTH = 703;//网络带宽，网速方面
    int MEDIA_INFO_BAD_INTERLEAVING = 800;//
    int MEDIA_INFO_NOT_SEEKABLE = 801;//不可设置播放位置，直播方面
    int MEDIA_INFO_METADATA_UPDATE = 802;//
    int MEDIA_INFO_TIMED_TEXT_ERROR = 900;
    int MEDIA_INFO_UNSUPPORTED_SUBTITLE = 901;//不支持字幕
    int MEDIA_INFO_SUBTITLE_TIMED_OUT = 902;//字幕超时

    int MEDIA_INFO_VIDEO_INTERRUPT= -10000;//数据连接中断
    int MEDIA_INFO_VIDEO_ROTATION_CHANGED = 10001;//视频方向改变
    int MEDIA_INFO_AUDIO_RENDERING_START = 10002;//音频开始整备中

    int MEDIA_ERROR_UNKNOWN = 1;//未知错误
    int MEDIA_ERROR_SERVER_DIED = 100;//服务挂掉
    int MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK = 200;//数据错误没有有效的回收
    int MEDIA_ERROR_IO = -1004;//IO错误
    int MEDIA_ERROR_MALFORMED = -1007;
    int MEDIA_ERROR_UNSUPPORTED = -1010;//数据不支持
    int MEDIA_ERROR_TIMED_OUT = -110;//数据超时




## ChangeLog ##

2016.09.05 修改支持非Activity中使用当前播放器view，例如fragment、holder之类使用，添加直播地址获取，提供可观看案例（如直播地址有不妥的地方可联系删除，多有得罪啦）
遗留问题：
触摸时，隐藏动作取消
触摸时，进度条面会有点共用
直播停止开始卡帧
手动换源卡帧
直播判断问题
进度条隐藏提取方法出来
修改初始化方法
本地视频播放问题
播放加载状态的监听问题比如说链接失效，请求超时，断流了，断网了之类的
缓冲好内容使用seekto之后还是需要重新缓冲
乐视手机  点开亮度为0

2016.08.30 修复播放时长为零问题；修复使用拉伸方法横屏方向不起效问题；修改最小支持版本为9；添加了SO文件的最小版本为9的类库；添加VideoijkBean比较方法，可以比对视频对象是不是同一个；添加横竖屏配置说明；添加隐藏虚拟按键的方法。

2016.08.26 1.0.5添加上下操作工具栏的隐藏定制，主要是hideControlPanl，hideHideTopBar和hideBottonBar方法控制

2016.08.26 1.0.4打包修复播放进度样式点击返回键不起效

2016.08.25 1.0.3添加视频播放进度条样式，添加菜单设置

2016.08.24 1.0.2修复播放出错点击没有反应，这是修改旋转视频方向是造成的bug,当前默认为5秒无操作自动重试，修改加载进度条的显示时间，之前是加载回调整备中才显示，改为点击加载立即显示，新增修改自动重试的方式和重试的时间

2016.08.20 修复视频进入后台继续播放，切换视频源是画面卡住等问题

2016.08.20 1.0.1修复点击播放、点击暂停、再点击播放时，加载进度条一直显示问题；修复第一次打开播放器，触摸视频界面，视频重新播放问题；恢复视频拖动条默认样式，修复显示不完整问题；添加对外操作的view，可通过getxxxView()方法获得；添加了PlayerView对象的方法及说明，可链式开发。

## About Author ##

#### 个人网站:[http://www.dou361.com][web] ####
#### GitHub:[jjdxmashl][github] ####
#### QQ:316988670 ####
#### 交流QQ群:548545202 ####


## License ##

    Copyright (C) dou361, The Framework Open Source Project
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
     	http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

## (Frequently Asked Questions)FAQ ##
## Bugs Report and Help ##

If you find any bug when using project, please report [here][issues]. Thanks for helping us building a better one.




[web]:http://www.dou361.com
[github]:https://github.com/jjdxmashl/
[project]:https://github.com/jjdxmashl/jjdxm_ijkplayer/
[issues]:https://github.com/jjdxmashl/jjdxm_ijkplayer/issues/new
[downapk]:https://raw.githubusercontent.com/jjdxmashl/jjdxm_ijkplayer/master/apk/app-debug.apk
[lastaar]:https://raw.githubusercontent.com/jjdxmashl/jjdxm_ijkplayer/master/release/jjdxm-ijkplayer-1.0.0.aar
[lastjar]:https://raw.githubusercontent.com/jjdxmashl/jjdxm_ijkplayer/master/release/jjdxm-ijkplayer-1.0.0.jar
[icon01]:https://raw.githubusercontent.com/jjdxmashl/jjdxm_ijkplayer/master/screenshots/icon01.png
[icon02]:https://raw.githubusercontent.com/jjdxmashl/jjdxm_ijkplayer/master/screenshots/icon02.png

[jaraar]:https://github.com/jjdxmashl/jjdxm_ecodingprocess/blob/master/架包的打包引用以及冲突解决.md
[minify]:https://github.com/jjdxmashl/jjdxm_ecodingprocess/blob/master/AndroidStudio代码混淆注意的问题.md
[author]:https://github.com/tcking
[author1]:https://github.com/Bilibili
[url]:https://github.com/tcking/GiraffePlayer
[url1]:https://github.com/Bilibili/ijkplayer
