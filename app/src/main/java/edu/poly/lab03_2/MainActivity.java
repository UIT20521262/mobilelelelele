package edu.poly.lab03_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView tvngaysinh;
    String gioitinh;
    String mssv;
    int mYear;
    int mMonth;
    int mDay;
    String date_time = "";
    DB db;
    ArrayList<SinhVien> sinhvien;
    AdapterCustom adaptercustom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText edmssv, edtensv;
        Button btnthem, btnngaysinh, btnsua;
        RadioGroup rdgroup;
        ListView lv;
        edmssv=findViewById(R.id.edmssv);
        edtensv=findViewById(R.id.edtensv);
        btnngaysinh=findViewById(R.id.btnngaysinh);
        btnthem=findViewById(R.id.btnthem);
        btnsua=findViewById(R.id.btnsua);
        rdgroup=findViewById(R.id.rdgroup);
        lv=findViewById(R.id.lv);
        tvngaysinh=findViewById(R.id.tvngaysinh);
        //lay ngay sinh
        btnngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePicker();
            }
        });
        //lay gia tri gioi tinh
        rdgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(i==R.id.rdnam) {
                    gioitinh="Nam";
                }
                if(i==R.id.rdnu) {
                    gioitinh="Nu";
                }
            }
        });

        db = new DB(MainActivity.this);
        sinhvien = new ArrayList<>();
        adaptercustom = new AdapterCustom(sinhvien);
        lv.setAdapter(adaptercustom);

        //tao csdl
        db.thucthisql("create table if not EXISTS sinhvien(\n" +
                "masv varchar(50) primary key,\n" +
                "tensv varchar(50),\n" +
                "ngaysinh varchar(20),\n" +
                "ngaysinh varchar(50),\n" +
                ")");
        btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tensv, mssv, ns, gt;
                tensv=edtensv.getText().toString();
                mssv=edmssv.getText().toString();
                ns=tvngaysinh.getText().toString();
                gt=gioitinh;
                if(TextUtils.isEmpty(tensv)){
                    Toast.makeText(MainActivity.this, "khong bo trong", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(mssv)){
                    Toast.makeText(MainActivity.this, "khong bo trong", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(ns)){
                    Toast.makeText(MainActivity.this, "khong bo trong", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(gt)){
                    Toast.makeText(MainActivity.this, "khong bo trong", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(db.thucthisql("insert into sinhvien(mssv, tensv, gioitinh, ngaysinh) values('"+mssv+"', '"+tensv+"','"+gt+"', '"+ns+"')")==1) {
                    Toast.makeText(MainActivity.this, "Them thanh cong", Toast.LENGTH_SHORT).show();

                }
                laydulieu();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mssv=sinhvien.get(i).masinhvien;
                edmssv.setText(sinhvien.get(i).masinhvien);
                edtensv.setText(sinhvien.get(i).tensinhvien);
                tvngaysinh.setText(sinhvien.get(i).ngaysinh);

                //
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Thong bao!");
                dialog.setMessage("Ban co muon xoa?");

                dialog.setNegativeButton("Khong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        return;
                    }
                });
                dialog.setPositiveButton("Co", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(db.thucthisql("delete from sinhvien\n" +
                                "where mssv='"+mssv+"'")==1){
                            Toast.makeText(MainActivity.this, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                        }
                        laydulieu();
                    }
                });
                dialog.show();
            }
        });
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tensv, mssv, ns, gt;
                tensv=edtensv.getText().toString();
                mssv=edmssv.getText().toString();
                ns=tvngaysinh.getText().toString();
                gt=gioitinh;
                if(db.thucthisql("Update sinhvien\n" +
                        "set mssv='"+mssv+"',\n" +
                        "tensv='"+tensv+"',\n" +
                        "gioitinh='"+gt+"',\n " +
                        "ngaysinh='"+ns+"',\n" +
                        "where mssv='"+mssv+"'")==1) {
                    Toast.makeText(MainActivity.this, "sua thanh cong", Toast.LENGTH_SHORT).show();
                }
                laydulieu();

            }
        });
        laydulieu();

    }

    private void datePicker(){
        //Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                date_time = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;
                tvngaysinh.setText(date_time);
            }
        }, mYear, mMonth, mDay);
        datePickerDialog.show();

    }

    public void laydulieu() {
        Cursor contro=db.laydulieu("select *from sinhvien");
        contro.moveToFirst();
        sinhvien.clear();
        while (!contro.isAfterLast()) {
            String mssv=contro.getString(0);
            String tensv=contro.getString(1);
            String gioitinh=contro.getString(2);
            String ngaysinh=contro.getString(3);
            sinhvien.add(new SinhVien(mssv, tensv, gioitinh, ngaysinh));
            contro.moveToNext();
        }
        adaptercustom.notifyDataSetChanged();
        contro.close();
    }
}