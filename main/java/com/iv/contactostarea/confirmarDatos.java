package com.iv.contactostarea;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class confirmarDatos extends AppCompatActivity {

    private TextView tvNombre;
    private TextView tvFecha;
    private TextView tvTelefono;
    private TextView tvEmail;
    private TextView tvDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_datos);


        tvNombre = (TextView) findViewById(R.id.txtNombre);
        tvFecha = (TextView) findViewById(R.id.txtFecha);
        tvTelefono = (TextView) findViewById(R.id.txtTelefono);
        tvEmail = (TextView) findViewById(R.id.txtEmail);
        tvDescripcion = (TextView) findViewById(R.id.txtDescripcion);

        Bundle parametros = getIntent().getExtras();
        final String nombre = parametros.getString("nombre");
        final String fecha = parametros.getString("fecha");
        final String telefono = parametros.getString("telefono");
        final String email = parametros.getString("email");
        final String descripcion = parametros.getString("descripcion");

        tvNombre.setText(nombre);
        tvFecha.setText("Fecha de Naciemiento: " + fecha);
        tvTelefono.setText("Telefono: " + telefono);
        tvEmail.setText("Email: " + email);
        tvDescripcion.setText("Descripcion: " + descripcion);

        Button btnEditar = (Button) findViewById(R.id.btnEditar);
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Contacto contacto = new Contacto(nombre, fecha, telefono, email, descripcion);
                Intent intent = new Intent(confirmarDatos.this, MainActivity.class);
                intent.putExtra("nombre",contacto.getNombre());
                intent.putExtra("fecha",contacto.getFecha());
                intent.putExtra("telefono",contacto.getTelefono());
                intent.putExtra("email", contacto.getEmail());
                intent.putExtra("descripcion", contacto.getDescripcion());
                //Toast.makeText(getBaseContext(),"Boton Editar", Toast.LENGTH_SHORT).show();
                //Toast.makeText(getBaseContext(),nombre, Toast.LENGTH_SHORT).show();
                setResult(RESULT_OK,intent);
                finish();
            }
        });

    }

}
