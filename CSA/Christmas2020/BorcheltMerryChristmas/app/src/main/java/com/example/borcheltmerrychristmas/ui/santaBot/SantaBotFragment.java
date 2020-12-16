package com.example.borcheltmerrychristmas.ui.santaBot;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.borcheltmerrychristmas.R;
import java.util.ArrayList;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class SantaBotFragment extends Fragment {
    private static final String TAG = SantaBotFragment.class.getName();
    SantaBotViewModel santaBotViewModel;
    TextToSpeech maggiesVoice;
    EditText keystrokeEditText;
    private TextView txtSpeechInput;
    Button submitTextInputButton;
    Button speechToTextButton;
    //Magpie4 maggie;
    private final int REQ_CODE_SPEECH_INPUT = 100;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        santaBotViewModel =
                new ViewModelProvider(this).get(SantaBotViewModel.class);
        View root = inflater.inflate(R.layout.fragment_santabot, container, false);
        txtSpeechInput = (TextView) root.findViewById(R.id.txtSpeechInput);
        keystrokeEditText = (EditText) root.findViewById(R.id.keystrokeInput);
        submitTextInputButton = (Button) root.findViewById(R.id.submitTextInput);
        speechToTextButton = (Button) root.findViewById(R.id.speechToTextButton);
        //maggie = new Magpie4();

        maggiesVoice=new TextToSpeech(getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    maggiesVoice.setLanguage(Locale.US);
                    maggiesVoice.setPitch(1.8f);
                }
            }
        });

        submitTextInputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = keystrokeEditText.getText().toString();
                processUserInput(userInput);
            }
        });

        speechToTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.speech_prompt));

                try {
                    startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getContext(),
                            getString(R.string.speech_not_supported),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String userInput = result.get(0);
                    txtSpeechInput.setText("You said: " + userInput);
                    processUserInput(userInput);

                }
                break;
            }

        }
    }

    private void processUserInput(String userInput){
        String response = userInput;//maggie.getResponse(userInput);
        if(response.equals("")){
            Toast.makeText(getContext(),response,Toast.LENGTH_SHORT).show();
            maggiesVoice.speak(response,TextToSpeech.QUEUE_FLUSH,null,null);
        }
        else {
            String greeting = userInput;
            Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
            maggiesVoice.speak(greeting, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }
}