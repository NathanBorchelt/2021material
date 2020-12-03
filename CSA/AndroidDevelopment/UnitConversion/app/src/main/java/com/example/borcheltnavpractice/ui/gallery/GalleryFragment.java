package com.example.borcheltnavpractice.ui.gallery;

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

import com.example.borcheltnavpractice.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private int n = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        //this line lads in the xml for the screen
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        //created the textView with the default message
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        final int[] m = {0};
        Button mb = root.findViewById(R.id.button);
        mb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(n%2==0){
                    textView.setText("Hey what's up?");
                    textView.append(String.valueOf(n));
                    textView.append(String.valueOf(m[0]));
                }
                else{
                    textView.setText("Bruh");
                }
                n++;
                m[0]++;
            }
        });



        return root;
    }
}