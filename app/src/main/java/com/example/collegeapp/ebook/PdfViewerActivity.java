package com.example.collegeapp.ebook;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.collegeapp.R;

public class PdfViewerActivity extends AppCompatActivity {

    private String url;
 //   private PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_viewer);

        url=getIntent().getStringExtra("pdfUrl");
//
//        PDFView pdfView = findViewById(R.id.pdfView);
//        pdfView.fromUri(Uri.parse(url))
//                .enableSwipe(true)
//                .swipeHorizontal(false)
//                .enableDoubletap(true)
//                .defaultPage(0)
//                .load();


    }

}