package com.ufo.judicature.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.ufo.judicature.Base.BaseActivity;
import com.ufo.judicature.JudiApplication;
import com.ufo.judicature.R;
import com.ufo.judicature.Widget.Toast;

/**
 * 主页
 */
public class MainActivity extends BaseActivity {

    // /** 再按一次退出程序 */
    private long exitTime = 0;
    private int currentTabIndex;
    private Fragment[] fragments;
    private int index;
    private Button[] mTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


    }

    private void initView() {
        this.mTabs = new Button[5];
        this.mTabs[0] = ((Button) findViewById(R.id.button_home));
        this.mTabs[1] = ((Button) findViewById(R.id.button_sort));
        this.mTabs[2] = ((Button) findViewById(R.id.button_store));
        this.mTabs[3] = ((Button) findViewById(R.id.button_shopcart));
        this.mTabs[4] = ((Button) findViewById(R.id.button_mycenter));
        this.mTabs[0].setSelected(true);
    }

    public void onTabClicked(View v) {
        tabSelect(v.getId());
    }

    public void tabSelect(int viewid) {
        switch (viewid) {
            case R.id.button_home:
                index = 0;
                selectTab();
                break;
            case R.id.button_sort:
                index = 1;
                selectTab();
                break;
            case R.id.button_store:
                index = 2;
                selectTab();
                break;
            case R.id.button_shopcart:
                index = 3;
                selectTab();
                break;
            case R.id.button_mycenter:
                index = 4;
                selectTab();
                break;
            default:
                break;
        }
    }

    private void selectTab() {
        if (currentTabIndex != index) {
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();
            transaction.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                transaction.add(R.id.fragment_container, this.fragments[index]);
            }
            transaction.show(fragments[index]).commitAllowingStateLoss();
        }
        mTabs[currentTabIndex].setSelected(false);
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }

    @Override
    protected void onNewIntent(Intent intent) {
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.show(this, "再按一次退出程序");
                exitTime = System.currentTimeMillis();
            } else {
                // finish();
                activityManager.popAllActivity();
                JudiApplication.getInstance().onTerminate();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
