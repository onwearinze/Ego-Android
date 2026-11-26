package com.ego.learning;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import java.util.Locale;
import java.util.Set;

public class MainActivity extends Activity {
    private WebView webView;
    private TextToSpeech tts;
    private volatile boolean ttsReady = false;

    public class EgoVoice {
        @JavascriptInterface
        public void speak(final String text) {
            runOnUiThread(() -> {
                if (ttsReady && tts != null && text != null && !text.trim().isEmpty()) {
                    tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "EGO_SPEECH");
                }
            });
        }
        @JavascriptInterface
        public void stop() {
            runOnUiThread(() -> { if (tts != null) tts.stop(); });
        }
        @JavascriptInterface
        public boolean speechAvailable() { return ttsReady; }
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.rgb(98,72,168));
        getWindow().setNavigationBarColor(Color.rgb(73,53,138));
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                boolean offlineVoiceFound = false;
                // Prefer an English voice already installed on the device; never require network speech.
                try {
                    Set<Voice> voices = tts.getVoices();
                    if (voices != null) {
                        for (Voice voice : voices) {
                            Locale locale = voice.getLocale();
                            if (locale != null && "en".equals(locale.getLanguage()) && !voice.isNetworkConnectionRequired()) {
                                tts.setVoice(voice);
                                offlineVoiceFound = true;
                                break;
                            }
                        }
                    }
                } catch (Exception ignored) { }
                if (offlineVoiceFound) tts.setLanguage(tts.getVoice().getLocale());
                tts.setSpeechRate(0.84f);
                tts.setPitch(1.06f);
                ttsReady = offlineVoiceFound;
            }
        });
        webView = new WebView(this);
        webView.setBackgroundColor(Color.rgb(255,250,246));
        WebSettings s = webView.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setAllowFileAccess(true);
        s.setAllowContentAccess(false);
        s.setAllowFileAccessFromFileURLs(false);
        s.setAllowUniversalAccessFromFileURLs(false);
        s.setMediaPlaybackRequiresUserGesture(true);
        s.setSupportZoom(false);
        webView.addJavascriptInterface(new EgoVoice(), "EgoNative");
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient());
        setContentView(webView);
        if (savedInstanceState == null) webView.loadUrl("file:///android_asset/index.html");
        else webView.restoreState(savedInstanceState);
    }

    @Override protected void onSaveInstanceState(Bundle out) {
        super.onSaveInstanceState(out);
        if (webView != null) webView.saveState(out);
    }
    @Override public void onBackPressed() {
        if (webView != null && webView.canGoBack()) webView.goBack();
        else super.onBackPressed();
    }
    @Override protected void onDestroy() {
        if (webView != null) { webView.destroy(); webView = null; }
        if (tts != null) { tts.stop(); tts.shutdown(); tts = null; }
        super.onDestroy();
    }
}
