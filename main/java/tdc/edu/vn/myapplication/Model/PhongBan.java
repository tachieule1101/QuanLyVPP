package tdc.edu.vn.myapplication.Model;

public class PhongBan {
    String ma, ten;

    @Override
    public String toString() {
        return "PBActivity{" +
                "ma='" + ma + '\'' +
                ", ten='" + ten + '\'' +
                '}';
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

}
