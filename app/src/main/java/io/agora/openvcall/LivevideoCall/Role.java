package io.agora.openvcall.LivevideoCall;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import io.agora.openvcall.R;
import io.agora.rtc.Constants;

public class Role extends Base {

    public static final String KEY_CLIENT_ROLE = "key_client_role";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_role);
    }

    @Override
    protected void onGlobalLayoutCompleted() {
        RelativeLayout layout = findViewById(R.id.role_title_layout);
        RelativeLayout.LayoutParams params =
                (RelativeLayout.LayoutParams) layout.getLayoutParams();
        params.height += mStatusBarHeight;
        layout.setLayoutParams(params);

        layout = findViewById(R.id.role_content_layout);
        params = (RelativeLayout.LayoutParams) layout.getLayoutParams();
        params.topMargin = (mDisplayMetrics.heightPixels -
                layout.getMeasuredHeight()) * 3 / 7;
        layout.setLayoutParams(params);
    }

    public void onJoinAsBroadcaster(View view) {
        gotoLiveActivity(Constants.CLIENT_ROLE_BROADCASTER);
    }

    public void onJoinAsAudience(View view) {
        gotoLiveActivity(Constants.CLIENT_ROLE_AUDIENCE);
    }

    private void gotoLiveActivity(int role) {
        Intent intent = new Intent(getIntent());
        intent.putExtra(KEY_CLIENT_ROLE, role);
        intent.setClass(getApplicationContext(), Live.class);
        startActivity(intent);
    }

    public void onBackArrowPressed(View view) {
        finish();
    }
}
