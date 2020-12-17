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

import com.example.borcheltmerrychristmas.ChristmasCountdown;
import com.example.borcheltmerrychristmas.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static android.app.Activity.RESULT_OK;

public class SantaBotFragment extends Fragment {
    private static final String TAG = SantaBotFragment.class.getName();
    private SantaBotViewModel santaBotViewModel;
    private TextToSpeech maggiesVoice;
    private EditText keystrokeEditText;
    private TextView txtSpeechInput;
    private TextView txtSantaOutput;
    private Button submitTextInputButton;
    private Button speechToTextButton;

    public static ArrayList<String> christmasList = new ArrayList<String>();

    private String name;
    private String gender;

    private Random random;


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
        txtSantaOutput = (TextView) root.findViewById(R.id.txtSantaOutPut);
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
                try {
                    processUserInput(userInput);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
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
                    try {
                        processUserInput(userInput);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
                break;
            }

        }
    }

    private void processUserInput(String userInput) throws ParseException {
        String[] responseArray;
        String response = userInput;//maggie.getResponse(userInput);
        response = response.toLowerCase();
        if (response.equals("")) {
            Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
            maggiesVoice.speak("Ho, ho, ho, I didn't get that!", TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            String greeting = "";
            if (response.contains("until") && response.contains("christmas")) {
                String[] timeUntil = ChristmasCountdown.getTimeLeft();
                greeting = timeUntil[0].concat(" days, ".concat(timeUntil[1].concat(" hours, ".concat(timeUntil[2].concat(" minutes, and ".concat(timeUntil[3].concat(" seconds until Christmas")))))));
            } else if (response.contains("good") && (response.contains("boy") || response.contains("girl"))) {
                if (response.contains("boy")) gender = "boy";
                else gender = "girl";
                if (random.nextBoolean()) greeting = "You sure have";
                else greeting = "Actually. you haven't... A lump of coal for you";
            } else if (response.contains("remember")) {
                if (response.contains("name")) {
                    greeting = "Yes, it is ".concat(name);
                }
                if (response.contains("ask")) {
                    if (christmasList.size() == 0) greeting = "You did not ask for anything";
                    else if (christmasList.size() == 1)
                        greeting = "You asked for a ".concat(christmasList.get(0));
                    else {
                        greeting = "You asked for ";
                        for (byte i = 0; i < christmasList.size() - 1; i++) {
                            greeting.concat(christmasList.get(i).concat(", "));
                        }
                        greeting.concat(" and".concat(christmasList.get(christmasList.size() - 1)));
                    }
                }
            } else if (response.contains("want")) {
                responseArray = response.split(" ");
                String wantedToy = responseArray[getIndexOf(responseArray, "a")+1];
                for(short i = (short) (getIndexOf(responseArray, "a")+1); i<responseArray.length; i++){
                    wantedToy.concat(" ".concat(responseArray[i]));}
                try{
                if (christmasList.contains(wantedToy)) {
                    greeting = "You want 2 ".concat(wantedToy.concat("s for Christmas!? Let's maybe just stick to one."));
                } else if (response.contains(" a ")) {
                    greeting = "Okay little one.";
                    christmasList.add(wantedToy);
                } else {
                    greeting = "I am sorry, you want \"A\" what for Christmas";
                }}
                catch (Exception e){
                    greeting = "Okay little one.";
                    christmasList.add(wantedToy);
                }
            } else if (response.contains("dangerous")) {
                greeting = "Yippee Ki Yay!";
            } else if (response.contains("see") && response.contains("sleep")) {
                greeting = "Checking to see if you are naughty or nice!";
            } else if (response.contains("eat")) {
                greeting = "The four main food groups: candy, candy canes, candy corn, and syrup";
            } else if (response.contains("gun")) {
                greeting = "Ho, Ho, Ho, you'll shoot your eye out";
            } else if (response.contains("left") || response.contains("lost")) {
                greeting = "Oh, you better call your parents. Make sure to lock the doors as well!";
            } else if (response.contains("mrs. clause")) {
                greeting = "Mrs. Clause is at the North Pole managing the elves!";
            } else if (response.contains("name") && response.contains("my")) {
                responseArray = response.split(" ");
                name = responseArray[getIndexOf(responseArray, "is") + 1];
                greeting = "Hi, I'm Santa, " + name;}
            else if (response.contains("elves")) {
                    if (response.contains("look")) {
                        greeting = "Well, they have pointy ears and a human looking face...?";
                    } else if (response.contains("where")) {
                        greeting = "They are defending the lands against Sauron! Wait, no they make toys for good little boys and girls.";
                    } else {
                        greeting = "I am sorry, I do not understand the question";
                    }
                } else {
                    greeting = "I am sorry, i do not understand the question.";
                }


                Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                maggiesVoice.speak(greeting, TextToSpeech.QUEUE_FLUSH, null, null);
                txtSantaOutput.setText(greeting);
            }
        }
    public static int getIndexOf(String[] allItems, String name){
        for(byte i=0; i<allItems.length; i++) {
            name = name.toLowerCase();
            if (allItems[i].contains(name)) {
                return i;
            }
        }
        return -1;
    }
}