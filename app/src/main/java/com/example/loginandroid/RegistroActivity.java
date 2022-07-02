package com.example.loginandroid;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginandroid.Interfaz.PeticionUsuario;
import com.example.loginandroid.Model.Usuario;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegistroActivity extends AppCompatActivity {

    DatePickerDialog picker;
    EditText fechaNacimiento;
    Button bn;
    TextView txtIr;
    EditText edNombre,edApP,edApM,edCorreo,edCont1,edCont2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        bn = (Button) findViewById(R.id.btn_Registrar);
        txtIr = (TextView) findViewById(R.id.txt_irLogin);
        edNombre = (EditText) findViewById(R.id.in_nombre);
        edApP = (EditText) findViewById(R.id.in_apellidoP);
        edApM = (EditText) findViewById(R.id.in_apellidoM);
        fechaNacimiento = (EditText) findViewById(R.id.in_fechaNacimieto);
        edCorreo = (EditText) findViewById(R.id.in_correo);
        edCont1 = (EditText) findViewById(R.id.in_contrasenia1);
        edCont2 = (EditText) findViewById(R.id.in_contrasenia2);

        fechaNacimiento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus)
                    showDatePicker();
            }
        });
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edNombre.getText().toString().equals("") || edApP.getText().toString().equals("") || edApM.getText().toString().equals("") || edCorreo.getText().toString().equals("") || edCont1.getText().toString().equals("") ||edCont2.getText().toString().equals("") || fechaNacimiento.getText().toString().equals("")){
                    muestraToast(view, "Llena todos los campos");
                }else{
                    /*registroApi rg = new registroApi();
                    try {
                        rg.registrar(edNombre.getText().toString(), edApP.getText().toString(), edApM.getText().toString(), edCorreo.getText().toString(), edCont1.getText().toString(), edCont2.getText().toString(), "2001-08-13");
                        muestraToast(view,"registro exitoso");
                        Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }catch (Exception ex){
                        muestraToast(view,"Error"+ex);
                    }*/
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://viqoxwhm.lucusvirtual.es/api/user/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    PeticionUsuario peticion = retrofit.create(PeticionUsuario.class);
                    Usuario usuario = new Usuario();
                    usuario.setName(edNombre.getText().toString());
                    usuario.setApPat(edApP.getText().toString());
                    usuario.setApMat(edApM.getText().toString());
                    usuario.setEmail(edCorreo.getText().toString());
                    usuario.setPassword(edCont1.getText().toString());
                    usuario.setValidate(edCont2.getText().toString());
                    usuario.setFecha_nacimiento("2001-05-01");
                    usuario.setCode("1970-01-01 02:07:38");
                    Call<Usuario> registro = peticion.registrar(usuario);
                    registro.enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                            if (!response.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Error revisa correo o contrasenia", Toast.LENGTH_LONG).show();
                            }else{
                                Usuario usuario = response.body();
                                muestraToast(view, "OK");
                            }
                        }

                        @Override
                        public void onFailure(Call<Usuario> call, Throwable t) {

                        }
                    });
                }

            }
        });
        txtIr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }

    public void muestraToast(View view,String mensaje) {
        Toast.makeText(this, ""+mensaje, Toast.LENGTH_SHORT).show();
    }

    public void showDatePicker() {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        picker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fechaNacimiento.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
            }
        }, year, month, day);
        picker.show();
    }
}