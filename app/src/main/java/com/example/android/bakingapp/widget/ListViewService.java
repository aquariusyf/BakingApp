package com.example.android.bakingapp.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;


public class ListViewService extends RemoteViewsService {

    private static final String LOG_TAG = ListViewService.class.getSimpleName();

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        ListViewProvider listViewProvider =
                new ListViewProvider(this.getApplicationContext(), intent);
        return listViewProvider;
    }
}
