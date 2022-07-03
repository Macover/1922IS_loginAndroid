package com.example.loginandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginandroid.Interfaz.PeticionUsuario;
import com.example.loginandroid.Model.Pokemon;
import com.example.loginandroid.Model.Stats;
import com.example.loginandroid.Model.Usuario;

import java.io.InputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class pokeApi extends AppCompatActivity {

    private Button btn;
    public Retrofit varRetro;
    EditText txtUserPokeName;
    /* Recursos */

    TextView txtId, txtPokeName, txtHeight, txtWeight, txtSpeName;
    TextView txtHP, txtAttack, txtDefense, txtSplAttack, txtSpeed;
    private ImageView imgPoke;

    String imgURL;

    String pokeId, pokeName, pokeHeight, pokeWeight, pokeSpecName;
    String pokeHP, pokeAttack, pokeDefense, pokeSplAttack, pokeSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_api);

        imgPoke = (ImageView) findViewById(R.id.imageViewPoke);
        imgPoke.setImageResource(R.drawable.who_pokemon);
        btn = (Button) findViewById(R.id.btn);
        txtUserPokeName = (EditText) findViewById(R.id.txtUserPokeName);


        txtId = (TextView) findViewById(R.id.id_txt);
        txtPokeName = (TextView) findViewById(R.id.pokeName);
        txtHeight = (TextView) findViewById(R.id.pokeHeight);
        txtWeight = (TextView) findViewById(R.id.pokeWeight);
        txtSpeName = (TextView) findViewById(R.id.pokeSpeciesesName);
        txtHP = (TextView) findViewById(R.id.pokeHP);
        txtAttack = (TextView) findViewById(R.id.pokeAttack);
        txtDefense = (TextView) findViewById(R.id.pokeDefense);
        txtSplAttack = (TextView) findViewById(R.id.pokeSpecialAttack);
        txtSpeed = (TextView) findViewById(R.id.pokeSpeed);

    }

    public void onClickAPI(View view){

        String userPokeName = txtUserPokeName.getText().toString();

        if(userPokeName.length() == 0){
            txtUserPokeName.setError("Ingrese un nombre de un pokemon");
        }else{
            varRetro = new Retrofit.Builder()
                    .baseUrl("https://pokeapi.co/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            PeticionUsuario peticionPokemon = varRetro.create(PeticionUsuario.class);
            Call<Pokemon> pokemon = peticionPokemon.getPokemon(userPokeName);
            pokemon.enqueue(new Callback<Pokemon>() {
                @Override
                public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Error intentalo de nuevo", Toast.LENGTH_LONG).show();
                    }else{
                        Pokemon pokemon = response.body();

                        pokeId = Integer.toString(pokemon.getId());
                        pokeName = pokemon.getName().toString();
                        pokeHeight = Integer.toString(pokemon.getHeight());
                        pokeWeight = Integer.toString(pokemon.getWeight());
                        pokeSpecName = pokemon.getSpecies().getName().toString();
                        pokeHP = Integer.toString(pokemon.getStats().get(0).getBase_stat());
                        pokeAttack = Integer.toString(pokemon.getStats().get(1).getBase_stat());
                        pokeDefense = Integer.toString(pokemon.getStats().get(2).getBase_stat());
                        pokeSplAttack = Integer.toString(pokemon.getStats().get(3).getBase_stat());
                        pokeSpeed = Integer.toString(pokemon.getStats().get(4).getBase_stat());

                        imgURL = pokemon.getSprites().getFront_default().toString();

                        new DownloadImageTask((ImageView) findViewById(R.id.imageViewPoke))
                                .execute(imgURL);

                        txtId.setText(pokeId);
                        txtPokeName.setText(pokeName);
                        txtHeight.setText(pokeHeight);
                        txtWeight.setText(pokeWeight);
                        txtSpeName.setText(pokeSpecName);
                        txtHP.setText(pokeHP);
                        txtAttack.setText(pokeAttack);
                        txtDefense.setText(pokeDefense);
                        txtSplAttack.setText(pokeSplAttack);
                        txtSpeed.setText(pokeSpeed);
                    }
                }
                @Override
                public void onFailure(Call<Pokemon> call, Throwable t) {

                }
            });
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}