package com.ego.learning;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.ConsoleMessage;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.webkit.WebViewAssetLoader;
import java.util.Locale;
import java.util.Set;

public class MainActivity extends Activity {
    private static final String TAG = "EGO-WebView";
    private static final long SPLASH_DURATION_MS = 2000L;
    private static final String APP_URL = "https://appassets.androidplatform.net/assets/index.html";
    private WebView webView;
    private TextToSpeech tts;
    private volatile boolean ttsReady = false;
    private volatile String pendingSpeech = null;
    private ToneGenerator successTone;
    private FrameLayout rootLayout;
    private ImageView splashView;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private long launchTime;
    private WebViewAssetLoader assetLoader;

    private void speakNative(final String text, final float rate) {
        if (text == null || text.trim().isEmpty()) return;
        runOnUiThread(() -> {
            if (tts == null || !ttsReady) { pendingSpeech = text; return; }
            tts.setSpeechRate(rate);
            tts.setPitch(1.0f);
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "EGO_SPEECH");
        });
    }

    public class EgoVoice {
        @JavascriptInterface public void speak(final String text) { speakNative(text, 0.86f); }
        @JavascriptInterface public void speakSlow(final String text) { speakNative(text, 0.66f); }
        @JavascriptInterface public void stop() {
            pendingSpeech = null;
            runOnUiThread(() -> { if (tts != null) tts.stop(); });
        }
        @JavascriptInterface public boolean speechAvailable() { return ttsReady; }
        @JavascriptInterface public void playSuccess() {
            runOnUiThread(() -> {
                try {
                    if (successTone == null) successTone = new ToneGenerator(AudioManager.STREAM_MUSIC, 55);
                    successTone.startTone(ToneGenerator.TONE_PROP_ACK, 170);
                } catch (Exception e) { Log.w(TAG, "System success tone unavailable", e); }
            });
        }
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        launchTime = SystemClock.elapsedRealtime();
        super.onCreate(savedInstanceState);
        // Android 15 edge-to-edge: explicitly inset the app content so controls remain reachable.
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        getWindow().setStatusBarColor(Color.rgb(98,72,168));
        getWindow().setNavigationBarColor(Color.rgb(73,53,138));
        tts = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                try {
                    Voice selected = null;
                    Set<Voice> voices = tts.getVoices();
                    if (voices != null) {
                        for (Voice voice : voices) {
                            Locale locale = voice.getLocale();
                            if (locale != null && "en".equals(locale.getLanguage())
                                    && "GB".equalsIgnoreCase(locale.getCountry())
                                    && !voice.isNetworkConnectionRequired()) { selected = voice; break; }
                        }
                        if (selected == null) for (Voice voice : voices) {
                            Locale locale = voice.getLocale();
                            if (locale != null && "en".equals(locale.getLanguage())
                                    && "US".equalsIgnoreCase(locale.getCountry())
                                    && !voice.isNetworkConnectionRequired()) { selected = voice; break; }
                        }
                        if (selected == null) for (Voice voice : voices) {
                            Locale locale = voice.getLocale();
                            if (locale != null && "en".equals(locale.getLanguage())
                                    && !voice.isNetworkConnectionRequired()) { selected = voice; break; }
                        }
                    }
                    if (selected != null) {
                        int languageStatus = tts.setLanguage(selected.getLocale());
                        tts.setVoice(selected);
                        ttsReady = languageStatus >= TextToSpeech.LANG_AVAILABLE;
                    } else {
                        ttsReady = false;
                        Log.w(TAG, "No offline English TTS voice is installed. Install one in Android Text-to-Speech settings.");
                    }
                    tts.setSpeechRate(0.86f);
                    tts.setPitch(1.0f);
                    if (ttsReady && pendingSpeech != null) {
                        String queued = pendingSpeech; pendingSpeech = null;
                        speakNative(queued, 0.86f);
                    }
                } catch (Exception e) {
                    ttsReady = false;
                    Log.e(TAG, "Could not initialise offline English speech", e);
                }
            } else {
                ttsReady = false;
                Log.e(TAG, "Android TextToSpeech initialisation failed: " + status);
            }
        });

        assetLoader = new WebViewAssetLoader.Builder()
                .addPathHandler("/assets/", new WebViewAssetLoader.AssetsPathHandler(this))
                .build();
        rootLayout = new FrameLayout(this);
        rootLayout.setBackgroundColor(Color.rgb(255,250,246));
        ViewCompat.setOnApplyWindowInsetsListener(rootLayout, (view, windowInsets) -> {
            Insets bars = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout());
            view.setPadding(bars.left, bars.top, bars.right, bars.bottom);
            return windowInsets;
        });

        webView = new WebView(this);
        webView.setBackgroundColor(Color.rgb(255,250,246));
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setAllowFileAccess(false);
        settings.setAllowContentAccess(false);
        settings.setMediaPlaybackRequiresUserGesture(true);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setDisplayZoomControls(false);
        settings.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW);
        webView.addJavascriptInterface(new EgoVoice(), "EgoNative");
        webView.setWebViewClient(new WebViewClient() {
            @Override public android.webkit.WebResourceResponse shouldInterceptRequest(WebView view, android.webkit.WebResourceRequest request) {
                return assetLoader.shouldInterceptRequest(request.getUrl());
            }
            @Override public boolean shouldOverrideUrlLoading(WebView view, android.webkit.WebResourceRequest request) {
                // EGO is an offline-only application. Keep navigation within its bundled origin.
                return !"appassets.androidplatform.net".equals(request.getUrl().getHost());
            }
            @Override public void onReceivedError(WebView view, android.webkit.WebResourceRequest request, android.webkit.WebResourceError error) {
                if (request.isForMainFrame()) Log.e(TAG, "Main page load failed: " + error.getDescription());
                else Log.e(TAG, "Asset load failed: " + request.getUrl() + " — " + error.getDescription());
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {
            @Override public boolean onConsoleMessage(ConsoleMessage message) {
                String line = message.message() + " (" + message.sourceId() + ":" + message.lineNumber() + ")";
                if (message.messageLevel() == ConsoleMessage.MessageLevel.ERROR) Log.e(TAG, line);
                else Log.d(TAG, line);
                return true;
            }
        });
        rootLayout.addView(webView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));

        splashView = new ImageView(this);
        splashView.setImageResource(R.drawable.ego_splash);
        splashView.setScaleType(ImageView.ScaleType.FIT_XY);
        splashView.setBackgroundColor(Color.rgb(170, 0, 0));
        rootLayout.addView(splashView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        setContentView(rootLayout);
        ViewCompat.requestApplyInsets(rootLayout);

        if (savedInstanceState == null) webView.loadUrl(APP_URL); else webView.restoreState(savedInstanceState);
        long remaining = Math.max(0L, SPLASH_DURATION_MS - (SystemClock.elapsedRealtime() - launchTime));
        mainHandler.postDelayed(() -> {
            if (splashView != null && rootLayout != null) {
                rootLayout.removeView(splashView); splashView = null;
                getWindow().setBackgroundDrawableResource(android.R.color.white);
            }
        }, remaining);
    }

    @Override protected void onSaveInstanceState(Bundle out) { super.onSaveInstanceState(out); if (webView != null) webView.saveState(out); }
    @Override public void onBackPressed() { if (webView != null && webView.canGoBack()) webView.goBack(); else super.onBackPressed(); }
    @Override protected void onDestroy() {
        mainHandler.removeCallbacksAndMessages(null);
        if (webView != null) { webView.stopLoading(); webView.removeJavascriptInterface("EgoNative"); webView.destroy(); webView = null; }
        if (tts != null) { tts.stop(); tts.shutdown(); tts = null; }
        if (successTone != null) { successTone.release(); successTone = null; }
        super.onDestroy();
    }
}
