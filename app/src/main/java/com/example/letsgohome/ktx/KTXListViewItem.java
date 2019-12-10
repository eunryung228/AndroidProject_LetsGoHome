package com.example.letsgohome.ktx;

public class KTXListViewItem
{
    private String vehicle; // 차량 정보
    private String depTime; // 출발 시간
    private String arrTime; // 도착 시간
    private String charge; // 금액

    public String getVehicle() {
        return vehicle;
    }
    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getDepTime() {
        return depTime;
    }
    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public String getArrTime() {
        return arrTime;
    }
    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }

    public String getCharge() {
        return charge;
    }
    public void setCharge(String charge) {
        this.charge = charge;
    }
}