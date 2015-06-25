package com.ufo.judicature.Activity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ufo.judicature.Base.BaseActivity;
import com.ufo.judicature.Fragment.AgencyFragment;
import com.ufo.judicature.Fragment.AidFragment;
import com.ufo.judicature.Fragment.ExamFragment;
import com.ufo.judicature.Fragment.LawyerFragment;
import com.ufo.judicature.Fragment.NotarizationFragment;
import com.ufo.judicature.Fragment.PropagandaFragment;
import com.ufo.judicature.JudiApplication;
import com.ufo.judicature.R;
import com.ufo.judicature.Utils.Utils;
import com.ufo.judicature.Widget.Toast;

/**
 * main page
 */
public class MainActivity extends BaseActivity {

    private long exitTime = 0;
    private int currentTabIndex;
    private Fragment[] fragments;
    private int index;
    private Button[] mTabs;
    private Resources resources;
    private PropagandaFragment propagandafragment;
    private ExamFragment examfragment;
    private LawyerFragment lawyerfragment;
    private NotarizationFragment notarizationfragment;
    private AgencyFragment agencyfragment;
    private AidFragment aidfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resources = getResources();

        initView();

        propagandafragment = new PropagandaFragment();
        examfragment = new ExamFragment();
        lawyerfragment = new LawyerFragment();
        notarizationfragment = new NotarizationFragment();
        agencyfragment = new AgencyFragment();
        aidfragment = new AidFragment();
        Fragment[] arrayOfFragment = new Fragment[6];
        arrayOfFragment[0] = propagandafragment;
        arrayOfFragment[1] = examfragment;
        arrayOfFragment[2] = lawyerfragment;
        arrayOfFragment[3] = notarizationfragment;
        arrayOfFragment[4] = agencyfragment;
        arrayOfFragment[5] = aidfragment;
        fragments = arrayOfFragment;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, propagandafragment).show(propagandafragment)
                .commit();
    }

    private void initView() {
        mTabs = new Button[7];
        mTabs[0] = ((Button) findViewById(R.id.button_propaganda));
        mTabs[1] = ((Button) findViewById(R.id.button_exam));
        mTabs[2] = ((Button) findViewById(R.id.button_lawyer));
        mTabs[3] = ((Button) findViewById(R.id.button_notarization));
        mTabs[4] = ((Button) findViewById(R.id.button_agency));
        mTabs[5] = ((Button) findViewById(R.id.button_aid));
        mTabs[6] = ((Button) findViewById(R.id.button_continue));
        mTabs[0].setSelected(true);

        int buttonWidth = Utils.getScreenWidth(this) / 4;
        for (Button button: mTabs) {
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) button.getLayoutParams();
            params.width = buttonWidth;
            button.setLayoutParams(params);
        }
    }

    public void onTabClicked(View v) {
        tabSelect(v.getId());
    }

    public void tabSelect(int viewid) {
        switch (viewid) {
            case R.id.button_propaganda:
                index = 0;
                selectTab();
                break;
            case R.id.button_exam:
                index = 1;
                selectTab();
                break;
            case R.id.button_lawyer:
                index = 2;
                selectTab();
                break;
            case R.id.button_notarization:
                index = 3;
                selectTab();
                break;
            case R.id.button_agency:
                index = 4;
                selectTab();
                break;
            case R.id.button_aid:
                index = 5;
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
                Toast.show(this, resources.getString(R.string.exit_toast));
                exitTime = System.currentTimeMillis();
            } else {
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
