package com.example.pets.classes;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pets.MainMenuActivity;
import com.example.pets.R;
import com.example.pets.UpdateActivity;
import com.example.pets.database.PetsDB;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList;
    private Context mContext;

    public UserAdapter(List<User> userList, Context mContext) {
        this.userList = userList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_card_view, viewGroup, false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        userViewHolder.tvFirstname.setText(userList.get(i).getFirstname());
        userViewHolder.tvLastname.setText(userList.get(i).getLastname());
        userViewHolder.tvEmail.setText(userList.get(i).getEmail());
    }

    @Override
    public int getItemCount() {
        try {
            return userList.size();
        }catch(java.lang.NullPointerException ex){
            return 0;
        }
    }

    class UserViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{

        TextView tvFirstname, tvLastname, tvEmail;

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
                            Intent intent = new Intent(mContext, UpdateActivity.class);
                            intent.putExtra("email", tvEmail.getText());
                            mContext.startActivity(intent);
                            return true;
                        case R.id.iteDeleteUser: //Delete user
                            PetsDB petsDB = new PetsDB(mContext,
                                    "pets", null, 1);
                            petsDB.delete(tvEmail.getText().toString());
                            //Toast.makeText(mContext, Resources.getSystem().getString(R.string.toastdeleted), Toast.LENGTH_SHORT).show();
                            mContext.startActivity(new Intent(mContext, MainMenuActivity.class));
                            return true;
                        default:
                            return false;
                    }
                }
            });
            popup.show();
        }
    }
}
