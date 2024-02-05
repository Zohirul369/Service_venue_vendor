package com.example.service_venue;

public class ViewModel {
    String vname, price, rating, hour, sname,vimage, distance, vaddress, vphone, cphone, cname, caddress, cimage, worktitle, cemail, vemail;

    public ViewModel(String vname, String price, String rating, String hour, String sname, String vimage, String distance, String vaddress, String vphone, String cphone, String cname, String caddress, String cimage, String worktitle, String cemail, String vemail) {
        this.vname = vname;
        this.price = price;
        this.rating = rating;
        this.hour = hour;
        this.sname = sname;
        this.vimage = vimage;
        this.distance = distance;
        this.vaddress = vaddress;
        this.vphone = vphone;
        this.cphone = cphone;
        this.cname = cname;
        this.caddress = caddress;
        this.cimage = cimage;
        this.worktitle = worktitle;
        this.cemail = cemail;
        this.vemail = vemail;
    }

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getVimage() {
        return vimage;
    }

    public void setVimage(String vimage) {
        this.vimage = vimage;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getVaddress() {
        return vaddress;
    }

    public void setVaddress(String vaddress) {
        this.vaddress = vaddress;
    }

    public String getVphone() {
        return vphone;
    }

    public void setVphone(String vphone) {
        this.vphone = vphone;
    }

    public String getCphone() {
        return cphone;
    }

    public void setCphone(String cphone) {
        this.cphone = cphone;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCaddress() {
        return caddress;
    }

    public void setCaddress(String caddress) {
        this.caddress = caddress;
    }

    public String getCimage() {
        return cimage;
    }

    public void setCimage(String cimage) {
        this.cimage = cimage;
    }

    public String getWorktitle() {
        return worktitle;
    }

    public void setWorktitle(String worktitle) {
        this.worktitle = worktitle;
    }

    public String getCemail() {
        return cemail;
    }

    public void setCemail(String cemail) {
        this.cemail = cemail;
    }

    public String getVemail() {
        return vemail;
    }

    public void setVemail(String vemail) {
        this.vemail = vemail;
    }
}
