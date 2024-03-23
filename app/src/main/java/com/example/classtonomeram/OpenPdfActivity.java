
//package com.example.classtonomeram;
//import android.Manifest;
//import android.content.pm.PackageManager;
//import android.os.Bundle;
//import android.webkit.WebChromeClient;
//import android.webkit.WebResourceRequest;
//import android.webkit.WebSettings;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//public class OpenPdfActivity extends AppCompatActivity {
//
//    private WebView webView;
//    private static final int PERMISSION_REQUEST_CODE = 1;
//    private TextView tv;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_open_pdf);
//        tv = findViewById(R.id.tv);
//
//        // Check and request internet permission if not granted
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.INTERNET},
//                    PERMISSION_REQUEST_CODE);
//        } else {
//            initializeWebView();
//        }
//    }
//
//    private void initializeWebView() {
//        webView = findViewById(R.id.pdfWebView);
//
//        // Enable JavaScript in the WebView
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                // Check if the URL is a PDF file
//                if (request.getUrl().toString().endsWith(".pdf")) {
//                    view.loadUrl(request.getUrl().toString());
//                    return true;
//                }
//                return super.shouldOverrideUrlLoading(view, request);
//            }
//        });
//
//        // Retrieve the PDF URL from the intent
//        String pdfUrl = getIntent().getStringExtra("pdfUrl");
//        tv.setText(pdfUrl);
//
//        if (pdfUrl != null) {
//            // Load the PDF URL in the WebView
//            webView.setWebChromeClient(new WebChromeClient());
//            webView.loadUrl(pdfUrl);
//        }
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == PERMISSION_REQUEST_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                initializeWebView();
//            } else {
//                // Handle permission denied, e.g., show an error message
//                Toast.makeText(this, "The PDF is not opening", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
////
////import android.Manifest;
////import android.content.pm.PackageManager;
////import android.os.Bundle;
////import android.webkit.WebChromeClient;
////import android.webkit.WebResourceRequest;
////import android.webkit.WebSettings;
////import android.webkit.WebView;
////import android.webkit.WebViewClient;
////import android.widget.TextView;
////import android.widget.Toast;
////
////import androidx.appcompat.app.AppCompatActivity;
////import androidx.core.app.ActivityCompat;
////import androidx.core.content.ContextCompat;
////
////public class OpenPdfActivity extends AppCompatActivity {
////
////    private WebView webView;
////    private static final int PERMISSION_REQUEST_CODE = 1;
////    private TextView tv;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_open_pdf);
////        tv=findViewById(R.id.tv);
////        // Check and request internet permission if not granted
////        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
////                != PackageManager.PERMISSION_GRANTED) {
////            ActivityCompat.requestPermissions(this,
////                    new String[]{Manifest.permission.INTERNET},
////                    PERMISSION_REQUEST_CODE);
////        } else {
////            initializeWebView();
////        }
////    }
////
////    private void initializeWebView() {
////        webView = findViewById(R.id.pdfWebView);
////
////        // Enable JavaScript in the WebView
////        WebSettings webSettings = webView.getSettings();
////        webSettings.setJavaScriptEnabled(true);
////
////        webView.setWebViewClient(new WebViewClient() {
////            @Override
////            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
////                // Check if the URL is a PDF file
////                if (request.getUrl().toString().endsWith(".pdf")) {
////                    view.loadUrl(request.getUrl().toString());
////                    return true;
////                }
////                return super.shouldOverrideUrlLoading(view, request);
////            }
////        });
////
////        // Retrieve the PDF URL from the intent
////        String pdfUrl = getIntent().getStringExtra("pdfUrl");
////        tv.setText(pdfUrl);
////
////        if (pdfUrl != null) {
////            // Load the PDF URL in the WebView
////            webView.setWebChromeClient(new WebChromeClient());
////            webView.loadUrl(pdfUrl);
////        }
////    }
////
////    @Override
////    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
////        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
////        if (requestCode == PERMISSION_REQUEST_CODE) {
////            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
////                initializeWebView();
////            } else {
////
////                // Handle permission denied, e.g., show an error message
////                Toast.makeText(this, "the pdf is not opening", Toast.LENGTH_SHORT).show();
////             }
////        }
////    }
////}
//
//
//
//
//
//
//
//
//
//
//
//
////package com.example.classtonomeram;
////import android.os.Bundle;
////import android.webkit.WebChromeClient;
////import android.webkit.WebResourceRequest;
////import android.webkit.WebSettings;
////import android.webkit.WebView;
////import android.webkit.WebViewClient;
////
////import androidx.appcompat.app.AppCompatActivity;
////
////public class OpenPdfActivity extends AppCompatActivity {
////
////    private WebView webView;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_open_pdf);
////
////        webView = findViewById(R.id.pdfWebView);
////
////        // Enable JavaScript in the WebView
////        WebSettings webSettings = webView.getSettings();
////        webSettings.setJavaScriptEnabled(true);
////        webView.setWebViewClient(new WebViewClient() {
////            @Override
////            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
////                // Check if the URL is a PDF file
////                if (request.getUrl().toString().endsWith(".pdf")) {
////                    view.loadUrl(request.getUrl().toString());
////                    return true;
////                }
////                return super.shouldOverrideUrlLoading(view, request);
////            }
////        });
////
////
////        // Retrieve the PDF URL from the intent
////        String pdfUrl = getIntent().getStringExtra("pdfUrl");
////
////        if (pdfUrl != null) {
////            // Load the PDF URL in the WebView
////            webView.setWebViewClient(new WebViewClient());
////            webView.setWebChromeClient(new WebChromeClient());
////            webView.loadUrl(pdfUrl);
////        }
////    }
////}
////
////
//
//
//
//
//
//
//
//
//
////package com.example.classtonomeram;
////import android.os.Bundle;
////import android.webkit.WebSettings;
////import android.webkit.WebView;
////import android.webkit.WebViewClient;
////
////import androidx.appcompat.app.AppCompatActivity;
////
////public class OpenPdfActivity extends AppCompatActivity {
////
////    private WebView webView;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_open_pdf);
////
////        webView = findViewById(R.id.pdfWebView);
////
////        // Enable JavaScript in the WebView
////        WebSettings webSettings = webView.getSettings();
////        webSettings.setJavaScriptEnabled(true);
////
////        // Retrieve the PDF URL from the intent
////        String pdfUrl = getIntent().getStringExtra("pdfUrl");
////
////        if (pdfUrl != null) {
////            // Load the PDF URL in the WebView
////            webView.loadUrl("https://docs.google.com/gview?embedded=true&url=" + pdfUrl);
////            webView.setWebViewClient(new WebViewClient());
////        }
////    }
////}
//
//
//
//
//
//
//
//
////
////import android.os.Bundle;
////import android.webkit.WebSettings;
////import android.webkit.WebView;
////import android.webkit.WebViewClient;
////
////import androidx.appcompat.app.AppCompatActivity;
////
////public class OpenPdfActivity extends AppCompatActivity {
////
////    private WebView webView;
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_open_pdf);
////
////        webView = findViewById(R.id.pdfWebView);
////        String pdfUrl = getIntent().getStringExtra("pdfUrl");
////
////        WebSettings webSettings = webView.getSettings();
////        webSettings.setJavaScriptEnabled(true);
////
////        // Enable local storage for PDF support
////        webSettings.setAllowFileAccessFromFileURLs(true);
////        webSettings.setAllowUniversalAccessFromFileURLs(true);
////
////        webView.setWebViewClient(new WebViewClient());
////        webView.loadUrl("https://docs.google.com/viewer?url=" + pdfUrl);
////    }
////}
