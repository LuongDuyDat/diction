package com.example.diction.API;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FreeTTSAPI {
    public static void speech(String text) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice symtheticVoice = voiceManager.getVoice("kevin16");
        symtheticVoice.allocate();
        symtheticVoice.speak(text);
        symtheticVoice.deallocate();
    }
}
