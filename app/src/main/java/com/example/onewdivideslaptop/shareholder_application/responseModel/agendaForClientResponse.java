package com.example.onewdivideslaptop.shareholder_application.responseModel;

public class agendaForClientResponse {
    String id;
    String agenda_no;
    String title;
    String detail;
    String full_title;
    String end_polling;

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getAgenda_no() {
        return agenda_no;
    }

    public String getDetail() {
        return detail;
    }

    public String getFull_title() {
        return full_title;
    }

    public String getEndPolling(){
        return end_polling;
    }
}
