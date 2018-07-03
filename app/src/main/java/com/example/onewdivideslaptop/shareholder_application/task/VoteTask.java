package com.example.onewdivideslaptop.shareholder_application.task;

import android.util.Pair;
import android.widget.Toast;

import com.example.onewdivideslaptop.shareholder_application.AppUtility;
import com.example.onewdivideslaptop.shareholder_application.responseModel.voteResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VoteTask extends Task {

    private int index = 0;
    private boolean error = false;
    private ArrayList<Pair<String,ArrayList<voteResponse>>> tasks;
    private Runnable onSuccess;

    public VoteTask(ArrayList<Pair<String,ArrayList<voteResponse>>> tasks, Runnable onSuccess){
        this.tasks = tasks;
        this.onSuccess = onSuccess;
    }

    public void perform(){
        if(index==tasks.size()){
            if(error){
                informTaskIncompleteness();
            }else{
                onSuccess.run();
            }
        }else{
            Call<String> call = AppUtility.client.vote(
                    AppUtility.token,
                    AppUtility.active_vote_type,
                    Integer.parseInt(tasks.get(index).first),
                    tasks.get(index++).second
            );
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    perform();
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    error = true;
                }
            });
        }
    }

    public static void informTaskIncompleteness(){
        Toast.makeText(AppUtility.getCurrentContext(), "เกิดข้อผิดพลาดในการออกเสียงบางสิทธิ์\nกรุณาลองใหม่อีกครั้ง", Toast.LENGTH_LONG).show();
    }
}
