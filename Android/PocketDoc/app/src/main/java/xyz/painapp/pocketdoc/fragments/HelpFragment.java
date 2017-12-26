package xyz.painapp.pocketdoc.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.painapp.pocketdoc.R;

/**
 * Created by keyur on 10/16/17.
 * Package: ${PACKAGE_NAME} as part of PocketDoc
 */

public class HelpFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.help_view, container, false);
    }
}
