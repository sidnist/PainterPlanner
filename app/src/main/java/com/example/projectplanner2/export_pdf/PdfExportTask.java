package com.example.projectplanner2.export_pdf;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.AsyncTask;
import android.os.Environment;

import androidx.room.Room;

import com.example.projectplanner2.Room.QuoteDataDao;
import com.example.projectplanner2.Room.QuoteDatabase;
import com.example.projectplanner2.presentation.tabs.model.InsertHomeItemModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PdfExportTask extends AsyncTask<Void, Void, List<InsertHomeItemModel>> {
    private Context context;

    public PdfExportTask(Context context) {
        this.context = context;
    }

    @Override
    protected List<InsertHomeItemModel> doInBackground(Void... voids) {
        // Initialize your Room database
        QuoteDatabase yourDatabase = Room.databaseBuilder(context, QuoteDatabase.class, "quote_data")
                .build();

        // Access the DAO and fetch data
        QuoteDataDao yourDao = yourDatabase.getDao();
        return yourDao.getAllData(); // Replace this with your method to fetch data
    }

    @Override
    protected void onPostExecute(List<InsertHomeItemModel> data) {
        super.onPostExecute(data);
        createPdf(data);
    }

    private void createPdf(List<InsertHomeItemModel> data) {
        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);

        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(12);

        int y = 20; // Initial y position
        for (InsertHomeItemModel entity : data) {
            canvas.drawText("Name: " + entity.getName() ,10, y, paint);
            canvas.drawText("Phone No: " + entity.getPhoneNo(), 10, y + 20, paint);
            // Add more columns as needed

            y += 40; // Increase y position for the next row
        }

        document.finishPage(page);

        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "TableData.pdf");

        try {
            document.writeTo(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }

        document.close();
    }
}
