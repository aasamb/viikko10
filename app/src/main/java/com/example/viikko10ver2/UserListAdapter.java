package com.example.viikko10ver2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private Context context;
    private ArrayList<User> users;

    public UserListAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(context).inflate(R.layout.user_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.fullName.setText(users.get(position).getFirstName() + " " + users.get(position).getLastName());
        holder.email.setText(users.get(position).getEmail());
        holder.studyField.setText(users.get(position).getDegreeProgram());
        holder.userImage.setImageResource(users.get(position).getImage());
        if (users.get(position).getDegrees().size() > 0)    {
            holder.degrees.setVisibility(View.VISIBLE);
            //System.out.println(holder.fullName.getText() + ": " + ((users.get(position).getDegrees() == null) ? "Null" : "Ei null"));
            //System.out.println(holder.fullName.getText() + ": " + users.get(position).getDegrees().size());
            //holder.degreesHeader.setText("Tutkinnot: ");
            StringBuilder sbDegrees = new StringBuilder("Tutkinnot:\n");
            for (String degree : users.get(position).getDegrees()) {
                sbDegrees.append("- " + degree + "\n");
            }
            holder.degrees.setText(sbDegrees.toString());
        }

    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
