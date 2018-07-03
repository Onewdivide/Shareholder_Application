package com.example.onewdivideslaptop.shareholder_application;

import com.example.onewdivideslaptop.shareholder_application.responseModel.*;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

public interface shareHolderClient {

    @GET("/getName/{delegate_Id}")
    Call<List<getNameResponse>> getName(@Path("delegate_Id") String delegate_Id);

    @GET("/checkAuthorityForVoteAll/{delegate_Id}")
    Call<List<checkAuthorityForVoteAllResponse>> checkAuthorityForVoteAll(@Header("Authorization") String token, @Path("delegate_Id") String delegate_id);

    @PATCH("voteAll/{voteChoice}")
    Call<String> voteAll(@Header("Authorization") String token, @Path("voteChoice") String voteChoice, @Body List<voteAllResponse> voteAllBody);

    @GET("/agendaForClient")
    Call<List<agendaForClientResponse>> getAllagenda(@Header("Authorization") String token);

    @GET("/agendaForClient/{agendaId}")
    Call<List<agendaForClientResponse>> getAgenda(@Header("Authorization") String token, @Path("agendaId") String agendaId);

    @GET("/checkAuthorityForVote/{agendaId}/{delegate_ID}")
    Call<List<checkAuthorityForVoteAgendaResponse>> checkAuthorityForVote(@Header("Authorization") String token, @Path("agendaId")int agendaId, @Path("delegate_ID") String delegate_ID);

    @PATCH("/vote/{voteChoice}/{agenda_Id}")
    Call<String> vote(@Header("Authorization") String token, @Path("voteChoice") String voteChoice, @Path("agenda_Id") int agenda_Id, @Body List<voteResponse> voteBody);

    @GET("/checkRemainRight/{holder_id}")
    Call<List<checkRemainRightResponse>> checkRemainRight(@Header("Authorization") String token, @Path("holder_id") String holder_id);
}
