package io.agora.LivevideoCall;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import io.agora.rtc.IRtcEngineEventHandler;
import io.agora.rtc.RtcEngine;

public abstract class BaseActivity extends AppCompatActivity implements EventHandler {
    protected DisplayMetrics mDisplayMetrics = new DisplayMetrics();
    protected int mStatusBarHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowUtil.hideWindowStatusBar(getWindow());
        setGlobalLayoutListener();
        getDisplayMetrics();
        initStatusBarHeight();
    }

    private void setGlobalLayoutListener() {
        final View layout = findViewById(Window.ID_ANDROID_CONTENT);
        ViewTreeObserver observer = layout.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                onGlobalLayoutCompleted();
            }
        });
    }

   protected void onGlobalLayoutCompleted() {

    }

    private void getDisplayMetrics() {
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
    }

    private void initStatusBarHeight() {
        mStatusBarHeight = WindowUtil.getSystemStatusBarHeight(this);
    }

    protected AgoraApplication application() {
        return (AgoraApplication) getApplication();
    }

    protected RtcEngine rtcEngine() {
        return application().rtcEngine();
    }

//    protected EngineConfig config() {
//        return application().engineConfig();
//    }

    protected StatsManager statsManager() { return application().statsManager(); }

    protected void registerRtcEventHandler(EventHandler handler) {
        application().registerEventHandler(handler);
    }

    protected void removeRtcEventHandler(EventHandler handler) {
        application().removeEventHandler(handler);
    }


    @Override
    public void onFirstRemoteVideoDecoded(int uid, int width, int height, int elapsed) {

    }


    @Override
    public void onJoinChannelSuccess(String channel, int uid, int elapsed) {

    }

    @Override
    public void onLeaveChannel(IRtcEngineEventHandler.RtcStats stats) {

    }

    @Override
    public void onUserOffline(int uid, int reason) {

    }

    @Override
    public void onUserJoined(int uid, int elapsed) {

    }

    @Override
    public void onLastmileQuality(final int quality) {

    }

    @Override
    public void onLastmileProbeResult(final IRtcEngineEventHandler.LastmileProbeResult result) {

    }

   @Override
    public void onLocalVideoStats(IRtcEngineEventHandler.LocalVideoStats stats) {

    }

    @Override
    public void onRtcStats(IRtcEngineEventHandler.RtcStats stats) {

    }

   @Override
    public void onNetworkQuality(int uid, int txQuality, int rxQuality) {

    }

   @Override
    public void onRemoteVideoStats(IRtcEngineEventHandler.RemoteVideoStats stats) {

    }

    @Override
    public void onRemoteAudioStats(IRtcEngineEventHandler.RemoteAudioStats stats) {

    }

    public static class WindowUtil {
        public static void hideWindowStatusBar(Window window) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(Color.TRANSPARENT);
            } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }

        public static int getSystemStatusBarHeight(Context context) {
            int id = context.getResources().getIdentifier(
                    "status_bar_height", "dimen", "android");
            return id > 0 ? context.getResources().getDimensionPixelSize(id) : id;
        }
    }
}
