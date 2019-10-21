package com.example.pets.classes;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pets.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_card_view, viewGroup, false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        userViewHolder.id = userList.get(i).getId();
        userViewHolder.tvFirstname.setText(userList.get(i).getFirstname());
        userViewHolder.tvLastname.setText(userList.get(i).getLastname());
        userViewHolder.tvEmail.setText(userList.get(i).getEmail());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{

        TextView tvFirstname, tvLastname, tvEmail;
        int id;

        UserViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFirstname = itemView.findViewById(R.id.textViewFirstname);
            tvLastname = itemView.findViewById(R.id.textViewLastname);
            tvEmail = itemView.findViewById(R.id.textViewMail);

            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            PopupMenu popup = new PopupMenu(v.getContext(), v);
            popup.getMenuInflater().inflate(R.menu.contextmenu, popup.getMenu());
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                //Button actions
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.iteUpdateUser: //Update user
                            System.out.println("Update user huashuashuas");
                        case R.id.iteDeleteUser: //Delete user
                            System.out.println("Delete user juejuejue");
                        default:
                            return true;
                    }
                }
            });
            popup.show();
        }
    }
}
