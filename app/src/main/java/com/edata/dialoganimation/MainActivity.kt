package com.edata.dialoganimation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.edata.dialoganimation.databinding.ActivityMainBinding

private fun MainActivity.showWebViewDialog(htmlContent: String) {}

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(/* toolbar = */ binding.toolbar)

        binding.btnBastir.setOnClickListener {
            val htmlContent = """
                <html>
                <head>
                    <style>
                        body {
                            font-family: sans-serif;
                            background-color: #E6CCF5;
                            padding: 20px;
                            text-align: center;
                        }
                        h1 {
                            color: #4B0082;
                        }
                        p {
                            color: #333333;
                        }

                        @keyframes slideIn {
                            from { transform: translateY(-100%); opacity: 0; }
                            to { transform: translateY(0); opacity: 1; }
                        }
                        body {
                            animation: slideIn 1s ease-out;
                        }
                    </style>
                </head>
                <body>
                    <h1>Merhaba Propay</h1>
                    <p>Ekranda yazı yukarıdan kayarak çıktı.</p>
                </body>
                </html>
            """.trimIndent()
            showWebViewDialog(htmlContent)
        }

        fun showWebViewDialog(html: String) {
            val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_webview, null)
            val webView = dialogView.findViewById<WebView>(R.id.webView)

            webView.webViewClient = WebViewClient()
            webView.settings.javaScriptEnabled = true
            webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null)

            val dialog = AlertDialog.Builder(this, R.style.SlideDialogTheme)
                .setView(dialogView)
                .setCancelable(true)
                .create()

            dialog.show()
        }
    }
}
