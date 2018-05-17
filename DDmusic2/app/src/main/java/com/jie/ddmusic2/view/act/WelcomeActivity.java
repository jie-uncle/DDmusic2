package com.jie.ddmusic2.view.act;


import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.jie.ddmusic2.R;
import com.jie.ddmusic2.inject.DaggerWelcomePstComponent;
import com.jie.ddmusic2.inject.impl.Welcomemoudle;
import com.jie.ddmusic2.presenter.impl.WelcomePresenter;
import com.jie.ddmusic2.presenter.iport.IWelcomePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WelcomeActivity extends BaseActiivity implements IWelcomePresenter.WelcomeView{

    @BindView(R.id.welcome_jump)
    TextView welcomeJump;

    @Inject
    WelcomePresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        DaggerWelcomePstComponent.builder()
                .welcomemoudle(new Welcomemoudle(this))
                .build()
                .inject(this);
        presenter.time();

    }

    @OnClick(R.id.welcome_jump)
    public void onViewClicked() {
        presenter.click();
    }

    @Override
    public void intent2main() {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
