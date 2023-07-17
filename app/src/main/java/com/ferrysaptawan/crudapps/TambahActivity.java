package com.ferrysaptawan.crudapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TambahActivity extends AppCompatActivity {
    EditText kode, nama, jumlah;
    Button simpan, tampil, edit, hapus;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        kode = findViewById(R.id.edtkode);
        nama = findViewById(R.id.edtnama);
        jumlah = findViewById(R.id.edtjumlah);
        simpan = findViewById(R.id.btnsimpan);
        tampil = findViewById(R.id.btntampil);
        edit = findViewById(R.id.btnedit);
        hapus = findViewById(R.id.btnhapus);
        db = new DBHelper(this);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isikode = kode.getText().toString();
                String isinama = nama.getText().toString();
                String isijumlah = jumlah.getText().toString();

                if (isikode.isEmpty() || isinama.isEmpty() || isijumlah.isEmpty()) {
                    Toast.makeText(TambahActivity.this, "Semua Field Wajib diIsi", Toast.LENGTH_LONG).show();
                } else {
                    boolean checkkode = db.checkkode(isikode);
                    if (!checkkode) {
                        boolean insert = db.insertBiodata(isikode, isinama, isijumlah);
                        if (insert) {
                            Toast.makeText(TambahActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(TambahActivity.this, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(TambahActivity.this, "Data Obat Sudah Ada", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = db.tampildata();
                if (res.getCount() == 0) {
                    Toast.makeText(TambahActivity.this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
                } else {
                    StringBuilder data = new StringBuilder();
                    while (res.moveToNext()) {
                        String kodeData = res.getString(0);
                        String namaData = res.getString(1);
                        String jumlahData = res.getString(2);

                        data.append("Kode: ").append(kodeData).append("\n");
                        data.append("Nama: ").append(namaData).append("\n");
                        data.append("Jumlah: ").append(jumlahData).append("\n\n");
                    }

                    Toast.makeText(TambahActivity.this, data.toString(), Toast.LENGTH_SHORT).show();
                }

                res.close();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newkode = kode.getText().toString();
                String newNama = nama.getText().toString();
                String newjumlah = jumlah.getText().toString();


                if (TextUtils.isEmpty(newkode) || TextUtils.isEmpty(newNama) || TextUtils.isEmpty(newjumlah)) {
                    Toast.makeText(TambahActivity.this, "Semua Field Wajib diIsi", Toast.LENGTH_LONG).show();
                } else {
                    boolean isUpdated = db.updateData(newkode, newNama, newjumlah);

                    if (isUpdated) {
                        Toast.makeText(TambahActivity.this, "Data berhasil diupdate", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(TambahActivity.this, "Data gagal diupdate", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kodehapus = kode.getText().toString();

                boolean isDeleted = db.deleteData(kodehapus);

                if (isDeleted) {
                    Toast.makeText(TambahActivity.this, "Record deleted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TambahActivity.this, "Failed to delete record", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TambahActivity.this, DashboardActivity.class);
        startActivity(intent);
    }
}