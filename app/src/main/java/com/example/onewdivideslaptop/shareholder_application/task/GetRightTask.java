package com.example.onewdivideslaptop.shareholder_application.task;

import android.util.Pair;

import com.example.onewdivideslaptop.shareholder_application.AppUtility;
import com.example.onewdivideslaptop.shareholder_application.responseModel.checkRemainRightResponse;
import com.example.onewdivideslaptop.shareholder_application.responseModel.voteResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetRightTask extends Task {

    private List<String> holders;
    private int index = 0;
    private Map<String,ArrayList<String>> immd;
    private Runnable onSucess;

    public GetRightTask(){

    }

    public GetRightTask(List<String> holders, Runnable onSucess){
        this.holders = holders;
        this.onSucess = onSucess;
        immd = new TreeMap<>();
    }

    public GetRightTask(String holder, Runnable onSucess){
        this.holders = new ArrayList<>();
        holders.add(holder);
        this.onSucess = onSucess;
        immd = new TreeMap<>();
    }

    public void reset(){
        index = 0;
        immd.clear();
    }

    public void perform(){
        if(index==holders.size()){
            exportResult();
            onSucess.run();
        }else{
            Call<List<checkRemainRightResponse>> call = AppUtility.client.checkRemainRight(AppUtility.token,holders.get(index));
            call.enqueue(new Callback<List<checkRemainRightResponse>>() {
                @Override
                public void onResponse(Call<List<checkRemainRightResponse>> call, Response<List<checkRemainRightResponse>> response) {
                    for(int i=0;i<response.body().size();++i){
                        String agenda_id = response.body().get(i).getAgenda_no();
                        if(!immd.containsKey(agenda_id)){
                            immd.put(agenda_id,new ArrayList<String>());
                        }
                        immd.get(agenda_id).add(holders.get(index));
                    }
                    ++index;
                    perform();
                }

                @Override
                public void onFailure(Call<List<checkRemainRightResponse>> call, Throwable t) {
                    reset();
                    perform();
                }
            });
        }
    }

    public void exportResult(){
        ArrayList<Pair<String,ArrayList<voteResponse>>> result = new ArrayList<>();
        for(String agenda : immd.keySet()){
            ArrayList<voteResponse> holders = new ArrayList<>();
            for(String holder : immd.get(agenda)){
                holders.add(new voteResponse(holder));
            }
            result.add(new Pair<>(agenda, holders));
        }
        AppUtility.getTeskResult = result;
    }

}
