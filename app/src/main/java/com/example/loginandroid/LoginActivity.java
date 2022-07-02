package com.example.loginandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginandroid.Interfaz.PeticionUsuario;
import com.example.loginandroid.Model.Usuario;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {
    public static Retrofit varRetro;
    public String status;
    public Button  btn_login;
    public TextInputEditText txtCorreo;
    public TextInputEditText txtContrasenia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = (Button) findViewById(R.id.btnIniciarSesion);
        txtCorreo = (TextInputEditText) findViewById(R.id.in_correo);
        txtContrasenia = (TextInputEditText) findViewById(R.id.in_contrasenia1);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtCorreo.getText().toString().equals(null) && txtContrasenia.toString().equals(null)){
                    Toast.makeText(getApplicationContext(), "Llena los campos", Toast.LENGTH_LONG).show();
                }else{
                    varRetro = new Retrofit.Builder()
                            .baseUrl("https://viqoxwhm.lucusvirtual.es/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    PeticionUsuario peticion = varRetro.create(PeticionUsuario.class);
                    Call<Usuario> login = peticion.verificar(txtCorreo.getText().toString(), txtContrasenia.getText().toString());
                    login.enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                            if (!response.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Error intentalo de nuevo", Toast.LENGTH_LONG).show();
                            }else{
                                Usuario usuario = response.body();
                                if  (usuario.getEstatus() != null){
                                    if ((usuario.getEstatus() != "error")){
                                        String content = "";
                                        content+= usuario.getMensaje();
                                        Toast.makeText(getApplicationContext(), content, Toast.LENGTH_LONG).show();
                                    }
                                }else{
                                    String content = "";
                                    content+= usuario.getNombre()+"\t";
                                    content+= usuario.getApellidoPaterno()+"\t";
                                    content+= usuario.getApellidoMaterno()+"\t";
                                    String datos = "id: " + usuario.getID()+"t";
                                    datos = "id: " + usuario.getID()+"t";
                                    datos = "nombre: " + usuario.getID()+"t";
                                    datos = "apellidoPaterno: " + usuario.getID()+"t";
                                    datos = "apellidoMaterno: " + usuario.getID()+"t";
                                    datos = "correo: " + usuario.getID()+"t";
                                    datos = "contrasenia: " + usuario.getID()+"t";
                                    datos = "fechaNacimiento: " + usuario.getID()+"t";
                                    datos = "status: " + usuario.getID()+"t";
                                    datos = "codigoConfirmacion: " + usuario.getID()+"t";
                                    datos = "tokenRecovery: " + usuario.getID()+"t";

                                    Toast.makeText(getApplicationContext(), "Bienvenido "+ content, Toast.LENGTH_LONG).show();
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<Usuario> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Error algo salio mal", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
    }
    public void irRegistro(View view){
        Intent i = new Intent(LoginActivity.this, RegistroActivity.class);
        startActivity(i);
    }
}