package com.baatu.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.baatu.adapter.UserListAdapter;
import com.baatu.db.TableUser;
import com.baatu.network.APIRequestService;
import com.baatu.network.ApiResultReceiver;
import com.baatu.shailendra.R;
import com.baatu.ui.UserViewModel;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Shailendra This is main activity class.
 *
 */
public class MainActivity extends AppCompatActivity implements ApiResultReceiver.Receiver {

    private Button mBtnCenter;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView;

    private AppCompatTextView textLeftTop;
    private AppCompatTextView textRightTop;
    private AppCompatTextView textLeftBottom;
    private AppCompatTextView textRightBottom;

    private UserListAdapter mRecyclerAdapter;

    private ApiResultReceiver mReceiver;
    private APIRequestService mService;
    boolean mBound = false;

    private UserViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mReceiver = new ApiResultReceiver(new Handler());
        mReceiver.setReceiver(this);

        attachApiRequestService();

        mBtnCenter = findViewById(R.id.btnCenter);
        mProgressBar = findViewById(R.id.progressBar);
        mRecyclerView = findViewById(R.id.recyclerView);
        textLeftTop = findViewById(R.id.textLeftTop);
        textLeftBottom = findViewById(R.id.textLeftBottom);
        textRightTop = findViewById(R.id.textRightTop);
        textRightBottom = findViewById(R.id.textRightBottom);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mRecyclerAdapter = new UserListAdapter(new ArrayList<TableUser>());
        mRecyclerView.setAdapter(mRecyclerAdapter);

        mBtnCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mBound) {
                    mService.sendUserListRequest();
                }
                else {
                    Toast.makeText(MainActivity.this, "Api Service is not attahced yet", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Get the ViewModel.
        model = new ViewModelProvider(this).get(UserViewModel.class);

        // Create the observer which updates the UI.
        final Observer<List<TableUser>> userObserver = new Observer<List<TableUser>>() {
            @Override
            public void onChanged(@Nullable final List<TableUser> userList) {
                if(userList.size() > 0) {
                    showUserList(userList);
                } else {
                    showCenterBtn("Click to get user list");
                }
            }
        };

        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        model.getUserList().observe(this, userObserver);
    }



    @Override
    protected void onStop() {
        super.onStop();
        if(mBound) {
            unbindService(mConnection);
        }
    }

    private void attachApiRequestService() {
        final Intent intent = new Intent(MainActivity.this, APIRequestService.class);
        bindService(intent, mConnection, Context.BIND_ADJUST_WITH_ACTIVITY | Context.BIND_AUTO_CREATE);
    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            APIRequestService.ApiBinder binder = (APIRequestService.ApiBinder) service;
            mService = binder.getService(mReceiver);
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case APIRequestService.RUNNING:
                showProgressBar();
                updateCornerText("Processing");
                break;
            case APIRequestService.SUCCESS:
                hideProgressBar();
                updateCornerText("Success");
                break;
            case APIRequestService.ERROR:
                showCenterBtn("ERROR! Something went wrong. Try Again");
                updateCornerText("Failed");
                break;
        }
    }

    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
        mBtnCenter.setVisibility(View.GONE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
        mBtnCenter.setVisibility(View.GONE);
    }

    private void showCenterBtn(String msg) {
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
        mBtnCenter.setVisibility(View.VISIBLE);
        mBtnCenter.setText(msg);
    }

    private void showUserList(List<TableUser> userList) {
        mProgressBar.setVisibility(View.GONE);
        mBtnCenter.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerAdapter.updateUserList(userList);
    }

    private void updateCornerText(String msg) {
        textRightBottom.setText(msg);
        textLeftBottom.setText(msg);
        textRightTop.setText(msg);
        textLeftTop.setText(msg);
    }
}
