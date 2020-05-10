package com.baatu.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.baatu.UserApplication;
import com.baatu.db.TableUser;
import com.baatu.db.UserDB;

import java.util.List;
/**
 *
 * @author Shailendra This is a viewModel class
 *In this class we are using Live Data.
 */
public class UserViewModel extends ViewModel {

    private LiveData<List<TableUser>> currentName;

    public LiveData<List<TableUser>> getUserList() {
        if (currentName == null) {
            currentName = UserDB.getInstance(UserApplication.getAppContext()).daoUser().getAllUser();
        }
        return currentName;
    }
}
