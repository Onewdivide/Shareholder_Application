package com.example.onewdivideslaptop.shareholder_application.task;

import com.example.onewdivideslaptop.shareholder_application.AppUtility;
import com.example.onewdivideslaptop.shareholder_application.Authority;

public class UpdateAgendaStatusTask extends Task {

    private Runnable onSuccess;
    private int tmp_agenda_id;

    public UpdateAgendaStatusTask(Runnable onSuccess){
        this.onSuccess = onSuccess;
    }

    public void perform(){
        tmp_agenda_id = AppUtility.active_agenda;
        perform(0);
    }

    private void perform(final int index){
        if(index==AppUtility.agenda_ids.length){
            AppUtility.active_agenda = tmp_agenda_id;
            Authority.requireUpdate();
            onSuccess.run();
        }else{
            AppUtility.active_agenda = AppUtility.agenda_ids[index];
            Authority.requireUpdate();
            Authority.getAuthorities(new Runnable() {
                @Override
                public void run() {
                    AppUtility.agenda_completed[index] = !Authority.anyAuthAvailable();
                    perform(index+1);
                }
            });
        }
    }
}
