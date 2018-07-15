package su.vfe.listview;

import android.content.Context;
import android.os.AsyncTask;
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
import java.io.IOException;
import java.net.URL;
import java.util.List;

import su.vfe.listview.model.Item;
import su.vfe.listview.utils.JsonUtils;
import su.vfe.listview.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    public static String API_URL = "http://jsonplaceholder.typicode.com/photos?albumId=10";

    private List<Item> itemsList;

    private ListView mItemsDisplayList;

    private String response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mItemsDisplayList = (ListView)findViewById(R.id.listView);

        URL apiUrl = NetworkUtils.buildUrl(API_URL);
        new ApiQueryTask().execute(apiUrl);
    }

    private static class CustomArrayAdapter extends ArrayAdapter<Item> {
        private final Context context;
        private final Item[] values;

        public CustomArrayAdapter(Context context, Item[] values) {
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

            textView.setText(values[position].getTitle());
            Picasso.get().load(values[position].getUrl()).fit().into(imageView);

            return rowView;
        }
    }

    public class ApiQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... params) {
            URL requestUrl = params[0];
            String requestResults = null;
            try {
                requestResults = NetworkUtils.getResponseFromHttpUrl(requestUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return requestResults;
        }

        @Override
        protected void onPostExecute(String requestResults) {
            if (requestResults != null && !requestResults.equals("")) {
                itemsList = JsonUtils.getItemsListFromJson(requestResults);

                ArrayAdapter<Item> adapter = new CustomArrayAdapter(MainActivity.this, itemsList.toArray(new Item[itemsList.size()]));

                mItemsDisplayList.setAdapter(adapter);
            }
        }
    }
}
