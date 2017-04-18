package com.example.nikita.smartphonesupdate;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.UUID;

public class PhoneFragment extends Fragment {
    private ImageView imagePhone;
    private TextView descriptionText;
    private  TextView titleText;
    private Phone phone;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.phone = PhoneLab.get(getActivity()).getPhone((UUID) getActivity().getIntent().getSerializableExtra(PhoneActivity.EXTRA_PHONE_ID));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.phone_fragment, container, false);
        ImageView imagePhone = (ImageView) view.findViewById(R.id.image_fragment);
        TextView descriptionText = (TextView) view.findViewById(R.id.description_fragment);
        TextView titleText = (TextView) view.findViewById(R.id.text_fragment);
        titleText.setText(this.phone.getPhoneName());
        imagePhone.setImageResource(this.phone.getDrawablePhoto());
        String buffer = "";
        for (String str : this.phone.getDescription()) {
            buffer = buffer + str + "\n";
        }
        descriptionText.setText(buffer);
        return view;
    }
}
