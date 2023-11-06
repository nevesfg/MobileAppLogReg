package br.edu.ifbaiano.guanambi.aplicacaoshape.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifbaiano.guanambi.aplicacaoshape.R;
import br.edu.ifbaiano.guanambi.aplicacaoshape.dao.UserDAO;
import br.edu.ifbaiano.guanambi.aplicacaoshape.model.User;

public class AttActivity extends AppCompatActivity {
    EditText edtNome, edtSenha;
    TextView txtemail;
    Button btnAtualizar;
    UserDAO uDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_att);

        edtNome = findViewById(R.id.edtNome);
        txtemail = findViewById(R.id.edtEmail);
        edtSenha = findViewById(R.id.edtSenha);
        btnAtualizar = findViewById(R.id.btnAtualizar);

        SharedPreferences sp = getSharedPreferences("appLogin", Context.MODE_PRIVATE);
        String email = sp.getString("email","");

        txtemail.setText(email);

        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = txtemail.getText().toString();
                String nome = edtNome.getText().toString();
                String senha = edtSenha.getText().toString();
                uDao = new UserDAO(getApplicationContext(), new User("", "", ""));

                User dados = new User(email, nome, senha);
                uDao = new UserDAO(getApplicationContext(), dados);

                uDao.updateUserDetails(email, nome, senha);

                if (uDao.update()) {
                    Toast.makeText(AttActivity.this, "Dados atualizados", Toast.LENGTH_SHORT).show();
                    Intent redirecionar = new Intent(AttActivity.this, ActivityPrincipal.class);
                    startActivity(redirecionar);
                } else {
                    Toast.makeText(AttActivity.this, "Erro ao atualizar", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}