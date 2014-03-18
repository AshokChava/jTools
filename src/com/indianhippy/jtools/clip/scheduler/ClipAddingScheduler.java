package com.indianhippy.jtools.clip.scheduler;
import java.util.Timer;
import java.util.TimerTask;
import com.indianhippy.jtools.jclip.ui.JClipPanel;

public class ClipAddingScheduler {
    Timer timer;
    JClipPanel main=null;
    public ClipAddingScheduler(JClipPanel main,int seconds) {
        this.main=main;
        timer = new Timer();  //At this line a new Thread will be created
        timer.scheduleAtFixedRate(new ClipAddTask(), seconds*1000,seconds*1000); //delay in milliseconds
    }

    class ClipAddTask extends TimerTask {

        @Override
        public void run() {
            main.addFromClipBoard();
            //timer..cancel();
            
        }
    }

    
}


