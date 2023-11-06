package br.edu.ifbaiano.guanambi.aplicacaoshape.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import br.edu.ifbaiano.guanambi.aplicacaoshape.R;
import br.edu.ifbaiano.guanambi.aplicacaoshape.dao.UserDAO;
import br.edu.ifbaiano.guanambi.aplicacaoshape.model.User;

public class ListActivity extends AppCompatActivity {

    ListView listusers;
    UserDAO uDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listusers = findViewById(R.id.listUsers);

        uDao = new UserDAO(getApplicationContext(), new User());

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(),
                R.layout.layout_users,
                uDao.listarUsers(),
                new String[]{"_id","nome"},
                new int[]{R.id.lay_email, R.id.lay_nome}, 0);
        listusers.setAdapter(adapter);


    }
}