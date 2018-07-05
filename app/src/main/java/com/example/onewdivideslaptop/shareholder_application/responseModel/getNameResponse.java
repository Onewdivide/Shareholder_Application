package com.example.onewdivideslaptop.shareholder_application.responseModel;

public class getNameResponse {

    String id,title_th,f_name_th,l_name_th,title_eng,f_name_eng,l_name_eng,token;

    public String getDelegate_id() {
        return id;
    }

    public String getDelegate_titleth(){
        return title_th;
    }

    public String getDelegate_nameth() {
        return f_name_th;
    }

    public String getDelegate_surnameth() {
        return l_name_th;
    }

    public String getDelegate_titleeng(){
        return title_eng;
    }

    public String getDelegate_nameeng() {
        return f_name_eng;
    }

    public String getDelegate_surnameeng() {
        return l_name_eng;
    }

    public String getToken(){ return token; }
}
