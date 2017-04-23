package com.iv.contactostarea;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;

import static com.iv.contactostarea.R.id.btnSiguiente;
import static com.iv.contactostarea.R.id.datePicker;
import static com.iv.contactostarea.R.string.nombre;

public class MainActivity extends AppCompatActivity {

    private DatePicker picker;
    private Calendar calendario;
    private TextView tvDisplay;
    private int year, month, day;

    private TextInputEditText txtInputNombre;
    private TextInputEditText txtInputTelefono;
    private TextInputEditText txtInputEmail;
    private TextInputEditText txtInputDescripcion;
    private TextView tvFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtInputNombre = (TextInputEditText) findViewById(R.id.txtInputNombre);
        txtInputTelefono = (TextInputEditText) findViewById(R.id.txtInputTelefono);
        txtInputEmail = (TextInputEditText) findViewById(R.id.txtInputEmail);
        txtInputDescripcion = (TextInputEditText) findViewById(R.id.txtInputDescripcion);
        tvFecha =(TextView) findViewById(R.id.tvPicker);
        picker = (DatePicker) findViewById(R.id.datePicker);

        Button btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                year = picker.getYear();
                month = picker.getMonth()+1;
                day = picker.getDayOfMonth();

                //getDateFromDatePicher(picker);

                final String fechaNacimiento = day +"/" + month + "/" + year;

                Contacto contacto = new Contacto(txtInputNombre.getEditableText().toString(),fechaNacimiento,txtInputTelefono.getEditableText().toString(),txtInputEmail.getEditableText().toString(),txtInputDescripcion.getEditableText().toString());

                Intent intent = new Intent(MainActivity.this, confirmarDatos.class);
                intent.putExtra("nombre",contacto.getNombre());
                intent.putExtra("fecha",contacto.getFecha());
                intent.putExtra("telefono",contacto.getTelefono());
                intent.putExtra("email",contacto.getEmail());
                intent.putExtra("descripcion",contacto.getDescripcion());
                startActivityForResult(intent,1);
                //finish();
                //Toast.makeText(getBaseContext(), fecha1, Toast.LENGTH_LONG).show();

            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK)
            {
                String nombre = data.getStringExtra("nombre");
                String fecha = data.getStringExtra("fecha");
                String telefono = data.getStringExtra("telefono");
                String email = data.getStringExtra("email");
                String descripcion = data.getStringExtra("descripcion");

                txtInputNombre.setText(nombre);
                tvFecha.setText(fecha);
                txtInputTelefono.setText(telefono);
                txtInputEmail.setText(email);
                txtInputDescripcion.setText(descripcion);
            }

        }
    }

    public static java.util.Date getDateFromDatePicher(DatePicker picker){
        int year = picker.getYear();
        int month = picker.getMonth()+1;
        int day = picker.getDayOfMonth();
        Calendar calendario = Calendar.getInstance();
        calendario = Calendar.getInstance();
        return calendario.getTime();

    }
}
