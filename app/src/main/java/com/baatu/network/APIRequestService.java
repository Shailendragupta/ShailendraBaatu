package com.baatu.network;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.baatu.db.DaoUser;
import com.baatu.db.TableUser;
import com.baatu.db.UserDB;
import com.baatu.model.User;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * @author Shailendra This is a bound service class
 * In this service class we are fetching data from back-end
 *
 */
public class APIRequestService extends Service {

    public static final int RUNNING = 1;
    public static final int SUCCESS = 2;
    public static final int ERROR = 3;

    private final IBinder localBinder = new ApiBinder();

    private ApiResultReceiver mReceiver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }


    public class ApiBinder extends Binder {

        public  APIRequestService getService(ApiResultReceiver receiver) {
            mReceiver = receiver;
            return APIRequestService.this;
        }
    }


    public void sendUserListRequest() {
        mReceiver.send(RUNNING, null);
        ServiceFactory.getServiceAPIs()
                .getUserList()
                .subscribeOn(Schedulers.io())
        .map(new Function<ArrayList<User>, ArrayList<User>>() {
            @Override
            public ArrayList<User> apply(ArrayList<User> users) {
                DaoUser daoUser = UserDB.getInstance(getApplicationContext()).daoUser();
                for(User user : users) {
                    daoUser.insertUser(new TableUser(user.getId(), user));
                }
                return users;
            }
        })
        .subscribe(new Consumer<ArrayList<User>>() {
            @Override
            public void accept(ArrayList<User> users) {
                mReceiver.send(SUCCESS, null);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                mReceiver.send(ERROR, null);
            }
        }, new Action() {
            @Override
            public void run()  {

            }
        });

    }
}
