package edu.poly.lab03_2;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterCustom extends BaseAdapter {
    ArrayList<SinhVien> sinhvien;
    public AdapterCustom(ArrayList<SinhVien> sinhvien) {
        this.sinhvien = sinhvien;
    }
    @Override
    public int getCount() {
        return sinhvien.size();
    }

    @Override
    public Object getItem(int i) {
        return sinhvien.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1;
        if (view == null) {
            view1=View.inflate(viewGroup.getContext(), R.layout.custom_listview, null);
        }else {
            view1=view;
        }
        SinhVien sinhVien = sinhvien.get(i);
        TextView tvsinhvien;
        tvsinhvien = view1.findViewById(R.id.tvsinhvien);
        tvsinhvien.setText(sinhVien.masinhvien + "-" + sinhVien.tensinhvien + "-" + sinhVien.gioitinh + "-" + sinhVien.ngaysinh);
        return view1;
    }
}
