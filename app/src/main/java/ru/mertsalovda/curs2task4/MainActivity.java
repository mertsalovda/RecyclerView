package ru.mertsalovda.curs2task4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements  ComplexRecyclerViewAdapter.OnItemClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, RecyclerViewFragment.newInstance())
                    .commit();
        }
    }

    @Override
    public void onItemClick() {
        Toast.makeText (this, "clicked",Toast.LENGTH_SHORT).show();
    }
}
