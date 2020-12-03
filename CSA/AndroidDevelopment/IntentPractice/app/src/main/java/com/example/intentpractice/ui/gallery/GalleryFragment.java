package com.example.intentpractice.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.intentpractice.DataToPass;
import com.example.intentpractice.MainActivity;
import com.example.intentpractice.MyBrandSpankinNewActivity;
import com.example.intentpractice.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        Button mb = root.findViewById(R.id.button);
        mb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent objName = new Constructor(on, goto);
                Intent intent = new Intent(getActivity(), MyBrandSpankinNewActivity.class);

                intent.putExtra("Greeting Message","Hola");
                intent.putExtra(DataToPass.ITEM_UNO, "uno mas");
                intent.putExtra(DataToPass.ITEM_DOS, "dos mas");
                //obj.method(name of data, actual data

                startActivity(intent);

            }
        });


        return root;
    }
}