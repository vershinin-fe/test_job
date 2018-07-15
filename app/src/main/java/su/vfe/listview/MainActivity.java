package su.vfe.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.listView);

        final String[] numeros = new String[] {
                "Uno", "Dos", "Tres", "Cuatro", "Cinco",
                "Seis", "Siete", "Ocho", "Nueve", "Diez",
                "Once", "Doce", "Trece"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, numeros);

        listView.setAdapter(adapter);
    }
}
