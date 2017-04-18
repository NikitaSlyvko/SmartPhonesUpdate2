package com.example.nikita.smartphonesupdate;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PhoneLab extends Activity {
    private static PhoneLab phoneLab;
    private Context appContext;
    private List<String[]> descriptions;
    private int[] images;
    private String[] namesPhone;
    private List<Phone> phones;

    public static PhoneLab get(Context context) {
        if (phoneLab == null) {
            phoneLab = new PhoneLab(context);
        }
        return phoneLab;
    }

    private void setDescriptions(Resources resources) {
        this.descriptions.add(resources.getStringArray(R.array.google_descriptions));
        this.descriptions.add(resources.getStringArray(R.array.htc_descriptions));
        this.descriptions.add(resources.getStringArray(R.array.iphone_descriptions));
        this.descriptions.add(resources.getStringArray(R.array.iphoneplus_descriptions));
        this.descriptions.add(resources.getStringArray(R.array.lgg5_descriptions));
        this.descriptions.add(resources.getStringArray(R.array.motorola_descriptions));
        this.descriptions.add(resources.getStringArray(R.array.motoz_descriptions));
        this.descriptions.add(resources.getStringArray(R.array.oneplus_descriptions));
        this.descriptions.add(resources.getStringArray(R.array.samsung_descriptions));
        this.descriptions.add(resources.getStringArray(R.array.samsungedge_descriptions));
    }

    private void initResources(Context context) {
        Resources resources = context.getResources();
        this.namesPhone = resources.getStringArray(R.array.smartphones);
        this.images = new int[this.namesPhone.length];
        this.descriptions = new ArrayList();
        TypedArray imagesArray = resources.obtainTypedArray(R.array.image_phone);
        for (int i = 0; i < this.namesPhone.length; i++) this.images[i] = imagesArray.getResourceId(i, -1);
        imagesArray.recycle();
        setDescriptions(resources);
    }

    private PhoneLab(Context context) {
        this.appContext = context;
        this.phones = new ArrayList();
        initResources(this.appContext);
        for (int i = 0; i < this.namesPhone.length; i++) {
            Phone phone = new Phone();
            phone.setPhoneName(this.namesPhone[i]);
            phone.setDrawablePhoto(this.images[i]);
            phone.setDescription((String[]) this.descriptions.get(i));
            this.phones.add(phone);
        }
    }

    public List<Phone> getPhones() {
        return this.phones;
    }

    public Phone getPhone(UUID id) {
        for (Phone phone : this.phones) {
            if (phone.getId().equals(id)) {
                return phone;
            }
        }
        return null;
    }
}

