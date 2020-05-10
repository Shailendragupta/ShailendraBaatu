package com.baatu.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.baatu.shailendra.R;
/**
 *
 * @author Shailendra This is a user view holder class
 * This class is using in userList adapter class
 */
public class UserViewHolder extends RecyclerView.ViewHolder {

    public TextView textName;
    public TextView textEmail;
    public TextView textPhone;
    public TextView textCompany;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        textName = itemView.findViewById(R.id.textName);
        textEmail = itemView.findViewById(R.id.textEmail);
        textPhone = itemView.findViewById(R.id.textPhone);
        textCompany = itemView.findViewById(R.id.textCompany);
    }
}
