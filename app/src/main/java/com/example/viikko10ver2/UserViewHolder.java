package com.example.viikko10ver2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    ImageView userImage;
    TextView fullName, email, studyField, degreesHeader, degrees;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        userImage = itemView.findViewById(R.id.ivUserImage);
        fullName  = itemView.findViewById(R.id.txtFullName);
        email  = itemView.findViewById(R.id.txtEmail);
        studyField  = itemView.findViewById(R.id.txtStudyField);
        degrees = itemView.findViewById(R.id.tvDegrees);
        //degreesHeader = itemView.findViewById(R.id.tvDegreesHeader);
    }


}
