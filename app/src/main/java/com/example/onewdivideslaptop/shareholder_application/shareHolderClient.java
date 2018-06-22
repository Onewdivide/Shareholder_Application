package com.example.onewdivideslaptop.shareholder_application;

import com.example.onewdivideslaptop.shareholder_application.responseModel.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface shareHolderClient {

    @GET("/getName/{delegate_Id}")
    Call<List<getNameResponse>> getName(@Path("delegate_Id") int delegate_Id);

    @GET("/checkAuthorityForVoteAll/{delegate_Id}")
    Call<List<checkAuthorityForVoteAllResponse>> checkAuthorityForVoteAll(@Path("delegate_Id") int delegate_id);

    @PATCH("voteAll/{voteChoice}")
    Call<String> voteAll(@Path("voteChoice") String voteChoice, @Body List<voteAllResponse> voteAllBody);

    @GET("/agendaForClient")
    Call<List<agendaForClientResponse>> getAllagenda();

    @GET("/agendaForClient/{agendaId}")
    Call<List<agendaForClientResponse>> getAgenda(@Path("agendaId") int agendaId);

    @GET("/checkAuthorityForVote/{agendaId}/{delegate_ID}")
    Call<List<checkAuthorityForVoteAgendaResponse>> checkAuthorityForVote(@Path("agendaId")int agendaId, @Path("delegate_ID") int delegate_ID);

    @PATCH("/vote/{voteChoice}/{agenda_Id}")
    Call<String> vote(@Path("voteChoice") String voteChoice, @Path("agenda_Id") int agenda_Id, @Body List<voteResponse> voteBody);
}
