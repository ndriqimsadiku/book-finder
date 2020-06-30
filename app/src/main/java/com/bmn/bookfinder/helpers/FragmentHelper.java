package com.bmn.bookfinder.helpers;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bmn.bookfinder.R;

public class FragmentHelper {

    public static boolean isFragmentInBackStack(final FragmentManager fragmentManager, final String fragmentTagName) {

        int backStackEntryCount = fragmentManager.getBackStackEntryCount();

        if (backStackEntryCount > 0) {
            return fragmentTagName.equals(fragmentManager.getBackStackEntryAt(backStackEntryCount - 1).getName());
        }
        return false;
    }

    public static void changeFragment(FragmentManager fragmentManager, Fragment fragment) {
        String backStateName = fragment.getClass().getName();

        boolean fragmentPopped = fragmentManager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped && fragmentManager.findFragmentByTag(backStateName) == null) { //fragment not in back stack, create it.
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.setCustomAnimations(0, R.anim.fade_out, 0, R.anim.fade_out);
            ft.replace(R.id.container, fragment, backStateName);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

}
