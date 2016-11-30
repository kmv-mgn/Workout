package com.hfad.workout;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity implements WorkoutListFragment.WorkoutListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*   //прямое задание значений фрагменту
        WorkoutDetailFragment frag = (WorkoutDetailFragment)
                getFragmentManager().findFragmentById(R.id.detail_frag);
        frag.setWorkout(1);*/

    }

    @Override
    public void itemClicked(long id) {
        //Здесь размещается код отображения подробной информаици
        View fragmentContainer = findViewById(R.id.fragment_container);
        if (fragmentContainer != null) {
            //если фрагмент fragment_container существует, занчит используется макет для планшетов
            WorkoutDetailFragment details = new WorkoutDetailFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            details.setWorkout(id);
            ft.replace(R.id.fragment_container, details);
            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        } else {   //фрагмент не найден, значит используется макет для телефонов
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra(DetailActivity.EXTRA_WORKOUT_ID, (int)id);
            startActivity(intent);
        }


    }
}
