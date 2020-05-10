package com.baatu.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.baatu.db.TableUser;
import com.baatu.model.User;
import com.baatu.shailendra.R;
import com.baatu.viewholder.UserViewHolder;

import java.util.List;
/**
 *
 * @author Shailendra This is a recyclerview class
 *
 */
public class UserListAdapter extends RecyclerView.Adapter  {

    List<TableUser> mUserList;

    public UserListAdapter(List<TableUser> userList) {
        mUserList = userList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        UserViewHolder userHolder = (UserViewHolder) holder;
        User user = mUserList.get(position).getUser();
        userHolder.textName.setText("Name :"+user.getName());
        userHolder.textPhone.setText("Phone :"+user.getPhone());
        userHolder.textEmail.setText("Email :"+user.getEmail());
        userHolder.textCompany.setText("Company :"+user.getCompany().getName());

    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public void updateUserList(List<TableUser> userList) {
        mUserList.clear();
        mUserList.addAll(userList);
        notifyDataSetChanged();
    }
}
