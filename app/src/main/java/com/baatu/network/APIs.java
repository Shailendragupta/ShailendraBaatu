package com.baatu.network;

import com.baatu.model.User;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 *
 * @author Shailendra This is a interface class
 *we have define observable user list
 */

public interface APIs {

    @GET("/users")
    Observable<ArrayList<User>> getUserList();
}
