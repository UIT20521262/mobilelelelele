package edu.poly.lab03_2;

public class SinhVien {
    String masinhvien;
    String tensinhvien;
    String gioitinh;
    String ngaysinh;

    public SinhVien(String masinhvien, String tensinhvien, String gioitinh, String ngaysinh) {
        this.masinhvien = masinhvien;
        this.tensinhvien = tensinhvien;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
    }

    public String getMasinhvien() {
        return masinhvien;
    }

    public void setMasinhvien(String masinhvien) {
        this.masinhvien = masinhvien;
    }

    public String getTensinhvien() {
        return tensinhvien;
    }

    public void setTensinhvien(String tensinhvien) {
        this.tensinhvien = tensinhvien;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }
}
