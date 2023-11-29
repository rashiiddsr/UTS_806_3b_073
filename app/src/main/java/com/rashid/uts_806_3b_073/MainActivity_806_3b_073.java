    package com.rashid.uts_806_3b_073;
    import androidx.appcompat.app.AlertDialog;
    import androidx.appcompat.app.AppCompatActivity;
    import android.os.Bundle;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.Spinner;
    import android.widget.Toast;

    public class MainActivity_806_3b_073 extends AppCompatActivity {
        Spinner idMekanikSelect;
        EditText totalBelanja;
        Button proses, batal, keluar;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_806_3b_073);

            idMekanikSelect = findViewById(R.id.id_mekanik);
            totalBelanja = findViewById(R.id.id_total);
            proses = findViewById(R.id.proses);
            batal = findViewById(R.id.batal);
            keluar = findViewById(R.id.keluar);


            proses.setOnClickListener(v -> {
                String idMekanik, namaMekanik, jabatanMekanik;
                long nilaiBelanja, diskonAwal, hargaNext, diskonAkhir, hargaAkhir, pajak, totalBayar;

                idMekanik = idMekanikSelect.getSelectedItem().toString();

                if (idMekanik.equals("100_073")) {
                    namaMekanik = "Arjuna";
                    jabatanMekanik = "Supervisor Mekanik";
                } else if (idMekanik.equals("101_073")) {
                    namaMekanik = "Arya";
                    jabatanMekanik = "Mekanik";
                } else if (idMekanik.equals("102_073")) {
                    namaMekanik = "Adiya";
                    jabatanMekanik = "Administrasi";
                } else {
                    Toast toast = Toast.makeText(this, "Mohon memilih salah satu ID",Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                if (totalBelanja.getText().toString().isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Mohon Masukkan Total Belanja").setNegativeButton("Ok", null).create().show();
                    return;
                }

                nilaiBelanja  = Long.parseLong(totalBelanja.getText().toString());
                diskonAwal = nilaiBelanja * 10/100;
                hargaNext = nilaiBelanja - diskonAwal;


                if (jabatanMekanik.equals("Supervisor Mekanik")) {
                    diskonAkhir = nilaiBelanja * 73 / 40 / 100;
                } else if (jabatanMekanik.equals("Mekanik")) {
                    diskonAkhir = nilaiBelanja * 73 / 20 / 100;
                } else {
                    diskonAkhir = nilaiBelanja * 73 / 10 / 100;
                }

                hargaAkhir = hargaNext - diskonAkhir;
                pajak = hargaAkhir * 10/100;
                totalBayar = hargaAkhir + pajak;

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                String pesanAkhir = "ID Mekanik : " + idMekanik +
                        "\nNama Mekanik : " + namaMekanik +
                        "\nJabatan Mekanik : " + jabatanMekanik +
                        "\nTotal Belanja : "+ nilaiBelanja +
                        "\nDiskon Mekanik (10%) : " + diskonAwal +
                        "\nDiskon Jabatan : " + diskonAkhir +
                        "\nPajak (10%) : " + pajak +
                        "\nTotal Bayar : " + totalBayar;
                builder.setMessage(pesanAkhir).setPositiveButton("Ok", null).create().show();

            });

            batal.setOnClickListener(v -> {
                totalBelanja.setText(null);
                idMekanikSelect.setSelection(0, true);
            });

            keluar.setOnClickListener(v -> {
                finish();
                System.exit(0);
            });
        }
    }