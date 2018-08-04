package com.developervishalsehgal.constraintlayoutchallenge;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View view) {
        int id = view.getId();

        Intent intent = null;
        switch (id) {
            case R.id.twitter_iv:
                try {
                    this.getPackageManager().getPackageInfo("com.twitter.android", 0);
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=vishalsehgal31"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                } catch (PackageManager.NameNotFoundException e) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/vishalsehgal31"));
                }
                startActivity(intent);
                break;

            case R.id.g_plus_iv:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/" + AppInformations.GPLUS_PROFILE));
                try {
                    if (MainActivity.this.getPackageManager().getPackageInfo("com.google.android.apps.plus", 0) != null) {
                        intent.setPackage("com.google.android.apps.plus");
                    }
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/" + AppInformations.GPLUS_PROFILE + "/posts")));
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }
                startActivity(intent);
                break;

            case R.id.linkedIn_iv:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("linkedin://add/%/@" + AppInformations.LINKEDIN_PROFILE));
                final PackageManager packageManager = getApplicationContext().getPackageManager();
                final List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                if (list.isEmpty()) {
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.linkedin.com/profile/view?id=" + AppInformations.LINKEDIN_PROFILE));
                }
                startActivity(intent);
                break;
            case R.id.facebook_iv:
                String facebookUrl;
                PackageManager packageManagerr = getApplicationContext().getPackageManager();
                try {
                    int versionCode = packageManagerr.getPackageInfo("com.facebook.katana", 0).versionCode;
                    if (versionCode >= 3002850) {
                        facebookUrl = "fb://facewebmodal/f?href=" + AppInformations.FACEBOOK_URL;
                    } else {
                        facebookUrl = "fb://page/" + AppInformations.FACEBOOK_PAGE_ID;
                    }

                } catch (Exception e) {
                    facebookUrl = AppInformations.FACEBOOK_URL;
                }
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(facebookUrl));
                startActivity(intent);
                break;
            case R.id.github_iv:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.github.com/" + AppInformations.GITHUB_PROFILE));
                startActivity(intent);
                break;
            case R.id.medium_iv:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://medium.com/@codervishalsehgal"));
                startActivity(intent);
                break;
        }
    }
}
