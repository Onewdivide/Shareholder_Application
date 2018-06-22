package com.example.onewdivideslaptop.shareholder_application;

import android.content.Context;
import android.widget.Toast;

import com.example.onewdivideslaptop.shareholder_application.responseModel.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppUtility {
    private static final String baseURL = "http://192.168.137.1:3137/";
    public static shareHolderClient client;

    private static Context context;
    public static Context mainPage;
    private static int delegate_id;
    private static String th_name, en_name;
    public static int active_agenda;
    public static final int AGENDA_ALL = -1;
    public static String active_vote_type;
    public static final String VOTE_AGREE = "yes";
    public static final String VOTE_DISAGREE = "no";
    public static final String VOTE_NOCOMMENT = "nocomment";
    public static String[] agenda_titles, agenda_full_titles, agenda_descriptions;
    public static int[] agenda_ids;

    static{
        client = (new Retrofit.Builder()).baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create()).build().create(shareHolderClient.class);
    }

    public static void focus(Context context){
        AppUtility.context = context;
    }

    public static Context getCurrentContext(){
        return context;
    }

    public static void login(final String delegate_id,final Runnable callback){
        AppUtility.delegate_id = Integer.parseInt(delegate_id);
        AppUtility.th_name = null;
        AppUtility.en_name = null;
        Call<List<getNameResponse>> call = client.getName(AppUtility.delegate_id);
        call.enqueue(new Callback<List<getNameResponse>>() {
            @Override
            public void onResponse(Call<List<getNameResponse>> call, Response<List<getNameResponse>> response) {
                if(response.body().size()==0) {
                    Toast.makeText(context,"Serial number not found.",Toast.LENGTH_SHORT).show();
                }else{
                    getNameResponse delegate = response.body().get(0);
                    AppUtility.th_name = delegate.getDelegate_nameth()+" "+delegate.getDelegate_surnameth();
                    AppUtility.en_name = delegate.getDelegate_nameeng()+" "+delegate.getDelegate_surnameeng();
                    callback.run();
                }
            }

            @Override
            public void onFailure(Call<List<getNameResponse>> call, Throwable t) {
                Toast.makeText(context,"Fail to send a request.",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static int getDelegateId(){
        return delegate_id;
    }

    public static String getDelegateThaiName(){
        return th_name;
    }

    public static String getDelegateEngName(){
        return en_name;
    }

    public static void fetchAgenda(final Runnable callback){
        Call<List<agendaForClientResponse>> call = client.getAllagenda();
        call.enqueue(new Callback<List<agendaForClientResponse>>() {
            @Override
            public void onResponse(Call<List<agendaForClientResponse>> call, Response<List<agendaForClientResponse>> response) {
                agenda_ids = new int[response.body().size()];
                agenda_titles = new String[response.body().size()];
                agenda_full_titles = new String[response.body().size()];
                agenda_descriptions = new String[response.body().size()];
                for(int i=0;i<response.body().size();++i){
                    agendaForClientResponse agenda = response.body().get(i);
                    agenda_ids[i] = Integer.parseInt(agenda.getId());
                    agenda_titles[i] = agenda.getTitle();
                    agenda_full_titles[i] = agenda.getFull_title();
                    agenda_descriptions[i] = agenda.getDetail();
                }
                callback.run();
            }

            @Override
            public void onFailure(Call<List<agendaForClientResponse>> call, Throwable t) {
                Toast.makeText(context,"Fail to send a request.",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
