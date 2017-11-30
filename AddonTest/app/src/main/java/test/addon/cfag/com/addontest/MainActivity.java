package test.addon.cfag.com.addontest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import test.addon.cfag.com.addontest.adapters.AddonsAdapter;
import test.addon.cfag.com.addontest.model.AddonItem;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private AddonsAdapter addonsAdapter;
    private List<AddonItem> listAddons;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setOnItemClickListener(this);

        findAddOnActivities();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void findAddOnActivities() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory("com.cfag.addons.intent.category.ADDON");

        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);

        if(list != null && !list.isEmpty()) {
            listAddons = new ArrayList<>(0);
            // list contains all activities that match your filters in mainIntent
            for (ResolveInfo resolveInfo : list) {
                listAddons.add(new AddonItem((String) resolveInfo.loadLabel(pm), resolveInfo.loadIcon(pm),
                        resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
            }

            addonsAdapter = new AddonsAdapter(this, listAddons, R.layout.addon_item);
            gridView.setAdapter(addonsAdapter);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.i("AddonTest", "position>>"+position);
        AddonItem addonItem = listAddons.get(position);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(addonItem.pakcageName, addonItem.className));
        startActivity(intent);
    }
}
