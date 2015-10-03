//Portions of this page are modifications based on work created and
// shared by the Android Open Source Project and used according to
// terms described in the Creative Commons 2.5 Attribution License.
//Modified by Adriano Marini, 01.10.2015
//http://developer.android.com/guide/topics/ui/dialogs.html

package ca.ualberta.cs.marini_reflex;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by adrianomarini on 2015-10-02.
 */
public class InstructionDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_string)
                .setPositiveButton(R.string.ok_dialog, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ReflexTesting.reflexGame();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}

