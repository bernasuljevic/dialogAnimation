package com.edata.dialoganimation

import android.os.Bundle
import android.view.LayoutInflater
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.edata.dialoganimation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        // Bastır butonu tıklama
        setOnClickListener {
            Toast.makeText(this, "Bastır butonuna tıklandı!", Toast.LENGTH_SHORT).show()
        }

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        // FAB tıklama - WebView Dialog göster
        binding.fab.setOnClickListener {
            val htmlContent = """
                <html>
                <head>
                    <style>
                        body { font-family: sans-serif; background-color: #DDA0DD; padding: 20px; }
                        h1 { color: ##4B0082; }
                    </style>
                </head>
                <body>
                    <h1>Merhaba Propay</h1>
                    <p>Ekranda yazı çıktı.</p>
                    <button onclick="alert('Butona tıklandı!')">Tıkla</button>
                </body>
                </html>
            """.trimIndent()

            showWebViewDialog(htmlContent)
        }
    }

    private fun showWebViewDialog(html: String) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_webview, null)
        val webView = dialogView.findViewById<WebView>(R.id.webView)

        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true

        // HTML içeriğini WebView'e yükle
        webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null)

        AlertDialog.Builder(this)
            .setTitle("WebView Dialog")
            .setView(dialogView)
            .setPositiveButton("Kapat", null)
            .show()
    }

    override fun onCreateOptionsMenu(menu: android.view.Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: android.view.MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}

private fun setOnClickListener(function: () -> Unit) {


}
