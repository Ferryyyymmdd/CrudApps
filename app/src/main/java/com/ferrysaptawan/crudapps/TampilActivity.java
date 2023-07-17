package com.ferrysaptawan.crudapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TampilActivity extends AppCompatActivity {
    private DBHelper dbHelper;
    private TextView textViewData;

    Button btnhapus;

    EditText hapus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);

        dbHelper = new DBHelper(this);
        textViewData = findViewById(R.id.textViewData);
        btnhapus = findViewById(R.id.btnhapus);
        hapus = findViewById(R.id.hapus);

        btnhapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nimToDelete = hapus.getText().toString();

                boolean isDeleted = dbHelper.deleteData(nimToDelete);

                if (isDeleted) {
                    Toast.makeText(TampilActivity.this, "Record deleted successfully", Toast.LENGTH_SHORT).show();
                    refreshData();
                } else {
                    Toast.makeText(TampilActivity.this, "Failed to delete record", Toast.LENGTH_SHORT).show();
                }
            }
        });


        Cursor res = dbHelper.tampildata();
        if (res.getCount() == 0) {
            Toast.makeText(TampilActivity.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
        } else {
            StringBuilder data = new StringBuilder();
            while (res.moveToNext()) {
                String kode = res.getString(0);
                String nama = res.getString(1);
                String jumlah = res.getString(2);


                data.append("Kode: ").append(kode).append("\n");
                data.append("Nama: ").append(nama).append("\n");
                data.append("Jumlah: ").append(jumlah).append("\n\n");

            }
            textViewData.setText(data.toString());
        }

        res.close();
    }

    private void loadData() {
        Cursor res = dbHelper.tampildata();
        if (res.getCount() == 0) {
            Toast.makeText(TampilActivity.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
        } else {
            StringBuilder data = new StringBuilder();
            while (res.moveToNext()) {
                String kode = res.getString(0);
                String nama = res.getString(1);
                String jumlah = res.getString(2);

                data.append("Kode: ").append(kode).append("\n");
                data.append("Nama: ").append(nama).append("\n");
                data.append("Jumlah: ").append(jumlah).append("\n\n");
            }

            textViewData.setText(data.toString());
            res.close();
        }
    }

    private void refreshData() {
        textViewData.setText("");
        loadData();
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TampilActivity.this, DashboardActivity.class);
        startActivity(intent);
    }
}