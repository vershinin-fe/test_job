package su.vfe.listview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

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

        ArrayAdapter<String> adapter = new CustomArrayAdapter(this, numeros);

        listView.setAdapter(adapter);
    }

    private static class CustomArrayAdapter extends ArrayAdapter<String> {
        private final Context context;
        private final String[] values;

        public CustomArrayAdapter(Context context, String[] values) {
            super(context, R.layout.row_layout, values);
            this.context = context;
            this.values = values;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.row_layout, parent, false);

            TextView textView = (TextView) rowView.findViewById(R.id.text);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.preview);

            textView.setText(values[position]);
            Picasso.get().load("https://defcon.ru/wp-content/uploads/2015/12/ico_android-3.png").fit().into(imageView);

            return rowView;
        }
    }
}
