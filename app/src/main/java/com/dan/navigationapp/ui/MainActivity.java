package com.dan.navigationapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.dan.navigationapp.BaseActivity;
import com.dan.navigationapp.R;
import com.dan.navigationapp.model.MenuModel;
import com.dan.navigationapp.network.NetworkService;
import com.dan.navigationapp.storage.StorageHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    NavigationView navigationView;
    ProgressBar progressBar;
    private List<MenuModel.MenuItem> menuItems = null;
    StorageHelper storageHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init StorageHelper
        storageHelper = new StorageHelper(MainActivity.this);
        //init toolebar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //init Navigation menu
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //init progressBar
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        downLoadMenu();

    }



    private void downLoadMenu(){

        showProgressBar();
        NetworkService.getInstance()
                .getJSONApi()
                .getMenuById(1)
                .enqueue(new Callback<MenuModel>() {
                    @Override
                    public void onResponse(@NonNull Call<MenuModel> call, @NonNull Response<MenuModel> response) {
                        menuItems = response.body().getMenu();
                        for(int i = 0 ; i < menuItems.size(); i++){
                            navigationView.getMenu().add(0,i,i,menuItems.get(i).getName());
                        }
                        setLastMenuPosition();
                        hideProgressBar();
                    }
                    @Override
                    public void onFailure(@NonNull Call<MenuModel> call, @NonNull Throwable t) {
                        hideProgressBar();
                    }
                });
    }

    private  void setLastMenuPosition(){
        String menuTitle = storageHelper.getLastMenuTitle();
        openFragment(menuTitle);
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
//        Log.d("qwer","save:"+item.getItemId());
        Log.d("qwer","save:"+item.getTitle());
        storageHelper.saveLastMenuTitle(item.getTitle().toString());
        openFragment(item.getTitle().toString());
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    private void openFragment(String menuTitle){


        Fragment fragment = null;
        Class fragmentClass = null;
        Bundle bundle = new Bundle();

        if(menuTitle == null){
            bundle.putString("text",  menuItems.get(0).getParam());
            fragmentClass = TextFragment.class;
        }else{
            for (int i = 0; i < menuItems.size(); i++){
                MenuModel.MenuItem menu =  menuItems.get(i);

                if(menuTitle.equals(menu.getName())){
                    String param = menu.getParam();
                    switch(menu.getFunction()){
                        case "text":
                            bundle.putString("text", param);
                            fragmentClass = TextFragment.class;
                            break;
                        case "image":
                            bundle.putString("img_path",param );
                            fragmentClass = ImageFragment.class;
                            break;
                        case "url":
                            bundle.putString("url", param);
                            fragmentClass = WebFragment.class;
                            break;

                    }
                }
            }
        }





        try {
            fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.app_container, fragment).commit();
    }

    private void showProgressBar(){
        progressBar.setVisibility(ProgressBar.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(ProgressBar.GONE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }


}
