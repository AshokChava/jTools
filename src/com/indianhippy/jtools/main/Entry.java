/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.indianhippy.jtools.main;

import javax.swing.SwingUtilities;
import com.indianhippy.jtools.clip.scheduler.ClipAddingScheduler;
import com.indianhippy.jtools.jclip.ui.JClipPanel;
import com.indianhippy.jtools.ui.JToolMain;

/**
 *
 * @author achava
 */
public class Entry  {
    
    
    public static void main(String[] args) {
         SwingUtilities.invokeLater(new Runnable() {

        @Override
        public void run() {
            JToolMain main=new JToolMain();
            main.pack();
            JClipPanel frame = new JClipPanel();
           ClipAddingScheduler cSche=new ClipAddingScheduler(frame,2);
        }
    });
    }
    
}
